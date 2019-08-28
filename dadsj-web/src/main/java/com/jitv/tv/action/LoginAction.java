package com.jitv.tv.action;

import javax.servlet.http.Cookie;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aspire.commons.rpc.RpcBean;
import com.aspire.commons.rpc.RpcContext;
import com.aspire.commons.rpc.RpcContexts;
import com.aspire.commons.rpc.RpcMethod;
import com.aspire.commons.util.StringUtil;
import com.jitv.tv.service.LoginService;
import com.jitv.tv.util.ErrorCode;
import com.jitv.tv.util.JitvGuidUtil;
import com.jitv.tv.util.WebUtil;
import com.jitv.tv.utils.ValidCodeUtils;
import com.jitv.tv.web.JitvLogin;

@RpcBean
public class LoginAction {
	private final static Logger logger = LoggerFactory.getLogger(LoginAction.class);

	private LoginService loginService;

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	@RpcMethod("/action/login/key")
	public void roleList() {
		RpcContext rc = RpcContexts.getContext();
		String key = JitvGuidUtil.getGuid();
		ValidCodeUtils.setCodeToRedis(key);
		rc.setData(key);

	}

	// 登陆 wxg 2019-01-06
	@RpcMethod("/action/login")
	public void Login() {
		RpcContext rc = RpcContexts.getContext();
		String loginName = rc.params().get("loginName") == null ? "" : (String) rc.params().get("loginName");
		String passWord = rc.params().get("passWord") == null ? "" : (String) rc.params().get("passWord");

		String tokenName = JitvLogin.getUserToken(loginName);
		String codeKey = rc.params().get("codeKey") == null ? "" : (String) rc.params().get("codeKey");
		String code = rc.params().get("code") == null ? "" : (String) rc.params().get("code");
		if (!ValidCodeUtils.equalCode(codeKey, code)) {
			throw ErrorCode.E997;
		}

		if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(passWord)) {
			throw ErrorCode.E700;
		} else {
			// 获取客户端的IP
			String outIP = WebUtil.getIpAddress(rc.getHttpServletRequest());
			rc.setData(loginService.login(loginName, passWord, tokenName,outIP));
			rc.getHttpServletRequest().setAttribute("token", tokenName);
		}
	}

	// 退出 wxg 2019-01-16
	@RpcMethod("/action/logout")
	public void LoginOut() {
		RpcContext rc = RpcContexts.getContext();
		String userToken = rc.getHttpServletRequest().getParameter(JitvLogin.UI_USERTOKEN_NAME);
		if (StringUtil.isEmpty(userToken)) {
			Cookie[] cookies = rc.getHttpServletRequest().getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (JitvLogin.UI_USERTOKEN_NAME.contains(cookie.getName()) && cookie.getValue().length() > 0) {
						userToken = cookie.getValue();
					}
				}
			}
		}
		loginService.LoginOut(userToken);
	}

	@RpcMethod("/action/login/user")
	public void loginUser() {
		RpcContext rc = RpcContexts.getContext();
		rc.setData(JitvLogin.getLoginUserInfo(rc.getHttpServletRequest()));
	}
}
