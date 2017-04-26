/*
 * =========================================================================
 * Copyright (c) 2014-2016 Pivotal Software, Inc. All Rights Reserved.
 * This product is protected by U.S. and international copyright
 * and intellectual property laws. Pivotal products are covered by
 * one or more patents listed at http://www.pivotal.io/patents.
 * =========================================================================
 */
package io.pivotal.gemfire.examples.function;

import io.pivotal.gemfire.gpdb.service.ImportConfiguration;
import org.apache.geode.cache.execute.FunctionException;
import io.pivotal.gemfire.examples.util.CustomExportService;
import io.pivotal.gemfire.examples.util.RegionName;
import io.pivotal.gemfire.gpdb.operations.OperationException;
import io.pivotal.gemfire.gpdb.service.GpdbService;
import io.pivotal.gemfire.examples.util.SingletonRegionFunctionAdapter;

import java.util.Properties;

import org.apache.geode.LogWriter;
import org.apache.geode.cache.Cache;
import org.apache.geode.cache.CacheFactory;
import org.apache.geode.cache.Declarable;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.execute.FunctionService;
import org.apache.geode.cache.execute.RegionFunctionContext;

public final class RunDemoFunction extends SingletonRegionFunctionAdapter implements Declarable {

	private static final String ID = "RunDemoFunction";


	@Override
	protected void executeSingleton(RegionFunctionContext context) {
		Cache cache = CacheFactory.getAnyInstance();
		LogWriter log = cache.getLogger();
		Region<String, String> parent = cache.getRegion(RegionName.Parent.name());
		Region<String, String> mappedChildren = cache.getRegion(RegionName.MappedChild.name());
		Region<String, String> orphans = cache.getRegion(RegionName.OrphanChild.name());

		Region<?, ?> region;
		RegionName[] names = { RegionName.Parent, RegionName.Child };

		long totalNumberOfRowsTransferred = 0;
		/*
		 * Clear out any old data and load some fresh data from Greenplum.
		 */
		for (RegionName n : names) {

			region = cache.getRegion(n.name());

			log.info("Clear Region " + n.name());
			FunctionService.onRegion(region).execute(ClearDataFunction.ID);

      long rows= 0;
      try {
      	rows = GpdbService.importRegion(ImportConfiguration.builder(region).build());
      } catch (OperationException e) {
        throw new FunctionException(e);
      }
      totalNumberOfRowsTransferred += rows;
			log.info("Loaded " + n.name() + " : " + rows);

		}

		/*
		 * Do some crude analytic that generates data in two regions
		 */
		ProcessDataFunction rulesFunction = new ProcessDataFunction();
		FunctionService.onRegion(parent).execute(rulesFunction).getResult();

		/*
		 * Store one mappedChildren.
		 */
		CustomExportService exportService = new CustomExportService();
    long rows = 0;
    try {
      rows = exportService.exportAndDelete(mappedChildren, null);
    } catch (OperationException e) {
      throw new FunctionException(e);
    }
    totalNumberOfRowsTransferred += rows;
		log.info("Exported: " + rows);

		context.getResultSender().lastResult(totalNumberOfRowsTransferred);
	}

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public void init(Properties arg0) {
	}

	@Override
	public boolean isHA() {
		return false;
	}
}
