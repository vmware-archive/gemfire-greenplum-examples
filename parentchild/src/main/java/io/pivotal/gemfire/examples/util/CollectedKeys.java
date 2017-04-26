/*
 * =========================================================================
 * Copyright (c) 2014-2016 Pivotal Software, Inc. All Rights Reserved.
 * This product is protected by U.S. and international copyright
 * and intellectual property laws. Pivotal products are covered by
 * one or more patents listed at http://www.pivotal.io/patents.
 * =========================================================================
 */
package io.pivotal.gemfire.examples.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public final class CollectedKeys {
	static final ConcurrentHashMap<String, CopyOnWriteArrayList<Object>> keys = new ConcurrentHashMap<String, CopyOnWriteArrayList<Object>>();

	public static CopyOnWriteArrayList<Object> getKeys(String id) {
		CopyOnWriteArrayList<Object> a = keys.get(id);
		if (null == a) {
			a = new CopyOnWriteArrayList<Object>();
			final CopyOnWriteArrayList<Object> b = keys.putIfAbsent(id, a);
			if (null != b) {
				return b;
			}
		}
		return a;
	}

	public static void removeKeys(String id) {
		keys.remove(id);
	}
}
