package kr.dbas.psychologyvrapi.vo;

import lombok.Data;

/**
 * Image 정보
 * @author ghost1236
 *
 */
@Data
public class ImageVO {

	/* 파일경로 */
    private String filePath;
    
    /* 파일명 */
    private String fileName;
    
}
