package test.hecj.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Before;
import org.junit.Test;

import test.hecj.util.SqlUtil;

import com.dmtz.framework.product.model.AutoInvestHistory;
import com.dmtz.framework.product.model.TInvest;
import com.dmtz.framework.product.service.AutoInvestHistoryService;
import com.dmtz.framework.product.service.AutoInvestService;
import com.dmtz.framework.product.service.TInvestService;
import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;

public class ProductService {
	
	String service_rmi = "//127.0.0.1:9041";
	AutoInvestService autoInvestService ;
	TInvestService tInvestService;
	AutoInvestHistoryService autoInvestHistoryService;
	@Before
	public void before(){
		
		try {
			autoInvestService = (AutoInvestService) Naming.lookup(service_rmi+"/AutoInvestRMIService");
			tInvestService = (TInvestService) Naming.lookup(service_rmi+"/TInvestRMIService");
			autoInvestHistoryService = (AutoInvestHistoryService) Naming.lookup(service_rmi+"/AutoInvestHistoryRMIService");

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test01(){
		
		try {

			Integer count = autoInvestService.countAutoInvestIds();
			System.out.println(count);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (ServiceDaoException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void test02() throws ServiceException, ServiceDaoException{
		
		String currDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
		
		Integer count = tInvestService.countTInvestIds();
		System.out.println(count);
		Map<String,Object> conditions = new HashMap<String,Object>();
		conditions.put("@query", "DISTINCT(investor)");
		List<Long> ids = tInvestService.getIdsByDynamicCondition(TInvest.class, conditions, 0, Integer.MAX_VALUE);
		System.out.println(ids);
		
		String userIds = "18890386, 18890405, 18890406, 18890457, 18890615, 18890626, 18890842";
	
		//截止昨天投资总金额
		Map<String,Object> conditionsInvestMoney = new HashMap<String,Object>();
		conditionsInvestMoney.put("@query", "sum(investAmount)");
		conditionsInvestMoney.put("&investor in ", "("+userIds+")");
		conditionsInvestMoney.put("&investTime < ","str_to_date('"+currDate+"', '%Y-%m-%d')");
		List<Object> investList=(List<Object>) tInvestService.getObjectsByDynamicCondition(TInvest.class, conditionsInvestMoney, 0, 1);
		BigDecimal investSumMoney = (BigDecimal)investList.get(0);
		
		System.out.println(investSumMoney);
		
		//截止昨天总投资笔数
		Map<String,Object> conditionsInvestCount = new HashMap<String,Object>();
		conditionsInvestCount.put("@query", "count(1)");
		conditionsInvestCount.put("&investor in ", "("+userIds+")");
		conditionsInvestCount.put("&investTime < ","str_to_date('"+currDate+"', '%Y-%m-%d')");
		List<Object> investCountList=(List<Object>) tInvestService.getObjectsByDynamicCondition(TInvest.class, conditionsInvestCount, 0, 1);
		BigInteger investCount = (BigInteger)investCountList.get(0);
		System.out.println(investCount);
		
		//截止昨天首投人数
		Map<String,Object> conditionsFirstInvestCount = new HashMap<String,Object>();
		conditionsFirstInvestCount.put("@query", "count(1)");
		conditionsFirstInvestCount.put("&id in ", "(select id from t_invest where investor in ("+userIds+") group by investor having count(1) = 1)");
		conditionsFirstInvestCount.put("&investTime < ","str_to_date('"+currDate+"', '%Y-%m-%d')");
		List<Object> fistInvestCountList=(List<Object>) tInvestService.getObjectsByDynamicCondition(TInvest.class, conditionsFirstInvestCount, 0, 1);
		BigInteger firstInvestCount = (BigInteger)fistInvestCountList.get(0);
		System.out.println(firstInvestCount);
		
		//截止昨天首投金额
		Map<String,Object> conditionsFirstInvestMoney = new HashMap<String,Object>();
		conditionsFirstInvestMoney.put("@query", "IFNULL(sum(investAmount),0)");
		conditionsFirstInvestMoney.put("&id in ", "(select id from t_invest where investor in ("+userIds+") group by investor having count(1) = 1)");
		conditionsFirstInvestMoney.put("&investTime < ","str_to_date('"+currDate+"', '%Y-%m-%d')");
		List<Object> firstInvestMoneyList=(List<Object>) tInvestService.getObjectsByDynamicCondition(TInvest.class, conditionsFirstInvestMoney, 0, 1);
		BigDecimal firstInvestSumMoney = (BigDecimal)firstInvestMoneyList.get(0);
		System.out.println("firstInvestSumMoney:"+firstInvestSumMoney);
		
	}
	
	/**
	 * 第三方渠道  首笔统计金额之和
	* @Title: test03
	* @Description: TODO
	* @param  
	* @return void
	* @throws
	 */
	@Test
	public void test03(){
		
		/*
		 * 
	Map<String,Object> conditionsFirstInvestMoney = new HashMap<String,Object>();
			conditionsFirstInvestMoney.put("@query", "IFNULL(sum(investAmount),0)");
			conditionsFirstInvestMoney.put("&id in ", "(SELECT id FROM (SELECT i.id,i.investor FROM t_invest i WHERE i.investor IN ("+SqlUtil.withSplit(nIssuedList, ",")+") and i.investTime < str_to_date('"+totalDateStr+"', '%Y-%m-%d %H:%i:%s') ORDER BY i.investTime ASC ) f GROUP BY f.investor )");
			List<Object> firstInvestMoneyList=(List<Object>) tInvestService.getObjectsByDynamicCondition(TInvest.class, conditionsFirstInvestMoney, 0, 1);
		    investMoney = (BigDecimal)(firstInvestMoneyList.get(0));
		 
Map<String,Object> conditionsFirstInvestMoney = new HashMap<String,Object>();
			conditionsFirstInvestMoney.put("@query", "IFNULL(sum(investAmount),0)");
			conditionsFirstInvestMoney.put("&id in ", "(SELECT id FROM (SELECT i.id,i.investor FROM t_invest i INNER JOIN user_issued_notes ns ON (ns.user_id = i.investor) WHERE i.investor IN ("+SqlUtil.withSplit(yIssuedList, ",")+") and i.investTime < str_to_date('"+totalDateStr+"', '%Y-%m-%d %H:%i:%s') and i.investTime < from_unixtime(ns.issued_at/1000) ORDER BY i.investTime ASC ) f GROUP BY f.investor )");
			List<Object> firstInvestMoneyList=(List<Object>) tInvestService.getObjectsByDynamicCondition(TInvest.class, conditionsFirstInvestMoney, 0, 1);
			investMoney2 = (BigDecimal)(firstInvestMoneyList.get(0));		 
		 
		 */
		
	}
	
	/**
	 * 统计首次投资金额
	 */
	@Test
	public void test04() throws ServiceException, ServiceDaoException{
		
		BigDecimal investMoney = new BigDecimal(0);
		
		List<Long> nIssuedList = new ArrayList<Long>();
		nIssuedList.add(18890531l);
		nIssuedList.add(18890626l);
		
		String totalDateStr = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		
		Map<String,Object> conditionsFirstInvestMoney = new HashMap<String,Object>();
		conditionsFirstInvestMoney.put("@query", "IFNULL(investAmount,0)");
		conditionsFirstInvestMoney.put("&id in ", "(SELECT id FROM (SELECT i.id,i.investor FROM t_invest i WHERE i.investor IN ("+SqlUtil.withSplit(nIssuedList, ",")+") and i.investTime < str_to_date('"+totalDateStr+"', '%Y-%m-%d %H:%i:%s') ORDER BY i.investTime ASC ) f GROUP BY f.investor )");
		List<Object> firstInvestMoneyList=(List<Object>) tInvestService.getObjectsByDynamicCondition(TInvest.class, conditionsFirstInvestMoney, 0, 1);
	    investMoney = (BigDecimal)(firstInvestMoneyList.get(0));
	    
	    System.out.println(investMoney);
		
	}
	
	@Test
	public void test05() throws ServiceException, ServiceDaoException{
		
		Long uid = 3243l;
		Long bid = 233l;
		List<AutoInvestHistory> autoInvestHistoryList = autoInvestHistoryService.getObjectsByIds(autoInvestHistoryService
				.getIdsByUserIdBorrowId(uid, bid));
		
		List<Long> ids = autoInvestHistoryService.getIdsByUserIdBorrowId(uid, bid);
		System.out.println(ids);
		System.out.println(ids.size());
	}
	
}
