package com.jitv.tv.web;

import java.net.URLEncoder;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aspire.commons.HttpClientHelper;
import com.aspire.commons.rpc.RpcContext;
import com.aspire.commons.util.DateUtil;
import com.aspire.commons.util.JsonUtil;
import com.aspire.commons.util.StringUtil;
 
 

public class McloudRpcEventV2Logger extends McloudRpcEventLogger {
    
    private static final Logger logger = LoggerFactory.getLogger(McloudRpcEventV2Logger.class); 
    
    private static final int MAX_LOG_LENGTH = 300 * 1024;
    
    

	public void log(RpcContext rc, Throwable thr) { 
		
		//logLocal(rc, thr); 
		//remoteLog(rc, thr);
	}  
	
	private void remoteLog(RpcContext rc, Throwable thr) {

    	LogData ld = new LogData(); 
    	ld.path = rc.getMethod();
    	ld.params = rc.params();
    	ld.fromIp = rc.getFromIp();
    	ld.are = thr;
    	ld.output = rc.getResult();
    	ld.elapsed = rc.getElapsed();
    	//ExecutorUtil.submit(ld);
	}

	 
	
    /**
     * 
     * @creator yuyoo(zhandulin@aspirecn.com)
     * @author www.aspirecn.com
     * @date 2012-7-26 下午3:45:01
     */
    private class LogData implements Runnable {
    	
    	
    	String path;
    	
    	Map<String, Object> params;
    	
    	String fromIp;
    	
    	Throwable are; 
    	
    	Object output;
    	
    	long elapsed;
    	
    	String remoteLogUrl = "";//mc.getConfig().get("log.sys.remote.url");
    	String app = "";//mc.getConfig().get("log.sys.app");

		public void run() { 
			try { 
				
				String jsonStr = (String) params.remove("jsonStr");
				if (null == jsonStr || "".equals(jsonStr)) {
					jsonStr = JsonUtil.toJson(params);
				}
	        	  
	        	String id = StringUtil.toString(params.get("userId"));
	            if (null == id) {
	            	id = StringUtil.toString(params.get("contactUserId"));
	            }
	            if (null == id) {
	            	id = StringUtil.toString(params.get("mobile"));
	            }
	            if (null == id) {
	            	id = StringUtil.toString(params.get("endpointId"));
	            }
	            if (null == id) {
	            	id = StringUtil.toString(params.get("token"));
	            }
	            if (null == id) {
	            	id = StringUtil.toString(params.get("imei"));
	            } 
	            if (null == id) {
	            	id = StringUtil.toString(params.get("companyId"));
	            } 
	            
	            String level = "i";
	            
	            if (null != are) { 
	            	level = "e";
	            }
	              
	            String serverIp = (String) params.remove("serverName");
	            
	            Map<String, Object> logData = new LinkedHashMap<String, Object>();
	            logData.put("app", app);
	            logData.put("operate", path);
	            logData.put("operator", id);
	            logData.put("level", level);
	            logData.put("logTime", DateUtil.convert(new Date()));
	            logData.put("input", jsonStr);
	            logData.put("output", output);
	            int inputLength = 0;
	            if(null != jsonStr){
	            	inputLength = jsonStr.getBytes().length;
	            }
	            int outputLength = 0;
	            if(null != output){
	            	outputLength = output.toString().getBytes().length;
	            }
	            int zipLength = inputLength + outputLength;
	            if(zipLength > MAX_LOG_LENGTH){
	            	logData.put("zipIo", 1);
	            }
	            logData.put("clientIp", fromIp);
	            logData.put("serverIp", serverIp);
	            logData.put("elapsed", elapsed);  
	            String json = JsonUtil.toJson(logData);
	            boolean isEncode = false;
        		if (null != json) {
    				if (json.indexOf('&') >= 0) {
    					isEncode = true;
    				}
    			}
        		if(isEncode) {
        			json = URLEncoder.encode(json) ;
        		}
	            String logStr = "jsonStr=" + json; 
	        	String r = HttpClientHelper.getInstance().post(remoteLogUrl, logStr); 
	    	} catch (Exception ex) {
	    		logger.info("ex:{}", ex.getMessage());
	    	}
		} 
    }

}
