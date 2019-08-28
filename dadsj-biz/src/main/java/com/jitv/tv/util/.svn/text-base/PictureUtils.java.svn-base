package com.jitv.tv.util;

import java.util.Map;
import java.util.Properties;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aspire.commons.HttpClientHelper;
import com.aspire.commons.util.JsonUtil;

public class PictureUtils {
	private static final Logger logger = LoggerFactory.getLogger(PictureUtils.class);
	/**
	 * 将图片保存到服务器
	 * @param picUrl 图片url
	 */
	@SuppressWarnings("unchecked")
	public static final String save(String picUrl) {
		if (StringUtils.isBlank(picUrl)) {
			return null;
		}
		try {
			Properties prop = PropertiesJitv.getInstance();
			String webServerLocation = prop.getProperty("file.server.web");
			String url = webServerLocation + "action/tv/image/download.action";
			
			NameValuePair param = new NameValuePair("imageNames", picUrl);
			
			String result = null;
			try {
				result = HttpClientHelper.getInstance().post(url, param);
				Map<String, Object> map1 = JsonUtil.toBean(result, Map.class);
				Map<String, String> map2 = (Map<String, String>) map1
						.get("retPicNames");
				for (String value : map2.values()) {
					return value;
				}
			} catch (Exception e) {
				logger.debug("url....error=============== "+url);
			}
			return null;
		} catch (Exception e) {
			//
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 保存单个文件
	 * 
	 * @param picUrl
	 * @return
	 * @author XiaoMingHui
	 * @date 2017-11-30 下午10:11:44
	 */
	@SuppressWarnings("unchecked")
	public static final String saveTwo(String picUrl) {
		if (StringUtils.isBlank(picUrl)) {
			return null;
		}
		try {
			Properties prop = PropertiesJitv.getInstance();
			String webServerLocation = prop.getProperty("file.server.web");

			String url = webServerLocation
					+ "action/tv/image/downloadTwo.action";
			NameValuePair param = new NameValuePair("imageNames", picUrl);

			String result = null;
			try {
				result = HttpClientHelper.getInstance().post(url, param);
				Map<String, Object> map1 = JsonUtil.toBean(result, Map.class);
				Map<String, String> map2 = (Map<String, String>) map1
						.get("retPicNames");
				for (String value : map2.values()) {
					return value;
				}
			} catch (Exception e) {
				logger.error("url....error=============== " + url, e);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 将图片保存到服务器并设置保存路径
	 * @param picUrl 图片url
	 */
	@SuppressWarnings("unchecked")
	public static final String saveByPageName(String picUrl,String pageName) {
		try {
			Properties prop = PropertiesJitv.getInstance();
			String webServerLocation = prop.getProperty("file.server.web");

			String url = webServerLocation + "action/tv/image/download.action";
			NameValuePair param = new NameValuePair("imageNames", picUrl);
			NameValuePair param1 = new NameValuePair("pageName", pageName);
			String result = null;
			try {
				result = HttpClientHelper.getInstance().post(url, param,param1);
				Map<String, Object> map1 = JsonUtil.toBean(result, Map.class);
				Map<String, String> map2 = (Map<String, String>) map1
						.get("retPicNames");
				for (String value : map2.values()) {
					return value;
				}
			} catch (Exception e) {
				logger.debug("url....error=============== "+url);
			}
			return null;
		} catch (Exception e) {
			//
			e.printStackTrace();
		}
		
		return null;
	}
	/**
	 * 删除图片文件
	 * 
	 * @param url
	 * @author XiaoMingHui
	 * @date 2017-8-1 下午3:34:49
	 */
	public static final void delete(String url) {
		String wN = "file.server.web";

		String property = PropertiesJitv.getInstance().getProperty(wN);

		property += "action/file/upload/delete.action";

		NameValuePair nvp = new NameValuePair("path", url);
		
		HttpClientHelper.getInstance().post(property, nvp);
	}
	
	/**
	 * 图片压缩，原尺寸，质量压缩,
	 * 
	 * proportion 比例， 范围是 0 - 1。
	 * 如 0.8，或者0.5，表示80%或者50%
	 * 
	 * @param img
	 * @param proportion
	 * @author XiaoMingHui
	 * @date 2018-1-18 下午2:35:32
	 */
	public static final void imgCompress(String img, Double proportion) {
		logger.info("图片压缩，原尺寸，质量压缩:" + img);
		String url = WebUtil.getActionRoot_sysui();
		url += "action/tvDvd/imgCompressProportion.action";
		HttpClientHelper.getInstance().post(url, new NameValuePair("img", img),
				new NameValuePair("proportion", proportion + ""));
	}
}
