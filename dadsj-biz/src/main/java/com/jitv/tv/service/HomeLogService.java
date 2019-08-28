package com.jitv.tv.service;

import java.io.InputStream;
import java.util.Map;
import com.aspire.commons.rpc.RpcContext;

public interface HomeLogService {
	Map<String, Object> getHomeLog(String startIndex, String pageSize, String asSorting, String pubIP, String pubPort,
			String objectiveIP, String objectivePort, String objectiveUrl, String BrasIP, String BrasName,
			String BdbAdmin, String beginDate, String endDate, String userId, String eventid);

	Map<String, Object> getProvinceLog(String startIndex, String pageSize, String asSorting);

	Map<String, Object> getUrl(String searchValue, String startIndex, String pageSize, String asSorting);

	Map<String, Object> Agreement(String searchValue, String startIndex, String pageSize);

	Map<String, Object> BatchLog(InputStream is);

	Map<String, Object> getLog(String startIndex, String pageSize, String exceldate);

	void Excel(String startIndex, String pageSize, String exceldate, RpcContext rc);

	String IF_QUERY(String startIndex, String pageSize, String asSorting, String pubIP, String pubPort,
			String objectiveIP, String objectivePort, String objectiveUrl, String BrasIP, String BrasName,
			String BdbAdmin, String beginDate, String endDate, String EventID);

	Map<String, Object> IF_NOTIFY();

	String redHomeLog(String value);

	Map<String, Object> homeLogPolling(String startIndex, String pageSize, String asSorting, String pubIP,
			String pubPort, String objectiveIP, String objectivePort, String objectiveUrl, String BrasIP,
			String BrasName, String BdbAdmin, String beginDate, String endDate, String eventid, String userId);

	// 家宽日志excel 导出
	void homeLogExcel(String startIndex, String pageSize, String pubIP, String pubPort, String objectiveIP,
			String objectivePort, String objectiveUrl, String BrasIP, String BrasName, String BdbAdmin,
			String beginDate, String endDate, String eventid, RpcContext rc);

}
