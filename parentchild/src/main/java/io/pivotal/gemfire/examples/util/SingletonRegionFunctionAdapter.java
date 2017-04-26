/*
 * =========================================================================
 * Copyright (c) 2014-2016 Pivotal Software, Inc. All Rights Reserved.
 * This product is protected by U.S. and international copyright
 * and intellectual property laws. Pivotal products are covered by
 * one or more patents listed at http://www.pivotal.io/patents.
 * =========================================================================
 */

package io.pivotal.gemfire.examples.util;

import org.apache.geode.cache.execute.FunctionException;
import org.apache.geode.cache.execute.RegionFunctionContext;
import org.apache.geode.internal.cache.execute.PartitionedRegionFunctionResultSender;
import io.pivotal.gemfire.gpdb.util.RegionFunctionAdapter;

public abstract class SingletonRegionFunctionAdapter extends RegionFunctionAdapter {

  private static final long serialVersionUID = 1L;

  @Override
  public final void execute(RegionFunctionContext context) {
    if (! (context.getResultSender() instanceof PartitionedRegionFunctionResultSender)) {
      throw new FunctionException("This function must be called on a Partitioned Region with either Function.onRegion or gfsh --region");
    }

    /**
     * When called with onRegion, isLocallyExecuted will be true on one member
     * and false on all others. If called with onMember, isLocallyExecuted is
     * true on every member.
     */
    if (((PartitionedRegionFunctionResultSender) context.getResultSender()).isLocallyExecuted()) {
      executeSingleton(context);
    } else {
      if (hasResult()) {
        context.getResultSender().lastResult(null);
      }
    }
  }

  protected abstract void executeSingleton(RegionFunctionContext context);

}
