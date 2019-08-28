package com.jitv.tv.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aspire.commons.rpc.RpcBean;
import com.aspire.commons.rpc.RpcContext;
import com.aspire.commons.rpc.RpcContexts;
import com.aspire.commons.rpc.RpcMethod;
import com.jitv.tv.dto.ARoleUserDTO;
import com.jitv.tv.dto.ResourcesDTO;
import com.jitv.tv.dto.UserDTO;
import com.jitv.tv.service.ARoleUserService;
import com.jitv.tv.service.AdminService;
import com.jitv.tv.service.BehaviorService;
import com.jitv.tv.service.RoleService;
import com.jitv.tv.util.ErrorCode;

@RpcBean
public class AdminAction {
	private final static Logger logger = LoggerFactory.getLogger(AdminAction.class);

	private AdminService adminService;

	private RoleService roleService;

	private ARoleUserService aroleUserService;

	private BehaviorService behaviorService;

	public void setBehaviorService(BehaviorService behaviorService) {
		this.behaviorService = behaviorService;
	}

	public void setAroleUserService(ARoleUserService aroleUserService) {
		this.aroleUserService = aroleUserService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	// 用户新增 wxg 2019-01-03
	@RpcMethod("/action/sys/user/add")
	public void addUser() {
		RpcContext rc = RpcContexts.getContext();
		String userId = rc.params().get("loginName") == null ? "" : (String) rc.params().get("loginName");
		String creator = rc.params().get("creator") == null ? "" : (String) rc.params().get("creator");
		String userPassword = rc.params().get("password") == null ? "" : (String) rc.params().get("password");
		String city = rc.params().get("userName") == null ? "" : (String) rc.params().get("userName");
		String mobile = rc.params().get("telephone") == null ? "" : (String) rc.params().get("telephone");
		String email = rc.params().get("email") == null ? "" : (String) rc.params().get("email");
		String createTime = rc.params().get("createTime") == null ? "" : (String) rc.params().get("createTime");
		String updateTime = rc.params().get("updateTime") == null ? "" : (String) rc.params().get("updateTime");
		int count = adminService.adduser(userId, userPassword, city, mobile, email, createTime, updateTime, creator);
		if (count > 0) {
			rc.setData("新增用户成功");
			logger.info("新增用户成功");
		} else {
			logger.info("新增用户失败");
			throw ErrorCode.E333;

		}

	}

	// 用户删除 wxg 2019-01-03
	@RpcMethod("/action/sys/user/del")
	public void delUser() {
		RpcContext rc = RpcContexts.getContext();
		String userId = rc.params().get("userId") == null ? "" : (String) rc.params().get("userId");
		int count = adminService.delUser(userId);
		if (count > 0) {
			rc.setData("删除用户成功");
		} else {
			logger.info("删除用户失败");
			throw ErrorCode.E333;
		}

	}

	// 用户更新 wxg 2019-01-03
	@RpcMethod("/action/sys/user/update")
	public void updateUser() {
		RpcContext rc = RpcContexts.getContext();
		String userId = rc.params().get("loginName") == null ? "" : (String) rc.params().get("loginName");
		String userPassword = rc.params().get("password") == null ? "" : (String) rc.params().get("password");
		String city = rc.params().get("userName") == null ? "" : (String) rc.params().get("userName");
		String mobile = rc.params().get("telephone") == null ? "" : (String) rc.params().get("telephone");
		String email = rc.params().get("email") == null ? "" : (String) rc.params().get("email");
		String createTime = rc.params().get("createTime") == null ? "" : (String) rc.params().get("createTime");
		String updateTime = rc.params().get("updateTime") == null ? "" : (String) rc.params().get("updateTime");
		int count = adminService.updateuser(userId, userPassword, city, mobile, email, createTime, updateTime);
		if (count > 0) {
			rc.setData("修改用户成功");
		} else {
			logger.info("修改用户失败");
			throw ErrorCode.E333;
		}
	}
	// 用户更新个人信息 wxg 2019-01-03
	@RpcMethod("/action/sys/user/update/one")
	public void update() {
		RpcContext rc = RpcContexts.getContext();
		String userId = rc.params().get("loginName") == null ? "" : (String) rc.params().get("loginName");

		String userPassword = rc.params().get("password") == null ? "" : (String) rc.params().get("password");
		String city = rc.params().get("userName") == null ? "" : (String) rc.params().get("userName");
		String mobile = rc.params().get("telephone") == null ? "" : (String) rc.params().get("telephone");
		String email = rc.params().get("email") == null ? "" : (String) rc.params().get("email");
		String createTime = rc.params().get("createTime") == null ? "" : (String) rc.params().get("createTime");
		String updateTime = rc.params().get("updateTime") == null ? "" : (String) rc.params().get("updateTime");
		int count = adminService.updat(userId, userPassword, city, mobile, email, createTime, updateTime);
		if (count > 0) {
			rc.setData("修改用户成功");
		} else {
			logger.info("修改用户失败");
			throw ErrorCode.E333;
		}
	}

	// 用户列表查询 wxg 2019-01-03
	@RpcMethod("/action/sys/user/sel")
	public void selUser() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		String userId = rc.params().get("userId") == null ? "" : (String) rc.params().get("userId");
		String city = rc.params().get("city") == null ? "" : (String) rc.params().get("city");
		String searchValue = rc.params().get("searchValue") == null ? "" : (String) rc.params().get("searchValue");
		if (StringUtils.isNotEmpty(searchValue)) {
			userId = searchValue;
		}
		List<Map<String, Object>> list = adminService.seluser(startIndex, pageSize, userId, city);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("iTotalRecords", adminService.getSum(userId, city));
		map.put("record", list);
		rc.setData(map);
	}

