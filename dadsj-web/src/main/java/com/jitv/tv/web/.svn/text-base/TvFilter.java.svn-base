package com.jitv.tv.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aspire.commons.AspireRuntimeException;
import com.aspire.commons.Errors;
import com.aspire.commons.HttpClientHelper;
import com.aspire.commons.util.JsonUtil;
import com.jitv.tv.util.ErrorCode;
import com.jitv.tv.util.ExecutorUtil;
import com.jitv.tv.util.WebUtil;

public class TvFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(TvFilter.class);

	public void destroy() {

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		JitvLogin JitvLogin = new JitvLogin();
		HttpServletRequest Request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		response.setHeader("Access-Control-Allow-Origin", "*");
		String ua = Request.getHeader("User-Agent");
		logger.debug("+++++ url=" + Request.getRequestURI() + "  ua=" + ua + " ipInfo=" + getIpAddress(Request));

		String uniqueIdentifier = Request.getParameter("uniqueIdentifier") == null ? ""
				: (String) Request.getParameter("uniqueIdentifier");
		// logger.info("uniqueIdentifier**************"+uniqueIdentifier);

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers",
				"Content-Type, x-requested-with, X-Custom-Header,DABIGDATA_TOKEN");
		if ("OPTIONS".equals(Request.getMethod())) {
			response.setStatus(HttpStatus.SC_NO_CONTENT);
		}

		Map<String, Object> pdMap = JitvLogin.isLogin(Request, response);
		boolean ok = (boolean) pdMap.get("ok");
		// String url = ((HttpServletRequest)arg0).getRequestURI();
		if (ok) {
			Request = (HttpServletRequest) pdMap.get("Request");
			arg2.doFilter(Request, response);
		} else {
			Map<String, Object> rtv = new LinkedHashMap<String, Object>();
			rtv.put("result", "0");
			rtv.put("requestId", Request.getParameter("requestId"));
			rtv.put("data", null);
			rtv.put("version", "2.0");
			rtv.put("optCode", null);
			AspireRuntimeException are = ErrorCode.valueOf(ErrorCode.clone(ErrorCode.E222));
			rtv.put("errorMessage", are.getErrorCode());
			rtv.put("error", ErrorCode.toMap(are));
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(JsonUtil.toJson(rtv));
		}

		String url = ((HttpServletRequest) arg0).getRequestURI();
		// 用户操作日志相关
		if (url.indexOf(".action") != -1) {
			String token = JitvLogin.getUserTokenByUI(Request);
			if (url.indexOf("action/login.action") > -1) {
				token = Request.getAttribute("token") == null ? "" : (String) Request.getAttribute("token");
			}
			JitvLogin.log(url, Request.getContextPath(), getIpAddress(Request), token);
		}
	}

	private String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

	public void test(final String actionUrl, final String time, final String fullPath, final String type,
			final HttpServletRequest Request) {
		Runnable task = new Runnable() {
			public void run() {
				// final String key =
				// "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCG33lOt/xNUQQr9D7Fip2IwwpTI3WXzLuS/E0hbPQwL1kUWHQeztrdwjo/5Jsa39Nld7NfT6ED1BaVz7smladL6F6DAnvzZjz0b1FoABBo/RX9dS6t7epFLCSFAOlfzZOSVGliqYHjfvyNiEisTGkxWsCANjVFO1v1FiP7qu2cNwIDAQAB";
				Map<String, Object> map = new HashMap<>();
				map.put("actionUrl", actionUrl);// 页面地址
				map.put("type", type);// 调用类型（页面，按钮）
				map.put("time", time);// 调用时间
				map.put("fullPath", fullPath);// 用于获取设备Id，版本
				String ip = Request.getHeader("x-forwarded-for");
				if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
					ip = Request.getHeader("Proxy-Client-IP");
				}
				if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
					ip = Request.getHeader("WL-Proxy-Client-IP");
				}
				if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
					ip = Request.getHeader("HTTP_CLIENT_IP");
				}
				if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
					ip = Request.getHeader("HTTP_X_FORWARDED_FOR");
				}
				if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
					ip = Request.getRemoteAddr();
				}
				map.put("ip", ip);// 用于获取用户ip
				try {
					String str = JsonUtil.toJson(map);
					Map<String, String> map1 = new HashMap<>();
					map1.put("info", str);
					String strMessage = JsonUtil.toJson(map1);
					// ProducerUtil.send("insertRecordingByJITV", strMessage);
					if ("VideoDetails.html".equals(actionUrl) || "zhuantiye.html".equals(actionUrl)
							|| "tvxiangqingye.html".equals(actionUrl)) {
						map1 = new HashMap<>();
						map1.put("info", str);
						String createTime = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
						map.put("createTime", createTime);
						strMessage = JsonUtil.toJson(map1);
						// ProducerUtil.send("insertChannelBehavior", strMessage);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		ExecutorUtil.submit(task);
	}
}
