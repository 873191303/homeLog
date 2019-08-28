package com.jitv.tv.dao;

import java.util.List;
import java.util.Map;
import com.jitv.tv.dto.IpDTO;

public interface IpDao {

	int addIp(IpDTO dto);

	int deleteIp(String id);

	int update(Map<String, Object> map);

	List<IpDTO> selectList(String pageIndex, String pageSum, String ip, String city);

	// 获取城市对应的IP
	List<IpDTO> selectList(String city);

	int getSum(String ip, String city);

	IpDTO selIpById(Long id);

}
