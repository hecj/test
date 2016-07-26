package com.hecj.test.server;

import java.io.UnsupportedEncodingException;

public class StringCode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String s = "妫?煡瀹㈡埛甯愭埛璧勬枡鏃跺嚭閿?";
		try {
			byte[] bs = s.getBytes("iso-8859-1");
			
			String str = new String(bs,"UTF-8");
			System.out.println(str);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
