package com.jitv.tv.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aspire.commons.rpc.RpcBean;
import com.aspire.commons.rpc.RpcContext;
import com.aspire.commons.rpc.RpcContexts;
import com.aspire.commons.rpc.RpcMethod;
import com.jitv.tv.service.IpService;
import com.jitv.tv.util.ErrorCode;

@RpcBean
public class IpAction {

	private final static Logger logger = LoggerFactory.getLogger(IpAction.class);

	private IpService ipService;

	public void setIpService(IpService ipService) {
		this.ipService = ipService;
	}

	// IP管理新增wxg 2019-01-17
	@RpcMethod("/action/sys/ip/add")
	public void add() {
		RpcContext rc = RpcContexts.getContext();
//		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
//		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
//		String searchValue = rc.params().get("searchValue") == null ? "" : (String) rc.params().get("searchValue");// 搜索参数
		String ip = rc.params().get("ip") == null ? "" : (String) rc.params().get("ip");
		String city = rc.params().get("city") == null ? "" : (String) rc.params().get("city");
		int count = ipService.add(ip, city, "", "");
		if (count > 0) {
			rc.setData("ok");
		}
	}

	// ip管理 分頁查詢 wxg 2019-01-18
	@RpcMethod("/action/sys/ip/sel")
	public void sel() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		String searchValue = rc.params().get("searchValue") == null ? "" : (String) rc.params().get("searchValue");// 搜索参数
		List<Map<String, Object>> list = ipService.sel(startIndex, pageSize, "", searchValue);
		int count = ipService.getSum("", searchValue);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("iTotalRecords", count);
		map.put("record", list);
		rc.setData(map);
	}

	// ip更新 wxg 2019-01-18
	@RpcMethod("/action/sys/ip/update")
	public void update() {
		RpcContext rc = RpcContexts.getContext();
		String city = rc.params().get("city") == null ? "" : (String) rc.params().get("city");
		String ip = rc.params().get("ip") == null ? "" : (String) rc.params().get("ip");
		String id = rc.params().get("id") == null ? "" : (String) rc.params().get("id");
		int count = ipService.updateuser(id, ip, city, "", "", "", "");
		if (count > 0) {
			rc.setData("ok");
		}
	}

	// ip管理 根据id查询 wxg 2019-01-18
	@RpcMethod("/action/sys/ip/byid")
	public void selById() {
		RpcContext rc = RpcContexts.getContext();
		String id = rc.params().get("id") == null ? "" : (String) rc.params().get("id");// id
		Map<String, Object> map = ipService.selById(id);
		rc.setData(map);
	}

	// ip删除wxg 2019-01-18
	@RpcMethod("/action/sys/ip/del")
	public void del() {
		RpcContext rc = RpcContexts.getContext();
		String id = rc.params().get("id") == null ? "" : (String) rc.params().get("id");// 搜索参数
		int count = ipService.del(id);
		if (count > 0) {
			rc.setData("删除IP成功");
		} else {
			logger.info("删除IP失败");
			throw ErrorCode.E333;
		}
	}

	// 验证IP是否重复 wxg 2019-01-18
	@RpcMethod("/action/sys/ip/checkip")
	public void checkIP() {
		RpcContext rc = RpcContexts.getContext();
		String value = rc.params().get("value") == null ? "" : (String) rc.params().get("value");
		int count = ipService.getSum(value, "");
		if (count > 0) {
			rc.setData("error");
			logger.info("新增用户无重复名称");
			// throw ErrorCode.E998;
		} else {
			rc.setData("success");

		}
	}

	// 验证IP是否重复 wxg 2019-01-18
	@RpcMethod("/check/IP")
	public void checkIP2() {
		RpcContext rc = RpcContexts.getContext();
		String value = rc.params().get("value") == null ? "" : (String) rc.params().get("value");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + value);
		if (StringUtils.isNotEmpty(value)) {
			int count = ipService.getSum(value, "");
			if (count > 0) {
				rc.setData("error");
				logger.info("新增用户无重复名称");
				// throw ErrorCode.E998;
			} else {
				rc.setData("success");

			}
		}
	}
}
