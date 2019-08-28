package com.jitv.tv.dao;

import java.util.List;
import com.jitv.tv.dto.ErrorLoginDTO;

public interface ErrorLoginDao {
	int getSum(String userName);

	List<ErrorLoginDTO> selectList(String pageIndex, String pageSum, String userName);

	int addErrorLogin(ErrorLoginDTO dto);

}
