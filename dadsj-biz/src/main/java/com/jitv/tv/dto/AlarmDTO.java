package com.jitv.tv.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.jitv.tv.dto.base.AbstractDTO;

//告警相关表
public class AlarmDTO extends AbstractDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date time;// 告警时间（数据入库时间）

	private String serviceip;// 服务器IP

	private String major;// 专业key

	private String manufactor;// 厂家

	private String devicetype;// 设备类型

	private String title;// 告警标题

	private String manufactorid;// 厂家ID

	private String alarmlevel;// 告警级别

	private String describe;// 告警描述

	private String other;// 备注

	private String col1;// 备用字段1

	private Integer id;

	@Override
	public void fromDbMap(Map<String, Object> dbMap) {
		this.time = toDate(dbMap, "time");
		this.serviceip = toString(dbMap, "serviceip");
		this.major = toString(dbMap, "major");
		this.manufactor = toString(dbMap, "manufactor");
		this.devicetype = toString(dbMap, "devicetype");
		this.title = toString(dbMap, "title");
		this.manufactorid = toString(dbMap, "manufactorid");
		this.alarmlevel = toString(dbMap, "alarmlevel");
		this.describe = toString(dbMap, "describe");
		this.other = toString(dbMap, "other");
		this.col1 = toString(dbMap, "col1");
		this.id = toInteger(dbMap, "id");

	}

	@Override
	public Map<String, Object> toDbMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("time", time);
		map.put("serviceip", serviceip);
		map.put("major", major);
		map.put("manufactor", manufactor);
		map.put("devicetype", devicetype);
		map.put("title", title);
		map.put("manufactorid", manufactorid);
		map.put("alarmlevel", alarmlevel);
		map.put("describe", describe);
		map.put("other", other);
		map.put("col1", col1);
		map.put("id", id);
		return map;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getServiceip() {
		return serviceip;
	}

	public void setServiceip(String serviceip) {
		this.serviceip = serviceip;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getManufactor() {
		return manufactor;
	}

	public void setManufactor(String manufactor) {
		this.manufactor = manufactor;
	}

	public String getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getManufactorid() {
		return manufactorid;
	}

	public void setManufactorid(String manufactorid) {
		this.manufactorid = manufactorid;
	}

	public String getAlarmlevel() {
		return alarmlevel;
	}

	public void setAlarmlevel(String alarmlevel) {
		this.alarmlevel = alarmlevel;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getCol1() {
		return col1;
	}

	public void setCol1(String col1) {
		this.col1 = col1;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
