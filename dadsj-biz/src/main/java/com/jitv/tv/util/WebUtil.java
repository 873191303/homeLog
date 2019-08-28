package com.jitv.tv.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aspire.commons.util.JsonUtil;
import com.aspire.commons.util.StringUtil;

public class WebUtil {
	private static final Logger logger = LoggerFactory
			.getLogger(WebUtil.class);
	public static String getWebRoot(HttpServletRequest request){
		String back =  request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/";
		
		return back;
	}
	
	public static String getMyRoot(){
		//http://10.17.174.12:8888/jitvui/  http://localhost:8888/jitv-tv-web/
		String web = PropertiesJitv.getInstance().getProperty("myWebRoot");
		//logger.info("web url="+web);
		return web;
	}
	
	public static String getActionRoot_dataSys(){
		String b = PropertiesJitv.getInstance().getProperty("backWebRoot_dataSys");
		return b;
	}
	
	public static String getJitvui_domainName(){
		return PropertiesJitv.getInstance().getProperty("jitvui_domainName");
	}
	
	public static String getActionRoot_user(){
		String web = PropertiesJitv.getInstance().getProperty("userWebRoot");
		return web;
	}
	
	public static String getActionRoot_device(){
		String web = PropertiesJitv.getInstance().getProperty("deviceWebRoot");
		return web;
	}
	
	public static String getActionRoot(){
		//http://10.17.174.12:8888/jitvui/  http://localhost:8888/jitv-tv-web/
		String web = PropertiesJitv.getInstance().getProperty("backWebRoot");
		//logger.info("web url="+web);
		return web;
	}
	public static String getLuceneRoot(){
		//http://10.17.174.12:8888/jitvui/  http://localhost:8888/jitv-tv-web/
		String web = PropertiesJitv.getInstance().getProperty("luceneWebUrl");
		//logger.info("web url="+web);
		return web;
	}
	public static String getRoot_lucene(){
		//http://10.17.174.12:8888/jitvui/  http://localhost:8888/jitv-tv-web/
		String web = PropertiesJitv.getInstance().getProperty("webUrl_lucene");
		//logger.info("web url="+web);
		return web;
	}
	public static String getImg(HttpServletRequest request,String headImg){
		return WebUtil.getWebRoot(request)+"webresources/img/my/"+headImg; 
	}
	public static String getServiceRoot(){
		//http://10.17.174.12:8888/jitvui/  http://localhost:8888/jitv-tv-web/
		String web = PropertiesJitv.getInstance().getProperty("serviceWebRoot");
		//logger.info("web url="+web);
		return web;
	}
	
	public static String getTestRoot(){
		String web = PropertiesJitv.getInstance().getProperty("testWebRoot");
		//logger.info("web url="+web);
		return web;
	}
	//生成返回电视端二维码使用
	public static String getActionRoot_get(){
		String b = PropertiesJitv.getInstance().getProperty("backWebRoot_get");
		return b;
	}
	//生成返回电视端二维码使用
	public static String getActionRoot_sysui(){
		String b = PropertiesJitv.getInstance().getProperty("backWebRoot_sysui");
		return b;
	}
	public static String getWebUrl(){
		String web = PropertiesJitv.getInstance().getProperty("webUrl");
		return web;
	}
	public static String getPlayVideoUrl(){
		String web = PropertiesJitv.getInstance().getProperty("playVideoUrl");
		return web;
	}
	public static String getPushActionRoot(){
		String web = PropertiesJitv.getInstance().getProperty("pushUrl");
		return web;
	}
	
