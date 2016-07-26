package test.hecj.service;

import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;

import test.hecj.entity.SyncAccountReq;

import com.gozap.config.PayEasyConfig;
import com.gozap.util.UtilDate;
import com.gozap.web.util.Des3Utill;
import com.gozap.web.util.GsonUtil;
import com.gozap.web.util.HttpsJsonUtil;
import com.gozap.web.util.JsonMapper;
import com.longdai.entity.SyncAccountResp;
import com.longdai.entity.TrsAccountServiceReq;
import com.longdai.entity.TrsAccountServiceResp;
import com.longdai.util.DateUtil;

public class SyncAccountServiceTest {
	
	public static Log log = LogFactory.getLog(SyncAccountServiceTest.class);
	
	@Before
	public void before() {
		/**
		 *  总公司
		 */
		PayEasyConfig.pay_easy_merdata6 = "7565";
		
		/**
		 * 测试环境
		 */
		/*  */
		// 提现转账查询等相关的key测试地址
//		PayEasyConfig.keycode = "NSIfWIgQQDg4JSkny91VZnAhdGUwIEDh";
		PayEasyConfig.keycode = "ESJPWIgWQDg4RXlRy91VZncpdJgwQEDi";
		// 账号同步url
		//PayEasyConfig.PAY_EASY_SYNC_ACCOUT_URL = "http://pay.duomeidai.com/duomei_test/synclient";
//		PayEasyConfig.PAY_EASY_SYNC_ACCOUT_URL = "http://p2poffline6.yizhifubj.com/duomei_test/synclient";
		PayEasyConfig.PAY_EASY_SYNC_ACCOUT_URL = "http://amspro.yizhifubj.com/duomei_uat/synclient";
		//查询账户资产
		PayEasyConfig.pay_easy_search_merchantInfo_url="http://p2poffline6.yizhifubj.com/duomei_test/transaccquery";
		
		
		/**
		 * 生产环境
		 */
		/*
		PayEasyConfig.keycode = "ASIfWIgQQDgyGSknq91VZnEhdGUwIEDi";
		// 账号同步url
		PayEasyConfig.PAY_EASY_SYNC_ACCOUT_URL = "http://p2p.yizhifubj.com/duomei/synclient";
		//查询账户资产
		PayEasyConfig.pay_easy_search_merchantInfo_url="http://p2p.yizhifubj.com/duomei/transaccquery";
		*/
	}
	
	/**
	 * @1、查询账户是否开通
	 * 
	 *  10000	账户同步开通
		10001	同步用户身份信息
		10002	同步用户手机号码
		10003	查询账户是否开通
		10004	同步用户银行卡信息
		10005	查询银行卡验证结果
	 */
	@Test
	public void accountSync(){
		System.out.println("---------查询账户是否开通--------");
		String userId = "18891950";
		//10003	查询账户是否开通
		
		SyncAccountReq req = new SyncAccountReq();
		req.setTransCode(PayEasyConfig.PAY_EASY_SYNC_ACCOUNT_TRANSCODE);
		req.setReqTime(UtilDate.getDateFormatter());
		// 查询账户是否开通
		req.setOperationCode("10001");
		req.setUser(userId);
		req.setGroupId(PayEasyConfig.pay_easy_merdata6);
		req.setBranchId(PayEasyConfig.pay_easy_merdata7);
		
		// 同步身份信息
		//req.setMobile("15811372713");
		
		req.setIdType("01");
		req.setId("341282199003081279"); //身份证号
		req.setUserName("何超杰"); // 真实姓名 realName
		
		SyncAccountResp response = getRespByTrsFeeCashReq(req, PayEasyConfig.keycode,
				PayEasyConfig.PAY_EASY_SYNC_ACCOUT_URL);
		System.out.println(response.getReturnCode()+":"+response.getReturnMsg());
	}
	
