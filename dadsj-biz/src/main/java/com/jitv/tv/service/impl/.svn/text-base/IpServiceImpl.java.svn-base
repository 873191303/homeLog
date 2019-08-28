package com.jitv.tv.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.jitv.tv.dao.DictionariesDao;
import com.jitv.tv.dao.IpDao;
import com.jitv.tv.dto.DictionariesDTO;
import com.jitv.tv.dto.IpDTO;
import com.jitv.tv.service.IpService;
import com.jitv.tv.util.RedisUtil;

public class IpServiceImpl implements IpService {

	private final static Logger logger = LoggerFactory.getLogger(IpServiceImpl.class);

	private IpDao ipDao;

	private DictionariesDao dictionariesDao;

	public void setDictionariesDao(DictionariesDao dictionariesDao) {
		this.dictionariesDao = dictionariesDao;
	}

	public void setIpDao(IpDao ipDao) {
		this.ipDao = ipDao;
	}

	@Override
	public int add(String ip, String city, String other, String COL) {
		IpDTO dto = new IpDTO();
		dto.setIp(ip);
		dto.setCity(city);
		dto.setCreateTime(new Date());
		dto.setUpdateTime(new Date());
		return ipDao.addIp(dto);
	}

	@Override
	public int del(String id) {
		return ipDao.deleteIp(id);
	}

	@Override
	public int updateuser(String id, String ip, String city, String createTime, String updateTime, String other,
			String COL) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", Long.parseLong(id));
		map.put("ip", ip);
		map.put("city", city);
		map.put("updateTime", new Date());
		return ipDao.update(map);

	}

	@Override
	public List<Map<String, Object>> sel(String startIndex, String pageSize, String ip, String city) {
		if (StringUtils.isNotEmpty(city)) {
			DictionariesDTO dto = dictionariesDao.selBytypeValue(city);
			if (dto != null) {
				String typeKey = dto.getTypeKey();
				city = typeKey;
			}
		}
		List<IpDTO> dtoList = ipDao.selectList(startIndex, pageSize, ip, city);
		List<Map<String, Object>> resultList = new LinkedList<Map<String, Object>>();
		if (dtoList != null) {
			for (IpDTO list : dtoList) {
				Map<String, Object> map = list.toDbMap();
				String cityKey = (String) map.get("city");
				String cityValue = RedisUtil.get(cityKey) == null ? "" : (String) RedisUtil.get(cityKey);
				if (StringUtils.isEmpty(cityValue)) {
					// 在这里存入缓存
					DictionariesDTO dto = dictionariesDao.selBytypeKey(cityKey);
					RedisUtil.set(dto.getTypeKey(), -1, dto.getTypeValue());
					map.put("cityValue", dto.getTypeValue());
				} else {
					map.put("cityValue", cityValue);
				}
				resultList.add(map);
			}
		}
		return resultList;
	}

	@Override
	public int getSum(String ip, String city) {
		if (StringUtils.isNotEmpty(city)) {
			DictionariesDTO dto = dictionariesDao.selBytypeValue(city);
			if (dto != null) {
				String typeKey = dto.getTypeKey();
				city = typeKey;
			}
		}
		return ipDao.getSum(ip, city);
	}

	@Override
	public Map<String, Object> selById(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(id)) {
			IpDTO dto = ipDao.selIpById(Long.parseLong(id));
			map = dto.toDbMap();
		}
		return map;

	}

	@Override
	public List<Map<String, Object>> sel(String city) {
		if (StringUtils.isNotEmpty(city)) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(10);
			List<IpDTO> dto = ipDao.selectList(city);
			for (int i = 0; i < dto.size(); i++) {
				Map<String, Object> map = dto.get(i).toDbMap();
				list.add(map);
			}
			return list;
		}
		return null;
	}

}
