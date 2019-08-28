package com.jitv.tv.action;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aspire.commons.rpc.RpcBean;
import com.aspire.commons.rpc.RpcContext;
import com.aspire.commons.rpc.RpcContexts;
import com.aspire.commons.rpc.RpcMethod;
import com.jitv.tv.service.ARoleResourcesService;
import com.jitv.tv.service.ResourcesService;
import com.jitv.tv.util.ErrorCode;

@RpcBean
public class ResourcesAction {
	private final static Logger logger = LoggerFactory.getLogger(ResourcesAction.class);

	private ResourcesService resourcesService;

	private ARoleResourcesService aroleResourcesService;

	public void setAroleResourcesService(ARoleResourcesService aroleResourcesService) {
		this.aroleResourcesService = aroleResourcesService;
	}

	public void setResourcesService(ResourcesService resourcesService) {
		this.resourcesService = resourcesService;
	}

	@RpcMethod("/action/resources/list")
	public void roleList() {
		RpcContext rc = RpcContexts.getContext();
		List<Map<String, Object>> list = resourcesService.selectTree();
		rc.setData(list);
	}

	// 新增资源 wxg 2019-01-05
	@RpcMethod("/action/resources/add")
	public void ResourceAdd() {
		RpcContext rc = RpcContexts.getContext();
		String authorizationType = rc.params().get("authType") == null ? "" : (String) rc.params().get("authType");// 授权类型
		String parentId = rc.params().get("parentId") == null ? "" : (String) rc.params().get("parentId");// 父级元素
		String resourcePath = rc.params().get("resPath") == null ? "" : (String) rc.params().get("resPath");// 资源路径
		String resourcesModular = rc.params().get("modeName") == null ? "" : (String) rc.params().get("modeName");// 资源模块
		String resourcesName = rc.params().get("resName") == null ? "" : (String) rc.params().get("resName");// 资源名称
		int sequenceNumber = rc.params().get("itemSeq") == null ? -1
				: Integer.parseInt((String) rc.params().get("itemSeq"));// 序列号
		String systemType = rc.params().get("sysType") == null ? "" : (String) rc.params().get("sysType");// 系统类型
		resourcesService.add(authorizationType, parentId, resourcePath, resourcesModular, resourcesName, sequenceNumber,
				systemType);
	}

	// 删除资源 wxg 2019-01-05
	@RpcMethod("/action/resources/delete")
	public void ResourceDel() {
		RpcContext rc = RpcContexts.getContext();
		String id = rc.params().get("id") == null ? "" : (String) rc.params().get("id");
		if (StringUtils.isEmpty(id)) {
			throw ErrorCode.E700;
		}
		try {
			resourcesService.delete(id);
		} catch (Exception e) {
			throw ErrorCode.E333;
		}
	}

	// 修改资源 wxg 2019-01-05
	@RpcMethod("/action/resources/update")
	public void ResourceUpdate() {
		RpcContext rc = RpcContexts.getContext();
		String id = rc.params().get("id") == null ? "" : (String) rc.params().get("id");
		String authorizationType = rc.params().get("authType") == null ? "" : (String) rc.params().get("authType");// 授权类型
		String parentId = rc.params().get("parentId") == null ? "" : (String) rc.params().get("parentId");// 父级元素
		String resourcePath = rc.params().get("resPath") == null ? "" : (String) rc.params().get("resPath");// 资源路径
		String resourcesModular = rc.params().get("modeName") == null ? "" : (String) rc.params().get("modeName");// 资源模块
		String resourcesName = rc.params().get("resName") == null ? "" : (String) rc.params().get("resName");// 资源名称
		int sequenceNumber = rc.params().get("itemSeq") == null ? -1
				: Integer.parseInt((String) rc.params().get("itemSeq"));// 序列号
		String systemType = rc.params().get("sysType") == null ? "" : (String) rc.params().get("sysType");// 系统类型
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("authorizationType", authorizationType);
		map.put("parentId", parentId);
		map.put("resourcePath", resourcePath);
		map.put("resourcesModular", resourcesModular);
		map.put("resourcesName", resourcesName);
		map.put("sequenceNumber", sequenceNumber);
		map.put("systemType", systemType);
		try {
			resourcesService.update(map);
		} catch (Exception e) {
			throw ErrorCode.E333;
		}

	}

	// 根据角色ID查询对应的资源 wxg 2019-01-06
	@RpcMethod("/action/resources/list/roleids")
	public void selResourceByRoleId() {
		RpcContext rc = RpcContexts.getContext();
		String ids = rc.params().get("roleids") == null ? "" : (String) rc.params().get("roleids");
		if (StringUtils.isNotEmpty(ids)) {
			List<String> list = new LinkedList<String>();
			if (ids.contains(",")) {
				String[] arr = ids.split(",");
				list = Arrays.asList(arr);
			} else {
				list.add(ids);
			}
			List<Map<String, Object>> result = aroleResourcesService.selectListByIds(list);
			rc.setData(result);
		} else {
			throw ErrorCode.E700;
		}
	}

	// 角色修改对应的资源 wxg 2019-01-06
	@RpcMethod("/action/resources/update/roleid")
	public void updateARoleResources() {
		RpcContext rc = RpcContexts.getContext();
		String roleid = rc.params().get("roleId") == null ? "" : (String) rc.params().get("roleId");
		String resourcesIds = rc.params().get("resourcesIds") == null ? "" : (String) rc.params().get("resourcesIds");
		if (StringUtils.isEmpty(roleid)) {
			throw ErrorCode.E700;
		} else {
			try {
				aroleResourcesService.updateByRoleid(roleid, resourcesIds);
				rc.setData("ok");
			} catch (Exception e) {
				throw ErrorCode.E333;
			}
			
		}
	}

}
