package com.zxtech.platform.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXmlExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import net.sf.jasperreports.export.SimpleXmlExporterOutput;

/**
 * JasperReport 工具类
 * @author lanb
 */
public class JasperReportUtil {
	/**
	 * pdf	默认文件名
	 * @param response
	 * @param jasperPath
	 * @param params
	 * @param jRDataSource
	 * @throws JRException
	 * @throws IOException
	 */
	public static void reportPdf(HttpServletResponse response, String jasperPath, Map<String,Object> params, JRDataSource jRDataSource) throws JRException, IOException {
		reportPdf(response, jasperPath, null, params, jRDataSource);
	}
	
	/**
	 * pdf	指定文件名
	 * @param response
	 * @param jasperPath
	 * @param fileName
	 * @param params
	 * @param jRDataSource
	 * @throws JRException
	 * @throws IOException
	 */
	public static void reportPdf(HttpServletResponse response, String jasperPath, String fileName, Map<String,Object> params, JRDataSource jRDataSource) throws JRException, IOException {
		JasperPrint jasperPrint = getJasperPrint(jasperPath, params, jRDataSource);

		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
		response.setHeader("Content-Disposition", "attachment;filename" + getFileName(fileName) +".pdf"); 
		response.setContentType("application/pdf"); 
		response.setCharacterEncoding("UTF-8");   
		exporter.exportReport();
	}
	
	/**
	 * xml	默认文件名
	 * @param response
	 * @param jasperPath
	 * @param params
	 * @param jRDataSource
	 * @throws JRException
	 * @throws IOException
	 */
	public static void reportXml(HttpServletResponse response, String jasperPath, Map<String,Object> params, JRDataSource jRDataSource) throws JRException, IOException {
		reportXml(response, jasperPath, null, params, jRDataSource);
	}
	
