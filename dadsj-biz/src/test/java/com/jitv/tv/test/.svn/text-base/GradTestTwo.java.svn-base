package com.jitv.tv.test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import com.jitv.tv.util.Action;

/**
 * @author xiaominghui@9ikandian.com
 * @date 2017-9-20 下午9:28:47
 * @describe 
 */
public class GradTestTwo {
	
	@Test
	public void test() throws IOException {
		
		System.out.println("[]");
		String url = "http://1212.ip138.com/ic.asp";
		String ip = "124.72.96.36";
		String port = "20640";

		System.getProperties().setProperty("http.proxyHost", ip);
		System.getProperties().setProperty("http.proxyPort", port);

		Connection connect = Jsoup.connect(url);

		Document document = connect.get();
		
		System.out.println(document.html());
	}
	
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void teste3() throws IOException {
		String URl = "http://www.xdaili.cn/ipagent//freeip/getFreeIps?page=1&rows=10";
		String html = Jsoup.connect(URl).get().select("body").html();

		Map<String, Object> map = (Map) Action.toBean(html).get("RESULT");

		if (map == null) {
			System.out.println("stop");
			return;
		}

		List<Map<String, Object>> ipAsPortMaps = (List) map.get("rows");

		if (ipAsPortMaps == null) {
			System.out.println("stop");
			return;
		}

		String checkUrl = "http://www.xdaili.cn/ipagent//checkIp/ipList?";

		for (Map<String, Object> ipAsPortMap : ipAsPortMaps) {
			Object ip = ipAsPortMap.get("ip");
			Object port = ipAsPortMap.get("port");
			checkUrl += "ip_ports[]=" + ip + ":" + port + "&";
		}
		
		Document document = Jsoup.connect(checkUrl).get();
		String data = document.select("body").html();
		System.out.println(Action.toBean(data));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

