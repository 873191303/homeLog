package com.jitv.tv.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.jitv.tv.dto.base.AbstractDTO;

public class BehaviorDto extends AbstractDTO implements Serializable {

	private int id;

	private String serviceIP;

	private String userName;

	private String type;

	private Date createTime;

	private String other;

	private String spare;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getServiceIP() {
		return serviceIP;
	}

	public void setServiceIP(String serviceIP) {
		this.serviceIP = serviceIP;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getSpare() {
		return spare;
	}

	public void setSpare(String spare) {
		this.spare = spare;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void fromDbMap(Map<String, Object> dbMap) {
		this.id = toInteger(dbMap, "id");
		this.serviceIP = toString(dbMap, "serviceIP");
		this.userName = toString(dbMap, "userName");
		this.type = toString(dbMap, "type");
		this.createTime = toDate(dbMap, "createTime");
		this.other = toString(dbMap, "other");
		this.spare = toString(dbMap, "spare");

	}

	@Override
	public Map<String, Object> toDbMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("serviceIP", serviceIP);
		map.put("userName", userName);
		map.put("type", type);
		map.put("createTime", createTime);
		map.put("other", other);
		map.put("spare", spare);
		return map;
	}

}
