package com.nexters.ssss.controller.gateway.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.nexters.ssss.conf.conf;
import com.nexters.ssss.db.dao.DAO_RECORD_Impl;
import com.nexters.ssss.db.dao.DAO_USER_Impl;
import com.nexters.ssss.db.dto.DTO_RECORD;
import com.nexters.ssss.db.dto.DTO_USER;
import com.nexters.ssss.util.FileUtil;
import com.nexters.ssss.util.serviceIf;
import com.nexters.ssss.util.sessionUtil;

/**
 * 업로드 서비스
 * @author 이준범
 *
 */

@Service
public class UD0001 implements serviceIf {
	private static final Logger logger = LoggerFactory.getLogger(UD0001.class);

	@Autowired
	private SqlSession sqlsession;
	
	private static final boolean isNeedLogin = false;
	private sessionUtil sessionutil;

	@Override
	public Map<String, Object> doFirst(HttpSession session, SqlSession sqlsession_gw, JSONObject reqData) {
		sessionutil = new sessionUtil(session);
		if(isNeedLogin){
			sessionutil.isUserLogin();
		}
		
		return execute(reqData);
	}

	@Override
	public Map<String, Object> execute(JSONObject reqData) {
		// TODO Auto-generated method stub
		Map<String, Object> rsltMap = new HashMap<String, Object>();
		Map<String, Object> hashmap = new HashMap<String, Object>();
		
		DAO_RECORD_Impl record_dao = new DAO_RECORD_Impl(sqlsession); // dao object
		DTO_RECORD record= new DTO_RECORD(); // dto object
		
		String strBase64 = (String) reqData.get("rec_file"); //base64 인코딩된걸 여기에 받아야한다.
		FileUtil fu = new FileUtil();
		try {
			// 사용자의 정보를 가져온다.
			//DTO_USER dtoUser = sessionutil.getUserInform();
			//파일을 바이너리로 저장 뒤, 저장된 파일 경로를 받는다.
			String strFilePath = fu.base64StringToFile(strBase64, conf.FILE_TEMP_PATH, "zip");
			
			//압축파일을 BRD_VOICE_PATH 변수로 설정된 곳에 압축해제를 한다.
			
			ArrayList<String> listFiles = fu.unZip(strFilePath, conf.BRD_VOICE_PATH, false);
			logger.debug("Zip List::"+listFiles.toString());
			////////////////////////////서버에 등록
			
			//2개는 client로 부터온다. 
			String strVoiceFilePath = (String)listFiles.get(0);
			//파일 경로가 없을 경우 압축해제를 실패했거나 정상적으로 해당 액션이 안된 경우를 처리해준다.
			if(strVoiceFilePath!=null) {
				record.setFile_path(strVoiceFilePath);
				record.setIs_file_yn("Y");
			} else {
				record.setFile_path("");
				record.setIs_file_yn("N");
			}
			

			//1개는 session에 저장되어 있는 것을 가져온다.
			//record.setUsr_no(dtoUser.getUsr_no());
			record.setUsr_no("1502100132290013");
			/// 2개뺴고 나머지 4개는 sql문에서 처리한다.
			record_dao.add_record(record);

			//압축 해제된 파일 리스트
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return rsltMap;
	}
}
