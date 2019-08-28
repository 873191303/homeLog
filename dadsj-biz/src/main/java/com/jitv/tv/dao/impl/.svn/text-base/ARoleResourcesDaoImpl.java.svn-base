package com.jitv.tv.dao.impl;

import java.util.List;

import com.jitv.tv.dao.ARoleResourcesDao; 
import com.jitv.tv.dao.base.AbstractDAO;
import com.jitv.tv.dto.ARoleResourcesDTO;

public class ARoleResourcesDaoImpl extends AbstractDAO implements ARoleResourcesDao {

	public ARoleResourcesDaoImpl() {
		super("t_j_a_role_resources"); 
	}

	@Override
	public void add(ARoleResourcesDTO dto) {
		insert("add", dto);
		
	}

	@Override
	public void delete(String id) {
		delete("delete", newParam("id", id));
		
	}

	@Override
	public List<ARoleResourcesDTO> selectList(String roleid) {
		return queryForList("getARoleResourcesList", ARoleResourcesDTO.class);
	}

	@Override
	public List<ARoleResourcesDTO> selectListByIds(List<String> ids) {
		return queryForList("getResourcesListByIds", ARoleResourcesDTO.class, newParam("ids", ids));
	}

	@Override
	public void deleteByrole(String roleId) {
		delete("deleteByrole", newParam("roleId", roleId));
		
	}

}
