package com.jitv.tv.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jitv.tv.dao.ARoleResourcesDao;
import com.jitv.tv.dao.ARoleUserDao;
import com.jitv.tv.dao.DictionariesDao;
import com.jitv.tv.dao.ResourcesDao;
import com.jitv.tv.dao.RoleDao;
import com.jitv.tv.dao.UserDao;
import com.jitv.tv.dto.ARoleResourcesDTO;
import com.jitv.tv.dto.ARoleUserDTO;
import com.jitv.tv.dto.DictionariesDTO;
import com.jitv.tv.dto.ResourcesDTO;
import com.jitv.tv.dto.RoleDTO;
import com.jitv.tv.dto.UserDTO;
import com.jitv.tv.service.ARoleUserService;
import com.jitv.tv.service.AdminService;
import com.jitv.tv.util.DateUtil;
import com.jitv.tv.util.ErrorCode;
import com.jitv.tv.util.LinuxHardware;
import com.jitv.tv.util.Md5Utils;
import com.jitv.tv.util.RedisUtil;

public class AdminServiceImpl implements AdminService {
	private final static Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

	private ARoleUserService aroleUserService;

	public void setAroleUserService(ARoleUserService aroleUserService) {
		this.aroleUserService = aroleUserService;
	}

	private DictionariesDao dictionariesDao;

	public void setDictionariesDao(DictionariesDao dictionariesDao) {
		this.dictionariesDao = dictionariesDao;
	}

	// 注入角色
	private RoleDao roleDao;

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	// 注入用户角色关联
	private ARoleUserDao aroleUserDao;

	public void setAroleUserDao(ARoleUserDao aroleUserDao) {
		this.aroleUserDao = aroleUserDao;
	}

	// 注入角色资源关联
	private ARoleResourcesDao aroleResourcesDao;

	public void setAroleResourcesDao(ARoleResourcesDao aroleResourcesDao) {
		this.aroleResourcesDao = aroleResourcesDao;
	}

	// 注入资源dao
	private ResourcesDao resourcesDao;

	public void setResourcesDao(ResourcesDao resourcesDao) {
		this.resourcesDao = resourcesDao;
	}

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public int adduser(String userId, String userPassword, String city, String mobile, String email, String createTime,
			String updateTime, String creator) {
		UserDTO dto = new UserDTO();
		dto.setUserId(userId);
		String encryptionPassword = Md5Utils.getMd5(userPassword + "dadsj");
		dto.setUserPassword(encryptionPassword);
		dto.setCity(city);
		dto.setCreator(creator);
		if (StringUtils.isNotEmpty(mobile)) {
			dto.setMobile(Long.parseLong(mobile));
		}
		dto.setEmail(email);
		dto.setCreateTime(new Date());
		dto.setUpdateTime(new Date());
		return userDao.addUser(dto);
	}

	@Override
	public int delUser(String userId) {
		return userDao.deleteUser(userId);
	}

	@Override
	public int updateuser(String userId, String userPassword, String city, String mobile, String email,
			String createTime, String updateTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("city", city);
		if (StringUtils.isNotEmpty(userPassword)) {
			Map<String, Object> dtoMap = selUserById(userId);
			String oldpwd = (String) dtoMap.get("userPassword");
			if (!oldpwd.contentEquals(userPassword)) {
				map.put("userPassword", Md5Utils.getMd5(userPassword + "dadsj"));
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>修改了密码");
			}
		}
		if (StringUtils.isNotEmpty(city)) {
			map.put("city", city);

		}
		if (StringUtils.isNotEmpty(mobile)) {
			map.put("mobile", Long.parseLong(mobile));
		}
		if (StringUtils.isNotEmpty(email)) {
			map.put("email", email);
		}
		map.put("updateTime", new Date());
		return userDao.update(map);
	}

	@Override
	public int updat(String userId, String userPassword, String city, String mobile, String email, String createTime,
			String updateTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("city", city);
		if (StringUtils.isNotEmpty(userPassword)) {
			Map<String, Object> dtoMap = selUserById(userId);
			String oldpwd = (String) dtoMap.get("userPassword");
			if (!oldpwd.contentEquals(userPassword)) {
				map.put("userPassword", Md5Utils.getMd5(userPassword + "dadsj"));
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>修改了密码");
			}
		}
		if (StringUtils.isNotEmpty(city)) {
			DictionariesDTO dto = dictionariesDao.selBytypeValue(city);
			if (dto != null) {
				String typeKey = dto.getTypeKey();
				map.put("city", typeKey);
			} else {
				throw ErrorCode.E444;
			}

		}
		if (StringUtils.isNotEmpty(mobile)) {
			map.put("mobile", Long.parseLong(mobile));
		}
		if (StringUtils.isNotEmpty(email)) {
			map.put("email", email);
		}
		map.put("updateTime", new Date());
		return userDao.update(map);
	}

