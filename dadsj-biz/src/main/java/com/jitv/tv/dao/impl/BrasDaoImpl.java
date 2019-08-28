package com.jitv.tv.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.jitv.tv.dao.BrasDao;
import com.jitv.tv.dao.base.AbstractDAO;
import com.jitv.tv.dto.BrasDTO;

public class BrasDaoImpl extends AbstractDAO implements BrasDao {

	public BrasDaoImpl() {
		super("bras_tbl");
	}

	@Override
	public int getSum(String ip, String name, Date startTime, Date endTime) {
		return (int) function("getSum", newParam("ip", ip),newParam("name", name), newParam("startTime", startTime),
				newParam("endTime", endTime));
	}

	@Override
	public List<BrasDTO> selectList(String pageIndex, String pageSum, String BrasIP, String BrasName, Date startTime,
			Date endTime) {
		return queryForList("getList", BrasDTO.class, newParam("pageIndex", Integer.parseInt(pageIndex)),
				newParam("pageSum", Integer.parseInt(pageSum)), newParam("ip", BrasIP), newParam("name", BrasName),
				newParam("startTime", startTime), newParam("endTime", endTime));
	}

	@Override
	public int addBras(BrasDTO dto) {
		try {
			insert("add", dto);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public BrasDTO selectList(String ip, Date time) {
		return queryForObject("getbras", BrasDTO.class, newParam("ip", ip), newParam("time", time));
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

}
