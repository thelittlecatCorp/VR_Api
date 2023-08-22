package kr.dbas.psychologyvrapi.utils;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class FileInfo {
	
	private String path;
	private String original;
	private String uploaded;
	private String extend;
	private String contentType;
	private Long size;
	
	/**
	 * GET File Info
	 * @param mf
	 */
	public FileInfo(MultipartFile mf) {
		String original = mf.getOriginalFilename();
		String extend = original.substring(original.indexOf(".")+1);
		String contentType = mf.getContentType();
		Long size = mf.getSize();
		
		//String uploaded = createSystemFileName().concat(".").concat(extend);
		String uploaded = mf.getOriginalFilename();
		this.setUploaded(uploaded);
		this.setOriginal(original);
		this.setExtend(extend);
		this.setContentType(contentType);
		this.setSize(size);
		
	}
	
	/**
	 * Create file Name
	 * @return
	 */
	private static String createSystemFileName(){
		return String.valueOf(System.currentTimeMillis());
	}
}
