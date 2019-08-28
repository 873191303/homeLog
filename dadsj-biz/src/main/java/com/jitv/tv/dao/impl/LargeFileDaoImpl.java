package com.jitv.tv.dao.impl;

import java.util.List;
import com.jitv.tv.dao.LargeFileDao;
import com.jitv.tv.dao.base.AbstractDAO;
import com.jitv.tv.dto.LargeFileDTO;

public class LargeFileDaoImpl extends AbstractDAO implements LargeFileDao {

	public LargeFileDaoImpl() {
		super("large_file");
	}

	@Override
	public List<LargeFileDTO> selectList(String pageIndex, String pageSum, String searchValue) {
		return queryForList("getList", LargeFileDTO.class, newParam("pageIndex", Integer.parseInt(pageIndex)),
				newParam("pageSum", Integer.parseInt(pageSum)), newParam("filename", searchValue));
	}

	@Override
	public int getSum(String searchValue) {
		return (int) function("getCount", newParam("filename", searchValue));
	}

	@Override
	public int getSum() {
		return (int) function("getSum");
	}

}
