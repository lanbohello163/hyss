package com.zxtech.ess.module.sys;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import com.google.common.io.Files;
import com.zxtech.platform.auth.JWTUtils;
import com.zxtech.platform.context.PlatformGlobalVar;
import com.zxtech.platform.utils.GlobalFileUtil;
import com.zxtech.platform.utils.StringUtil;
import com.zxtech.platform.utils.cache.CacheTemplate;
import com.zxtech.platform.utils.excel.EasyExcelUtil;
import com.zxtech.platform.vo.UserBean;

/**
 * 文件公共处理类
 */
@Controller
@RequestMapping("/sys")
public class FileHanldeController {
	private static Logger logger = LoggerFactory.getLogger(FileHanldeController.class);
	@Autowired
	private CommonsMultipartResolver multipartResolver;

	/**
	 * 将文件保存后，返回文件保存的服务器相对路径，如果同时上传多个文件用，分隔文件名称
	 * 
	 * @param request
	 * @param upload_type
	 * @return
	 */
	@RequestMapping(value = "/fileuploadsave.do")
	@ResponseBody
	public String handleFormUpload(HttpServletRequest request, String upload_type) {
		String result = "";
		if (this.multipartResolver != null && this.multipartResolver.isMultipart(request)) {
			if (request instanceof MultipartHttpServletRequest) {
				MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
				Map<String, MultipartFile> fileMap = mRequest.getFileMap();
				for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
					MultipartFile mf = entity.getValue();
					long fileSize = mf.getSize();
					if (fileSize > 50 * 1024 * 1024) {
						return "maxsizeerror";
					}
					try {
						UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
						String path = GlobalFileUtil.saveUploadFile(mf.getInputStream(), String.valueOf(user.getUserId()),
								mf.getOriginalFilename(), upload_type);
						result = result + path + ",";
					} catch (IOException e) {
						logger.error(e.getMessage(),e);
						return "error";
					}
				}
			}
		}
		if (result.endsWith(",")) {
			result = result.substring(0, result.length() - 1);
		}
		return result;
	}
	
	/**
	 * 获取附件实际存储路径
	 * 
	 * @param attach_url
	 * @return
	 */
	@RequestMapping(value = "/fetchrealfileuploadpath.do", method = { RequestMethod.GET })
	@ResponseBody
	public String fetchRealFilePath(String attach_url) {
		return GlobalFileUtil.getUploadPath() + attach_url;
	}

	@RequestMapping(value = "/fileuploaddelete.do")
	@ResponseBody
	public String handleFormDelete(String fileUrl) {
		String path = GlobalFileUtil.getUploadPath() + fileUrl;
		File file = new File(path);
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				return "success";
			} else {
				return "error";
			}
		} else {
			return "error";
		}
	}


	@RequestMapping(value = "/downloaduploadfile.do", method = { RequestMethod.GET })
	@ResponseBody
	public void downloadUploadFile(HttpServletRequest request, HttpServletResponse response, String fileName,
			String url) throws ServletException, IOException {
		//UserBean user = (UserBean) request.getAttribute(JWTUtils.CLAIMS_USER_KEY);
		// 实际存储路径
		String fileSaveRootPath = GlobalFileUtil.getUploadPath() + url;
		// 得到要下载的文件
		File file = new File(fileSaveRootPath);
		// 如果文件不存在
		if (!file.exists()) {
			response.setHeader("Content-Type", "text/plain;charset=utf-8");
			response.setStatus(200);
			response.getWriter().print("NoFileForDownLoad");
			return;
		}
		// 设置响应头，控制浏览器下载该文件
		String agent = (String)request.getHeader("User-Agent");
		
		//默认文件名
		if(StringUtil.isBlank(fileName)) {
			fileName = file.getName();
		}
		
		if (agent != null && agent.toLowerCase().indexOf("firefox") != -1) {
			fileName = "=?UTF-8?B?" + (new String(Base64.encodeBase64(fileName.getBytes("UTF-8")))) + "?=";
		} else {
			fileName = URLEncoder.encode(fileName, "utf-8");
		}
		response.addHeader("Content-Type", "application/octet-stream;charset=utf-8");
		response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
		// 读取要下载的文件，保存到文件输入流
		FileInputStream in = new FileInputStream(fileSaveRootPath);
		// 创建输出流
		OutputStream out = response.getOutputStream();
		// 创建缓冲区
		byte buffer[] = new byte[1024];
		int len = 0;
		// 循环将输入流中的内容读取到缓冲区当中
		while ((len = in.read(buffer)) > 0) {
			// 输出缓冲区的内容到浏览器，实现文件下载
			out.write(buffer, 0, len);
		}
		// 关闭文件输入流
		in.close();
		// 关闭输出流
		out.close();
	}
	
	@RequestMapping(value = "/downLoadFile.do", method = { RequestMethod.GET, RequestMethod.POST })
	public void downLoadFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String tempPath = PlatformGlobalVar.SYS_PROPERTIES.get("realDownloadCatalog");
		
		String fileName = URLDecoder.decode(request.getParameter("fileName"), "UTF-8");
		String timestamp = request.getParameter("timestamp");
		try {
			String newFileName = fileName;
			newFileName = newFileName.replace(timestamp, "");
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/octet-stream");
			if(isMSBrowser(request)){
				response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
			}else{
				response.setHeader("content-disposition", "attachment;filename=" + new String(newFileName.getBytes(), "ISO8859-1"));
			}
			
			//UUID 32位 + "."  + 后缀名(长度)
			int extensionLength = 33 + Files.getFileExtension(fileName).length();
	        File file = new File(tempPath + File.separator + fileName.substring(fileName.length() - extensionLength, fileName.length()));
	        
	        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));  
	        BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());  
	        byte[] buff = new byte[2048];  
	        int bytesRead;  
	        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
	        	bos.write(buff, 0, bytesRead);  
	        }  
	        bis.close();  
	        bos.close();
	        //删除临时文件
	        if(file.exists()){
	        	file.delete();
	        }
	        CacheTemplate.REDIS.execute((RedisConnection conn) -> {
				conn.del((EasyExcelUtil.EXPORT_GUID_HEAD+fileName.substring(fileName.length()-Files.getFileExtension(fileName).length()-33, 
		        		fileName.length()-Files.getFileExtension(fileName).length()-1)).getBytes());
				return null;
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("文件下载失败!");
		}
	}
	
	//方法功能描述:       判断是否是IE浏览器 
	public boolean isMSBrowser(HttpServletRequest request) {
		String[] IEBrowserSignals = {"MSIE", "Trident", "Edge"};
		String userAgent = request.getHeader("User-Agent");
		for (String signal : IEBrowserSignals) {
			if (userAgent.contains(signal)){
				return true;
			}
		}
		return false;
	}
	
}
