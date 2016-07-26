package test.hecj.service;

import org.junit.Test;

import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;

public class ProRedService {
	
	
	@Test
	public void test01() throws ServiceException, ServiceDaoException{
	 
		int n = 60000+49000+20000+15000;
		System.out.println(n);
		// 余额宝 招行 招行信用卡            交行   广发  中信信用卡       微店   中信储蓄卡
		int n2 = 38800+51000+37700+2400+1200+4200+       7400+3700;
		System.out.println(n2);
		
	}
	
	@Test
	public void test02(){
		System.out.println(90/1501d);
	}
	
}
