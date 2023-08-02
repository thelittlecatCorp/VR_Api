package kr.dbas.psychologyvrapi.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PhenoMapper {

	public int insertPhenoData(Map<String, Object> map);
	
	public int insertVrTaskResult(Map<String, Object> map);	
	
	public int insertVrTaskComplete(Map<String, Object> map);	
	
}
