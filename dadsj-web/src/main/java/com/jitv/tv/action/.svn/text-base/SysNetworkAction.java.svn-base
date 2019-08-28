package com.jitv.tv.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aspire.commons.rpc.RpcBean;
import com.aspire.commons.rpc.RpcContext;
import com.aspire.commons.rpc.RpcContexts;
import com.aspire.commons.rpc.RpcMethod;
import com.jitv.tv.service.SysNetworkService;

/**
 * 系统网管相关接口
 * 
 * @author Administrator
 *
 */
@RpcBean
public class SysNetworkAction {

	private final static Logger logger = LoggerFactory.getLogger(SysNetworkAction.class);

	private SysNetworkService sysNetworkService;

	public void setSysNetworkService(SysNetworkService sysNetworkService) {
		this.sysNetworkService = sysNetworkService;
	}

	// 系统网管统一请求后台接口 wxg 2019-04-12
	@RpcMethod("/action/sysnetwork/get/alarm")
	public void alarm() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		String title = rc.params().get("title") == null ? "" : (String) rc.params().get("title");
		String searchValue = rc.params().get("searchValue") == null ? "" : (String) rc.params().get("searchValue");// 搜索参数
		Map<String, Object> map = sysNetworkService.alarm(startIndex, pageSize, title, searchValue);
		rc.setData(map);
	}

	// Radius处理信息查询 wxg 2019-01-09
	@RpcMethod("/action/sysnetwork/get/radius")
	public void getRadius() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		String searchValue = rc.params().get("searchValue") == null ? "" : (String) rc.params().get("searchValue");// 搜索参数
		Map<String, Object> map = sysNetworkService.getRadius(startIndex, pageSize, searchValue);
		rc.setData(map);
	}

	// 远端用户日志 wxg 2019-01-10
	@RpcMethod("/action/sysnetwork/get/userlog")
	public void UserLog() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		String searchValue = rc.params().get("searchValue") == null ? "" : (String) rc.params().get("searchValue");// 搜索参数
		Map<String, Object> map = sysNetworkService.UserLog(startIndex, pageSize, searchValue);
		rc.setData(map);
	}

	// 本地用户日志 wxg 2019-01-10
	@RpcMethod("/action/sysnetwork/get/local/userlog")
	public void LocalUserLog() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		String searchValue = rc.params().get("searchValue") == null ? "" : (String) rc.params().get("searchValue");// 搜索参数
		Map<String, Object> map = sysNetworkService.LocalUserLog(startIndex, pageSize, searchValue);
		rc.setData(map);
	}

	// 系统告警 wxg 2019-01-10
	@RpcMethod("/action/sysnetwork/get/sys/alarm")
	public void SysAlarm() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		String searchValue = rc.params().get("searchValue") == null ? "" : (String) rc.params().get("searchValue");// 搜索参数
		Map<String, Object> map = sysNetworkService.SysAlarm(startIndex, pageSize, searchValue);
		rc.setData(map);
	}

	// 业务告警 wxg 2019-01-10
	@RpcMethod("/action/sysnetwork/get/business/alarm")
	public void BusinessAlarm() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		String searchValue = rc.params().get("searchValue") == null ? "" : (String) rc.params().get("searchValue");// 搜索参数
		String title = "Workflow作业异常";
		String title2 = "进程down";

		Map<String, Object> map = sysNetworkService.BusinessAlarm(startIndex, pageSize, title, title2, searchValue);
		rc.setData(map);
	}

	// 非法用户登录 非法用户告警 wxg 2019-01-10
	@RpcMethod("/action/sysnetwork/get/user/alarm")
	public void UserAlarm() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		String searchValue = rc.params().get("searchValue") == null ? "" : (String) rc.params().get("searchValue");// 搜索参数
		Map<String, Object> map = sysNetworkService.UserAlarm(startIndex, pageSize, searchValue);
		rc.setData(map);
	}

	// 数据采集异常 wxg 2019-01-10
	@RpcMethod("/action/sysnetwork/get/data/abnormal")
	public void DataException() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		String searchValue = rc.params().get("searchValue") == null ? "" : (String) rc.params().get("searchValue");// 搜索参数
		Map<String, Object> map = sysNetworkService.DataException(startIndex, pageSize, searchValue);
		rc.setData(map);
	}

	// SFTP操作异常 wxg 2019-01-10
	@RpcMethod("/action/sysnetwork/get/sftp/abnormal")
	public void SFTPException() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		String searchValue = rc.params().get("searchValue") == null ? "" : (String) rc.params().get("searchValue");// 搜索参数
		Map<String, Object> map = sysNetworkService.SFTPException(startIndex, pageSize, searchValue);
		rc.setData(map);
	}

	// 文件操作异常 wxg 2019-01-10
	@RpcMethod("/action/sysnetwork/get/file/abnormal")
	public void FileException() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		String searchValue = rc.params().get("searchValue") == null ? "" : (String) rc.params().get("searchValue");// 搜索参数
		Map<String, Object> map = sysNetworkService.FileException(startIndex, pageSize, searchValue);
		rc.setData(map);
	}

	// NAT摘要信息 wxg 2019-01-10
	@RpcMethod("/action/sysnetwork/get/nat/message")
	public void NATMessage() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		String searchValue = rc.params().get("searchValue") == null ? "" : (String) rc.params().get("searchValue");// 搜索参数
		Map<String, Object> map = sysNetworkService.NATMessage(startIndex, pageSize, searchValue);
		rc.setData(map);
	}

	// DPL处理信息 wxg 2019-01-10
	@RpcMethod("/action/sysnetwork/get/dpl/message")
	public void DPLMessage() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		String searchValue = rc.params().get("searchValue") == null ? "" : (String) rc.params().get("searchValue");// 搜索参数
		Map<String, Object> map = sysNetworkService.DPLMessage(startIndex, pageSize, searchValue);
		rc.setData(map);
	}

	// 告警配置 wxg 2019-01-16
	@RpcMethod("/action/sysnetwork/alarm/configure")
	public void AlarmConfigure() {
		RpcContext rc = RpcContexts.getContext();
		Map<String, Object> map = sysNetworkService.AlarmConfigure();
		rc.setData(map);
	}

	// 验证接口 wxg 2019-01-17
	@RpcMethod("/check/volume")
	public void volume() {
		RpcContext rc = RpcContexts.getContext();
		rc.setData("");
	}

	// 告警配置修改 wxg 2019-01-17
	@RpcMethod("/action/sysnetwork/alarm/configure/update")
	public void AlarmConfigureUpdate() {
		RpcContext rc = RpcContexts.getContext();
		String maxthreshold = rc.params().get("maxthreshold") == null ? "" : (String) rc.params().get("maxthreshold");// 阈值上限
		String minthreshold = rc.params().get("minthreshold") == null ? "" : (String) rc.params().get("minthreshold");// 阈值下限
		String timerange = rc.params().get("timerange") == null ? "" : (String) rc.params().get("timerange");// 时间范围
		String number = rc.params().get("number") == null ? "" : (String) rc.params().get("number");// 发生次数
		String alarmlevel = rc.params().get("alarmlevel") == null ? "" : (String) rc.params().get("alarmlevel");// 告警级别
		String cpu = rc.params().get("cpu") == null ? "" : (String) rc.params().get("cpu");// CPU使用率
		String memory = rc.params().get("memory") == null ? "" : (String) rc.params().get("memory");// 内存使用率
		String hd = rc.params().get("hd") == null ? "" : (String) rc.params().get("hd");// 硬盘使用率 物理内存使用率
		String partition = rc.params().get("partition") == null ? "" : (String) rc.params().get("partition");// 分区使用率
		String network = rc.params().get("network") == null ? "" : (String) rc.params().get("network");// 网络带宽 网络宽带占用率
		String project = rc.params().get("project") == null ? "" : (String) rc.params().get("project");// 项目监控

		Map<String, Object> map = sysNetworkService.AlarmConfigureUpdate(maxthreshold, minthreshold, timerange, number,
				alarmlevel, cpu, memory, hd, partition, network, project);
		rc.setData(map);
	}

	@RpcMethod("/action/sysnetwork/alarm/configure/test")
	public void test() {
		RpcContext rc = RpcContexts.getContext();
		String cmd = rc.params().get("cmd") == null ? "" : (String) rc.params().get("cmd");// 阈值上限
		String back = sysNetworkService.executeLinuxCmd(cmd);
		rc.setData(back);
	}
}
