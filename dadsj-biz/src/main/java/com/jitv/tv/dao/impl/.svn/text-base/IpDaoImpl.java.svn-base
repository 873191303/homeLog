package com.jitv.tv.dao.impl;

import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import com.jitv.tv.dao.IpDao;
import com.jitv.tv.dao.base.AbstractDAO;
import com.jitv.tv.dto.IpDTO;

public class IpDaoImpl extends AbstractDAO implements IpDao {

	public IpDaoImpl() {
		super("ip_tbl");
	}

	@Override
	public int addIp(IpDTO dto) {
		try {
			insert("add", dto);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public int deleteIp(String id) {
		try {
			if (StringUtils.isNotEmpty(id)) {
				long newId = Long.parseLong(id);
				delete("delete", newParam("id", newId));
				return 1;
			}
			return 0;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public int update(Map<String, Object> map) {
		try {
			update("update", map);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public List<IpDTO> selectList(String pageIndex, String pageSum, String ip, String city) {
		return queryForList("getList", IpDTO.class, newParam("pageIndex", Integer.parseInt(pageIndex)),
				newParam("pageSum", Integer.parseInt(pageSum)), newParam("ip", ip), newParam("city", city));
	}

	@Override
	public int getSum(String ip, String city) {
		return (int) function("getSum", newParam("ip", ip), newParam("city", city));
	}

	@Override
	public IpDTO selIpById(Long id) {
		return queryForObject("getById", IpDTO.class, newParam("id", id));
	}

	//获取城市对用的IP
	@Override
	public List<IpDTO> selectList(String city) {
		return queryForList("getIP", IpDTO.class, newParam("city", city));
	}

}
