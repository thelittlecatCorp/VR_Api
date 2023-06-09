package kr.dbas.psychologyvrapi.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {
	
	public static String RESULT_CODE = "code";
	public static String RESULT_MSG = "msg";
	
	public static String HEADER_LANG = "lang";
	public static String HEADER_DEVICE_ID = "deviceId";
	public static String HEADER_SESSION_ID = "sessionId";
	
	public static String HEADER_KEY_LANG = "X-VR-lang";
	public static String HEADER_KEY_DEVICE_ID = "X-VR-deviceid";
	

	
	public static String LOGINTYPE_GOOGLE = "G";
	public static String LOGINTYPE_KAKAO = "K";
	public static String LOGINTYPE_FACEBOOK = "F";


	// 0 : localhost, 1: REAL
	public static int SYSTEM_LOCATION = 1;
	public static String IMAGE_REAL_URL = "http://ec2-15-164-221-196.ap-northeast-2.compute.amazonaws.com:8080/api/files";
	public static String IMAGE_LOCALHOST_URL = "http://192.168.0.29:9095/api/files";
	
	public static String FILE_IMAGE_PATH_LOCALHOST = "/Users/ghost1236/Pictures/Makit/files";
	public static String FILE_IMAGE_PATH_DEV = "/home/ec2-user/apache-tomcat-8.5.83/webapps/files";
	
	public static String DIR_CAPTURE = "capture";
	public static String DIR_GALLERY = "gallery";
	public static String DIR_PROFILE = "profile";
		
}