	// 根据用户Id查询 wxg 2019-01-03
	@RpcMethod("/action/sys/user/userid")
	public void getUserById() {
		RpcContext rc = RpcContexts.getContext();
		String userId = rc.params().get("userId") == null ? "" : (String) rc.params().get("userId");
		Map<String, Object> map = adminService.selUserById(userId);
		rc.setData(map);
	}

	// 用户列表检查名称是否存在 wxg 2019-01-04 check/loginName
	@RpcMethod("/action/sys/user/checkloginname")
	public void checkLoginName() {
		RpcContext rc = RpcContexts.getContext();
		String value = rc.params().get("value") == null ? "" : (String) rc.params().get("value");
		int count = adminService.getSum(value, "");
		if (count > 0) {
			rc.setData("error");
			logger.info("新增用户无重复名称");
			// throw ErrorCode.E998;
		} else {
			rc.setData("success");

		}
	}

	// 用户修改密码 wxg 2019-01-04
	@RpcMethod("/action/sys/user/setpwd")
	public void setUserPwd() {
		RpcContext rc = RpcContexts.getContext();
		String userId = rc.params().get("userId") == null ? "" : (String) rc.params().get("userId");
		String newPassword = rc.params().get("newPassword") == null ? "" : (String) rc.params().get("newPassword");
		String password = rc.params().get("password") == null ? "" : (String) rc.params().get("password");
		String result = adminService.setUserPwd(userId, newPassword, password);
		if ("error".contentEquals(result)) {
			throw ErrorCode.E111;
		} else {
			rc.setData(result);
		}
	}

	// 测试接口selectUserHaveRoles
	@RpcMethod("/action/sys/user/demo")
	public void demo() {
		RpcContext rc = RpcContexts.getContext();
		String ids = rc.params().get("ids").toString();
		List<Map<String, Object>> list = adminService.selectList(ids);
		rc.setData(list);
	}

	// 获取用户归属角色 wxg 2019-01-05
	@RpcMethod("/action/sys/role/userid")
	public void setUserByUserId() {
		RpcContext rc = RpcContexts.getContext();
		String userId = rc.params().get("userId") == null ? "" : (String) rc.params().get("userId");
		List<ARoleUserDTO> list = aroleUserService.selectList(userId, "");
		rc.setData(list);
	}

	// 查询所有角色 wxg 2019-01-05
	@RpcMethod("/action/sys/user/role")
	public void setUserByUserId2() {
		RpcContext rc = RpcContexts.getContext();
		String userId = rc.params().get("userId") == null ? "" : (String) rc.params().get("userId");
		List<Map<String, Object>> list = roleService.selectList("", 0, 1000);
		rc.setData(list);
	}

	// 用户选择角色 wxg 2019-01-05
	@RpcMethod("/action/sys/user/addrole")
	public void addRole() {
		RpcContext rc = RpcContexts.getContext();
		String userId = rc.params().get("userId") == null ? "" : (String) rc.params().get("userId");
		String roleId = rc.params().get("roles") == null ? "" : (String) rc.params().get("roles");
		try {
			aroleUserService.updateArole(userId, roleId);
			rc.setData("ok");
		} catch (Exception e) {
			throw ErrorCode.E333;
		}

	}

	// 根据用户id查询用户所拥有的资源 wxg 2019-01-06
	@RpcMethod("/action/sys/user/sel/resource")
	public void selResource() {
		RpcContext rc = RpcContexts.getContext();
		String userId = rc.params().get("userId") == null ? "" : (String) rc.params().get("userId");
		if (StringUtils.isEmpty(userId)) {
			throw ErrorCode.E700;
		} else {
			List<ResourcesDTO> list = adminService.selResourceByUserId(userId);
			rc.setData(list);
		}
	}

	// *****************************系统资源告警日志**********************//

	// 告警日志 wxg 2019-01-12
	@RpcMethod("/action/sys/alarm/log")
	public void AlarmLog() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		String searchValue = rc.params().get("searchValue") == null ? "" : (String) rc.params().get("searchValue");// 搜索参数
		Map<String, Object> map = adminService.AlarmLog(startIndex, pageSize, searchValue);
		rc.setData(map);
	}

	// ************************系统资源操作日志*************************//

	// 操作日志 wxg 2019-01-12
	@RpcMethod("/action/sys/operation/log")
	public void OperationLog() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		String searchValue = rc.params().get("searchValue") == null ? "" : (String) rc.params().get("searchValue");// 搜索参数
		List<Map<String, Object>> list = behaviorService.select(startIndex, pageSize, "", searchValue);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("Result", list);
		result.put("totalItems", behaviorService.getSum(searchValue, ""));
		rc.setData(result);
	}

	// 运行日志 wxg 2019-01-16
	@RpcMethod("/action/sys/function/log")
	public void FunctionLog() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		String searchValue = rc.params().get("searchValue") == null ? "" : (String) rc.params().get("searchValue");// 搜索参数
		Map<String, Object> map = adminService.FunctionLog(startIndex, pageSize, searchValue);
		rc.setData(map);
	}
	
	// 根据用户查询角色表 wxg 2019-01-18
	@RpcMethod("/action/sys/user/sel/role")
	public void selRole() {
		RpcContext rc = RpcContexts.getContext();
		String id = rc.params().get("id") == null ? "" : (String) rc.params().get("id");
		List<Map<String, Object>> list = adminService.selRole(id);
		rc.setData(list);
	}

}
