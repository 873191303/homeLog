package com.jitv.tv.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aspire.commons.rpc.RpcContext;
import com.jitv.tv.dao.HostInnerDao;
import com.jitv.tv.dao.HostOuterDao;
import com.jitv.tv.dao.LargeFileDao;
import com.jitv.tv.dao.TmWebsiteDao;
import com.jitv.tv.dao.UsergroupDao;
import com.jitv.tv.dao.UsergrpIspMstDao;
import com.jitv.tv.dto.HostDTO;
import com.jitv.tv.dto.LargeFileDTO;
import com.jitv.tv.dto.TmWebsiteDTO;
import com.jitv.tv.dto.UsergroupDTO;
import com.jitv.tv.dto.UsergrpIspMstDTO;
import com.jitv.tv.service.InternetResourcesService;
import com.jitv.tv.util.ExcelUtils;

public class InternetResourcesServiceImpl implements InternetResourcesService {

	// host统计 dao注入
	private HostInnerDao hostInnerDao;

	private HostOuterDao hostOuterDao;

	// 用户群流量统计 dao 注入
	private UsergrpIspMstDao usergrpIspMstDao;

	// 用户群 运营商
	private UsergroupDao usergroupDao;

	// 大文件dao 注入
	private LargeFileDao largeFileDao;

	// 网站名称id 对应表
	private TmWebsiteDao tmWebsiteDao;

	public void setTmWebsiteDao(TmWebsiteDao tmWebsiteDao) {
		this.tmWebsiteDao = tmWebsiteDao;
	}

	public void setLargeFileDao(LargeFileDao largeFileDao) {
		this.largeFileDao = largeFileDao;
	}

	public void setUsergroupDao(UsergroupDao usergroupDao) {
		this.usergroupDao = usergroupDao;
	}

	public void setUsergrpIspMstDao(UsergrpIspMstDao usergrpIspMstDao) {
		this.usergrpIspMstDao = usergrpIspMstDao;
	}

	public void setHostInnerDao(HostInnerDao hostInnerDao) {
		this.hostInnerDao = hostInnerDao;
	}

	public void setHostOuterDao(HostOuterDao hostOuterDao) {
		this.hostOuterDao = hostOuterDao;
	}

	private final static Logger logger = LoggerFactory.getLogger(InternetResourcesServiceImpl.class);

