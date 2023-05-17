package kr.dbas.psychologyvrapi.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PhenoMapper {
    
    // 피노 타입 검사
	public int addPhenoData(Map<String, Object> param);
}
