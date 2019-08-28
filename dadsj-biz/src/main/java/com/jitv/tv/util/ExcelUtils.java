package com.jitv.tv.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.aspire.commons.rpc.RpcContext;

public class ExcelUtils {
	private String arr[] = null;

	/**
	 * @info 写出Excel标题
	 * @param fos
	 * @return
	 */
	@SuppressWarnings("resource")
	public static void writeExcelTitle(String filePath, String[] ss) throws IOException {
		OutputStream fos = new FileOutputStream(filePath);
		HSSFWorkbook xls = new HSSFWorkbook();
		HSSFSheet sheet = xls.createSheet();
		HSSFRow row = sheet.createRow(0);// 第一行
		CellStyle style = setHeadStyleColor(xls);
		for (int i = 0; i < ss.length; i++) {
			row.createCell(i).setCellValue(ss[i]);
			row.getCell(i).setCellStyle(style);
		}
		xls.write(fos);
		fos.close();
	}

	/**
	 * @info 写出Excel标题内容
	 * @param fos
	 * @return
	 */
	@SuppressWarnings("resource")
	public static byte[] writeExcel(String[] titles, List<Map<Integer, String>> lists) throws IOException {
		HSSFWorkbook xls = new HSSFWorkbook();
		HSSFSheet sheet = xls.createSheet();
		HSSFRow row = sheet.createRow(0);// 第一行
		CellStyle style = setHeadStyleColor(xls);
		for (int i = 0; i < titles.length; i++) {
			row.createCell(i).setCellValue(titles[i]);
			row.getCell(i).setCellStyle(style);
		}
		// 内容
		int rowNum = 1;
		for (Map<Integer, String> map : lists) {
			HSSFRow rowTmp = sheet.createRow(rowNum);
			int cols = map.size();
			for (int i = 0; i < cols; i++) {
				rowTmp.createCell(i).setCellValue(map.get(i));
			}
			rowNum++;
		}
		ByteArrayOutputStream fos = new ByteArrayOutputStream();
		xls.write(fos);
		byte[] buf = fos.toByteArray();// 获取内存缓冲区中的数据
		fos.close();
		return buf;
	}

	@SuppressWarnings("resource")
	public static List<Map<String, String>> readXlsx(InputStream is) throws IOException {
		List<Map<String, String>> contents = new LinkedList<Map<String, String>>();
		XSSFWorkbook wb = new XSSFWorkbook(is);
		XSSFSheet sheet = wb.getSheetAt(0);
		// 得到总行数
		int rowNum = sheet.getLastRowNum();
		XSSFRow row = sheet.getRow(0);// 第一行
		// 总列数
		int colNum = row.getPhysicalNumberOfCells();
		// 正文内容应该从第二行开始,第一行为表头的标题
		// 标题总列数
		String[] keys = new String[colNum];
		for (int i = 0; i < colNum; i++) {
			keys[i] = getCellFormatValue(row.getCell(i));
		}
		for (int i = 1; i <= rowNum; i++) {
			row = sheet.getRow(i);
			int j = 0;
			Map<String, String> content = new HashMap<String, String>();
			while (j < colNum) {
				String cellValue = getCellFormatValue(row.getCell(j)).trim();

				content.put(keys[j], cellValue);
				j++;
			}
			contents.add(content);
		}
		is.close();
		return contents;
	}

	/**
	 * @info 写出Excel标题内容
	 * @param fos
	 * @return
	 */
	@SuppressWarnings("resource")
	public static void writeExcel(String filePath, String[] titles, List<Map<Integer, String>> lists)
			throws IOException {
		OutputStream fos = new FileOutputStream(filePath);
		HSSFWorkbook xls = new HSSFWorkbook();
		HSSFSheet sheet = xls.createSheet();
		HSSFRow row = sheet.createRow(0);// 第一行
		CellStyle style = setHeadStyleColor(xls);
		for (int i = 0; i < titles.length; i++) {
			row.createCell(i).setCellValue(titles[i]);
			row.getCell(i).setCellStyle(style);
		}
		// 内容
		int rowNum = 1;
		for (Map<Integer, String> map : lists) {
			HSSFRow rowTmp = sheet.createRow(rowNum);
			int cols = map.size();
			for (int i = 0; i < cols; i++) {
				rowTmp.createCell(i).setCellValue(map.get(i));
			}
			rowNum++;
		}
		xls.write(fos);
		fos.close();
	}

