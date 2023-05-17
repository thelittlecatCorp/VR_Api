package kr.dbas.psychologyvrapi.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import kr.dbas.psychologyvrapi.dao.ApiResponse;
import kr.dbas.psychologyvrapi.mapper.PhenoMapper;
import kr.dbas.psychologyvrapi.utils.Utils;

@Service
public class PhenoService {
    
    @Resource
    PhenoMapper phenoMapper;

    public ApiResponse addPhenoData(HttpServletRequest request, Map<String, Object> param) throws Exception{
        
        phenoMapper.addPhenoData(param);
    }


}
