package kr.dbas.psychologyvrapi.dao;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class ApiResponse {

	private ApiResponseHeader header;
	private Map<String, Object> body;
	
	
	public static String CODE_SUCESS = "0000";
	public static String CODE_REQUIRED_PARAMETER_ERROR = "1000";
	
	public static String CODE_NO_CONTENT = "2000";
	
	public static String CODE_EXPIRE_SESSIONID = "0100";
	public static String CODE_NOT_PROFILE = "0101";
	public static String CODE_ACCESS_TOKEN_ERROR = "0102";
	public static String CODE_STOP_USER = "0103";
	
	// 갤러리 업로드 관련 에러
	public static String RESULT_NOT_UPLOAD_FILE = "0200";
	
	// 좋아요 업데이트 실패
	public static String CODE_FAIL_LIKE_COUNT = "0300";
	
	public static String CODE_UDATE_PROFILE_FAIL = "0400";
	
	public static String CODE_FAIL_DELETE_GALLERY = "0500";
	public static String CODE_NODATA_DELETE_GALLERY = "0501";
	public static String CODE_NO_MY_DELETE_GALLERY = "0502";
	
	public static String CODE_FAIL_UPDATE_GALLERY = "0600";
	public static String CODE_NO_MY_UPDATE_GALLERY = "0601";
	
	
	private final static String MESSAGE_SUCCESS = "SUCCESS";
	public final static String MESSAGE_REQUIRED_PARAMETER_ERROR = "[필수 파라미터 오류] 파라미터를 확인 해 주세요.";

	
	public static ApiResponse success(Map<String, Object> body) {
        return new ApiResponse(new ApiResponseHeader(CODE_SUCESS, MESSAGE_SUCCESS), body);
    }
	
	public static ApiResponse fail(String code, String message) {
        return new ApiResponse(new ApiResponseHeader(code, message), null);
    }

	public static ApiResponse fail(String code, String message, Map<String, Object> body) {
        return new ApiResponse(new ApiResponseHeader(code, message), body);
    }
}