	@Override
	public List<Map<String, Object>> seluser(String startIndex, String pageSize, String userId, String city) {
		List<UserDTO> list = userDao.selectList(startIndex, pageSize, userId, city);
		List<Map<String, Object>> result = new LinkedList<Map<String, Object>>();
		if (list.size() > 0) {
			for (UserDTO dto : list) {
				Map<String, Object> map = dto.toDbMap();
				String cityKey = (String) map.get("city");
				String cityValue = RedisUtil.get(cityKey) == null ? "" : (String) RedisUtil.get(cityKey);
				if (StringUtils.isEmpty(cityValue)) {
					// 在这里存入缓存
					DictionariesDTO dictionaries = dictionariesDao.selBytypeKey(cityKey);
					RedisUtil.set(dictionaries.getTypeKey(), -1, dictionaries.getTypeValue());
					map.put("cityValue", dictionaries.getTypeValue());
				} else {
					map.put("cityValue", cityValue);
				}
				result.add(map);
			}
		}
		return result;
	}

	@Override
	public int getSum(String userId, String city) {
		if (StringUtils.isNotEmpty(city)) {
			DictionariesDTO dto = dictionariesDao.selBytypeValue(city);
			if (dto != null) {
				String typeKey = dto.getTypeKey();
				city = typeKey;
			}
		}
		return userDao.getSum(userId, city);
	}

	@Override
	public Map<String, Object> selUserById(String userId) {
		UserDTO dto = userDao.selUserById(userId);
		Map<String, Object> map = dto.toDbMap();
		String cityKey = (String) map.get("city");
		String cityValue = RedisUtil.get(cityKey) == null ? "" : (String) RedisUtil.get(cityKey);
		if (StringUtils.isEmpty(cityValue)) {
			// 在这里存入缓存
			DictionariesDTO dictionaries = dictionariesDao.selBytypeKey(cityKey);
			RedisUtil.set(dictionaries.getTypeKey(), -1, dictionaries.getTypeValue());
			map.put("cityValue", dictionaries.getTypeValue());
		} else {
			map.put("cityValue", cityValue);
		}
		return map;
	}

	@Override
	public String setUserPwd(String userId, String newPassword, String password) {
		if (StringUtils.isNotEmpty(userId)) {
			UserDTO dto = userDao.selUserById(userId);
			String oldpwd = dto.getUserPassword();
			if (oldpwd.equals(Md5Utils.getMd5(password + "dadsj"))) {
				logger.info(">>>>>>>>>>>>密码验证通过");
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userId", userId);
				map.put("userPassword", Md5Utils.getMd5(newPassword + "dadsj"));
				map.put("updateTime", new Date());
				userDao.update(map);
				return "" + userDao.update(map);
			} else {
				logger.info(">>>>>>>>>>>>密码验证不通过！！！");
				return "error";
			}
		}
		return "UserId Null";
	}

	@Override
	public List<Map<String, Object>> selectList(String ids) {
		if (StringUtils.isNotEmpty(ids)) {
			List<String> listId = new LinkedList<String>();
			if (ids.contains(",")) {// 把字符串id 转换成list
				String[] arr = ids.split(",");
				listId = Arrays.asList(arr);
			} else {
				listId.add(ids);
			}
			List<UserDTO> list = userDao.selectList(listId);
			List<Map<String, Object>> DtoList = new LinkedList<Map<String, Object>>();
			for (UserDTO dto : list) {
				DtoList.add(dto.toDbMap());
			}
			return DtoList;
		}
		return null;

	}

	@Override
	public Map<String, Object> selectRoleHaveUsers(String roleId) {
		Map<String, Object> backMap = new HashMap<String, Object>();

		// 获得角色
		List<Map<String, Object>> userList = selectList();

		// 获得角色的所有已经配置的用户 获得用户所有已经配置的角色
		List<ARoleUserDTO> aroleUserDTOList = aroleUserService.selectList("", roleId);
		Map<String, Object> aroleUserMap = new HashMap<String, Object>();
		for (ARoleUserDTO dto : aroleUserDTOList) {
			aroleUserMap.put(dto.getUserId(), "");
		}
		for (int i = 0; i < userList.size(); i++) {
			if (aroleUserMap.containsKey(userList.get(i).get("userId"))) {
				userList.get(i).put("isHave", "true");
			} else {
				userList.get(i).put("isHave", "");
			}
		}
		/*
		 * // 查询所有地市 Map<String, Object> cityMap = new HashMap<String, Object>();
		 * 
		 * 
		 * for(int i =0 ; i <userList2.size();i++) { String city =
		 * userList2.get(i).get("city").toString(); if(backMap.containsKey(city)) {
		 * ((List)((Map)backMap.get(city)).get("users")).add(userList2.get(i)); }else {
		 * Map<String, Object> cityMap2 = new HashMap<String, Object>();
		 * List<Map<String, Object>> userList3 = new ArrayList<Map<String, Object>>();
		 * userList3.add(userList2.get(i)); cityMap2.put("users", userList3);
		 * cityMap2.put("city", city); backMap.put(city, cityMap2); } }
		 */
		backMap.put("users", userList);
		return backMap;
	}

