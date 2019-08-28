package com.jitv.tv.test;

import java.io.File;
import java.io.FileNotFoundException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.ResourceUtils;

public class ServiceTestParent {

	private static ApplicationContext ctx = null;

	public ServiceTestParent() {
		File file = null;
		try {
			file = ResourceUtils.getFile("classpath:jitv.Properties");
		} catch (FileNotFoundException e) {
			throw new RuntimeException("jitv.Properties file not found");
		}
		System.setProperty("jitv.sys.properties", file.getAbsolutePath());
		ctx = new ClassPathXmlApplicationContext(
				"spring/jitv-service-beans.xml");
	}

	public Object getBean(String name) {
		return ctx.getBean(name);
	}

}
