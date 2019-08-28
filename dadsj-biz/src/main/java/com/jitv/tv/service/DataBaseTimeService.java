package com.jitv.tv.service;

import java.util.List;
import java.util.Map;

public interface DataBaseTimeService {

	List<Map<String, Object>> getList();

	int update(String id,String type,String time);
	
	public String update(Map<String, Object> map );

}
