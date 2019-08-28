package com.jitv.tv.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.jitv.tv.dao.IpTopnDao;
import com.jitv.tv.dto.IpTopnDTO;
import com.jitv.tv.service.UserBusinessService;

public class UserBusinessServiceImpl implements UserBusinessService {

	private final static Logger logger = LoggerFactory.getLogger(UserBusinessServiceImpl.class);

	private IpTopnDao ipTopnDao;

	public void setIpTopnDao(IpTopnDao ipTopnDao) {
		this.ipTopnDao = ipTopnDao;
	}

	@Override
	public Map<String, Object> UserFwjk(String startIndex, String pageSize, String searchValue) {
		List<IpTopnDTO> listDto = ipTopnDao.selectList(startIndex, pageSize, null,searchValue);
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>(10);
		for (IpTopnDTO dto : listDto) {
			Map<String, Object> map = dto.toDbMap();
			resultList.add(map);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Result", resultList);
		map.put("totalItems", ipTopnDao.getSum(null,searchValue));
		return map;
	}

	@Override
	public Map<String, Object> UserFlow(String startIndex, String pageSize, String searchValue) {
		List<IpTopnDTO> listDto = ipTopnDao.selectList(startIndex, pageSize, null,searchValue);
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>(10);
		for (IpTopnDTO dto : listDto) {
			Map<String, Object> map = dto.toDbMap();
			resultList.add(map);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Result", resultList);
		map.put("totalItems", ipTopnDao.getSum(null,searchValue));
		return map;
	}

}
