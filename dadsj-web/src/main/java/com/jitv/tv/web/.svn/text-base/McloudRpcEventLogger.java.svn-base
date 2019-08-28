package com.jitv.tv.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aspire.commons.rpc.RpcContext;
import com.aspire.commons.rpc.RpcLogger;
import com.aspire.commons.util.JsonUtil;
import com.aspire.commons.util.StringUtil;

public class McloudRpcEventLogger implements RpcLogger {
    
    private static final Logger logger = LoggerFactory.getLogger(McloudRpcEventLogger.class);
    
    private int maxOutputChars = 5120*2;
    
    private boolean includeExtLog = true;
    
	public void log(RpcContext rc, Throwable thr) { 
		logLocal(rc, thr);
	}

	protected void logLocal(RpcContext rc, Throwable thr) { 
		
		Object output = rc.getResult();
		
		int ignoredChars = 0;
        int maxChars = maxOutputChars;
        String outputStr = "";
        if (includeExtLog) {
        	if (null != output) {
        		if (output instanceof Map) {
        			outputStr = JsonUtil.toJson(output);
        		} else {
        			outputStr = output.toString();
        		} 
            } 
        	ignoredChars = outputStr.length() - maxOutputChars;
        	if (ignoredChars > 0) {
        		maxChars = maxOutputChars + maxOutputChars; 
        	}
        }
        
        String id = StringUtil.toString(rc.params().get("userId"));
        if (null == id) {
        	id = StringUtil.toString(rc.params().get("contactUserId"));
        }
        if (null == id) {
        	id = StringUtil.toString(rc.params().get("mobile"));
        }
        
        StringBuilder msg = new StringBuilder(maxChars);
        msg.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date()));
        msg.append(" ");
        msg.append(rc.getFromIp());
        msg.append(" ");
        msg.append(rc.getElapsed());
        msg.append(" ");
        msg.append(id);
        msg.append(" ");
        msg.append(rc.getMethod());
        msg.append(" ");
        
        if (includeExtLog) {
        	
        	Object ps = rc.params().remove("jsonStr"); 
        	if (null == ps || "".equals(ps)) {
        		try {
        			ps = JsonUtil.toJson(rc.params());
        		} catch (Exception ex) { /* swallow exception */ }
        	}
        	
            msg.append(ps);
            msg.append(" "); 
            
            if (ignoredChars > 0) {
            	msg.append(outputStr.substring(0, maxOutputChars));
            	msg.append(" (... more ").append(ignoredChars).append(" chars)");
            } else {
            	msg.append(outputStr);
            }
        } 
        logger.info(msg.toString()); 
	} 
}
