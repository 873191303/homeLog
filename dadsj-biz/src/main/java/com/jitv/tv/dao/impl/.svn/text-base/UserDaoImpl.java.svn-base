package com.jitv.tv.dao.impl;

import java.util.List;
import java.util.Map;

import com.jitv.tv.dao.UserDao;
import com.jitv.tv.dao.base.AbstractDAO;
import com.jitv.tv.dto.UserDTO;

public class UserDaoImpl extends AbstractDAO implements UserDao {

	public UserDaoImpl() {
		super("t_j_user");
	}

	@Override
	public int addUser(UserDTO dto) {
		try {
			insert("add", dto);
			return 1;
		} catch (Exception e) {
			return 0;
		}

	}

	@Override
	public int deleteUser(String id) {
		try {
			delete("deleteUserById", newParam("id", id));
			return 1;
		} catch (Exception e) {
			return 0;
		}

	}

	@Override
	public int update(Map<String, Object> map) {
		try {
			update("updateUser", map);
			return 1;
		} catch (Exception e) {
			return 0;
		}

	}

	@Override
	public List<UserDTO> selectList(String pageIndex, String pageSum, String userId, String city) {
		return queryForList("getUserList", UserDTO.class, newParam("pageIndex", Integer.parseInt(pageIndex)),
				newParam("pageSum", Integer.parseInt(pageSum)), newParam("userId", userId),newParam("city", city));
	}

	@Override
	public int getSum(String userId, String city) {
		return (int) function("getSum", newParam("userId", userId),newParam("city", city));
	}

	@Override
	public UserDTO selUserById(String userId) {
		return queryForObject("getUserById", UserDTO.class, newParam("userId", userId));
	}

	@Override
	public List<UserDTO> selectList(List<String> ids) {
		return queryForList("getUserByIds", UserDTO.class, newParam("ids", ids));
	}

	@Override
	public List<UserDTO> selectList() {
		return queryForList("getUserAll", UserDTO.class);
	}

}
