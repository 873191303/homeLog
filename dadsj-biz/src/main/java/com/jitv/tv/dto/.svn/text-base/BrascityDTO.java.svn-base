package com.jitv.tv.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.jitv.tv.dto.base.AbstractDTO;

public class BrascityDTO extends AbstractDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String ip;

	private String name;

	private String account;// 用户账号

	private Date time;

	private String manufactor;// 厂家

	private String city;// 城市

	private String col1;// 备用字段1

	private String col2;// 备用字段2
	
	private Long count;//总数

	@Override
	public void fromDbMap(Map<String, Object> dbMap) {
		this.id = toLong(dbMap, "id");
		this.ip = toString(dbMap, "ip");
		this.name = toString(dbMap, "name");
		this.account = toString(dbMap, "account");
		this.time = toDate(dbMap, "time");
		this.manufactor = toString(dbMap, "manufactor");
		this.city = toString(dbMap, "city");
		this.col1 = toString(dbMap, "col1");
		this.col2 = toString(dbMap, "col2");
		this.count = toLong(dbMap, "count");
	}

	@Override
	public Map<String, Object> toDbMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("ip", ip);
		map.put("name", name);
		map.put("account", account);
		map.put("time", time);
		map.put("manufactor", manufactor);
		map.put("city", city);
		map.put("col1", col1);
		map.put("col2", col2);
		map.put("count", count);
		return map;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getManufactor() {
		return manufactor;
	}

	public void setManufactor(String manufactor) {
		this.manufactor = manufactor;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCol1() {
		return col1;
	}

	public void setCol1(String col1) {
		this.col1 = col1;
	}

	public String getCol2() {
		return col2;
	}

	public void setCol2(String col2) {
		this.col2 = col2;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
	

}
