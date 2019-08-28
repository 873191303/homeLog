package com.jitv.tv.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aspire.commons.AspireRuntimeException;
import com.aspire.commons.HttpClientHelper;
import com.aspire.commons.rpc.RpcContext;
import com.aspire.commons.util.JsonUtil;
import com.jitv.tv.constant.Constant;

/**
 * 
 * @author xiaominghui 简易的action常用方法封装
 */
public class Action {

	private static final Logger logger = LoggerFactory.getLogger(Action.class);

	/**
	 * 获取请求里面的参数，name是参数的name，value是是否指定当获取到的值为空时的默认值，不写就默认为null
	 * 
	 * @param rc
	 * @param name
	 * @param val
	 * @return
	 * @author XiaoMingHui
	 * @date 2017-8-3 下午8:34:25
	 */
	public final static String getStr(RpcContext rc, String name, String... val) {
		String str = (String) rc.params().get(name);
		return StringUtils.isBlank(str) ? val.length == 0 ? null : val[0] : str;
	}

	/**
	 * 获取请求里面的参数，name是参数的name，value是是否指定当获取到的值为空时的默认值，不写就默认为null
	 * 
	 * @param map
	 * @param name
	 * @param val
	 * @return
	 * @author XiaoMingHui
	 * @date 2017-12-11 下午3:46:24
	 */
	public final static String getParam(Map<String, Object> map, String name,
			String... val) {
		String str = (String) map.get(name);
		return StringUtils.isEmpty(str) ? val.length == 0 ? null : val[0] : str;
	}

	public final static Integer getInt(Map<String, Object> map, String name,
			Integer... val) {
		String str = (String) map.get(name);
		if (StringUtils.isEmpty(str)) {
			if (val.length == 0) {
				return null;
			} else {
				return val[0];
			}
		} else {
			try {
				return Integer.parseInt(str);
			} catch (Exception e) {
				logger.error("数字转换异常：" + str, e);
				return null;
			}
		}
	}

	/**
	 * user项目
	 * 
	 * @param RpcContext
	 * @param url
	 * @param vlaues
	 * @return
	 * @author XiaoMingHui
	 * @date 2017-7-6 上午11:54:48
	 */
	public static final Map<String, Object> getMap(RpcContext RpcContext,
			String url, String... vlaues) {
		return getJsonTOBean(RpcContext, WebUtil.getActionRoot_user() + url,
				vlaues);
	}

	/**
	 * user项目
	 * 
	 * @param RpcContext
	 * @param url
	 * @param nameValuePairs
	 * @return
	 * @author XiaoMingHui
	 * @date 2017-7-6 上午11:54:36
	 */
	public static final Map<String, Object> getMap2(RpcContext RpcContext,
			String url, NameValuePair... nameValuePairs) {
		return getJsonTOBean(WebUtil.getActionRoot_user() + url, nameValuePairs);
	}

	/**
	 * user
	 * 
	 * @param rc
	 * @param url
	 * @param data
	 * @return
	 * @author XiaoMingHui
	 * @date 2017-7-6 上午11:55:46
	 */
	public static final Map<String, Object> getU(RpcContext rc, String url,
			String... data) {
		return getJsonTOBean(rc, WebUtil.getActionRoot_user() + url, data);
	}

	/**
	 * user
	 * 
	 * @param url
	 * @param data
	 * @return
	 * @author XiaoMingHui
	 * @date 2017-7-6 上午11:55:51
	 */
	public static final Map<String, Object> getU(String url,
			NameValuePair... data) {
		return getJsonTOBean(WebUtil.getActionRoot_user() + url, data);
	}

	/**
	 * jitv-dataStatistics-web
	 * 
	 * @param rc
	 * @param url
	 * @param data
	 * @return
	 * @author XiaoMingHui
	 * @date 2017-7-6 上午11:55:46
	 */
	public static final Map<String, Object> getDe(RpcContext rc, String url,
			String... data) {
		return getJsonTOBean(rc, WebUtil.getActionRoot_device() + url, data);
	}

	/**
	 * jitv-dataStatistics-web
	 * 
	 * @param url
	 * @param data
	 * @return
	 * @author XiaoMingHui
	 * @date 2017-7-6 上午11:55:51
	 */
	public static final Map<String, Object> getDe(String url,
			NameValuePair... data) {
		return getJsonTOBean(WebUtil.getActionRoot_device() + url, data);
	}

	/**
	 * jitv-dataStatistics-web
	 * 
	 * @param rc
	 * @param url
	 * @param data
	 * @return
	 * @author XiaoMingHui
	 * @date 2017-7-6 上午11:55:46
	 */
	public static final Map<String, Object> getD(RpcContext rc, String url,
			String... data) {
		return getJsonTOBean(rc, WebUtil.getActionRoot_dataSys() + url, data);
	}

