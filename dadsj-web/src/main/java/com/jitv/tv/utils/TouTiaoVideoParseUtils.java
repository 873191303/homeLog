package com.jitv.tv.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.util.ResourceUtils;

import com.aspire.commons.AspireRuntimeException;
import com.aspire.commons.HttpClientHelper;
import com.aspire.commons.util.JsonUtil;
import com.jitv.tv.util.RedisUtil;

/**
 * @author 作者 : liujuncai E-mail:345814882@qq.com
 * @version 创建时间：2017-8-28 上午10:05:28
 * @describe
 */
public class TouTiaoVideoParseUtils {

    private TouTiaoVideoParseUtils() {
    }

    public static List<String> userAgentS = new ArrayList<>();
    static {
    	userAgentS.add("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:54.0) Gecko/20100101 Firefox/54.0");
    	userAgentS.add("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.79 Safari/537.36");
    	userAgentS.add("Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1");
        userAgentS.add("Mozilla/5.0 (Linux; Android 5.0; SM-G900P Build/LRX21T) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.79 Mobile Safari/537.36");
    }

    /**
     * 头条视频解析接口域名
     */
    private static final String URI_API = "http://service0.iiilab.com/video/web/toutiao";
    private static final String WEIBO_API  = "http://service0.iiilab.com/video/web/weibo";
    private static final String UNSHORT = "http://service0.iiilab.com/url/unshort";

    /**
     * 设置请求的来源地址
     */
    private static final String ORIGIN = "http://toutiao.iiilab.com";
    private static final String WEIBO = "http://weibo.iiilab.com";
    
    private static final String WEIBO_IIILAB_COOKIE = "WEIBO_IIILAB_COOKIE";
    
    @SuppressWarnings("deprecation")
    public static String parseVideoLink(String link) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        String result = "";
        try {
        	HttpPost httpRequst = new HttpPost(URI_API);
            Map<String, String> map = excuteJs(link);
            httpRequst.setHeader("Origin", ORIGIN);
            httpRequst.setHeader("User-Agent",userAgentS.get(new Random().nextInt(4)));
            httpRequst.setHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
            httpRequst.setHeader("Cookie","PHPSESSIID="+System.currentTimeMillis()+";");
            /**
             *  设置超时时间
             *  setConnectTimeout：设置连接超时时间，单位毫秒。
             *  setConnectionRequestTimeout：设置从connect Manager获取Connection 超时时间，单位毫秒。目前版本是可以共享连接池的。
             *  setSocketTimeout：请求获取数据的超时时间，单位毫秒。如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
             */
            RequestConfig requestConfig = RequestConfig.custom()
            		.setConnectTimeout(8000)
            		.setConnectionRequestTimeout(1000)
            		.setSocketTimeout(8000)
            		.build();
            httpRequst.setConfig(requestConfig);
            
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("link", link));// 请求的参数
            params.add(new BasicNameValuePair("r", map.get("r")));// 请求的参数
            params.add(new BasicNameValuePair("s", map.get("s")));// 请求的参数
            
