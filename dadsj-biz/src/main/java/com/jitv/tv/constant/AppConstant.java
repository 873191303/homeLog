package com.jitv.tv.constant;
/**
 * @author 谢炜新  274604559@qq.com
 * @date 2017-9-14 下午3:33:10
 * @describe 这个程序员很懒，什么都没留下
 */
public interface AppConstant {
	/**
	 * app下载并发数量
	 */
	String REDIS_DOWNLOAD_NUM_KEY = "REDIS_DOWNLOAD_NUM_KEY";
	
	/** 
	 * 讯码 更新接口
	 */
	String XUNMA_GET_UPDATE_URL = "http://vdl.dsk.vsoontech.com/v1/branddsk/phoneHelper";
	
	/**
	 * 电视端连接状态
	 */
	String TV_CONNECT_STATE_ = "TV_CONNECT_STATE_";
	
	/***
	 * 第一梯队 等候队列
	 */
	String FIRST_DOWNLOAD_WAITING_QUEUE = "FIRST_DOWNLOAD_WAITING_QUEUE";
	
	/***
	 * 第二梯队 等候队列
	 */
	String SECOND_DOWNLOAD_WAITING_QUEUE = "SECOND_DOWNLOAD_WAITING_QUEUE";
	
	/***
	 * 第三梯队 等候队列
	 */
	String THIRD_DOWNLOAD_WAITING_QUEUE = "THIRD_DOWNLOAD_WAITING_QUEUE";
}
