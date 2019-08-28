package com.jitv.tv.util;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.ConnectionFactoryBuilder.Locator;
import net.spy.memcached.ConnectionFactoryBuilder.Protocol;
import net.spy.memcached.FailureMode;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.spring.MemcachedClientFactoryBean;
import net.spy.memcached.transcoders.SerializingTranscoder;

public class MemcachedUtils {
	
	private static MemcachedClientFactoryBean factory; 
	private static final Logger logger = LoggerFactory.getLogger(MemcachedUtils.class);
	private MemcachedUtils(){}
	
	private static MemcachedClient getClient(){
		MemcachedClient back = null;
		try{
			if(factory == null){
				synchronized (MemcachedUtils.class) {
					Properties prop = PropertiesJitv.getInstance();
					factory = new MemcachedClientFactoryBean();
					//setTranscoder(new CustomSerializingTranscoder()) //Add this line 
					factory.setTranscoder(new CustomSerializingTranscoder());
					factory.setServers(prop.getProperty("servers"));
					factory.setOpTimeout(Long.parseLong(prop.getProperty("opTimeout")));
					factory.setTimeoutExceptionThreshold(Integer.parseInt(prop.getProperty("timeoutExceptionThreshold")));
					factory.setUseNagleAlgorithm(Boolean.parseBoolean(prop.getProperty("useNagleAlgorithm")));
					
					Protocol protocol = ConnectionFactoryBuilder.Protocol.valueOf(prop.getProperty("protocol"));
					factory.setProtocol(protocol);
					
					Locator locator = ConnectionFactoryBuilder.Locator.valueOf(prop.getProperty("locatorType"));
					factory.setLocatorType(locator);
					
					FailureMode failureMode = FailureMode.valueOf(prop.getProperty("failureMode"));
					factory.setFailureMode(failureMode);
					
					SerializingTranscoder transcoder = new SerializingTranscoder();
					transcoder.setCompressionThreshold(Integer.parseInt(prop.getProperty("compressionThreshold")));
					factory.setTranscoder(transcoder);
					
					factory.afterPropertiesSet();
				}
			}
			back = (MemcachedClient)factory.getObject();
		}catch(Exception ex){
			logger.error("MemcachedClient Exception:",ex);
		}
		return back;
	}
	
	public static void set(String key ,int exp ,Object o){
		if(o!=null){
			RedisUtil.set(key, exp, o); //使用redis缓存
			//getClient().set(key, exp, o);//使用memcached缓存
		}
	}
	public static Object get(String key){
		return RedisUtil.get(key); // redis
		//return getClient().get(key); //memcached
	}
	public static void delete(String key){
		//getClient().delete(key);
		RedisUtil.delete(key);
	}
	/**
	 * 批量删除
	 * @param key
	 */
	public static void deleteBatch(String key){
		RedisUtil.deleteBatch(key);
	}
	public   static class CustomSerializingTranscoder extends SerializingTranscoder{
		 
	    @Override
	    protected Object deserialize(byte[] bytes) {
	        final ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
	        ObjectInputStream in = null;
	        try {
	            ByteArrayInputStream bs = new ByteArrayInputStream(bytes);
	            in = new ObjectInputStream(bs) {
	                @Override
	                protected  Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
	                    try {
	                        return currentClassLoader.loadClass(objectStreamClass.getName());
	                    } catch (Exception e) {
	                        return super.resolveClass(objectStreamClass);
	                    }
	                }
	            };
	            return in.readObject();
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
	        } finally {
	            closeStream(in);
	        }
	    }
	 
	    private   void closeStream(Closeable c) {
	        if (c != null) {
	            try {
	                c.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
}
