package com.jitv.tv.dao.impl;

import java.util.List;
import com.jitv.tv.dao.BehaviorDao;
import com.jitv.tv.dao.base.AbstractDAO;
import com.jitv.tv.dto.BehaviorDto;

public class BehaviorDaoImpl extends AbstractDAO implements BehaviorDao {

	public BehaviorDaoImpl() {
		super("behavior_tbl");
	}

	@Override
	public int addUser(BehaviorDto dto) {
		try {
			insert("add", dto);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public List<BehaviorDto> selectList(String pageIndex, String pageSum, String userName, String ip) {
		return queryForList("getbehavior", BehaviorDto.class, newParam("pageIndex", Integer.parseInt(pageIndex)),
				newParam("pageSum", Integer.parseInt(pageSum)), newParam("userName", userName),
				newParam("serviceIP", ip));
	}

	@Override
	public int getSum(String userName, String ip) {
		return (int) function("getSum", newParam("userName", userName), newParam("serviceIP", ip));
	}

}
