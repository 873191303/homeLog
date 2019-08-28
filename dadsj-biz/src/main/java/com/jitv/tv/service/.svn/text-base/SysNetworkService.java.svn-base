package com.jitv.tv.service;

import java.util.Map;

public interface SysNetworkService {

	Map<String, Object> alarm(String startIndex, String pageSize, String title, String searchValue);

	Map<String, Object> getRadius(String startIndex, String pageSize, String searchValue);

	Map<String, Object> UserLog(String startIndex, String pageSize, String searchValue);

	Map<String, Object> LocalUserLog(String startIndex, String pageSize, String searchValue);

	Map<String, Object> SysAlarm(String startIndex, String pageSize, String searchValue);

	Map<String, Object> BusinessAlarm(String startIndex, String pageSize, String title, String title2,
			String searchValue);

	Map<String, Object> UserAlarm(String startIndex, String pageSize, String searchValue);

	Map<String, Object> DataException(String startIndex, String pageSize, String searchValue);

	Map<String, Object> SFTPException(String startIndex, String pageSize, String searchValue);

	Map<String, Object> FileException(String startIndex, String pageSize, String searchValue);

	Map<String, Object> NATMessage(String startIndex, String pageSize, String searchValue);

	Map<String, Object> DPLMessage(String startIndex, String pageSize, String searchValue);

	Map<String, Object> AlarmConfigure();

	Map<String, Object> AlarmConfigureUpdate(String maxthreshold, String minthreshold, String timerange, String number,
			String alarmlevel, String cpu, String memory, String hd, String partition, String network, String project);

	public String executeLinuxCmd(String cmd);

}
