package com.test.hecj.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import com.longdai.data.dao.MySQL;
import com.longdai.data.dao.Parameter;
import com.longdai.data.dao.ParameterDirection;
/**
 * 执行函数
 * @author hechaojie
 *
 */
public class MySQLFunctionTest {
	
	public static void main(String[] args) {
		
		try {
			Connection conn = MySQL.getConnection();
		
				Object result = MySQL.executeFunction(conn,"`test_totaluser`", 
						new Parameter(Types.INTEGER,ParameterDirection.RETURN, null),
						new Parameter(Types.INTEGER, ParameterDirection.IN,10000));
				System.out.println(result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
