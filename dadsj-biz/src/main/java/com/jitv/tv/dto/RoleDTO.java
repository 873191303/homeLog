package com.jitv.tv.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.jitv.tv.dto.base.AbstractDTO;

public class RoleDTO extends AbstractDTO implements Serializable{
	private String id;
	private String name;
	private String describe;
	private Date createTime;
	private Date updateTime;
	private String createUser;
	private int type;//0表示非超级管理员角色1表示是超级管理员角色
	@Override
	public void fromDbMap(Map<String, Object> dbMap) { 
		this.id = toString(dbMap, "id");
		this.name = toString(dbMap, "name");
		this.describe = toString(dbMap, "describes"); 
		this.createTime = toDate(dbMap, "createtime");
		this.updateTime = toDate(dbMap, "updatetime");
		this.createUser = toString(dbMap, "createuser");
		this.type = toInteger(dbMap, "type");
	}

	@Override
	public Map<String, Object> toDbMap() { 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("name", name);
		map.put("describe", describe); 
		map.put("createTime", createTime);
		map.put("updateTime", updateTime);
		map.put("createUser", createUser);
		map.put("type", type);
		return map;
	}
	

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
}
