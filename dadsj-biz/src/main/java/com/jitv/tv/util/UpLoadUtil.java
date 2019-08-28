package com.jitv.tv.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpLoadUtil {
	private static final Logger logger = LoggerFactory
			.getLogger(UpLoadUtil.class);

	public static String toUpLoad(InputStream in,String oldfileurl) {//,String oldfileurl
		String back = "";
		
		try {
			String url = PropertiesJitv.getString("file.server.web")
					+ "action/file/upload/img.action?oldfileurl="+oldfileurl;
			HttpClient client = new HttpClient();
			PostMethod post = new PostMethod(url); 
			post.setRequestBody(in);
			//client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"UTF-8");
			client.getParams().setParameter("Content-Type", "text/html; charset=gb2312");
//			client.getParams().setParameter("oldfileurl", oldfileurl);
			client.executeMethod(post);

			back = post.getResponseBodyAsString();
		} catch (Exception e) {
			logger.error("toUpLoad exptione :", e);
		}
		return back;
	}
	public static String toUpLoad(String str) {
		String back = "";
		try {
			 back = toUpLoad(StringTOInputStream(str),null);
		} catch (Exception e) {
			logger.error("toUpLoad exptione :", e);
		}
		return back;
	}
	
	public static String upLoadByUrl(String url){
		String back = null;
		try {
			HttpClient client = new HttpClient();  
			GetMethod get = new GetMethod(url);  
			int status = client.executeMethod(get);  
			//System.out.println("Content-type="+get.getResponseHeader("Content-Type"));
			//name = (name.indexOf(".") == -1)?(name + getFiex(get.getResponseHeader("Content-Type").getValue())):name;
	        if(status == 200){ 
	        	ByteArrayInputStream is = new ByteArrayInputStream(get.getResponseBody());  
	        	back = toUpLoad(is,null);
	        } 
		} catch (Exception e) {
			logger.error("toUpLoad exptione :", e);
		}
		return back;
	}
	
	public static InputStream StringTOInputStream(String in) throws Exception{         
        ByteArrayInputStream is = new ByteArrayInputStream(in.getBytes());  
        return is;  
    }
}
