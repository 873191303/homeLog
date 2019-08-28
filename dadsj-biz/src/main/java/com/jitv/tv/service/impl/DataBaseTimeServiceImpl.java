package com.jitv.tv.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aspire.commons.HttpClientHelper;
import com.aspire.commons.util.XmlUtil;
import com.jitv.tv.dao.DataBaseTimeDao;
import com.jitv.tv.dto.DataBaseTimeDTO;
import com.jitv.tv.service.DataBaseTimeService;
import com.jitv.tv.util.WebUtil;

public class DataBaseTimeServiceImpl implements DataBaseTimeService {

	private final static Logger logger = LoggerFactory.getLogger(DataBaseTimeServiceImpl.class);

	private DataBaseTimeDao dataBaseTimeDao;

	public void setDataBaseTimeDao(DataBaseTimeDao dataBaseTimeDao) {
		this.dataBaseTimeDao = dataBaseTimeDao;
	}

	@Override
	public List<Map<String, Object>> getList() {
		List<DataBaseTimeDTO> list = dataBaseTimeDao.getList();
		List<Map<String, Object>> result_List = new ArrayList<Map<String, Object>>();
		for (DataBaseTimeDTO dto : list) {
			Map<String, Object> map = dto.toDbMap();
			result_List.add(map);
		}
		logger.info(">>>>>>>>>>>数据库过期时间查询");
		return result_List;
	}

	@Override
	public int update(String id, String type, String time) {
		DataBaseTimeDTO dto = dataBaseTimeDao.getList(type);
		int result_time = dto.getTime();
		if (result_time != Integer.parseInt(time)) {// 数据跟数据库的数据不一致的时候修改
			// 1.先请求底层平台
			// 1.组装要发送的xml
			StringBuffer sb = new StringBuffer(1000);
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			sb.append("<info>");
			sb.append("<type>" + type + "</type>");
			sb.append("<duration>" + time + "</duration>");
			sb.append("</info>");
			String url = WebUtil.getServiceRoot() + "jiakelog/api/inter/jiake/IF_UPDATE_HoldTime";
			// 2.发送http请求
			logger.info("数据库保存天数要发送的xml："+sb.toString());
			String rsp = HttpClientHelper.getInstance().post(url, sb.toString());
			logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			logger.info("请求返回的xml："+rsp);
			if (StringUtils.isNotEmpty(rsp)) {
				Map<String, Object> result_map = new HashMap<String, Object>();
				try {
					 result_map = XmlUtil.toMap(rsp);
				} catch (Exception e) {
					logger.info(">>>>>>>>>>>数据库过期时间修改xml 解析异常");
					e.printStackTrace();
				}
				String resultCode = (String) result_map.get("resultCode");
				String failReason = (String) result_map.get("failReason");
				if ("1".equals(resultCode)) {
					// 2.更新本地持久化数据
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", id);
					map.put("type", type);
					map.put("time", time);
					logger.info(">>>>>>>>>>>数据库过期时间修改");
					return dataBaseTimeDao.update(map);
				} else {
					logger.info(">>>>>>>>>>>数据库过期时间修改返回值："+resultCode+"保存失败");
					return 0;
				}
			}

		}
		return 0;

	}

	public String update(Map<String, Object> map) {
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			update("", entry.getKey(), (String) entry.getValue());
		}
		return "修改成功";
	}

}
