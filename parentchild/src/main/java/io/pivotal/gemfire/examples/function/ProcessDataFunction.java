/*
 * =========================================================================
 * Copyright (c) 2014-2016 Pivotal Software, Inc. All Rights Reserved.
 * This product is protected by U.S. and international copyright
 * and intellectual property laws. Pivotal products are covered by
 * one or more patents listed at http://www.pivotal.io/patents.
 * =========================================================================
 */
package io.pivotal.gemfire.examples.function;

import io.pivotal.gemfire.examples.util.RegionName;
import io.pivotal.gemfire.gpdb.util.RegionFunctionAdapter;

import java.math.BigInteger;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.geode.LogWriter;
import org.apache.geode.cache.Cache;
import org.apache.geode.cache.CacheFactory;
import org.apache.geode.cache.Declarable;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.execute.RegionFunctionContext;
import org.apache.geode.cache.partition.PartitionRegionHelper;
import org.apache.geode.internal.cache.LocalDataSet;
import org.apache.geode.internal.cache.PartitionedRegion;
import org.apache.geode.pdx.PdxInstance;

/**
 * Performs some simple logic on the data imported from Greenplum. The function
 * implements a sort based matching of the foreign key ids to link data. This
 * pattern currently outperforms indexed OQL queries for cases when all of the
 * data in the region will need to be processed (i.e. batched analytics).
 */
public final class ProcessDataFunction extends RegionFunctionAdapter implements Declarable {

	public static final String ID = "ProcessDataFunction";

	/*
	 * Data has been collocated on the parentId.
	 */
	private static class KeySorter implements Comparator<Map<String, Object>> {
		public int compare(Map<String, Object> o1, Map<String, Object> o2) {
			BigInteger k1 = (BigInteger) o1.get("parentId");
			BigInteger k2 = (BigInteger) o2.get("parentId");
			return k1.compareTo(k2);
		}
	}

	@SuppressWarnings("unchecked")
	public void execute(RegionFunctionContext context) {
		Cache cache = CacheFactory.getAnyInstance();
		LogWriter logger = cache.getLogger();
		int count = 0;
		Region<Map<String, Object>, Object> mappedChildRegion = getLocalRegion(cache, RegionName.MappedChild.name());
		Region<Map<String, Object>, Object> orphanRegion = getLocalRegion(cache, RegionName.OrphanChild.name());

		/*
		 * We're going to process the local region data bucket by bucket using a
		 * sorting strategy to link parent & child objects. When we find a child
		 * that has a parent we'll store it in the MappedChild region; those
		 * without a parent will be stored as orphans. A one parent with one
		 * child type example would normally be addressed via a join. This
		 * pattern becomes necessary as we have a top level parent with many
		 * different child types.
		 */
		Set<Integer> allbuckets = ((PartitionedRegion) context.getDataSet()).getDataStore().getAllLocalPrimaryBucketIds();

		for (Integer i : allbuckets) {
			logger.info("Processing bucket#:" + i);

			Region<BigInteger, PdxInstance> parents = getLocalBucketData(cache, RegionName.Parent.name(), i);
			Region<Map<String, Object>, PdxInstance> children = getLocalBucketData(cache, RegionName.Child.name(), i);

			LinkedList<BigInteger> parentKeys = getKeys(parents);
			LinkedList<Map<String, Object>> childKeys = getData(children);

			BigInteger parentId;
			/*
			 * The children are stored with a compound key made up of the parent
			 * and child id.
			 */
			Map<String, Object> currentChildKey;
			BigInteger childsParentID;
			PdxInstance child;

			while (parentKeys.size() > 0) {
				parentId = parentKeys.pop();

				while (childKeys.size() > 0) {
					currentChildKey = childKeys.getFirst();
					child = children.get(currentChildKey);
					childsParentID = (BigInteger) currentChildKey.get("parentId");

					if (childsParentID.equals(parentId)) {
						mappedChildRegion.put(currentChildKey, child);
						childKeys.pop();
					} else if (childsParentID.compareTo(parentId) < 0) {
						orphanRegion.put(currentChildKey, child);
						childKeys.pop();
					} else {
						// The child may belong to a future parent
						break;
					}
				}

				count++;
			}
			/*
			 * We got through all off the collocated parents and didn't find a
			 * mapping.
			 */
			while (childKeys.size() > 0) {
				currentChildKey = childKeys.pop();
				child = children.get(currentChildKey);
				orphanRegion.put(currentChildKey, child);
			}

		}

		context.getResultSender().lastResult(count);

	}

	private LinkedList<BigInteger> getKeys(Region<BigInteger, PdxInstance> region) {

		LinkedList<BigInteger> result = new LinkedList<>();
		Set<BigInteger> entrySet = region.keySet();
		result.addAll(entrySet);

		Collections.sort(result);
		return result;
	}

	private LinkedList<Map<String, Object>> getData(Region<Map<String, Object>, ?> region) {
		LinkedList<Map<String, Object>> result = new LinkedList<Map<String, Object>>();
		Set<Map<String, Object>> entrySet = region.keySet();
		result.addAll(entrySet);

		Collections.sort(result, new KeySorter());
		return result;
	}

	@Override
	public String getId() {
		return ID;
	}

	/*
	 * Build a region interface for a single bucket.
	 */
	@SuppressWarnings("rawtypes")
	private Region getLocalBucketData(Cache cache, String name, Integer bucket) {
		PartitionedRegion pr = (PartitionedRegion) cache.getRegion(name);
		return new LocalDataSet(pr, Collections.singleton(bucket));
	}

	@SuppressWarnings("rawtypes")
	private Region getLocalRegion(Cache cache, String name) {
		return PartitionRegionHelper.getLocalPrimaryData(cache.getRegion(name));
	}

	public void init(Properties arg0) {
	}

}
