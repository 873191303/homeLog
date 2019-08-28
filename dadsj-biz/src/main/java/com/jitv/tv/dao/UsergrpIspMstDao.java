package com.jitv.tv.dao;

import java.util.List;

import com.jitv.tv.dto.UsergrpIspMstDTO;

public interface UsergrpIspMstDao {

	List<UsergrpIspMstDTO> selectList(String pageIndex, String pageSum, Integer usergrpid, Integer ispid);

	int getSum(Integer usergrpid, Integer ispid);

}
