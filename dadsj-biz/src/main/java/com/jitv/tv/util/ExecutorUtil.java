package com.jitv.tv.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.aspire.commons.util.ThreadPoolUtil;

/**
 * 执行,调度工具类
 * @creator  LHL
 * @author www.aspirehld.com
 * @date 2011-6-14 下午12:34:57
 * @version 
 * @since
 */
public class ExecutorUtil {
	private static ScheduledExecutorService schExecutor = ThreadPoolUtil.newScheduledExecutorService(30, "mcloud-backend-schedule");
	
	/**
	 * 线程池
	 */
	private static ExecutorService threadpool = ThreadPoolUtil.newExecutorService(60, 60, 100000, 1800, "mcloud-backend");
	
	/**
	 * 按延迟时长调度一次任务
	 * @param task -  任务
	 * @param delay -  延迟时长,单位:毫秒
	 * @return - 执行期望值
	 */
	public static ScheduledFuture<?> schedule(Runnable task, long delay) {
		return schExecutor.schedule(task, delay, TimeUnit.MILLISECONDS);
	}
	
	/**
	 * 执行任务
	 * @param task - 任务
	 * @return - 执行期望值
	 */
	public static Future<?> submit(Runnable task) {
		return threadpool.submit(task); 
	}
}
