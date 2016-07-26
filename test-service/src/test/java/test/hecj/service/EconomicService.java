package test.hecj.service;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.dmtz.app.economic.model.UserBrokerRelation;
import com.dmtz.app.economic.service.PuserService;
import com.dmtz.app.economic.service.QrCodeService;
import com.dmtz.app.economic.service.UserBrokerRelationService;
import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;

public class EconomicService {
	
	String service_rmi = "//127.0.0.1:9001";
	
	PuserService puserService ;
	UserBrokerRelationService userBrokerRelationService ;
	QrCodeService qrCodeService ;
	
	@Before
	public void before(){
		
		try {
			puserService = (PuserService) Naming.lookup(service_rmi+"/PuserRMIService");
			userBrokerRelationService = (UserBrokerRelationService) Naming.lookup(service_rmi+"/UserBrokerRelationRMIService");
			qrCodeService = (QrCodeService) Naming.lookup(service_rmi+"/QrCodeRMIService");
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
			Integer count = puserService.countPuserIds();
			System.out.println(count);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (ServiceDaoException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 根据uids查询经纪人对象
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	@Test
	public void test02() throws ServiceException, ServiceDaoException{
		
		List<Long> uids = new ArrayList<Long>();
		uids.add(18890388l);
		uids.add(18890393l);
		uids.add(18890396l);
		String uid = "";
		for(Long id : uids){
			uid += id + ",";
		}
		if(uid.length() > 0){
			uid = uid.substring(0, uid.length() - 1);
		}
		
		System.out.println(userBrokerRelationService);
		Map<String,Object> conditions = new HashMap<String,Object>();
		conditions.put("@query", "broker_tuser_id");
		conditions.put("&t_user_id in "," ("+uid+") ");
		conditions.put("&is_delete = "," 0 ");
		conditions.put("@table", " duomeidai.t_user u inner join db_dmwx.user_broker_relation b  on b.t_user_id = u.id  ");
		List<Long> ids = userBrokerRelationService.getIdsByDynamicCondition(UserBrokerRelation.class, conditions, 0, Integer.MAX_VALUE);
		System.out.println(ids);
	}
	
	@Test
	public void test03() throws ServiceException, ServiceDaoException{
		
		//////匹配uids
		
		//1.限定id查询
		//判断有无投资    判断有无经纪人
		
		//2.限定经纪人
		//查用户经纪人关系表  中uids
		//级联查询 t_user 和t_invest in (uids)
		
		//3.限定经纪人类型
	    //有经纪人
			
		//无经纪人
		
		/* 根据限定条件查询uids */
		
		
		//4.限定是否投资
		
		/*
		String sql = "";
		select u.id from duomeidai.t_user u where 1=1 and u.belongTo = 1
		
		if(uids != null){
			and u.id in (uids);
		}
		if(经纪人类型 == 有){
			and u.id in (select DISTINCT(ubr.t_user_id) from db_dmwx.user_broker_relation ubr);
		}else if(经纪人类型 == 无){
		    and u.id not in (select DISTINCT(ubr.t_user_id) from db_dmwx.user_broker_relation ubr);
		}
		
		if(投资 == 是){
			and u.id in (select DISTINCT(i.investor) from duomeidai.t_invest i );
		}else if(投资 == 否){
			and u.id not in (select DISTINCT(i.investor) from duomeidai.t_invest i );
		}
	
		if(注册时间起 != null){
			and u.createTime >= 注册时间起
		}
		
		if(注册时间至 != null){
			and u.createTime <= 注册时间止
		}
		
		limit start,size

		 */
		
		
		
		
		Map<String,Object> conditions = new HashMap<String,Object>();
		conditions.put("@query", "broker_tuser_id");
		//conditions.put("&t_user_id in "," ("+uid+") ");
		conditions.put("&is_delete = "," 0 ");
		conditions.put("@table", " db_dmwx.user_broker_relation b left join duomeidai.t_user u on b.t_user_id = u.id  ");
		List<Long> ids = userBrokerRelationService.getIdsByDynamicCondition(UserBrokerRelation.class, conditions, 0, Integer.MAX_VALUE);
		System.out.println(ids);
		
		
	}
	
	@Test
	public void test04 (){
		System.out.println(Integer.MAX_VALUE);
		
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(1);
		list1.add(2);
		list1.add(3);
		System.out.println(list1);
		
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(1);
		list2.add(4);
		System.out.println(list2);
		
//		System.out.println(list1.retainAll(list2));
//		System.out.println(list1);
		
		System.out.println(list1.removeAll(list2));
		System.out.println(list1);
		
	}
	
	@Test
	public void test05() throws ServiceException, ServiceDaoException{
		
		List<Long> ids = qrCodeService.getQrCodeIdsByBrokerIdAndCodeStatus(18891597l, 2, 0, 11);
		for(Long i :ids){
			System.out.println(i);
		}
	}
	
}
