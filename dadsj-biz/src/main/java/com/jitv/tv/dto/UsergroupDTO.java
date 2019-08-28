package com.jitv.tv.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.jitv.tv.dto.base.AbstractDTO;
//用户群资源管理表—流量统计 （2）	运营商管理表 公用同一个实体类

public class UsergroupDTO extends AbstractDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	@Override
	public void fromDbMap(Map<String, Object> dbMap) {
		this.id = toInteger(dbMap, "id");
		this.name = toString(dbMap, "name");

	}

	@Override
	public Map<String, Object> toDbMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("name", name);
		return map;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
