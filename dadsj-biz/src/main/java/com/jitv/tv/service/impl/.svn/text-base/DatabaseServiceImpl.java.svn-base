package com.jitv.tv.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jitv.tv.service.DatabaseService;
import com.jitv.tv.util.RedisUtil;

public class DatabaseServiceImpl implements DatabaseService{
	
	private final static Logger logger = LoggerFactory.getLogger(DatabaseServiceImpl.class);
	private static int StorageTime = 29;

	@Override
	public int selectStorageTime() {
		logger.info("获取数据库过期天数成功");
		return StorageTime;
	}

	@Override
	public String setStorageTime(String Expiration) {
		if(StringUtils.isNotEmpty(Expiration)) {
			StorageTime = Integer.parseInt(Expiration);
		}
		logger.info("修改数据库过期天数成功");
		return "修改成功";
	}

}
