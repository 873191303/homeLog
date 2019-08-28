package com.jitv.tv.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.jitv.tv.dto.base.AbstractDTO;

public class UserDTO extends AbstractDTO implements Serializable {

	private String userId;
	private String userPassword;
	private String city;
	private String creator;// 创建者
	private long mobile;
	private String email;
	private Date createTime;
	private Date updateTime;

	@Override
	public void fromDbMap(Map<String, Object> dbMap) {
		this.userId = toString(dbMap, "userid");
		this.userPassword = toString(dbMap, "userpassword");
		this.city = toString(dbMap, "city");
		this.creator = toString(dbMap, "creator");
		this.mobile = toLong(dbMap, "mobile");
		this.email = toString(dbMap, "email");
		this.createTime = toDate(dbMap, "createtime");
		this.updateTime = toDate(dbMap, "updatetime");
	}

	@Override
	public Map<String, Object> toDbMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("userPassword", userPassword);
		map.put("city", city);
		map.put("creator", creator);
		map.put("mobile", mobile);
		map.put("email", email);
		map.put("createTime", createTime);
		map.put("updateTime", updateTime);
		return map;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