	/**
	 * @2、查询银行卡验证结果
	 * 10005	查询银行卡验证结果
	 * 10004        同步银行卡
	 * 10008        四项验卡（银行卡）
	 * String sql = "select a.userId,a.cardUserName,a.bankName,a.cardNo,a.bankProvince,a.bankCity,b.idNo,b.realName,b.idType"+
			"from t_bankcard a left join t_person b on a.userId = b.userId where a.userId = ";
	 */
	@Test
	public void respByTrsFeeCashReq() {
		
		System.out.println("---------查询银行卡验证结果--------");
		String userId = "18891950";
		
		SyncAccountReq req = new SyncAccountReq();
		
		//==========================================
		req.setId("341282199003081279"); //身份证号
		req.setUserName("何超杰"); // 真实姓名 realName
		
		req.setAccName("何超杰");// 银行卡用户名 cardUserName
		req.setAccProvice("安徽省");// 银行卡所在省
		req.setAccCity("阜阳市");// 城市
		req.setAccBank("中国工商银行股份有限公司");// 银行名称
		req.setAccNum("6222021311000657841");
		req.setMobile("15811372713");
		// ========================================
		req.setTransCode(PayEasyConfig.PAY_EASY_SYNC_ACCOUNT_TRANSCODE);
		req.setReqTime(UtilDate.getDateFormatter());
		// 查询银行卡验证结果
		req.setOperationCode("10005");
		req.setUser(userId);
		req.setGroupId(PayEasyConfig.pay_easy_merdata6);
		req.setBranchId(PayEasyConfig.pay_easy_merdata7);
		req.setAccBranchName("");
		//01身份证
		req.setIdType("01");
		req.setAccType(PayEasyConfig.pay_easy_withdaral_default_accType);
		req.setAccProp(PayEasyConfig.pay_easy_withdaral_default_accProp);
		
		// 商户流水号
		req.setSerlNum(PayEasyConfig.pay_easy_merdata6+"20160113184111111");
		
		SyncAccountResp response = getRespByTrsFeeCashReq(req, PayEasyConfig.keycode,
				PayEasyConfig.PAY_EASY_SYNC_ACCOUT_URL);
		System.out.println(response.getReturnCode()+":"+response.getReturnMsg());
	}

	
	/**
	 * @3、查询首信易用户资金
	 * 60000：查询投资账户额度
	 * 60001：查询负债账户额度
	 */
	@Test
	public void searchMerchantInfo(){
		
		TrsAccountServiceResp trs = searchMerchant("30000374","60000");
		System.out.println("------"+trs.getReturnCode()+"-"+trs.getReturnMsg()+"--------------");
		System.out.println("总  金  额："+trs.getCurrentBalance());
		System.out.println("可用余额："+trs.getAvailableBalance());
		System.out.println("冻结金额："+trs.getBlockedBalance());
	}
	
	/**
	 * 查询虚拟账户余额
	 * 
	 * @return
	 */
	private TrsAccountServiceResp searchMerchant(String user,
			String operationCode) {
		
		Date now = new Date();
		String transCode = PayEasyConfig.SEARCH_MEMCHENT_TRANSCODE;
		String nowStr = DateUtil.YYYY_MM_DD_MM_HH_SS.format(now);
		String pin = PayEasyConfig.pay_easy_withdaral_default_pin;
		//pay_easy_v_mid=7548
		PayEasyConfig.pay_easy_v_mid = "7548";
		String groupId = PayEasyConfig.pay_easy_v_mid;
		
		String branchId = PayEasyConfig.pay_easy_withdaral_default_branchId;
		String merData1 = PayEasyConfig.pay_easy_withdaral_merData1;
		String merData2 = PayEasyConfig.pay_easy_withdaral_merData2;
		TrsAccountServiceResp trsAccountServiceResp = searchMerchantInfo(transCode, nowStr, operationCode, user,
						pin, groupId, branchId, merData1, merData2);
		return trsAccountServiceResp;
	}
	
	
	private SyncAccountResp getRespByTrsFeeCashReq(SyncAccountReq syncAccountReq, String keyCode, String postUrl) {
		String strContent = GsonUtil.toJsonString(syncAccountReq);
		log.info("同步账号请求内容：" + strContent);
		String decryptContentStr = getRespJsonStr(strContent, keyCode, postUrl);
		log.info("同步账号返回内容：" + decryptContentStr);
		SyncAccountResp resp = GsonUtil.toObject(decryptContentStr, SyncAccountResp.class);
		return resp;
	}
	
