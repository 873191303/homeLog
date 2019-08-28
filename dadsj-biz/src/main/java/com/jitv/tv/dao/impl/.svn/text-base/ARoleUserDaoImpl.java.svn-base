package com.jitv.tv.dao.impl;

import java.util.List;

import com.jitv.tv.dao.ARoleUserDao; 
import com.jitv.tv.dao.base.AbstractDAO;
import com.jitv.tv.dto.ARoleUserDTO;
import com.jitv.tv.dto.ResourcesDTO;

public class ARoleUserDaoImpl extends AbstractDAO implements ARoleUserDao{

	public ARoleUserDaoImpl() {
		super("t_j_a_role_user"); 
	}

	@Override
	public void add(ARoleUserDTO dto) {
		insert("add", dto);
		
	}

	@Override
	public void delete(String id) {
		delete("delete", newParam("id", id));
		
	}

	@Override
	public List<ARoleUserDTO> selectList(String userId,String roleId) { 
		return queryForList("getARoleUserList", ARoleUserDTO.class, newParam("userId", userId), newParam("roleId", roleId));
	}

	@Override
	public void deleteByUserId(String userId) {//根据用户Id删除
		delete("deletebyuserid", newParam("userId", userId));
		
	}

	@Override
	public void deleteByRoleId(String roleId) {//根据角色资源删除
		delete("deletebyroleId", newParam("roleId", roleId));
		
	}

}
