package test.hecj.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;

import test.hecj.util.QRCodeUtil_DMD;

import com.gozap.web.util.MathCalcUtill;

public class Test01 {
	
	
	@Test
	public void test01(){
		System.out.println(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		
		
	}
	// 1439740800000 1439827200000
	@Test
	public void test02() throws ParseException{
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = "2015-08-18 00:00:00";
		System.out.println(format.parse(str).getTime());
	}
	
	@Test
	public void test03(){
		BigDecimal amount = new BigDecimal(10000);
		double decale = 0.51222352d ;
		
		System.out.println(MathCalcUtill.mul(amount.toString(), String.valueOf(decale)));
		
	}
	
	@Test
	public void test04(){
		DecimalFormat formater = new DecimalFormat();
        formater.setMaximumFractionDigits(2);
        formater.setGroupingSize(0);
        formater.setRoundingMode(RoundingMode.FLOOR);
        
		double d = MathCalcUtill.div(31d, 1000d);
		System.out.println(d);
		System.out.println(formater.format(d));
		System.out.println(Double.valueOf(formater.format(d)));
		
	}
	
	@Test
	public void test05(){
		
		System.out.println(2.5%1);
		
	}
	
	@Test
	public void test06() throws IOException{
		
		String content = "http://dev.red.duomeidai.com/#/activity/loveindex/-1:-1";
		try {
			FileOutputStream out = new FileOutputStream(new File("d://weixin.jpg"));
			QRCodeUtil_DMD.generateQRCode(content, out, 200, 200);
			
			out.flush();
			out.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void test07(){
		
		String pwd = "   '11111   ";
		System.out.println(pwd.trim().length());
	}
	
}
