package com.jitv.tv.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.jitv.tv.dto.base.AbstractDTO;

public class ARoleResourcesDTO extends AbstractDTO implements Serializable{
	private String id;
	private String roleId;
	private String resourcesId;
	private Date createTime;
	private Date updateTime;
	@Override
	public void fromDbMap(Map<String, Object> dbMap) {
		this.id = toString(dbMap, "id");
		this.roleId = toString(dbMap, "roleid");
		this.resourcesId = toString(dbMap, "resourcesid"); 
		this.createTime = toDate(dbMap, "createtime");
		this.updateTime = toDate(dbMap, "updatetime");
		
	}
	@Override
	public Map<String, Object> toDbMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("roleId", roleId);
		map.put("resourcesId", resourcesId); 
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
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getResourcesId() {
		return resourcesId;
	}
	public void setResourcesId(String resourcesId) {
		this.resourcesId = resourcesId;
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
