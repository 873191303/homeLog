package com.jitv.tv.test;

/**
 * @author xiaominghui@9ikandian.com
 * @date 2017-9-22 下午4:26:35
 * @describe
 */
public class ThreadTwoTest implements Runnable {

	private String name = null;

	public ThreadTwoTest(String name3) {
		name = name3;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(name + ":输出结果...");
		}
	}
}
