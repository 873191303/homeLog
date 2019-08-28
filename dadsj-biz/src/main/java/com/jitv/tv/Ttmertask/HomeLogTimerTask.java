package com.jitv.tv.Ttmertask;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aspire.commons.BeanFactory;
import com.jitv.tv.dao.HostInnerDao;
import com.jitv.tv.dao.HostOuterDao;
import com.jitv.tv.dto.HostDTO;
import com.jitv.tv.service.BrasService;
import com.jitv.tv.util.DateUtil;
import com.jitv.tv.util.PropertiesJitv;

public class HomeLogTimerTask {
	private final static Logger logger = LoggerFactory.getLogger(HomeLogTimerTask.class);
	private final static String BRASUrl = PropertiesJitv.getString("brasUrl");// brasurl

	public static BrasService brasService;// bras业务层注入

	public static HostOuterDao hostOuterDao;

	public static HostInnerDao hostInnerDao;

	public void startTime() {

		new CreatTimeThread().start();// 创建子线程开启定时器
	}

	class CreatTimeThread extends Thread {

		@Override
		public void run() {

			BrasTimerTask();// brase统计定时器
//			HostTimerTask();//host统计定时器
			Trap.TrapTimerTask();//trap(告警相关的定时器)
		}

	}

	// Host统计相关定时器
	public void HostTimerTask() {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				logger.info("》》》》》》》》》》》》》》》》》》》》》》》Host定时器执行24小时执行一次");
				List<File> outer_list = FileRead.walk(BRASUrl + java.io.File.separator + "host" + java.io.File.separator
						+ "outer" + java.io.File.separator);
				List<File> inner_list = FileRead.walk(BRASUrl + java.io.File.separator + "host" + java.io.File.separator
						+ "inner" + java.io.File.separator);
				// 新增之前先删除之前的记录
				if (hostOuterDao == null) {
					hostOuterDao = BeanFactory.getBean("hostOuterDao");
				}
				if (hostInnerDao == null) {
					hostInnerDao = BeanFactory.getBean("hostInnerDao");
				}
				hostOuterDao.delete();// 入库之前先清理数据
				for (File f : outer_list) {
					if (f.toString().contains(".csv")) {
						String[] arr = f.getName().split("\\.");
						// String outFileName = arr[0];
						// String filePath = FileRead.unGzipFile(f.toString(), outFileName);
						// 组装读取的数据
						File file = new File(f.getPath());
						try {
							FileInputStream in = new FileInputStream(file);
							BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));
							String s = null;
							while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
								// 组装读取的数据
								String[] arrs = s.split(",");
								List<String> strlist = Arrays.asList(arrs);
								String time = strlist.get(0);
								String netid = strlist.get(1);
								String usergrpid = strlist.get(2);
								String direction = strlist.get(3);
								String siteid = strlist.get(4);
								String host = strlist.get(5);
								String ipaddr = strlist.get(6);
								String upbyte = strlist.get(7);
								String dnbyte = strlist.get(8);
								String count = strlist.get(9);
								if (hostOuterDao == null) {
									hostOuterDao = BeanFactory.getBean("hostOuterDao");
								}
								try {
									HostDTO dto = new HostDTO();
									dto.setTime(DateUtil.time2Date(Long.parseLong(time)));
									dto.setNetid(Integer.parseInt(netid));
									dto.setUsergrpid(Integer.parseInt(usergrpid));
									dto.setDirection(Integer.parseInt(direction));
									dto.setSiteid(Integer.parseInt(siteid));
									dto.setHost(host);
									dto.setIpaddr(ipaddr);
									dto.setUpbyte(Long.parseLong(upbyte));
									dto.setDnbyte(Long.parseLong(dnbyte));
									dto.setCount(Long.parseLong(count));
									hostOuterDao.addHost(dto);
								} catch (Exception e) {
									continue;
								}
							}
							br.close();
						} catch (Exception e) {
							e.printStackTrace();
						}

					}

				}
				hostInnerDao.delete();// 新增之前先清理表
				for (File f : inner_list) {
					if (f.toString().contains(".csv")) {
						String[] arr = f.getName().split("\\.");
						String outFileName = arr[0];
						// String filePath = FileRead.unGzipFile(f.toString(), outFileName);
						// 组装读取的数据
						File file = new File(f.getPath());
						try {
							FileInputStream in = new FileInputStream(file);
							BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));
							String s = null;
							while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
								// 组装读取的数据
								String[] arrs = s.split(",");
								List<String> strlist = Arrays.asList(arrs);
								String time = strlist.get(0);
								String netid = strlist.get(1);
								String usergrpid = strlist.get(2);
								String direction = strlist.get(3);
								String siteid = strlist.get(4);
								String host = strlist.get(5);
								String ipaddr = strlist.get(6);
								String upbyte = strlist.get(7);
								String dnbyte = strlist.get(8);
								String count = strlist.get(9);
								if (hostInnerDao == null) {
									hostInnerDao = BeanFactory.getBean("hostInnerDao");
								}
								try {
									HostDTO dto = new HostDTO();
									dto.setTime(DateUtil.time2Date(Long.parseLong(time)));
									dto.setNetid(Integer.parseInt(netid));
									dto.setUsergrpid(Integer.parseInt(usergrpid));
									dto.setDirection(Integer.parseInt(direction));
									dto.setSiteid(Integer.parseInt(siteid));
									dto.setHost(host);
									dto.setIpaddr(ipaddr);
									dto.setUpbyte(Long.parseLong(upbyte));
									dto.setDnbyte(Long.parseLong(dnbyte));
									dto.setCount(Long.parseLong(count));
									hostInnerDao.addHost(dto);
								} catch (Exception e) {
									continue;
								}

							}
							br.close();
						} catch (Exception e) {
							e.printStackTrace();
						}

					}

				}
				FileRead.delAllFile(BRASUrl + java.io.File.separator + "host" + java.io.File.separator + "outer");
				FileRead.delAllFile(BRASUrl + java.io.File.separator + "host" + java.io.File.separator + "inner");

			}
		}, DateUtil.BeforeBawn() * 1000, 1000 * 60 * 60 * 24);// 24小时执行一次
	}

	// bras 统计定时器
	public static void BrasTimerTask() {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				logger.info("》》》》》》》》》》》》》》》》》》》》》》》Bras定时器执行5分钟执行一次");
				logger.info("BRAS文件读取路径"+BRASUrl +java.io.File.separator);
				List<File> list = FileRead.walk(BRASUrl + java.io.File.separator);// walk
				for (File f : list) {
					if (f.toString().contains(".gz")) {
						String[] arr = f.getName().split("\\.");
						String outFileName = arr[0];
						String filePath = FileRead.unGzipFile(f.toString(), outFileName);
						// 组装读取的数据
						File file = new File(filePath);
						try {
							FileInputStream in = new FileInputStream(file);
							BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));
							String s = null;
							while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
								// 组装读取的数据
								String[] arrs = s.split("\\|");
								List<String> strlist = Arrays.asList(arrs);
								String ip = strlist.get(0);
								String name = strlist.get(1);
								String[] nameArr = name.split("-");
								String account = strlist.get(2);
								String time = strlist.get(3);
								if (brasService == null) {
									brasService = BeanFactory.getBean("brasService");
								}
								try {
									brasService.addBras(ip, name, account, time,
											BrasCity.map.get(nameArr[nameArr.length - 1]).toString(),
											BrasCity.map.get(nameArr[0]).toString(), "1");

									// 城市相关数据新增
									brasService.addBrasCity(ip, name, account, time,
											BrasCity.map.get(nameArr[nameArr.length - 1]).toString(),
											BrasCity.map.get(nameArr[0]).toString(), "1");
								} catch (Exception e) {
									continue;
								}
							}
							br.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				FileRead.delAllFile(BRASUrl + java.io.File.separator);
			}
		}, 1000, 1000 * 60 * 5);
	}

}
