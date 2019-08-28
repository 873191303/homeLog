package com.jitv.tv.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Administrator on 2016/12/22.
 */
public class JedisPoolManager {
    private static JedisPool jedisPool;
    private volatile static JedisPoolManager jedisPoolManager;
    private JedisPoolManager(){
//        Properties properties = new Properties();
//        try {
//         //   String filePath = System.getProperties().getProperty("redis.properties");
//            String path = Class.class.getClass().getResource("/").getPath();
//             File file = new File(path+"redis.properties");
//            FileInputStream fileInputStream = new FileInputStream(file);
//            properties.load(fileInputStream);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            return;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return;
//        }
        // 创建jedis池配置实例
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置池配置项值
      
        String maxTotal = PropertiesJitv.getString("redis.pool.maxTotal");
        config.setMaxTotal(Integer.parseInt(maxTotal));
        String maxIdle =   PropertiesJitv.getString("redis.pool.maxIdle");
        config.setMaxIdle(Integer.parseInt(maxIdle));
        String minIdle =  PropertiesJitv.getString("redis.pool.minIdle"); 
        config.setMinIdle(Integer.parseInt(minIdle));
        String maxWaitMillis = PropertiesJitv.getString("redis.pool.maxWaitMillis");   
        config.setMaxWaitMillis(Integer.parseInt(maxWaitMillis));
        String testOnBorrow = PropertiesJitv.getString("redis.pool.testOnBorrow");   
        config.setTestOnBorrow("true".equals(testOnBorrow));
        String testOnReturn = PropertiesJitv.getString("redis.pool.testOnReturn");    
        config.setTestOnReturn("true".equals(testOnReturn));
        String auth = PropertiesJitv.getString("redis.server.auth");    
        String timeOut = PropertiesJitv.getString("redis.server.timeout");   
        String host = PropertiesJitv.getString("redis.server.host");  
        String port = PropertiesJitv.getString("redis.server.port");   
       // JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPool = new JedisPool(config,host,Integer.parseInt(port)); //,Integer.parseInt(timeOut),auth
        int time =  Integer.parseInt(timeOut);
        //database  redis 数据库命名  每个数据库对外都是一个从0开始的递增数字命名，Redis默认支持16个数据库
        String password = PropertiesJitv.getString("redis.server.psw");
        jedisPool = new JedisPool(config,host,Integer.parseInt(port), time, password, 0);  
    }
    public static JedisPoolManager getManager(){
        if(jedisPoolManager == null){
            synchronized (JedisPoolManager.class){
                if(jedisPoolManager == null){
                    jedisPoolManager = new JedisPoolManager();
                }
            }
        }
        return jedisPoolManager;
    }
    public Jedis getResource(){
        Jedis jedis = jedisPool.getResource();
        return jedis;
    }

    public void destroy(){
        jedisPool.destroy();
    }
    public void close(){
        jedisPool.close();
    }
}
