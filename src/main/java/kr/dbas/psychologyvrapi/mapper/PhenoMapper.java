package kr.dbas.psychologyvrapi.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PhenoMapper {

	public int insertPhenoData(Map<String, Object> map);
}
