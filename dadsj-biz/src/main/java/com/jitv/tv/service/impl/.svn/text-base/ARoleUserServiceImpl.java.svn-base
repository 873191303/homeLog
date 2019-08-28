package com.jitv.tv.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.jitv.tv.dao.ARoleUserDao;
import com.jitv.tv.dto.ARoleResourcesDTO;
import com.jitv.tv.dto.ARoleUserDTO;
import com.jitv.tv.service.ARoleUserService;
import com.jitv.tv.util.JitvGuidUtil;

public class ARoleUserServiceImpl implements ARoleUserService {
	private ARoleUserDao aroleUserDao;

	public void setAroleUserDao(ARoleUserDao aroleUserDao) {
		this.aroleUserDao = aroleUserDao;
	}

	@Override
	public void add(String userId, String roleId) {
		ARoleUserDTO dto = new ARoleUserDTO();
		dto.setCreateTime(new Date());
		dto.setUpdateTime(new Date());
		dto.setUserId(userId);
		dto.setRoleId(roleId);
		dto.setId(JitvGuidUtil.getGuid());
		aroleUserDao.add(dto);

	}

	@Override
	public void delete(String id) {
		aroleUserDao.delete(id);

	}

	@Override
	public List<ARoleUserDTO> selectList(String userId, String roleId) {
		return aroleUserDao.selectList(userId, roleId);
	}

	@Override
	public void updateArole(String userId, String roleId) {
		// 更新用户对应的角色
		// 1.删除旧资源
		aroleUserDao.deleteByUserId(userId);
		// 2.插入新的资源
		if (StringUtils.isNotEmpty(roleId)) {
			roleId = roleId.substring(0, roleId.length()-1);
			List<String> list = new ArrayList<String>();
			if (roleId.contains(",")) {
				String[] arr = roleId.split(",");
				list = Arrays.asList(arr);
			} else {
				list.add(roleId);
			}
			for (int i = 0; i < list.size(); i++) {
				ARoleUserDTO dto = new ARoleUserDTO();
				dto.setUserId(userId);
				dto.setRoleId(list.get(i));
				dto.setId(JitvGuidUtil.getGuid());
				dto.setCreateTime(new Date());
				dto.setUpdateTime(new Date());
				aroleUserDao.add(dto);
			}
		}

	}
	
	@Override
	public void deleteByRoleId(String roleId) {
		aroleUserDao.deleteByRoleId(roleId);
		
	}

}
