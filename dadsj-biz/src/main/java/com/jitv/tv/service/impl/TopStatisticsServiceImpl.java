package com.jitv.tv.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.jitv.tv.dao.HostInnerDao;
import com.jitv.tv.dao.HostOuterDao;
import com.jitv.tv.dao.IpTopnDao;
import com.jitv.tv.dao.TmWebsiteDao;
import com.jitv.tv.dao.UsergroupDao;
import com.jitv.tv.dao.UsergrpIspMstDao;
import com.jitv.tv.dto.HostDTO;
import com.jitv.tv.dto.IpTopnDTO;
import com.jitv.tv.dto.TmWebsiteDTO;
import com.jitv.tv.dto.UsergrpIspMstDTO;
import com.jitv.tv.service.TopStatisticsService;

public class TopStatisticsServiceImpl implements TopStatisticsService {

	private final static Logger logger = LoggerFactory.getLogger(TopStatisticsServiceImpl.class);

	private HostOuterDao hostOuterDao;

	private HostInnerDao hostInnerDao;

	// 用户排名 dao 注入
	private UsergrpIspMstDao usergrpIspMstDao;

	private UsergroupDao usergroupDao;

	// 网站管理注入
	private TmWebsiteDao tmWebsiteDao;

	// iptop注入dao
	private IpTopnDao ipTopnDao;

	public void setIpTopnDao(IpTopnDao ipTopnDao) {
		this.ipTopnDao = ipTopnDao;
	}

	public void setTmWebsiteDao(TmWebsiteDao tmWebsiteDao) {
		this.tmWebsiteDao = tmWebsiteDao;
	}

	public void setUsergroupDao(UsergroupDao usergroupDao) {
		this.usergroupDao = usergroupDao;
	}

	public void setUsergrpIspMstDao(UsergrpIspMstDao usergrpIspMstDao) {
		this.usergrpIspMstDao = usergrpIspMstDao;
	}

	public void setHostOuterDao(HostOuterDao hostOuterDao) {
		this.hostOuterDao = hostOuterDao;
	}

	public void setHostInnerDao(HostInnerDao hostInnerDao) {
		this.hostInnerDao = hostInnerDao;
	}

