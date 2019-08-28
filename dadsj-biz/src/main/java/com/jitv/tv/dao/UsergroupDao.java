package com.jitv.tv.dao;

import java.util.List;

import com.jitv.tv.dto.UsergroupDTO;

public interface UsergroupDao {
	UsergroupDTO selectUserGroup(Integer id, String name);

	List<UsergroupDTO> selectUserGroup();

	UsergroupDTO selectListIsp(Integer id, String name);

	List<UsergroupDTO> selectListIsp();

}
