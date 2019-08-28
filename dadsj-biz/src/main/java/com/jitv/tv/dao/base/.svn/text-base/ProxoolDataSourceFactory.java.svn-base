package com.jitv.tv.dao.base;

import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang3.math.NumberUtils;
import org.logicalcobwebs.proxool.ProxoolDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibatis.sqlmap.engine.datasource.DataSourceFactory;
import com.jitv.tv.util.PropertiesJitv;

 

public class ProxoolDataSourceFactory  implements DataSourceFactory {

	private ProxoolDataSource dataSource;
	private static final Logger logger = LoggerFactory
			.getLogger(ProxoolDataSourceFactory.class);
	public DataSource getDataSource() {
		return dataSource;
	}

	public void initialize(@SuppressWarnings("rawtypes") Map map) {
		dataSource = new ProxoolDataSource(); 
		dataSource.setDriver(PropertiesJitv.getString("mysql.db.driver"));
		dataSource.setDriverUrl(PropertiesJitv.getString("mysql.db.url"));
		dataSource.setUser(PropertiesJitv.getString("mysql.db.username"));
		dataSource.setPassword(PropertiesJitv.getString("mysql.db.password"));
		dataSource.setMinimumConnectionCount(3);
		dataSource.setMaximumConnectionCount(NumberUtils.toInt(PropertiesJitv.getString("maximumConnectionCount"), 500));
		dataSource.setMaximumActiveTime(30000); // 30绉�
		dataSource.setMaximumConnectionLifetime(600000); // 10鍒嗛挓
		dataSource.setSimultaneousBuildThrottle(100);
		// ... set others
		dataSource.setHouseKeepingTestSql("select 0");
		dataSource.setTestBeforeUse(true);
		dataSource.setTrace(true);
		//logger.info("DriverUrl="+dataSource.getDriverUrl());
		//logger.info("User="+dataSource.getUser());
		//logger.info("Password="+dataSource.getPassword());
	}

}
