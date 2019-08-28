package com.jitv.tv.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aspire.commons.rpc.RpcBean;
import com.aspire.commons.rpc.RpcContext;
import com.aspire.commons.rpc.RpcContexts;
import com.aspire.commons.rpc.RpcMethod;
import com.jitv.tv.service.DataBaseTimeService;
import com.jitv.tv.service.DatabaseService;

/**
 * 数据库过期天数
 * 
 * @author Administrator
 *
 */
@RpcBean
public class DatabaseConfigAction {

	private final static Logger logger = LoggerFactory.getLogger(DatabaseConfigAction.class);

	private DatabaseService databaseService;
	
	private DataBaseTimeService dataBaseTimeService;

	public void setDatabaseService(DatabaseService databaseService) {
		this.databaseService = databaseService;
	}

	public void setDataBaseTimeService(DataBaseTimeService dataBaseTimeService) {
		this.dataBaseTimeService = dataBaseTimeService;
	}



	// 查询数据库过期时间 wxg 2019-01-09
	@RpcMethod("/action/database/get/days")
	public void getDay() {
		RpcContext rc = RpcContexts.getContext();
		//int day = databaseService.selectStorageTime();
		List<Map<String, Object>> list = dataBaseTimeService.getList();
		rc.setData(list);
	}

	// 修改数据库过期时间 wxg 2019-01-09
	@RpcMethod("/action/database/set/days")
	public void setDay() {
		RpcContext rc = RpcContexts.getContext();
		//String Expiration = rc.params().get("Expiration") == null ? "" : (String) rc.params().get("Expiration");
		String GZFileRetainedDays = rc.params().get("GZFileRetainedDays") == null ? "" : (String)rc.params().get("GZFileRetainedDays");
		String ESMonitorRetainedDate = rc.params().get("ESMonitorRetainedDate") == null ? "" :(String)rc.params().get("ESMonitorRetainedDate");
		String XdrLogFileRetainedDays = rc.params().get("XdrLogFileRetainedDays") == null ? "" : (String)rc.params().get("XdrLogFileRetainedDays");
		String CommonFileRetainedDays = rc.params().get("CommonFileRetainedDays") == null ? "" : (String)rc.params().get("CommonFileRetainedDays");
		//String result = databaseService.setStorageTime(Expiration);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("GZFileRetainedDays", GZFileRetainedDays);
		map.put("ESMonitorRetainedDate", ESMonitorRetainedDate);
		map.put("XdrLogFileRetainedDays", XdrLogFileRetainedDays);
		map.put("CommonFileRetainedDays", CommonFileRetainedDays);
		String result = dataBaseTimeService.update(map);
		rc.setData(result);
	}

}
