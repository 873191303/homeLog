package com.jitv.tv.util;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
 
import com.aspire.commons.util.JsonUtil;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class TaobaoAPI {
	
	private static final Logger logger = LoggerFactory.getLogger(TaobaoAPI.class);
	
 
	
	private final static String URL = "https://eco.taobao.com/router/rest";
	private final static String APP_KEY = "23327751";
	private final static String SECRET = "8dde3a6a388108972f8cee77dd89c698";
	
	public static boolean sendMessage(String mobile, String captcha) throws ApiException{
		
		Map<String, Object> map = new HashMap<>();
		map.put("product", "9iTv");
		map.put("code", captcha);
		
		String json = JsonUtil.toJson(map);
		
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		
		req.setSmsType("normal");
		//短信签名
		req.setSmsFreeSignName("登录验证");
		//短信模板id(短信内容模板)
		req.setSmsTemplateCode("SMS_6075760");
		//短信模板参数
		req.setSmsParam(json);
		//手机号码
		req.setRecNum(mobile);
		
		TaobaoClient client = new DefaultTaobaoClient(URL, APP_KEY, SECRET);
		AlibabaAliqinFcSmsNumSendResponse resp = client.execute(req);
		
		boolean success = resp.isSuccess();
		if(!success){
			String message = MessageFormat.format("短信发送失败,response.body={0}", resp.getBody());
			logger.warn(message);
		}
		return success;
	}
	
}
