package test.service.main;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class MainTest {

	/**
	 * @param args
	 */
	public static void test01(){
		
		java.text.DecimalFormat myformat=new java.text.DecimalFormat("0.0000");
		
		BigDecimal a = new BigDecimal(1);
		BigDecimal b = new BigDecimal(1000);
		
		System.out.println(a.doubleValue()/b.doubleValue());
		System.out.println(myformat.format(a.doubleValue()/b.doubleValue()));
		
	}
	
	@Test
	public void test02() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class cls = this.getClass();
		String s = cls.getName();
		System.out.println(s);
		System.out.println(cls.getSimpleName());
		
		Method method = cls.getMethod("test01");
		method.invoke(cls);
	}
	/*
	public static void main(String[] args) {
		
		String orderBy = "ORDER BY " +
				 " IF(borrowStatus=2,publishTime,0) desc," +
				 " IF(borrowStatus=7,publishTime,0) desc," +
				 " IF(borrowStatus=3,borrowCompleteTime,0) desc," +
                 " IF(borrowStatus=4,borrowCompleteTime,0) desc," +
                 " IF(borrowStatus=5,publishTime,0) desc," +
                 " id desc";
		System.out.println(orderBy.length());
		
	}
	*/
	@Test
	public void test03(){
		System.out.println(System.currentTimeMillis());
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		long beginAt = calendar.getTime().getTime();
		System.out.println(beginAt);
		System.out.println(calendar.getTime());
		
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MILLISECOND, -1);
		
		long endAt = calendar.getTime().getTime();
		System.out.println(endAt);
		System.out.println(calendar.getTime());
	}
	
	
}
