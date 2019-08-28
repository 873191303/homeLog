package com.jitv.tv.service.impl;

import com.jitv.tv.util.Message;
import com.jitv.tv.util.ObjectUtil;
import com.jitv.tv.util.RedisUtil;

public class RedisQueue {
	//public static byte[] redisKey = "keyLog".getBytes();

	public void pop() {

		new CreatThread().start();//创建子线程监听redis
	}

	class CreatThread extends Thread {

		@Override
		public void run() {
			
			System.out.println("启动创建线程");
//			while (true) {
//				byte[] bytes = RedisUtil.rpop(redisKey);
//				if (bytes != null) {
//					Message msg;
//					try {
//						msg = (Message) ObjectUtil.bytes2Object(bytes);
//						if (msg != null) {
//							System.out.println(msg.getId() + "----" + msg.getContent());
//						}
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//
//				} else {
//					//System.out.println("睡眠3秒");
//					try {
//						Thread.sleep(3000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//
//			}
		}

	}

}
