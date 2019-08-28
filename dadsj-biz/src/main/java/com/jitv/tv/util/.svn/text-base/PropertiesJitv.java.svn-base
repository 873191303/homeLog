package com.jitv.tv.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesJitv {
	private static Properties prop = null;
	private static final Logger logger = LoggerFactory
			.getLogger(PropertiesJitv.class);

	public static Properties getInstance() {
		if (prop == null) {

			// InputStream in
			// =ClassLoader.getSystemResourceAsStream("jitv.properties");
			FileInputStream fis = null;
			try {
				String filePath = System.getProperties().getProperty(
						"jitv.sys.properties");
				logger.info(" Properties path:" + filePath);
				fis = new FileInputStream(filePath);
				prop = new Properties();
				prop.load(fis);

			} catch (Exception e) {
				logger.error("load Properties error",e);
			} finally {
				try {
					if (fis != null) {
						fis.close();
					}
				} catch (IOException e) {
					logger.error("propertiesJitv getInstance", e);
				}
			}
		}
		return prop;
	}

	public static String getString(String key) {
		String b = "";
		b = getInstance().getProperty(key);
		return b;
	}
}
