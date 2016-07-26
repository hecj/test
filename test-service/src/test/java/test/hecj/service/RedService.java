package test.hecj.service;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.dmtz.dmd.red.model.Kid;
import com.dmtz.dmd.red.model.WxShareRecord;
import com.dmtz.dmd.red.service.AchievementRelactionService;
import com.dmtz.dmd.red.service.GameScoreConvertService;
import com.dmtz.dmd.red.service.GameScoreSettingService;
import com.dmtz.dmd.red.service.KidService;
import com.dmtz.dmd.red.service.PlayerFriendsScoreService;
import com.dmtz.dmd.red.service.PlayerScoreService;
import com.dmtz.dmd.red.service.UserOpenRelationService;
import com.dmtz.dmd.red.service.WxShareRecordService;
import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;

public class RedService {
	
//	String red_service = "//127.0.0.1:9021";
	String red_service = "//192.168.1.235:9021";
//	String red_service = "//dmd.red.service:9021";

	AchievementRelactionService achievementRelactionService;
	GameScoreConvertService gameScoreConvertService;
	GameScoreSettingService gameScoreSettingService;
	KidService kidService;
	PlayerScoreService playerScoreService;
	UserOpenRelationService userOpenRelationService;
	PlayerFriendsScoreService playerFriendsScoreService;
	WxShareRecordService wxShareRecordService;
	
