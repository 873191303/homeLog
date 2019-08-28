package com.jitv.tv.dao.impl;

import java.util.List;

import com.jitv.tv.dao.TmWebsiteDao;
import com.jitv.tv.dao.base.AbstractDAO;
import com.jitv.tv.dto.TmWebsiteDTO;

public class TmWebsiteDaoImpl extends AbstractDAO implements TmWebsiteDao {

	public TmWebsiteDaoImpl() {
		super("tm_website");
	}

	@Override
	public TmWebsiteDTO select(String id, String name) {
		return queryForObject("selectlist", TmWebsiteDTO.class, newParam("id", id), newParam("name", name));
	}

	@Override
	public List<TmWebsiteDTO> select() {
		return queryForList("select", TmWebsiteDTO.class);
	}

}
