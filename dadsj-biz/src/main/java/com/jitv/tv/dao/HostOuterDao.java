package com.jitv.tv.dao;

import java.util.List;
import java.util.Map;
import com.jitv.tv.dto.HostDTO;

public interface HostOuterDao {

	int getSum();

	List<HostDTO> selectList(String pageIndex, String pageSum);
	
	List<HostDTO> selectList(String pageIndex, String pageSum,String host,String usergrpid);
	
	List<HostDTO> selectList();

	int addHost(HostDTO dto);

	HostDTO select();

	int update(Map<String, Object> map);
	
	int delete();

}