	@Override
	public Map<String, Object> UserGroupGetFlow(String startIndex, String pageSize, String dls, String User,
			String link) {
		Integer usergrpid = null;// 用户群id
		Integer ispid = null;// 运营商id
		if (StringUtils.isNotEmpty(User)) {
			UsergroupDTO dto = usergroupDao.selectUserGroup(null, User);
			usergrpid = dto.getId();
		}
		if (StringUtils.isNotEmpty(dls)) {
			UsergroupDTO dto = usergroupDao.selectListIsp(null, dls);
			ispid = dto.getId();
		}
		List<UsergrpIspMstDTO> list = usergrpIspMstDao.selectList(startIndex, pageSize, usergrpid, ispid);
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for (UsergrpIspMstDTO dto : list) {
			Map<String, Object> map = dto.toDbMap();
			Integer result_usergrpid = (Integer) map.get("usergrpid");
			Integer result_ispid = (Integer) map.get("ispid");
			map.put("usergrpid", usergroupDao.selectUserGroup(result_usergrpid, "").getName());
			map.put("ispid", usergroupDao.selectListIsp(result_ispid, "").getName());
			resultList.add(map);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Result", resultList);
		map.put("totalItems", usergrpIspMstDao.getSum(usergrpid, ispid));
		return map;
	}

	@Override
	public void UserGroupExcel(String startIndex, String pageSize, String dls, String User, String link,
			RpcContext rc) {
		Integer usergrpid = null;// 用户群id
		Integer ispid = null;// 运营商id
		if (StringUtils.isNotEmpty(User)) {
			UsergroupDTO dto = usergroupDao.selectUserGroup(null, User);
			usergrpid = dto.getId();
		}
		if (StringUtils.isNotEmpty(dls)) {
			UsergroupDTO dto = usergroupDao.selectListIsp(null, dls);
			ispid = dto.getId();
		}
		List<UsergrpIspMstDTO> list = usergrpIspMstDao.selectList(startIndex, pageSize, usergrpid, ispid);
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for (UsergrpIspMstDTO dto : list) {
			Map<String, Object> map = dto.toDbMap();
			Integer result_usergrpid = (Integer) map.get("usergrpid");
			Integer result_ispid = (Integer) map.get("ispid");
			map.put("usergrpid", usergroupDao.selectUserGroup(result_usergrpid, "").getName());
			map.put("ispid", usergroupDao.selectListIsp(result_ispid, "").getName());
			resultList.add(map);
		}
		// *********************返回excle******************************************//
		ExcelUtils.downloadExcelUserGroup(resultList, rc);

	}

	@Override
	public Map<String, Object> OperatorGetFlow(String startIndex, String pageSize, String searchValue) {
		Integer usergrpid = null;// 用户群id
		Integer ispid = null;// 运营商id
		if (StringUtils.isNotEmpty(searchValue)) {
			UsergroupDTO dto = usergroupDao.selectListIsp(null, searchValue);
			ispid = dto.getId();
		}
		List<UsergrpIspMstDTO> list = usergrpIspMstDao.selectList(startIndex, pageSize, usergrpid, ispid);
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for (UsergrpIspMstDTO dto : list) {
			Map<String, Object> map = dto.toDbMap();
			// Integer result_usergrpid = (Integer) map.get("usergrpid");
			Integer result_ispid = (Integer) map.get("ispid");
			// map.put("usergrpid", usergroupDao.selectUserGroup(result_usergrpid,
			// "").getName());
			map.put("ispid", usergroupDao.selectListIsp(result_ispid, "").getName());
			resultList.add(map);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Result", resultList);
		map.put("totalItems", usergrpIspMstDao.getSum(usergrpid, ispid));
		return map;
	}

	@Override
	public Map<String, Object> WebsiteGetFlow(String startIndex, String pageSize, String dls, String User, String type,
			String website) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		// 互联网资源host流量
		List<HostDTO> hostOuter = hostOuterDao.selectList();
		List<HostDTO> hostinner = hostInnerDao.selectList();
		hostOuter.addAll(hostinner);// 合并list
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(20);
		for (HostDTO dto : hostOuter) {
			Map<String, Object> map = dto.toDbMap();
			Integer siteid = (Integer) map.get("siteid");
			TmWebsiteDTO tDto = tmWebsiteDao.select(siteid.toString(), "");
			map.put("siteid", tDto.getName());
			list.add(map);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(website)) {
//			TmWebsiteDTO tDto= tmWebsiteDao.select("", website);
//			String siteid = tDto.getId();
			List<Map<String, Object>> result_list = new ArrayList<Map<String, Object>>(10);
			for (int i = 0; i < list.size(); i++) {
				String r_siteid = (String) list.get(i).get("siteid");
				if (website.equals(r_siteid)) {
					result_list.add(list.get(i));
				} else {
					continue;
				}
			}
			if (Integer.parseInt(startIndex) + Integer.parseInt(pageSize) > result_list.size()) {
				result = result_list.subList(Integer.parseInt(startIndex), result_list.size());// 截取list用作前端分页
			} else {
				result = result_list.subList(Integer.parseInt(startIndex),
						Integer.parseInt(startIndex) + Integer.parseInt(pageSize));// 截取list用作前端分页
			}
			map.put("Result", result);
			map.put("totalItems", result_list.size());
			return map;
		} else {
			if (Integer.parseInt(startIndex) + Integer.parseInt(pageSize) > list.size()) {
				result = list.subList(Integer.parseInt(startIndex), list.size());// 截取list用作前端分页
			} else {
				result = list.subList(Integer.parseInt(startIndex),
						Integer.parseInt(startIndex) + Integer.parseInt(pageSize));// 截取list用作前端分页
			}
			map.put("Result", result);
			map.put("totalItems", list.size());
			return map;
		}
	}

	@Override
	public Map<String, Object> HostGetFlow(String startIndex, String pageSize, String dls, String User, String type,
			String host) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		// 互联网资源host流量
		List<HostDTO> hostOuter = hostOuterDao.selectList();
		List<HostDTO> hostinner = hostInnerDao.selectList();
		hostOuter.addAll(hostinner);// 合并list
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(20);
		for (HostDTO dto : hostOuter) {
			Map<String, Object> map = dto.toDbMap();
			list.add(map);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(host)) {
			List<Map<String, Object>> result_list = new ArrayList<Map<String, Object>>(10);
			for (int i = 0; i < list.size(); i++) {
				String r_host = (String) list.get(i).get("host");
				if (host.equals(r_host)) {
					result_list.add(list.get(i));
				} else {
					continue;
				}
			}
			if (Integer.parseInt(startIndex) + Integer.parseInt(pageSize) > result_list.size()) {
				result = result_list.subList(Integer.parseInt(startIndex), result_list.size());// 截取list用作前端分页
			} else {
				result = result_list.subList(Integer.parseInt(startIndex),
						Integer.parseInt(startIndex) + Integer.parseInt(pageSize));// 截取list用作前端分页
			}
			map.put("Result", result);
			map.put("totalItems", result_list.size());
			return map;
		} else {
			if (Integer.parseInt(startIndex) + Integer.parseInt(pageSize) > list.size()) {
				result = list.subList(Integer.parseInt(startIndex), list.size());// 截取list用作前端分页
			} else {
				result = list.subList(Integer.parseInt(startIndex),
						Integer.parseInt(startIndex) + Integer.parseInt(pageSize));// 截取list用作前端分页
			}
			map.put("Result", result);
			map.put("totalItems", list.size());
			return map;
		}
	}

