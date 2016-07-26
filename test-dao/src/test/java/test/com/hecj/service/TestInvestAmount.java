package test.com.hecj.service;

import java.math.BigDecimal;

import org.junit.Test;

import com.gozap.web.util.MathCalcUtill;
import com.longdai.constants.IConstants;
import com.longdai.service.FinanceService;

public class TestInvestAmount {
	
	public static void main(String[] args) {
		
//		FinanceService fs = new FinanceService();
//		BigDecimal useableSum = new BigDecimal(30000);
//		BigDecimal InvestAmountEnd = new BigDecimal(300001);
//		BigDecimal hasInvestAmount = new BigDecimal(500000);
//		BigDecimal borrowAmount = new BigDecimal(500000);
//		BigDecimal userInvestAmount = new BigDecimal(246000);
//		BigDecimal investAmount = fs.getInvestAmount(useableSum, InvestAmountEnd, hasInvestAmount, borrowAmount, userInvestAmount);
//		System.out.println(investAmount);
//		
//		
//		System.out.println(useableSum.equals(InvestAmountEnd));
		
//		long num = MathCalcUtill.strDiv("3185", IConstants.UNIT_PRICE, 2);
//		
//		 BigDecimal b1 = new BigDecimal("31825");
//         BigDecimal b2 = new BigDecimal("100");
////         System.out.println(b1.remainder(b2));
////         BigDecimal c =  b1.remainder(b2).setScale(2, BigDecimal.ROUND_HALF_UP);
////         System.out.println(c);
//         
//         long c =  b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).longValue();
//         System.out.println(c);
		
        BigDecimal bg2 = BigDecimal.valueOf (32423423.4);
        BigDecimal bg = BigDecimal.valueOf (500);
        System.out.println (bg2.divideAndRemainder (bg)[1]);
        
        System.out.println(32423423.4%500);
		
        
	}
	
}
