package kr.dbas.psychologyvrapi.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.dbas.psychologyvrapi.dao.ApiResponse;



@RequestMapping("/api/pheno")
@RestController
public class PhenoController extends BaseController {

	
	@PostMapping("/taskProgress")
	public ResponseEntity<?> addPhynoData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ApiResponse apiResponse = new ApiResponse(null, null);
		
		try {
			Map<String, Object> map = getParameterMap(request);
			apiResponse = userService.login(request, map);
			
			return getReturn(HttpServletResponse.SC_OK, apiResponse);
		} catch (Exception e) {
			e.printStackTrace();
			return getReturn(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, null);
		}
		
	}
	
	
}
