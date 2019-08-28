package com.jitv.tv.test;

import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.spy.memcached.compat.log.Logger;
import net.spy.memcached.compat.log.LoggerFactory;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 明星库抓取
 * 
 * @author Administrator
 * 
 */
public class SinastarTest {
	private static final Logger logger = LoggerFactory
			.getLogger(SinastarTest.class);
	private static final String url = "http://ku.ent.sina.com.cn/star/search&page_no=";

	private static void testGrab() {
		logger.info("+++++++抓取地址:" + url);
		try {
			Document document = getConnection(url).get();
			// 获取总页数
			Elements content = document.select("[class=to-page] > span:eq(0)");
			Pattern p = Pattern.compile("[^0-9]");
			Matcher m = p.matcher(content.text());
			String pageText = m.replaceAll("");
			if (StringUtils.isNotBlank(pageText)) {
				int pageCount = Integer.parseInt(pageText);
				testGrabList(pageCount);
			}
		} catch (IOException e) {
			logger.error("++++抓取失败+++++", e);
		}
	}

	private static void testGrabList(int pageCount) {
		for (int i = 1; i <= pageCount; i++) {
			String url1 = url + i;
			try {
				Document document = getConnection(url1).get();
				// 获取搜索结果
				Elements content = document
						.select("[class=tv-list clearfix] > li");
				Iterator<Element> iterator = content.iterator();
				while (iterator.hasNext()) {
					Element next = iterator.next();
					// 明星详情地址
					String starInfoUrl = next.select("a:eq(0)").attr("href");
					// 明星图片地址
					String imgUrl = next.select("img:eq(0)").attr("src");
					// 明星姓名
					String name = next.select(
							"[class=item-title clearfix] > h4").text();
					// 评分
					String score = next.select(
							"[class=item-title clearfix] > span").text();
					// 性别
					String sex = next.select("p:eq(1)").text();
					// 职业
					String profession = next.select("p:eq(2)").text();
					// 国籍
					String nationality = next.select("p:eq(3)").text();
					// 出生日期
					String birthday = next.select("p:eq(4)").text();
					// 星座
					String constellation = next.select("p:eq(5)").text();
					// 身高
					String height = next.select("p:eq(6)").text();
					// 简介
					String context = getStartContext(starInfoUrl);
					logger.info(context);
				}
			} catch (IOException e) {
				logger.error("++++抓取失败+++++", e);
			}
		}
	}

	/**
	 * 获取简介
	 * 
	 * @param starInfoUlr
	 * @return
	 */
	private static String getStartContext(String starInfoUlr) {
		String back = null;
		if (StringUtils.isNotBlank(starInfoUlr)) {
			try {
				Document document = getConnection(starInfoUlr).get();
				// 获取搜索结果
				Elements content = document.select("[class=stars-say]");
				back = content.text();
				back = back.replaceAll("更多>>", "").trim();
			} catch (IOException e) {
				logger.error("++++抓取失败+++++", e);
			}
		}
		return back;
	}

	/**
	 * Jsoup请求url返回请求信息
	 * 
	 * @param urlStr
	 * @return
	 */
	private static Connection getConnection(String urlStr) {
		Connection connection = null;
		connection = Jsoup.connect(urlStr);
		return connection;
	}

	public static void main(String[] args) {
		testGrab();
	}
}
