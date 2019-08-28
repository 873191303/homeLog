package com.jitv.tv.dao.impl;

import java.util.List;
import com.jitv.tv.dao.ErrorLoginDao;
import com.jitv.tv.dao.base.AbstractDAO;
import com.jitv.tv.dto.ErrorLoginDTO;

public class ErrorLoginDaoImpl extends AbstractDAO implements ErrorLoginDao {

	public ErrorLoginDaoImpl() {
		super("error_login_tbl");
	}

	@Override
	public int getSum(String userName) {
		return (int) function("getSum", newParam("username", userName));
	}

	@Override
	public List<ErrorLoginDTO> selectList(String pageIndex, String pageSum, String userName) {
		return queryForList("getList", ErrorLoginDTO.class, newParam("pageIndex", Integer.parseInt(pageIndex)),
				newParam("pageSum", Integer.parseInt(pageSum)), newParam("username", userName));
	}

	@Override
	public int addErrorLogin(ErrorLoginDTO dto) {
		try {
			insert("add", dto);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

}
