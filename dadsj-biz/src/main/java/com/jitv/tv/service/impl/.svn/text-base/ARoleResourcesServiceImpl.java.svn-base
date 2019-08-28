package com.jitv.tv.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.jitv.tv.dao.ARoleResourcesDao;
import com.jitv.tv.dao.ResourcesDao;
import com.jitv.tv.dto.ARoleResourcesDTO;
import com.jitv.tv.dto.ResourcesDTO;
import com.jitv.tv.service.ARoleResourcesService;
import com.jitv.tv.util.JitvGuidUtil;

public class ARoleResourcesServiceImpl implements ARoleResourcesService {
	private ARoleResourcesDao aroleResourcesDao;

	public void setAroleResourcesDao(ARoleResourcesDao aroleResourcesDao) {
		this.aroleResourcesDao = aroleResourcesDao;
	}

	// 注入资源dao
	private ResourcesDao resourcesDao;

	public void setResourcesDao(ResourcesDao resourcesDao) {
		this.resourcesDao = resourcesDao;
	}

	@Override
	public void add(String resourcesId, String roleId) {
		ARoleResourcesDTO dto = new ARoleResourcesDTO();
		dto.setCreateTime(new Date());
		dto.setUpdateTime(new Date());
		dto.setResourcesId(resourcesId);
		dto.setRoleId(roleId);
		dto.setId(JitvGuidUtil.getGuid());
		aroleResourcesDao.add(dto);
	}

	@Override
	public void delete(String id) {
		aroleResourcesDao.delete(id);
	}

	@Override
	public List<ARoleResourcesDTO> selectList(String roleid) {
		return aroleResourcesDao.selectList(roleid);
	}

	@Override
	public List<Map<String, Object>> selectListByIds(List<String> ids) {
		// 1. 查询角色和资源对应信息
		List<ARoleResourcesDTO> list = aroleResourcesDao.selectListByIds(ids);
		if (list.size() > 0) {
			List<String> resourcesIds = new LinkedList<String>();// 资源id
			for (ARoleResourcesDTO dto : list) {
				String resourcesId = dto.getResourcesId();
				resourcesIds.add(resourcesId);
			}
			// 2.根据资源id查询查询资源表
			List<ResourcesDTO> resList = resourcesDao.selectList(resourcesIds);
			List<Map<String, Object>> Result = new LinkedList<Map<String, Object>>();
			for (ResourcesDTO dto : resList) {
				Map<String, Object> map = ResourcesServiceImpl.getUiMap(dto.getResourcesName(),
						dto.getResourcesModular(), dto.getId(), dto.getResourcePath(), dto.getParent(), 1,
						dto.getSequenceNumber() + "", dto.getCreateTime(), dto.getUpdateTime());
				Result.add(map);
			}
			return Result;
		}
		return null;
	}

	@Override
	public void updateByRoleid(String roleid, String resourcesIds) {// 更新角色对用的资源
		// 1.删除该角色之前关联的所有资源
		aroleResourcesDao.deleteByrole(roleid);
		// 2.新增该角色和传递过来的资源id
		if (StringUtils.isNotEmpty(resourcesIds)) {
			List<String> resIdList = new LinkedList<String>();
			if (resourcesIds.contains(",")) {
				String[] arr = resourcesIds.split(",");
				resIdList = Arrays.asList(arr);
			}else {
				resIdList.add(resourcesIds);
			}
			for(int i = 0;i< resIdList.size();i++) {
				ARoleResourcesDTO dto = new ARoleResourcesDTO();
				dto.setCreateTime(new Date());
				dto.setId(JitvGuidUtil.getGuid());
				dto.setRoleId(roleid);
				dto.setResourcesId(resIdList.get(i));
				dto.setUpdateTime(new Date());
				aroleResourcesDao.add(dto);
			}
		}
	}

}
