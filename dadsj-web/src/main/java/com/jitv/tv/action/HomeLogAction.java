package com.jitv.tv.action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aspire.commons.rpc.RpcBean;
import com.aspire.commons.rpc.RpcContext;
import com.aspire.commons.rpc.RpcContexts;
import com.aspire.commons.rpc.RpcMethod;
import com.jitv.tv.service.HomeLogService;
import com.jitv.tv.util.ErrorCode;
import com.jitv.tv.web.JitvLogin;

/**
 * 家宽日志相关接口
 * 
 * @author Administrator
 *
 */
@RpcBean
public class HomeLogAction {
	private final static Logger logger = LoggerFactory.getLogger(HomeLogAction.class);

	private HomeLogService homeLogService;

	public HomeLogService getHomeLogService() {
		return homeLogService;
	}

	public void setHomeLogService(HomeLogService homeLogService) {
		this.homeLogService = homeLogService;
	}

	// 家宽日志 wxg 2019-01-02
	@RpcMethod("/action/get/getHomeLog")
	public void getHomeLog() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		String asSorting = rc.params().get("asSorting") == null ? "" : (String) rc.params().get("asSorting");// 排序规则
		String pubIP = rc.params().get("pubIP") == null ? "" : (String) rc.params().get("pubIP");// 公网
		String pubPort = rc.params().get("pubPort") == null ? "" : (String) rc.params().get("pubPort");// 公网端口
		String objectiveIP = rc.params().get("objectiveIP") == null ? "" : (String) rc.params().get("objectiveIP");// 目的IP
		String objectivePort = rc.params().get("objectivePort") == null ? ""
				: (String) rc.params().get("objectivePort");// 目的端口
		String objectiveUrl = rc.params().get("objectiveUrl") == null ? "" : (String) rc.params().get("objectiveUrl");// 目的url
		String BrasIP = rc.params().get("BrasIP") == null ? "" : (String) rc.params().get("BrasIP");// BrasIP
		String BrasName = rc.params().get("BrasName") == null ? "" : (String) rc.params().get("BrasName");// Bras名称
		String BdbAdmin = rc.params().get("BdbAdmin") == null ? "" : (String) rc.params().get("BdbAdmin");// 家庭宽带上网账号
		String beginDate = rc.params().get("beginDate") == null ? "" : (String) rc.params().get("beginDate");// 开始时间
		String endDate = rc.params().get("endDate") == null ? "" : (String) rc.params().get("endDate");// 结束时间
		String eventid = rc.params().get("eventid") == null ? "" : (String) rc.params().get("eventid");// uuid
		// 获取用户信息
		String token = (String) rc.params().get("DABIGDATA_TOKEN");
		String userId = JitvLogin.getUserId(token);
		Map<String, Object> map = homeLogService.getHomeLog(startIndex, pageSize, asSorting, pubIP, pubPort,
				objectiveIP, objectivePort, objectiveUrl, BrasIP, BrasName, BdbAdmin, beginDate, endDate, userId,
				eventid);
		rc.setData(map);

	}

	// 家宽日志批量导出excel wxg 2019-03-05
	@RpcMethod("/action/get/homelog/excel")
	public void homeLogExcel() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		String asSorting = rc.params().get("asSorting") == null ? "" : (String) rc.params().get("asSorting");// 排序规则
		String pubIP = rc.params().get("pubIP") == null ? "" : (String) rc.params().get("pubIP");// 公网
		String pubPort = rc.params().get("pubPort") == null ? "" : (String) rc.params().get("pubPort");// 公网端口
		String objectiveIP = rc.params().get("objectiveIP") == null ? "" : (String) rc.params().get("objectiveIP");// 目的IP
		String objectivePort = rc.params().get("objectivePort") == null ? ""
				: (String) rc.params().get("objectivePort");// 目的端口
		String objectiveUrl = rc.params().get("objectiveUrl") == null ? "" : (String) rc.params().get("objectiveUrl");// 目的url
		String BrasIP = rc.params().get("BrasIP") == null ? "" : (String) rc.params().get("BrasIP");// BrasIP
		String BrasName = rc.params().get("BrasName") == null ? "" : (String) rc.params().get("BrasName");// Bras名称
		String BdbAdmin = rc.params().get("BdbAdmin") == null ? "" : (String) rc.params().get("BdbAdmin");// 家庭宽带上网账号
		String beginDate = rc.params().get("beginDate") == null ? "" : (String) rc.params().get("beginDate");// 开始时间
		String endDate = rc.params().get("endDate") == null ? "" : (String) rc.params().get("endDate");// 结束时间
		String eventid = rc.params().get("eventid") == null ? "" : (String) rc.params().get("eventid");
		homeLogService.homeLogExcel(startIndex, pageSize, pubIP, pubPort, objectiveIP, objectivePort, objectiveUrl,
				BrasIP, BrasName,BdbAdmin,beginDate,endDate,eventid, rc);
	}

	// 家宽日志轮询接口 wxg 2019-02-27
	@RpcMethod("/action/get/homelogpolling")
	public void homeLogPolling() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = rc.params().get("startIndex") == null ? "" : (String) rc.params().get("startIndex");// 0
		String pageSize = rc.params().get("pageSize") == null ? "" : (String) rc.params().get("pageSize");// 10
		String asSorting = rc.params().get("asSorting") == null ? "" : (String) rc.params().get("asSorting");// 排序规则
		String pubIP = rc.params().get("pubIP") == null ? "" : (String) rc.params().get("pubIP");// 公网
		String pubPort = rc.params().get("pubPort") == null ? "" : (String) rc.params().get("pubPort");// 公网端口
		String objectiveIP = rc.params().get("objectiveIP") == null ? "" : (String) rc.params().get("objectiveIP");// 目的IP
		String objectivePort = rc.params().get("objectivePort") == null ? ""
				: (String) rc.params().get("objectivePort");// 目的端口
		String objectiveUrl = rc.params().get("objectiveUrl") == null ? "" : (String) rc.params().get("objectiveUrl");// 目的url
		String BrasIP = rc.params().get("BrasIP") == null ? "" : (String) rc.params().get("BrasIP");// BrasIP
		String BrasName = rc.params().get("BrasName") == null ? "" : (String) rc.params().get("BrasName");// Bras名称
		String BdbAdmin = rc.params().get("BdbAdmin") == null ? "" : (String) rc.params().get("BdbAdmin");// 家庭宽带上网账号
		String beginDate = rc.params().get("beginDate") == null ? "" : (String) rc.params().get("beginDate");// 开始时间
		String endDate = rc.params().get("endDate") == null ? "" : (String) rc.params().get("endDate");// 结束时间
		String eventid = rc.params().get("eventid") == null ? "" : (String) rc.params().get("eventid");
		// 获取用户信息
		String token = (String) rc.params().get("DABIGDATA_TOKEN");
		String userId = JitvLogin.getUserId(token);
		Map<String, Object> map = homeLogService.homeLogPolling(startIndex, pageSize, asSorting, pubIP, pubPort,
				objectiveIP, objectivePort, objectiveUrl, BrasIP, BrasName, BdbAdmin, beginDate, endDate, eventid,
				userId);
		rc.setData(map);
	}

	// 家宽日志系统通知接口 wxg 2019-02-23
	@RpcMethod("/action/homeLog/IF_NOTIFY")
	public void IF_NOTIFY() {
		RpcContext rc = RpcContexts.getContext();
		try {
			BufferedReader br = rc.getHttpServletRequest().getReader();
			String str=""; 
			String wholeStr = "";
			while((str = br.readLine()) != null){
				wholeStr += str;
			}
			logger.info("获取到的request的body："+wholeStr);
			String result = homeLogService.redHomeLog(wholeStr);
			logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>请求接口"+wholeStr);
			rc.setData(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 省内日志 wxg 2019-01-03
	@RpcMethod("/action/get/getProvinceLog")
	public void getProvinceLog() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = (String) rc.params().get("startIndex");// 0
		String pageSize = (String) rc.params().get("pageSize");// 10
		String asSorting = (String) rc.params().get("asSorting");// 排序规则
		String pubIP = rc.params().get("pubIP") == null ? "" : (String) rc.params().get("pubIP");// 公网
		String pubPort = rc.params().get("pubPort") == null ? "" : (String) rc.params().get("pubPort");// 公网端口
		String objectiveIP = rc.params().get("objectiveIP") == null ? "" : (String) rc.params().get("objectiveIP");// 目的IP
		String objectivePort = rc.params().get("objectivePort") == null ? ""
				: (String) rc.params().get("objectivePort");// 目的端口
		String objectiveUrl = rc.params().get("objectiveUrl") == null ? "" : (String) rc.params().get("objectiveUrl");// 目的url
		String BrasIP = rc.params().get("BrasIP") == null ? "" : (String) rc.params().get("BrasIP");// BrasIP
		String BrasName = rc.params().get("BrasName") == null ? "" : (String) rc.params().get("BrasName");// Bras名称
		String BdbAdmin = rc.params().get("BdbAdmin") == null ? "" : (String) rc.params().get("BdbAdmin");// 家庭宽带上网账号
		String beginDate = rc.params().get("beginDate") == null ? "" : (String) rc.params().get("beginDate");// 开始时间
		String endDate = rc.params().get("endDate") == null ? "" : (String) rc.params().get("endDate");// 结束时间
		Map<String, Object> map = homeLogService.getProvinceLog(startIndex, pageSize, asSorting);
		rc.setData(map);
	}

	// URL查询 wxg 2019-01-04
	@RpcMethod("/action/get/geturl")
	public void getUrl() {
		RpcContext rc = RpcContexts.getContext();
		String searchValue = rc.params().get("searchValue") == null ? "" : (String) rc.params().get("searchValue");
		String startIndex = (String) rc.params().get("startIndex");// 0
		String pageSize = (String) rc.params().get("pageSize");// 10
		String asSorting = (String) rc.params().get("asSorting");// 排序规则
		Map<String, Object> map = homeLogService.getUrl(searchValue, startIndex, pageSize, asSorting);
		logger.info("URL查询searchValue:" + searchValue);
		rc.setData(map);
	}

	// 协议查询 wxg 2019-01-10
	@RpcMethod("/action/get/agreement")
	public void Agreement() {
		RpcContext rc = RpcContexts.getContext();
		String searchValue = rc.params().get("searchValue") == null ? "" : (String) rc.params().get("searchValue");// 搜索参数
		String startIndex = (String) rc.params().get("startIndex");// 0
		String pageSize = (String) rc.params().get("pageSize");// 10
		Map<String, Object> map = homeLogService.Agreement(searchValue, startIndex, pageSize);
		logger.info("协议查询searchValue:" + searchValue);
		rc.setData(map);
	}

	// 批量日志查询上传excel wxg 2019-01-10
	@RpcMethod("/action/upload/log")
	public void BatchLog() {
		RpcContext rc = RpcContexts.getContext();
		// 使用Apache文件上传组件处理文件上传步骤：
		// 1、创建一个DiskFileItemFactory工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 2、创建一个文件上传解析器
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 解决上传文件名的中文乱码
		upload.setHeaderEncoding("UTF-8");
		// 3、判断提交上来的数据是否是上传表单的数据
		if (!ServletFileUpload.isMultipartContent(rc.getHttpServletRequest())) {
			// 按照传统方式获取数据
			throw ErrorCode.E996;
		}
		try {
			List<FileItem> list = upload.parseRequest(rc.getHttpServletRequest());
			for (FileItem item : list) {
				if (item.isFormField()) {
					String name = item.getFieldName();
					String value = item.getString("UTF-8");
				} else {
					String filename = item.getName();
					InputStream is = item.getInputStream();
					Map<String, Object> map = homeLogService.BatchLog(is);
					rc.setData(map);
					is.close();
//	                    //关闭输出流
//	                    //删除处理文件上传时生成的临时文件
					item.delete();
					return;
				}
			}
		} catch (Exception ex) {

		}
	}

	// 批量日志查询 wxg 2019-01-13
	@RpcMethod("/action/get/log")
	public void getLog() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = (String) rc.params().get("startIndex");// 0
		String pageSize = (String) rc.params().get("pageSize");// 10
//		String objectivePort = rc.params().get("objectivePort") == null ? ""
//				: (String) rc.params().get("objectivePort");// 目的端口
//		String objectivePortURL = rc.params().get("objectivePortURL") == null ? ""
//				: (String) rc.params().get("objectivePortURL");// 目的URL
//		String objectiveIP = rc.params().get("objectiveIP") == null ? "" : (String) rc.params().get("objectiveIP");// 目的IP
//		String beginDate = rc.params().get("beginDate") == null ? "" : (String) rc.params().get("beginDate");// 开始事件
//		String endDate = rc.params().get("endDate") == null ? "" : (String) rc.params().get("endDate");// 结束时间
//		String pubIP = rc.params().get("pubIP") == null ? "" : (String) rc.params().get("pubIP");// 公网IP
//		String pubPort = rc.params().get("pubPort") == null ? "" : (String) rc.params().get("pubPort");// 公网端口
//		String dls = rc.params().get("dls") == null ? "" : (String) rc.params().get("dls");// 上网账号
//		String BrasIP = rc.params().get("BrasIP") == null ? "" : (String) rc.params().get("BrasIP");// BrasIP
//		String BrasName = rc.params().get("BrasName") == null ? "" : (String) rc.params().get("BrasName");// Bras名称

		String exceldate = rc.params().get("searchValue") == null ? "" : (String) rc.params().get("searchValue");
		Map<String, Object> map = homeLogService.getLog(startIndex, pageSize, exceldate);
		rc.setData(map);

	}

	// 家宽日志 批量日志查询 导出 wxg 2019-01-19
	@RpcMethod("/action/homeLog/excel")
	public void Excel() {
		RpcContext rc = RpcContexts.getContext();
		String startIndex = (String) rc.params().get("startIndex");// 0
		String pageSize = (String) rc.params().get("pageSize");// 10
		String exceldate = rc.params().get("searchValue") == null ? "" : (String) rc.params().get("searchValue");
		homeLogService.Excel(startIndex, pageSize, exceldate, rc);
	}

}