	/**
	 * xml	指定文件名
	 * @param response
	 * @param jasperPath
	 * @param fileName
	 * @param params
	 * @param jRDataSource
	 * @throws JRException
	 * @throws IOException
	 */
	public static void reportXml(HttpServletResponse response, String jasperPath, String fileName, Map<String,Object> params, JRDataSource jRDataSource) throws JRException, IOException {
		JasperPrint jasperPrint = getJasperPrint(jasperPath, params, jRDataSource);
		
		JRXmlExporter exporter = new JRXmlExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleXmlExporterOutput(response.getOutputStream()));
		response.setHeader("Content-Disposition", "attachment;filename=" + getFileName(fileName) +".xml"); 
		exporter.exportReport();
	}
	
	/**
	 * ppt	默认文件名
	 * @param response
	 * @param jasperPath
	 * @param params
	 * @param jRDataSource
	 * @throws JRException
	 * @throws IOException
	 */
	public static void reportPptx(HttpServletResponse response, String jasperPath, Map<String,Object> params, JRDataSource jRDataSource) throws JRException, IOException {
		reportPptx(response, jasperPath, null, params, jRDataSource);
	}
	
	/**
	 * ppt	指定文件名
	 * @param response
	 * @param jasperPath
	 * @param fileName
	 * @param params
	 * @param jRDataSource
	 * @throws JRException
	 * @throws IOException
	 */
	public static void reportPptx(HttpServletResponse response, String jasperPath, String fileName, Map<String,Object> params, JRDataSource jRDataSource) throws JRException, IOException {
		JasperPrint jasperPrint = getJasperPrint(jasperPath, params, jRDataSource);
		
		JRPptxExporter exporter = new JRPptxExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
		response.setHeader("Content-Disposition", "attachment;filename=" + getFileName(fileName) +".pptx"); 
		exporter.exportReport();
	}
	
	/**
	 * csv	默认文件名
	 * @param response
	 * @param jasperPath
	 * @param params
	 * @param jRDataSource
	 * @throws JRException
	 * @throws IOException
	 */
	public static void reportCsv(HttpServletResponse response, String jasperPath, Map<String,Object> params, JRDataSource jRDataSource) throws JRException, IOException {
		reportCsv(response, jasperPath, null, params, jRDataSource);
	}
	
	/**
	 * csv	指定文件名
	 * @param response
	 * @param jasperPath
	 * @param fileName
	 * @param params
	 * @param jRDataSource
	 * @throws JRException
	 * @throws IOException
	 */
	public static void reportCsv(HttpServletResponse response, String jasperPath, String fileName, Map<String,Object> params, JRDataSource jRDataSource) throws JRException, IOException {
		JasperPrint jasperPrint = getJasperPrint(jasperPath, params, jRDataSource);
		
		JRCsvExporter exporter = new JRCsvExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleWriterExporterOutput(response.getOutputStream()));
		response.setHeader("Content-Disposition", "attachment;filename=" + getFileName(fileName) +".csv"); 
	    response.setContentType("text/csv"); 
		exporter.exportReport();
	}
	
	/**
	 * docx  默认文件名
	 * @param response
	 * @param jasperPath
	 * @param params
	 * @param jRDataSource
	 * @throws JRException
	 * @throws IOException
	 */
	public static void reportDocx(HttpServletResponse response, String jasperPath, Map<String,Object> params, JRDataSource jRDataSource) throws JRException, IOException {
		reportDocx(response, jasperPath, null, params, jRDataSource);
	}
	
	/**
	 * docx  指定文件名
	 * @param response
	 * @param jasperPath
	 * @param fileName
	 * @param params
	 * @param jRDataSource
	 * @throws JRException
	 * @throws IOException
	 */
	public static void reportDocx(HttpServletResponse response, String jasperPath, String fileName, Map<String,Object> params, JRDataSource jRDataSource) throws JRException, IOException {
		JasperPrint jasperPrint = getJasperPrint(jasperPath, params, jRDataSource);
		
		JRDocxExporter exporter = new JRDocxExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
		response.setHeader("Content-Disposition", "attachment;filename" + getFileName(fileName) +".docx"); 
	    response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document"); 
		exporter.exportReport();
	}
	
	/**
	 * xls	默认文件名
	 * @param response
	 * @param jasperPath
	 * @param params
	 * @param jRDataSource
	 * @throws JRException
	 * @throws IOException
	 */
	public static void reportXls(HttpServletResponse response, String jasperPath, Map<String,Object> params, JRDataSource jRDataSource) throws JRException, IOException {
		reportXls(response, jasperPath, null, params, jRDataSource);
	}
	
	/**
	 * xls	指定文件名
	 * @param response
	 * @param jasperPath
	 * @param fileName
	 * @param params
	 * @param jRDataSource
	 * @throws JRException
	 * @throws IOException
	 */
	public static void reportXls(HttpServletResponse response, String jasperPath, String fileName, Map<String,Object> params, JRDataSource jRDataSource) throws JRException, IOException {
		JasperPrint jasperPrint = getJasperPrint(jasperPath, params, jRDataSource);
	    
	    JRXlsExporter exporter = new JRXlsExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
		SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
		configuration.setOnePagePerSheet(false);
		exporter.setConfiguration(configuration);
		response.setHeader("Content-Disposition", "attachment;filename=" + getFileName(fileName) +".xls"); 
	    response.setContentType("application/vnd_ms-excel"); 
		exporter.exportReport();
	}
	
	/**
	 * xlsx  默认文件名
	 * @param response
	 * @param jasperPath
	 * @param params
	 * @param jRDataSource
	 * @throws JRException
	 * @throws IOException
	 */
	public static void reportXlsx(HttpServletResponse response, String jasperPath, Map<String,Object> params, JRDataSource jRDataSource) throws JRException, IOException {
		reportXlsx(response, jasperPath, null, params, jRDataSource);
	}
	
	/**
	 * xlsx  指定文件名
	 * @param response
	 * @param jasperPath
	 * @param fileName
	 * @param params
	 * @param jRDataSource
	 * @throws JRException
	 * @throws IOException
	 */
	public static void reportXlsx(HttpServletResponse response, String jasperPath, String fileName, Map<String,Object> params, JRDataSource jRDataSource) throws JRException, IOException {
		JasperPrint jasperPrint = getJasperPrint(jasperPath, params, jRDataSource);
		JRXlsxExporter exporter = new JRXlsxExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
		SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
		configuration.setOnePagePerSheet(false);
		exporter.setConfiguration(configuration);
		response.setHeader("Content-Disposition", "attachment;filename=" + getFileName(fileName) +".xlsx"); 
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); 
		exporter.exportReport();
	}
	
	/**
	 * pdf 多模板导出
	 * 
	 * paths:模板路径数组
	 * params:全局参数
	 * jrdss:数据源
	 * @param response
	 * @param paths
	 * @param params
	 * @param jrdss
	 * @throws JRException
	 * @throws IOException
	 */
	public static void reportPdfByJaspers(HttpServletResponse response, String[] paths, Map<String, Object> params, JRDataSource[] jrdss) throws JRException, IOException {
		reportByJaspers(response, paths, "pdf", params, jrdss);
	}
	
	/**
	 * docx 多模板导出
	 * 
	 * paths:模板路径数组
	 * params:全局参数
	 * jrdss:数据源
	 * @param response
	 * @param paths
	 * @param params
	 * @param jrdss
	 * @throws JRException
	 * @throws IOException
	 */
	public static void reportDocxByJaspers(HttpServletResponse response, String[] paths, Map<String, Object> params, JRDataSource[] jrdss) throws JRException, IOException {
		reportByJaspers(response, paths, "docx", params, jrdss);
	}
	
	/**
	 * ppt 多模板导出
	 * 
	 * paths:模板路径数组
	 * params:全局参数
	 * jrdss:数据源
	 * @param response
	 * @param paths
	 * @param params
	 * @param jrdss
	 * @throws JRException
	 * @throws IOException
	 */
	public static void reportPptxByJaspers(HttpServletResponse response, String[] paths, Map<String, Object> params, JRDataSource[] jrdss) throws JRException, IOException {
		reportByJaspers(response, paths, "pptx", params, jrdss);
	}
	
	private static void reportByJaspers(HttpServletResponse response, String[] paths, String suffix, Map<String, Object> params, JRDataSource[] jrdss) throws JRException, IOException {
		List<JasperPrint> jpList = new ArrayList<JasperPrint>();
		JRDataSource jrds = null;
		JasperReport jr = null;
		JasperPrint jp = null;
		for (int i = 0; i < paths.length; i++) {
			if (jrdss == null || jrdss.length == 0) {
				jrds = new JREmptyDataSource();
			} else {
				jrds = jrdss[i] == null ? new JREmptyDataSource() : jrdss[i];
			}
			
			InputStream resourceAsStream = JasperReportUtil.class.getClassLoader().getResourceAsStream(paths[i]);
			jr = (JasperReport)JRLoader.loadObject(resourceAsStream);
			
			jp = JasperFillManager.fillReport(jr, params, jrds);
			jpList.add(jp);
		}
		
		JRAbstractExporter exporter = getJRAbstractExporter(suffix);
		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jpList);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, arrayOutputStream);
		exporter.setParameter(JRHtmlExporterParameter.IS_OUTPUT_IMAGES_TO_DIR, Boolean.TRUE);
		exporter.exportReport();
		byte[] bt = arrayOutputStream.toByteArray();
		arrayOutputStream.close();
		setResponse(response, suffix, bt);
	}
	
	private static JRAbstractExporter getJRAbstractExporter(String suffix) {
		if("pdf".equals(suffix)) {
			return new JRPdfExporter();
		}else if("docx".equals(suffix)) {
			return new JRDocxExporter();
		}else if("pptx".equals(suffix)) {
			return new JRPptxExporter();
		}else {
			return null;
		}
	}
	
	private static void setResponse(HttpServletResponse response, String suffix, byte[] bt) throws IOException {
		String fileName = String.valueOf(System.currentTimeMillis()) + "." + suffix;
		response.setHeader("Content-Disposition", "attachment;filename" + fileName); 
		if("pdf".equals(suffix)) {
			response.setContentType("application/pdf"); 
		}else if("docx".equals(suffix)) {
			response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document"); 
		}else if("pptx".equals(suffix)) {
			response.setContentType("application/vnd.ms-powerpoint"); 
		}
		
		response.setCharacterEncoding("UTF-8");   
		ServletOutputStream os = response.getOutputStream();
		os.write(bt);
		os.close();
	}
	
	private static JasperPrint getJasperPrint(String jasperPath, Map<String,Object> params, JRDataSource jRDataSource) throws JRException {
		URL url = JasperReportUtil.class.getClassLoader().getResource(jasperPath);
		return JasperFillManager.fillReport(url.getPath(), params, jRDataSource);
	}
	
	private static String getFileName(String fileName) {
		if(StringUtil.isNotBlank(fileName)) {
			return fileName;
		}else {
			return String.valueOf(System.currentTimeMillis());
		}
	}
}
