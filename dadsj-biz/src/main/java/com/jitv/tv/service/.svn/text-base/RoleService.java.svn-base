package com.jitv.tv.service;

import java.util.List;
import java.util.Map;

import com.jitv.tv.dto.RoleDTO;

public interface RoleService {
	void add(String name,String describe,String createUser);
	void delete(String id);
	void update(String id,String name,String describe);
	List<Map<String, Object>> selectList(String name,int pageIndex, int pageSum);
	
	List<Map<String, Object>> selRole(String id);
	int listSum(String name);
	Map<String,Object> get(String id);
	public Map<String, Object> selectUserHaveRoles(String userId, int pageIndex, int pageSum);
	public int updateRoleToUsers(String roleId,String[] userIds);
}
