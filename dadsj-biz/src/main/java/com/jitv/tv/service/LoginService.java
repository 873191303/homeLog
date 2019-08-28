package com.jitv.tv.service;

import java.util.Map;

public interface LoginService {
	Map<String, Object> login(String loginName, String passWord, String token, String outIP);

	void LoginOut(String token);

	Boolean verification(String token);

	Map<String, Object> getLoginUserInfo(String token);
}
