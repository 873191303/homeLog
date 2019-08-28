package com.jitv.tv.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jitv.tv.dao.AlarmDao;
import com.jitv.tv.dao.ErrorLoginDao;
import com.jitv.tv.dto.AlarmDTO;
import com.jitv.tv.dto.ErrorLoginDTO;
import com.jitv.tv.service.SysNetworkService;

import net.sf.json.JSONArray;

public class SysNetworkServiceImpl implements SysNetworkService {

	private final static Logger logger = LoggerFactory.getLogger(SysNetworkServiceImpl.class);

	private ErrorLoginDao errorLoginDao;

	private AlarmDao alarmDao;

	public void setAlarmDao(AlarmDao alarmDao) {
		this.alarmDao = alarmDao;
	}

	public void setErrorLoginDao(ErrorLoginDao errorLoginDao) {
		this.errorLoginDao = errorLoginDao;
	}

	@Override
	public Map<String, Object> getRadius(String startIndex, String pageSize, String searchValue) {
		logger.info(">>>>>>>>>Radius处理信息>>>>搜索参数：" + searchValue);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(20);
		for (int i = 0; i < 20; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("IP", "30.222.1.2" + i);// 服务器IP
			map.put("information", "处理信息1" + i);// 处理信息
			map.put("time", "2019年1月9日 17:44:0" + i);// 时间
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
	public Map<String, Object> UserLog(String startIndex, String pageSize, String searchValue) {
		logger.info(">>>>>>>>>远端用户日志>>>>搜索参数：" + searchValue);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(20);
		for (int i = 0; i < 20; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("IP", "30.222.1.2" + i);// IP
			map.put("userName", "jetty" + i);// 用户名
			map.put("operation", "登陆" + i);// 操作
			map.put("time", "2019年1月9日 17:44:0" + i);// 时间
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
	public Map<String, Object> LocalUserLog(String startIndex, String pageSize, String searchValue) {
		logger.info(">>>>>>>>>本地用户日志>>>>搜索参数：" + searchValue);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(20);
		for (int i = 0; i < 20; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("IP", "30.222.1.2" + i);// IP
			map.put("userName", "tomcat" + i);// 用户名
			map.put("operation", "登陆" + i);// 操作
			map.put("time", "2019年1月9日 17:44:0" + i);// 时间
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
	public Map<String, Object> SysAlarm(String startIndex, String pageSize, String searchValue) {
		logger.info(">>>>>>>>>系统告警>>>>搜索参数：" + searchValue);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(20);
		for (int i = 0; i < 20; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("IP", "30.222.1.2" + i);// 服务器IP
			map.put("cpu", i + "%");// cpu使用率
			map.put("Memory", i + ".5%");// 内存使用率
			map.put("HD", i + ".8%");// 物理内存使用率
			map.put("partition", i + ".3%");// 分区利用率
			map.put("network", i + ".1%");// 网络宽带占用率
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
	public Map<String, Object> BusinessAlarm(String startIndex, String pageSize, String title, String title2,
			String searchValue) {// 业务告警
		List<AlarmDTO> list = alarmDao.selectList(startIndex, pageSize, title, title2, searchValue);
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for (AlarmDTO dto : list) {
			Map<String, Object> map = dto.toDbMap();
			resultList.add(map);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Result", resultList);
		map.put("totalItems", alarmDao.getSum(title, title2, searchValue));
		return map;

//		logger.info(">>>>>>>>>业务告警>>>>搜索参数：" + searchValue);
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(20);
//		for (int i = 0; i < 20; i++) {
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("IP", "30.222.1.2" + i);// 服务器IP
//			map.put("name", "业务" + i);// 业务名称
//			map.put("Reason", "异常使用" + i);// 告警原因
//			map.put("time", "2019年1月9日 17:44:0" + i);// 时间
//			list.add(map);
//		}
//		Map<String, Object> map = new HashMap<String, Object>();
//		if ("0".equals(startIndex)) {
//			map.put("Result", list = list.subList(0, 10));
//		} else if ("10".equals(startIndex)) {
//			map.put("Result", list = list.subList(10, list.size()));
//		} else {
//			map.put("Result", list);
//		}
//		map.put("totalItems", list.size() * 2);
//		return map;
	}

	@Override
	public Map<String, Object> UserAlarm(String startIndex, String pageSize, String searchValue) {
		List<ErrorLoginDTO> listDto = errorLoginDao.selectList(startIndex, pageSize, searchValue);
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>(10);
		for (ErrorLoginDTO dto : listDto) {
			Map<String, Object> map = dto.toDbMap();
			resultList.add(map);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Result", resultList);
		map.put("totalItems", errorLoginDao.getSum(searchValue));
		return map;
	}

	@Override
	public Map<String, Object> DataException(String startIndex, String pageSize, String searchValue) {
		logger.info(">>>>>>>>>数据采集异常>>>>搜索参数：" + searchValue);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(20);
		for (int i = 0; i < 20; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ServiceIP", "30.222.1.2" + i);// 服务器IP
			map.put("Reason", "解析文件失败" + i);// 异常原因
			map.put("time", "2019年1月9日 17:44:0" + i);// 时间
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
	public Map<String, Object> SFTPException(String startIndex, String pageSize, String searchValue) {

		// 上报接口机异常、接收接口机异常、NAT文件接收中断、NAT文件接收延迟、NAT文件过早接收、Radius文件接收中断、Radius文件接收延迟、Radius文件过早接收
		String title1 = "上报接口机异常";
		String title2 = "接收接口机异常";
		String title3 = "NAT文件接收中断";
		String title4 = "NAT文件接收延迟";
		String title5 = "NAT文件过早接收";
		String title6 = "Radius文件接收中断";
		String title7 = "Radius文件接收延迟";
		String title8 = "Radius文件过早接收";
		List<AlarmDTO> list = alarmDao.selectList(startIndex, pageSize, title1, title2, title3, title4, title5, title6,
				title7, title8, searchValue);
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for (AlarmDTO dto : list) {
			Map<String, Object> map = dto.toDbMap();
			resultList.add(map);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Result", resultList);
		map.put("totalItems",
				alarmDao.getSum(title1, title2, title3, title4, title5, title6, title7, title8, searchValue));
		return map;
	}

	@Override
	public Map<String, Object> FileException(String startIndex, String pageSize, String searchValue) {
		// 上报接口机异常、接收接口机异常、NAT文件接收中断、NAT文件接收延迟、NAT文件过早接收、Radius文件接收中断、Radius文件接收延迟、Radius文件过早接收、DPI文件接收中断、DPI文件接收延迟、DPI文件过早接收
		String title1 = "上报接口机异常";
		String title2 = "接收接口机异常";
		String title3 = "NAT文件接收中断";
		String title4 = "NAT文件接收延迟";
		String title5 = "NAT文件过早接收";
		String title6 = "Radius文件接收中断";
		String title7 = "Radius文件接收延迟";
		String title8 = "Radius文件过早接收";
		String title9 = "DPI文件接收中断";
		String title10 = "DPI文件接收延迟";
		String title11 = "DPI文件过早接收";
		List<AlarmDTO> list = alarmDao.selectList(startIndex, pageSize, title1, title2, title3, title4, title5, title6,
				title7, title8, title9, title10, title11, searchValue);
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for (AlarmDTO dto : list) {
			Map<String, Object> map = dto.toDbMap();
			resultList.add(map);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Result", resultList);
		map.put("totalItems", alarmDao.getSum(startIndex, pageSize, title1, title2, title3, title4, title5, title6,
				title7, title8, title9, title10, title11, searchValue));
		return map;

	}

	@Override
	public Map<String, Object> NATMessage(String startIndex, String pageSize, String searchValue) {
		logger.info(">>>>>>>>>NAT摘要信息 >>>>搜索参数：" + searchValue);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(20);
		for (int i = 0; i < 20; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ServiceIP", "30.222.1.2" + i);// 服务器IP
			map.put("message", "处理了" + i + "0.2G信息");// 摘要信息
			map.put("time", "2019年1月9日 17:44:0" + i);// 时间
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
	public Map<String, Object> DPLMessage(String startIndex, String pageSize, String searchValue) {
		logger.info(">>>>>>>>>DPL处理信息>>>>搜索参数：" + searchValue);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(20);
		for (int i = 0; i < 20; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ServiceIP", "30.222.1.2" + i);// 服务器IP
			map.put("message", "处理信息" + i);// 处理信息
			map.put("time", "2019年1月9日 17:44:0" + i);// 时间
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
	public Map<String, Object> AlarmConfigure() {// 告警配置
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("maxthreshold", 20);// 阈值上限
		map.put("minthreshold", 1);// 阈值下限
		map.put("timerange", 1);// 时间范围
		map.put("number", 1);// 发生次数
		map.put("alarmlevel", 1);// 告警级别
		// map.put("alarmlevel", "一级");// 告警级别
		map.put("cpu", 70);// CPU使用率
		map.put("memory", 20);// 内存使用率
		map.put("hd", 10);// 硬盘使用率 物理内存使用率
		map.put("partition", 90);// 分区使用率
		map.put("network", 60);// 网络带宽 网络宽带占用率
		// 项目是否监控
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(20);
		for (int i = 1; i < 5; i++) {
			// vm.main.list1 = [{"id":0,"name":"否"},{"id":1,"name":"是"}],//项目监控
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("projectname", "项目" + i);
			maps.put("monitor", 0);
			list.add(maps);
		}
		map.put("project", list);
		return map;
	}

	@Override
	public Map<String, Object> AlarmConfigureUpdate(String maxthreshold, String minthreshold, String timerange,
			String number, String alarmlevel, String cpu, String memory, String hd, String partition, String network,
			String project) {// 告警配置修改

		if ("undefined".equals(project)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("Result", null);
			map.put("totalItems", 0);
			return map;
		}
		if (StringUtils.isNotEmpty(project)) {
			String str = project.replaceAll("\\\\", "\"");
			JSONArray json = JSONArray.fromObject(str);
			List<Map<String, Object>> lists = (List<Map<String, Object>>) JSONArray.toCollection(json, Map.class);
			for (Map<String, Object> map : lists) {
				//// vm.main.list1 = [{"id":0,"name":"否"},{"id":1,"name":"是"}],//项目监控
				String projectname = (String) map.get("projectname");// 项目名称
				int monitor = map.get("monitor") == null ? 0 : (Integer) map.get("monitor");// 是否监控1表示监控0表示不监控
				System.out.println("项目名称：" + projectname + " 是否监控：" + monitor);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "ok");
		return map;
	}

	public String executeLinuxCmd(String cmd) {
		System.out.println("got cmd job : " + cmd);
		Runtime run = Runtime.getRuntime();
		try {
			Process process = run.exec(cmd);
			InputStream in = process.getInputStream();
			BufferedReader bs = new BufferedReader(new InputStreamReader(in));
			// System.out.println("[check] now size \n"+bs.readLine());
			StringBuffer out = new StringBuffer();
			byte[] b = new byte[8192];
			for (int n; (n = in.read(b)) != -1;) {
				out.append(new String(b, 0, n));
			}
			String result = out.toString();
			System.out.println("job result [" + result + "]");
			logger.info("job result============================================================");
			logger.info(result);

			logger.info("job end===============================================================");
			in.close();
			// process.waitFor();
			process.destroy();
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Map<String, Object> alarm(String startIndex, String pageSize, String title, String searchValue) {
		List<AlarmDTO> list = alarmDao.selectList(startIndex, pageSize, title, searchValue);
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for (AlarmDTO dto : list) {
			Map<String, Object> map = dto.toDbMap();
			resultList.add(map);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Result", resultList);
		map.put("totalItems", alarmDao.getSum(title, searchValue));
		return map;
	}

}
