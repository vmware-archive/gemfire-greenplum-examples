/*
 * =========================================================================
 * Copyright (c) 2014-2016 Pivotal Software, Inc. All Rights Reserved.
 * This product is protected by U.S. and international copyright
 * and intellectual property laws. Pivotal products are covered by
 * one or more patents listed at http://www.pivotal.io/patents.
 * =========================================================================
 */
package io.pivotal.gemfire.examples.function;

import io.pivotal.gemfire.gpdb.util.RegionFunctionAdapter;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.geode.cache.Declarable;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.execute.RegionFunctionContext;
import org.apache.geode.cache.partition.PartitionRegionHelper;

/**
 * Removes all data that is currently stored in a region.
 */
public final class ClearDataFunction extends RegionFunctionAdapter implements Declarable {

	public static final String ID = "ClearDataFunction";

	@Override
	public void execute(RegionFunctionContext context) {

		Region<Object, Object> region = context.getDataSet();
		region = PartitionRegionHelper.getLocalPrimaryData(region);

		//Snapshot keys to prevent dynamic view changes.
		Set<Object> keys = new HashSet<>(region.keySet());
		long count = keys.size();
		if (count > 0) {
			region.removeAll(keys);
		}
		context.getResultSender().lastResult(count);

	}

	@Override
	public String getId() {
		return ClearDataFunction.ID;
	}

	@Override
	public void init(Properties arg0) {
	}

	@Override
	public boolean isHA() {
		return true;
	}

}
