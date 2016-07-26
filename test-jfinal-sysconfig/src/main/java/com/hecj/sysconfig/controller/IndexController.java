package com.hecj.sysconfig.controller;

import java.util.List;

import com.hecj.sysconfig.model.PortConfig;
import com.jfinal.core.Controller;

public class IndexController extends Controller {
	
	public void index() {
		
		List<PortConfig> list = PortConfig.me.findAll();
		
		
//		renderText("Hello JFinal World.");
		renderJson(list);
	}
	public void index2() {
		renderText("Hello2JFinal World.");
	}
}