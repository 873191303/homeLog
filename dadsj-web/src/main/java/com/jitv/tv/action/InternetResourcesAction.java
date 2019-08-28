package com.jitv.tv.action;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aspire.commons.rpc.RpcBean;
import com.aspire.commons.rpc.RpcContext;
import com.aspire.commons.rpc.RpcContexts;
import com.aspire.commons.rpc.RpcMethod;
import com.jitv.tv.service.InternetResourcesService;

/**
 * 互联网资源相关接口
 * 
 * @author Administrator
 *
 */
@RpcBean
public class InternetResourcesAction {

	private final static Logger logger = LoggerFactory.getLogger(InternetResourcesAction.class);

	private InternetResourcesService internetResourcesService;

	public void setInternetResourcesService(InternetResourcesService internetResourcesService) {
		this.internetResourcesService = internetResourcesService;
	}

	// 用户群流量 wxg 2019-01-11
	@RpcMethod("/action/usergroup/get/flow")
	public void UserGroupGetFlow() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		String dls = rc.params().get("dls") == null ? "" : (String) rc.params().get("dls");// 运营商
		String User = rc.params().get("user") == null ? "" : (String) rc.params().get("user");// 用户
		String link = rc.params().get("link") == null ? "" : (String) rc.params().get("link");// 链路
		logger.info("用户群流量搜索值dls：" + dls + " User:" + User + " link" + link);
		Map<String, Object> map = internetResourcesService.UserGroupGetFlow(startIndex, pageSize, dls, User, link);
		rc.setData(map);
	}

	// 用户群流量 excle导出wxg 2019-01-20
	@RpcMethod("/action/usergroup/excel")
	public void UserGroupExcel() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		String dls = rc.params().get("dls") == null ? "" : (String) rc.params().get("dls");// 运营商
		String User = rc.params().get("user") == null ? "" : (String) rc.params().get("user");// 用户
		String link = rc.params().get("link") == null ? "" : (String) rc.params().get("link");// 链路
		logger.info("用户群流量搜索值dls：" + dls + " User:" + User + " link" + link);
		internetResourcesService.UserGroupExcel(startIndex, pageSize, dls, User, link, rc);
	}

	// 运营商流量 wxg 2019-01-11
	@RpcMethod("/action/operator/get/flow")
	public void OperatorGetFlow() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		String searchValue = rc.params().get("searchValue") == null ? "" : (String) rc.params().get("searchValue");//
		logger.info("运营商流量搜索值searchValue：" + searchValue);
		Map<String, Object> map = internetResourcesService.OperatorGetFlow(startIndex, pageSize, searchValue);
		rc.setData(map);

	}

	// 网站流量 wxg 2019-01-11
	@RpcMethod("/action/website/get/flow")
	public void WebsiteGetFlow() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		String dls = rc.params().get("dls") == null ? "" : (String) rc.params().get("dls");// 运营商
		String User = rc.params().get("user") == null ? "" : (String) rc.params().get("user");// 用户
		String type = rc.params().get("type") == null ? "" : (String) rc.params().get("type");// 网站类别
		String website = rc.params().get("website") == null ? "" : (String) rc.params().get("website");// 网站
		logger.info("网站流量搜索值dls：" + dls + " User:" + User + " type" + type + " website:" + website);
		Map<String, Object> map = internetResourcesService.WebsiteGetFlow(startIndex, pageSize, dls, User, type,
				website);
		rc.setData(map);
	}

	// 网站流量百分比 wxg 2019-01-20
	@RpcMethod("/action/website/get/percentage")
	public void WebsitePercentage() {
		RpcContext rc = RpcContexts.getContext();
		List<Map<String, Object>> list = internetResourcesService.WebsitePercentage();
		rc.setData(list);
	}

	// 网站流量 excel 导出 wxg 2019-01-23
	@RpcMethod("/action/website/get/excel")
	public void WebsitePercentageExcel() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		String dls = rc.params().get("dls") == null ? "" : (String) rc.params().get("dls");// 运营商
		String User = rc.params().get("user") == null ? "" : (String) rc.params().get("user");// 用户
		String type = rc.params().get("type") == null ? "" : (String) rc.params().get("type");// 网站类别
		String website = rc.params().get("website") == null ? "" : (String) rc.params().get("website");// 网站
		logger.info("网站流量搜索值dls：" + dls + " User:" + User + " type" + type + " website:" + website);
		internetResourcesService.WebsitePercentageExcel(startIndex, pageSize, dls, User, type, website, rc);
	}

	// HOST流量 wxg 2019-01-11
	@RpcMethod("/action/host/get/flow")
	public void HostGetFlow() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		String dls = rc.params().get("dls") == null ? "" : (String) rc.params().get("dls");// 运营商
		String User = rc.params().get("user") == null ? "" : (String) rc.params().get("user");// 用户
		String type = rc.params().get("type") == null ? "" : (String) rc.params().get("type");// 网站类别
		String host = rc.params().get("host") == null ? "" : (String) rc.params().get("host");// host
		logger.info("HOST流量dls：" + dls + " User:" + User + " type" + type + " host:" + host);
		Map<String, Object> map = internetResourcesService.HostGetFlow(startIndex, pageSize, dls, User, type, host);
		rc.setData(map);
	}

	// Host流量 excel 导出 wxg 2019-01-23
	@RpcMethod("/action/host/get/flow/excel")
	public void HostGetFlowExcel() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		String dls = rc.params().get("dls") == null ? "" : (String) rc.params().get("dls");// 运营商
		String User = rc.params().get("user") == null ? "" : (String) rc.params().get("user");// 用户
		String type = rc.params().get("type") == null ? "" : (String) rc.params().get("type");// 网站类别
		String host = rc.params().get("host") == null ? "" : (String) rc.params().get("host");// host
		logger.info("HOST流量dls：" + dls + " User:" + User + " type" + type + " host:" + host);
		internetResourcesService.HostGetFlowExcel(startIndex, pageSize, dls, User, type, host, rc);
	}

	// HOST流量百分比 wxg 2019-01-20
	@RpcMethod("/action/host/get/percentage")
	public void Percentage() {
		RpcContext rc = RpcContexts.getContext();
		List<Map<String, Object>> list = internetResourcesService.Percentage();
		rc.setData(list);
	}

	// 大文件 wxg 2019-01-11
	@RpcMethod("/action/bigdate/get")
	public void BigData() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		String searchValue = rc.params().get("searchValue") == null ? "" : (String) rc.params().get("searchValue");
		Map<String, Object> map = internetResourcesService.BigData(startIndex, pageSize, searchValue);
		logger.info("大文件searchValue：" + searchValue);
		rc.setData(map);
	}

	// 配置管理 wxg 2019-01-24
	@RpcMethod("/action/configuration/get")
	public void configuration() {
		RpcContext rc = RpcContexts.getContext();
		Map<String, Object> map = internetResourcesService.configuration();
		rc.setData(map);
	}
	//配置管理 大文件阈值修改 wxg 2019-01-24
	@RpcMethod("/action/configuration/bigdata/update")
	public void bigdataUpdate() {
		RpcContext rc = RpcContexts.getContext();
		String value = rc.params().get("value") == null ? "" : (String)rc.params().get("value");
		Map<String, Object> map = internetResourcesService.bigdataUpdate(value);
		rc.setData(map);
	}
	//配置管理 用户删除 wxg 2019-01-24
	@RpcMethod("/action/configuration/userconfiguration/delete")
	public void userConfigurationDelete() {
		RpcContext rc = RpcContexts.getContext();
		String id = rc.params().get("id") == null ? "" : (String)rc.params().get("id");
		Map<String, Object> map = internetResourcesService.userConfigurationDelete(id);
		rc.setData(map);
	}
	//配置管理 用户更新 wxg 2019-01-24
	@RpcMethod("/action/configuration/userconfiguration/update")
	public void userConfigurationUpdate() {
		RpcContext rc = RpcContexts.getContext();
		String id = rc.params().get("id") == null ? "" : (String)rc.params().get("id");
		String value = rc.params().get("value") == null ? "" : (String)rc.params().get("value");
		String type = rc.params().get("type") == null ? "" : (String)rc.params().get("type");
		Map<String, Object> map = internetResourcesService.userConfigurationUpdate(id,value,type);
		rc.setData(map);
	}
	//配置管理 新增统一入口 wxg 2019-01-24
	@RpcMethod("/action/configuration/userconfiguration/add")
	public void userConfigurationAdd() {
		RpcContext rc = RpcContexts.getContext();
		String value = rc.params().get("value") == null ? "" : (String)rc.params().get("value");
		String type = rc.params().get("type") == null ? "" : (String)rc.params().get("type");
		Map<String, Object> map = internetResourcesService.userConfigurationAdd(value,type);
		rc.setData(map);
	}
	//配置管理 网站分类删除 wxg 2019-01-24
	@RpcMethod("/action/configuration/websiteconfiguration/delete")
	public void websiteConfigurationDelete() {
		RpcContext rc = RpcContexts.getContext();
		String id = rc.params().get("id") == null ? "" : (String)rc.params().get("id");
		Map<String, Object> map = internetResourcesService.websiteConfigurationDelete(id);
		rc.setData(map);
	}
	//配置管理 网站IP配置删除 wxg 2019-01-24
	@RpcMethod("/action/configuration/ipconfiguration/delete")
	public void ipConfigurationDelete() {
		RpcContext rc = RpcContexts.getContext();
		String id = rc.params().get("id") == null ? "" : (String)rc.params().get("id");
		Map<String, Object> map = internetResourcesService.ipConfigurationDelete(id);
		rc.setData(map);
	}
	//配置管理 网站分类删除 wxg 2019-01-24
	@RpcMethod("/action/configuration/domainconfiguration/delete")
	public void domainConfigurationDelete() {
		RpcContext rc = RpcContexts.getContext();
		String id = rc.params().get("id") == null ? "" : (String)rc.params().get("id");
		Map<String, Object> map = internetResourcesService.domainConfiguration(id);
		rc.setData(map);
	}
	//配置管理 网站二级域名删除 wxg 2019-01-24
	@RpcMethod("/action/configuration/secondconfiguration/delete")
	public void secondConfigurationDelete() {
		RpcContext rc = RpcContexts.getContext();
		String id = rc.params().get("id") == null ? "" : (String)rc.params().get("id");
		Map<String, Object> map = internetResourcesService.secondConfiguration(id);
		rc.setData(map);
	}
	//配置管理 网站运营商删除 wxg 2019-01-24
	@RpcMethod("/action/configuration/operatorconfiguration/delete")
	public void operatorConfigurationDelete() {
		RpcContext rc = RpcContexts.getContext();
		String id = rc.params().get("id") == null ? "" : (String)rc.params().get("id");
		Map<String, Object> map = internetResourcesService.operatorConfigurationDelete(id);
		rc.setData(map);
	}

}
