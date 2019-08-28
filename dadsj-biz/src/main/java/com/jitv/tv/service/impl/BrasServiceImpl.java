package com.jitv.tv.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aspire.commons.rpc.RpcContext;
import com.jitv.tv.dao.Bras2cityDao;
import com.jitv.tv.dao.BrasDao;
import com.jitv.tv.dto.BrasDTO;
import com.jitv.tv.dto.BrascityDTO;
import com.jitv.tv.service.BrasService;
import com.jitv.tv.util.DateUtil;
import com.jitv.tv.util.ExcelUtils;

public class BrasServiceImpl implements BrasService {
	private final static Logger logger = LoggerFactory.getLogger(BrasServiceImpl.class);

	// 注入bras dao

	private BrasDao brasDao;

	public void setBrasDao(BrasDao brasDao) {
		this.brasDao = brasDao;
	}

	private Bras2cityDao bras2cityDao;

	public void setBras2cityDao(Bras2cityDao bras2cityDao) {
		this.bras2cityDao = bras2cityDao;
	}

	@Override
	public Map<String, Object> NumberCount(String startIndex, String pageSize, String BrasIP, String BrasName,
			String BrasType, String startTime, String endTime) {
		Date start_time = null;
		Date end_time = null;
		if (StringUtils.isNotEmpty(startTime)) {
			String DATEFORMAT = "yyyy-MM-dd-HH";
			try {
				start_time = DateUtil.parseDate(startTime, DATEFORMAT);
				end_time = DateUtil.parseDate(endTime, DATEFORMAT);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		List<BrasDTO> list = brasDao.selectList(startIndex, pageSize, BrasIP, BrasName, start_time, end_time);
		List<Map<String, Object>> result_list = new ArrayList<Map<String, Object>>(10);
		for (BrasDTO dto : list) {
			Map<String, Object> map = dto.toDbMap();
			result_list.add(map);
		}
		int count = brasDao.getSum(BrasIP, BrasName, start_time, end_time);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Result", result_list);
		map.put("totalItems", count);
		return map;
	}

	@Override
	public Map<String, Object> CityCount(String startIndex, String pageSize, String BrasIP, String BrasName,
			String City, String Manufactor) {
		List<BrascityDTO> list = bras2cityDao.selectList(startIndex, pageSize, BrasIP, BrasName, City, Manufactor);
		List<Map<String, Object>> result_list = new ArrayList<Map<String, Object>>(10);
		for (BrascityDTO dto : list) {
			Map<String, Object> map = dto.toDbMap();
			result_list.add(map);
		}
		int count = bras2cityDao.getSum(BrasIP, BrasName, City, Manufactor);
		Map<String, Object> map = new HashMap<String, Object>(5);
		map.put("Result", result_list);
		map.put("totalItems", count);
		return map;
	}

	@Override
	public Map<String, Object> columnTable(String startIndex, String pageSize, String BrasIP, String BrasName,
			String BrasType, String startTime, String endTime) {
		Map<String, Object> result_map = NumberCount(startIndex, pageSize, BrasIP, BrasName, BrasType, startTime, endTime);
		List<Map<String, Object>> result_list = (List<Map<String,Object>>)result_map.get("Result");
		
		
//		logger.debug("Bras数量统计柱表接受到的参数》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》" + "startIndex:" + startIndex + " pageSize:"
//				+ pageSize + " BrasIP:" + BrasIP + " BrasName:" + BrasName + " BrasType:" + BrasType + " startTime:"
//				+ startTime + " endTime:" + endTime);
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(20);
//		for (int i = 0; i < 10; i++) {
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("name", "tom" + i);
//			map.put("value", 10);
//			list.add(map);
//		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result_list);
		return map;
	}

	@Override
	public void BrasDownloadExcel(String startIndex, String pageSize, String BrasIP, String BrasName, String BrasType,
			String startTime, String endTime, RpcContext rc) {
		
		Map<String, Object> result_map = NumberCount(startIndex, pageSize, BrasIP, BrasName, BrasType, startTime, endTime);
		List<Map<String, Object>> result_list = (List<Map<String,Object>>)result_map.get("Result");
		// *********************返回excle******************************************//
		ExcelUtils.BrasDownloadExcel(result_list, rc);

	}

	// brase 新增数据统计相关
	@Override
	public int addBras(String ip, String name, String account, String time, String manufactor, String city,
			String count) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
		// 1.新增之前先根据ip和时间去数据库查询看是否存在数据
		BrasDTO dto = new BrasDTO();
		dto.setIp(ip);
		dto.setName(name);
		dto.setAccount(account);
		try {
			dto.setTime(sdf.parse(time));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		dto.setManufactor(manufactor);
		dto.setCity(city);
		dto.setCount(Long.parseLong(count));
		try {
			BrasDTO result_dto = brasDao.selectList(ip, sdf.parse(time));
			// 如果存在在原来的基础上加1
			if (result_dto != null) {
				logger.info("有重复数据》》》》》》》》》》》》》》》》》》》》》》》》》");
				Long result_count = result_dto.getCount();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("ip", ip);
				map.put("time", sdf.parse(time));
				map.put("count", result_count += 1L);
				return brasDao.update(map);
			} else {
				logger.info("无重复数据？？？？？？？？？？？？？？？？？？？？？？？？？？？？");
				// 如果不存在新增一条记录入库
				return brasDao.addBras(dto);
			}
			// 如果不存在则新增一条记录
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return 0;

	}

	@Override
	public int addBrasCity(String ip, String name, String account, String time, String manufactor, String city,
			String count) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
		// 1.新增之前先根据ip和时间去数据库查询看是否存在数据
		BrascityDTO dto = new BrascityDTO();
		dto.setIp(ip);
		dto.setName(name);
		dto.setAccount(account);
		try {
			dto.setTime(sdf.parse(time));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		dto.setManufactor(manufactor);
		dto.setCity(city);
		dto.setCount(Long.parseLong(count));
		try {
			BrascityDTO result_dto = bras2cityDao.select(ip, city);
			// 如果存在在原来的基础上加1
			if (result_dto != null) {
				logger.info("有重复数据》》》》》》》》》》》》》》》》》》》》》》》》》");
				Long result_count = result_dto.getCount();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("ip", ip);
				map.put("time", time);
				map.put("count", result_count += 1L);
				return bras2cityDao.update(map);
			} else {
				logger.info("无重复数据？？？？？？？？？？？？？？？？？？？？？？？？？？？？");
				// 如果不存在新增一条记录入库
				return bras2cityDao.addBras(dto);
			}
			// 如果不存在则新增一条记录
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return 0;
	}

	@Override
	public Map<String, Object> select(String city, String manufactor, String ip, String name, String pageIndex,
			String pageSum) {
		return null;
	}

}
