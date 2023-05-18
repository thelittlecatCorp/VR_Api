package kr.dbas.psychologyvrapi.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import kr.dbas.psychologyvrapi.dao.ApiResponse;
import kr.dbas.psychologyvrapi.service.PhenoService;



@RequestMapping("/api/pheno")
@RestController
public class PhenoController extends BaseController {

	@Resource
	private PhenoService phenoService;
	
	@PostMapping("/taskProgress")
	public ResponseEntity<?> addPhynoData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ApiResponse apiResponse = new ApiResponse(null, null);
		
		try {
			Map<String, Object> map = getParameterMap(request);
			apiResponse = phenoService.addPhenoData(request, map);
			
			return getReturn(HttpServletResponse.SC_OK, apiResponse);
		} catch (Exception e) {
			e.printStackTrace();
			return getReturn(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, null);
		}
		
	}
	
	
}
