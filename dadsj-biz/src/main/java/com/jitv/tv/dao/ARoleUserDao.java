package com.jitv.tv.dao;

import java.util.List;

import com.jitv.tv.dto.ARoleUserDTO;

public interface ARoleUserDao {
	void add(ARoleUserDTO dto);

	void delete(String id);
	//根据用户id删除
	void deleteByUserId(String userId);
	//根据角色id删除
	void deleteByRoleId(String roleId);

	List<ARoleUserDTO> selectList(String userId, String roleId);
}
