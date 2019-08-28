package com.jitv.tv.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.jitv.tv.dao.DictionariesDao;
import com.jitv.tv.dto.DictionariesDTO;
import com.jitv.tv.service.DictionariesService;

public class DictionariesServiceImpl implements DictionariesService {

	private DictionariesDao dictionariesDao;

	public void setDictionariesDao(DictionariesDao dictionariesDao) {
		this.dictionariesDao = dictionariesDao;
	}

	private final static Logger logger = LoggerFactory.getLogger(DictionariesServiceImpl.class);

	@Override
	public int add(int type, String key, String value, String parent, String other, String COL) {
		DictionariesDTO dto = new DictionariesDTO();
		dto.setType(type);
		dto.setTypeKey(key);
		dto.setTypeValue(value);
		dto.setParent(parent);
		dto.setOther(other);
		dto.setCOL(COL);
		dto.setCreateTime(new Date());
		dto.setUpdateTime(new Date());
		return dictionariesDao.add(dto);

	}

	@Override
	public int delete(String id) {
		return dictionariesDao.delete(id);
	}

	@Override
	public List<Map<String, Object>> selectList(String id, int type, String key, String value, String parent) {
		List<DictionariesDTO> list = dictionariesDao.selectList(id, type, key, value, parent);
		List<Map<String, Object>> result = new LinkedList<Map<String, Object>>();
		for (DictionariesDTO dto : list) {
			Map<String, Object> map = dto.toDbMap();
			result.add(map);
		}
		return result;
	}

	@Override
	public int getSum(String id, int type, String key, String value, String parent) {
		return dictionariesDao.getSum(id, type, key, value, parent);
	}

	@Override
	public Map<String, Object> selById(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		DictionariesDTO dto = dictionariesDao.selById(id);
		if (dto != null) {
			map = dto.toDbMap();
		}
		return map;
	}

	@Override
	public List<Map<String, Object>> selectList(List<String> ids) {
		List<DictionariesDTO> list = dictionariesDao.selectList(ids);
		List<Map<String, Object>> result = new LinkedList<Map<String, Object>>();
		for (DictionariesDTO dto : list) {
			Map<String, Object> map = dto.toDbMap();
			result.add(map);
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> selectList() {
		List<DictionariesDTO> list = dictionariesDao.selectList();
		List<Map<String, Object>> result = new LinkedList<Map<String, Object>>();
		for (DictionariesDTO dto : list) {
			Map<String, Object> map = dto.toDbMap();
			result.add(map);
		}
		return result;
	}

	@Override
	public int update(int type, String key, String value, String parent, String other, String COL) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("typeKey", key);
		map.put("typeValue", value);
		map.put("parent", parent);
		map.put("other", other);
		map.put("COL", COL);
		map.put("updateTime", new Date());
		return dictionariesDao.update(map);
	}

	@Override
	public Map<String, Object> selBytypeValue(String typevalue) {
		DictionariesDTO dto =dictionariesDao.selBytypeValue(typevalue);
		return dto.toDbMap();
	}

}
