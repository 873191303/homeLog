package com.jitv.tv.dao;

import java.util.List;

import com.jitv.tv.dto.TmWebsiteDTO;

public interface TmWebsiteDao {

	TmWebsiteDTO select(String id, String name);

	List<TmWebsiteDTO> select();

}
