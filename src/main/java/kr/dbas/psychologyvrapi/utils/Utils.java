package kr.dbas.psychologyvrapi.utils;

import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class Utils {

	/**
	 * Object null check
	 * @param Object
	 * @return Boolean
	 */
	public static Boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;
		}
		if ((obj instanceof String) && (((String) obj).trim().length() == 0)) {
			return true;
		}
		if (obj instanceof Map) {
			return ((Map<?, ?>) obj).isEmpty();
		}
		if (obj instanceof List) {
			return ((List<?>) obj).isEmpty();
		}
		if (obj instanceof Object[]) {
			return (((Object[]) obj).length == 0);
		}
		return false;
	}
	
	
	public static Map<String, Object> jsonToMap(String json) {
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			Map<String, Object> body = mapper.readValue(json, Map.class);
			System.out.println("body : " + body);
			return body;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static int getInteger(Map<String, Object> obj, String key) {
        try {
            Object resultObj = obj.get(key);

            if (resultObj != null) {
                int result = (Integer) resultObj;
                return result;
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
	
	public static String getString(Map<String, Object> obj, String key) {
		try {
			Object resultObj = obj.get(key);

			if (resultObj != null) {
				String result = resultObj.toString();
				return result;
			} else {
				return new String("");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new String("");
		}
	}

	public static boolean getBoolean(Map<String, Object> obj, String key) {
		try {
			Object resultObj = obj.get(key);

			if (resultObj != null) {
				return Utils.nullToFalse(resultObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static Map<String, Object> getHashMap(Map<String, Object> obj, String key) {
		try {
			Object resultObj = obj.get(key);

			if (resultObj != null) {
				Map<String, Object> result = (Map<String, Object>) resultObj;
				return result;
			} else {
				return new HashMap<>();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new HashMap<>();
		}
	}
	
	public static boolean nullToFalse(Object obj) {
		if(obj != null) {
			return nullToFalse(obj.toString());
		}
		return false;
	}

	public static boolean nullToFalse(String str) {
		if("true".equalsIgnoreCase(str) || "yes".equalsIgnoreCase(str)) {
			return true;
		}
		return false;
	}
	
	/**
     * 아이디 생성
     * @return
     */
    public static String generateId(String prefix, String idx) {
        StringBuffer sb = new StringBuffer();
        sb.append(prefix).append(idx);
        return sb.toString();
    }
    
    public static String generateSessionId() {
        return UUID.randomUUID().toString().toUpperCase().replace("-", "");
    }
	
	public static String getBetweenTime(String startTime, String endTime) {
		
		String[] sTimes = {startTime.substring(0,2), startTime.substring(3,5), startTime.substring(6,8)};
		String[] eTimes = {endTime.substring(0,2), endTime.substring(3,5), endTime.substring(6,8)};
		

		LocalTime time1 = LocalTime.of(Integer.parseInt(sTimes[0]), Integer.parseInt(sTimes[1]), Integer.parseInt(sTimes[2]));
		LocalTime time2 = LocalTime.of(Integer.parseInt(eTimes[0]), Integer.parseInt(eTimes[1]), Integer.parseInt(eTimes[2]));

		Duration duration = Duration.between(time1, time2);

		long hours = duration.toHours(); // Extract hours from the duration
        long minutes = duration.toMinutes() % 60; // Extract minutes from the duration
        long seconds = duration.getSeconds() % 60; // Extract seconds from the duration
        
		return String.format("%02d", hours) + ":" + String.format("%02d", minutes) + "." + String.format("%02d", seconds);
	}
}