            httpRequst.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            HttpResponse httpResponse = closeableHttpClient.execute(httpRequst);

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                HttpEntity httpEntity = httpResponse.getEntity();
                result = EntityUtils.toString(httpEntity);// 取出应答字符串
            }
        } catch (Exception e) {
            throw new AspireRuntimeException("888", "头条视频解析异常", e);
        } finally {
            if (closeableHttpClient != null) {
                try {
                    closeableHttpClient.close();
                } catch (IOException e) {

                }
            }
        }
        return result;
    }

	private static Map<String, String> excuteJs(String link) throws Exception {

        // E:\workspace\jitv-tv-biz\jitv-tv\jitv-tv-biz\src\main\resources\jitv.Properties
        String filePath = System.getProperties().getProperty(
                "jitv.sys.properties");

        // E:\workspace\jitv-tv-biz\jitv-tv\jitv-tv-biz\src\main\resources
        filePath = filePath.substring(0, filePath.lastIndexOf(File.separator));

        // E:\workspace\jitv-tv-biz\jitv-tv\jitv-tv-web\src\main\resources\touTiaoVideoParseScript.js
        File file = ResourceUtils.getFile(filePath + File.separator
                + "touTiaoVideoParseScript.js");

        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine engine = engineManager.getEngineByName("JavaScript"); // 得到脚本引擎
        engine.eval(new FileReader(file.getAbsoluteFile()));
        Invocable inv = (Invocable) engine;
        Object valueR = inv.invokeFunction("getR");// 调用js方法获取随机
        if (valueR == null) {
            return null;
        }
        String r = valueR.toString();
        String param = link + "@" + r;// 组装数据
        Object valueS = inv.invokeFunction("getS", param);// 调用js方法实现加密
        if (valueS == null) {
            return null;
        }

        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("r", r);
        hashMap.put("s", valueS.toString());
        return hashMap;
    }

	/**
	 * 获得该网站的cookie->iii_Session 
	 * @return
	 */
	public static String getCookie() {
		//首先查询缓存
		String cookie = (String) RedisUtil.get(WEIBO_IIILAB_COOKIE);
		if(cookie != null && StringUtils.isNotEmpty(cookie)) {
			return cookie;
		}
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
	    HttpGet httpGet = new HttpGet(WEIBO);
        HttpResponse httpResponse2;
		try {
			httpResponse2 = closeableHttpClient.execute(httpGet);
			Header[] responseHeaders = httpResponse2.getAllHeaders();
	          if(responseHeaders != null && responseHeaders.length > 0) {
	          	for(Header h : responseHeaders) {
	          		if("Set-Cookie".equals(h.getName())) {
	          			//cookie
	          			System.out.println("相应头 Set-Cookie：" + h.getValue());
	          			String value = h.getValue();
	          			if(value.contains("iii_Session")) {
	          				String[] vs = value.split(";");
	          				if(vs != null && vs.length > 0) {
	          					for(String v : vs) {
	          						if(v.contains("iii_Session")) {
	          							//缓存设置15分
	          							RedisUtil.set(WEIBO_IIILAB_COOKIE, 60*5, v);
	          							return v;
	          						}
	          					}
	          				}
	          			}
	          		}
	          	}
	          }
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("deprecation")
	public static String parseWeiBoLinks(String link) {
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        String result = "";
        try {
        	String cookie = getCookie();
        	HttpPost httpRequst = new HttpPost(WEIBO_API);
        	Map<String,Object> dataMap = getData(link);
        	link = (String) dataMap.get("data");
            Map<String, String> map = excuteJs(link);
            httpRequst.setHeader("Origin", WEIBO);
            httpRequst.setHeader("User-Agent",userAgentS.get(new Random().nextInt(4)));
            httpRequst.setHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
            httpRequst.setHeader("Cookie",cookie + "; PHPSESSIID="+System.currentTimeMillis()+";");
            // 设置超时时间
            RequestConfig requestConfig = RequestConfig.custom()
            		.setConnectTimeout(5000)
            		.setConnectionRequestTimeout(1000)
            		.setSocketTimeout(5000)
            		.build();
            httpRequst.setConfig(requestConfig);
            
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("link", link));// 请求的参数
            params.add(new BasicNameValuePair("r", map.get("r")));// 请求的参数
            params.add(new BasicNameValuePair("s", map.get("s")));// 请求的参数

            httpRequst.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            HttpResponse httpResponse = closeableHttpClient.execute(httpRequst);
            
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                HttpEntity httpEntity = httpResponse.getEntity();
                result = EntityUtils.toString(httpEntity);// 取出应答字符串
            }
        } catch (Exception e) {
            throw new AspireRuntimeException("888", "微博视频解析异常", e);
        } finally {
            if (closeableHttpClient != null) {
                try {
                    closeableHttpClient.close();
                } catch (IOException e) {

                }
            }
        }
        return result;
	}

	@SuppressWarnings("deprecation")
	private static Map<String,Object> getData(String link) {
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        String result = "";
        try {
        	HttpPost httpRequst = new HttpPost(UNSHORT);
            Map<String, String> map = excuteJs(link);
            httpRequst.setHeader("Origin", WEIBO);
            httpRequst.setHeader("User-Agent",userAgentS.get(new Random().nextInt(4)));
            httpRequst.setHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
            httpRequst.setHeader("Cookie","PHPSESSIID="+System.currentTimeMillis()+";");
            // 设置超时时间
            RequestConfig requestConfig = RequestConfig.custom()
            		.setConnectTimeout(5000)
            		.setConnectionRequestTimeout(1000)
            		.setSocketTimeout(5000)
            		.build();
            httpRequst.setConfig(requestConfig);
            
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("link", link));// 请求的参数
            params.add(new BasicNameValuePair("r", map.get("r")));// 请求的参数
            params.add(new BasicNameValuePair("s", map.get("s")));// 请求的参数

            httpRequst.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            HttpResponse httpResponse = closeableHttpClient.execute(httpRequst);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                HttpEntity httpEntity = httpResponse.getEntity();
                result = EntityUtils.toString(httpEntity);// 取出应答字符串
            }
        } catch (Exception e) {
            throw new AspireRuntimeException("888", "微博视频解析异常", e);
        } finally {
            if (closeableHttpClient != null) {
                try {
                    closeableHttpClient.close();
                } catch (IOException e) {

                }
            }
        }
        return JsonUtil.toBean(result, Map.class);
	}
    
}