	@Override
	public Map<String, Object> BigData(String startIndex, String pageSize, String searchValue) {
		List<LargeFileDTO> dtoList = largeFileDao.selectList(startIndex, pageSize, searchValue);
		List<Map<String, Object>> reusltList = new ArrayList<Map<String, Object>>(10);
		for (LargeFileDTO dto : dtoList) {
			Map<String, Object> map = dto.toDbMap();
			reusltList.add(map);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Result", reusltList);
		map.put("totalItems", largeFileDao.getSum(searchValue));
		return map;
	}

	@Override
	public List<Map<String, Object>> Percentage() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(10);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "中国移动");
		map.put("value", 60);
		list.add(map);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("name", "中国联通");
		map2.put("value", 30);
		list.add(map2);
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("name", "中国电信");
		map3.put("value", 10);
		list.add(map3);
		return list;
	}

	@Override
	public List<Map<String, Object>> WebsitePercentage() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(10);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "中国移动");
		map.put("value", 60);
		list.add(map);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("name", "中国联通");
		map2.put("value", 30);
		list.add(map2);
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("name", "中国电信");
		map3.put("value", 10);
		list.add(map3);
		return list;
	}

	@Override
	public void WebsitePercentageExcel(String startIndex, String pageSize, String dls, String User, String type,
			String website, RpcContext rc) {// 网站流量excel 导出
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		// 互联网资源host流量
		List<HostDTO> hostOuter = hostOuterDao.selectList();
		List<HostDTO> hostinner = hostInnerDao.selectList();
		hostOuter.addAll(hostinner);// 合并list
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(20);
		for (HostDTO dto : hostOuter) {
			Map<String, Object> map = dto.toDbMap();
			Integer siteid = (Integer) map.get("siteid");
			TmWebsiteDTO tDto = tmWebsiteDao.select(siteid.toString(), "");
			map.put("siteid", tDto.getName());
			list.add(map);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(website)) {
//			TmWebsiteDTO tDto= tmWebsiteDao.select("", website);
//			String siteid = tDto.getId();
			List<Map<String, Object>> result_list = new ArrayList<Map<String, Object>>(10);
			for (int i = 0; i < list.size(); i++) {
				String r_siteid = (String) list.get(i).get("siteid");
				if (website.equals(r_siteid)) {
					result_list.add(list.get(i));
				} else {
					continue;
				}
			}
			if (Integer.parseInt(startIndex) + Integer.parseInt(pageSize) > result_list.size()) {
				result = result_list.subList(Integer.parseInt(startIndex), result_list.size());// 截取list用作前端分页
			} else {
				result = result_list.subList(Integer.parseInt(startIndex),
						Integer.parseInt(startIndex) + Integer.parseInt(pageSize));// 截取list用作前端分页
			}
		} else {
			if (Integer.parseInt(startIndex) + Integer.parseInt(pageSize) > list.size()) {
				result = list.subList(Integer.parseInt(startIndex), list.size());// 截取list用作前端分页
			} else {
				result = list.subList(Integer.parseInt(startIndex),
						Integer.parseInt(startIndex) + Integer.parseInt(pageSize));// 截取list用作前端分页
			}
		}
		ExcelUtils.downloadExcelWebsitePercentage(result, rc);
	}

	@Override
	public void HostGetFlowExcel(String startIndex, String pageSize, String dls, String User, String type, String host,
			RpcContext rc) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		// 互联网资源host流量
		List<HostDTO> hostOuter = hostOuterDao.selectList();
		List<HostDTO> hostinner = hostInnerDao.selectList();
		hostOuter.addAll(hostinner);// 合并list
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(20);
		for (HostDTO dto : hostOuter) {
			Map<String, Object> map = dto.toDbMap();
			list.add(map);
		}
		if (StringUtils.isNotEmpty(host)) {
			List<Map<String, Object>> result_list = new ArrayList<Map<String, Object>>(10);
			for (int i = 0; i < list.size(); i++) {
				String r_host = (String) list.get(i).get("host");
				if (host.equals(r_host)) {
					result_list.add(list.get(i));
				} else {
					continue;
				}
			}
			if (Integer.parseInt(startIndex) + Integer.parseInt(pageSize) > result_list.size()) {
				result = result_list.subList(Integer.parseInt(startIndex), result_list.size());// 截取list用作前端分页
			} else {
				result = result_list.subList(Integer.parseInt(startIndex),
						Integer.parseInt(startIndex) + Integer.parseInt(pageSize));// 截取list用作前端分页
			}
		} else {
			if (Integer.parseInt(startIndex) + Integer.parseInt(pageSize) > list.size()) {
				result = list.subList(Integer.parseInt(startIndex), list.size());// 截取list用作前端分页
			} else {
				result = list.subList(Integer.parseInt(startIndex),
						Integer.parseInt(startIndex) + Integer.parseInt(pageSize));// 截取list用作前端分页
			}
		}
		ExcelUtils.HostGetFlowExcel(result, rc);

	}

	@Override
	public Map<String, Object> configuration() {// 配置管理
		Map<String, Object> result = new HashMap<String, Object>();
		// 大文件配置
		Map<String, Object> bigdata = new HashMap<String, Object>();
		bigdata.put("bigdata", 15);
		// 用户群配置
		List<UsergroupDTO> userConfiguration = usergroupDao.selectUserGroup();
		List<Map<String, Object>> userConfiguration2 = new ArrayList<Map<String,Object>>(5);
		for (UsergroupDTO dto : userConfiguration) {
			Map<String, Object> map = dto.toDbMap();
			map.put("usergroup", map.get("name"));// 用户群名称
			map.put("usergroupid", map.get("id"));// id
			userConfiguration2.add(map);
		}

		// 网站分类配置
		List<Map<String, Object>> websiteConfiguration = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 3; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("website", "网站分类" + 1 + i);// 网站分类名称
			map.put("websiteid", +11 + i);// id
			websiteConfiguration.add(map);
		}

		// 网站IP配置
		List<Map<String, Object>> ipConfiguration = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 3; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ip", "127.0.0.1" + i + 1);// 网站分类名称
			map.put("ipid", i + 11);// 网站分类名称
			ipConfiguration.add(map);
		}

		// 网站域名配置
		List<TmWebsiteDTO> domainConfiguration = tmWebsiteDao.select();
		List<Map<String, Object>> domainConfiguration2 = new ArrayList<Map<String,Object>>();
		for (TmWebsiteDTO dto:domainConfiguration) {
			Map<String, Object> map = dto.toDbMap();
			map.put("domain", map.get("name"));// 网站域名配置名称
			map.put("domainid",map.get("id"));// id
			domainConfiguration2.add(map);
		}
		// 网站二级域名配置
		List<Map<String, Object>> secondConfiguration = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 3; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("second", "http://www.csdn.com" + i + 1);// 网站二级域名名称
			map.put("secondid", i + 11);// 网站二级域名名称
			secondConfiguration.add(map);
		}

		// 运营商配置
		List<UsergroupDTO> OperatorConfiguration = usergroupDao.selectListIsp();
		List<Map<String, Object>> OperatorConfiguration2 = new ArrayList<Map<String,Object>>(5);
		for (UsergroupDTO dto : OperatorConfiguration) {
			Map<String, Object> map = dto.toDbMap();
			map.put("operator", map.get("name"));// 运营商名称
			map.put("operatorid", map.get("id"));// id
			OperatorConfiguration2.add(map);
		}
		result.put("bigdata", bigdata);// 大文件配置
		result.put("userConfiguration", userConfiguration2);// 用户群配置
		result.put("websiteConfiguration", websiteConfiguration);// 网站分类配置
		result.put("ipConfiguration", ipConfiguration);// 网站IP配置
		result.put("domainConfiguration", domainConfiguration2);// 网站分类配置
		result.put("secondConfiguration", secondConfiguration);// 网站二级域名配置
		result.put("OperatorConfiguration", OperatorConfiguration2);// 运营商配置
		return result;
	}

	@Override
	public Map<String, Object> bigdataUpdate(String value) {// 大文件阈值修改
		logger.info("大文件修改参数：value:" + value);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "succeed");
		return map;
	}

	@Override
	public Map<String, Object> userConfigurationDelete(String id) {// 用户配置删除
		logger.info("用户配置删除：id:" + id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "删除成功");
		return map;
	}

	@Override
	public Map<String, Object> userConfigurationUpdate(String id, String value, String type) {// 用户配置修改
		logger.info("用户配置修改：id:" + id + " value:" + value + " type:" + type);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "修改成功");
		return map;
		// type类型
		// 用户群配置修改
		// 网站分类配置修改
		// 网站IP配置修改
		// 域名修改
		// 二级域名修改
		// 网站运营商修改
	}

	@Override
	public Map<String, Object> userConfigurationAdd(String value, String type) {// 配置管理新增同意入口
		logger.info("配置管理新增同意入口： value:" + value + " type:" + type);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "新增成功");
		return map;
		// type 类型
		// 用户群配置新增
		// 网站分类新增
		// 网站IP新增
		// 网站域名新增
		// 网站二级域名新增
		// 网站运营商新增
	}

	@Override
	public Map<String, Object> websiteConfigurationDelete(String id) {// 网站分类配置删除
		logger.info("网站分类配置删除：id:" + id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "删除成功");
		return map;
	}

	@Override
	public Map<String, Object> ipConfigurationDelete(String id) {// 网站IP配置删除
		logger.info("网站IP配置删除：id:" + id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "删除成功");
		return map;
	}

	@Override
	public Map<String, Object> domainConfiguration(String id) {// 网站域名配置删除
		logger.info("网站域名配置删除：id:" + id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "删除成功");
		return map;
	}

	@Override
	public Map<String, Object> secondConfiguration(String id) {// 网站二级域名删除
		logger.info("网站二级域名删除：id:" + id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "删除成功");
		return map;
	}

	@Override
	public Map<String, Object> operatorConfigurationDelete(String id) {// 网站运营商删除
		logger.info("网站运营商删除：id:" + id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "删除成功");
		return map;
	}

}
