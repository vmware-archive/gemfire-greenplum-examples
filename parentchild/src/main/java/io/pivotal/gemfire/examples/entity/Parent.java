/* Copyright (c) 2014-2016 Pivotal Software, Inc. All Rights Reserved.
 * This product is protected by U.S. and international copyright
 * and intellectual property laws. Pivotal products are covered by
 * one or more patents listed at http://www.pivotal.io/patents.
 * =========================================================================
 */
package io.pivotal.gemfire.examples.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Parent {

	private BigInteger id;
	private String name;
	private BigDecimal income;

	public BigDecimal getIncome() {
		return income;
	}

	public String getName() {
		return name;
	}

	public void setIncome(BigDecimal income) {
		this.income = income;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

}

