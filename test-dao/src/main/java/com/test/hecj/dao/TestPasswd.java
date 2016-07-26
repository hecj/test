package com.test.hecj.dao;

import org.junit.Test;

import com.longdai.constants.IConstants;

public class TestPasswd {
	
	@Test
	public void test01(){
		String str = "111111";
		IConstants.PASS_KEY = "4B103DF3D3505AF871EFED63A78E3B75";
		String password = com.longdai.security.Encrypt.MD5( str + IConstants.PASS_KEY);
		System.out.println(password);
	}
}










