package com.zxtech.platform.utils;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.util.Strings;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.zxtech.ess.app.util.SysTools;
import com.zxtech.platform.context.PlatformGlobalVar;

public class GlobalFileUtil {

	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.toLowerCase().substring(dot + 1);
			}
		}
		return filename;
	}

	public static String getUploadPath() {
		String uploadPath = "";
		String isDebug = PlatformGlobalVar.SYS_PROPERTIES.get("isDebugMode");
		if ("1".equals(isDebug)) {
			String nowPath = GlobalFileUtil.class.getClassLoader().getResource("/").getPath();
			uploadPath = nowPath.substring(0, nowPath.indexOf("classes")) + "upload/";
		} else {
			uploadPath = PlatformGlobalVar.SYS_PROPERTIES.get("realUploadCatalog") + "/";
		}
		return uploadPath;
	}

	public static String saveUploadFile(InputStream fileStream, String userId, String srcFileName, String upload_type)
			throws IOException {
		String catalog = DateUtil.date2String(new Date(), "yyyyMM");
		String realPath = getUploadPath() + upload_type + "/" + catalog;
		File dir = new File(realPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		String suffixName = srcFileName.substring(srcFileName.lastIndexOf(".")).toLowerCase();
		String fileName = Strings.EMPTY;
		
		fileName += "/" + SysTools.getGUID() + suffixName;
		FileOutputStream fout = new FileOutputStream(new File(realPath + fileName));
		BufferedInputStream bis = new BufferedInputStream(fileStream);
		byte[] cacheArray = new byte[2048];
		int size;
		while ((size = bis.read(cacheArray)) != -1) {
			fout.write(cacheArray, 0, size);
		}
		fout.close();
		bis.close();
		return upload_type + "/" + catalog + fileName;
	}

	/**
	 * 头像上传 临时编写
	 * 
	 * @param request
	 * @param response
	 * @param second_path
	 * @return
	 */
	public static String uploadEmpPhoto(HttpServletRequest request, HttpServletResponse response, String second_path,
			String filename) {
		response.setContentType("text/html;charset=utf-8");
		try {
			request.setCharacterEncoding("utf-8");
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());
			String path = "";
			String su = "";
			if (multipartResolver.isMultipart(request)) {
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				@SuppressWarnings("rawtypes")
				Iterator iter = multiRequest.getFileNames();
				while (iter.hasNext()) {
					MultipartFile file = multiRequest.getFile((String) iter.next());
					if (file != null) {
						// fileName = file.getOriginalFilename();
						// fileName = new
						// String(fileName.getBytes("gbk"),"utf-8");
						String nowPath = PlatformGlobalVar.SYS_PROPERTIES.get("realUploadCatalog") + "/";
						su = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."))
								.toLowerCase();
						path = nowPath + second_path + "/" + filename + su;
						File localFile = new File(path);

						// BufferedImage bIMG =ImageIO.read(localFile);
						// path = nowPath + second_path + "/"+ filename +".jpg";
						// ImageIO.write(bIMG, "jpg", new File(path));
						if (!localFile.exists()) {
							localFile.mkdirs();
						}
						file.transferTo(localFile);
					}
				}

			}
			return filename + su;
		} catch (Exception e) {
			e.printStackTrace();
			return "FAIL";
		}
	}

	/**
	 * 图像裁剪
	 * 
	 * @param x1
	 * @param y1
	 * @param width
	 * @param height
	 * @param sourcePath
	 * @param descpath
	 */
	public static void pic_cut(int x1, int y1, int width, int height, String sourcePath, String descpath) {
		FileInputStream is = null;
		ImageInputStream iis = null;
		try {
			String nowPath = PlatformGlobalVar.SYS_PROPERTIES.get("realUploadCatalog") + "/";
			sourcePath = nowPath + sourcePath;
			is = new FileInputStream(sourcePath);
			String fileSuffix = sourcePath.substring(sourcePath.lastIndexOf(".") + 1);
			Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(fileSuffix);
			ImageReader reader = it.next();
			iis = ImageIO.createImageInputStream(is);
			reader.setInput(iis, true);
			ImageReadParam param = reader.getDefaultReadParam();
			Rectangle rect = new Rectangle(x1, y1, width, height);
			param.setSourceRegion(rect);
			BufferedImage bi = reader.read(0, param);
			descpath = nowPath + descpath;
			ImageIO.write(bi, fileSuffix, new File(descpath));
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				is = null;
			}
			if (iis != null) {
				try {
					iis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				iis = null;
			}
		}
	}
	
	/**
	 * 获取下载文件保存路径
	 * @return
	 * @author: syp637
	 */
	public static String getDownloadPath() {
		String filePath = PlatformGlobalVar.SYS_PROPERTIES.get("realDownloadCatalog");
		File file = new File(filePath);
		if(!file.exists()){  
		    file.mkdirs();  
		}
		return filePath;
	}
	
	/**
	 * 获取classpash 下的文件InputStream
	 * @param fileName
	 * @return
	 * @author: syp637
	 */
	public static InputStream getResourcesFileInputStream(String fileName) {
		return GlobalFileUtil.class.getClassLoader().getResourceAsStream(fileName);
	}
}
