package test.com.hecj.service;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import org.junit.Test;

import com.dmtz.framework.product.model.AutoInvestHistory;
import com.dmtz.framework.product.service.AutoInvestHistoryService;
import com.dmtz.framework.product.service.AutoInvestService;
import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;

public class TestServices {
	
	@Test
	public void before(){
		
		
	}
	
	@Test
	public void test01(){
		
		String framework_product_rmi = "//127.0.0.1:9041";
		
		try {
			
			AutoInvestService autoInvestService=(AutoInvestService) Naming.lookup(framework_product_rmi+"/AutoInvestRMIService");
			System.out.println(autoInvestService);
			System.out.println(autoInvestService.countAutoInvestIds());
			System.out.println(autoInvestService.getObjectById(3l).getUserId());
		
//			AutoInvestHistoryService autoInvestHistoryService=(AutoInvestHistoryService) Naming.lookup(framework_product_rmi+"/AutoInvestHistoryRMIService");
//			System.out.println(autoInvestHistoryService.getIdsByUserIdBorrowId(30000197l,469l).size());
//		
//			List<Long> ids = autoInvestHistoryService.getIdsByUserIdBorrowId(30000197l,469l);
//			List<AutoInvestHistory> lists = autoInvestHistoryService.getObjectsByIds(ids);
//			BigDecimal b = new BigDecimal(0);
//			Double sum = 0d;
//			for (int i = 0; i < lists.size(); i++) {
//				AutoInvestHistory a = lists.get(i);
//				b = b.add(a.getInvestAmount());
//				sum+=a.getInvestAmount().doubleValue();
//			}
//			System.out.println(b.doubleValue());
//			System.out.println(sum);
//			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (ServiceDaoException e) {
			e.printStackTrace();
		}

	
	}
	
	
	
}
