package kr.dbas.psychologyvrapi.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import kr.dbas.psychologyvrapi.dao.ApiResponse;
import kr.dbas.psychologyvrapi.mapper.PhenoMapper;
import kr.dbas.psychologyvrapi.utils.Utils;

@Service
public class PhenoService {
    
    @Resource
    PhenoMapper phenoMapper;

    public ApiResponse addPhenoData(HttpServletRequest request, Map<String, Object> param) throws Exception{
        // object를 잡고 놓은 경과시간 
        String gCountStartTime = Utils.getString(param, "gCountStartTime");
        String gCountEndTime = Utils.getString(param, "gCountEndTime");
        String gCountLeadTime = Utils.getBetweenTime(gCountStartTime, gCountEndTime);
        param.put("gCountLeadTime", gCountLeadTime);

        String vCountStartTime = Utils.getString(param, "vCountStartTime");
        String vCountEndTime = Utils.getString(param, "vCountEndTime");
        String vCountLeadTime = Utils.getBetweenTime(vCountStartTime, vCountEndTime);
        param.put("vCountLeadTime", vCountLeadTime);

        phenoMapper.insertPhenoData(param);
        
        Map<String, Object> map = new HashMap<>();
		return ApiResponse.success(map);
    }


}
