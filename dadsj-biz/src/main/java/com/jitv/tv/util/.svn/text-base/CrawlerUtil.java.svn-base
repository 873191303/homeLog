package com.jitv.tv.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xiaominghui@9ikandian.com
 * @date 2018-2-6 下午4:56:02
 * @describe
 */
public class CrawlerUtil {
	private static final Logger logger = LoggerFactory
			.getLogger(CrawlerUtil.class);

	/**
	 * 讲指定内容输出到指定的地址文件中
	 * 
	 * @param outPage
	 * @param inputStr
	 * @author XiaoMingHui
	 * @date 2018-1-23 下午4:58:44
	 */
	public static final void out(String outPage, String inputStr) {
		FileWriter fw = null;
		BufferedWriter out = null;
		try {
			if (StringUtils.isEmpty(outPage)) {
				outPage = "d://testHTML//" + JitvGuidUtil.getGuid() + ".html";
			}
			File file = new File(outPage);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			if (!file.exists()) {
				file.createNewFile();
			}
			fw = new FileWriter(file);
			out = new BufferedWriter(fw);
			out.write(inputStr);
			out.flush();
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (fw != null) {
					fw.close();
				}
			} catch (Exception e2) {
				logger.error("", e2);
			}
		}
	}

	/**
	 * 根据url，使用流的形式获取页面HTML代码
	 * 
	 * @param strUrl
	 * @return
	 * @author XiaoMingHui
	 * @date 2018-1-23 下午4:17:41
	 */
	public static final String getHtmlPage(String strUrl) {
		InputStream is = null;
		InputStreamReader in = null;
		BufferedReader br = null;
		try {
			URL url = new URL(strUrl);

			is = url.openStream();

			in = new InputStreamReader(is);

			br = new BufferedReader(in);

			String text = null;

			StringBuffer sb = new StringBuffer();

			while ((text = br.readLine()) != null) {
				sb.append(text);
			}
			return sb.toString();
		} catch (Exception e) {
			logger.error("error open url:" + strUrl, e);
			return null;
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (in != null) {
					in.close();
				}
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				logger.error("error open url:" + strUrl, e);
			}
		}
	}
	
	/**
	 * 将谷歌的cookie信息字符串转换为map形式
	 * 
	 * @param cookieStr
	 * @return
	 * @author XiaoMingHui
	 * @date 2018-2-24 下午6:20:46
	 */
	public static final Map<String, String> toCookie(String cookieStr) {
		if (StringUtils.isEmpty(cookieStr)) {
			return null;
		}
		Map<String, String> map = new HashMap<>();
		for (String key_val : cookieStr.split("; ")) {
			String[] split = key_val.split("=");
			map.put(split[0], split[1]);
		}
		return map;
	}
	
}
