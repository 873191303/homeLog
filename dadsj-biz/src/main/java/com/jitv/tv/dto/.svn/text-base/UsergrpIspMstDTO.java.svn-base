package com.jitv.tv.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.jitv.tv.dto.base.AbstractDTO;

//用户群流量统计
public class UsergrpIspMstDTO extends AbstractDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date time;

	private Integer usergrpid;

	private Integer ispid;

	private String ipversion;

	private Long upbps;

	private Long dnbps;

	private Long updisbps;

	private Long dndisbps;

	private Long upmaxbps;

	private Long dnmaxbps;

	private Long upminbps;

	private Long dnminbps;

	@Override
	public void fromDbMap(Map<String, Object> dbMap) {
		this.time = toDate(dbMap, "time");
		this.usergrpid = toInteger(dbMap, "usergrpid");
		this.ispid = toInteger(dbMap, "ispid");
		this.ipversion = toString(dbMap, "ipversion");
		this.upbps = toLong(dbMap, "upbps");
		this.dnbps = toLong(dbMap, "dnbps");
		this.updisbps = toLong(dbMap, "updisbps");
		this.dndisbps = toLong(dbMap, "dndisbps");
		this.upmaxbps = toLong(dbMap, "upmaxbps");
		this.dnmaxbps = toLong(dbMap, "dnmaxbps");
		this.upminbps = toLong(dbMap, "upminbps");
		this.dnminbps = toLong(dbMap, "dnminbps");

	}

	@Override
	public Map<String, Object> toDbMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("time", time);
		map.put("usergrpid", usergrpid);
		map.put("ispid", ispid);
		map.put("ipversion", ipversion);
		map.put("upbps", upbps);
		map.put("dnbps", dnbps);
		map.put("updisbps", updisbps);
		map.put("dndisbps", dndisbps);
		map.put("upmaxbps", upmaxbps);
		map.put("dnmaxbps", dnmaxbps);
		map.put("upminbps", upminbps);
		map.put("dnminbps", dnminbps);
		return map;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getUsergrpid() {
		return usergrpid;
	}

	public void setUsergrpid(Integer usergrpid) {
		this.usergrpid = usergrpid;
	}

	public Integer getIspid() {
		return ispid;
	}

	public void setIspid(Integer ispid) {
		this.ispid = ispid;
	}

	public String getIpversion() {
		return ipversion;
	}

	public void setIpversion(String ipversion) {
		this.ipversion = ipversion;
	}

	public Long getUpbps() {
		return upbps;
	}

	public void setUpbps(Long upbps) {
		this.upbps = upbps;
	}

	public Long getDnbps() {
		return dnbps;
	}

	public void setDnbps(Long dnbps) {
		this.dnbps = dnbps;
	}

	public Long getUpdisbps() {
		return updisbps;
	}

	public void setUpdisbps(Long updisbps) {
		this.updisbps = updisbps;
	}

	public Long getDndisbps() {
		return dndisbps;
	}

	public void setDndisbps(Long dndisbps) {
		this.dndisbps = dndisbps;
	}

	public Long getUpmaxbps() {
		return upmaxbps;
	}

	public void setUpmaxbps(Long upmaxbps) {
		this.upmaxbps = upmaxbps;
	}

	public Long getDnmaxbps() {
		return dnmaxbps;
	}

	public void setDnmaxbps(Long dnmaxbps) {
		this.dnmaxbps = dnmaxbps;
	}

	public Long getUpminbps() {
		return upminbps;
	}

	public void setUpminbps(Long upminbps) {
		this.upminbps = upminbps;
	}

	public Long getDnminbps() {
		return dnminbps;
	}

	public void setDnminbps(Long dnminbps) {
		this.dnminbps = dnminbps;
	}

}
