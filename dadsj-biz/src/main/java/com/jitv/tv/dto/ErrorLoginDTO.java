package com.jitv.tv.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.jitv.tv.dto.base.AbstractDTO;
//非法用户相关实体类
//Illegal User Related Entity Class
public class ErrorLoginDTO extends AbstractDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date time;

	private String username;

	private String password;

	private String reason;// 原因

	private String outip;// 外网IP

	private String serviceip;// 服务器IP

	private String other;// 备注

	@Override
	public void fromDbMap(Map<String, Object> dbMap) {
		this.time = toDate(dbMap, "time");
		this.username = toString(dbMap, "username");
		this.password = toString(dbMap, "password");
		this.reason = toString(dbMap, "reason");
		this.outip = toString(dbMap, "outip");
		this.serviceip = toString(dbMap, "serviceip");
		this.other = toString(dbMap, "other");

	}

	@Override
	public Map<String, Object> toDbMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("time", time);
		map.put("username", username);
		map.put("password", password);
		map.put("reason", reason);
		map.put("outip", outip);
		map.put("serviceip", serviceip);
		map.put("other", other);
		return map;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getOutip() {
		return outip;
	}

	public void setOutip(String outip) {
		this.outip = outip;
	}

	public String getServiceip() {
		return serviceip;
	}

	public void setServiceip(String serviceip) {
		this.serviceip = serviceip;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

}