	public static String getFileServerRoot(){
		return PropertiesJitv.getString("file.server.web");
	}
	public static Map<String, Object> sendGet(String url,
			Map<String, String> heard) {
		Map<String, Object> back = new HashMap<String, Object>();
		String result = "";
		HttpURLConnection  conn =null;
		try {
			String urlName = url;
			URL realUrl;
			realUrl = new URL(urlName);
			conn = (HttpURLConnection)realUrl.openConnection();
			conn.setRequestProperty(
					"User-Agent",
					"Mozilla/5.0 (Linux; Android 5.0; SM-G900P Build/LRX21T) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.23 Mobile Safari/537.36");
			conn.setRequestProperty("Host", "m.youku.com");
			conn.setRequestProperty("Referer:http", "http://www.youku.com/");
			for (String key : heard.keySet()) {
				conn.setRequestProperty(key, heard.get(key));
			}
			conn.connect();
			String content_Length = "";
			String back_head = "";
			Map<String, List<String>> map = conn.getHeaderFields();
			back.put("headerFields", map);
			
			for (String key : map.keySet()) {
				if ("Content-Length".equals(key)) {
					content_Length = map.get(key).toString();
					content_Length = content_Length.replace("[", "");
					content_Length = content_Length.replace("]", "");
				}
				back_head = back_head + " " + map.get(key).toString();
			}
			logger.info(" back head =" + back_head); 
			BufferedReader in = null;

			try {
				in = new BufferedReader(new InputStreamReader(
						conn.getInputStream()));
				String line;
				while ((line = in.readLine()) != null) {
					result = result + line;
				}
				back.put("backhtml", result);
			} catch (Exception e) {
				logger.info("sendGet Exception:", e);
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
					}
				}
			}
			int a=0;
			a= 1;
		} catch (Exception ex) {
			logger.info("sendGet Exception:", ex);	
		}
		logger.info("web url=" + result);
		return back;

	}
	/**
	 * 获得文件大小 单位字节
	 * @param urlPath
	 * @return
	 */
	public static int getFileSize(String urlPath){
		int fileLength = 0;
		try {
			URL url =new URL(urlPath);
			HttpURLConnection  urlcon = (HttpURLConnection)url.openConnection(); 
		    fileLength = urlcon.getContentLength();
		} catch (MalformedURLException e) {
			logger.error("网络请求异常",e);
		} catch (IOException e) {
			logger.error("io 异常",e);
		}   
		return fileLength;
	}
	public static String sendGet(String url,
			Map<String, String> heard,int readTimeout,int connectTimeout,String host, String userAgent) {
		Map<String, Object> back = new HashMap<String, Object>();
		String result = "";
		HttpURLConnection  conn =null;
		try {
			String urlName = url;
			URL realUrl;
			realUrl = new URL(urlName);
			conn = (HttpURLConnection)realUrl.openConnection();
			if(StringUtil.isEmpty(userAgent)){
				conn.setRequestProperty(
						"User-Agent",
						"Mozilla/5.0 (Linux; Android 5.0; SM-G900P Build/LRX21T) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.23 Mobile Safari/537.36");
			}else{
				conn.setRequestProperty("User-Agent", userAgent);
			}
		
			if(StringUtil.isEmpty(host)){
				conn.setRequestProperty("Host", "m.youku.com");
				conn.setRequestProperty("Referer:http", "http://www.youku.com/");
			}else{
				conn.setRequestProperty("Host", host);
			}
			
			if(readTimeout > 0){
				//如果设置了读取超时时间
				conn.setReadTimeout(readTimeout);
			}
			if(connectTimeout > 0){
				//如果设置了连接超时时间
				conn.setReadTimeout(connectTimeout);
			}
			for (String key : heard.keySet()) {
				conn.setRequestProperty(key, heard.get(key));
			}
			conn.connect();
			String content_Length = "";
			String back_head = "";
			Map<String, List<String>> map = conn.getHeaderFields();
			back.put("headerFields", map);
			
			for (String key : map.keySet()) {
				if ("Content-Length".equals(key)) {
					content_Length = map.get(key).toString();
					content_Length = content_Length.replace("[", "");
					content_Length = content_Length.replace("]", "");
				}
				back_head = back_head + " " + map.get(key).toString();
			}
			logger.info(" back head =" + back_head); 
			BufferedReader in = null;

			try {
				in = new BufferedReader(new InputStreamReader(
						conn.getInputStream()));
				String line;
				while ((line = in.readLine()) != null) {
					result = result + line;
				}
				back.put("backhtml", result);
			} catch (Exception e) {
				logger.info("sendGet Exception:{} 获取IP地址时出现了异常，异常信息来自conn.getInputStream()的连接出现问题=============================================");
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
					}
				}
			}
			int a=0;
			a= 1;
		} catch (Exception ex) {
			logger.info("sendGet Exception:", ex);	
		}
		logger.info("web url=" + result);
		String returnStr = (String) back.get("backhtml");
		return returnStr;

	}
	public static Map<String, Object> sendGet2(String url,
			Map<String, String> heard) {
		Map<String, Object> back = new HashMap<String, Object>();
		String result = "";
		HttpURLConnection  conn =null;
		try {
			String urlName = url;
			URL realUrl;
			realUrl = new URL(urlName);
			conn = (HttpURLConnection )realUrl.openConnection();
			conn.setRequestProperty(
					"User-Agent",
					"Mozilla/5.0 (Linux; Android 5.0; SM-G900P Build/LRX21T) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.23 Mobile Safari/537.36");
			conn.setRequestProperty("Host", "m.youku.com");
			conn.setRequestProperty("Referer:http", "http://www.youku.com/");
			for (String key : heard.keySet()) {
				conn.setRequestProperty(key, heard.get(key));
			}
			// 必须设置false，否则会自动redirect到Location的地址
            conn.setInstanceFollowRedirects(false);
			conn.connect();
			String content_Length = "";
			String back_head = "";
			Map<String, List<String>> map = conn.getHeaderFields();
			back.put("headerFields", map);
			for (String key : map.keySet()) {
				if ("Content-Length".equals(key)) {
					content_Length = map.get(key).toString();
					content_Length = content_Length.replace("[", "");
					content_Length = content_Length.replace("]", "");
				}
				back_head = back_head + " " + map.get(key).toString();
			} 
			 
		} catch (Exception ex) {
			logger.info("sendGet Exception:", ex);	
		} 
		return back;

	}
	
	/**
	 * 获得Ip地址
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) { 
	    String ip = request.getHeader("x-forwarded-for"); 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("Proxy-Client-IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("WL-Proxy-Client-IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("HTTP_CLIENT_IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getRemoteAddr(); 
	    } 
	    return ip; 
	  } 
	//获取本机IP
	 public static String getIpAddress() {
         InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}  
         String ip=addr.getHostAddress().toString(); //获取本机ip  
         String hostName=addr.getHostName().toString(); //获取本机计算机名称  
         return ip;
    }
	
	public static String[] getPlaceByIp(String ip){
		logger.debug("用户ip========" + ip);
		String region = "";
		String city = "";
		long time1 = System.currentTimeMillis();
		try {
			String place = (String) MemcachedUtils.get("IP_"+ip);
			if(place == null || "".equals(place.trim()) || ";".equals(place)) {
				// 默认广东省
				region = "广东";
				// 默认广州
				city = "广州";
				String[] places2 = {region,city};
				return places2;
/*				
				long time1_2 = System.currentTimeMillis();
				String ipAddressUrl = "http://ip.taobao.com/service/getIpInfo.php?ip="+ip;   //淘宝的接口
				//新浪的接口
//				String ipAddressUrl = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=" + ip;//120.203.215.19
				Map params = new HashMap<>();
				params.put("ip", ip);
				String host = "ip.taobao.com";
				String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36";
				String rsp2 = WebUtil.sendGet(ipAddressUrl, params,2000,2000,host,userAgent);  //调用第三方接口时，设置了连接超时2秒，读取超时2秒
				long time2 = System.currentTimeMillis();
				logger.info("获取ip地址所属地方 所需时间 " + (time2 - time1_2));
				Map dataMap = null;
				try {
					Map map = JsonUtil.toBean(rsp2, Map.class);
					dataMap = (Map) map.get("data");
					if(dataMap != null){
						// 省份
						region = (String) dataMap.get("region");
						// 城市
						city = (String) dataMap.get("city");
					}else{
						//120.203.215.19
						ipAddressUrl = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=" + ip;
						params.put("ip", ip);
						rsp2 = WebUtil.sendGet(ipAddressUrl, params,2000,2000,host,userAgent);
						map = JsonUtil.toBean(rsp2, Map.class);
						if(map != null){
							region = (String) map.get("province");
							city = (String) map.get("city");
						}
					}
				} catch (Exception e) {
					// 默认广东省
					region = "广东";
					// 默认广州
					city = "广州";
//					logger.error("解析IP地址出现未知异常============================================================");
//					return null;
				}
				city = city.replaceAll("市", "");
				logger.debug("用户所在省份========" + region);
				logger.debug("用户所在城市========" + city);
				if ("".equals(region)) {
					// 默认广东省
					region = "广东";
				}
				if ("".equals(city)) {
					// 默认广州
					city = "广州";
				}
				MemcachedUtils.set("IP_" + ip, -1, region + ";" + city);
				String[] places = {region,city};
				return places;*/
			} else {
				String[] places = place.split(";");
				region = places[0];
				city = places[1];
				return places;
			}

		} catch (Exception e) {
//			e.printStackTrace();
			logger.error("获取ip地址地理位置失败",e);
		}

		return null;
	}
	
	
	  private static String TruncateUrlPage(String strURL)
	    {
	    String strAllParam=null;
	      String[] arrSplit=null;
	     
//	      strURL=strURL.trim().toLowerCase();
	     
	      arrSplit=strURL.split("[?]");
	      if(strURL.length()>1)
	      {
	          if(arrSplit.length>1)
	          {
	                  if(arrSplit[1]!=null)
	                  {
	                  strAllParam=arrSplit[1];
	                  }
	          }
	      }
	     
	    return strAllParam;   
	    }
	  
	    /**
	     * 解析出url参数中的键值对
	     * 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
	     * @param URL  url地址
	     * @return  url请求参数部分
	     */
	public static Map<String, String> URLRequest(String URL) {
		Map<String, String> mapRequest = new HashMap<String, String>();

		String[] arrSplit = null;

		String strUrlParam = TruncateUrlPage(URL);
		if (strUrlParam == null) {
			return mapRequest;
		}
		// 每个键值为一组
		arrSplit = strUrlParam.split("[&]");
		for (String strSplit : arrSplit) {
			String[] arrSplitEqual = null;
			arrSplitEqual = strSplit.split("[=]");

			// 解析出键值
			if (arrSplitEqual.length > 1) {
				// 正确解析
				mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);
			} else {
				if (!"".equals(arrSplitEqual[0])) {
					// 只有参数没有值，不加入
					mapRequest.put(arrSplitEqual[0], "");
				}
			}
		}
		return mapRequest;
	}
	
	public static String[] getPlaceByXinLangAPI(String ip) {
		String api = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=" + ip;
		String province = "";
		String city = "";
		String place = (String) MemcachedUtils.get("IP_" + ip);
		if(place == null || "".equals(place.trim()) || ";".equals(place)) {
			Map<String, String> params = new HashMap<>();
			params.put("ip", ip);
			String host = "int.dpool.sina.com.cn";
			String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36";
			String result = WebUtil.sendGet(api, params, 2000, 2000, host, userAgent); 
			if(StringUtils.isNotEmpty(result)) {
				Map<String, Object> bean = JsonUtil.toBean(result, Map.class);
				province = (String) bean.get("province");
				city = (String) bean.get("city");
				String[] places = {province, city};
				MemcachedUtils.set("IP_" + ip, -1, province + ";" + city);
				return places;
			}
		} else {
			return place.split(";");
		}
		return null;
	}
	
}