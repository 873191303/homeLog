package com.jitv.tv.dao;

import java.util.List;
import java.util.Map;

import com.jitv.tv.dto.AlarmDTO;

public interface AlarmDao {

	List<AlarmDTO> selectList(String pageIndex, String pageSum, String title, String servieIP);

	List<AlarmDTO>  select(String serviceip, String manufactorid);

	List<AlarmDTO> selectList(String pageIndex, String pageSum, String title, String title2, String servieIP);

	List<AlarmDTO> selectList(String startIndex, String pageSize, String title1, String title2, String title3,
			String title4, String title5, String title6, String title7, String title8, String searchValue);

	List<AlarmDTO> selectList(String startIndex, String pageSize, String title1, String title2, String title3,
			String title4, String title5, String title6, String title7, String title8, String title9, String title10,
			String title11, String searchValue);

	int getSum(String title, String serviceIp);

	int getSum(String title, String title2, String serviceIp);

	int getSum(String title1, String title2, String title3, String title4, String title5, String title6, String title7,
			String title8, String searchValue);

	int getSum(String startIndex, String pageSize, String title1, String title2, String title3, String title4,
			String title5, String title6, String title7, String title8, String title9, String title10, String title11,
			String searchValue);

	int addAlarm(AlarmDTO dto);

	int update(Map<String, Object> map);

}
