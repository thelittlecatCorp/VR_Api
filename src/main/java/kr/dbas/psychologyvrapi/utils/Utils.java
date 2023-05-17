package kr.dbas.psychologyvrapi.utils;

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
	
}
