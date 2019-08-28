package com.jitv.tv.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.jitv.tv.dto.base.AbstractDTO;

//ipTOPN 统计相关实体类
public class IpTopnDTO extends AbstractDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Date time;

	private Integer iptype;

	private String ipaddr;

	private Long upbyte;

	private Long dnbyte;

	private Long uppkt;

	private Long dnpkt;

	@Override
	public void fromDbMap(Map<String, Object> dbMap) {
		this.id = toLong(dbMap, "id");
		this.time = toDate(dbMap, "time");
		this.iptype = toInteger(dbMap, "iptype");
		this.ipaddr = toString(dbMap, "ipaddr");
		this.upbyte = toLong(dbMap, "upbyte");
		this.dnbyte = toLong(dbMap, "dnbyte");
		this.uppkt = toLong(dbMap, "uppkt");
		this.dnpkt = toLong(dbMap, "dnpkt");

	}

	@Override
	public Map<String, Object> toDbMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("time", time);
		map.put("iptype", iptype);
		map.put("ipaddr", ipaddr);
		map.put("upbyte", upbyte);
		map.put("dnbyte", dnbyte);
		map.put("uppkt", uppkt);
		map.put("dnpkt", dnpkt);
		return map;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getIptype() {
		return iptype;
	}

	public void setIptype(Integer iptype) {
		this.iptype = iptype;
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

	public Long getUppkt() {
		return uppkt;
	}

	public void setUppkt(Long uppkt) {
		this.uppkt = uppkt;
	}

	public Long getDnpkt() {
		return dnpkt;
	}

	public void setDnpkt(Long dnpkt) {
		this.dnpkt = dnpkt;
	}

}
