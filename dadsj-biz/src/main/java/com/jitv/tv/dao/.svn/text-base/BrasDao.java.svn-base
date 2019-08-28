package com.jitv.tv.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.jitv.tv.dto.BrasDTO;

public interface BrasDao {

	int getSum(String ip, String name, Date startTime, Date endTime);

	List<BrasDTO> selectList(String pageIndex, String pageSum, String BrasIP, String BrasName, Date startTime,
			Date endTime);

	int addBras(BrasDTO dto);

	BrasDTO selectList(String ip, Date time);

	int update(Map<String, Object> map);

}
