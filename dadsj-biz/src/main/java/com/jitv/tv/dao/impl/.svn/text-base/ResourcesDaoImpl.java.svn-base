package com.jitv.tv.dao.impl;

import java.util.List;
import java.util.Map;
import com.jitv.tv.dao.ResourcesDao;
import com.jitv.tv.dao.base.AbstractDAO;
import com.jitv.tv.dto.ResourcesDTO;

public class ResourcesDaoImpl extends AbstractDAO implements ResourcesDao {

	public ResourcesDaoImpl() {
		super("t_j_resources");
	}

	@Override
	public void add(ResourcesDTO dto) {
		insert("add", dto);
	}

	@Override
	public void delete(String id) {
		delete("delete", newParam("id", id));
	}

	@Override
	public void update(Map<String, Object> map) {
		update("update", map);
	}

	@Override
	public List<ResourcesDTO> selectList() {
		return queryForList("getResourcesList", ResourcesDTO.class);
	}

	@Override
	public List<ResourcesDTO> selectList(List<String> ids) {
		return queryForList("getResourcesListByIds", ResourcesDTO.class, newParam("ids", ids));
	}


}
