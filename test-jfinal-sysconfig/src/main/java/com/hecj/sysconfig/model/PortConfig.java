package com.hecj.sysconfig.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

public class PortConfig extends Model<PortConfig> {
	
	public static final PortConfig me = new PortConfig();
	
	public List<PortConfig> findAll(){
		List<PortConfig> list = null ;
		try {
			
			return this.me.find("select p.* from portConfig p");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  null;
	}
}
