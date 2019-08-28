package com.jitv.tv.dto.base;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.aspire.commons.util.StringUtil;


public abstract class AbstractDTO {
	
	public abstract void fromDbMap(Map<String, Object> dbMap);
	public abstract Map<String, Object> toDbMap();
	public static String DATA_FORMAT_STR="yyyy-MM-dd HH:mm:ss";
	protected static final String toString(Map<String, Object> dbMap, String key) {
		key = key.toLowerCase();
		Object o = dbMap.get(key);
		return StringUtil.toString(o);
	}
	public static final Date getNowTime(){
		SimpleDateFormat df = new SimpleDateFormat(DATA_FORMAT_STR);		
		Date b=null;
		try {
			String s = df.format(new Date());
			b = df.parse(s);
		} catch (ParseException e) { 
		}
		return b;
	}
	
	protected static final Integer toInteger(Map<String, Object> dbMap, String key) {
		key = key.toLowerCase();
		Object o = dbMap.get(key);
		
		return o == null ? null : StringUtil.toInteger(o.toString());
	}
	
	protected static final Long toLong(Map<String, Object> dbMap, String key) {
		key = key.toLowerCase();
		Object o = dbMap.get(key);
		
		return o == null ? null : StringUtil.toLong(o.toString());
	}
	
	protected static final Date toDate(Map<String, Object> dbMap, String key){
		key = key.toLowerCase();
		Object o = dbMap.get(key);
		
		Date  back = null;
		if(o!=null){
			back = (Date)o;
			SimpleDateFormat df = new SimpleDateFormat(DATA_FORMAT_STR);			
			Calendar c =Calendar.getInstance();
			c.setTime(back);
			df.setCalendar(c);
			back = df.getCalendar().getTime();
		}
		return back;
	}
	
	protected static final Double toDouble(Map<String, Object> dbMap, String key){
		key = key.toLowerCase();
		Object object = dbMap.get(key);
		return (Double)object;
	}
	protected static final Boolean toBoolean(Map<String, Object> dbMap, String key) {
		key = key.toLowerCase();
		Object o = dbMap.get(key);
		return o == null ? null : (Boolean)o;
	}
	
}
