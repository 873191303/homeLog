package com.jitv.tv.dao;

import java.util.List;
import java.util.Map;

import com.jitv.tv.dto.BrascityDTO;

public interface Bras2cityDao {

	int  getSum(String ip, String name,String city,String Manufactor);

	List<BrascityDTO> selectList(String pageIndex, String pageSum, String BrasIP, String BrasName, String City,
			String Manufactor);

	int addBras(BrascityDTO dto);

	BrascityDTO select(String ip, String city);

	int update(Map<String, Object> map);

}
