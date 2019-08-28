package com.jitv.tv.action;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aspire.commons.rpc.RpcBean;
import com.aspire.commons.rpc.RpcContext;
import com.aspire.commons.rpc.RpcContexts;
import com.aspire.commons.rpc.RpcMethod;
import com.jitv.tv.service.LargeFileService;
import com.jitv.tv.service.TopStatisticsService;

/**
 * TOP统计分析相关接口
 * 
 * @author Administrator
 *
 */
@RpcBean
public class TopStatisticsAction {

	private final static Logger logger = LoggerFactory.getLogger(TopStatisticsAction.class);

	private TopStatisticsService topStatisticsService;
	
	//注入大文件业务层
	private LargeFileService largeFileService;

	public void setLargeFileService(LargeFileService largeFileService) {
		this.largeFileService = largeFileService;
	}

	public void setTopStatisticsService(TopStatisticsService topStatisticsService) {
		this.topStatisticsService = topStatisticsService;
	}

	// 经分用户上网日志 wxg 2019-01-11
	@RpcMethod("/action/top/get/userlog")
	public void UserLog() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		String searchValue = rc.params().get("searchValue") == null ? "" : (String) rc.params().get("searchValue");// 根据IP搜索
		logger.info("经分用户上网日志搜索值searchValue：" + searchValue);
		Map<String, Object> map = topStatisticsService.UserLog(startIndex, pageSize);
		rc.setData(map);
	}

	// 主机流量 wxg 2019-01-11
	@RpcMethod("/action/top/get/hostflow")
	public void HostFlow() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		String searchValue = rc.params().get("searchValue") == null ? "" : (String) rc.params().get("searchValue");// 根据IP搜索
		logger.info("主机流量搜索值searchValue：" + searchValue);
		//Map<String, Object> map = topStatisticsService.HostFlow(startIndex, pageSize);
		Map<String, Object> map = largeFileService.HostFlow(startIndex, pageSize);
		rc.setData(map);
	}

	// 大文件访问量 wxg 2019-01-11
	@RpcMethod("/action/top/get/fileflow")
	public void FileFlow() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		String searchValue = rc.params().get("searchValue") == null ? "" : (String) rc.params().get("searchValue");// 根据IP搜索
		//logger.info("大文件访问量搜索值searchValue：" + searchValue);
		//Map<String, Object> map = topStatisticsService.FileFlow(startIndex, pageSize);
		Map<String, Object> map = largeFileService.FileFlow(startIndex, pageSize);
		rc.setData(map);
	}

	// 用户排名 wxg 2019-01-11
	@RpcMethod("/action/top/get/userranking")
	public void UserRanking() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		String searchValue = rc.params().get("searchValue") == null ? "" : (String) rc.params().get("searchValue");// 根据IP搜索
		logger.info("访问监控搜索值searchValue：" + searchValue);
		Map<String, Object> map = topStatisticsService.UserRanking(startIndex, pageSize);
		rc.setData(map);
	}

	// ******************网站TOPN流量*******************//
	// 国内网站TOPN流量 wxg 2019-01-11
	@RpcMethod("/action/top/domestic/flow")
	public void DomesticFlow() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		Map<String, Object> map = topStatisticsService.DomesticFlow(startIndex, pageSize);
		rc.setData(map);
	}

	// 国外网站TOPN流量 wxg 2019-01-11
	@RpcMethod("/action/top/abroad/flow")
	public void AbroadFlow() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		Map<String, Object> map = topStatisticsService.AbroadFlow(startIndex, pageSize);
		rc.setData(map);
	}

	// ******************网站TOPN访问******************//
	// 国内网站TOPN访问 wxg 2019-01-11
	@RpcMethod("/action/top/domestic/visit")
	public void DomesticVisit() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		Map<String, Object> map = topStatisticsService.DomesticVisit(startIndex, pageSize);
		rc.setData(map);
	}

	// 国外网站TOPN流量 wxg 2019-01-11
	@RpcMethod("/action/top/abroad/visit")
	public void AbroadVisit() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		Map<String, Object> map = topStatisticsService.AbroadVisit(startIndex, pageSize);
		rc.setData(map);
	}

	// *************************二级域名TOPN流量******************//

	// 国内 二级域名TOPN流量 wxg 2019-01-11
	@RpcMethod("/action/top/domestic/twolevel/flow")
	public void DomesticTwoFlow() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		Map<String, Object> map = topStatisticsService.DomesticTwoFlow(startIndex, pageSize);
		rc.setData(map);
	}

	// 国外 二级域名TOPN流量 wxg 2019-01-11
	@RpcMethod("/action/top/abroad/twolevel/flow")
	public void AbroadTwoFlow() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		Map<String, Object> map = topStatisticsService.AbroadTwoFlow(startIndex, pageSize);
		rc.setData(map);
	}

	// *****************************二级域名TOPN访问********************//

	// 国内 二级域名TOPN访问 wxg 2019-01-11
	@RpcMethod("/action/top/domestic/twolevel/visit")
	public void DomesticTwoVisit() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		Map<String, Object> map = topStatisticsService.DomesticTwoVisit(startIndex, pageSize);
		rc.setData(map);
	}

	// 国外 二级域名TOPN访问 wxg 2019-01-11
	@RpcMethod("/action/top/abroad/twolevel/visit")
	public void AbroadTwoVisit() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		Map<String, Object> map = topStatisticsService.AbroadTwoVisit(startIndex, pageSize);
		rc.setData(map);
	}

	// *******************HOSTTOPN流量*******************//

	// 国内 HOSTTOPN流量 wxg 2019-01-11
	@RpcMethod("/action/top/domestic/host/flow")
	public void DomesticHostFlow() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		Map<String, Object> map = topStatisticsService.DomesticHostFlow(startIndex, pageSize);
		rc.setData(map);
	}

	// 国外 HOSTTOPN流量 wxg 2019-01-11
	@RpcMethod("/action/top/abroad/host/flow")
	public void AbroadHostFlow() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		Map<String, Object> map = topStatisticsService.AbroadHostFlow(startIndex, pageSize);
		rc.setData(map);
	}

	// ************************HOSTTOPN访问******************//
	// 国内 HOSTTOPN访问 wxg 2019-01-11
	@RpcMethod("/action/top/domestic/host/visit")
	public void DomesticHostVisit() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		Map<String, Object> map = topStatisticsService.DomesticHostVisit(startIndex, pageSize);
		rc.setData(map);
	}

	// 国外 HOSTTOPN访问 wxg 2019-01-11
	@RpcMethod("/action/top/abroad/host/visit")
	public void AbroadHostVisit() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		Map<String, Object> map = topStatisticsService.AbroadHostVisit(startIndex, pageSize);
		rc.setData(map);
	}

	// *****************************IPTOPN流量************************//

	// 国内 IPTOPN流量 wxg 2019-01-11
	@RpcMethod("/action/top/domestic/ip/flow")
	public void DomesticIpFlow() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		Map<String, Object> map = topStatisticsService.DomesticIpFlow(startIndex, pageSize);
		rc.setData(map);
	}

	// 国外 IPTOPN流量 wxg 2019-01-11
	@RpcMethod("/action/top/abroad/ip/flow")
	public void AbroadIpFlow() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		Map<String, Object> map = topStatisticsService.AbroadIpFlow(startIndex, pageSize);
		rc.setData(map);
	}

	// *****************************IPTOPN访问*****************************//

	// 国内 IPTOPN访问 wxg 2019-01-11
	@RpcMethod("/action/top/domestic/ip/visit")
	public void DomesticIpVisit() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		Map<String, Object> map = topStatisticsService.DomesticIpVisit(startIndex, pageSize);
		rc.setData(map);
	}

	// 国外 IPTOPN访问 wxg 2019-01-11
	@RpcMethod("/action/top/abroad/ip/visit")
	public void AbroadIpVisit() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		Map<String, Object> map = topStatisticsService.AbroadIpVisit(startIndex, pageSize);
		rc.setData(map);
	}
}
