package com.jitv.tv.action;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aspire.commons.rpc.RpcBean;
import com.aspire.commons.rpc.RpcContext;
import com.aspire.commons.rpc.RpcContexts;
import com.aspire.commons.rpc.RpcMethod;
import com.jitv.tv.service.DictionariesService;

@RpcBean
public class DictionariesAction {

	private final static Logger logger = LoggerFactory.getLogger(DictionariesAction.class);

	private DictionariesService dictionariesService;

	public void setDictionariesService(DictionariesService dictionariesService) {
		this.dictionariesService = dictionariesService;
	}

	// 字典表查询 wxg 2019-01-19 IP管理使用
	@RpcMethod("/action/dictionaries/city/sel")
	public void DictionariesSel() {
		RpcContext rc = RpcContexts.getContext();
		Integer type = rc.params().get("type") == null ? 1 : Integer.parseInt(rc.params().get("type").toString());// 0
		String id = rc.params().get("id") == null ? "" : (String) rc.params().get("id");// 10
		String key = rc.params().get("key") == null ? "" : (String) rc.params().get("key");
		String value = rc.params().get("value") == null ? "" : (String) rc.params().get("value");
		String parent = rc.params().get("parent") == null ? "" : (String) rc.params().get("parent");
		List<Map<String, Object>> list = dictionariesService.selectList(id, type, key, value, parent);
		rc.setData(list);
	}
	
	// 根据城市查询对应的key wxg 2019-01-19 个人信息使用
	@RpcMethod("/action/dictionaries/city/selbycity")
	public void DictionariesSelByCity() {
		RpcContext rc = RpcContexts.getContext();
		String value = rc.params().get("city") == null ? "" : (String) rc.params().get("city");
	    Map<String, Object> map = dictionariesService.selBytypeValue(value);
		rc.setData(map.get("typeKey"));
	}

}
