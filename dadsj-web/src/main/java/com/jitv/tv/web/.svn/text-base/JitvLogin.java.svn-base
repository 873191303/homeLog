package com.jitv.tv.web;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.httpclient.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aspire.commons.BeanFactory;
import com.aspire.commons.HttpClientHelper;
import com.aspire.commons.rpc.RpcContext;
import com.aspire.commons.util.JsonUtil;
import com.aspire.commons.util.StringUtil;
import com.jitv.tv.service.BehaviorService;
import com.jitv.tv.service.LoginService;
import com.jitv.tv.util.ExecutorUtil;
import com.jitv.tv.util.Md5Utils;
import com.jitv.tv.util.WebUtil;

@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
public class JitvLogin {

	private static final Logger logger = LoggerFactory.getLogger(JitvLogin.class);

	private static LoginService loginService;

	private static BehaviorService behaviorService;

	public static String UI_USERTOKEN_NAME = "DABIGDATA_TOKEN";

	public Map<String, Object> isLogin(HttpServletRequest Request, HttpServletResponse Response) {
		Map<String, Object> back = new HashMap<String, Object>();
		back.put("Request", Request);
		boolean ok = true;
		String userToken = "";
		String url = Request.getRequestURI();
		try {
			if (url.toLowerCase().matches("([^\\?]*)\\.(?:js|png|jpg|gif|jpeg)")) {
			} else {
				userToken = getUserTokenByUI(Request);

				if (url.indexOf("login/imgcode") == -1 && url.indexOf("action/login/key") == -1
						&& url.indexOf("action/login") == -1 && url.indexOf("action/logout") == -1 && url.indexOf("/action/homeLog/IF_NOTIFY") == -1

				) {
					// 判断token是否合法
					ok = isOkToken(userToken);
				}
			}
			back.put("ok", ok);
			back.put("Request", Request);
		} catch (Exception ex) {
			logger.info("isLogin Exception:", ex);
		}
		return back;
	}

	public static boolean isOkToken(String userToken) {

		if (loginService == null) {
			loginService = (LoginService) BeanFactory.getBean("loginService");
		}
		return loginService.verification(userToken);

	}

	public static Map<String, Object> getLoginUserInfo(HttpServletRequest Request) {
		return getLoginUserInfo(getUserTokenByUI(Request));
	}

	public static Map<String, Object> getLoginUserInfo(String token) {
		if (loginService == null) {
			loginService = (LoginService) BeanFactory.getBean("loginService");
		}
		return loginService.getLoginUserInfo(token);
	}

	public static String getUserId(String token) {
		Map<String, Object> user = getLoginUserInfo(token);
		if (user != null && user.containsKey("userId")) {
			return user.get("userId").toString();
		}
		return "";
	}

	public static String getUserId(HttpServletRequest Request) {
		Map<String, Object> user = getLoginUserInfo(Request);
		if (user != null && user.containsKey("userId")) {
			return user.get("userId").toString();
		}
		return "";
	}

	public static String getUserTokenByUI(HttpServletRequest Request) {
		String userToken = Request.getParameter(UI_USERTOKEN_NAME);
		if (StringUtil.isEmpty(userToken)) {
			Cookie[] cookies = Request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (UI_USERTOKEN_NAME.contains(cookie.getName()) && cookie.getValue().length() > 0) {
						userToken = cookie.getValue();
					}
				}
			}
		}
		return userToken;
	}

	public static String getUserToken(String userId) {
		return Md5Utils.getMd5(userId);
	}

	// 用户日志相关
	private static Map<String, Object> URLMAP = new HashMap<String, Object>();

	static {
		URLMAP.put("/action/sys/user/add.action", "用户新增 ");
		URLMAP.put("/action/sys/user/del.action", "用户删除 ");
		URLMAP.put("/action/sys/user/update.action", "用户更新");
		URLMAP.put("/action/sys/user/sel.action", "用户列表查询");
		URLMAP.put("/action/sys/user/userid.action", "个人信息");
		URLMAP.put("/action/sys/user/setpwd.action", "密码修改");
		URLMAP.put("/action/sys/role/userid.action", "获取用户角色");
		URLMAP.put("/action/sys/user/role.action", "查询所有角色");
		URLMAP.put("/action/sys/user/addrole.action", "修改角色");
		URLMAP.put("/action/sys/user/sel/resource.action", "查询资源");
		URLMAP.put("/action/login.action", "登陆");
		URLMAP.put("/action/logout.action", "登出");
		URLMAP.put("/action/resources/add.action", "新增资源");
		URLMAP.put("/action/resources/delete.action", "删除资源");
		URLMAP.put("/action/resources/update.action", "修改资源");
		URLMAP.put("/action/resources/update/roleid.action", "修改角色对应资源");
		URLMAP.put("/action/role/list.action", "角色查询");
		URLMAP.put("/action/role/add.action", "角色新增");
		URLMAP.put("/action/role/delete.action", "角色删除");
		URLMAP.put("/action/role/update.action", "角色修改");
		URLMAP.put("/action/role/user/update.action", "修改用户对应角色");
	}

	public static void log(final String url, final String xmName, final String ip, String token) {
		Runnable task = new Runnable() {
			@Override
			public void run() {
				try {
					String url2 = url.replace(xmName, "");
					if (url2 != null) {
						if (URLMAP.containsKey(url2)) {
							// String Name = request.getContextPath();//项目名称
							String userId = JitvLogin.getUserId(token);
							String type = (String) URLMAP.get(url2);// 类型
							if (behaviorService == null) {
								behaviorService = (BehaviorService) BeanFactory.getBean("behaviorService");
							}
							behaviorService.add(ip, userId, type, "", url2);
							logger.debug("url =" + url);
							logger.debug("ip =" + ip);
							logger.debug("token =" + token);
							logger.debug("userId =" + userId);
						}
					}

				} catch (Exception e) {
					logger.error("操作日志异常", e);
				}

			}
		};
		ExecutorUtil.submit(task);

	}

}
