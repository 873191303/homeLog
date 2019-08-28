package com.jitv.tv.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jitv.tv.dao.ResourcesDao;
import com.jitv.tv.dto.ResourcesDTO;
import com.jitv.tv.dto.RoleDTO;
import com.jitv.tv.service.ResourcesService;
import com.jitv.tv.util.JitvGuidUtil;

public class ResourcesServiceImpl implements ResourcesService{
	private ResourcesDao resourcesDao;
	
	 

	public void setResourcesDao(ResourcesDao resourcesDao) {
		this.resourcesDao = resourcesDao;
	}

	@Override
	public void add(String authorizationType,String parent,String resourcePath,String resourcesModular,
			String resourcesName,int sequenceNumber,String systemType
			) {
		ResourcesDTO dto = new ResourcesDTO();
		dto.setCreateTime(new Date());
		dto.setUpdateTime(new Date()); 
		dto.setId(JitvGuidUtil.getGuid());
		dto.setAuthorizationType(authorizationType);
		dto.setParent(parent);
		dto.setResourcePath(resourcePath);
		dto.setResourcesModular(resourcesModular);
		dto.setResourcesName(resourcesName);
		dto.setSequenceNumber(sequenceNumber);
		dto.setSystemType(systemType);
		resourcesDao.add(dto);
		
	}

	@Override
	public void delete(String id) {
		resourcesDao.delete(id);
		
	}

	@Override
	public void update(Map<String, Object> map) {
		resourcesDao.update(map);
		
	}

	@Override
	public List<Map<String, Object>> selectList() { 
		List<ResourcesDTO> dtos = resourcesDao.selectList();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for(ResourcesDTO dto : dtos) {
			list.add(dto.toDbMap());
		}
		return list; 
	}
	private void selectTreeTmp(List<ResourcesDTO> dtos,Map<String, Object> node){
		for(int i=0;i<dtos.size();i++) {
			ResourcesDTO dto = dtos.get(i);
			String id = node.get("id").toString();
			if(dto.getParent().equals(id)) {
				if(node.get("children")==null) {
					List<Map<String, Object>> nodes = new ArrayList<Map<String, Object>>();
		 
					Map<String, Object> node2 = getUiMap(dto.getResourcesName(),dto.getResourcesModular()
							,dto.getId(),dto.getResourcePath()
							,id,1,dto.getSequenceNumber()+""
							, dto.getCreateTime()
							, dto.getUpdateTime()
							);
					nodes.add(node2);
					node.put("children", nodes);
				}else {
					List<Map<String, Object>> nodes = (List<Map<String, Object>>)node.get("children");
  
					Map<String, Object> node2 = getUiMap(dto.getResourcesName(),dto.getResourcesModular()
							,dto.getId(),dto.getResourcePath()
							,id,nodes.size()+1,dto.getSequenceNumber()+""
							, dto.getCreateTime()
							, dto.getUpdateTime()
							);
					
					nodes.add(node2);
					node.put("children", nodes);
				} 
				 
			} 
			 
		}
		List<Map<String, Object>> nodes = (List<Map<String, Object>>)node.get("children");
		if(nodes!=null) {
			Collections.sort(nodes, new Comparator<Map<String, Object>>() {
	            public int compare(Map arg0, Map arg1) {
	            	Integer i0 = Integer.valueOf(arg0.get("itemSeq").toString());
	            	Integer i1 = Integer.valueOf(arg1.get("itemSeq").toString());
	                return i0.compareTo(i1);
	            }
	        });
			for(Map map:nodes) {
				selectTreeTmp(dtos,map);
			} 
		} 
	}
	public List<Map<String, Object>> selectTree(){ 
		List<ResourcesDTO> dtos = resourcesDao.selectList(); 
		return selectTree(dtos);
	}
	
	public List<Map<String, Object>> selectTree(List<ResourcesDTO> dtos ){
		List<Map<String, Object>> tree = new ArrayList<Map<String, Object>>(); 
		//构造根目录
		Map<String, Object> root = new HashMap<String, Object>();
		for(ResourcesDTO dto: dtos) {
			if(dto.getParent().equals("-1")) {   
				root = getUiMap(dto.getResourcesName(),dto.getResourcesModular()
						,dto.getId(),dto.getResourcePath()
						,dto.getParent(),1,dto.getSequenceNumber()+""
						, dto.getCreateTime()
						, dto.getUpdateTime()
						);
				break;
			}
		} 
	    
		selectTreeTmp(dtos,root);
		tree.add(root);
		return tree;
	}
	public static Map<String, Object> getUiMap(String resourcesName,String resourcesModular
			,String id,String resourcePath
			,String parent,int depth,String sequenceNumber
			,Date createTime,Date updateTime
			){
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("resName",resourcesName );
		root.put("modeName", resourcePath);
		root.put("id",id);
		root.put("resPath", resourcesModular);
		root.put("parentId", parent);
		root.put("depth", 1);
		root.put("itemSeq", sequenceNumber); 
		root.put("createdDate", createTime);
		root.put("updatedDate", updateTime); 
		
		root.put("groupId", "");
		root.put("createdBy", "1");
		root.put("updatedBy", "1");
		root.put("sysType", "0000");
		root.put("authType", "0");
		root.put("funType", "1");
		root.put("defaultRoleId", "");
		root.put("description", "");
		
		root.put("createdByName", "1");
		root.put("updatedByName", "1");
		root.put("parentName", "1");
		
		
		return root;
	}

}
