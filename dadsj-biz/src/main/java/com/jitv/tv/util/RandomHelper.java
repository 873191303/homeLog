package com.jitv.tv.util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomHelper {

	private final static int delta = 0x9fa5 - 0x4e00;
	private final static Random random = new Random();
	
	public static final String chinese(int size){
		StringBuilder builder= new StringBuilder(size);
		for (int i = 0; i < size; i++) {
			char c = (char)(0x4e00 + random.nextInt(delta));
			builder.append(c);
		}
		
		return builder.toString();
	}
	
	/**
	 * 随机数字 
	 */
	public static final String numberic(int size){
		return RandomStringUtils.randomNumeric(size);
	}
	
	/**
	 * 随机字母+数字组合
	 */
	public static final String alphanumeric(int size){
		return RandomStringUtils.randomAlphanumeric(size);
	}
	
	public static final String getStringRandom(int length) {
		String val = "";
		Random random = new Random();

		// 参数length，表示生成几位随机数
		for (int i = 0; i < length; i++) {

			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			// 输出字母还是数字
			if ("char".equalsIgnoreCase(charOrNum)) {
				// 输出是大写字母还是小写字母
				int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
				val += (char) (random.nextInt(26) + temp);
			} else if ("num".equalsIgnoreCase(charOrNum)) {
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val;
	} 
	
	/**
	 * 获得一组整形数组
	 * @param count
	 * @param max
	 * @return
	 */
	public static int[] getRandomNums(int count,int max){
		int nums[] = new int[count]; 
		Set<Integer> set = new HashSet<>();
		for(int i = 0; i < count; ){
			int r = random.nextInt(max);
			if(!set.contains(r)){
				nums[i] = r;
				set.add(r);
				i++;
			}
		}
		return nums;
	}
	
	public static Random getRandomInstance(){
		return random;
	}
}
