package com.jitv.tv.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @author 谢炜新  274604559@qq.com
 * @date 2017-9-26 上午10:16:11
 * @describe 素组自定义工具类
 */
public class ListUtils {
	/**
	 * 排序
	 * @param list
	 * @return
	 */
	private List<Map<String,Object>> sortReleatVideo(List<Map<String,Object>> list){
		try {
			Collections.sort(list, new Comparator<Map<String,Object>>() {
				@Override
				public int compare(Map<String, Object> o1,
						Map<String, Object> o2) {
					Long time1 = (Long) o1.get("time");
					Long time2 = (Long) o2.get("time");
					return time2.compareTo(time1);
				}
			}); // 按时间排序
		} catch (Exception e) {
			return list;
		}
		
		return list;
	}
}
