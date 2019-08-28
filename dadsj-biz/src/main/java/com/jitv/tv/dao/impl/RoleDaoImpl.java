package com.jitv.tv.dao.impl;

import java.util.List;
import java.util.Map;

import com.jitv.tv.dao.RoleDao;
import com.jitv.tv.dao.base.AbstractDAO; 
import com.jitv.tv.dto.RoleDTO; 

public class RoleDaoImpl  extends AbstractDAO implements RoleDao{

	public RoleDaoImpl() {
		super("t_j_role"); 
	}

	@Override
	public void add(RoleDTO dto) { 
		insert("add", dto);
	}

	@Override
	public void delete(String id) { 
		delete("delete", newParam("id", id));
	}

	@Override
	public void update(RoleDTO dto) { 
		update("update", dto.toDbMap());
	}

	@Override
	public List<RoleDTO> selectList(String name, int pageIndex, int pageSum) { 
		return queryForList("getRoleList", RoleDTO.class, newParam("name", name),newParam("pageIndex", pageIndex), 
				newParam("pageSum", pageSum));
	}

	@Override
	public int listSum(String name) { 
		return (int) function("listSum", newParam("name", name));
	}

	@Override
	public RoleDTO get(String id) { 
		return queryForObject("getRole", RoleDTO.class, newParam("id", id));
	}

	@Override
	public List<RoleDTO> selectList(List<String> ids) {
		return queryForList("getRoleLists", RoleDTO.class, newParam("ids", ids));
	}

	 

}