	/**
	 * jitv-dataStatistics-web
	 * 
	 * @param url
	 * @param data
	 * @return
	 * @author XiaoMingHui
	 * @date 2017-7-6 上午11:55:51
	 */
	public static final Map<String, Object> getD(String url,
			NameValuePair... data) {
		return getJsonTOBean(WebUtil.getActionRoot_dataSys() + url, data);
	}

	/**
	 * ji_tv_get
	 * 
	 * @param url
	 * @param data
	 * @return
	 * @author XiaoMingHui
	 * @date 2017-10-25 上午9:52:22
	 */
	public static final Map<String, Object> getG(String url,
			NameValuePair... data) {
		return getJsonTOBean(WebUtil.getActionRoot_get() + url, data);
	}

	/**
	 * ji_tv_get
	 * 
	 * @param rc
	 * @param url
	 * @param data
	 * @return
	 * @author XiaoMingHui
	 * @date 2017-10-25 上午9:52:31
	 */
	public static final Map<String, Object> getG(RpcContext rc, String url,
			String... data) {
		return getJsonTOBean(rc, WebUtil.getActionRoot_get() + url, data);
	}

	/**
	 * 
	 * @param rc
	 * @param url
	 * @param data
	 * @return
	 * @author XiaoMingHui
	 * @date 2017-7-6 上午11:46:28
	 */
	private static Map<String, Object> getJsonTOBean(RpcContext rc, String url,
			String... data) {
		HttpClientHelper instance = HttpClientHelper.getInstance();

		String post = instance.post(url + ".action", getNVP(rc, data));

		return JsonUtil.toBean(post, Map.class);
	}

	/**
	 * 
	 * @param string
	 * @param data
	 * @return
	 * @author XiaoMingHui
	 * @date 2017-7-6 上午11:46:32
	 */
	private static Map<String, Object> getJsonTOBean(String url,
			NameValuePair[] data) {
		String rsp = HttpClientHelper.getInstance().post(url + ".action", data);

		return JsonUtil.toBean(rsp, Map.class);
	}

	/**
	 * 输入对应的属性名字，获取对应的nameValuePair数组。 用传入参数使用。
	 * 
	 * @param RpcContext
	 * @param vlaues
	 * @return NameValuePair[]
	 */
	public static final NameValuePair[] getNVP(RpcContext rc, String... vlaues) {
		NameValuePair[] nPair = new NameValuePair[vlaues.length];

		Map<String, Object> map = rc.params();

		for (int i = 0; i < vlaues.length; i++) {
			nPair[i] = new NameValuePair(vlaues[i], (String) map.get(vlaues[i]));
		}
		return nPair;
	}

	/**
	 * <p>
	 * json转Map，防止异常,<br>
	 * 并且跑出异常的json
	 * <p>
	 * 
	 * @param json
	 * @return Map
	 * @author XiaoMingHui
	 * @date 2017-7-3 下午7:03:29
	 */
	public static final Map<String, Object> toBean(String json) {
		if (StringUtils.isNotBlank(json) && !"null".equals(json)) {
			try {
				return JsonUtil.toBean(json, Map.class);
			} catch (Exception e) {
				logger.error("json转换异常, 转换的json为：" + json, e);
			}
		}
		return new HashMap<>();
	}

	/**
	 * json转List
	 * 
	 * @param json
	 * @return
	 * @author XiaoMingHui
	 * @date 2017-9-6 上午11:34:29
	 */
	public static final List<Object> toList(String json) {
		if (StringUtils.isNotEmpty(json) && !"null".equals(json)) {
			try {
				return JsonUtil.toBean(json, List.class);
			} catch (Exception e) {
				logger.error("json转换异常, 转换的json为：" + json, e);
			}
		}
		return new ArrayList<>();
	}

	/**
	 * 缺少参数异常
	 * 
	 * @param name
	 * @param value
	 * @author XiaoMingHui
	 * @date 2017-9-8 上午11:48:21
	 */
	public static final void nullError(String name, String value) {
		if (StringUtils.isBlank(value)) {
			throw new AspireRuntimeException("700", "lack parameter : " + name);
		}
	}

	/**
	 * 检验字符串是不是空的，或者是null字符串，或者是undefined
	 * 
	 * @param deviceToken
	 * @return
	 * @author XiaoMingHui
	 * @date 2017-12-29 上午10:43:12
	 */
	public static boolean isEmpty(String deviceToken) {
		if (StringUtils.isEmpty(deviceToken)) {
			return true;
		} else if (Constant.NULL.equals(deviceToken)) {
			return true;
		} else if (Constant.UNDEFINED.equals(deviceToken)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isNotEmpty(String deviceToken) {
		if (Action.isEmpty(deviceToken)) {
			return false;
		} else {
			return true;
		}
	}
}
