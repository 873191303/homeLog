package com.jitv.tv.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.jitv.tv.dto.base.AbstractDTO;

public class ResourcesDTO  extends AbstractDTO implements Serializable{

	private String id;
	private String resourcesName;
	private String resourcesModular;
	private String parent;
	private int sequenceNumber;
	private String resourcePath;
	private String authorizationType;
	private String systemType;
	private Date createTime;
	private Date updateTime;
	@Override
	public void fromDbMap(Map<String, Object> dbMap) { 
		this.id = toString(dbMap, "id");
		this.resourcesName = toString(dbMap, "resourcesname");
		this.resourcesModular = toString(dbMap, "resourcesmodular");
		this.parent = toString(dbMap, "parent");
		this.sequenceNumber = toInteger(dbMap, "sequencenumber");
		this.resourcePath = toString(dbMap, "resourcepath");
		this.authorizationType = toString(dbMap, "authorizationtype");
		this.systemType = toString(dbMap, "systemtype"); 
		this.createTime = toDate(dbMap, "createtime");
		this.updateTime = toDate(dbMap, "updatetime");
	}

	@Override
	public Map<String, Object> toDbMap() { 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("resourcesName", resourcesName);
		map.put("resourcesModular", resourcesModular);
		map.put("parent", parent);
		map.put("sequenceNumber", sequenceNumber); 
		map.put("resourcePath", resourcePath); 
		map.put("authorizationType", authorizationType); 
		map.put("systemType", systemType); 
		map.put("createTime", createTime);
		map.put("updateTime", updateTime);
		return map;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getResourcesName() {
		return resourcesName;
	}

	public void setResourcesName(String resourcesName) {
		this.resourcesName = resourcesName;
	}

	public String getResourcesModular() {
		return resourcesModular;
	}

	public void setResourcesModular(String resourcesModular) {
		this.resourcesModular = resourcesModular;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	public String getAuthorizationType() {
		return authorizationType;
	}

	public void setAuthorizationType(String authorizationType) {
		this.authorizationType = authorizationType;
	}

	public String getSystemType() {
		return systemType;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType;
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

}