	@Override
	public List<Map<String, Object>> selectList() {
		List<UserDTO> list = userDao.selectList();
		List<Map<String, Object>> resultList = new LinkedList<Map<String, Object>>();
		for (UserDTO dto : list) {
			Map<String, Object> map = dto.toDbMap();
			resultList.add(map);
		}
		return resultList;
	}

	@Override
	public List<ResourcesDTO> selResourceByUserId(String userId) {
		// 1.查询用户和角色对应表查询出所有关联的角色ID
		List<ARoleUserDTO> list = aroleUserDao.selectList(userId, "");
		if (list.size() > 0) {
			List<String> ListIds = new LinkedList<String>();// 当前用户所对用的所有角色ID
			for (ARoleUserDTO dto : list) {
				String roleId = dto.getRoleId();
				ListIds.add(roleId);
			}
			// 2.根据角色id 查询角色和资源对应信息
			List<ARoleResourcesDTO> Resources = aroleResourcesDao.selectListByIds(ListIds);
			// 3.根据资源id查询资源表
			List<String> resourceIds = new LinkedList<String>();// 资源id
			for (ARoleResourcesDTO resDto : Resources) {
				String resourcesId = resDto.getResourcesId();
				resourceIds.add(resourcesId);

			}
			// 4.根据资源id查询所有的资源
//			List<ResourcesDTO> resourceList =resourcesDao.selectList(resourceIds);
//			List<Map<String, Object>> result = new LinkedList<Map<String,Object>>();
//			for(ResourcesDTO resDto : resourceList) {
//				Map<String, Object> map = new HashMap<String, Object>();
//				map = resDto.toDbMap();
//				result.add(map);
//			}
			return resourcesDao.selectList(resourceIds);
		}

		return null;
	}

	@Override
	public Map<String, Object> AlarmLog(String startIndex, String pageSize, String searchValue) {// 系统资源告警日志
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(2);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("project", "门户网");// 项目
		map.put("reason", "运行正常");// 告警原因
		map.put("time", DateUtil.parseString(new Date(), "yyyy-MM-dd HH:mm:ss"));// 时间
		list.add(map);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("project", "服务器");// 项目
		map2.put("reason", "运行正常");// 告警原因
		map2.put("time", DateUtil.parseString(new Date(), "yyyy-MM-dd HH:mm:ss"));// 时间
		list.add(map2);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("Result", list);
		result.put("totalItems", list.size());
		return result;
	}

	@Override
	public Map<String, Object> FunctionLog(String startIndex, String pageSize, String searchValue) {// 运行日志
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(2);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("server", "服务器");// 服务器名
			logger.info("cpu使用率"+LinuxHardware.getcpuUsage()+"%");
			map.put("cpu", LinuxHardware.getcpuUsage()+"%");// cpu使用率
			
			logger.info("内存使用率"+LinuxHardware.memoryUsage()+"%");
			map.put("memory", LinuxHardware.memoryUsage()+"%");// 内存使用率
			
			logger.info("硬盘使用率"+LinuxHardware.getDeskUsage()+"%");
			map.put("hd", LinuxHardware.getDeskUsage());// 硬盘使用率
			
			logger.info("分区使用率"+LinuxHardware.getPatitionUsage("/home")+"%");
			map.put("partition", LinuxHardware.getPatitionUsage("/home")+"%");// 分区使用率
			
			logger.info("网络带宽"+LinuxHardware.get()+"%");
			map.put("network", LinuxHardware.get()+"%");// 网络带宽
			list.add(map);
		} catch (Exception e) {
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("server", "服务器");// 服务器名
			map2.put("cpu", "读取失败");// cpu使用率
			map2.put("memory", "读取失败");// 内存使用率
			map2.put("hd", "读取失败");// 硬盘使用率
			map2.put("partition", "读取失败");// 分区使用率
			map2.put("network", "读取失败");// 网络带宽
			list.add(map2);
		}

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("Result", list);
		result.put("totalItems", list.size());
		return result;
	}

	@Override
	public List<Map<String, Object>> selRole(String id) {
		List<Map<String, Object>> result = new LinkedList<Map<String, Object>>();
		// 1.查询用户和角色对应表查询出所有关联的角色ID
		List<ARoleUserDTO> list = aroleUserDao.selectList(id, "");
		if (list.size() > 0) {
			List<String> ListIds = new LinkedList<String>();// 当前用户所对用的所有角色ID
			for (ARoleUserDTO dto : list) {
				String roleId = dto.getRoleId();
				ListIds.add(roleId);
			}
			List<RoleDTO> dto = roleDao.selectList(ListIds);
			for (RoleDTO map : dto) {
				Map<String, Object> toMap = map.toDbMap();
				result.add(toMap);
			}
		}

		return result;
	}

}
