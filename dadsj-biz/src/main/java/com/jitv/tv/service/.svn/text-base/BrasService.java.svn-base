package com.jitv.tv.service;

import java.util.Map;

import com.aspire.commons.rpc.RpcContext;

public interface BrasService {
	Map<String, Object> NumberCount(String startIndex, String pageSize, String BrasIP, String BrasName, String BrasType,
			String startTime, String endTime);

	Map<String, Object> columnTable(String startIndex, String pageSize, String BrasIP, String BrasName, String BrasType,
			String startTime, String endTime);

	Map<String, Object> CityCount(String startIndex, String pageSize, String BrasIP, String BrasName, String City,
			String Manufactor);

	void BrasDownloadExcel(String startIndex, String pageSize, String BrasIP, String BrasName, String BrasType,
			String startTime, String endTime, RpcContext rc);

	// bras 新增统计相关
	int addBras(String ip, String name, String account, String time, String manufactor, String city, String count);

	// bras 新增 城市相关
	int addBrasCity(String ip, String name, String account, String time, String manufactor, String city, String count);

	Map<String, Object> select(String city, String manufactor, String ip, String name, String pageIndex,
			String pageSum);

}
