package com.jitv.tv.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;
import com.jitv.tv.constant.Constant;

/**
 * @author xiaominghui@9ikandian.com
 * @date 2018-1-29 下午7:19:40
 * @describe
 */
public class DateUtil {

	private final static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<>();

	/**
	 * 从当前线程中获取到该线程的simpleDateFormat类
	 * 
	 * @return
	 * @author XiaoMingHui
	 * @date 2018-1-30 上午9:47:48
	 */
	public final static SimpleDateFormat getSimpleDateFormat() {
		SimpleDateFormat simpleDateFormat = threadLocal.get();
		if (simpleDateFormat == null) {
			simpleDateFormat = new SimpleDateFormat();
			threadLocal.set(simpleDateFormat);
		}
		return simpleDateFormat;
	}

	/**
	 * 获取添加或者减去指定毫秒数的时间string字符串
	 * 
	 * @param isInsert
	 * @param time
	 * @return
	 * @author XiaoMingHui
	 * @date 2018-1-30 上午9:48:10
	 */
	public static final String getDateString(long time) {
		return parseString(getDate(time), Constant.DATEFORMAT);
	}

	/**
	 * 获取添加或者减去指定毫秒数的时间date类
	 * 
	 * @param isInsert
	 * @param time
	 * @return
	 * @author XiaoMingHui
	 * @date 2018-1-30 上午9:48:42
	 */
	public static final Date getDate(long time) {
		return new Date(new Date().getTime() + time);
	}

	/**
	 * 获取到当前时间的字符串
	 * 
	 * @return
	 * @author XiaoMingHui
	 * @date 2018-1-30 上午9:48:54
	 */
	public static String getNowDateString() {
		return parseString(new Date(), Constant.DATEFORMAT);
	}
	
    public final static String FORMAT_STRING = "yyyy-MM-dd HH:mm:ss";

    public final static String[] REPLACE_STRING = new String[]{"GMT+0800", "GMT+08:00"};

    public final static String SPLIT_STRING = "(中国标准时间)";
	/**
	 * 
	 * @param dateString
	 * @return
	 */
    public static String str2Date(String dateString) {
    	
        try {
            dateString = dateString.split(Pattern.quote(SPLIT_STRING))[0].replace(REPLACE_STRING[0], REPLACE_STRING[1]);
            SimpleDateFormat sf1 = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss z", Locale.US);
            Date date = sf1.parse(dateString);
            return parseString(date, "yyyy-MM-dd-HH-00-00");
        } catch (Exception e) {
            throw new RuntimeException("时间转化格式错误" + "[dateString=" + dateString + "]" + "[FORMAT_STRING=" + FORMAT_STRING + "]");
        }
    }
    
    public static String  TextDate(String dateString) {
			try {
				Date orderDateStart = new SimpleDateFormat("yyyyMMddHHmmss").parse(dateString);
	            //输出格式
	            String DateStart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(orderDateStart);
	            return DateStart;
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return "";
        }


	/**
	 * 获取到指定格式的当前时间的字符串
	 * 
	 * @param pattern
	 * @return
	 * @author XiaoMingHui
	 * @date 2018-1-30 上午9:49:07
	 */
	public String getNowDateString(String pattern) {
		return parseString(new Date(), pattern);
	}

	/**
	 * 根据指定的date时间类和指定的时间格式，返回时间字符串
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 * @author XiaoMingHui
	 * @date 2018-1-30 上午9:49:31
	 */
	public static String parseString(Date date, String pattern) {
		SimpleDateFormat simpleDateFormat = getSimpleDateFormat();
		simpleDateFormat.applyPattern(pattern);
		return simpleDateFormat.format(date);
	}

	public static Date parseDate(String timeString, String pattern)
			throws ParseException {
		SimpleDateFormat simpleDateFormat = getSimpleDateFormat();
		simpleDateFormat.applyPattern(pattern);
		return simpleDateFormat.parse(timeString);
	}

	public static Date parseDate(String timeString) throws ParseException {
		return parseDate(timeString, Constant.DATEFORMAT);
	}
	
	
	/**
	 * 对比时间大小,
	 * <pre>
	 * date1 > date2 ----> false
	 * date1 < date2 ----> true
	 * <pre>
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * @author XiaoMingHui
	 * @date 2018-3-2 上午11:53:56
	 */
	public static boolean contrast(Date date1, Date date2) {
		return date1.getTime() < date2.getTime();
	}
	//时间戳2date
	public static Date time2Date(Long time) {
	    SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    String d = format.format(time);  
	    Date date = null;
		try {
			date = format.parse(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
//	    System.out.println("Format To String(Date):"+d);  
//	    System.out.println("Format To Date:"+date);  
		return date;
	}
	//当前时间到凌晨的时间
	public static Long BeforeBawn(){
		long now = System.currentTimeMillis();
		try {
	        SimpleDateFormat sdfOne = new SimpleDateFormat("yyyy-MM-dd");
	        long overTime = (now - (sdfOne.parse(sdfOne.format(now)).getTime()))/1000;
	        //当前毫秒数
	        //System.out.println(now);
	        //当前时间  距离当天凌晨  秒数
	       // System.out.println(overTime);
	        //当天凌晨毫秒数
	       // System.out.println(sdfOne.parse(sdfOne.format(now)).getTime());
	        //当天凌晨日期
	       // SimpleDateFormat sdfTwo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        //System.out.print(sdfTwo.format(sdfOne.parse(sdfOne.format(now)).getTime()));
	    	return overTime;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0l;
	
	}


}
