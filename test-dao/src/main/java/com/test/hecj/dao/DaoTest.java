package com.test.hecj.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.longdai.data.dao.MySQL;
import com.longdai.database.Dao;

public class DaoTest {
	
	public static void main(String[] args) {
		
		try {
			Connection conn = MySQL.getConnection();
			Dao.Tables.t_user t_user = new Dao().new Tables().new t_user();
			long total = t_user.getCount(conn, "");
			System.out.println(total);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
