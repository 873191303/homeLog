package com.jitv.tv.util;

import redis.clients.jedis.Jedis;

import java.io.*;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Administrator on 2016/12/21.
 */
public class RedisUtil {
	private RedisUtil() {
	};

	public static Jedis getJedis() {
		Jedis jedis = null;
		if (jedis == null) {
			jedis = JedisPoolManager.getManager().getResource();
		}
		return jedis;
	}

	/**
	 *
	 * @param key
	 * @param object
	 * @param time   当time不大于零时，不设置生存时间，即为一直存在，时间单位为秒
	 */
	public static void set(String key, int time, Object object) {
		Jedis jedis = JedisPoolManager.getManager().getResource();
		try {
			if (object != null) {
				if (time > 0) {
					jedis.setex(key.getBytes(), time, serialize(object));
				} else {
					jedis.set(key.getBytes(), serialize(object));
				}
			}
		} finally {
			jedis.close();
		}

	}

	/**
	 * 存储REDIS队列 顺序存储
	 * 
	 * @param key   reids键名
	 * @param value 键值
	 */
	public static void lpush(byte[] key, byte[] value) {

		Jedis jedis = JedisPoolManager.getManager().getResource();
		try {
			jedis.lpush(key, value);
		} catch (Exception e) {
			// 释放redis对象
			e.printStackTrace();
		} finally {
			// 返还到连接池
			jedis.close();
		}
	}

	/**
	 * 获取队列数据
	 * 
	 * @param key 键名
	 * @return
	 */
	public static byte[] rpop(byte[] key) {

		byte[] bytes = null;
		Jedis jedis = JedisPoolManager.getManager().getResource();
		try {
			bytes = jedis.rpop(key);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			// 返还到连接池
			jedis.close();
		}
		return bytes;
	}

	/**
	 * 设置有效时长
	 * 
	 * @param key
	 * @param mills
	 */
	public static void expire(String key, int mills) {
		Jedis jedis = JedisPoolManager.getManager().getResource();
		try {
			jedis.expire(key, mills);
		} finally {
			jedis.close();
		}
	}

	public static Object get(String key) {
		Jedis jedis = JedisPoolManager.getManager().getResource();
		try {
			byte[] bytes = jedis.get(key.getBytes());
			if (bytes == null) {
				return null;
			}
			Object object = unSerialize(bytes);
			return object;
		} finally {
			jedis.close();
		}

	}

	/***
	 * 删除指定缓存
	 * 
	 * @param key
	 */
	public static long delete(String key) {
		Jedis jedis = JedisPoolManager.getManager().getResource();
		try {
			long l = jedis.del(key.getBytes());
			return l;
		} finally {
			jedis.close();
		}
	}

	/**
	 * 根据Key前缀批量删除
	 * 
	 * @param key
	 * @return
	 */
	public static long deleteBatch(String key) {
		Jedis jedis = JedisPoolManager.getManager().getResource();
		Set<String> set = jedis.keys(key + "*"); // 返回前缀为 key 的所有主键
		Iterator<String> it = set.iterator();
		long num = 0L;
		try {
			while (it.hasNext()) {
				/**
				 * 遍历删除
				 */
				String keyStr = it.next();
				jedis.del(keyStr);
				num++;
			}
			return num;
		} finally {
			jedis.close();
		}

	}

	/**
	 * 获取 以 key 为前缀的Key组合
	 * 
	 * @param key
	 * @return
	 */
	public static Set<String> getKeys(String key) {
		Jedis jedis = JedisPoolManager.getManager().getResource();
		try {
			Set<String> set = jedis.keys(key + "*"); // 返回前缀为 key 的所有主键
			return set;
		} finally {
			jedis.close();
		}
	}

	/**
	 * 序列化对象
	 * 
	 * @param object
	 * @return
	 */
	public static byte[] serialize(Object object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		baos = new ByteArrayOutputStream();
		try {
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			byte[] bytes = baos.toByteArray();
			baos.close();
			return bytes;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 反序列化对象
	 * 
	 * @param bytes
	 * @return
	 */
	public static Object unSerialize(byte[] bytes) {
		ByteArrayInputStream bais = null;
		bais = new ByteArrayInputStream(bytes);
		try {
			ObjectInputStream ois = new ObjectInputStream(bais);
			Object object = ois.readObject();
			ois.close();
			return object;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
