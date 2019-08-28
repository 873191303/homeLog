package com.jitv.tv.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import com.jitv.tv.dao.BehaviorDao;
import com.jitv.tv.dto.BehaviorDto;
import com.jitv.tv.service.BehaviorService;

public class BehaviorServiceImpl implements BehaviorService {

	private BehaviorDao behaviorDao;

	public void setBehaviorDao(BehaviorDao behaviorDao) {
		this.behaviorDao = behaviorDao;
	}

	@Override
	public int add(String serviceIP, String userName, String type, String other, String spare) {
		BehaviorDto dto = new BehaviorDto();
		dto.setServiceIP(serviceIP);
		dto.setUserName(userName);
		dto.setType(type);
		dto.setCreateTime(new Date());
		dto.setOther(other);
		dto.setSpare(spare);
		int count = behaviorDao.addUser(dto);
		return count;
	}

	@Override
	public List<Map<String, Object>> select(String startIndex, String pageSize, String serviceIP, String userName) {
		List<BehaviorDto> dtos = behaviorDao.selectList(startIndex, pageSize, userName, serviceIP);
		List<Map<String, Object>> result = new LinkedList<Map<String, Object>>();
		for (BehaviorDto list : dtos) {
			Map<String, Object> map = list.toDbMap();
			result.add(map);
		}
		return result;
	}

	@Override
	public int getSum(String userName, String ip) {
		return behaviorDao.getSum(userName, ip);
	}

}
