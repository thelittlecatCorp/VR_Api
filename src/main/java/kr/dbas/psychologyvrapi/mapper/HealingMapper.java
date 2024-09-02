package kr.dbas.psychologyvrapi.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HealingMapper {

	public int getStart(Map<String, Object> param);

	public int insertHealcheckData(Map<String, Object> param);

	public int getEnd(Map<String, Object> param);

	public int mileSecondData(Map<String, Object> map);

	public int secondData(Map<String, Object> map);

	public int minuteData(Map<String, Object> map);

	public int settingStart(Map<String, Object> map);	
	
}
