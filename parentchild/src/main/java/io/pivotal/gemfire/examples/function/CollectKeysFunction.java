/*
 * =========================================================================
 * Copyright (c) 2014-2016 Pivotal Software, Inc. All Rights Reserved.
 * This product is protected by U.S. and international copyright
 * and intellectual property laws. Pivotal products are covered by
 * one or more patents listed at http://www.pivotal.io/patents.
 * =========================================================================
 */
package io.pivotal.gemfire.examples.function;

import io.pivotal.gemfire.examples.util.CollectedKeys;
import io.pivotal.gemfire.gpdb.gpfdist.PdxRowProducer;
import io.pivotal.gemfire.gpdb.gpfdist.Gpfdist;
import io.pivotal.gemfire.gpdb.util.RegionFunctionAdapter;

import java.util.Collection;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.geode.cache.Declarable;
import org.apache.geode.cache.execute.RegionFunctionContext;
import org.apache.geode.internal.InternalEntity;

/**
 * Creates a snapshot of keys for the objects that are part of a specific GPDB
 * operation whose id is passed as an argument.
 *
 */
public class CollectKeysFunction extends RegionFunctionAdapter implements Declarable, InternalEntity {

	private static final long serialVersionUID = 1L;

	private static final Logger log = LogManager.getLogger();

	public static final String ID = CollectKeysFunction.class.getName();

	@Override
	public void execute(final RegionFunctionContext context) {
		final String id = (String) context.getArguments();

		@SuppressWarnings("unchecked")
		final PdxRowProducer<Object> rowProducer = (PdxRowProducer<Object>) Gpfdist.getInstance().getResource(id)
				.createRowProducer();

		final Collection<Object> keys = rowProducer.getKeys();

		if (log.isDebugEnabled()) {
			log.debug("Collected keys: id={}, keys={}", id, keys);
		}

		CollectedKeys.getKeys(id).addAll(keys);

		context.getResultSender().lastResult(true);
	}

	@Override
	public boolean isHA() {
		return false;
	}

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public void init(Properties props) {
	}

	public static Object arguments(final String id) {
		return id;
	}
}
