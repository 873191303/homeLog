package com.jitv.tv.util;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.aspire.commons.AspireRuntimeException;
import com.aspire.commons.util.JsonUtil;

public class ErrorCode {
	public static final AspireRuntimeException E111 = new AspireRuntimeException("111", "密码验证失败"); 
	public static final AspireRuntimeException E222 = new AspireRuntimeException("222", "用户未登录"); 
	public static final AspireRuntimeException E700 = new AspireRuntimeException("700", "缺少参数");
	public static final AspireRuntimeException E711 = new AspireRuntimeException("711", "参数类型不匹配");
	public static final AspireRuntimeException E333 = new AspireRuntimeException("333", "数据库操作失败");
	public static final AspireRuntimeException E444 = new AspireRuntimeException("444", "城市非法");
	public static final AspireRuntimeException E999 = new AspireRuntimeException("999", "未知异常");	
	public static final AspireRuntimeException E998 = new AspireRuntimeException("998", "用户名重复");	
	public static final AspireRuntimeException E991 = new AspireRuntimeException("991", "用户名不存在");	
	public static final AspireRuntimeException E997 = new AspireRuntimeException("997", "验证码错误");
	public static final AspireRuntimeException E996 = new AspireRuntimeException("996", "上传非excel文件");
	
	
	/**
	 * 抛出异常type的一个实例
	 * @param type - 异常类型样本类型
	 * @param cause - 异常原因对象
	 */
	public static void throwEx(AspireRuntimeException type, Throwable cause) {
		throw clone(type, cause);
	}

	/**
	 * 抛出异常type的一个实例
	 * @param type - 异常类型样本类型 
	 * @exception - AspireRuntimeException
	 */
	public static void throwEx(AspireRuntimeException type) {
		throw clone(type);
	}
	
	/**
	 * 将异常原因对象转成卓望基础运行时异常实例
	 * 备注: 如果类型不匹配,使用[999:未知异常]类型将其包装成卓望基础运行时异常
	 * @param ex - 异常类型样本类型 
	 * @return - 卓望基础运行时异常实例
	 */
	public static AspireRuntimeException valueOf(Throwable ex) {
		
		AspireRuntimeException are = null;
		if (ex instanceof AspireRuntimeException) {
			are = (AspireRuntimeException)ex;
		} else if (ex instanceof NullPointerException) {
			are = clone(E700, ex);
		} else if (ex instanceof ClassCastException) { 
			are = clone(E711, ex);
		} else if (ex instanceof SQLException) { 
			are = clone(E333, ex);
		} else {
			are = clone(E999, ex);
		}
		return are;
	}
	
	/**
	 * 将异常原因对象转成卓望基础运行时异常实例
	 * 备注: 如果类型不匹配,使用指定包装类型将其包装成卓望基础运行时异常
	 * @param ex - 异常类型样本类型
	 * @param wrapType - 异常包装类型
	 * @return - 卓望基础运行时异常实例
	 */
	public static AspireRuntimeException valueOf(Throwable ex, AspireRuntimeException wrapType) {
		
		AspireRuntimeException are = null;
		if (ex instanceof AspireRuntimeException) {
			are = (AspireRuntimeException)ex;
		} else {
			are = clone(wrapType, ex);
		}
		return are;
	}
	
	/**
	 * 根据异常类型样本类型和异常原因对象构造一个异常新实例
	 * @param type - 异常类型样本类型
	 * @param cause - 异常原因对象
	 * @return - 异常新实例
	 */
	public static AspireRuntimeException clone(AspireRuntimeException type, Throwable cause) {

		AspireRuntimeException newOne = new AspireRuntimeException();
		newOne.setErrorCode(type.getErrorCode());
		newOne.setErrorMsg(type.getErrorMsg());
		newOne.setCause(cause);
		newOne.setErrorAction(type.getErrorAction());
		return newOne;
	}
	
	/**
	 * 根据异常类型样本类型和异常原因对象构造一个异常新实例
	 * @param type - 异常类型样本类型
	 * @param cause - 异常原因描述
	 * @return - 异常新实例
	 */
	public static AspireRuntimeException clone(AspireRuntimeException type, String cause) {

		AspireRuntimeException newOne = clone(type); 
		newOne.setErrorCause(cause); 
		return newOne;
	} 
	
	/**
	 * 根据异常类型样本类型和异常原因对象构造一个异常新实例
	 * @param type - 异常类型样本类型
	 * @return - 异常新实例
	 */
	public static AspireRuntimeException clone(AspireRuntimeException type) {

		AspireRuntimeException newOne = new AspireRuntimeException();
		newOne.setErrorCode(type.getErrorCode());
		newOne.setErrorMsg(type.getErrorMsg());
		newOne.setErrorAction(type.getErrorAction());
		return newOne;
	}
	
	/**
	 * 根据异常类型样本类型和异常原因对象构造一个Map对象
	 * @param ex - 异常对象
	 * @return - Map对象: {errorSn, errorCode, errorMsg, errorCause, errorAction}
	 */
	public static Map<String, String> toMap(AspireRuntimeException ex) {

		Map<String, String> errorMap = new LinkedHashMap<String, String>();
		errorMap.put("errorCode", ex.getErrorCode());
		errorMap.put("errorSn", ex.getErrorSn());
		errorMap.put("errorMsg", ex.getErrorMsg());
		errorMap.put("errorCause", ex.getErrorCause());
		errorMap.put("errorAction", ex.getErrorAction());
		return errorMap;
	}
	
	/**
	 * 将卓望异常对象转成json字符串
	 * @param ex - 异常对象
	 * @return - json字符串
	 */
	public static String toJson(AspireRuntimeException ex) {
		return JsonUtil.toJson(toMap(ex));
	}
	
	/**
	 * 将Map对象转成异常对象
	 * @param errorMap - Map对象: {errorSn, errorCode, errorMsg, errorCause, errorAction}
	 * @return - 异常对象
	 */
	public static AspireRuntimeException parse(Map<String, String> errorMap) {
		
		AspireRuntimeException ex = new AspireRuntimeException();
		ex.setErrorSn(errorMap.get("errorSn"));
		ex.setErrorCode(errorMap.get("errorCode"));
		ex.setErrorMsg(errorMap.get("errorMsg"));
		ex.setErrorCause(errorMap.get("errorCause"));
		ex.setErrorAction(errorMap.get("errorAction"));
		return ex;		
	}
	
	/**
	 * 将json字符串转成卓望异常对象
	 * @param json - json字符串
	 * @return - 异常对象
	 */
	public static AspireRuntimeException parse(String json) {
		Map<String, String> errorMap = JsonUtil.toBean(json, Map.class); 
		return parse(errorMap);
	}
}
