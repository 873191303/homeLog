package com.jitv.tv.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aspire.commons.util.StringUtil;
import com.jitv.tv.dao.ErrorLoginDao;
import com.jitv.tv.dao.UserDao;
import com.jitv.tv.dto.ErrorLoginDTO;
import com.jitv.tv.dto.ResourcesDTO;
import com.jitv.tv.dto.UserDTO;
import com.jitv.tv.service.AdminService;
import com.jitv.tv.service.LoginService;
import com.jitv.tv.service.ResourcesService;
import com.jitv.tv.util.ErrorCode;
import com.jitv.tv.util.Md5Utils;
import com.jitv.tv.util.RedisUtil;
import com.jitv.tv.util.StaticClass;
import com.jitv.tv.util.WebUtil;

public class LoginServiceImpl implements LoginService {

	private UserDao userDao;
	private ErrorLoginDao errorLoginDao;
	private static int TOKEN_TIME = 60 * 60 * 24 * 7;

	public void setErrorLoginDao(ErrorLoginDao errorLoginDao) {
		this.errorLoginDao = errorLoginDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	private AdminService adminService;

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	private ResourcesService resourcesService;

	public void setResourcesService(ResourcesService resourcesService) {
		this.resourcesService = resourcesService;
	}

	@Override
	public Map<String, Object> login(String loginName, String passWord, String token, String outIP) {// 登陆验证
		// 1. 根据用户名称查询用户表
		UserDTO dto = userDao.selUserById(loginName);
		if (dto == null) {// 用户不存在
			ErrorLoginDTO errorLoginDto = new ErrorLoginDTO();
			errorLoginDto.setTime(new Date());
			errorLoginDto.setPassword(passWord);
			errorLoginDto.setReason("用户不存在");
			errorLoginDto.setUsername(loginName);
			errorLoginDto.setOutip(outIP);
			errorLoginDto.setServiceip(WebUtil.getIpAddress());
			errorLoginDao.addErrorLogin(errorLoginDto);
			throw ErrorCode.E991;
		}
		String dtoPassword = dto.getUserPassword();
		if (!dtoPassword.equals(Md5Utils.getMd5(passWord + "dadsj"))) {// 用户名或密码错误
			ErrorLoginDTO errorLoginDto = new ErrorLoginDTO();
			errorLoginDto.setTime(new Date());
			errorLoginDto.setPassword(passWord);
			errorLoginDto.setReason("用户密码错误");
			errorLoginDto.setServiceip(WebUtil.getIpAddress());
			errorLoginDto.setUsername(loginName);
			errorLoginDto.setOutip(outIP);
			errorLoginDao.addErrorLogin(errorLoginDto);
			throw ErrorCode.E111;
		} else {// 正确组装数据返回
				// 查询当前用户所有的资源
			List<ResourcesDTO> UsersResources = adminService.selResourceByUserId(dto.getUserId());
			Map<String, Object> map = new HashMap<String, Object>();
			Map<String, Object> dtoMap = dto.toDbMap();
			map.put("user", dtoMap);
			if (UsersResources != null) {
				map.put("usersResources", resourcesService.selectTree(UsersResources));
			} else {
				map.put("usersResources", "");
			}

			map.put("token", token);
			RedisUtil.set(StaticClass.R_LOGIN_KEY + token, TOKEN_TIME, map);
			return map;
		}
	}

	@Override
	public Boolean verification(String token) {
		if (!StringUtil.isEmpty(token)) {
			Map<String, Object> loginInfo = (Map) RedisUtil.get(StaticClass.R_LOGIN_KEY + token);
			if (!StringUtil.isEmpty(loginInfo)) {
				if (loginInfo.get("token").equals(token)) {
					RedisUtil.expire(StaticClass.R_LOGIN_KEY + token, TOKEN_TIME);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public Map<String, Object> getLoginUserInfo(String token) {
		if (!StringUtil.isEmpty(token)) {
			Map<String, Object> loginInfo = (Map) RedisUtil.get(StaticClass.R_LOGIN_KEY + token);
			if (!StringUtil.isEmpty(loginInfo)) {
				return (Map<String, Object>) loginInfo.get("user");
			}
		}
		return new HashMap<String, Object>();
	}

	@Override
	public void LoginOut(String token) {// 退出登陆
		RedisUtil.delete(StaticClass.R_LOGIN_KEY + token);
	}

}