	/**
	 * 设置表头背景颜色
	 * 
	 * @param arr
	 */
	@SuppressWarnings("deprecation")
	public static CellStyle setHeadStyleColor(HSSFWorkbook xls) {
		// 创建表头style
		HSSFCellStyle cellStyleTitle = xls.createCellStyle();
		cellStyleTitle.setBorderBottom(CellStyle.BORDER_THIN); // 下边框
		cellStyleTitle.setBorderLeft(CellStyle.BORDER_THIN);// 左边框
		cellStyleTitle.setBorderTop(CellStyle.BORDER_THIN);// 上边框
		cellStyleTitle.setBorderRight(CellStyle.BORDER_THIN);// 右边框
		cellStyleTitle.setVerticalAlignment(CellStyle.ALIGN_LEFT);
		cellStyleTitle.setWrapText(true);
		// 颜色
		CellStyle style = xls.createCellStyle();
		style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);// 填充单元格
		cellStyleTitle.setFillBackgroundColor(HSSFColor.LIGHT_YELLOW.index);
		cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// //居中显示
		return style;
	}

	/**
	 * @info 读取Excel内容，List行，MAP行数据
	 * @param filePath
	 * @return
	 */
	@SuppressWarnings("resource")
	public static List<Map<String, String>> readExcelKeyMap(String filePath) throws IOException {
		List<Map<String, String>> contents = new LinkedList<Map<String, String>>();
		InputStream is = new FileInputStream(filePath);
		POIFSFileSystem fs = new POIFSFileSystem(is);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheetAt(0);
		// 得到总行数
		int rowNum = sheet.getLastRowNum();
		HSSFRow row = sheet.getRow(0);// 第一行
		// 总列数
		int colNum = row.getPhysicalNumberOfCells();
		// 正文内容应该从第二行开始,第一行为表头的标题
		String[] keys = readExcelTitle(filePath);
		for (int i = 1; i <= rowNum; i++) {
			row = sheet.getRow(i);
			int j = 0;
			Map<String, String> content = new HashMap<String, String>();
			while (j < colNum) {
				String cellValue = getCellFormatValue(row.getCell(j)).trim();

				content.put(keys[j], cellValue);
				j++;
			}
			contents.add(content);
		}
		is.close();
		return contents;
	}

	@SuppressWarnings("resource")
	public static List<Map<String, String>> readExcelKeyMap(InputStream is) throws IOException {
		List<Map<String, String>> contents = new LinkedList<Map<String, String>>();
		POIFSFileSystem fs = new POIFSFileSystem(is);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheetAt(0);
		// 得到总行数
		int rowNum = sheet.getLastRowNum();
		HSSFRow row = sheet.getRow(0);// 第一行
		// 总列数
		int colNum = row.getPhysicalNumberOfCells();
		// 正文内容应该从第二行开始,第一行为表头的标题
		// 标题总列数
		String[] keys = new String[colNum];
		for (int i = 0; i < colNum; i++) {
			keys[i] = getCellFormatValue(row.getCell(i));
		}
		for (int i = 1; i <= rowNum; i++) {
			row = sheet.getRow(i);
			int j = 0;
			Map<String, String> content = new HashMap<String, String>();
			while (j < colNum) {
				String cellValue = getCellFormatValue(row.getCell(j)).trim();

				content.put(keys[j], cellValue);
				j++;
			}
			contents.add(content);
		}
		is.close();
		return contents;
	}

	/**
	 * @info 读取Excel标题
	 * @param is
	 * @return
	 */
	@SuppressWarnings("resource")
	public static String[] readExcelTitle(String filePath) throws IOException {
		InputStream is = new FileInputStream(filePath);
		POIFSFileSystem fs = new POIFSFileSystem(is);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow row = sheet.getRow(0);// 第一行
		// 标题总列数
		int colNum = row.getPhysicalNumberOfCells();
		String[] title = new String[colNum];
		for (int i = 0; i < colNum; i++) {
			title[i] = getCellFormatValue(row.getCell(i));
		}
		is.close();
		return title;
	}

	/**
	 * @info 读取Excel内容，List行，MAP行数据
	 * @param filePath
	 * @return
	 */
	@SuppressWarnings("resource")
	public static List<Map<Integer, String>> readExcelContent(String filePath) throws IOException {
		List<Map<Integer, String>> contents = new LinkedList<Map<Integer, String>>();
		InputStream is = new FileInputStream(filePath);
		POIFSFileSystem fs = new POIFSFileSystem(is);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheetAt(0);
		// 得到总行数
		int rowNum = sheet.getLastRowNum();
		HSSFRow row = sheet.getRow(0);// 第一行
		// 总列数
		int colNum = row.getPhysicalNumberOfCells();
		// 正文内容应该从第二行开始,第一行为表头的标题
		for (int i = 1; i <= rowNum; i++) {
			row = sheet.getRow(i);
			int j = 0;
			Map<Integer, String> content = new HashMap<Integer, String>();
			while (j < colNum) {
				String cellValue = getCellFormatValue(row.getCell(j)).trim();
				content.put(j, cellValue);
				j++;
			}
			contents.add(content);
		}
		is.close();
		return contents;
	}

	/**
	 * @info 读取Excel值
	 * @param cell
	 * @return
	 */
	@SuppressWarnings("deprecation")
	static String getCellFormatValue(HSSFCell cell) {
		String cellvalue = "";
		if (cell != null) {
			switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_NUMERIC: {
				BigDecimal b = new BigDecimal(cell.getNumericCellValue());
				cellvalue = b.toPlainString();
				break;
			}
			case HSSFCell.CELL_TYPE_FORMULA: {
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cellvalue = cell.getStringCellValue();
				// System.out.println(cellvalue);
				break;
			}
			case HSSFCell.CELL_TYPE_STRING:
				cellvalue = cell.getRichStringCellValue().getString();
				// System.out.println(cellvalue);
				break;
			default:
				cellvalue = "";
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;
	}

	@SuppressWarnings("deprecation")
	static String getCellFormatValue(XSSFCell xssfCell) {
		String cellvalue = "";
		if (xssfCell != null) {
			switch (xssfCell.getCellType()) {
			case HSSFCell.CELL_TYPE_NUMERIC: {
				BigDecimal b = new BigDecimal(xssfCell.getNumericCellValue());
				if (HSSFDateUtil.isCellDateFormatted(xssfCell)) {
					Date d = xssfCell.getDateCellValue();
					DateFormat formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					cellvalue = formater.format(d);
				} else {
					cellvalue = b.toPlainString();
				}
				break;
			}
			case HSSFCell.CELL_TYPE_FORMULA: {
				xssfCell.setCellType(Cell.CELL_TYPE_STRING);
				cellvalue = xssfCell.getStringCellValue();
				// System.out.println(cellvalue);
				break;
			}
			case HSSFCell.CELL_TYPE_STRING:
				cellvalue = xssfCell.getRichStringCellValue().getString();
				// System.out.println(cellvalue);
				break;
			default:
				cellvalue = "";
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;
	}

	/**
	 * @info 读取Excel值
	 * @param cell
	 * @return
	 */
	@SuppressWarnings("deprecation")
	static String getStringCellValue(HSSFCell cell) {
		String strCell = "";
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
			strCell = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			strCell = String.valueOf(cell.getNumericCellValue());
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			strCell = String.valueOf(cell.getBooleanCellValue());
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			strCell = "";
			break;
		default:
			strCell = "";
			break;
		}
		if (strCell.equals("") || strCell == null) {
			return "";
		}
		return strCell;
	}

	// host流量导出
	public static void HostGetFlowExcel(List<Map<String, Object>> list, RpcContext rc) {
		// *********************返回excle******************************************//
		HSSFWorkbook wb = new HSSFWorkbook();// 声明工
		Sheet sheet = wb.createSheet("host流量");// 新建表
		sheet.setDefaultColumnWidth(100);// 设置表宽
		HSSFCellStyle style = wb.createCellStyle();
		org.apache.poi.hssf.usermodel.HSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 12);
		HSSFCellStyle headerStyle = wb.createCellStyle();
		org.apache.poi.hssf.usermodel.HSSFFont headerFont = wb.createFont();
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle.setFont(headerFont);
		CellRangeAddress cra0 = new CellRangeAddress(0, 1, 0, 5);
		sheet.addMergedRegion(cra0);
		sheet.setDefaultColumnWidth((short) 15);
		Row row = sheet.createRow(0);
		Cell cell1 = row.createCell(0);
		cell1.setCellValue("host流量");
		cell1.setCellStyle(headerStyle);
		// 设置字体样式
		org.apache.poi.hssf.usermodel.HSSFFont titleFont = wb.createFont();
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(titleFont);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		Row row1 = sheet.createRow(2);
		Cell cell = row1.createCell(0);
		cell.setCellValue("HOST");
		cell.setCellStyle(style);
		cell = row1.createCell(1);
		cell.setCellValue("网站ID");
		cell.setCellStyle(style);
		cell = row1.createCell(2);
		cell.setCellValue("上行字节");
		cell.setCellStyle(style);
		cell = row1.createCell(3);
		cell.setCellValue("下行字节");
		cell.setCellStyle(style);
		cell = row1.createCell(4);
		cell.setCellValue("访问量");
		cell.setCellStyle(style);
		cell = row1.createCell(5);
		cell.setCellValue("结束时间");
		cell.setCellStyle(style);
		cell = row1.createCell(6);
		// 时间转字符串的格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0, imax = list.size(); i < imax; i++) {
			row1 = sheet.createRow(i + 3);
			if (list.get(i).get("host") == null || "".equals(list.get(i).get("host"))) {
				row1.createCell(0).setCellValue("-");
			} else {
				row1.createCell(0).setCellValue((String) list.get(i).get("host"));
			}
			if (list.get(i).get("siteid") == null || "".equals(list.get(i).get("siteid"))) {
				row1.createCell(1).setCellValue("-");
			} else {
				row1.createCell(1).setCellValue((Integer) list.get(i).get("siteid"));
			}
			if (list.get(i).get("upbyte") == null || "".equals(list.get(i).get("upbyte"))) {
				row1.createCell(2).setCellValue("-");
			} else {
				row1.createCell(2).setCellValue((Long) list.get(i).get("upbyte"));
			}
			if (list.get(i).get("dnbyte") == null || "".equals(list.get(i).get("dnbyte"))) {
				row1.createCell(3).setCellValue("-");
			} else {
				row1.createCell(3).setCellValue((Long) list.get(i).get("dnbyte"));
			}
			if (list.get(i).get("count") == null || "".equals(list.get(i).get("count"))) {
				row1.createCell(4).setCellValue("-");
			} else {
				row1.createCell(4).setCellValue((Long) list.get(i).get("count"));
			}
			if (list.get(i).get("time") == null || "".equals(list.get(i).get("time"))) {
				row1.createCell(5).setCellValue("-");
			} else {
				row1.createCell(5).setCellValue(sdf.format(list.get(i).get("time")));
			}
		}
		rc.getHttpServletResponse().reset();
		rc.getHttpServletResponse().setHeader("Content-Disposition", "attachment;filename=createList.xls");
		rc.getHttpServletResponse().setContentType("application/octet-stream");
		rc.getHttpServletResponse().addHeader("Access-Control-Expose-Headers", "Content-Disposition");
		rc.getHttpServletResponse().setHeader("Access-Control-Allow-Origin", "*");
		rc.getHttpServletResponse().setHeader("Access-Control-Allow-Origin", "*");
		rc.getHttpServletResponse().setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		rc.getHttpServletResponse().setHeader("Access-Control-Max-Age", "3600");
		rc.getHttpServletResponse().setHeader("Access-Control-Allow-Headers",
				"Content-Type, x-requested-with, X-Custom-Header,DABIGDATA_TOKEN");
		rc.getHttpServletResponse().setContentType("application/octet-stream;charset=ISO8859-1");
		rc.getHttpServletResponse().setHeader("Content-Disposition", "attachment;filename=" + "createList.xls\"");
		rc.getHttpServletResponse().addHeader("Pargam", "no-cache");
		rc.getHttpServletResponse().addHeader("Cache-Control", "no-cache");
		try {
			OutputStream out = rc.getHttpServletResponse().getOutputStream();
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

	// Bras 数量统计excel 导出
	public static void BrasDownloadExcel(List<Map<String, Object>> list, RpcContext rc) {
		// *********************返回excle******************************************//
		HSSFWorkbook wb = new HSSFWorkbook();// 声明工
		Sheet sheet = wb.createSheet("Bras数量统计");// 新建表
		sheet.setDefaultColumnWidth(100);// 设置表宽
		HSSFCellStyle style = wb.createCellStyle();
		org.apache.poi.hssf.usermodel.HSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 12);
		HSSFCellStyle headerStyle = wb.createCellStyle();
		org.apache.poi.hssf.usermodel.HSSFFont headerFont = wb.createFont();
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle.setFont(headerFont);
		CellRangeAddress cra0 = new CellRangeAddress(0, 1, 0, 3);
		sheet.addMergedRegion(cra0);
		sheet.setDefaultColumnWidth((short) 15);
		Row row = sheet.createRow(0);
		Cell cell1 = row.createCell(0);
		cell1.setCellValue("Bras数量统计");
		cell1.setCellStyle(headerStyle);
		// 设置字体样式
		org.apache.poi.hssf.usermodel.HSSFFont titleFont = wb.createFont();
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(titleFont);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		Row row1 = sheet.createRow(2);
		Cell cell = row1.createCell(0);
		cell.setCellValue("BrasIP");
		cell.setCellStyle(style);
		cell = row1.createCell(1);
		cell.setCellValue("Bras名称");
		cell.setCellStyle(style);
		cell = row1.createCell(2);
		cell.setCellValue("用户数量");
		cell.setCellStyle(style);
		cell = row1.createCell(3);
		cell.setCellValue("时间");
		cell.setCellStyle(style);
		cell = row1.createCell(4);
		// 时间转字符串的格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0, imax = list.size(); i < imax; i++) {
			row1 = sheet.createRow(i + 3);
			if (list.get(i).get("ip") == null || "".equals(list.get(i).get("ip"))) {
				row1.createCell(0).setCellValue("-");
			} else {
				row1.createCell(0).setCellValue((String) list.get(i).get("ip"));
			}
			if (list.get(i).get("name") == null || "".equals(list.get(i).get("name"))) {
				row1.createCell(1).setCellValue("-");
			} else {
				row1.createCell(1).setCellValue((String) list.get(i).get("name"));
			}
			if (list.get(i).get("count") == null || "".equals(list.get(i).get("count"))) {
				row1.createCell(2).setCellValue("-");
			} else {
				row1.createCell(2).setCellValue((Long) list.get(i).get("count"));
			}
			if (list.get(i).get("time") == null || "".equals(list.get(i).get("time"))) {
				row1.createCell(3).setCellValue("-");
			} else {
				try {
					row1.createCell(3).setCellValue(
							DateUtil.parseString((Date) list.get(i).get("time"), "\"yyyy-MM-dd HH:mm:ss\""));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
//			if (list.get(i).get("endTime") == null || "".equals(list.get(i).get("endTime"))) {
//				row1.createCell(4).setCellValue("-");
//			} else {
//				try {
//					row1.createCell(4).setCellValue(sdf.format(sdf.parse((String) list.get(i).get("endTime"))));
//				} catch (ParseException e) {
//					e.printStackTrace();
//				}
//			}
		}
		rc.getHttpServletResponse().reset();
		rc.getHttpServletResponse().setHeader("Content-Disposition", "attachment;filename=createList.xls");
		rc.getHttpServletResponse().setContentType("application/octet-stream");
		rc.getHttpServletResponse().addHeader("Access-Control-Expose-Headers", "Content-Disposition");
		rc.getHttpServletResponse().setHeader("Access-Control-Allow-Origin", "*");
		rc.getHttpServletResponse().setHeader("Access-Control-Allow-Origin", "*");
		rc.getHttpServletResponse().setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		rc.getHttpServletResponse().setHeader("Access-Control-Max-Age", "3600");
		rc.getHttpServletResponse().setHeader("Access-Control-Allow-Headers",
				"Content-Type, x-requested-with, X-Custom-Header,DABIGDATA_TOKEN");
		rc.getHttpServletResponse().setContentType("application/octet-stream;charset=ISO8859-1");
		rc.getHttpServletResponse().setHeader("Content-Disposition", "attachment;filename=" + "createList.xls\"");
		rc.getHttpServletResponse().addHeader("Pargam", "no-cache");
		rc.getHttpServletResponse().addHeader("Cache-Control", "no-cache");
		try {
			OutputStream out = rc.getHttpServletResponse().getOutputStream();
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

	// 网站流量导出
	public static void downloadExcelWebsitePercentage(List<Map<String, Object>> list, RpcContext rc) {
		// *********************返回excle******************************************//
		HSSFWorkbook wb = new HSSFWorkbook();// 声明工
		Sheet sheet = wb.createSheet("网站流量");// 新建表
		sheet.setDefaultColumnWidth(100);// 设置表宽
		HSSFCellStyle style = wb.createCellStyle();
		org.apache.poi.hssf.usermodel.HSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 12);
		HSSFCellStyle headerStyle = wb.createCellStyle();
		org.apache.poi.hssf.usermodel.HSSFFont headerFont = wb.createFont();
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle.setFont(headerFont);
		CellRangeAddress cra0 = new CellRangeAddress(0, 1, 0, 5);
		sheet.addMergedRegion(cra0);
		sheet.setDefaultColumnWidth((short) 15);
		Row row = sheet.createRow(0);
		Cell cell1 = row.createCell(0);
		cell1.setCellValue("网站流量");
		cell1.setCellStyle(headerStyle);
		// 设置字体样式
		org.apache.poi.hssf.usermodel.HSSFFont titleFont = wb.createFont();
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(titleFont);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		Row row1 = sheet.createRow(2);
		Cell cell = row1.createCell(0);
		cell.setCellValue("网站");
		cell.setCellStyle(style);
		cell = row1.createCell(1);
		cell.setCellValue("上行字节");
		cell.setCellStyle(style);
		cell = row1.createCell(2);
		cell.setCellValue("下行字节");
		cell.setCellStyle(style);
		cell = row1.createCell(3);
		cell.setCellValue("访问量");
		cell.setCellStyle(style);
		cell = row1.createCell(4);
		cell.setCellValue("结束时间");
		cell.setCellStyle(style);
		cell = row1.createCell(5);
		// 时间转字符串的格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0, imax = list.size(); i < imax; i++) {
			row1 = sheet.createRow(i + 3);
			if (list.get(i).get("siteid") == null || "".equals(list.get(i).get("siteid"))) {
				row1.createCell(0).setCellValue("-");
			} else {
				row1.createCell(0).setCellValue((String) list.get(i).get("siteid"));
			}
			if (list.get(i).get("upbyte") == null || "".equals(list.get(i).get("upbyte"))) {
				row1.createCell(1).setCellValue("-");
			} else {
				row1.createCell(1).setCellValue((Long) list.get(i).get("upbyte"));
			}
			if (list.get(i).get("dnbyte") == null || "".equals(list.get(i).get("dnbyte"))) {
				row1.createCell(2).setCellValue("-");
			} else {
				row1.createCell(2).setCellValue((Long) list.get(i).get("dnbyte"));
			}
			if (list.get(i).get("count") == null || "".equals(list.get(i).get("count"))) {
				row1.createCell(3).setCellValue("-");
			} else {
				row1.createCell(3).setCellValue((Long) list.get(i).get("count"));
			}
			if (list.get(i).get("time") == null || "".equals(list.get(i).get("time"))) {
				row1.createCell(4).setCellValue("-");
			} else {
				row1.createCell(4).setCellValue(sdf.format(list.get(i).get("time")));
			}
		}
		rc.getHttpServletResponse().reset();
		rc.getHttpServletResponse().setHeader("Content-Disposition", "attachment;filename=createList.xls");
		rc.getHttpServletResponse().setContentType("application/octet-stream");
		rc.getHttpServletResponse().addHeader("Access-Control-Expose-Headers", "Content-Disposition");
		rc.getHttpServletResponse().setHeader("Access-Control-Allow-Origin", "*");
		rc.getHttpServletResponse().setHeader("Access-Control-Allow-Origin", "*");
		rc.getHttpServletResponse().setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		rc.getHttpServletResponse().setHeader("Access-Control-Max-Age", "3600");
		rc.getHttpServletResponse().setHeader("Access-Control-Allow-Headers",
				"Content-Type, x-requested-with, X-Custom-Header,DABIGDATA_TOKEN");
		rc.getHttpServletResponse().setContentType("application/octet-stream;charset=ISO8859-1");
		rc.getHttpServletResponse().setHeader("Content-Disposition", "attachment;filename=" + "createList.xls\"");
		rc.getHttpServletResponse().addHeader("Pargam", "no-cache");
		rc.getHttpServletResponse().addHeader("Cache-Control", "no-cache");
		try {
			OutputStream out = rc.getHttpServletResponse().getOutputStream();
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

	// 用户群流量导出
	public static void downloadExcelUserGroup(List<Map<String, Object>> list, RpcContext rc) {
		// *********************返回excle******************************************//
		HSSFWorkbook wb = new HSSFWorkbook();// 声明工
		Sheet sheet = wb.createSheet("用户群流量");// 新建表
		sheet.setDefaultColumnWidth(100);// 设置表宽
		HSSFCellStyle style = wb.createCellStyle();
		org.apache.poi.hssf.usermodel.HSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 12);
		HSSFCellStyle headerStyle = wb.createCellStyle();
		org.apache.poi.hssf.usermodel.HSSFFont headerFont = wb.createFont();
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle.setFont(headerFont);
		CellRangeAddress cra0 = new CellRangeAddress(0, 1, 0, 11);
		sheet.addMergedRegion(cra0);
		sheet.setDefaultColumnWidth((short) 15);
		Row row = sheet.createRow(0);
		Cell cell1 = row.createCell(0);
		cell1.setCellValue("用户群流量");
		cell1.setCellStyle(headerStyle);
		// 设置字体样式
		org.apache.poi.hssf.usermodel.HSSFFont titleFont = wb.createFont();
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(titleFont);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		Row row1 = sheet.createRow(2);
		Cell cell = row1.createCell(0);
		cell.setCellValue("运营商");
		cell.setCellStyle(style);
		cell = row1.createCell(1);
		cell.setCellValue("用户");
		cell.setCellStyle(style);
		cell = row1.createCell(2);
		cell.setCellValue("IP类型");
		cell.setCellStyle(style);
		cell = row1.createCell(3);
		cell.setCellValue("上行实际速率");
		cell.setCellStyle(style);
		cell = row1.createCell(4);
		cell.setCellValue("下行实际速率");
		cell.setCellStyle(style);
		cell = row1.createCell(5);
		cell.setCellValue("上行丢弃速率");
		cell.setCellStyle(style);
		cell = row1.createCell(6);
		cell.setCellValue("下行丢弃速率");
		cell.setCellStyle(style);
		cell = row1.createCell(7);
		cell.setCellValue("上行峰值速率");
		cell.setCellStyle(style);
		cell = row1.createCell(8);
		cell.setCellValue("下行峰值速率");
		cell.setCellStyle(style);
		cell = row1.createCell(9);
		cell.setCellValue("上行最小速率");
		cell.setCellStyle(style);
		cell = row1.createCell(10);
		cell.setCellValue("下行最小速率");
		cell.setCellStyle(style);
		cell = row1.createCell(11);
		cell.setCellValue("保存时间");
		cell.setCellStyle(style);
		cell = row1.createCell(12);
		// 时间转字符串的格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0, imax = list.size(); i < imax; i++) {
			row1 = sheet.createRow(i + 3);
			if (list.get(i).get("ispid") == null || "".equals(list.get(i).get("ispid"))) {
				row1.createCell(0).setCellValue("-");
			} else {
				row1.createCell(0).setCellValue((String) list.get(i).get("ispid"));
			}
			if (list.get(i).get("usergrpid") == null || "".equals(list.get(i).get("usergrpid"))) {
				row1.createCell(1).setCellValue("-");
			} else {
				row1.createCell(1).setCellValue((String) list.get(i).get("usergrpid"));
			}
			if (list.get(i).get("ipversion") == null || "".equals(list.get(i).get("ipversion"))) {
				row1.createCell(2).setCellValue("-");
			} else {
				row1.createCell(2).setCellValue((String) list.get(i).get("ipversion"));
			}
			if (list.get(i).get("upbps") == null || "".equals(list.get(i).get("upbps"))) {
				row1.createCell(3).setCellValue("-");
			} else {
				row1.createCell(3).setCellValue((Long) list.get(i).get("upbps"));
			}
			if (list.get(i).get("dnbps") == null || "".equals(list.get(i).get("dnbps"))) {
				row1.createCell(4).setCellValue("-");
			} else {
				row1.createCell(4).setCellValue((Long) list.get(i).get("dnbps"));
			}
			if (list.get(i).get("updisbps") == null || "".equals(list.get(i).get("updisbps"))) {
				row1.createCell(5).setCellValue("-");
			} else {
				row1.createCell(5).setCellValue((Long) list.get(i).get("updisbps"));
			}
			if (list.get(i).get("dndisbps") == null || "".equals(list.get(i).get("dndisbps"))) {
				row1.createCell(6).setCellValue("-");
			} else {
				row1.createCell(6).setCellValue((Long) list.get(i).get("dndisbps"));
			}
			if (list.get(i).get("upmaxbps") == null || "".equals(list.get(i).get("upmaxbps"))) {
				row1.createCell(7).setCellValue("-");
			} else {
				row1.createCell(7).setCellValue((Long) list.get(i).get("upmaxbps"));
			}
			if (list.get(i).get("dnmaxbps") == null || "".equals(list.get(i).get("dnmaxbps"))) {
				row1.createCell(8).setCellValue("-");
			} else {
				row1.createCell(8).setCellValue((Long) list.get(i).get("dnmaxbps"));
			}
			if (list.get(i).get("upminbps") == null || "".equals(list.get(i).get("upminbps"))) {
				row1.createCell(9).setCellValue("-");
			} else {
				row1.createCell(9).setCellValue((Long) list.get(i).get("upminbps"));
			}
			if (list.get(i).get("dnminbps") == null || "".equals(list.get(i).get("dnminbps"))) {
				row1.createCell(10).setCellValue("-");
			} else {
				row1.createCell(10).setCellValue((Long) list.get(i).get("dnminbps"));
			}
			if (list.get(i).get("time") == null || "".equals(list.get(i).get("time"))) {
				row1.createCell(11).setCellValue("-");
			} else {
				row1.createCell(11).setCellValue(sdf.format(list.get(i).get("time")));
			}
		}
		rc.getHttpServletResponse().reset();
		rc.getHttpServletResponse().setHeader("Content-Disposition", "attachment;filename=createList.xls");
		rc.getHttpServletResponse().setContentType("application/octet-stream");
		rc.getHttpServletResponse().addHeader("Access-Control-Expose-Headers", "Content-Disposition");
		rc.getHttpServletResponse().setHeader("Access-Control-Allow-Origin", "*");
		rc.getHttpServletResponse().setHeader("Access-Control-Allow-Origin", "*");
		rc.getHttpServletResponse().setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		rc.getHttpServletResponse().setHeader("Access-Control-Max-Age", "3600");
		rc.getHttpServletResponse().setHeader("Access-Control-Allow-Headers",
				"Content-Type, x-requested-with, X-Custom-Header,DABIGDATA_TOKEN");
		rc.getHttpServletResponse().setContentType("application/octet-stream;charset=ISO8859-1");
		rc.getHttpServletResponse().setHeader("Content-Disposition", "attachment;filename=" + "createList.xls\"");
		rc.getHttpServletResponse().addHeader("Pargam", "no-cache");
		rc.getHttpServletResponse().addHeader("Cache-Control", "no-cache");
		try {
			OutputStream out = rc.getHttpServletResponse().getOutputStream();
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

	// 导出excel
	/**
	 * 
	 * @param list 数据
	 * @param rc   http 对象
	 */
	public static void downloadExcelLog(List<Map<String, Object>> list, RpcContext rc) {
		// *********************返回excle******************************************//
		HSSFWorkbook wb = new HSSFWorkbook();// 声明工
		Sheet sheet = wb.createSheet("批量日志查询");// 新建表
		sheet.setDefaultColumnWidth(25);// 设置表宽
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
		cell1.setCellValue("批量日志查询");
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
			row1 = sheet.createRow(i + 3);
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
		rc.getHttpServletResponse().reset();

		rc.getHttpServletResponse().setHeader("Content-Disposition", "attachment;filename=createList.xls");
		// rc.getHttpServletResponse().setContentType("application/octet-stream");
		rc.getHttpServletResponse().setContentType("application/octet-stream");
		rc.getHttpServletResponse().addHeader("Access-Control-Expose-Headers", "Content-Disposition");
		// rc.getHttpServletResponse().setHeader("Content-disposition",
		// "attachment;filename=createList.xls");//默认Excel名称
		rc.getHttpServletResponse().setHeader("Access-Control-Allow-Origin", "*");
		rc.getHttpServletResponse().setHeader("Access-Control-Allow-Origin", "*");
		rc.getHttpServletResponse().setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		rc.getHttpServletResponse().setHeader("Access-Control-Max-Age", "3600");
		rc.getHttpServletResponse().setHeader("Access-Control-Allow-Headers",
				"Content-Type, x-requested-with, X-Custom-Header,DABIGDATA_TOKEN");

		rc.getHttpServletResponse().setContentType("application/octet-stream;charset=ISO8859-1");
		rc.getHttpServletResponse().setHeader("Content-Disposition", "attachment;filename=" + "createList.xls\"");
		rc.getHttpServletResponse().addHeader("Pargam", "no-cache");
		rc.getHttpServletResponse().addHeader("Cache-Control", "no-cache");

		try {
//			SimpleDateFormat newsdf = new SimpleDateFormat("yyyyMMddHHmmss");
//			String date = newsdf.format(new Date());
//			rc.getHttpServletResponse().addHeader("Content-Disposition", "attachment;filename=\""
//					+ new String(("批量日志查询" + date + ".xls").getBytes("utf-8"), "ISO8859_1") + "\"");
			OutputStream out = rc.getHttpServletResponse().getOutputStream();
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

	// 家宽日志批量导出excel
	// 用户群流量导出
	public static void downloadExcelHomeLog(List<Map<String, Object>> list, RpcContext rc) {
		// *********************返回excle******************************************//
		HSSFWorkbook wb = new HSSFWorkbook();// 声明工
		Sheet sheet = wb.createSheet("家宽日志");// 新建表
		sheet.setDefaultColumnWidth(100);// 设置表宽
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

		cell1.setCellValue("家宽日志");
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
		cell.setCellStyle(style);
		cell = row1.createCell(9);
		cell.setCellValue("结束时间");
		cell.setCellStyle(style);
		cell = row1.createCell(10);
		// 时间转字符串的格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0, imax = list.size(); i < imax; i++) {
			row1 = sheet.createRow(i + 3);
			if (list.get(i).get("dls") == null || "".equals(list.get(i).get("dls"))) {
				row1.createCell(0).setCellValue("-");
			} else {
				row1.createCell(0).setCellValue((String) list.get(i).get("dls"));
			}
			if (list.get(i).get("Bras") == null || "".equals(list.get(i).get("Bras"))) {
				row1.createCell(1).setCellValue("-");
			} else {
				row1.createCell(1).setCellValue((String) list.get(i).get("Bras"));
			}
			if (list.get(i).get("pubIP") == null || "".equals(list.get(i).get("pubIP"))) {
				row1.createCell(2).setCellValue("-");
			} else {
				row1.createCell(2).setCellValue((String) list.get(i).get("pubIP"));
			}
			if (list.get(i).get("pubPort") == null || "".equals(list.get(i).get("pubPort"))) {
				row1.createCell(3).setCellValue("-");
			} else {
				row1.createCell(3).setCellValue((String) list.get(i).get("pubPort"));
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
			if (list.get(i).get("objectiveUrl") == null || "".equals(list.get(i).get("objectiveUrl"))) {
				row1.createCell(6).setCellValue("-");
			} else {
				row1.createCell(6).setCellValue((String) list.get(i).get("objectiveUrl"));
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
		rc.getHttpServletResponse().reset();
		rc.getHttpServletResponse().setHeader("Content-Disposition", "attachment;filename=homeLog.xls");
		rc.getHttpServletResponse().setContentType("application/octet-stream");
		rc.getHttpServletResponse().addHeader("Access-Control-Expose-Headers", "Content-Disposition");
		rc.getHttpServletResponse().setHeader("Access-Control-Allow-Origin", "*");
		rc.getHttpServletResponse().setHeader("Access-Control-Allow-Origin", "*");
		rc.getHttpServletResponse().setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		rc.getHttpServletResponse().setHeader("Access-Control-Max-Age", "3600");
		rc.getHttpServletResponse().setHeader("Access-Control-Allow-Headers",
				"Content-Type, x-requested-with, X-Custom-Header,DABIGDATA_TOKEN");
		rc.getHttpServletResponse().setContentType("application/octet-stream;charset=ISO8859-1");
		rc.getHttpServletResponse().setHeader("Content-Disposition", "attachment;filename=" + "createList.xls\"");
		rc.getHttpServletResponse().addHeader("Pargam", "no-cache");
		rc.getHttpServletResponse().addHeader("Cache-Control", "no-cache");

		try {
			OutputStream out = rc.getHttpServletResponse().getOutputStream();
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
