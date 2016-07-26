package com.hecj.weixin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.hecj.weixin.util.SignUtil;

public class WXServlet extends HttpServlet {

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
			// 微信加密签名
			String signature = req.getParameter("signature");
			// 时间戳
			String timestamp = req.getParameter("timestamp");
			// 随机数
			String nonce = req.getParameter("nonce");
			// 随机字符串
			String echostr = req.getParameter("echostr");
			LogServlet.addLog(req,"signature:" + signature + ",timestamp:" + timestamp + ",nonce:" + nonce + ",echostr:" + echostr);
			log.info("signature:" + signature + ",timestamp:" + timestamp + ",nonce:" + nonce + ",echostr:" + echostr);

			if(StringUtils.isBlank(signature)||StringUtils.isBlank(timestamp)||StringUtils.isBlank(nonce)
					||StringUtils.isBlank(echostr)){
				LogServlet.addLog(req,"参数有空值情况，请检查。");
				log.info("参数有空值情况，请检查。");
				out.print("参数有空值情况，请检查。");
				return;
			}
			
			// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
			if (SignUtil.checkSignature(signature, timestamp, nonce)) {
				out.print(echostr);
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} finally{
			out.close();
			out = null;
		}
	}

	private void writer(HttpServletResponse resp, Object o) {
		try {
			// resp.setContentType("text/html;charset=UTF-8");
			resp.setHeader("Content-type", "text/html;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.print(JSONObject.toJSONString(o));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
