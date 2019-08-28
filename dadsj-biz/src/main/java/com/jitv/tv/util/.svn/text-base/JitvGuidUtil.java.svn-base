package com.jitv.tv.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;

import com.aspire.commons.util.JsonUtil;
import com.jitv.tv.constant.Constant;

 
public class JitvGuidUtil {
	
	private static final Random random = new Random();
	
	private final static Logger logger = LoggerFactory.getLogger(JitvGuidUtil.class);
	
	public static String getGuid() {
		String back = "";
		UUID uuid = UUID.randomUUID();
		back = uuid.toString();
		back = back.toUpperCase();
		back = back.replaceAll("-", "");
		return back;
	}

	public static final String toDate(Date time) {
		String back = "";
		if (time != null) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			Calendar c = Calendar.getInstance();
			c.setTime(time);
			df.setCalendar(c);
			back = df.format(time);
		}
		return back;
	}

	public static final String toDate(String timeStr) {
		Date time = null;
		try {
			SimpleDateFormat sdf1 = new SimpleDateFormat(
					"EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
			time = sdf1.parse(timeStr);
		} catch (ParseException e) { 
			e.printStackTrace();
		}
		return toDate(time);
	}

	public static String getBASE64(byte[] b) {
		String s = null;
		if (b != null) {
			s = new sun.misc.BASE64Encoder().encode(b);
		}
		return s;
	}
	public static String encode(final byte[] bytes) {  
        return new String(Base64.encodeBase64(bytes));  
    }
	public static byte[] decode(final byte[] bytes) {  
        return Base64.decodeBase64(bytes);  
    }  
	public static byte[] getFromBASE64(String s) {
		byte[] b = null;
		if (s != null) {
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				b = decoder.decodeBuffer(s);
				return b;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return b;
	}
	public static String getZhongWen(String str){ 
		return str.replaceAll("[^\u4E00-\u9FA5]","");
	}
	
	/**
	 * 根据输入的页数i值，翻页截取list的数据
	 * 时间list翻页
	 * @param l 要翻页的list
	 * @param index 第几页(从0开始)
	 * @param pageSum 每页显示几条
	 * @return list
	 * @author XiaoMingHui
	 * @date 2017-7-20 下午4:06:56
	 */
	public final static List<Object> subList(List<Object> l, int index,
			int pageSum) {
		int size = l.size();
		// 每一页显示多少数据
		if (pageSum < 1) {
			return null;
		} else if (size < pageSum) {
			return l;
			/*
			 * 当输入的页数，超过数据最大页数时，循环回去第一页
			 */
		} else if ((index + 1) * pageSum >= size
				&& (index + 1) * pageSum - size >= pageSum) {
			int pagination = size / pageSum;
			/*
			 * 根据list长度，获取到按照输入的一页的总数获取到的实际页数
			 */
			int r = size % pageSum != 0 ? pagination + 1 : pagination;
			int g = (index % r) * pageSum;
			int gs = g + pageSum;
			if (gs < size) {
				return l.subList(g, gs);
			} else {
				return l.subList(g, size);
			}
		} else {
			int fromIndex = index * pageSum;
			if (fromIndex + pageSum < size) {
				return l.subList(fromIndex, fromIndex + pageSum);
			} else {
				return l.subList(fromIndex, size);
			}
		}
	}
	
	public static StringToDateUtil uu = new StringToDateUtil();
	
	/**
	 * 时间条件筛选。根据 type 判断要执行的代码
	 * @param type
	 * @param objects
	 * @param sd
	 * @param ed
	 * @return
	 * @author XiaoMingHui
	 * @date 2017-7-20 下午4:08:00
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public static final Map<String, Object> timeContrast(int type, List<Object> objects,
			Date sd, Date ed) {
		// 定义总数
		int i = 0;
		// 定义数据；list
		List<Object> objs = new ArrayList<Object>();
		// 定义返回的map
		Map<String, Object> map = new HashMap<String, Object>();

		Object o;
		for (Object object : objects) {
			Map<String, Object> map2 = (Map<String, Object>) object;
			// 获取数据中的时间json
			String times = (String) map2.get("times");
			if (!"".equals(times)  && times != null) {
				// json转换
				List<Map<String, Object>> maps = JsonUtil.toBean(times,
						List.class);
				Date sd2 = new Date();
				Date ed2 = new Date();
				// 遍历对比
				for (Map<String, Object> map3 : maps) {
					// 获取数据中的开始时间
					sd2 = uu.toDate((String) map3.get("startTime"));
					// 获取数据中的结束时间
					ed2 = uu.toDate((String) map3.get("endTime"));
					// 对比，有就执行相对应type的的代码。
					if (sd == null) {
						if (dateContrast(sd2, ed) && dateContrast(ed, ed2)) {
							o = type == 0 ? i++ : objs.add(object);
							break;
						}
					} else if (ed == null) {
						if (dateContrast(sd2, sd) && dateContrast(sd, ed2)) {
							o = type == 0 ? i++ : objs.add(object);
							break;
						}
					} else {
						if (dateContrast(sd2, ed) && dateContrast(sd, ed2)) {
							o = type == 0 ? i++ : objs.add(object);
							break;
						}
					}
				}
			} else {
				o = type == 0 ? i++ : objs.add(object);
			}
		}
		o = type == 0 ? map.put("sum", i) : map.put("list", objs);
		return map;
	}
	

	/**时间对比。 d1 大于等于 d2， 真。 不然就是假
	 * @param d1
	 * @param d2
	 * @return
	 * @author XiaoMingHui
	 * @date 2017-7-20 下午6:38:53
	 */
	public static boolean dateContrast(Date d1, Date d2) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		int result = c2.compareTo(c1);
		if (result == 0){
			return true;
		}
		else if (result < 0){
			return false;
		}
		else{
			return true;
		}
	}
	
	
	/**
	 * 返回一个随机数
	 * 
	 * @param max
	 * @param min
	 * @return
	 * @author XiaoMingHui
	 * @date 2017-8-2 下午4:12:15
	 */
	public static final int getRandom(int min, int max) {
		return random.nextInt(max) % (max - min + 1) + min;
	}
	
	/**
	 * List分页
	 * @param pageIndex  页数 从1开始
	 * @param pageSum	  每页大小
	 * @param list       分页的集合
	 * @return           分页之后的集合
	 * @author LiuJunCai
	 * @date 2017-9-13 上午11:53:40
	 */
	public static List<Object> fenYe(List<Object> list, int pageIndex, int pageSum) {
		
		List<Object> subList = new ArrayList<>();
		
		int totalCount = list.size(); // list长度  

		int pageCount = 0;   // 总页数  

		int m = totalCount % pageSum; // 集合长度 % 每页大小   

		if (m > 0) { // 如果大于0      则 pageCount = 集合长度 / 每页大小 + 1                 
			pageCount = totalCount / pageSum + 1;
		} else {    // 如果小于等于0 则 pageCount = 集合长度 / 每页大小                                           
			pageCount = totalCount / pageSum;                 
		}
		
		if(pageIndex <=  pageCount){
			if (m <= 0) {       // pageSum >= totalCount  
				subList = list.subList((pageIndex - 1) * pageSum, pageSum * (pageIndex));
			} else {
				if (pageIndex == pageCount) {       // 最后一页
					subList = list.subList((pageIndex - 1) * pageSum, totalCount);
				} else if(pageIndex < pageCount){  // 不是最后一页
					subList = list.subList((pageIndex - 1) * pageSum, pageSum * (pageIndex));
				}
			}
			return subList;
		}else{
			return new ArrayList<>();
		}
		
	}
	
	public static boolean isTestDevice(String deviceId){
		if("86586302446831138BC1AC76812".equals(deviceId)){
			return true;
		}
//		if("822678180545260847303051FD3".equals(deviceId)){
//			return true;
//		}
		if("866500027346669000000000000".equals(deviceId)){
			return true;
		}
		if("864783023516657AA3800045DB1".equals(deviceId)){
			return true;
		}
		if("358584051765635F025B7799359".equals(deviceId)){
			return true;
		}
		if("866500027346669000000000000".equals(deviceId)){
			return true;
		}
		if("86788602416157618DC56F065C8".equals(deviceId)){
			return true;
		}
		if("2C839A86FB4C4FE4BA9EF5D318BF2902".equals(deviceId)){
			return true;
		}
		if("8688530273640852C5BB844DA8A".equals(deviceId)){
			return true;
		}
		if("359608067522505020000000000".equals(deviceId)){
			return true;
		}
		if("868403020808488000000000000".equals(deviceId)){
			return true;
		}
		if("861065030403159000000000000".equals(deviceId)){  
			return true;
		}
		if("860709030281476000000000000".equals(deviceId)){
			return true;
		}
		if("35362607861479000000000000".equals(deviceId)){
			return true;
		}
		if("86588002021461480618F61D817".equals(deviceId)){
			return true;
		}
		if("860670023043846ACF7F3AB0A63".equals(deviceId)){
			return true;
		}
		if("357523052099058D022BE3E1079".equals(deviceId)){
			return true;
		}
//		if("96b2934e10b046359578e541d3634533".equals(deviceId)){
//			return true;
//		}
		if("FCE0560E3E6145EFAD3A30B0B8F5597D".equals(deviceId)){
			return true;
		}
		if("38424ec716ed457e8d33c5c9b532c4d0".equals(deviceId)){
			return true;
		}
		return false;
	}
	
	/**
	 * 公司设备的mac
	 * @param deviceId
	 * @return
	 */
	public static boolean isTestMac(String mac){
		if("74:ff:4c:30:f8:24".equals(mac)){
			return true;
		}
		if("90:97:d5:90:a5:d0".equals(mac)){
			return true;
		}
		if("18:82:19:dd:96:0a".equals(mac)){
			return true;
		}
		if("02:00:00:00:00:00".equals(mac)){
			return true;
		}
		if("00:9e:c8:6e:b5:af".equals(mac)){
			return true;
		}
		if("00:9e:c8:40:20:35".equals(mac)){
			return true;
		}
		if("d8:47:10:c2:f7:3a".equals(mac)){
			return true;
		}
		if("0c:91:60:8b:92:71".equals(mac)){
			return true;
		}
		if("5c:ff:ff:92:52:94".equals(mac)){
			return true;
		}
		if("5c:ff:ff:e1:c6:f6".equals(mac)){
			return true;
		}
		if("00:9e:c8:40:20:35".equals(mac)){
			return true;
		}
		if("b8:fc:9a:0c:7d:a5".equals(mac)){
			return true;
		}
		return false;
	}
	
	/**
	 * 将h5自带的时间标签的时间字符串转换成为date类型,
	 * 2017-10-04T00:00
	 * 
	 * @param time
	 * @return
	 * @author XiaoMingHui
	 * @date 2018-1-16 下午2:43:39
	 */
	public static final Date h5DateToDate(String time) {
		if (StringUtils.isNotBlank(time)) {
			time = time.replace("T", " ");
			try {
				if (time.length() > 17) {
					return DateUtils.parseDate(time, Constant.DATEFORMAT);
				} else {
					return DateUtils.parseDate(time, Constant.DATEFORMATTHREE);
				}
			} catch (ParseException e) {
				logger.error("parseDate error", e);
			}
		}
		return null;
	}
	
	/**
	 * 讲时间字符串转换为h5可以显示的字符串
	 * 
	 * @param date
	 * @return
	 * @author XiaoMingHui
	 * @date 2017-11-1 下午5:56:54
	 */
	public static final String strDateToH5Date(String date) {
		return date.replace(" ", "T");
	}
	
	/**
	 * 讲date类型的数据，转换为在h5标签下展示的string类型
	 * 
	 * @param time
	 * @return
	 * @author XiaoMingHui
	 * @date 2017-10-9 下午6:14:38
	 */
	public static final String h5DateToDate(Date time) {
		if (time != null) {
			String date = new SimpleDateFormat(Constant.DATEFORMAT)
					.format(time);
			return date.replaceAll(" ", "T");
		} else {
			return null;
		}
	}
	
}