package com.jitv.tv.action;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Bras 统计
 * @author Administrator
 *
 */

import com.aspire.commons.rpc.RpcBean;
import com.aspire.commons.rpc.RpcContext;
import com.aspire.commons.rpc.RpcContexts;
import com.aspire.commons.rpc.RpcMethod;
import com.jitv.tv.service.BrasService;

/**
 * Bras 统统计
 * 
 * @author Administrator
 *
 */
@RpcBean
public class BrasAction {
	private final static Logger logger = LoggerFactory.getLogger(BrasAction.class);

	private BrasService brasService;

	public void setBrasService(BrasService brasService) {
		this.brasService = brasService;
	}

	// Bras数量统统计 wxg 2019-01-09
	@RpcMethod("/action/bras/number/count")
	public void NumberCount() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		String BrasIP = rc.params().get("BrasIP") == null ? "" : (String) rc.params().get("BrasIP");
		String BrasName = rc.params().get("BrasName") == null ? "" : (String) rc.params().get("BrasName");
		String BrasType = rc.params().get("BrasType") == null ? "" : (String) rc.params().get("BrasType");
		String startTime = rc.params().get("startTime") == null ? "" : (String) rc.params().get("startTime");
		String endTime = rc.params().get("endTime") == null ? "" : (String) rc.params().get("endTime");
		Map<String, Object> map = brasService.NumberCount(startIndex, pageSize, BrasIP, BrasName, BrasType, startTime,
				endTime);
		rc.setData(map);
	}

	// Bras数量统统计柱表图表 wxg 2019-01-24
	@RpcMethod("/action/bras/number/columntable")
	public void columnTable() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		String BrasIP = rc.params().get("BrasIP") == null ? "" : (String) rc.params().get("BrasIP");
		String BrasName = rc.params().get("BrasName") == null ? "" : (String) rc.params().get("BrasName");
		String BrasType = rc.params().get("BrasType") == null ? "" : (String) rc.params().get("BrasType");
		String startTime = rc.params().get("startTime") == null ? "" : (String) rc.params().get("startTime");
		String endTime = rc.params().get("endTime") == null ? "" : (String) rc.params().get("endTime");
		Map<String, Object> map = brasService.columnTable(startIndex, pageSize, BrasIP, BrasName, BrasType, startTime,
				endTime);
		rc.setData(map);
	}

	// Bars数量统计 excel 导出 wxg 2019-01-26
	@RpcMethod("/action/bras/number/excel")
	public void BrasDownloadExcel() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		String BrasIP = rc.params().get("BrasIP") == null ? "" : (String) rc.params().get("BrasIP");
		String BrasName = rc.params().get("BrasName") == null ? "" : (String) rc.params().get("BrasName");
		String BrasType = rc.params().get("BrasType") == null ? "" : (String) rc.params().get("BrasType");
		String startTime = rc.params().get("startTime") == null ? "" : (String) rc.params().get("startTime");
		String endTime = rc.params().get("endTime") == null ? "" : (String) rc.params().get("endTime");
		brasService.BrasDownloadExcel(startIndex, pageSize, BrasIP, BrasName, BrasType, startTime, endTime, rc);
	}

	// Bras城市统计 wxg 2019-01-09
	@RpcMethod("/action/bras/city/count")
	public void CityCount() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		String BrasIP = rc.params().get("BrasIP") == null ? "" : (String) rc.params().get("BrasIP");
		String BrasName = rc.params().get("BrasName") == null ? "" : (String) rc.params().get("BrasName");
		String City = rc.params().get("City") == null ? "" : (String) rc.params().get("City");// 城市
		String Manufactor = rc.params().get("Manufactor") == null ? "" : (String) rc.params().get("Manufactor");// 厂家
		Map<String, Object> map = brasService.CityCount(startIndex, pageSize, BrasIP, BrasName, City, Manufactor);
		rc.setData(map);

	}

}
