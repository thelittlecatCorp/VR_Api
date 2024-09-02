package kr.dbas.psychologyvrapi.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.dbas.psychologyvrapi.dao.ApiResponse;
import kr.dbas.psychologyvrapi.service.HealingService;
import kr.dbas.psychologyvrapi.service.PhenoService;
import kr.dbas.psychologyvrapi.utils.FileUtils;
import kr.dbas.psychologyvrapi.vo.ImageVO;


@RequestMapping("/api/heal")
@RestController
public class HealingController extends BaseController {

	@Resource
	private HealingService healService;
	
	
	@GetMapping("/getStart")
	public ResponseEntity<?> getStart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ApiResponse apiResponse = new ApiResponse(null, null);
		
		try {
			Map<String, Object> map = getParameterMap(request);
			
			String massage = request.getParameter("msg");
			String[] strArr = massage.split(",");			
			map.put("vital_divice_number", strArr[1]);
			
			apiResponse = healService.getStart(request, map);
			
			return getReturn(HttpServletResponse.SC_OK, apiResponse);
		} catch (Exception e) {
			e.printStackTrace();
			return getReturn(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, null);
		}		
	}
		
	
	@GetMapping("/getEnd")
	public ResponseEntity<?> getEnd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ApiResponse apiResponse = new ApiResponse(null, null);
		
		try {
			Map<String, Object> map = getParameterMap(request);
			
			String massage = request.getParameter("msg");
			String[] strArr = massage.split(",");			
			map.put("vital_divice_number", strArr[1]);
			
			apiResponse = healService.getEnd(request, map);
			
			return getReturn(HttpServletResponse.SC_OK, apiResponse);
		} catch (Exception e) {
			e.printStackTrace();
			return getReturn(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, null);
		}	
	}
	
	@GetMapping("/mileSecondData")
	public ResponseEntity<?> mileSecondData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ApiResponse apiResponse = new ApiResponse(null, null);
		
		try {
			Map<String, Object> map = getParameterMap(request);
			
			String massage = request.getParameter("msg");
			String[] strArr = massage.split(",");			
			map.put("vital_divice_number", strArr[1]);
			map.put("gsr_val", strArr[2]);
			map.put("ppg_val", strArr[3]);
			
			apiResponse = healService.mileSecondData(request, map);
			
			return getReturn(HttpServletResponse.SC_OK, apiResponse);
		} catch (Exception e) {
			e.printStackTrace();
			return getReturn(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, null);
		}	
	}	
	
	@GetMapping("/secondData")
	public ResponseEntity<?> secondData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ApiResponse apiResponse = new ApiResponse(null, null);
		
		try {
			Map<String, Object> map = getParameterMap(request);
			
			String massage = request.getParameter("msg");
			String[] strArr = massage.split(",");			
			map.put("vital_divice_number", strArr[1]);
			map.put("bpm_val", strArr[2]);		
			
			apiResponse = healService.secondData(request, map);
			
			return getReturn(HttpServletResponse.SC_OK, apiResponse);
		} catch (Exception e) {
			e.printStackTrace();
			return getReturn(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, null);
		}	
	}	
	
	@GetMapping("/minuteData")
	public ResponseEntity<?> minuteData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ApiResponse apiResponse = new ApiResponse(null, null);
		
		try {
			Map<String, Object> map = getParameterMap(request);
			
			String massage = request.getParameter("msg");
			String[] strArr = massage.split(",");			
			map.put("vital_divice_number", strArr[1]);
			map.put("step_count", strArr[2]);
			map.put("bpm_value", strArr[3]);				
		
			
			apiResponse = healService.minuteData(request, map);
			
			return getReturn(HttpServletResponse.SC_OK, apiResponse);
		} catch (Exception e) {
			e.printStackTrace();
			return getReturn(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, null);
		}	
	}	
	
}
