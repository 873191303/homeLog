package com.jitv.tv.dao.impl;

import java.util.List;
import com.jitv.tv.dao.UsergrpIspMstDao;
import com.jitv.tv.dao.base.AbstractDAO;
import com.jitv.tv.dto.UsergrpIspMstDTO;

public class UsergrpIspMstDaoImpl extends AbstractDAO implements UsergrpIspMstDao {

	public UsergrpIspMstDaoImpl() {
		super("td_usergrp_isp_mst");
	}

	@Override
	public List<UsergrpIspMstDTO> selectList(String pageIndex, String pageSum, Integer usergrpid, Integer ispid) {
		return queryForList("selectlist", UsergrpIspMstDTO.class, newParam("pageIndex", Integer.parseInt(pageIndex)),
				newParam("pageSum", Integer.parseInt(pageSum)), newParam("usergrpid", usergrpid),
				newParam("ispid", ispid));
	}

	@Override
	public int getSum(Integer usergrpid, Integer ispid) {
		return (int) function("getSum", newParam("usergrpid", usergrpid), newParam("ispid", ispid));
	}

}
