package com.jitv.tv.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class LinuxHardware {
	/**
	 * 功能：内存使用率
	 */
	public static float memoryUsage() {
		Map<String, Object> map = new HashMap<String, Object>();
		InputStreamReader inputs = null;
		BufferedReader buffer = null;
		try {
			inputs = new InputStreamReader(new FileInputStream("/proc/meminfo"));
			buffer = new BufferedReader(inputs);
			String line = "";
			while (true) {
				line = buffer.readLine();
				if (line == null)
					break;
				int beginIndex = 0;
				int endIndex = line.indexOf(":");
				if (endIndex != -1) {
					String key = line.substring(beginIndex, endIndex);
					beginIndex = endIndex + 1;
					endIndex = line.length();
					String memory = line.substring(beginIndex, endIndex);
					String value = memory.replace("kB", "").trim();
					map.put(key, value);
				}
			}

			long memTotal = Long.parseLong(map.get("MemTotal").toString());
			long memFree = Long.parseLong(map.get("MemFree").toString());
			long memused = memTotal - memFree;
			long buffers = Long.parseLong(map.get("Buffers").toString());
			long cached = Long.parseLong(map.get("Cached").toString());
			float usage = (float) (memused - buffers - cached) / memTotal;
			return usage;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				buffer.close();
				inputs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return 0;
	}

	/**
	 * 获取分区的使用占用率
	 * 
	 * @param path
	 * @return
	 */
	public static float getPatitionUsage(String path) {
		File f = new File(path);
		long total = f.getTotalSpace();
		long free = f.getFreeSpace();
		long used = total - free;
		float usage = (float) used / total;
		return usage;
	}

	/**
	 * 获取带宽使使用率
	 * 
	 * @return
	 */
	public static float get() {
		float TotalBandwidth = 1000;
		// System.out.println("开始收集网络带宽使用率");
		float netUsage = 0.0f;
		Process pro1, pro2;
		Runtime r = Runtime.getRuntime();
		try {
			String command = "cat /proc/net/dev";
			// 第一次采集流量数据
			long startTime = System.currentTimeMillis();
			pro1 = r.exec(command);
			BufferedReader in1 = new BufferedReader(new InputStreamReader(pro1.getInputStream()));
			String line = null;
			long inSize1 = 0, outSize1 = 0;
			while ((line = in1.readLine()) != null) {
				line = line.trim();
				if (line.startsWith("eth0")) {
					String[] temp = line.split("\\s+");
					inSize1 = Long.parseLong(temp[1].substring(5)); // Receive bytes,单位为Byte
					outSize1 = Long.parseLong(temp[9]); // Transmit bytes,单位为Byte
					break;
				}
			}
			in1.close();
			pro1.destroy();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				StringWriter sw = new StringWriter();
				e.printStackTrace(new PrintWriter(sw));
//                System.out.println("NetUsage休眠时发生InterruptedException. " + e.getMessage());  
//                System.out.println(sw.toString());  
			}
			// 第二次采集流量数据
			long endTime = System.currentTimeMillis();
			pro2 = r.exec(command);
			BufferedReader in2 = new BufferedReader(new InputStreamReader(pro2.getInputStream()));
			long inSize2 = 0, outSize2 = 0;
			while ((line = in2.readLine()) != null) {
				line = line.trim();
				if (line.startsWith("eth0")) {
					String[] temp = line.split("\\s+");
					inSize2 = Long.parseLong(temp[1].substring(5));
					outSize2 = Long.parseLong(temp[9]);
					break;
				}
			}
			if (inSize1 != 0 && outSize1 != 0 && inSize2 != 0 && outSize2 != 0) {
				float interval = (float) (endTime - startTime) / 1000;
				// 网口传输速度,单位为bps
				float curRate = (float) (inSize2 - inSize1 + outSize2 - outSize1) * 8 / (1000000 * interval);
				netUsage = curRate / TotalBandwidth;
//                System.out.println("本节点网口速度为: " + curRate + "Mbps");  
//                System.out.println("本节点网络带宽使用率为: " + netUsage);  
			}
			in2.close();
			pro2.destroy();
		} catch (IOException e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
//            System.out.println("NetUsage发生InstantiationException. " + e.getMessage());  
//            System.out.println(sw.toString());  
		}
		return netUsage;
	}

	/**
	 * 获取cpu使用情况
	 * 
	 * @return
	 * @throws Exception
	 */
	public static double getcpuUsage() throws Exception {
		double cpuUsed = 0;

		Runtime rt = Runtime.getRuntime();
		Process p = rt.exec("top -b -n 1");// 调用系统的“top"命令

		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String str = null;
			String[] strArray = null;

			while ((str = in.readLine()) != null) {
				int m = 0;

				if (str.indexOf(" R ") != -1) {// 只分析正在运行的进程，top进程本身除外 &&

					strArray = str.split(" ");
					for (String tmp : strArray) {
						if (tmp.trim().length() == 0)
							continue;
						if (++m == 9) {// 第9列为cpu的使用百分比(RedHat

							cpuUsed += Double.parseDouble(tmp);

						}

					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			in.close();
		}
		return cpuUsed;
	}

	/**
	 * 获取磁盘空间大小
	 * 
	 * @return
	 * @throws Exception
	 */
    public static String getDeskUsage() {
    	String Total = "";
    	String Used = "";
    	String result = "";
        try {
            Runtime rt = Runtime.getRuntime();
            Process p = rt.exec("df -hl");// df -hl 查看硬盘空间
            BufferedReader in = null;
            try {
                in = new BufferedReader(new InputStreamReader(
                        p.getInputStream()));
                String str = null;
                String[] strArray = null;
                int line = 0;
                while ((str = in.readLine()) != null) {
                    line++;
                    if (line != 2) {
                        continue;
                    }
                    int m = 0;
                    strArray = str.split(" ");
                    for (String para : strArray) {
                        if (para.trim().length() == 0)
                            continue;
                        ++m;
                        if (para.endsWith("G") || para.endsWith("Gi")) {
                            // 目前的服务器
                            if (m == 2) {
                                Total = para;
                            }
                            if (m == 3) {
                                Used = para;
                            }
                        }
                        if (para.endsWith("%")) {
                            if (m == 5) {
                                result = para;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
