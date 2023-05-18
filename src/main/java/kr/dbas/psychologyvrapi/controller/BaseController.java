package kr.dbas.psychologyvrapi.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import kr.dbas.psychologyvrapi.utils.Constants;

@RestController
public class BaseController {

	@Resource
	private ServletContext context;

		
	/**
	 * Common Return Response
	 */
	public ResponseEntity<?> getReturn(int code, Object object) {
		if (code == HttpServletResponse.SC_OK || code == HttpServletResponse.SC_ACCEPTED) {
			return ResponseEntity.ok(object);
		} else {
			return ResponseEntity.status(code).body(object);
		}
	}
	
	
	public static Map<String, Object> getParameterMap(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Enumeration param = request.getParameterNames();
		while(param.hasMoreElements()) {
			String name = param.nextElement().toString();
			String value = request.getParameter(name);
			map.put(name, value);
		}
		
		map.put(Constants.HEADER_LANG, request.getHeader(Constants.HEADER_KEY_LANG));
        map.put(Constants.HEADER_DEVICE_ID, request.getHeader(Constants.HEADER_KEY_DEVICE_ID));
			
        System.out.println("ParamMap : " + map);
        
		return map;
	}
	
	/**
	 * Request Parameter to Map
	 * @param request HttpServletRequest Object
	 * @return (parameter name, parameter value) Pair Map
	 */
	public static Map<String, Object> getParameterMap(MultipartHttpServletRequest request) {
		Map<String, Object> reqMap = new HashMap<String, Object>();
		Enumeration param = request.getParameterNames();
		while(param.hasMoreElements()) {
			String name = param.nextElement().toString();
			String value = request.getParameter(name);
			reqMap.put(name, value);
		}
		
		reqMap.put(Constants.HEADER_LANG, request.getHeader(Constants.HEADER_KEY_LANG));
		reqMap.put(Constants.HEADER_DEVICE_ID, request.getHeader(Constants.HEADER_KEY_DEVICE_ID));
		
		System.out.println("MultipartParamMap : " + reqMap);
		
		return reqMap;
	}
	
	
	
}
