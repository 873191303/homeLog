package com.jitv.tv.service;

import java.util.List;
import java.util.Map;

import com.jitv.tv.dao.RoleDao;
import com.jitv.tv.dto.ResourcesDTO;
import com.jitv.tv.dto.RoleDTO;
import com.jitv.tv.dto.UserDTO;

public interface AdminService {

	int adduser(String userId, String userPassword, String city, String mobile, String email, String createTime,
			String updateTime, String creator);

	int delUser(String userId);

	int updateuser(String userId, String userPassword, String city, String mobile, String email, String createTime,
			String updateTime);
	int updat(String userId, String userPassword, String city, String mobile, String email, String createTime,
			String updateTime);

	List<Map<String, Object>> seluser(String startIndex, String pageSize, String userId, String city);

	int getSum(String userId, String city);

	Map<String, Object> selUserById(String userId);

	String setUserPwd(String userId, String newPassword, String password);

	List<Map<String, Object>> selectList(String ids);

	List<Map<String, Object>> selectList();

	public Map<String, Object> selectRoleHaveUsers(String roleId);

	// 根据用户id 查询所有的资源
	List<ResourcesDTO> selResourceByUserId(String userId);

	// 根据用户id 查询所拥有的角色信息
	List<Map<String, Object>> selRole(String id);

	// 系统资源告警日志
	Map<String, Object> AlarmLog(String startIndex, String pageSize, String searchValue);

	// 运行日志
	Map<String, Object> FunctionLog(String startIndex, String pageSize, String searchValue);

}
