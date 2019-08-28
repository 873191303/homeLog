package com.jitv.tv.Ttmertask;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aspire.commons.BeanFactory;
import com.jitv.tv.dao.AlarmDao;
import com.jitv.tv.dto.AlarmDTO;
import com.jitv.tv.util.PropertiesJitv;

public class Trap {
	private final static Logger logger = LoggerFactory.getLogger(Trap.class);

	private final static String trapUrl = PropertiesJitv.getString("trap");

	public static AlarmDao alarmDao;

	// trap 定时器 五分钟取一次数据

	public static void TrapTimerTask() {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				logger.info("》》》》》》》》》》》》》》》》》》》》》》》Trap(告警相关定时器开始执行)5分钟执行一次");
				logger.info("》》》》》》》》》》》》》》》》》》》》》》》Trap路径："+trapUrl + java.io.File.separator);
				List<File> list = FileRead.walk(trapUrl + java.io.File.separator);// walk
				for (File f : list) {
					if (f.toString().contains(".txt")) {
						// 组装读取的数据
						File file = new File(f.toString());
						try {
							FileInputStream in = new FileInputStream(file);
							BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));
							String s = null;
							StringBuffer sb = new StringBuffer(200);
							AlarmDTO dto = new AlarmDTO();
							// 序号，告警时间，告警设备，告警级别，告警名称，告警描述
							while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
								sb.append(s);
							}
							logger.info("Trap读取如下》》》》》》》》》》》》》》》》》》》》"+sb.toString());
							br.close();
							if (alarmDao == null) {
								alarmDao = BeanFactory.getBean("alarmDao");
							}
							List<String> resultList = strToSz(sb.toString());
							String manufactorid = get_list_value(resultList, "SNMPv2-SMI::enterprises.3470.12.1.1.8.1");
							String recovery = get_list_value(resultList, "SNMPv2-SMI::enterprises.3470.12.1.1.9.1");
							String serviceIP = get_list_value(resultList, "UDP");
							if(1 ==Integer.parseInt(recovery)) {//新报警插入数据库
								String alarmlevel = get_list_value(resultList, "SNMPv2-SMI::enterprises.3470.12.1.1.13.1");
								String title = get_list_value(resultList, "SNMPv2-SMI::enterprises.3470.12.1.1.7.1");
								byte[] by = hexToByteArray(title.replace(" ", ""));
								title = new String(by);
								String describe = get_list_value(resultList, "SNMPv2-SMI::enterprises.3470.12.1.1.30.1");
								byte[] sz = hexToByteArray(describe.replace(" ", ""));
								describe = new String(sz);
								String deivceid = get_list_value(resultList, "SNMPv2-SMI::enterprises.3470.12.1.1.5.1");

								dto.setCol1("未恢复");//是否回复告警
								dto.setServiceip(serviceIP);// 服务器IP
								dto.setTime(new Date());// 告警时间
								dto.setDevicetype(deivceid);// 设备类型
								dto.setTitle(title);// 告警标题
								dto.setManufactorid(manufactorid);// 厂家ID
								dto.setAlarmlevel(alarmlevel);// 告警级别
								dto.setDescribe(describe);// 告警描述
								try {
									List<AlarmDTO> resultDto = alarmDao.select(serviceIP, manufactorid);
									if(null == resultDto) {
										alarmDao.addAlarm(dto);
									}else {
										Map<String, Object> map = new HashMap<String, Object>();
										map.put("serviceip", serviceIP);
										map.put("manufactorid", manufactorid);
										map.put("alarmlevel", alarmlevel);
										map.put("title", title);
										map.put("describe", describe);
										map.put("time", new Date());
										map.put("col1", "未恢复");
										alarmDao.update(map);
									}
									
								} catch (Exception e) {
									continue;
								}
							}else if(2 ==Integer.parseInt(recovery)) {//恢复报警
								Map<String, Object> map = new HashMap<String, Object>();
								map.put("serviceip", serviceIP);
								map.put("manufactorid", manufactorid);
								map.put("time", new Date());
								map.put("col1", "已恢复");
								alarmDao.update(map);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}

				FileRead.delAllFile(trapUrl);

			}
		}, 1000, 1000 * 60 * 5);

	}

	private static List<String> strToSz(String str) {
		String qz = "SNMPv2";
		String[] sz1 = str.split(qz);
		String[] sz2 = sz1[0].split("\n");

		List<String> list = new ArrayList<String>();
		for (String s : sz2) {
			list.add(s);
		}
		for (int i = 1; i < sz1.length; i++) {
			list.add(qz+sz1[i]);
		}
		return list;

	}

	private static String get_list_value(List<String> list, String name) {
		String value = "";
		for (String s : list) {
			if (s.contains(name)) {// 找到行
				if (name.startsWith("UDP")) {
					Pattern r = Pattern.compile("\\[.*?\\]");
					Matcher m = r.matcher(s);
					if (m.find()) {
						value = m.group(0).trim();
						value = value.replace("[", "");
						value = value.replace("]", "");
					}
				} else {
					String[] sz = s.split("\"");
					if(sz.length ==1) { 
						sz = s.split(" "); 
					}
					value = sz[1].trim();
					value = value.replace("\"", "");
				}

			}
		}

		return value;
	}

	public static byte[] hexToByteArray(String inHex) {
		int hexlen = inHex.length();
		byte[] result;
		if (hexlen % 2 == 1) {
			// 奇数
			hexlen++;
			result = new byte[(hexlen / 2)];
			inHex = "0" + inHex;
		} else {
			// 偶数
			result = new byte[(hexlen / 2)];
		}
		int j = 0;
		for (int i = 0; i < hexlen; i += 2) {
			result[j] = hexToByte(inHex.substring(i, i + 2));
			j++;
		}
		return result;
	}

	public static byte hexToByte(String inHex) {
		return (byte) Integer.parseInt(inHex, 16);
	}
}
