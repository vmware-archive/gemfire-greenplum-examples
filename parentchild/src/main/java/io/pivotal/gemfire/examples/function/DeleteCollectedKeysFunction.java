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
import io.pivotal.gemfire.gpdb.util.RegionFunctionAdapter;

import java.util.Collection;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.geode.cache.CacheTransactionManager;
import org.apache.geode.cache.Declarable;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.execute.RegionFunctionContext;
import org.apache.geode.cache.partition.PartitionRegionHelper;
import org.apache.geode.internal.cache.AbstractRegion;
import org.apache.geode.internal.cache.GemFireCacheImpl;

/**
 * Removes the objects for the specified GPDB operation from the cache provided
 * the keys were previously collected. This is usually accomplished by a prior
 * call to <code>CollectKeysFunction</code>
 * 
 * @see io.pivotal.gemfire.demo.function.CollectKeysFunction
 *
 */
public class DeleteCollectedKeysFunction extends RegionFunctionAdapter implements Declarable {

	private static final long serialVersionUID = 1L;

	private static final Logger log = LogManager.getLogger();

	public static final String ID = "gpdbDestroyKeys";

	@Override
	public void execute(final RegionFunctionContext context) {

		final AbstractRegion rawRegion = (AbstractRegion) context.getDataSet();
		final GemFireCacheImpl cache = rawRegion.getCache();
		@SuppressWarnings("unchecked")
		Region<Object, Object> region2 = PartitionRegionHelper.getLocalPrimaryData(rawRegion);

		final String id = (String) context.getArguments();

		if (log.isDebugEnabled()) {
			log.debug("Deleting keys for id {}.", id);
		}

		final CacheTransactionManager tx = cache.getCacheTransactionManager();
		tx.begin();

		final Collection<Object> keys = CollectedKeys.getKeys(id);
		region2.removeAll(keys);
		keys.clear();
		CollectedKeys.removeKeys(id);

		tx.commit();
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
}
