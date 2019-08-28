package com.jitv.tv.dao.impl;

import java.util.List;

import com.jitv.tv.dao.UsergroupDao;
import com.jitv.tv.dao.base.AbstractDAO;
import com.jitv.tv.dto.UsergroupDTO;

public class UsergroupDaoImpl extends AbstractDAO implements UsergroupDao {

	public UsergroupDaoImpl() {
		super("tm_usergroup");
	}

	@Override
	public UsergroupDTO selectUserGroup(Integer id, String name) {
		return queryForObject("selectusergroup", UsergroupDTO.class, newParam("id", id), newParam("name", name));
	}

	@Override
	public UsergroupDTO selectListIsp(Integer id, String name) {
		return queryForObject("selecisp", UsergroupDTO.class, newParam("id", id), newParam("name", name));
	}

	@Override
	public List<UsergroupDTO> selectUserGroup() {
		return queryForList("selectusergrouplist", UsergroupDTO.class);
	}

	@Override
	public List<UsergroupDTO> selectListIsp() {
		return queryForList("selecisplist", UsergroupDTO.class);
	}

}
