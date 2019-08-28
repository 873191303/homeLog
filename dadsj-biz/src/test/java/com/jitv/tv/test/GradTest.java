package com.jitv.tv.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import com.aspire.commons.util.JsonUtil;

/**
 * @author xiaominghui@9ikandian.com
 * @date 2017-9-11 下午12:13:22
 * @describe
 */
public class GradTest {

	/**
	 * 搜索的连接--https://www.douban.com/search?q=
	 */
	private static final String douban = "https://www.douban.com/search?q=";

	/**
	 * 搜索页面的来源列表的css路径 .result .content .title
	 */
	private static final String cssQuery = ".result .content .title";

	/**
	 * 获取到视频类型的css路径--h3 span:first-child
	 */
	private static final String cssQuery2 = "h3 span:first-child";

	/**
	 * 视频类型名称--[电视剧]
	 */
	private static final String vType1 = "[电视剧]";

	/**
	 * 视频类型名称--[电影]
	 */
	private static final String vType2 = "[电影]";

	/**
	 * 简介的css路径--- .rating-info .subject-cast
	 */
	private static final String infoCssPath = ".rating-info .subject-cast";

	/**
	 * 查询搜索结果中保存视频ID的a便签的css路径 h3 a
	 */
	private static final String urlCss = "h3 a";

	/**
	 * 点击事件 onclick
	 */
	private static final String onclick = "onclick";

	/**
	 * 播放来源的a的css路径 -- .page .card section ul li a
	 */
	private static final String aCssPath = ".page .card section ul li a";

	/**
	 * 点击事件中，保存ID的key值 sid
	 */
	private static final String idKey = "sid";

	/**
	 * header头文件的name---User-Agent
	 */
	private static final String headerName = "User-Agent";

	/**
	 * header头文件的值
	 * <p>
	 * Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X)
	 * AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143
	 * Safari/601.1
	 * </p>
	 */
	private static final String headerValue = "Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1";

	/**
	 * a标签的href属性name---href
	 */
	private static final String href = "href";

	/**
	 * 最终获取到的播放地址的css路径-- span:first-child
	 */
	private static final String souUrl = "span:first-child";

	/**
	 * 来源地址 url
	 */
	private static final String sourceUrl = "url";

	/**
	 * 来源名称 name
	 */
	private static final String sourceName = "name";

	/**
	 * 来源页面的url拼接前缀 https://m.douban.com/movie/subject/
	 */
	private static final String url1 = "https://m.douban.com/movie/subject/";

	/**
	 * 来源页面的url拼接后缀 /vendors?from=subject
	 */
	private static final String url2 = "/vendors?from=subject";
	
	/**
	 * video 表中的电影字段type ，电视电影
	 */
	private static final String movie = "movie";
	
	/**
	 * video 表中的电影字段type，电视剧
	 */
	private static final String TV = "tv";
	
	/**
	 * 来源集合的key
	 */
	String sourceListKey = "videoSourceList";
	
	/**
	 * id
	 */
	String videoIdKey = "id";

	@Test
	public void testOne() throws IOException {

		Map<String, Object> videoList = getVideoList(
				"人民的名义", 
				"李路",
				"陆毅,张丰毅,吴刚,许亚军,张志坚,柯蓝,胡静,张凯丽,赵子琪,白志迪,李建义,高亚麟,丁海峰,冯雷,李光复,张晞临,徐光宇,陶慧敏,黄俊鹏,阚犇犇,唐菀,岳秀清,许文广,李威,施大生,侯勇,王丽云", 
				"2017", 
				"tv");

		System.out.println(videoList);
	}

	/**
	 * 
	 * @param seek
	 * @param director
	 * @param actor
	 * @param time
	 * @param type TODO
	 * @return
	 * @author XiaoMingHui
	 * @throws IOException
	 * @date 2017-9-12 上午9:55:19
	 */
	public Map<String, Object> getVideoList(String seek, String director,
			String actor, String time, String type) throws IOException {

		// 搜索页面下的结果列表HTML页面
		Document document = Jsoup.connect(douban + seek).get();

		Iterator<Element> it = document.select(cssQuery).iterator();

		while (it.hasNext()) {
			Element title = it.next();

			// 检查是否符合条件
			if (checkFiltration(title, type, director, actor, time, seek)) 
				continue;

			String videoIdJson = title.select(urlCss).attr(onclick);

			// 指定位置截取json
			videoIdJson = videoIdJson.substring(13, videoIdJson.length() - 1);

			Map<String, Object> map = JsonUtil.toBean(videoIdJson, Map.class);
			
			String id = map.get(idKey).toString();
			
			// 拼接成url再直接请求
			Connection con = Jsoup.connect(url1 + id + url2);
			
			con.header(headerName, headerValue);
			
			Map<String, Object> vMap = new HashMap<>();
			
			List<Map<String, Object>> maps = new ArrayList<>();

			// 来源的a标签
			Iterator<Element> it2 = con.get().select(aCssPath).iterator();
			while (it2.hasNext()) {
				Element alabel = it2.next();

				Map<String, Object> videoMap = new HashMap<>();
				// 来源播放地址
				videoMap.put(sourceUrl, alabel.attr(href));
				// 来源name
				videoMap.put(sourceName, alabel.select(souUrl).html());

				maps.add(videoMap);
			}
			vMap.put(sourceListKey, maps);
			vMap.put(videoIdKey, id);
			return vMap;
		}
		return new HashMap<>();
	}
	
	/**
	 * 过滤影片，只有在条件符合的情况下，才返回false，不然一律返回true
	 * 
	 * @param title
	 * @param type
	 * @param director
	 * @param actor
	 * @param time
	 * @param seek
	 * @return
	 * @author XiaoMingHui
	 * @date 2017-9-12 下午1:10:19
	 */
	private boolean checkFiltration(Element title, String type,
			String director, String actor, String time, String seek) {
		
		String videoType = title.select(cssQuery2).html();

		if (!vType1.equals(videoType) && !vType2.equals(videoType))
			return true;

		// 区分电影和电视剧的类型，进行过滤
		if (movie.equals(type) || TV.equals(type))
			if (!(movie.equals(type) ? vType2 : vType1).equals(videoType))
				return true;

		String[] infos = title.select(infoCssPath).html().split("/");

		// 导演过滤
		if (StringUtils.isNotBlank(director)) {
			if (!"暂无".equals(director) 
					&& !"无".equals(director) 
					&& !"未知".equals(director))
				if (!director.contains(infos[1].trim()))
					return true;
		}
		
		// 主演过滤
		if (StringUtils.isNotBlank(actor)) {
			if (!"暂无".equals(actor) 
					&& !"无".equals(actor)
					&& !"未知".equals(actor))
				if (!actor.contains(infos[2].trim()))
					return true;
		}
		// 年代过滤
		if (StringUtils.isNotBlank(time)) {
			if (!"暂无年份".equals(time))
				if (!time.contains(infos[3].trim()))
					return true;
		}

		// 搜索名称过滤
		if (!title.select(urlCss).html().trim().equals(seek))
			return true;

		return false;
	}
	
	

}
