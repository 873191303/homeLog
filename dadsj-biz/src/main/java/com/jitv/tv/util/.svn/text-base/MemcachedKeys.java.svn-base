package com.jitv.tv.util;

public enum MemcachedKeys {
	USER_TOKEN, 
	USER_CAPTCHA,//登录验证码
	CHAT,
	Dictionary,
	;

	public String getKey(String suffix) {
		String prefix = this.name();

		return prefix + "_" + suffix;
	}
}
