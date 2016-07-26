package test.com.hecj.service;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gozap.web.util.Des3Utill;
import com.gozap.web.util.HttpsJsonUtil;

public class HttpTest {
	public static Log log = LogFactory.getLog(HttpsJsonUtil.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
			String keyCode = "NSIfWIgQQDg4JSkny91VZnAhdGUwIEDh";
			String postUrl = "http://pay.duomeidai.com/duomei_test/transfer";
			String strContent = "{\"transCode\":\"TRS001007\",\"reqTime\":\"2015-04-16 18:17:56\",\"operationCode\":\"24003\",\"serlNum\":\"201504160969729199\",\"authId\":\"\",\"transferInIdType\":\"\",\"transferInId\":\"\",\"transferInMobile\":\"\",\"transferOutIdType\":\"\",\"transferOutId\":\"\",\"transferOutIMobile\":\"\",\"pin\":\"\",\"TransferOutUser\":\"PLAN_MGR_FEE\",\"TransferInUser\":\"30000220\",\"TransferAmount\":\"35.00\",\"TransferOutUserFee\":\"0.00\",\"TransferInUserFee\":\"0.00\",\"groupId\":\"7565\",\"branchId\":\"duomei\",\"merdata1\":\"\",\"merdata2\":\"\"}";
	        log.info("accountTransferResult 请求的内容：" + strContent);
			byte[] encoded = Des3Utill.encryptMode(keyCode, strContent.getBytes());
			String encodeContenStr = new String(encoded);
			String responeStr = HttpsJsonUtil.httpJsonPost(postUrl, encodeContenStr);
			log.info("response 返回的内容：" + responeStr);
			byte[] decryptContentByte = Des3Utill.decryptMode(keyCode, Base64.decodeBase64(responeStr.getBytes()));
			String decryptContentStr = new String(decryptContentByte);
			System.out.println(decryptContentStr);

	}

}
