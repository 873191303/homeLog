package com.jitv.tv.web;

import com.aspire.commons.Errors;
import com.aspire.commons.rpc.RpcContext;
import com.aspire.commons.rpc.RpcFilter;
import com.aspire.commons.util.StringUtil;
 

public final class CcEnableRpcFilter implements RpcFilter {
	
	 

	public boolean precall(RpcContext rc) {
		 
		return true; 
	}

	public void postcall(RpcContext rc) { 

	} 
	
	private Integer getFlag(Object f) {
		
		try {
			Integer cc = StringUtil.strToInteger(f);
			return cc;
		} catch (Exception ex) {
			throw Errors.clone(Errors.E702, ex);
		}
	}
	
	 
}
