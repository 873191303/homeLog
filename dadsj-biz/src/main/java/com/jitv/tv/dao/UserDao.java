package com.jitv.tv.dao;

import java.util.List;
import java.util.Map;
import com.jitv.tv.dto.UserDTO;

public interface UserDao {
	int addUser(UserDTO dto);
	int deleteUser(String id);
	int update(Map<String,Object> map);
	List<UserDTO> selectList(String pageIndex, String pageSum,String userId,String city);
	int getSum(String userId, String city);
	UserDTO selUserById(String userId);
	List<UserDTO> selectList(List<String> ids);
	List<UserDTO> selectList();

}
