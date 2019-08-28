package com.jitv.tv.dao.impl;

import java.util.List;
import com.jitv.tv.dao.IpTopnDao;
import com.jitv.tv.dao.base.AbstractDAO;
import com.jitv.tv.dto.IpTopnDTO;

public class IpTopnDaoImpl extends AbstractDAO implements IpTopnDao {

	public IpTopnDaoImpl() {
		super("td_uas_iptopn");
	}

	@Override
	public int addIp(IpTopnDTO dto) {
		try {
			insert("add", dto);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public int deleteIpTopn() {
		try {
			delete("delete");
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public List<IpTopnDTO> selectList(String pageIndex, String pageSum, Integer iptype,String ipaddr) {
		return queryForList("getList", IpTopnDTO.class, newParam("pageIndex", Integer.parseInt(pageIndex)),
				newParam("pageSum", Integer.parseInt(pageSum)), newParam("iptype", iptype),newParam("ipaddr", ipaddr));
	}

	@Override
	public int getSum(Integer iptype,String ipaddr) {
		return (int) function("getSum", newParam("iptype", iptype),newParam("ipaddr", ipaddr));
	}

}
