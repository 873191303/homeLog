package com.jitv.tv.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.jitv.tv.dto.base.AbstractDTO;

public class HostDTO extends AbstractDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date time;// 时间 精确到小时

	private Integer netid;// 流向ID

	private Integer usergrpid;// 用户群id

	private Integer direction;// 请求方向

	private Integer siteid;// 网站id

	private String host;// host域名

	private String ipaddr;// IP地址

	private Long upbyte;// 上行字节

	private Long dnbyte;// 下行字节

	private Long count;// 总数

	@Override
	public void fromDbMap(Map<String, Object> dbMap) {
		this.time = toDate(dbMap, "time");
		this.netid = toInteger(dbMap, "netid");
		this.usergrpid = toInteger(dbMap, "usergrpid");
		this.direction = toInteger(dbMap, "direction");
		this.siteid = toInteger(dbMap, "siteid");
		this.host = toString(dbMap, "host");
		this.ipaddr = toString(dbMap, "ipaddr");
		this.upbyte = toLong(dbMap, "upbyte");
		this.dnbyte = toLong(dbMap, "dnbyte");
		this.count = toLong(dbMap, "count");

	}

	@Override
	public Map<String, Object> toDbMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("time", time);
		map.put("netid", netid);
		map.put("usergrpid", usergrpid);
		map.put("direction", direction);
		map.put("siteid", siteid);
		map.put("host", host);
		map.put("ipaddr", ipaddr);
		map.put("upbyte", upbyte);
		map.put("dnbyte", dnbyte);
		map.put("count", count);
		return map;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getNetid() {
		return netid;
	}

	public void setNetid(Integer netid) {
		this.netid = netid;
	}

	public Integer getUsergrpid() {
		return usergrpid;
	}

	public void setUsergrpid(Integer usergrpid) {
		this.usergrpid = usergrpid;
	}

	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	public Integer getSiteid() {
		return siteid;
	}

	public void setSiteid(Integer siteid) {
		this.siteid = siteid;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getIpaddr() {
		return ipaddr;
	}

	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}

	public Long getUpbyte() {
		return upbyte;
	}

	public void setUpbyte(Long upbyte) {
		this.upbyte = upbyte;
	}

	public Long getDnbyte() {
		return dnbyte;
	}

	public void setDnbyte(Long dnbyte) {
		this.dnbyte = dnbyte;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
	
	

}
