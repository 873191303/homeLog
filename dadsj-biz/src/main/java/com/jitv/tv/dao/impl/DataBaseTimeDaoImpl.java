package com.jitv.tv.dao.impl;

import java.util.List;
import java.util.Map;
import com.jitv.tv.dao.DataBaseTimeDao;
import com.jitv.tv.dao.base.AbstractDAO;
import com.jitv.tv.dto.DataBaseTimeDTO;

public class DataBaseTimeDaoImpl extends AbstractDAO implements DataBaseTimeDao {

	public DataBaseTimeDaoImpl() {
		super("databasetime_tbl");
	}

	@Override
	public List<DataBaseTimeDTO> getList() {
		return queryForList("getList", DataBaseTimeDTO.class);
	}

	@Override
	public int update(Map<String, Object> map) {
		try {
			update("update", map);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public DataBaseTimeDTO getList(String type) {
		return queryForObject("slebytype", DataBaseTimeDTO.class, newParam("type", type));
	}

}
