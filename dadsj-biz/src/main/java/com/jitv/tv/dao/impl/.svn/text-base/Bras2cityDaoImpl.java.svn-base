package com.jitv.tv.dao.impl;

import java.util.List;
import java.util.Map;

import com.jitv.tv.dao.Bras2cityDao;
import com.jitv.tv.dao.base.AbstractDAO;
import com.jitv.tv.dto.BrascityDTO;

public class Bras2cityDaoImpl extends AbstractDAO implements Bras2cityDao {

	public Bras2cityDaoImpl() {
		super("bcity_tbl");
	}

	@Override
	public int getSum(String ip, String name, String city, String Manufactor) {
		return (int) function("getSum", newParam("ip", ip), newParam("name", name), newParam("city", city),
				newParam("manufactor", Manufactor));
	}

	@Override
	public List<BrascityDTO> selectList(String pageIndex, String pageSum, String BrasIP, String BrasName, String City,
			String Manufactor) {
		return queryForList("getList", BrascityDTO.class, newParam("pageIndex", Integer.parseInt(pageIndex)),
				newParam("pageSum", Integer.parseInt(pageSum)), newParam("ip", BrasIP), newParam("name", BrasName),
				newParam("city", City), newParam("manufactor", Manufactor));
	}

	@Override
	public int addBras(BrascityDTO dto) {
		try {
			insert("add", dto);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public BrascityDTO select(String ip, String city) {
		return queryForObject("getbras", BrascityDTO.class, newParam("ip", ip), newParam("city", city));
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
