package com.jitv.tv.util;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecurityUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);
	
	private static Cipher ENCRYPT_CIPHER;
	private static Cipher DECRYPT_CIPHER;

	static{
		String AES = "AES";//加密算法
		String KEY = "abcdefgh12345678";//加密算法用到的key
		
//		SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(), AES);
		
		try {
			KeyGenerator _generator = KeyGenerator.getInstance(AES);
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");  
			secureRandom.setSeed(KEY.getBytes());
			_generator.init(128, secureRandom);
			SecretKey secretKey2 = _generator.generateKey();
			
			ENCRYPT_CIPHER = Cipher.getInstance(AES);
			ENCRYPT_CIPHER.init(Cipher.ENCRYPT_MODE, secretKey2);
			
			DECRYPT_CIPHER = Cipher.getInstance(AES);
			DECRYPT_CIPHER.init(Cipher.DECRYPT_MODE, secretKey2);
		} catch (Exception e) {
			logger.error("加密工具初始化错误!", e);
		}
	}
	
	/**
	 * 加解密
	 * @param bytes
	 * @param flag true:加密 false:解密
	 */
	private static byte[] doFinal(byte[] bytes, boolean flag) throws Exception{
		return (flag ? ENCRYPT_CIPHER : DECRYPT_CIPHER).doFinal(bytes);
	}
	
	/**
	 * 二进制转16进制字符串
	 * @param 二进制
	 * @return 16进制字符串
	 */
	private static String byte2hex(byte[] bs){
		String hex = "";
		for (byte b : bs) {
			String tmp = Integer.toHexString((b & 0xFF));
			hex += tmp.length() == 1 ? "0" + tmp : tmp;
		}
		return hex.toUpperCase();
	}
	
	/**
	 * 16进制转二进制
	 * @param hex 16进制字符串
	 * @return	二进制
	 */
	private static byte[] hex2byte(String hex){
		byte[] h = hex.getBytes();
		if(h.length % 2 != 0){
			throw new IllegalArgumentException("[hex2byte]长度不是偶数!");
		}
		
		byte[] b = new byte[h.length / 2];
		for (int i = 0; i < h.length; i += 2) {
			String s = new String(h, i, 2);
			b[i / 2] = (byte) Integer.parseInt(s, 16);
		}
		
		return b;
	}
	
	/**
	 * 加密 
	 */
	public static String encrypt (String data) throws Exception{
		byte[] bytes = doFinal(data.getBytes(), true);
		return byte2hex(bytes);
	}
	
	/**
	 * 解密 
	 */
	public static String decrypt (String data) throws Exception{
		byte[] bytes = doFinal(hex2byte(data), false);
		return new String(bytes);
	}
}
