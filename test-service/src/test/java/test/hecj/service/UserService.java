package test.hecj.service;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Before;
import org.junit.Test;

import test.hecj.util.SqlUtil;

import com.dmtz.framework.product.model.TInvest;
import com.dmtz.framework.product.service.TInvestService;
import com.dmtz.framework.user.model.LeaveMessage;
import com.dmtz.framework.user.model.TLinkSourceTotal;
import com.dmtz.framework.user.model.TPerson;
import com.dmtz.framework.user.model.TUser;
import com.dmtz.framework.user.service.LeaveMessageService;
import com.dmtz.framework.user.service.TAdminService;
import com.dmtz.framework.user.service.TIdnoAuthService;
import com.dmtz.framework.user.service.TLinkSourceService;
import com.dmtz.framework.user.service.TLinkSourceTotalService;
import com.dmtz.framework.user.service.TPersonService;
import com.dmtz.framework.user.service.TUserService;
import com.dmtz.framework.user.service.UserIssuedNotesService;
import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;

public class UserService {
	
	//String service_rmi = "//127.0.0.1:9031";
//	String service_rmi2 = "//127.0.0.1:9041";
	String service_rmi = "//framework.user.service:9031";
//	String service_rmi = "//192.168.1.235:9031";
	TUserService tUserService ;
	TPersonService tPersonService ;
	TLinkSourceService tLinkSourceService;
	TLinkSourceTotalService tLinkSourceTotalService;
	TIdnoAuthService tIdnoAuthService ;
	UserIssuedNotesService userIssuedNotesService ;
	TAdminService tAdminService ;
	TInvestService tInvestService;
	LeaveMessageService leaveMessageService;

