package com.jitv.tv.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.jitv.tv.dto.base.AbstractDTO;

//大文件实体类
public class LargeFileDTO extends AbstractDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Date Time;

	private String hostip;

	private String host;

	private Integer netid;

	private String filename;

	private String filetype;

	private Integer Length;

	private Long Count;

	@Override
	public void fromDbMap(Map<String, Object> dbMap) {
		this.id = toLong(dbMap, "id");
		this.Time = toDate(dbMap, "time");
		this.hostip = toString(dbMap, "hostip");
		this.host = toString(dbMap, "host");
		this.netid = toInteger(dbMap, "netid");
		this.filename = toString(dbMap, "filename");
		this.filetype = toString(dbMap, "filetype");
		this.Length = toInteger(dbMap, "length");
		this.Count = toLong(dbMap, "count");

	}

	@Override
	public Map<String, Object> toDbMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("Time", Time);
		map.put("hostip", hostip);
		map.put("host", host);
		map.put("netid", netid);
		map.put("filename", filename);
		map.put("filetype", filetype);
		map.put("Length", Length);
		map.put("Count", Count);
		return map;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTime() {
		return Time;
	}

	public void setTime(Date time) {
		Time = time;
	}

	public String getHostip() {
		return hostip;
	}

	public void setHostip(String hostip) {
		this.hostip = hostip;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getNetid() {
		return netid;
	}

	public void setNetid(Integer netid) {
		this.netid = netid;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public Integer getLength() {
		return Length;
	}

	public void setLength(Integer length) {
		Length = length;
	}

	public Long getCount() {
		return Count;
	}

	public void setCount(Long count) {
		Count = count;
	}

}
