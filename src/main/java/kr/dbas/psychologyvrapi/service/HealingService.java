package kr.dbas.psychologyvrapi.service;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.dbas.psychologyvrapi.dao.ApiResponse;
import kr.dbas.psychologyvrapi.mapper.HealingMapper;
import kr.dbas.psychologyvrapi.mapper.PhenoMapper;
import kr.dbas.psychologyvrapi.utils.FileInfo;
import kr.dbas.psychologyvrapi.utils.FileUtils;
import kr.dbas.psychologyvrapi.utils.Utils;
import kr.dbas.psychologyvrapi.vo.ImageVO;

@Service
public class HealingService {
    
    @Resource
    HealingMapper mapper;

    public ApiResponse getStart(HttpServletRequest request, Map<String, Object> map) throws Exception{
    	//int setting = mapper.settingStart(map);
    	int state = mapper.getStart(map);        
		if (state == -1) return ApiResponse.fail(null, null);
		return ApiResponse.success(map);
    }


	public ApiResponse getEnd(HttpServletRequest request, Map<String, Object> map) throws Exception{
    	int state = mapper.getEnd(map);        
		if (state == -1) return ApiResponse.fail(null, null);
		return ApiResponse.success(map);
	}

	public ApiResponse mileSecondData(HttpServletRequest request, Map<String, Object> map) {
    	int state = mapper.mileSecondData(map);        
		if (state == -1) return ApiResponse.fail(null, null);
		return ApiResponse.success(map);
	}


	public ApiResponse secondData(HttpServletRequest request, Map<String, Object> map) {
    	int state = mapper.secondData(map);        
		if (state == -1) return ApiResponse.fail(null, null);
		return ApiResponse.success(map);
	}


	public ApiResponse minuteData(HttpServletRequest request, Map<String, Object> map) {
    	int state = mapper.minuteData(map);        
		if (state == -1) return ApiResponse.fail(null, null);
		return ApiResponse.success(map);
	}
    
}