	@Before
	public void before(){
		
		try {
			tUserService = (TUserService) Naming.lookup(service_rmi+"/TUserRMIService");
			tPersonService = (TPersonService) Naming.lookup(service_rmi+"/TPersonRMIService");
			tLinkSourceService = (TLinkSourceService) Naming.lookup(service_rmi+"/TLinkSourceRMIService");
			tLinkSourceTotalService = (TLinkSourceTotalService) Naming.lookup(service_rmi+"/TLinkSourceTotalRMIService");
			tIdnoAuthService = (TIdnoAuthService) Naming.lookup(service_rmi+"/TIdnoAuthRMIService");
			userIssuedNotesService = (UserIssuedNotesService) Naming.lookup(service_rmi+"/UserIssuedNotesRMIService");
			tAdminService = (TAdminService) Naming.lookup(service_rmi+"/TAdminRMIService");
			tAdminService = (TAdminService) Naming.lookup(service_rmi+"/TAdminRMIService");
			
			leaveMessageService = (LeaveMessageService) Naming.lookup(service_rmi+"/LeaveMessageRMIService");
		
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

			Integer count = tUserService.countTUserIdsByMobilePhone("15811372713");
			System.out.println(count);
			
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (ServiceDaoException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void test02() throws ServiceException, ServiceDaoException{
		
		Map<String,Object> conditions = new HashMap<String,Object>();
		conditions.put("&belongTo = ",1);
		List<Long> ids = tUserService.getIdsByDynamicCondition(TUser.class, conditions, 0, Integer.MAX_VALUE);
		System.out.println("count : "+ids.size());
		System.out.println(ids);
	}
	
	@Test
	public void test03() throws ServiceException, ServiceDaoException{
		
		String userIds = "18890386, 18890405, 18890406, 18890457, 18890615, 18890626, 18890842";
		
		//实名总数
		Map<String,Object> conditionsAuthCount = new HashMap<String,Object>();
		conditionsAuthCount.put("@query", "count(1)");
		conditionsAuthCount.put("&userId in ", "("+userIds+")");
		conditionsAuthCount.put("&idNoAuthStatus = ", 2);
		List<Long> authList = tPersonService.getIdsByDynamicCondition(TPerson.class, conditionsAuthCount, 0, 1);
		Long authCount = authList.get(0);
		
		System.out.println(authCount);
	}
	
	@Test
	public void test04() throws ServiceException, ServiceDaoException{
		
		String currDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
		
		//截止昨天注册人数
		Map<String,Object> conditions = new HashMap<String,Object>();
		conditions.put("&source = ", "'activity_47'");
		conditions.put("&createTime < ", "str_to_date('"+currDate+"', '%Y-%m-%d')");
		List<Long> userIdList = tUserService.getIdsByDynamicCondition(TUser.class, conditions, 0, Integer.MAX_VALUE);
		
		String userIds = SqlUtil.withSplit(userIdList, ",");
		
		//总注册人数
		Integer regCount = userIdList.size();
		
		System.out.println(regCount);
		
		//截止昨天总实名人数
		Map<String,Object> conditionsAuthCount = new HashMap<String,Object>();
		conditionsAuthCount.put("@query", "count(1)");
		conditionsAuthCount.put("&userId in ", "("+userIds+")");
		conditionsAuthCount.put("&idNoAuthStatus = ", 2);
		List<Long> authList = tPersonService.getIdsByDynamicCondition(TPerson.class, conditionsAuthCount, 0, 1);
		Long authCount = authList.get(0);
		
		System.out.println(authCount);
		
		
	}
	
	@Test
	public void test05() throws ServiceException, ServiceDaoException{
		System.out.println(tLinkSourceService.countTLinkSourceIds());
		
//		TLinkSource t = new TLinkSource();
//		t.setCreateBy(33l);
//		tLinkSourceService.insert(t);
	}
	
	@Test
	public void test06() throws ServiceException, ServiceDaoException{
		System.out.println(tLinkSourceTotalService.countTLinkSourceTotalIds());
		tLinkSourceTotalService.insert(new TLinkSourceTotal());
		
		tLinkSourceTotalService.getTLinkSourceTotalIdsByLinkSourceId(1111l, 0, 1);
	}
	
	@Test
	public void test07() throws ServiceException, ServiceDaoException{
		List<Long> idList = tLinkSourceTotalService.getTLinkSourceTotalIds(0, Integer.MAX_VALUE);
		System.out.println(idList.size());
	}
	
	@Test
	public void test08() throws ServiceException, ServiceDaoException{
		List<Long> idList = tUserService.getTUserIdsBySource("123");
		System.out.println(idList);
	}
	
	@Test
	public void test09() throws ServiceException, ServiceDaoException{
		List<Long> idList = tLinkSourceService.getTLinkSourceIdsByLinkName("点睛理财");
		System.out.println(idList.size());
	}
	
	@Test
	public void test10() throws ServiceException, ServiceDaoException{
		List<Long> idList = tLinkSourceService.getTLinkSourceIdsByLinkKey("marketing_activity_chuizhi011");
		System.out.println(idList.size());
	}
	
	@Test
	public void test11() throws ServiceException, ServiceDaoException{
		
		String currDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		System.out.println(currDate);
		//截止昨天注册人数
		Map<String,Object> conditions = new HashMap<String,Object>();
		conditions.put("&source = ", "'123'");
		conditions.put("&createTime < ", "str_to_date('"+currDate+"', '%Y-%m-%d %H:%i:%s')");
		List<Long> userIdList = tUserService.getIdsByDynamicCondition(TUser.class, conditions, 0, Integer.MAX_VALUE);
		System.out.println(userIdList);
	}
	
	@Test
	public void test12() {
		try {
			System.out.println(tIdnoAuthService);
//			Integer c = tIdnoAuthService.countTIdnoAuthIds();
//			System.out.println(c);
			
			Long s = tIdnoAuthService.getTIdnoAuthIdByIdNo("152631198803020047");
			System.out.println(s);
			
//			Integer c = tIdnoAuthService.countTIdnoAuthIds();
//			System.out.println(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void test13() throws ServiceException, ServiceDaoException{
		System.out.println(System.currentTimeMillis());
		Integer count = userIssuedNotesService.countUserIssuedNotesIds();
		System.out.println(count);
		
		System.out.println(userIssuedNotesService.getObjectById(1l));
		
	}
	
	@Test
	public void test14(){
		BigDecimal investMoney = new BigDecimal(0);
		
		System.out.println(investMoney.add(new BigDecimal(14)));
		System.out.println(investMoney);
		
	}
	
	@Test
	public void test15() throws ServiceException, ServiceDaoException{

		System.out.println(tAdminService);
//		System.out.println(tAdminService.getTAdminIdByUserName("hechaojie"));
		
		int n = (int)(Math.random()*20);
		System.out.println(n);
	}
	
	@Test
	public void test16() throws ServiceException, ServiceDaoException{
		
		for(int i = 0;i<30000;i++){
			TUser u = tUserService.getObjectById(18890388l);
			u.setId(null);
			u.setUsername(UUID.randomUUID().toString().substring(0,11));
			u.setMobilePhone(UUID.randomUUID().toString().substring(0,11));
			u.setBelongTo(0);
			u.setSource("marketing_activity_chuizhi004");
			Long uid = tUserService.insert(u);
			
			TPerson p = tPersonService.getObjectById(18890395l);
			p.setUserId(uid);
			p.setId(null);
			p.setCellPhone(u.getMobilePhone());
			p.setRealName(UUID.randomUUID().toString().substring(0,11));
			tPersonService.insert(p);
			
			int n = (int)(Math.random()*20);
			
			for(int j=0;j<n;j++){
				TInvest tInvest = tInvestService.getObjectById(314l);
				tInvest.setId(null);
				tInvest.setInvestAmount(new BigDecimal(100*(n-j)));
				tInvest.setInvestor(uid);
				tInvestService.insert(tInvest);
				System.out.println(j);
			}
			System.out.println("====="+i);
			
		}
		
	}
	
	
	@Test
	public void test17(){
		
		for(int i=0;i<10;i++){
			
			new Thread(){
				public void run() {
					try {
						test16();
					} catch (ServiceException e) {
						e.printStackTrace();
					} catch (ServiceDaoException e) {
						e.printStackTrace();
					}
				};
			}.run();
		}
		
	}
	
	@Test
	public void test18() throws ServiceException, ServiceDaoException{
		
		TUser t = tUserService.getObjectById(null);
		System.out.println(t+"=");
	}
	
	@Test
	public void test19() throws ServiceException, ServiceDaoException{
		
		for(int i=0;i<500;i++){
			LeaveMessage l = new LeaveMessage();
			l.setUserId(30000374l);
			l.setContent("第（"+i+"）条：多美贷生日快乐，祝愿多美贷原来越好，活动期间给多美贷祝福留言（每天可留言两次）");
			l.setStatus(2);
			l.setIsShow(0);
			l.setIsWin(0);
			leaveMessageService.insert(l);
		}
		
//		List<Long> list = leaveMessageService.getLeaveMessageIds(0, 10);
//		System.out.println(list.size());
	}
	
	@Test
	public void test20() {
		System.out.println(113/10);
	}
}