	@Override
	public Map<String, Object> UserLog(String startIndex, String pageSize) {
		// logger.info(">>>>>>>>>经分用户上网日志>>>>参数：" + searchValue);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(20);
		for (int i = 0; i < 20; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userName", "用户" + i);// 用户名
			map.put("ip", "30.222.1.2" + i);// IP
			map.put("url", "http://www.baidu.com" + i);// 目标URL
			map.put("startTime", "2019年1月9日 17:44:0" + i);// 开始时间
			map.put("endTime", "2019年1月11日 17:44:0" + i);// 结束时间
			list.add(map);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if ("0".equals(startIndex)) {
			map.put("Result", list = list.subList(0, 10));
		} else if ("10".equals(startIndex)) {
			map.put("Result", list = list.subList(10, list.size()));
		} else {
			map.put("Result", list);
		}
		map.put("totalItems", list.size() * 2);
		return map;
	}

	@Override
	public Map<String, Object> HostFlow(String startIndex, String pageSize) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(20);
		for (int i = 0; i < 20; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("hostName", "主机" + i);// 主机名称
			map.put("flow", "10" + i + "T");// 流量
			list.add(map);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if ("0".equals(startIndex)) {
			map.put("Result", list = list.subList(0, 10));
		} else if ("10".equals(startIndex)) {
			map.put("Result", list = list.subList(10, list.size()));
		} else {
			map.put("Result", list);
		}
		map.put("totalItems", list.size() * 2);
		return map;
	}

	@Override
	public Map<String, Object> FileFlow(String startIndex, String pageSize) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(20);
		for (int i = 0; i < 20; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("fileName", "主机" + i);// 大文件名
			map.put("flow", "10" + i + "T");// 访问量
			list.add(map);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if ("0".equals(startIndex)) {
			map.put("Result", list = list.subList(0, 10));
		} else if ("10".equals(startIndex)) {
			map.put("Result", list = list.subList(10, list.size()));
		} else {
			map.put("Result", list);
		}
		map.put("totalItems", list.size() * 2);
		return map;
	}

	@Override
	public Map<String, Object> UserRanking(String startIndex, String pageSize) {

		List<UsergrpIspMstDTO> dtoList = usergrpIspMstDao.selectList(startIndex, pageSize, null, null);
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>(10);
		for (UsergrpIspMstDTO dto : dtoList) {
			Map<String, Object> map = dto.toDbMap();
			Integer result_usergrpid = (Integer) map.get("usergrpid");
			Integer result_ispid = (Integer) map.get("ispid");
			map.put("usergrpid", usergroupDao.selectUserGroup(result_usergrpid, "").getName());
			map.put("ispid", usergroupDao.selectListIsp(result_ispid, "").getName());
			resultList.add(map);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Result", resultList);
		map.put("totalItems", usergrpIspMstDao.getSum(null, null));
		return map;
	}

	@Override
	public Map<String, Object> DomesticFlow(String startIndex, String pageSize) {// 国内网站TOPN流量 wxg 2019-01-11
		List<HostDTO> dto_list = hostInnerDao.selectList(startIndex, pageSize);
		List<Map<String, Object>> result_list = new ArrayList<Map<String, Object>>(10);
		for (HostDTO dto : dto_list) {
			Map<String, Object> map = dto.toDbMap();
			Integer siteid = (Integer) map.get("siteid");
			String name = tmWebsiteDao.select(siteid.toString(), "").getName();
			Long upbyte = (Long) map.get("upbyte");
			Long dnbyte = (Long) map.get("dnbyte");
			Long count = upbyte + dnbyte;
			map.put("count", count);
			map.put("name", name);
			result_list.add(map);
		}
		int count = hostInnerDao.getSum();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Result", result_list);
		map.put("totalItems", count);
		return map;

	}

	@Override
	public Map<String, Object> AbroadFlow(String startIndex, String pageSize) {
		List<HostDTO> dto_list = hostOuterDao.selectList(startIndex, pageSize);
		List<Map<String, Object>> result_list = new ArrayList<Map<String, Object>>(10);
		for (HostDTO dto : dto_list) {
			Map<String, Object> map = dto.toDbMap();
			Integer siteid = (Integer) map.get("siteid");
			String name = tmWebsiteDao.select(siteid.toString(), "").getName();
			Long upbyte = (Long) map.get("upbyte");
			Long dnbyte = (Long) map.get("dnbyte");
			Long count = upbyte + dnbyte;
			map.put("count", count);
			map.put("name", name);
			result_list.add(map);
		}
		int count = hostOuterDao.getSum();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Result", result_list);
		map.put("totalItems", count);
		return map;
	}

	@Override
	public Map<String, Object> DomesticVisit(String startIndex, String pageSize) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(20);
		for (int i = 0; i < 20; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("domainName", "http://www.sina.com.cn" + i);// 域名
			map.put("flow", "77887" + i);// 访问量
			list.add(map);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if ("0".equals(startIndex)) {
			map.put("Result", list = list.subList(0, 10));
		} else if ("10".equals(startIndex)) {
			map.put("Result", list = list.subList(10, list.size()));
		} else {
			map.put("Result", list);
		}
		map.put("totalItems", list.size() * 2);
		return map;
	}

	@Override
	public Map<String, Object> AbroadVisit(String startIndex, String pageSize) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(20);
		for (int i = 0; i < 20; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("domainName", "http://www.google.cn" + i);// 域名
			map.put("flow", "77887" + i);// 访问量
			list.add(map);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if ("0".equals(startIndex)) {
			map.put("Result", list = list.subList(0, 10));
		} else if ("10".equals(startIndex)) {
			map.put("Result", list = list.subList(10, list.size()));
		} else {
			map.put("Result", list);
		}
		map.put("totalItems", list.size() * 2);
		return map;
	}

	@Override
	public Map<String, Object> DomesticTwoFlow(String startIndex, String pageSize) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(20);
		for (int i = 0; i < 20; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("domainName", "http://www.sina.com.cn" + i);// 域名
			map.put("flow", "77887" + i);// 访问量
			list.add(map);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if ("0".equals(startIndex)) {
			map.put("Result", list = list.subList(0, 10));
		} else if ("10".equals(startIndex)) {
			map.put("Result", list = list.subList(10, list.size()));
		} else {
			map.put("Result", list);
		}
		map.put("totalItems", list.size() * 2);
		return map;
	}

	@Override
	public Map<String, Object> AbroadTwoFlow(String startIndex, String pageSize) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(20);
		for (int i = 0; i < 20; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("domainName", "http://www.google.cn" + i);// 域名
			map.put("flow", "77887" + i);// 访问量
			list.add(map);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if ("0".equals(startIndex)) {
			map.put("Result", list = list.subList(0, 10));
		} else if ("10".equals(startIndex)) {
			map.put("Result", list = list.subList(10, list.size()));
		} else {
			map.put("Result", list);
		}
		map.put("totalItems", list.size() * 2);
		return map;
	}

	@Override
	public Map<String, Object> DomesticTwoVisit(String startIndex, String pageSize) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(20);
		for (int i = 0; i < 20; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("domainName", "http://www.sina.com.cn" + i);// 域名
			map.put("flow", "77887" + i);// 访问量
			list.add(map);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if ("0".equals(startIndex)) {
			map.put("Result", list = list.subList(0, 10));
		} else if ("10".equals(startIndex)) {
			map.put("Result", list = list.subList(10, list.size()));
		} else {
			map.put("Result", list);
		}
		map.put("totalItems", list.size() * 2);
		return map;
	}

	@Override
	public Map<String, Object> AbroadTwoVisit(String startIndex, String pageSize) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(20);
		for (int i = 0; i < 20; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("domainName", "http://www.google.cn" + i);// 域名
			map.put("flow", "77887" + i);// 访问量
			list.add(map);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if ("0".equals(startIndex)) {
			map.put("Result", list = list.subList(0, 10));
		} else if ("10".equals(startIndex)) {
			map.put("Result", list = list.subList(10, list.size()));
		} else {
			map.put("Result", list);
		}
		map.put("totalItems", list.size() * 2);
		return map;
	}

	@Override
	public Map<String, Object> DomesticHostFlow(String startIndex, String pageSize) {
		List<HostDTO> dto_list = hostInnerDao.selectList(startIndex, pageSize);
		List<Map<String, Object>> result_list = new ArrayList<Map<String, Object>>(10);
		for (HostDTO dto : dto_list) {
			Map<String, Object> map = dto.toDbMap();
			result_list.add(map);
		}
		int count = hostInnerDao.getSum();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Result", result_list);
		map.put("totalItems", count);
		return map;
	}

	@Override
	public Map<String, Object> AbroadHostFlow(String startIndex, String pageSize) {
		List<HostDTO> dto_list = hostOuterDao.selectList(startIndex, pageSize);
		List<Map<String, Object>> result_list = new ArrayList<Map<String, Object>>(10);
		for (HostDTO dto : dto_list) {
			Map<String, Object> map = dto.toDbMap();
			result_list.add(map);
		}
		int count = hostOuterDao.getSum();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Result", result_list);
		map.put("totalItems", count);
		return map;
	}

	@Override
	public Map<String, Object> DomesticHostVisit(String startIndex, String pageSize) {
		List<HostDTO> dto_list = hostInnerDao.selectList(startIndex, pageSize);
		List<Map<String, Object>> result_list = new ArrayList<Map<String, Object>>(10);
		for (HostDTO dto : dto_list) {
			Map<String, Object> map = dto.toDbMap();
			result_list.add(map);
		}
		int count = hostInnerDao.getSum();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Result", result_list);
		map.put("totalItems", count);
		return map;
	}

	@Override
	public Map<String, Object> AbroadHostVisit(String startIndex, String pageSize) {
		List<HostDTO> dto_list = hostOuterDao.selectList(startIndex, pageSize);
		List<Map<String, Object>> result_list = new ArrayList<Map<String, Object>>(10);
		for (HostDTO dto : dto_list) {
			Map<String, Object> map = dto.toDbMap();
			result_list.add(map);
		}
		int count = hostOuterDao.getSum();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Result", result_list);
		map.put("totalItems", count);
		return map;
	}

	@Override
	public Map<String, Object> DomesticIpFlow(String startIndex, String pageSize) {
		int iptype = 1;
		List<IpTopnDTO> listDto = ipTopnDao.selectList(startIndex, pageSize, iptype,"");
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>(10);
		for (IpTopnDTO dto : listDto) {
			Map<String, Object> map = dto.toDbMap();
			resultList.add(map);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Result", resultList);
		map.put("totalItems", ipTopnDao.getSum(iptype,""));
		return map;
	}

	@Override
	public Map<String, Object> AbroadIpFlow(String startIndex, String pageSize) {
		int iptype = 2;
		List<IpTopnDTO> listDto = ipTopnDao.selectList(startIndex, pageSize, iptype,"");
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>(10);
		for (IpTopnDTO dto : listDto) {
			Map<String, Object> map = dto.toDbMap();
			resultList.add(map);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Result", resultList);
		map.put("totalItems", ipTopnDao.getSum(iptype,""));
		return map;
	}

	@Override
	public Map<String, Object> DomesticIpVisit(String startIndex, String pageSize) {// 国内
		int iptype = 1;
//		        =1，网内ip 10.10.10.10
//				=2，网外 ip 111.111.111.111
//				=3，网内C 10.10.10.0
//				=4，网外C 111.111.111.0
//				=5，网外B 111.111.0.0
//				=9，指定ip段，10.10.10.10、111.111.111.111

		List<IpTopnDTO> listDto = ipTopnDao.selectList(startIndex, pageSize, iptype,"");
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>(10);
		for (IpTopnDTO dto : listDto) {
			Map<String, Object> map = dto.toDbMap();
			resultList.add(map);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Result", resultList);
		map.put("totalItems", ipTopnDao.getSum(iptype,""));
		return map;
	}

	@Override
	public Map<String, Object> AbroadIpVisit(String startIndex, String pageSize) {
		int iptype = 2;
//      =1，网内ip 10.10.10.10
//		=2，网外 ip 111.111.111.111
//		=3，网内C 10.10.10.0
//		=4，网外C 111.111.111.0
//		=5，网外B 111.111.0.0
//		=9，指定ip段，10.10.10.10、111.111.111.111

		List<IpTopnDTO> listDto = ipTopnDao.selectList(startIndex, pageSize, iptype,"");
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>(10);
		for (IpTopnDTO dto : listDto) {
			Map<String, Object> map = dto.toDbMap();
			resultList.add(map);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Result", resultList);
		map.put("totalItems", ipTopnDao.getSum(iptype,""));
		return map;
	}

}
