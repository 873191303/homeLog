package com.jitv.tv.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jitv.tv.dao.ARoleUserDao;
import com.jitv.tv.dao.RoleDao;
import com.jitv.tv.dto.ARoleUserDTO;
import com.jitv.tv.dto.RoleDTO;
import com.jitv.tv.service.ARoleUserService;
import com.jitv.tv.service.RoleService;
import com.jitv.tv.util.JitvGuidUtil;

public class RoleServiceImpl implements RoleService{

	private RoleDao roleDao; 
	private ARoleUserService  aroleUserService;

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	 
 



	public void setAroleUserService(ARoleUserService aroleUserService) {
		this.aroleUserService = aroleUserService;
	}






	@Override
	public void add(String name,String describe,String createUser) {
		RoleDTO dto = new RoleDTO();
		dto.setCreateTime(new Date());
		dto.setUpdateTime(new Date()); 
		dto.setId(JitvGuidUtil.getGuid());
		dto.setName(name);
		dto.setDescribe(describe);
		dto.setCreateUser(createUser);
		dto.setType(0);
		roleDao.add(dto);
		
	}

	@Override
	public void delete(String id) {
		roleDao.delete(id);
		
	}

	@Override
	public void update(String id,String name,String describe) { 
		RoleDTO roleDTO = new RoleDTO(); 
		roleDTO.setId(id);
		roleDTO.setName(name);
		roleDTO.setDescribe(describe);
		roleDTO.setUpdateTime(new Date());
		roleDao.update(roleDTO);
		
	}

	@Override
	public List<Map<String, Object>> selectList(String name, int pageIndex, int pageSum) { 
		List<RoleDTO> dtos = roleDao.selectList(name, pageIndex, pageSum);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for(RoleDTO dto : dtos) {
			list.add(dto.toDbMap());
		}
		return list;
	}

	@Override
	public int listSum(String name) { 
		return roleDao.listSum(name);
	}

	@Override
	public Map<String, Object> get(String id) {
		return roleDao.get(id).toDbMap();
	}

	@Override
	public Map<String, Object> selectUserHaveRoles(String userId, int pageIndex, int pageSum) {
		Map<String, Object> backMap = new HashMap<String, Object>();
		int sum = listSum("");
		//获得角色
		List<Map<String, Object>> roleList = selectList("",  pageIndex,  pageSum);
		//获得角色的所有已经配置的用户 获得用户所有已经配置的角色
		List<ARoleUserDTO> aroleUserDTOList =  aroleUserService.selectList(userId, "");
		Map<String, Object> aroleUserMap = new HashMap<String, Object>();
		for(ARoleUserDTO dto : aroleUserDTOList) {
			aroleUserMap.put(dto.getRoleId(),"");
		}
		for(int i=0;i<roleList.size();i++) {
			if(aroleUserMap.containsKey(roleList.get(i).get("id"))) {
				roleList.get(i).put("isHave", "true");
			}else {
				roleList.get(i).put("isHave", "");
			}
		}
		backMap.put("sum", sum+"");
		backMap.put("roleList", roleList);
		return backMap;
	}
	@Override
	public int updateRoleToUsers(String roleId,String[] userIds) {
		int back = 0;
		//先删除
		aroleUserService.deleteByRoleId(roleId);
		for(int i=0;i<userIds.length;i++) {
			aroleUserService.add(userIds[i], roleId);
			back++;
		}
		return back;
	}






	@Override
	public List<Map<String, Object>> selRole(String id) {
		// TODO Auto-generated method stub
		return null;
	}
}
