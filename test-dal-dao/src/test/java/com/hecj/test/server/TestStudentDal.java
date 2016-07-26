package com.hecj.test.server;

import java.util.List;

import org.junit.Test;

import com.gemantic.dal.dao.exception.DaoException;
import com.gemantic.dal.dao.impl.CompositeDaoImpl;
import com.hecj.test.model.Student;

public class TestStudentDal {
	
	CompositeDaoImpl dao = new CompositeDaoImpl();
	
	@Test
	public void save(){
		
		Student s = new Student();
		s.setName("何超杰");
		try {
			dao.save(s);
			
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void delete(){
		
		try {
			dao.delete(Student.class, 1);
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void exe(){
		
//		try {
//
//			List<Student> list = (List<Student>) dao.excuteSimpleSql("select * from student", Student.class);
//			System.out.println(list.size());
//			for(int i=0;i<list.size();i++){
//				System.out.println(list.get(i).getId());
//			}
//		} catch (DaoException e) {
//			e.printStackTrace();
//		}
		
	}
	
	@Test 
	public void exe2(){
		try {
			dao.getIdList("getIds", 12, true);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}



