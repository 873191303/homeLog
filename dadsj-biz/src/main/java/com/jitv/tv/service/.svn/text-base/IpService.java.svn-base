package com.jitv.tv.service;

import java.util.List;
import java.util.Map;

public interface IpService {

	int add(String ip, String city, String other, String COL);

	int del(String id);

	int updateuser(String id, String ip, String city, String createTime, String updateTime, String other, String COL);

	List<Map<String, Object>> sel(String startIndex, String pageSize, String ip, String city);

	// 获取城市对应的IP
	List<Map<String, Object>> sel(String city);

	int getSum(String ip, String city);

	Map<String, Object> selById(String id);

}
