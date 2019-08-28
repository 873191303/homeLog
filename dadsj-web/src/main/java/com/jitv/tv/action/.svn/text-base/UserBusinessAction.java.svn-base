package com.jitv.tv.action;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aspire.commons.rpc.RpcBean;
import com.aspire.commons.rpc.RpcContext;
import com.aspire.commons.rpc.RpcContexts;
import com.aspire.commons.rpc.RpcMethod;
import com.jitv.tv.service.UserBusinessService;

/**
 * 用户业务监控相关接口
 * 
 * @author Administrator
 *
 */
@RpcBean
public class UserBusinessAction {

	private final static Logger logger = LoggerFactory.getLogger(RoleAction.class);

	private UserBusinessService userBusinessService;

	public void setUserBusinessService(UserBusinessService userBusinessService) {
		this.userBusinessService = userBusinessService;
	}

	// 访问监控 wxg 2019-01-09
	@RpcMethod("/action/menu_xtgl_fwjk/select")
	public void UserFwjk() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		String searchValue = rc.params().get("searchValue") == null ? "" : (String) rc.params().get("searchValue");// 根据IP搜索
		logger.info("访问监控搜索值searchValue："+searchValue);
		Map<String, Object> map = userBusinessService.UserFwjk(startIndex, pageSize, searchValue);
		rc.setData(map);
	}

	// 流量监控 wxg 2019-01-09
	@RpcMethod("/action/menu_xtgl_lljk/select")
	public void UserFlow() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		String searchValue = rc.params().get("searchValue") == null ? "" : (String) rc.params().get("searchValue");// 根据IP搜索
		logger.info("流量监控searchValue："+searchValue);
		Map<String, Object> map = userBusinessService.UserFlow(startIndex, pageSize, searchValue);
		rc.setData(map);
	}

}
