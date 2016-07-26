package com.test.hecj.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.gozap.base.BaseService;
import com.gozap.vo.PageBean;
import com.longdai.data.DataException;
import com.longdai.data.dao.MySQL;
/**
 * 执行存过(分页)
 * @author hechaojie
 *
 */
public class MySQLProcedureTest {
	
	public static void main(String[] args) {
		
		try {
			Connection conn = MySQL.getConnection();
		
			BaseService base = new BaseService();
			PageBean<Map<String, Object>> pageBean = new PageBean<Map<String, Object>>();
			pageBean.setPageSize(10);
			base.dataPage(conn, pageBean, "t_user", "*", "", "");
			
			System.out.println(pageBean.getTotalNum());
			List<Map<String,Object>> list = pageBean.getPage();
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				Set<String> keys = map.keySet();
				Iterator<String> its = keys.iterator();
				while(its.hasNext()){
					String key = its.next();
					Object val = (Object) map.get(key);
					System.out.println(key+":"+val);
				}
				System.out.println("\n");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DataException e) {
			e.printStackTrace();
		}
		
	}
}
