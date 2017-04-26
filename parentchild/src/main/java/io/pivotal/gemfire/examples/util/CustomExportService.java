/*
 * =========================================================================
 * Copyright (c) 2014-2016 Pivotal Software, Inc. All Rights Reserved.
 * This product is protected by U.S. and international copyright
 * and intellectual property laws. Pivotal products are covered by
 * one or more patents listed at http://www.pivotal.io/patents.
 * =========================================================================
 */
package io.pivotal.gemfire.examples.util;

import io.pivotal.gemfire.examples.function.CollectKeysFunction;
import io.pivotal.gemfire.examples.function.DeleteCollectedKeysFunction;
import io.pivotal.gemfire.gpdb.operations.OperationException;
import io.pivotal.gemfire.gpdb.operationevents.AbstractOperationEventListener;
import io.pivotal.gemfire.gpdb.operationevents.CommitOperationEvent;
import io.pivotal.gemfire.gpdb.operationevents.ExternalTableOperationEvent;
import io.pivotal.gemfire.gpdb.operationevents.OperationEventListener;
import io.pivotal.gemfire.gpdb.operationevents.TypeOperationEvent;
import io.pivotal.gemfire.gpdb.service.ExportConfiguration;
import io.pivotal.gemfire.gpdb.service.GpdbService;

import org.apache.geode.cache.Region;
import org.apache.geode.cache.execute.FunctionService;

/**
 * This service provides a sample implementation of the GpdbService
 * <code>OperationEventListener</code> framework which allows clients to
 * customize the data motion cycle.
 *
 * This example deletes the data in GemFire after it is exported to Greenplum.
 * It provides an alternate to the use of an AEQ and LRU eviction to reduce the
 * number of temporary external tables and simultaneous SQL queries issued by
 * GemFire.
 */
public class CustomExportService {

	public long exportAndDelete(final Region<?, ?> region, final OperationEventListener listener)
	throws OperationException {
		ExportConfiguration configuration = ExportConfiguration.builder(region).addOperationEventListener(
				new AbstractOperationEventListener() {
					private static final long serialVersionUID = 1L;

					/**
					 * In order to delete the correct population of data we need
					 * to capture a snapshot of keys for the data we expect to
					 * export.
					 */
					@Override
					public void onAfterType(final TypeOperationEvent event) {
						FunctionService.onRegion(event.getRegion()).withArgs(CollectKeysFunction.arguments(event.getTxId()))
								.execute(CollectKeysFunction.ID);
						if (null != listener) {
							listener.onAfterType(event);
						}
					}

					/**
					 * Once the data has been exported we can safely delete data
					 * in GemFire
					 */
					@Override
					public void onAfterCommit(CommitOperationEvent event) {
						FunctionService.onRegion(event.getRegion()).withArgs(CollectKeysFunction.arguments(event.getTxId()))
								.execute(DeleteCollectedKeysFunction.ID);
						if (null != listener) {
							listener.onAfterCommit(event);
						}
					}

					@Override
					public void onBeforeCommit(CommitOperationEvent event) {
						if (null != listener) {
							listener.onBeforeCommit(event);
						}
					}

					@Override
					public void onBeforeType(TypeOperationEvent event) {
						if (null != listener) {
							listener.onBeforeType(event);
						}
					}

					@Override
					public void onAfterExternalTable(ExternalTableOperationEvent event) {
						if (null != listener) {
							listener.onAfterExternalTable(event);
						}
					}

					@Override
					public void onBeforeExternalTable(ExternalTableOperationEvent event) {
						if (null != listener) {
							listener.onAfterExternalTable(event);
						}
					}
				}).build();

		return GpdbService.exportRegion(configuration);
	}
}
