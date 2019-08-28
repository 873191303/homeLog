package com.jitv.tv.dao.impl;

import java.util.List;
import java.util.Map;
import com.jitv.tv.dao.HostOuterDao;
import com.jitv.tv.dao.base.AbstractDAO;
import com.jitv.tv.dto.HostDTO;

public class HostOuterDaoImpl extends AbstractDAO implements HostOuterDao {

	public HostOuterDaoImpl() {
		super("host_outer_tbl");
	}

	@Override
	public int getSum() {
		return (int) function("getSum");
	}

	@Override
	public List<HostDTO> selectList(String pageIndex, String pageSum) {
		return queryForList("getList", HostDTO.class, newParam("pageIndex", Integer.parseInt(pageIndex)),
				newParam("pageSum", Integer.parseInt(pageSum)));
	}

	@Override
	public int addHost(HostDTO dto) {
		try {
			insert("add", dto);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public HostDTO select() {
		return queryForObject("gethost", HostDTO.class);
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
	public int delete() {
		try {
			delete("delete");
			return 1;
		} catch (Exception e) {
			return 0;
		}

	}

	@Override
	public List<HostDTO> selectList(String pageIndex, String pageSum, String host, String usergrpid) {
		return queryForList("selectlist", HostDTO.class, newParam("pageIndex", Integer.parseInt(pageIndex)),
				newParam("pageSum", Integer.parseInt(pageSum)), newParam("host", host),
				newParam("usergrpid", usergrpid));
	}

	@Override
	public List<HostDTO> selectList() {
		return queryForList("selectall", HostDTO.class);
	}
}
