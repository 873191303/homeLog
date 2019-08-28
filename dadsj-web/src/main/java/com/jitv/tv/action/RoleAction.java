package com.jitv.tv.action;

  
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aspire.commons.rpc.RpcBean;
import com.aspire.commons.rpc.RpcContext;
import com.aspire.commons.rpc.RpcContexts;
import com.aspire.commons.rpc.RpcMethod;
import com.jitv.tv.service.AdminService;
import com.jitv.tv.service.RoleService;
import com.jitv.tv.web.JitvLogin;

@RpcBean
public class RoleAction {
	private final static Logger logger = LoggerFactory.getLogger(RoleAction.class); 
	
	private RoleService roleService;
	
	private AdminService adminService;
	
	  
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	} 
	@RpcMethod("/action/role/list")
	public void roleList() { 
		RpcContext rc = RpcContexts.getContext();
		int pageIndex = Integer.valueOf((String)rc.params().get("startIndex"));
		int pageSum = Integer.valueOf((String)rc.params().get("pageSize"));
		String name = (String)rc.params().get("searchValue"); 
		List<Map<String, Object>> list = roleService.selectList(name, pageIndex, pageSum); 
		Map<String,Object> map  = new HashMap<String,Object>();
		map.put("iTotalRecords", roleService.listSum(name));
		map.put("list", list);
		rc.setData(map);  
	} 
	
 
	
	@RpcMethod("/action/role/add")
	public void roleAdd() { 
		RpcContext rc = RpcContexts.getContext();
		//String name,String describe
		String name = (String)rc.params().get("name");
		String describe = (String)rc.params().get("describe");
		String createUser = JitvLogin.getUserId(rc.getHttpServletRequest());
		roleService.add(name, describe,createUser);
		rc.setData("ok");
	}
	
	@RpcMethod("/action/role/delete")
	public void roleDelete() { 
		RpcContext rc = RpcContexts.getContext();
		//String name,String describe
		String id = (String)rc.params().get("id"); 
		roleService.delete(id);
		rc.setData("ok");
	}
	
	@RpcMethod("/action/role/get")
	public void roleGet() { 
		RpcContext rc = RpcContexts.getContext();
		//String name,String describe
		String id = (String)rc.params().get("id");  
		rc.setData(roleService.get(id));
	}
	@RpcMethod("/action/role/update")
	public void roleUpdate() { 
		RpcContext rc = RpcContexts.getContext();
		//String name,String describe
		String id = (String)rc.params().get("id"); 
		String name = (String)rc.params().get("name"); 
		String describe = (String)rc.params().get("describe"); 
		roleService.update(id, name, describe);
		rc.setData("ok");
	}
	
	@RpcMethod("/action/role/user/list")
	public void roleuserList() { 
		RpcContext rc = RpcContexts.getContext();
		String roleId = (String)rc.params().get("roleId");  
		Map<String,Object> map =  adminService.selectRoleHaveUsers(roleId); 
		rc.setData(map);  
	}
	@RpcMethod("/action/role/user/update")
	public void roleUserAdd() { 
		RpcContext rc = RpcContexts.getContext();
		//String name,String describe
		String roleId = (String)rc.params().get("roleId");
		String userIdstmp = (String)rc.params().get("userIds");
		String[] userIds = userIdstmp.split(",");
		int sum = roleService.updateRoleToUsers(roleId, userIds);
		rc.setData(sum+"");
	}
}
