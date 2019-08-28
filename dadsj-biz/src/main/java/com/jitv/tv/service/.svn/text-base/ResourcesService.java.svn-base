package com.jitv.tv.service;

import java.util.List;
import java.util.Map;

import com.jitv.tv.dto.ResourcesDTO;

public interface ResourcesService {
	void add(String authorizationType, String parent, String resourcePath, String resourcesModular,
			String resourcesName, int sequenceNumber, String systemType);

	void delete(String id);

	void update(Map<String, Object> map);

	List<Map<String, Object>> selectList();

	public List<Map<String, Object>> selectTree();

	public List<Map<String, Object>> selectTree(List<ResourcesDTO> dtos);
}