	@Before
	public void before(){
		
		try {
			
			kidService = (KidService) Naming.lookup(red_service+"/KidRMIService");
			gameScoreConvertService = (GameScoreConvertService) Naming.lookup(red_service+"/GameScoreConvertRMIService");
			gameScoreSettingService = (GameScoreSettingService) Naming.lookup(red_service+"/GameScoreSettingRMIService");
			playerScoreService = (PlayerScoreService) Naming.lookup(red_service+"/PlayerScoreRMIService");
			playerFriendsScoreService = (PlayerFriendsScoreService) Naming.lookup(red_service+"/PlayerFriendsScoreRMIService");
			userOpenRelationService = (UserOpenRelationService) Naming.lookup(red_service+"/UserOpenRelationRMIService");
			wxShareRecordService = (WxShareRecordService) Naming.lookup(red_service+"/WxShareRecordRMIService");

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	/*
	@Test
	public void test01(){
		
		try {

			Integer count = kidService.countKidIds();
			System.out.println(count);
			System.out.println(gameScoreConvertService.countGameScoreConvertIds()+",");
			System.out.println(gameScoreSettingService.countGameScoreSettingIds()+",");
			
			Kid kid = new Kid();
			kid.setIsDelete(0);
			kidService.insert(kid);
			
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (ServiceDaoException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void test02() throws ServiceException, ServiceDaoException{
		
		List<Long> ids = kidService.getKidIdsByIsDelete(0, 0, 10);
		System.out.println(ids);
		
		List<Long> idss = kidService.getKidIds(0, 10);
		System.out.println(idss);
	}
	
	@Test
	public void test03() throws ServiceException, ServiceDaoException{
		
		Kid kid = new Kid();
		kid.setName("李源杰");
		kid.setAge(13);
		kid.setNation("苗族");
		kid.setMobile("15912896947");
		kid.setAddress("蒙自市老寨村七组");
		kid.setSchool("老寨中学");
		kid.setInfo("李源杰，女，13岁，就读于云南省红河州蒙自市老寨中学，父母吵架至母亲离家出走，家庭贫困。");
		kid.setImgUrl("");
		kid.setDonateAmount(new BigDecimal(1000));
		kid.setHasDonateAmount(new BigDecimal(0));
		kid.setIsDelete(0);
		kidService.insert(kid);
		
		Kid kid2 = new Kid();
		kid2.setName("刘财顺");
		kid2.setAge(16);
		kid2.setNation("苗族");
		kid2.setMobile("15187344508");
		kid2.setAddress("蒙自市老寨村委会坝心村");
		kid2.setSchool("老寨中学");
		kid2.setInfo("刘财顺，男，16岁，就读云南省红河州蒙自市老寨中学，共7口人，只有两个劳动力，共有4个人读书。");
		kid2.setImgUrl("");
		kid2.setDonateAmount(new BigDecimal(1000));
		kid2.setHasDonateAmount(new BigDecimal(0));
		kid2.setIsDelete(0);
		kidService.insert(kid2);
		
		Kid kid3 = new Kid();
		kid3.setName("杨开伟");
		kid3.setAge(12);
		kid3.setNation("苗族");
		kid3.setMobile("15924638440");
		kid3.setAddress("蒙自市老寨村委会老营盘村");
		kid3.setSchool("老寨中学");
		kid3.setInfo("杨开伟，男，12岁，就读于云南省红河州蒙自市老寨中学，父亲车祸去世、母亲出走，没有经济来源。");
		kid3.setImgUrl("");
		kid3.setDonateAmount(new BigDecimal(1000));
		kid3.setHasDonateAmount(new BigDecimal(0));
		kid3.setIsDelete(0);
		kidService.insert(kid3);
		
		Kid kid4 = new Kid();
		kid4.setName("徐锦");
		kid4.setAge(17);
		kid4.setNation("苗族");
		kid4.setMobile("15094128061");
		kid4.setAddress("蒙自市草坝镇大郭西村三组");
		kid4.setSchool("蒙自一中南湖校区");
		kid4.setInfo("徐锦，女，17岁，就读于云南省红河州蒙自市蒙自一中南湖校区，父亲癌症去世，母亲收入甚少供养3个学生。");
		kid4.setImgUrl("");
		kid4.setDonateAmount(new BigDecimal(1000));
		kid4.setHasDonateAmount(new BigDecimal(0));
		kid4.setIsDelete(0);
		kidService.insert(kid4);
		
		Kid kid5 = new Kid();
		kid5.setName("徐铭");
		kid5.setAge(16);
		kid5.setNation("苗族");
		kid5.setMobile("15094128061");
		kid5.setAddress("蒙自市草坝镇大郭西村三组");
		kid5.setSchool("草坝中学二校区");
		kid5.setInfo("徐铭，男，16岁，就读于云南省红河州蒙自市草坝中学二校区学，父亲因癌症去世，母亲收入甚少供养3个学生。");
		kid5.setImgUrl("");
		kid5.setDonateAmount(new BigDecimal(1000));
		kid5.setHasDonateAmount(new BigDecimal(0));
		kid5.setIsDelete(0);
		kidService.insert(kid5);
		
		Kid kid6 = new Kid();
		kid6.setName("陶绍珍");
		kid6.setAge(18);
		kid6.setNation("苗族");
		kid6.setMobile("13408924774（班主任：王万其）");
		kid6.setAddress("蒙自市鸣鹫镇扎门村大凹子组");
		kid6.setSchool("蒙自一中南湖校区");
		kid6.setInfo("陶绍珍，女，18岁，就读于云南省红河州蒙自市蒙自一中南湖校区，父母亲在家务农，收入甚少，供养3个学生。");
		kid6.setImgUrl("");
		kid6.setDonateAmount(new BigDecimal(1000));
		kid6.setHasDonateAmount(new BigDecimal(0));
		kid6.setIsDelete(0);
		kidService.insert(kid6);
		
		Kid kid7 = new Kid();
		kid7.setName("朱露德");
		kid7.setAge(14);
		kid7.setNation("汉族");
		kid7.setMobile("13988050230");
		kid7.setAddress("蒙自市鸣鹫镇鸣鹫村五社");
		kid7.setSchool("蒙自市鸣鹫镇中学");
		kid7.setInfo("朱露德，女，14岁，就读于云南省红河州蒙自市鸣鹫镇中学，父残疾无劳动力、母精神疾病，生活靠民政部门救济。");
		kid7.setImgUrl("");
		kid7.setDonateAmount(new BigDecimal(1000));
		kid7.setHasDonateAmount(new BigDecimal(0));
		kid7.setIsDelete(0);
		kidService.insert(kid7);
		
		Kid kid8 = new Kid();
		kid8.setName("古家贵");
		kid8.setAge(17);
		kid8.setNation("苗族");
		kid8.setMobile("18314004011");
		kid8.setAddress("蒙自市老寨乡羊街子坝心村4组");
		kid8.setSchool("蒙自市老寨苗族乡中学");
		kid8.setInfo("古家贵，男，17岁，就读于云南省红河州蒙自市老寨苗族乡中学，劳动力只有父母亲，收入甚少，供养2个学生。");
		kid8.setImgUrl("");
		kid8.setDonateAmount(new BigDecimal(1000));
		kid8.setHasDonateAmount(new BigDecimal(0));
		kid8.setIsDelete(0);
		kidService.insert(kid8);
		
		Kid kid9 = new Kid();
		kid9.setName("周绍福");
		kid9.setAge(17);
		kid9.setNation("苗族");
		kid9.setMobile("15912834492");
		kid9.setAddress("蒙自市老寨乡菲土村");
		kid9.setSchool("蒙自市老寨苗族乡中学");
		kid9.setInfo("周绍福，男，17岁，就读云南省红河州蒙自市老寨苗族乡中学，父为劳动力，母常年生病，家中供养3个学生。");
		kid9.setImgUrl("");
		kid9.setDonateAmount(new BigDecimal(1000));
		kid9.setHasDonateAmount(new BigDecimal(0));
		kid9.setIsDelete(0);
		kidService.insert(kid9);
		
		Kid kid10 = new Kid();
		kid10.setName("李洪军");
		kid10.setAge(15);
		kid10.setNation("苗族");
		kid10.setMobile("15912834492");
		kid10.setAddress("蒙自市老寨乡白牛厂村");
		kid10.setSchool("蒙自市老寨苗族乡中学");
		kid10.setInfo("李洪军，男，15岁，就读云南省红河州蒙自市老寨苗族乡中学，父久病缠身，收入甚微，供家中2个学生。");
		kid10.setImgUrl("");
		kid10.setDonateAmount(new BigDecimal(1000));
		kid10.setHasDonateAmount(new BigDecimal(0));
		kid10.setIsDelete(0);
		kidService.insert(kid10);
		
		
	}
	
	@Test
	public void test04() throws ServiceException, ServiceDaoException{
		
		System.out.println(playerScoreService.getPlayerScoreIdsByKidId(8l, 0, 10));
	}
	
	@Test
	public void test05() throws ServiceException, ServiceDaoException{
		List<Long> idList = userOpenRelationService.getUserOpenRelationIdsByOpenId("oJyy2s4d_Q2D8C44bWTl9GlmPJjc", 0, 1);
		System.out.println(idList.size());
		
	}
	
	@Test
	public void test06() throws ServiceException, ServiceDaoException{
//		List<Long> ids = playerFriendsScoreService.getPlayerFriendsScoreIdsByKidIdAndUserOpenIdAndCreateAt(1l, "oJyy2s0v0NV5Ngjb7wwfD8MNEUUQ",1435912421659l 1, 10, null);
//		System.out.println(ids);
		Long d = 14359124216592l;
		List<Long> ids = playerFriendsScoreService.getPlayerFriendsScoreIdsByKidIdAndUserOpenIdAndCreateAt(1l, "oJyy2s0v0NV5Ngjb7wwfD8MNEUUQ", d, 0, 10);
		System.out.println(ids);
	}
	
	@Test
	public void test07() throws ServiceException, ServiceDaoException{
		
		WxShareRecord wx = new WxShareRecord();
		wxShareRecordService.insert(wx);
	}*/
	
	
	@Test
	public void test07(){
		
		try {
			List<Long> ids = gameScoreConvertService.getGameScoreConvertIds(0, 100);
			System.out.println(ids.size());
			
			
		} catch (ServiceException e) {
			e.printStackTrace();
			e.printStackTrace();
		} catch (ServiceDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
