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
import kr.dbas.psychologyvrapi.mapper.PhenoMapper;
import kr.dbas.psychologyvrapi.utils.FileInfo;
import kr.dbas.psychologyvrapi.utils.FileUtils;
import kr.dbas.psychologyvrapi.utils.Utils;

@Service
public class PhenoService {
    
    @Resource
    PhenoMapper phenoMapper;
    
    @Resource
    private FileUtils fileUtils;    

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

    
    //피노타입 검사종료 후에 전달되는 결과 데이터 수집
    public ApiResponse addPhenoResultData(HttpServletRequest request, Map<String, Object> param) throws Exception{
    	
        String userId = Utils.getString(param, "userId");
        String checkSerialNumber = Utils.getString(param, "checkSerialNumber");
        String IntercomResult = Utils.getString(param, "intercomResult");
        String taskComplete = Utils.getString(param, "taskComplete");
        String bookArrange = Utils.getString(param, "bookArrange"); 
        
        //VR_TASK_RESULT Table Data Insert
        JSONParser parser = new JSONParser();
        JSONObject bookArrange_obj = (JSONObject) parser.parse(String.valueOf(param.get("bookArrange")));
        Map<String, Object> map_1 = new HashMap<>();
        
        map_1.put("userId", userId);        
        System.out.println("### checkSerialNumber --> "+checkSerialNumber);     
        map_1.put("checkSerialNumber", checkSerialNumber);
        map_1.put("intercomResult", IntercomResult);
        
        map_1.put("size", (String)bookArrange_obj.get("size"));
        map_1.put("color", (String)bookArrange_obj.get("color"));
        map_1.put("double", (String)bookArrange_obj.get("double"));
        map_1.put("step1", (String)bookArrange_obj.get("step1"));
        map_1.put("step2", (String)bookArrange_obj.get("step2"));
        map_1.put("step3", (String)bookArrange_obj.get("step3"));
        map_1.put("stepNone", (String)bookArrange_obj.get("stepNone"));
        map_1.put("positionLeft", (String)bookArrange_obj.get("positionLeft"));
        map_1.put("positionRight", (String)bookArrange_obj.get("positionRight"));
        map_1.put("positionNone", (String)bookArrange_obj.get("positionNone"));
        map_1.put("wayStand", (String)bookArrange_obj.get("wayStand"));
        map_1.put("wayLie", (String)bookArrange_obj.get("wayLie"));
        map_1.put("wayNone", (String)bookArrange_obj.get("wayNone"));
        map_1.put("none", (String)bookArrange_obj.get("none"));
        
    	phenoMapper.insertVrTaskResult(map_1);
        

        
        
        // VR_TASK_COMPLETE data insert        
        JSONObject jsonObject = (JSONObject) parser.parse(String.valueOf(param.get("taskComplete")));    	
        JSONArray arr = (JSONArray)jsonObject.get("completeList");        
        List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
        		
        for (int i = 0; i < arr.size(); i++) {
            Map<String, Object> map_2 = new HashMap<>();
            JSONObject jsonCompleteObj =  (JSONObject) arr.get(i);
            map_2.put("userId", userId);
            map_2.put("checkSerialNumber", checkSerialNumber);
            map_2.put("taskCode", (String) jsonCompleteObj.get("taskCode"));
            map_2.put("completeYn", (String) jsonCompleteObj.get("completeYn"));
            map_2.put("completeRate", (String) jsonCompleteObj.get("completeRate"));
            
            listMap.add(map_2);
        }
        
        Map<String, Object> map_3 = new HashMap<>();
        map_3.put("completeList", listMap);
        phenoMapper.insertVrTaskComplete(map_3);        
        
        Map<String, Object> map_result = new HashMap<>();
		return ApiResponse.success(map_result);
    }    
       
    /**
     *  피노타입 각 시퀀스에서 수집된 이미지 저장 API
     * @param param
     * @param mFile
     * @return
     * @throws Exception
     */
    public ApiResponse uploadPhenoTypeImage(Map<String, Object> param, MultipartFile mFile) throws Exception {

        Object userId = param.get("userId");

        if (mFile != null) {
            String path = File.separator + "pheno" + File.separator +userId;
            System.out.println("path : " + path);
            FileInfo fileInfo = fileUtils.upload(mFile, path);
            
            String _getPath = fileInfo.getPath();
            String _getFileName = fileInfo.getOriginal();
            
            File file = new File(_getPath+"/"+_getFileName);
            InputStream in = new FileInputStream(file);
            
            BufferedImage inputImage = ImageIO.read(in);
            BufferedImage resize = resize(inputImage, 50, 50);
            
            FileOutputStream out = new FileOutputStream(_getPath+"/resize_"+_getFileName);
            ImageIO.write(resize, "png", out);
            out.flush();
            
	    }
        
        Map<String, Object> map_result = new HashMap<>();
		return ApiResponse.success(map_result);
    }   
    
    
	    /**
	     * image resizing
	     * @param image
	     * @param w
	     * @param h
	     * @return
	     */
	    public BufferedImage resize(final BufferedImage image, int w, int h) {
	    	
	        final int ow = image.getWidth();
	        final int oh = image.getHeight();
	
	        double sw = (double) w / (double) ow;
	        double sh = (double) h / (double) oh;
	
	        BufferedImage resize = new BufferedImage(w, h, image.getType());
	
	        AffineTransform transForm = new AffineTransform();
	        transForm.scale(sw, sh);
	        AffineTransformOp transformOp = new AffineTransformOp(transForm, AffineTransformOp.TYPE_BICUBIC);
	        return transformOp.filter(image, resize);
	    }
       
    
	    /**
	     * multipartFileToFile
	     * @param file
	     * @return
	     * @throws IOException
	     */
	    public static File multipartFileToFile(MultipartFile file) throws IOException {	      	
	        File convFile = new File(file.getOriginalFilename());
	        convFile.createNewFile();
	        return convFile;	        
	    }
    
    
}
