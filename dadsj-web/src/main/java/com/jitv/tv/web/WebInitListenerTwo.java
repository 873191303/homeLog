package com.jitv.tv.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;

import com.aspire.commons.BeanFactory;
import com.jitv.tv.util.PropertiesJitv;

public class WebInitListenerTwo implements ServletContextListener {

	private static final Logger logger = LoggerFactory.getLogger(WebInitListenerTwo.class);
	
	public void contextInitialized(ServletContextEvent e) { 
		try {
			if (!BeanFactory.hasApplicationContext()) {
				WebApplicationContext context = null;
				try { 
					//new IbatisInit().initDb();
			        //ApplicationContext context2 =  new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"}); 
			        //context = (WebApplicationContext)context2;
					context = (WebApplicationContext)e.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
				} catch (Throwable ex) {
					ex.printStackTrace();
				} 
				if (null != context) {
					BeanFactory.setApplicationContext(context);
				}  else{
					logger.debug("WebInitListener  not context={}",context);
				}
			} 
			BeanFactory.containBean("xxx-unknown");
		} catch (RuntimeException ex) {
			logger.error("contextInitialized方法异常",ex);
			throw ex;
		}
		logger.debug("WebInitListener contextInitialized.");
		PropertiesJitv.getInstance();
	} 

	public void contextDestroyed(ServletContextEvent e) { 
		logger.debug("WebInitListener destroyed.");
	}
}
