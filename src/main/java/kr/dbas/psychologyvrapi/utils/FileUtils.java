package kr.dbas.psychologyvrapi.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.net.URLDecoder;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUtils {

	@Resource
	private ServletContext context;
	
	private int SYSTEM_LOCATION = Constants.SYSTEM_LOCATION;
	private String FILE_LOCALHOST_IMAGE = Constants.FILE_IMAGE_PATH_LOCALHOST;
	private String FILE_DEV_PATH = Constants.FILE_IMAGE_PATH_DEV;
	
	public FileInfo upload(MultipartFile mf, String folerPath) throws IllegalStateException, IOException {
		if (mf == null || mf.isEmpty()) {
			return null;
		}
		
		String rootPath = "";
		if (SYSTEM_LOCATION == 0) {
			rootPath = FILE_LOCALHOST_IMAGE; 				
		} else {
			rootPath = FILE_DEV_PATH; 
		}
		
		StringBuffer path = new StringBuffer(1024);
		path.append(rootPath);
		path.append(folerPath);
		
		FileInfo fi = new FileInfo(mf);
		fi.setPath(String.valueOf(path));
		File dir = new File(String.valueOf(path));
		if (!dir.exists())
			dir.mkdirs();

		File f = new File(fi.getPath(), fi.getUploaded());
		mf.transferTo(f);
		
		return fi;
	}
	
	public byte[] getImages(String filePath) throws Exception {
		byte[] imageInByte = null ;
		String pathTmp = "";
//		String regEx = "[~`!@#$%\\^&*()]"; // 특수문자
		if(!"".equals(filePath) && filePath != null) { // null 체크
			if (SYSTEM_LOCATION == 0) {
				pathTmp = FILE_LOCALHOST_IMAGE + File.separator + filePath; 				
			} else {
				pathTmp = FILE_DEV_PATH + File.separator + filePath; 
			}
			
			File file = new File(URLDecoder.decode(pathTmp, "UTF-8"));
			
			if (!file.exists()) {
				return null;
			}
			
			imageInByte = new byte[(int) file.length()];
	        FileInputStream fis = new FileInputStream(file);
	        fis.read(imageInByte);
	        fis.close();
		}
		
		return imageInByte;
	}
	
	/**
	 * Get File MimeType
	 */
	public MediaType getFileMimeType(String filePath) throws Exception {
		String imgGif = "image/gif";
		String imgJpeg = "image/jpeg";
		String imgPng = "image/png";
	
		String rootPath = "";
		if (SYSTEM_LOCATION == 0) {
			rootPath = FILE_LOCALHOST_IMAGE; 				
		} else {
			rootPath = FILE_DEV_PATH; 
		}
		
		StringBuffer path = new StringBuffer(1024);
		path.append(rootPath);
		path.append(File.separator);
		path.append(filePath);
		
		File file = new File(String.valueOf(path));
		String mimeType =  URLConnection.guessContentTypeFromName(file.getName());
		
		if (imgGif.equalsIgnoreCase(mimeType)) {
			return MediaType.IMAGE_GIF;
		} else if (imgJpeg.equalsIgnoreCase(mimeType)) {
			return MediaType.IMAGE_JPEG;
		} else if (imgPng.equalsIgnoreCase(mimeType)) {
			return MediaType.IMAGE_PNG;
		}
		
		return MediaType.IMAGE_JPEG;
	}
	
	/**
	 * Folder Delete
	 */
	public void deleteFile(String filePath) {
		String rootPath = "";
		if (SYSTEM_LOCATION == 0) {
			rootPath = FILE_LOCALHOST_IMAGE; 				
		} else {
			rootPath = FILE_DEV_PATH; 
		}
		
		StringBuffer path = new StringBuffer(1024);
		path.append(rootPath);
		path.append(File.separator);
		path.append(filePath);
		
		File file = new File(String.valueOf(path));
		if (file.exists()) {
			file.delete();
		}
	}
	
}
