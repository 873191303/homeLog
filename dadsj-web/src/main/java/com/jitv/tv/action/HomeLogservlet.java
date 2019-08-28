package com.jitv.tv.action;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

public class HomeLogservlet extends HttpServlet  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    //处理get请求
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// *********************解析传递过来的参数****************************//
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(10);
		for (int i = 0; i < 10; i++) {// 模拟假数据
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("dls", "dls" + i);// 上网账号
			map.put("BrasName", "BrasName" + i);// Bras名称
			map.put("pubIP", "127.0.0." + i);// 公网ip
			map.put("pubPort", "8080" + i);// 公网端口
			map.put("objectiveIP", "127.0.0." + i);// 目的IP
			map.put("objectivePort", "8080" + i);// 目的端口
			map.put("objectivePortURL", "www" + i);// 目的URL
			map.put("BrasIP", "127.0.0." + i);// BrasIP
			map.put("beginDate", "2006-01-01 00:00:00");// 开始时间
			map.put("endDate", "2019-01-01 12:12:12");// 结束时间
			list.add(map);
		} 
		// *********************返回excle******************************************//
		HSSFWorkbook wb = new HSSFWorkbook();// 声明工
		Sheet sheet = wb.createSheet("批量日志查询表");// 新建表
		sheet.setDefaultColumnWidth(15);// 设置表宽
		HSSFCellStyle style = wb.createCellStyle();
		org.apache.poi.hssf.usermodel.HSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 12);
		HSSFCellStyle headerStyle = wb.createCellStyle();
		org.apache.poi.hssf.usermodel.HSSFFont headerFont = wb.createFont();
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle.setFont(headerFont);
		CellRangeAddress cra0 = new CellRangeAddress(0, 1, 0, 9);
		sheet.addMergedRegion(cra0);
		sheet.setDefaultColumnWidth((short) 15);
		Row row = sheet.createRow(0);
		Cell cell1 = row.createCell(0);

		cell1.setCellValue("批量日志查询表");
		cell1.setCellStyle(headerStyle);
		// 设置字体样式
		org.apache.poi.hssf.usermodel.HSSFFont titleFont = wb.createFont();
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(titleFont);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		Row row1 = sheet.createRow(2);
		Cell cell = row1.createCell(0);
		cell.setCellValue("上网账号");
		cell.setCellStyle(style);
		cell = row1.createCell(1);
		cell.setCellValue("Bras名称");
		cell.setCellStyle(style);
		cell = row1.createCell(2);
		cell.setCellValue("公网IP");
		cell.setCellStyle(style);
		cell = row1.createCell(3);
		cell.setCellValue("公网端口");
		cell.setCellStyle(style);
		cell = row1.createCell(4);
		cell.setCellValue("目的IP");
		cell.setCellStyle(style);
		cell = row1.createCell(5);
		cell.setCellValue("目的端口");
		cell.setCellStyle(style);
		cell = row1.createCell(6);
		cell.setCellValue("目的URL");
		cell.setCellStyle(style);
		cell = row1.createCell(7);
		cell.setCellValue("BrasIP");
		cell.setCellStyle(style);
		cell = row1.createCell(8);
		cell.setCellValue("开始时间");
		cell = row1.createCell(9);
		cell.setCellValue("结束时间");
		cell.setCellStyle(style);
		// 时间转字符串的格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0, imax = list.size(); i < imax; i++) {
			row1 = sheet.createRow(i + 8);
			if (list.get(i).get("dls") == null || "".equals(list.get(i).get("dls"))) {
				row1.createCell(0).setCellValue("-");
			} else {
				row1.createCell(0).setCellValue((String) list.get(i).get("dls"));
			}
			if (list.get(i).get("BrasName") == null || "".equals(list.get(i).get("BrasName"))) {
				row1.createCell(1).setCellValue("-");
			} else {
				row1.createCell(1).setCellValue((String) list.get(i).get("BrasName"));
			}
			if (list.get(i).get("pubIP") == null || "".equals(list.get(i).get("pubIP"))) {
				row1.createCell(2).setCellValue("-");
			} else {
				row1.createCell(2).setCellValue((String) list.get(i).get("pubIP"));
			}
			if (list.get(i).get("pubPort") == null || "".equals(list.get(i).get("pubPort"))) {
				row1.createCell(3).setCellValue("-");
			} else {
				row1.createCell(3).setCellValue((String) list.get(i).get("pubPort"));// sdf.format()
			}
			if (list.get(i).get("objectiveIP") == null || "".equals(list.get(i).get("objectiveIP"))) {
				row1.createCell(4).setCellValue("-");
			} else {
				row1.createCell(4).setCellValue((String) list.get(i).get("objectiveIP"));
			}
			if (list.get(i).get("objectivePort") == null || "".equals(list.get(i).get("objectivePort"))) {
				row1.createCell(5).setCellValue("-");
			} else {
				row1.createCell(5).setCellValue((String) list.get(i).get("objectivePort"));
			}
			if (list.get(i).get("objectivePortURL") == null || "".equals(list.get(i).get("objectivePortURL"))) {
				row1.createCell(6).setCellValue("-");
			} else {
				row1.createCell(6).setCellValue((String) list.get(i).get("objectivePortURL"));
			}
			if (list.get(i).get("BrasIP") == null || "".equals(list.get(i).get("BrasIP"))) {
				row1.createCell(7).setCellValue("-");
			} else {
				row1.createCell(7).setCellValue((String) list.get(i).get("BrasIP"));
			}
			if (list.get(i).get("beginDate") == null || "".equals(list.get(i).get("beginDate"))) {
				row1.createCell(8).setCellValue("-");
			} else {
				try {
					row1.createCell(8).setCellValue(sdf.format(sdf.parse((String) list.get(i).get("beginDate"))));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (list.get(i).get("endDate") == null || "".equals(list.get(i).get("endDate"))) {
				row1.createCell(9).setCellValue("-");
			} else {
				try {
					row1.createCell(9).setCellValue(sdf.format(sdf.parse((String) list.get(i).get("endDate"))));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

		}
		response.reset();
		response.setHeader("Content-Disposition", "attachment;filename=createList.xls");
		// rc.getHttpServletResponse().setContentType("application/octet-stream");
		response.setContentType("application/octet-stream");
		response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
		// rc.getHttpServletResponse().setHeader("Content-disposition",
		// "attachment;filename=createList.xls");//默认Excel名称
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers",
				"Content-Type, x-requested-with, X-Custom-Header,DABIGDATA_TOKEN");
		
		response.setContentType("application/octet-stream;charset=ISO8859-1");
        response.setHeader("Content-Disposition", "attachment;filename="+ "createList.xls\"");
        response.addHeader("Pargam", "no-cache");
        response.addHeader("Cache-Control", "no-cache");

		try {
//			SimpleDateFormat newsdf = new SimpleDateFormat("yyyyMMddHHmmss");
//			String date = newsdf.format(new Date());
//			rc.getHttpServletResponse().addHeader("Content-Disposition", "attachment;filename=\""
//					+ new String(("批量日志查询" + date + ".xls").getBytes("utf-8"), "ISO8859_1") + "\"");
			OutputStream out = response.getOutputStream(); 
			wb.write(out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "导出失败!");
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "导出失败!");
			e.printStackTrace();
		}   
    }
	
	

}
