package com.jitv.tv.service.impl;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import com.aspire.commons.HttpClientHelper;
import com.aspire.commons.rpc.RpcContext;
import com.aspire.commons.util.XmlUtil;
import com.jitv.tv.util.DateUtil;
import com.jitv.tv.Ttmertask.FileRead;
import com.jitv.tv.dao.UserDao;
import com.jitv.tv.dto.UserDTO;
import com.jitv.tv.service.HomeLogService;
import com.jitv.tv.service.IpService;
import com.jitv.tv.util.ExcelUtils;
import com.jitv.tv.util.Md5Utils;
import com.jitv.tv.util.PropertiesJitv;
import com.jitv.tv.util.RedisUtil;
import com.jitv.tv.util.StaticClass;
import com.jitv.tv.util.WebUtil;
import net.sf.json.JSONArray;

public class HomeLogServiceImpl implements HomeLogService {
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(HomeLogServiceImpl.class);

	// 家宽日志硬盘路径
	private final static String homeLog = PropertiesJitv.getString("homeLog");

	private UserDao userDao;// 用户相关dao

	private IpService ipService;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setIpService(IpService ipService) {
		this.ipService = ipService;
	}

	public static Object waitObject = new Object();

	@SuppressWarnings("unused")
	private Map<String, Object> getIPOfUserId(String userId, String pubIP) {
		RedisUtil.delete(StaticClass.CITY_KEY + userId);
		RedisUtil.delete(StaticClass.CITY_KEY + userId+"1");
		UserDTO dto = userDao.selUserById(userId);
		// 获取当前用户城市代号
		String city = dto.getCity();
		if ("1".equals(city)) {// 表示广东省
			RedisUtil.set(StaticClass.CITY_KEY + userId + "1", 6000, city);
		}
		List<Map<String, Object>> ips = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		if (null == RedisUtil.get(StaticClass.CITY_KEY + userId)) {
			ips = ipService.sel(city);
			for (int i = 0; i < ips.size(); i++) {
				String ip = (String) ips.get(i).get("ip");
				// ipList.add(ip);
				map.put(ip, "");
			}
		} else {
			map = (Map<String, Object>) RedisUtil.get(StaticClass.CITY_KEY + userId);
		}
		RedisUtil.set(StaticClass.CITY_KEY + userId, 6000, map);
		if (StringUtils.isNotEmpty(pubIP)) {
			RedisUtil.delete(StaticClass.CITY_KEY + userId);
			map.clear();
			map.put(pubIP, "");
			RedisUtil.set(StaticClass.CITY_KEY + userId, 6000, map);
		}
		return map;
	}

	@Override
	public Map<String, Object> getHomeLog(String startIndex, String pageSize, String asSorting, String pubIP,
			String pubPort, String objectiveIP, String objectivePort, String objectiveUrl, String BrasIP,
			String BrasName, String BdbAdmin, String beginDate, String endDate, String userId, String eventid) {// 家宽日志查询
		// 获取当前用户对应的IP
		Map<String, Object> ips = getIPOfUserId(userId, pubIP);
		String screen = pubIP + pubPort + objectiveIP + objectivePort + objectiveUrl + BrasIP + BrasName + BdbAdmin
				+ beginDate + endDate + eventid;
		// 从redis 中获取数据
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		if (null != RedisUtil.get(Md5Utils.getMd5(screen))) {
			String uuid = (String) RedisUtil.get(Md5Utils.getMd5(screen));
			List<Map<String, Object>> list = (List<Map<String, Object>>) RedisUtil.get(uuid);
			if (Integer.parseInt(startIndex) + Integer.parseInt(pageSize) > list.size()) {
				result = list.subList(Integer.parseInt(startIndex), list.size());// 截取list用作前端分页
			} else {
				result = list.subList(Integer.parseInt(startIndex),
						Integer.parseInt(startIndex) + Integer.parseInt(pageSize));// 截取list用作前端分页
			}
			map.put("result", result);
			map.put("totalItems", list.size());
			return map;
		}
		// 1.用户信息查询接口
		// 根据UserId获取当前用户所在城市对应的IP
		// 获取EventID
		String EventID = EventID();
		RedisUtil.set(EventID + "ips", 5000, ips);// 存入redis
		RedisUtil.set(EventID, 6000, userId);// 用户信息存入Redis

		String result_EventID = IF_QUERY(startIndex, pageSize, asSorting, pubIP, pubPort, objectiveIP, objectivePort,
				objectiveUrl, BrasIP, BrasName, BdbAdmin, beginDate, endDate, EventID);
		if (StringUtils.isNotEmpty(result_EventID)) {
			map.put("result", EventID);
			map.put("totalItems", 0);
		} else {
			map.put("result", EventID);
			map.put("totalItems", -1);// 标记没有返回数据
		}
		// 模拟获取了家宽日志发送的请求
		// redHomeLog(EventID);
		return map;

	}

