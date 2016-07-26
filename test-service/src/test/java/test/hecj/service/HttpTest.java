package test.hecj.service;

import org.junit.Test;

import com.hecj.pro.common.utils.HttpRequest;

public class HttpTest {
	
	public static String url = "http://wx.hecj.top/qq/list";
	
	@Test
	public void test01(){
		
		for(int i=0;i<1000;i++){
			String content = HttpRequest.sendGet(url);
			System.out.println(content);
		}
		
	}
}
