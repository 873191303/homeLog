package com.jitv.tv.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.jitv.tv.dto.base.AbstractDTO;


//数据库管理相关实体类
public class DataBaseTimeDTO extends AbstractDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String type;

	private Integer time;

	private String other;

	@Override
	public void fromDbMap(Map<String, Object> dbMap) {
		this.id = toLong(dbMap, "id");
		this.type = toString(dbMap, "type");
		this.time = toInteger(dbMap, "time");
		this.other = toString(dbMap, "other");

	}

	@Override
	public Map<String, Object> toDbMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("type", type);
		map.put("time", time);
		map.put("other", other);
		return map;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

}
