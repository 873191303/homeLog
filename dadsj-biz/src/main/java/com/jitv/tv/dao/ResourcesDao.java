package com.jitv.tv.dao;

import java.util.List;
import java.util.Map;

import com.jitv.tv.dto.ResourcesDTO;

public interface ResourcesDao {
	void add(ResourcesDTO dto);
	void delete(String id);
	void update(Map<String,Object> map);
	List<ResourcesDTO> selectList();
	List<ResourcesDTO> selectList(List<String> ids);
}
