package com.hecj.weixin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class LogDelServlet extends HttpServlet {

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
		
		String ns = req.getParameter("n");
		Long n = Long.parseLong(ns);
		
		PrintWriter out = resp.getWriter();
		try {
			
			ServletContext context = req.getSession().getServletContext();
			List<String> logs = (List<String>) context.getAttribute("logs");
			for(Long i=0l;i<n;i++){
				if(logs.size() ==0){
					break;
				}
				logs.remove(0);
			}
			context.setAttribute("logs", logs);
			
			out.print("日志清理成功");

		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} finally{
			out.flush();
			out.close();
			out = null;
		}
	}

}
