package com.zxtech.ess.app.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelUtil {
	private static Logger log = LoggerFactory.getLogger(ExcelUtil.class);
	final static int MAX_ROWCOUNT = 1000000;
	
	public static List<Map<String, String>> excelImport(InputStream in) throws Exception {
		List<Map<String, String>> resList = new ArrayList<>();

		XSSFWorkbook workbook = new XSSFWorkbook(in);
		XSSFSheet sheet = workbook.getSheetAt(0);

		XSSFRow row = sheet.getRow(0);
		short lastCellNum = row.getLastCellNum();
		String[] nameArr = new String[lastCellNum];
		for (int j = 0; j < lastCellNum; j++) {
			nameArr[j] = row.getCell(j).getStringCellValue().trim();
		}

		int lastRowIndex = sheet.getLastRowNum();
		for (int i = 1; i <= lastRowIndex; i++) {
			Map<String, String> rowMap = new HashMap<>();
			row = sheet.getRow(i);
			if (row == null) {
				break;
			}

			lastCellNum = row.getLastCellNum();
			for (int j = 0; j < lastCellNum; j++) {
				rowMap.put(nameArr[j], row.getCell(j).toString());
			}

			resList.add(rowMap);
		}

		workbook.close();

		return resList;
	}
	
	public static String excelExport(String[] colNameList, String[] colShowNameList, String fileName, String path,
			List<Map<String, Object>> resultList) throws Exception {
		return excelExport(Arrays.asList(colNameList), Arrays.asList(colShowNameList), fileName, path, resultList);
	}
	
	public static String excelExport(List<String> colNameList, List<String> colShowNameList, String fileName, String path,
			List<Map<String, Object>> resultList) throws Exception {
		String guidFile = SysTools.getGUID() + ".xlsx";
		fileName += "-" + guidFile;
		int totalRowNum = resultList.size();
		if (totalRowNum > MAX_ROWCOUNT)
			return null;
		FileOutputStream fout = new FileOutputStream(path + File.separator + guidFile);
		SXSSFWorkbook wb = new SXSSFWorkbook(100);
		SXSSFSheet sheet = wb.createSheet();
		// 制作列表头
		createTitle(colShowNameList, wb, sheet);
		// 遍历写入数据
		insertRows(resultList, colNameList, wb, sheet, 0);
		// 自适应宽度，但对于仅有数字和英文的文本宽度会加大（如果每个单元格判断中文，得不偿失）
//		sheet.trackAllColumnsForAutoSizing();
//		for (int i = 0; i < colNameList.size(); i++) {
//            sheet.autoSizeColumn(i);
//            sheet.setColumnWidth(i, sheet.getColumnWidth(i) * 17 / 10);
//        }
		// 写入文件
		wb.write(fout);
		fout.close();
		wb.close();
		
		if(totalRowNum != 0)
			return fileName;
		return null;
	}
	
	public static int excelExportBigData(String[] colNameList, String[] colShowNameList, String fileName, String path,
			Iterator<List<Map<String, Object>>> listIterator) throws Exception {
		return excelExportBigData(Arrays.asList(colNameList), Arrays.asList(colShowNameList), fileName, path, listIterator);
	}
	public static int excelExportBigData(List<String> colNameList, List<String> colShowNameList, String fileName, String path,
			Iterator<List<Map<String, Object>>> listIterator) throws Exception {
		
		long start = System.currentTimeMillis();
		log.info("====BigData Export Excel START======");
		
		int totalRowNum = 0;
		List<Map<String, Object>> resultList;

		fileName += ".xlsx";

		FileOutputStream fout = new FileOutputStream(path + File.separator + fileName);
		SXSSFWorkbook wb = new SXSSFWorkbook(100);
		SXSSFSheet sheet = wb.createSheet();

		// 制作列表头
		createTitle(colShowNameList, wb, sheet);

		int index = 0;
		while (listIterator.hasNext()) {
			resultList = listIterator.next();
			totalRowNum += resultList.size();
			if (totalRowNum > MAX_ROWCOUNT) {
				fout.close();
				wb.close();

				File file = new File(path + File.separator + fileName);
				if (file.exists()) {
					file.delete();
				}
				return 0;
			}

			// 遍历写入数据
			insertRows(resultList, colNameList, wb, sheet, index);

			index = index + resultList.size();
		}
		
		// 写入文件
		wb.write(fout);
		fout.close();
		wb.close();

		log.info("====BigData Export Excel END======" + (System.currentTimeMillis() - start));
		return totalRowNum;
	}
	
	public static void createTitle(List<String> colShowNameList, SXSSFWorkbook wb, SXSSFSheet sheet) {
		CellStyle styleTitle = wb.createCellStyle();
		styleTitle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		styleTitle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		styleTitle.setBorderBottom(BorderStyle.THIN);
		styleTitle.setBorderLeft(BorderStyle.THIN);
		styleTitle.setBorderRight(BorderStyle.THIN);
		styleTitle.setBorderTop(BorderStyle.THIN);

		Font font = wb.createFont();
		font.setBold(true);
		styleTitle.setFont(font);

		Row row = sheet.createRow(0);
		Cell cell;
		for (int i = 0; i < colShowNameList.size(); i++) {
			cell = row.createCell(i);
			cell.setCellValue(colShowNameList.get(i));
			cell.setCellStyle(styleTitle);
		}
	}

	public static void insertRows(List<Map<String, Object>> resultList, List<String> colNameList, SXSSFWorkbook wb,
			SXSSFSheet sheet, int startRow) {
		CellStyle styleRow = wb.createCellStyle();
		styleRow.setBorderBottom(BorderStyle.THIN);
		styleRow.setBorderLeft(BorderStyle.THIN);
		styleRow.setBorderRight(BorderStyle.THIN);
		styleRow.setBorderTop(BorderStyle.THIN);

		Row row;
		Cell cell;
		Object val;
		for (int k = 0; k < resultList.size(); k++) {
			row = sheet.createRow(k + 1 + startRow);
			for (int i = 0; i < colNameList.size(); i++) {
				cell = row.createCell(i);
				val = resultList.get(k).get(colNameList.get(i));
				cell.setCellValue(val == null ? "" : val.toString());
				cell.setCellStyle(styleRow);
			}
			resultList.set(k, null);
		}
	}
}
