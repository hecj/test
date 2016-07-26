package com.hecj.sysconfig.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.hecj.sysconfig.controller.IndexController;
import com.hecj.sysconfig.model.PortConfig;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.druid.DruidStatViewHandler;

public class GlobalConfig extends JFinalConfig {
	
	public void configConstant(Constants me) {
		loadPropertyFile("config.properties");
		me.setDevMode(getPropertyToBoolean("devMode", false));
	}

	public void configRoute(Routes me) {
		me.add("/index", IndexController.class);
	}

	public void configPlugin(Plugins me) {
		// DruidPlugin
		DruidPlugin dp = new DruidPlugin(getProperty("msyql.jdbcUrl"),getProperty("msyql.user"), getProperty("msyql.password"));
		dp.addFilter(new StatFilter());
		WallFilter wall = new WallFilter();
		wall.setDbType("mysql");
		dp.addFilter(wall);
		me.add(dp);

		// ActiveRecordPlugin
		ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
		me.add(arp);
		arp.addMapping("portConfig", PortConfig.class);
	}

	public void configInterceptor(Interceptors me) {
		
	}

	public void configHandler(Handlers me) {

		DruidStatViewHandler dvh = new DruidStatViewHandler("/druid");
		me.add(dvh);
	}
}