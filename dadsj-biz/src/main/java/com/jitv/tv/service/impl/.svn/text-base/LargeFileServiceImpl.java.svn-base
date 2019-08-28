package com.jitv.tv.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.jitv.tv.dao.LargeFileDao;
import com.jitv.tv.dto.LargeFileDTO;
import com.jitv.tv.service.LargeFileService;

public class LargeFileServiceImpl implements LargeFileService {

	private LargeFileDao largeFileDao;

	public void setLargeFileDao(LargeFileDao largeFileDao) {
		this.largeFileDao = largeFileDao;
	}

	@Override
	public List<Map<String, Object>> selectList(String pageIndex, String pageSum) {
		List<LargeFileDTO> list = largeFileDao.selectList(pageIndex, pageSum,"");
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>(10);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i).toDbMap();
				resultList.add(map);
			}
		}
		return resultList;
	}

	@Override
	public Map<String, Object> FileFlow(String pageIndex, String pageSum) {
		List<Map<String, Object>> list = selectList(pageIndex, pageSum);
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("fileName", (String) map.get("filename"));// 大文件名
			resultMap.put("flow", ((Long) map.get("Count"))+"次");// 访问量
			resultList.add(resultMap);
		}
		int count = largeFileDao.getSum();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Result", resultList);
		map.put("totalItems", count);
		return map;
	}
	
	//主机流量
	@Override
	public Map<String, Object> HostFlow(String pageIndex, String pageSum) {
		List<Map<String, Object>> list = selectList(pageIndex, pageSum);
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("hostName", (String) map.get("hostip"));// 大文件名
			resultMap.put("flow", ((Integer) map.get("Length"))+"MB");// 访问量
			resultList.add(resultMap);
		}
		int count = largeFileDao.getSum();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Result", resultList);
		map.put("totalItems", count);
		return map;
	}

}