	@Override
	public Map<String, Object> getProvinceLog(String startIndex, String pageSize, String asSorting) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(20);
		for (int i = 0; i < 20; i++) {
			Map<String, Object> map = new HashMap<String, Object>(10);
			map.put("dls", "dls" + i);// 上网账号
			map.put("Bras", "Bras" + i);// Brase名称
			map.put("pubIP", "127.0.0.1");// 公网IP
			map.put("pubPort", "80");// 公网端口
			map.put("objectiveIP", "127.0.0.1");// 目的IP
			map.put("objectivePort", "8080");// 目的端口
			map.put("objectiveUrl", "http://www.baidu.com/");// 目的URL
			map.put("BrasIP", "127.0.0.1");// BrasIP
			map.put("beginDate", "2018年8月5日 17:44:01");// 开始时间
			map.put("endDate", "2019年8月5日 17:44:01");// 结束时间
			list.add(map);
		}
		Map<String, Object> maps = new HashMap<String, Object>();
		if ("0".equals(startIndex)) {
			maps.put("Result", list = list.subList(0, 10));
		} else if ("10".equals(startIndex)) {
			maps.put("Result", list = list.subList(10, list.size()));
		} else {
			maps.put("Result", list);
		}
		maps.put("totalItems", list.size() * 2);
		return maps;
	}

	@Override
	public Map<String, Object> getUrl(String searchValue, String startIndex, String pageSize, String asSorting) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(20);
		for (int i = 0; i < 20; i++) {
			Map<String, Object> maps = new HashMap<String, Object>(10);
			maps.put("dls", "dls" + i);// 家庭宽带上网账号
			maps.put("wlan", "wlan" + i);// wlan上网账号
			maps.put("objectiveUrl", "http://www.baidu.com" + i);// 目的URL
			maps.put("objectiveIP", "127.0.0." + i);// 目的IP
			maps.put("objectivePort", "8080");// 目的端口
			maps.put("BrasIP", "127.0.0.1");// BrasIP
			maps.put("beginDate", "2018年8月5日 17:44:0");// 开始时间
			maps.put("endDate", "2019年8月5日 17:44:0");// 结束时间
			list.add(maps);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if ("0".equals(startIndex)) {
			map.put("Result", list = list.subList(0, 10));
		} else if ("10".equals(startIndex)) {
			map.put("Result", list = list.subList(10, list.size()));
		} else {
			map.put("Result", list);
		}
		map.put("totalItems", list.size() * 2);
		return map;

	}

	@Override
	public Map<String, Object> BatchLog(InputStream is) {
		try {
			List<Map<String, String>> list = ExcelUtils.readXlsx(is);
			//要发送的参数 在list 中
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("exceldate", list);
			map.put("exceltotalItems", list.size());
			// getLog(list);
			// 把excle 数据进行搜索
			return map;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

//	private Map<String, String> getLog(List<Map<String, String>> list) {
//		for (Map<String, String> excel : list) {
//			System.out.println(excel);//上传的excel数据
//			String objectivePort = excel.get("objectivePort");//目的端口
//			String objectivePortURL = excel.get("objectivePortURL");//目的URL
//			String objectiveIP = excel.get("objectiveIP");//目的IP
//			String beginDate = excel.get("beginDate");//开始事件
//			String endDate = excel.get("endDate");//结束时间
//			String pubIP = excel.get("pubIP");//公网IP
//			String pubPort = excel.get("pubPort");//公网端口
//			String dls = excel.get("dls");//上网账号
//			String BrasIP = excel.get("BrasIP");//BrasIP
//			String BrasName = excel.get("BrasName");//Bras名称
//		}
//		return null;
//	}

	@Override
	public Map<String, Object> Agreement(String searchValue, String startIndex, String pageSize) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(20);
		for (int i = 0; i < 20; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("dls", "dls" + i);// 家庭宽带上网账号
			map.put("WLAN", "jetty" + i);// WLAN上网账号
			map.put("agreement", "http" + i);// 协议分析
			map.put("startTime", "2019年1月9日 17:44:0" + i);// 时间
			map.put("endTime", "2019年1月11日 17:44:0" + i);// 时间
			list.add(map);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if ("0".equals(startIndex)) {
			map.put("Result", list = list.subList(0, 10));
		} else if ("10".equals(startIndex)) {
			map.put("Result", list = list.subList(10, list.size()));
		} else {
			map.put("Result", list);
		}
		map.put("totalItems", list.size() * 2);
		return map;
	}

	@Override
	public Map<String, Object> getLog(String startIndex, String pageSize, String exceldate) {
		if ("undefined".equals(exceldate)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("Result", null);
			map.put("totalItems", 0);
			return map;
		}
		String str = exceldate.replaceAll("\\\\", "\"");
		JSONArray json = JSONArray.fromObject(str);
		List<Map<String, Object>> lists = (List<Map<String, Object>>) JSONArray.toCollection(json, Map.class);
		for (Map<String, Object> map : lists) {
			String objectivePort = (String) map.get("objectivePort");// 目的端口
			String objectivePortURL = (String) map.get("objectivePortURL");// 目的URL
			String objectiveIP = (String) map.get("objectiveIP");// 目的IP
			String beginDate = (String) map.get("beginDate");// 开始事件
			String endDate = (String) map.get("endDate");// 结束时间
			String pubIP = (String) map.get("pubIP");// 公网IP
			String pubPort = (String) map.get("pubPort");// 公网端口
			String dls = (String) map.get("dls");// 上网账号
			String BrasIP = (String) map.get("BrasIP");// BrasIP
			String BrasName = (String) map.get("BrasName");// Bras名称
			
//			String eventid = getHomeLog(startIndex, pageSize, "", 
//					pubIP, pubPort, objectiveIP, objectivePortURL,
//					objectivePortURL, BrasIP, BrasName, BdbAdmin, beginDate, endDate, userId, eventid);
		}
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(20);
		for (int i = 0; i < 20; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("dls", "dls" + i);// 上网账号
			map.put("BrasName", "BrasName" + i);// Bras名称
			map.put("pubIP", "127.0.0." + i);// 公网ip
			map.put("pubPort", "8080" + i);// 公网端口
			map.put("objectiveIP", "127.0.0." + i);// 目的IP
			map.put("objectivePort", "8080" + i);// 目的端口
			map.put("objectivePortURL", "www" + i);// 目的URL
			map.put("BrasIP", "127.0.0." + i);// BrasIP
			map.put("beginDate", "2019-01-13 12:12:12" + i);// 开始时间
			map.put("endDate", "2019-01-13 12:12:12" + i);// 结束时间
			list.add(map);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if ("0".equals(startIndex)) {
			map.put("Result", list = list.subList(0, 10));
		} else if ("10".equals(startIndex)) {
			map.put("Result", list = list.subList(10, list.size()));
		} else {
			map.put("Result", list);
		}
		map.put("totalItems", list.size() * 2);
		return map;
	}

	@Override
	public void Excel(String startIndex, String pageSize, String exceldate, RpcContext rc) {
		String str = exceldate.replaceAll("\\\\", "\"");
		JSONArray json = JSONArray.fromObject(str);
		List<Map<String, Object>> lists = (List<Map<String, Object>>) JSONArray.toCollection(json, Map.class);
		for (Map<String, Object> map : lists) {
			String objectivePort = (String) map.get("objectivePort");// 目的端口
			String objectivePortURL = (String) map.get("objectivePortURL");// 目的URL
			String objectiveIP = (String) map.get("objectiveIP");// 目的IP
			String beginDate = (String) map.get("beginDate");// 开始事件
			String endDate = (String) map.get("endDate");// 结束时间
			String pubIP = (String) map.get("pubIP");// 公网IP
			String pubPort = (String) map.get("pubPort");// 公网端口
			String dls = (String) map.get("dls");// 上网账号
			String BrasIP = (String) map.get("BrasIP");// BrasIP
			String BrasName = (String) map.get("BrasName");// Bras名称
		}
		// *********************解析传递过来的参数****************************//
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(10);
		for (int i = 0; i < 10; i++) {// 模拟假数据
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("dls", "dls" + i);// 上网账号
			map.put("BrasName", "BrasName" + i);// Bras名称
			map.put("pubIP", "127.0.0." + i);// 公网ip
			map.put("pubPort", "8080" + i);// 公网端口
			map.put("objectiveIP", "127.0.0." + i);// 目的IP
			map.put("objectivePort", "8080" + i);// 目的端口
			map.put("objectivePortURL", "www" + i);// 目的URL
			map.put("BrasIP", "127.0.0." + i);// BrasIP
			map.put("beginDate", "2006-01-01 00:00:00");// 开始时间
			map.put("endDate", "2019-01-01 12:12:12");// 结束时间
			list.add(map);
		}
		// *********************返回excle******************************************//
		ExcelUtils.downloadExcelLog(list, rc);
	}

	// 家宽日志（用户信息查询接口）
	@Override
	public String IF_QUERY(String startIndex, String pageSize, String asSorting, String pubIP, String pubPort,
			String objectiveIP, String objectivePort, String objectiveUrl, String BrasIP, String BrasName,
			String BdbAdmin, String beginDate, String endDate, String EventID) {
		// 1.组装要发送的xml
		StringBuffer sb = new StringBuffer(1000);
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
		sb.append("<UDRQueryRequest xmlns=\"http://www.w3.org/2001/XMLSchema-instance\">");
		sb.append("<EventID>" + EventID + "</EventID>");// 查询请求的序列号标识，由第三方查询系统生成，格式为yyyy-mm-dd-hh-mi-ss-nnnnn其中nnnnn标识位的序号，由00000起始依次递增
		sb.append("<provID></provID>");
		sb.append("<typeID></typeID>");
		sb.append("<IdcID></IdcID>");
		sb.append("<HouseID></HouseID>");
		sb.append("<SourceIP>" + pubIP + "</SourceIP>");// 用户访问外部网站时使用的公网IP地址（应同时支持IPv4、IPv6地址）
		sb.append("<SourcePort>" + pubPort + "</SourcePort>");// 用户访问外部网站时使用的端口号
		sb.append("<StartTime>" + beginDate + "</StartTime>");// 查询起始时间，格式为yyyy-mm-dd-hh-mi-ss
		sb.append("<EndTime>" + endDate + "</EndTime>");// 查询结束时间，格式为yyyy-mm-dd-hh-mi-ss
		sb.append("<ProtocolType></ProtocolType>");
		sb.append("<DestinationURL>" + objectiveUrl + "</DestinationURL>");// 用户访问的目标网站URL
		sb.append("<SearchType></SearchType>");// 当采用查询条件11（开始时间、结束时间、目的URL）时，填写该字段，如下：1：表示模糊查询；2：表示精确查询。针对其他查询条件，如果查询中带有URL，URL统一默认填2，否则该字段为空。
		sb.append("<DestinationIP>" + objectiveIP + "</DestinationIP>");// 用户访问的目标网站IP地址对于WLAN用户上网记录查询为可选
		sb.append("<DestinationPort>" + objectivePort + "</DestinationPort>");// 用户访问的目标网站端口号
		sb.append("<MSISDN></MSISDN>");// 被查询用户的手机号码对于WLAN/家庭宽带用户上网记录查询为可选
		sb.append("<Account>" + BdbAdmin + "</Account>");// 被查询用户的账号，用于WLAN、家庭宽带 对于移动网用户上网记录查询为可选.
		sb.append("</UDRQueryRequest>");
		logger.info("发送的xml" + sb.toString());
		String url = WebUtil.getServiceRoot() + "jiakelog/api/inter/jiake/IF_QUERY";
		logger.info("发送地址：" + url);
		// 2.发送http请求
		String rsp = HttpClientHelper.getInstance().post(url, sb.toString());
		logger.info("返回的数据" + rsp);
		if (StringUtils.isNotEmpty(rsp)) {
			Map<String, Object> map = XmlUtil.toMap(rsp);
			String result_EventID = (String) map.get("EventID");
			String AcceptResult = (String) map.get("AcceptResult");
			logger.info("解析返回的数据result_EventID=" + result_EventID + "AcceptResult=" + AcceptResult);
			return result_EventID;
		}
		// 模拟返回数据
		// Map portraitMap = XmlUtil.toMap(str.toString());
		return "";
	}

	// 用户信息上报完毕通知响应消息(UDRNotifyResponse)
	@Override
	public Map<String, Object> IF_NOTIFY() {
		return null;
	}

	@Override
	public String redHomeLog(String value) {
		logger.info("接受请求的接口接受到的数据" + value);
		if (StringUtils.isNotEmpty(value)) {
			// 解析map
			Map portraitMap = XmlUtil.toMap(value.toString());
			// 模拟获取文件路径
			// 获取用户对应的IP
			String EventID = (String) portraitMap.get("EventID");
			// String FTPPath = (String) portraitMap.get("FTPPath");// 模拟路径
			List<File> file_list = FileRead.walk(homeLog);// walk
			logger.info("文件夹下所有文件：" + file_list);
			String path = file_list.get(0).getPath();
			logger.info("要解压的文件路径：" + path);
			// 用户拥有的IP 在ipList 里边
			String fileName = unGzipFile(path);
			logger.info("文件路径如下" + fileName);
			File file = new File(homeLog + fileName);
			List<Map<String, Object>> list = txt2String(file, EventID);// 获取数据
			logger.info("解析出的硬盘文件如下：");
			// 模拟key值，真正的key值要从xml中解析
			if (list.size() > 0 && null != list) {
				RedisUtil.set(EventID, 7000, list);
				logger.info("硬盘解析出的数据存入了redis");
			}
			// 删除文件
			FileRead.delAllFile(homeLog);// 删除文件夹下所有文件
			logger.info("删除目录" + homeLog + "下的所有文件");
			// 2.组装要发送给家宽日志系统的xml
			StringBuffer str = new StringBuffer(1000);
			str.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			str.append("<UDRQueryRequest xmlns:xs=\\'http://www.w3.org/2001/XMLSchema\\'>");
			str.append("<complexType>");
			str.append("<sequence>");
			str.append("<EventID>" + EventID + "</EventID>");// 查询请求的序列号标识，由”第三方查询系统”生成，格式为yyyy-mm-dd-hh-mi-ss-nnnnn其中nnnnn标识5位的序号，由00000起始依次递增
			str.append("<NotifyResult>" + 00 + "" + "</NotifyResult>");// 第三方查询系统处理家宽上网日志系统返回的通知消息的结果：00：成功；01：失败；
			str.append("</sequence>");
			str.append("</complexType>");
			str.append("</UDRQueryRequest>");
			logger.info("接受请求的接口返回的数据+" + str.toString());
			return str.toString();
		} else {
			StringBuffer str = new StringBuffer(1000);
			str.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			str.append("<UDRQueryRequest xmlns:xs=\\'http://www.w3.org/2001/XMLSchema\\'>");
			str.append("<complexType>");
			str.append("<sequence>");
			str.append("<EventID></EventID>");// 查询请求的序列号标识，由”第三方查询系统”生成，格式为yyyy-mm-dd-hh-mi-ss-nnnnn其中nnnnn标识5位的序号，由00000起始依次递增
			str.append("<NotifyResult>" + 01 + "" + "</NotifyResult>");// 第三方查询系统处理家宽上网日志系统返回的通知消息的结果：00：成功；01：失败；
			str.append("</sequence>");
			str.append("</complexType>");
			str.append("</UDRQueryRequest>");
			logger.info("接受请求的接口返回的数据+" + str.toString());
			return str.toString();
		}

	}

	/**
	 * 读取txt文件的内容 wxg 2019-02-23
	 * 
	 * @param file 想要读取的文件对象
	 * @return 返回文件内容
	 */
	public static List<Map<String, Object>> txt2String(File file, String EventID) {
		Map<String, Object> map = (Map<String, Object>) RedisUtil.get(EventID + "ips");// 获取ips
		String userId = (String)RedisUtil.get(EventID);
		String city = RedisUtil.get(StaticClass.CITY_KEY + userId + "1") == null ? "" : 
			(String)RedisUtil.get(StaticClass.CITY_KEY + userId + "1");
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		try {
			FileInputStream in = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));
			String s = null;

			while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
				// 组装读取的数据
				String[] arr = s.split("\\|");
				List<String> strlist = Arrays.asList(arr);
				String beginDate = DateUtil.TextDate(strlist.get(0));// 开始时间
				String endDate = DateUtil.TextDate(strlist.get(1));// 结束时间
				String dls = strlist.get(2);// 上网账号
				String pubIP = strlist.get(3);// 公网IP
				boolean state = map.containsKey(pubIP);
				if (!state && "".equals(city)) {//
					continue;
				}
				String pubPort = strlist.get(4);// 公网端口
				String objectiveUrl = strlist.get(5);// 目的URL
				String ProtocolTpye = strlist.get(6);// 用户协议
				String objectiveIP = strlist.get(7);// 目的IP
				String objectivePort = strlist.get(8);// 目的端口
				String ClientMAC = strlist.get(9);// 终端MAC地址
				String ClientIP = strlist.get(10);// 终端私网IP
//            	 String ClientPort = strlist.get(11);//终端私网端口
//            	 String APid = strlist.get(12);//终端设备标识
//            	 String APN = strlist.get(13);//apn
				Map<String, Object> result_map = new HashMap<String, Object>();
				result_map.put("dls", dls);// 上网账号
				result_map.put("Bras", "Bras");// Brase名称
				result_map.put("pubIP", pubIP);// 公网IP
				result_map.put("pubPort", pubPort);// 公网端口
				result_map.put("objectiveIP", objectiveIP);// 目的IP
				result_map.put("objectivePort", objectivePort);// 目的端口
				result_map.put("objectiveUrl", objectiveUrl);// 目的URL
				result_map.put("BrasIP", "BrasIP");// BrasIP
				result_map.put("beginDate", beginDate);// 开始时间
				result_map.put("endDate", endDate);// 结束时间
				list.add(result_map);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static String unGzipFile(String sourcedir) {
		try {
			// 建立gzip压缩文件输入流
			FileInputStream fin = new FileInputStream(sourcedir);
			// 建立gzip解压工作流
			GZIPInputStream gzin = new GZIPInputStream(fin);

			File outdir = new File(homeLog);
			extractFile(gzin, outdir, "homelog");
			gzin.close();
			fin.close();
			return "homelog";
		} catch (Exception ex) {
			System.err.println(ex.toString());
		}
		return "";
	}

	// 将压缩文件解压到硬盘
	private static void extractFile(GZIPInputStream in, File outdir, String name) throws IOException {
		byte[] buffer = new byte[1024];
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(outdir, name)));
		int count = -1;
		while ((count = in.read(buffer)) != -1) {
			out.write(buffer, 0, count);
		}
		out.close();
	}

	// 获取EventID wxg 2019-02-26
	private String EventID() {
		long count = 0;
		if (null != RedisUtil.get("00000")) {
			count = (long) RedisUtil.get("00000");
			count += 1;
		} else {
			RedisUtil.set("00000", -1, count);
		}
		String time = DateUtil.getNowDateString();// 获取当前时间
		String str = String.format("%05d", count);// 格式化数字不够5位数前边补齐0
		RedisUtil.delete("00000");
		RedisUtil.set("00000", -1, count);
		return time + "-" + str;
	}

	// 家宽日志轮询接口
	@Override
	public Map<String, Object> homeLogPolling(String startIndex, String pageSize, String asSorting, String pubIP,
			String pubPort, String objectiveIP, String objectivePort, String objectiveUrl, String BrasIP,
			String BrasName, String BdbAdmin, String beginDate, String endDate, String eventid, String userId) {
		// 从redis 中获取数据
		Map<String, Object> map = new HashMap<String, Object>();
		logger.info("家宽日志轮询接口eventid：+" + eventid);
		if (null == RedisUtil.get(eventid)) {
			map.put("Result", null);
			map.put("totalItems", 0);
		} else {
			String screen = pubIP + pubPort + objectiveIP + objectivePort + objectiveUrl + BrasIP + BrasName + BdbAdmin
					+ beginDate + endDate + eventid;
			RedisUtil.set(Md5Utils.getMd5(screen), 600, eventid);
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			try {
				list = (List<Map<String, Object>>) RedisUtil.get(eventid);
			} catch (Exception e) {
				logger.info("家宽日志轮询接口获取数据异常");
				map.put("Result", null);
				map.put("totalItems", 0);
				return map;
			}

			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>(10);
			if (Integer.parseInt(startIndex) + Integer.parseInt(pageSize) > list.size()) {// 截取长度大于list集合长度
				result = list.subList(Integer.parseInt(startIndex), list.size());// 截取list用作前端分页
			} else {
				result = list.subList(Integer.parseInt(startIndex),
						Integer.parseInt(startIndex) + Integer.parseInt(pageSize));// 截取list用作前端分页
			}
			map.put("Result", result);
			map.put("totalItems", list.size());
		}
		return map;
	}

	// 家宽日志批量导出excel
	@Override
	public void homeLogExcel(String startIndex, String pageSize, String pubIP, String pubPort, String objectiveIP,
			String objectivePort, String objectiveUrl, String BrasIP, String BrasName, String BdbAdmin,
			String beginDate, String endDate, String eventid, RpcContext rc) {
//		if(StringUtils.isNotEmpty(beginDate)) {
//			beginDate = DateUtil.str2Date(beginDate);
//		}
//		if(StringUtils.isNotEmpty(endDate)) {
//			endDate = DateUtil.str2Date(endDate);
//		}
		// 1.获取数据
		String screen = pubIP + pubPort + objectiveIP + objectivePort + objectiveUrl + BrasIP + BrasName + BdbAdmin
				+ beginDate + endDate + eventid;
		if (null == RedisUtil.get(Md5Utils.getMd5(screen))) {
			return;
		}
		String eventId = (String) RedisUtil.get(Md5Utils.getMd5(screen));
		List<Map<String, Object>> list = (List<Map<String, Object>>) RedisUtil.get(eventId);
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>(10);
		if (Integer.parseInt(startIndex) + Integer.parseInt(pageSize) > list.size()) {
			result = list.subList(Integer.parseInt(startIndex), list.size());// 截取list用作前端分页
		} else {
			result = list.subList(Integer.parseInt(startIndex),
					Integer.parseInt(startIndex) + Integer.parseInt(pageSize));// 截取list用作前端分页
		}
		// *********************返回excle******************************************//
		ExcelUtils.downloadExcelHomeLog(result, rc);

	}

}