	private String getRespJsonStr(String strContent, String keyCode, String postUrl) {
		byte[] encoded = Des3Utill.encryptMode(keyCode, strContent.getBytes());
		String encodeContenStr = new String(encoded);
		String responeStr = HttpsJsonUtil.httpJsonPost(postUrl, encodeContenStr);
		byte[] decryptContentByte = Des3Utill.decryptMode(keyCode, Base64.decodeBase64(responeStr.getBytes()));
		String decryptContentStr = new String(decryptContentByte);
		return decryptContentStr;
	}
	
	
	/**
	 * 查询商户可用余额
	 * 
	 * @param transCode
	 *            交易码
	 * @param reqTime
	 *            请求日期
	 * @param operationCode
	 *            业务操作码
	 * @param user
	 *            P2P网站用户注册名
	 * @param pin
	 *            用户密码
	 * @param groupId
	 *            P2P商户编号
	 * @param branchId
	 *            P2P子公司编号
	 * @param merData1
	 *            预留字段1
	 * @param merData2
	 *            预留字段2
	 * @return
	 */
	public TrsAccountServiceResp searchMerchantInfo(String transCode, String reqTime, String operationCode,
			String user, String pin, String groupId, String branchId, String merData1, String merData2) {
		TrsAccountServiceReq trsAccountServiceReq = new TrsAccountServiceReq();
		trsAccountServiceReq.setTransCode(transCode);
		trsAccountServiceReq.setReqTime(reqTime);
		trsAccountServiceReq.setOperationCode(operationCode);
		trsAccountServiceReq.setUser(user);
		trsAccountServiceReq.setPin(pin);
		trsAccountServiceReq.setGroupId(groupId);
		trsAccountServiceReq.setBranchId(branchId);
		trsAccountServiceReq.setMerdata1(merData1);
		trsAccountServiceReq.setMerdata2(merData2);
		log.info("获取用户余额请求内容：" + GsonUtil.toJsonString(trsAccountServiceReq));
		log.info(PayEasyConfig.pay_easy_search_merchantInfo_url);
		log.info(PayEasyConfig.keycode);
		TrsAccountServiceResp resp = getRespByTrsAccountServiceReq(trsAccountServiceReq, PayEasyConfig.keycode,
				PayEasyConfig.pay_easy_search_merchantInfo_url);

		if (null != resp) {
			if (null != resp.getAvailableBalance()) {
				resp.setAvailableBalance(resp.getAvailableBalance().replaceAll(",", ""));
			}
			if (null != resp.getBlockedBalance()) {
				resp.setBlockedBalance(resp.getBlockedBalance().replaceAll(",", ""));

			}
		}

		return resp;
	}

	/**
	 * 得到商户可用余额的实体
	 * 
	 * @param trsAccountServiceReq
	 * @param keyCode
	 * @param postUrl
	 * @return
	 */
	private TrsAccountServiceResp getRespByTrsAccountServiceReq(TrsAccountServiceReq trsAccountServiceReq,
			String keyCode, String postUrl) {
		String strContent = JsonMapper.toJsonString(trsAccountServiceReq);
		String decryptContentStr = getRespJsonStr(strContent, keyCode, postUrl);
		log.info("获取用户余额返回内容：" + decryptContentStr);

		return JsonMapper.toObject(decryptContentStr, TrsAccountServiceResp.class);
	}
}
