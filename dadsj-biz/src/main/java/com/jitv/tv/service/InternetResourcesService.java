package com.jitv.tv.service;

import java.util.List;
import java.util.Map;

import com.aspire.commons.rpc.RpcContext;

public interface InternetResourcesService {

	Map<String, Object> UserGroupGetFlow(String startIndex, String pageSize, String dls, String User, String link);

	void UserGroupExcel(String startIndex, String pageSize, String dls, String User, String link, RpcContext rc);

	Map<String, Object> OperatorGetFlow(String startIndex, String pageSize, String searchValue);

	Map<String, Object> WebsiteGetFlow(String startIndex, String pageSize, String dls, String User, String type,
			String website);

	void WebsitePercentageExcel(String startIndex, String pageSize, String dls, String User, String type,
			String website, RpcContext rc);

	Map<String, Object> HostGetFlow(String startIndex, String pageSize, String dls, String User, String type,
			String host);

	void HostGetFlowExcel(String startIndex, String pageSize, String dls, String User, String type, String host,
			RpcContext rc);

	List<Map<String, Object>> Percentage();

	List<Map<String, Object>> WebsitePercentage();

	Map<String, Object> BigData(String startIndex, String pageSize, String searchValue);

	Map<String, Object> configuration();

	Map<String, Object> bigdataUpdate(String value);

	Map<String, Object> userConfigurationDelete(String id);

	Map<String, Object> userConfigurationUpdate(String id, String value, String type);

	Map<String, Object> userConfigurationAdd(String value, String type);

	Map<String, Object> websiteConfigurationDelete(String id);

	Map<String, Object> ipConfigurationDelete(String id);

	Map<String, Object> domainConfiguration(String id);

	Map<String, Object> secondConfiguration(String id);

	Map<String, Object> operatorConfigurationDelete(String id);

}
