package com.jitv.tv.dao.base;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jitv.tv.util.PropertiesJitv;

public class IbatisInit {
	private static final Logger logger = LoggerFactory.getLogger(IbatisInit.class);
	private static boolean isLoad = true;
	public void initDb(){
		FileOutputStream oFile =  null;
		InputStream in = null;
		try{
			
			if(isLoad){
				logger.debug("IbatisInit begian:");
				Properties prop = new Properties();
				in = getClass().getResourceAsStream("/jitv.db.Properties");
				prop.load(in); 
				
				isLoad = false;
				///保存属性到b.properties文件
				String path2 = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
				path2 = path2 +"/jitv.db.Properties";
				logger.debug("IbatisInit path2="+path2);  
				
				oFile = new FileOutputStream(path2, false);//true表示追加打开
				Properties prop2 = new Properties();
				prop2.setProperty("driver",PropertiesJitv.getInstance().getProperty("mysql.db.driver"));
				prop2.setProperty("url",PropertiesJitv.getInstance().getProperty("mysql.db.url"));
				prop2.setProperty("username", PropertiesJitv.getInstance().getProperty("mysql.db.username"));
				prop2.setProperty("password", PropertiesJitv.getInstance().getProperty("mysql.db.password"));
				prop2.store(oFile, "The New properties file");
				oFile.close();
			}
			 
		}catch(Exception ex){
			logger.error("initDb Properties error:",ex);
		}finally{
			try {
				if(in!=null){
					in.close();
				}
				if(oFile!=null){
					oFile.close();
				}
			} catch (IOException e) { 
			}
		}
	}
}
