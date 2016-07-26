package test.hecj.pay;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class InvestService {
	
	/**
	 * 1.同步账户开通
	 * SyncAccountResp com.longdai.service.SyncAccountService.accountSync(String userId)
	 * 2.实名认证 
	 * Open Declaration SyncAccountResp com.longdai.service.SyncAccountService.accountSync(String userId, String id, String username)
	 * @throws ParseException 
	 */
	@Test
	public void test01() throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = format.parse("2015-7-8 15:00:00");
		System.out.println(d.getTime());
	}
}
