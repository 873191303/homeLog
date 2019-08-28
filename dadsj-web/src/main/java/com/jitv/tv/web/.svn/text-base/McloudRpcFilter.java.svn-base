package com.jitv.tv.web;

import java.util.LinkedHashMap;
import java.util.Map;

import com.aspire.commons.Errors;
import com.aspire.commons.rpc.RpcContext;
import com.aspire.commons.rpc.RpcFilter;

@SuppressWarnings({"unchecked","rawtypes"})
public final class McloudRpcFilter implements RpcFilter {

	public boolean precall(RpcContext rc) {

		String m = rc.getMethod();
		if (null != m) {
			if (m.startsWith("/mcloud/") || m.startsWith("/flex/")) { 
				Object requestId = rc.params().get("requestId");
				if (null == requestId || "".equals(requestId)) {
					throw Errors.clone(Errors.E701, "requestId不能为空");
				}
			}
		} 
		return true; 
	}

	public void postcall(RpcContext rc) {
		
		Map<String, Object> rtv = new LinkedHashMap<String, Object>();
//		rtv.put("id", rc.params().get("id"));
		rtv.put("requestId", rc.params().get("requestId"));
		rtv.put("result", "1");
		rtv.put("data", rc.getData());
		rtv.put("error", null);
		rtv.put("errorMessage", null);
		rtv.put("version", "2.0"); 
		rtv.put("optCode", null);
		rc.setResult(rtv);
		/*
		Object rs = rc.getResult();
		if (rs instanceof Map) { 
			Map<String, Object> result = (Map)rs; 
			
			if (!result.containsKey("result")) {
				result.put("result", "1");
			}
			result.put("requestId", rc.params().get("requestId"));
			if (!result.containsKey("errorMessage")) {
				result.put("errorMessage", null);
			}
			if (!result.containsKey("error")) {
				result.put("error", null);
			}
			result.put("version", "1");
			result.put("optCode", null);
		} else {
			Map<String, Object> rtv = new LinkedHashMap<String, Object>();
			rtv.put("id", rc.params().get("id"));
			rtv.put("result", rc.getResult());
			rtv.put("data", rc.getData());
			rtv.put("error", null);
			rtv.put("jsonrpc", "2.0"); 
			rc.setResult(rtv);
		}
		*/
	}

}
