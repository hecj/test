package com.hecj.weixin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class LogServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(getClass());

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.service(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setHeader("Content-type", "text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		try {
			
			ServletContext context = req.getSession().getServletContext();
			List<String> logs = (List<String>) context.getAttribute("logs");
			StringBuffer sb = new StringBuffer();
			for(String log : logs){
				sb.append(log+"<br>");
			}
			out.print(sb.toString());
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} finally{
			out.flush();
			out.close();
			out = null;
		}
	}

	@SuppressWarnings("unchecked")
	public static void addLog(HttpServletRequest req, String log) {
		
		ServletContext context = req.getSession().getServletContext();
		
		Object obj = context.getAttribute("logs");
		List<String> logs ;
		if(obj == null){
			logs = new ArrayList<String>();
		}else{
			logs = (List<String>) obj;
		}
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logs.add(format.format(new Date())+" - "+log);
		context.setAttribute("logs", logs);
	}

}
