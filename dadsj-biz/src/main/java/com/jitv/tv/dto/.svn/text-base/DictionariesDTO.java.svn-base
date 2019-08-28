package com.jitv.tv.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.jitv.tv.dto.base.AbstractDTO;

public class DictionariesDTO extends AbstractDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private int type;

	private String typeKey;

	private String typeValue;

	private String parent;

	private String other;

	private Date createTime;

	private Date updateTime;

	private String COL;

	@Override
	public void fromDbMap(Map<String, Object> dbMap) {
		this.id = toLong(dbMap, "id");
		this.type = toInteger(dbMap, "type");
		this.typeKey = toString(dbMap, "typekey");
		this.typeValue = toString(dbMap, "typevalue");
		this.parent = toString(dbMap, "parent");
		this.other = toString(dbMap, "other");
		this.createTime = toDate(dbMap, "createtime");
		this.updateTime = toDate(dbMap, "updatetime");
		this.COL = toString(dbMap, "col");

	}

	@Override
	public Map<String, Object> toDbMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("type", type);
		map.put("typeKey", typeKey);
		map.put("typeValue", typeValue);
		map.put("parent", parent);
		map.put("other", other);
		map.put("createTime", createTime);
		map.put("updateTime", updateTime);
		map.put("COL", COL);
		return map;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTypeKey() {
		return typeKey;
	}

	public void setTypeKey(String typeKey) {
		this.typeKey = typeKey;
	}

	public String getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
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

	public String getCOL() {
		return COL;
	}

	public void setCOL(String cOL) {
		COL = cOL;
	}

}
