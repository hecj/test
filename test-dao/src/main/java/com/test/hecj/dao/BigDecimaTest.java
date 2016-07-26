package com.test.hecj.dao;

import java.math.BigDecimal;

import org.junit.Test;

public class BigDecimaTest {
	
	@Test
	public void test01(){
		
		Double earningsrateEnd = new BigDecimal(3000).doubleValue();
		
		System.out.println(earningsrateEnd%100 == 0);
	}
}
