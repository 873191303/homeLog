package com.jitv.tv.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.jitv.tv.dto.base.AbstractDTO;

public class IpDTO extends AbstractDTO implements Serializable {

	private long id;

	private String ip;

	private String city;

	private Date createTime;

	private Date updateTime;

	private String other;

	private String COL;

	@Override
	public void fromDbMap(Map<String, Object> dbMap) {
		this.id = toLong(dbMap, "id");
		this.ip = toString(dbMap, "ip");
		this.city = toString(dbMap, "city");
		this.createTime = toDate(dbMap, "createtime");
		this.updateTime = toDate(dbMap, "updatetime");
		this.other = toString(dbMap, "other");
		this.COL = toString(dbMap, "col");

	}

	@Override
	public Map<String, Object> toDbMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("ip", ip);
		map.put("city", city);
		map.put("createTime", createTime);
		map.put("updateTime", updateTime);
		map.put("other", other);
		map.put("COL", COL);
		return map;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getCOL() {
		return COL;
	}

	public void setCOL(String cOL) {
		COL = cOL;
	}

}
