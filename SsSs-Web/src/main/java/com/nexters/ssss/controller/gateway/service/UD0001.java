package com.nexters.ssss.controller.gateway.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nexters.ssss.conf.conf;
import com.nexters.ssss.db.dao.DAO_RECORD_Impl;
import com.nexters.ssss.db.dao.DAO_USER_Impl;
import com.nexters.ssss.db.dto.DTO_RECORD;
import com.nexters.ssss.db.dto.DTO_USER;
import com.nexters.ssss.util.FileUtil;
import com.nexters.ssss.util.serviceIf;
import com.nexters.ssss.util.sessionUtil;

/**
 * 로그아웃
 * @author limjuhyun
 *
 */
@Repository
public class UD0001 implements serviceIf {

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
			//파일을 바이너리로 저장 뒤, 저장된 파일 경로를 받는다.
			String strFilePath = fu.base64StringToFile(strBase64, conf.FILE_TEMP_PATH, "zip");
			
			//압축파일을 BRD_VOICE_PATH 변수로 설정된 곳에 압축해제를 한다.
			
			ArrayList listFiles = fu.unZip(strFilePath, conf.BRD_VOICE_PATH);
			
			////////////////////////////서버에 등록
			
			//2개는 client로 부터온다. 
			record.setFile_path((String)listFiles.get(0));
			record.setIs_file_yn((String) reqData.get("is_file_yn"));
			//session에서 부터 usr_no가져오기
			/// 2개뺴고 나머지 4개는 sql문에서 처리한다. -> uuid를 보내는 이유는 tn_user에서 uuid를 통해 usr_no를 찾아야함 
			record_dao.add_record( record ,(String) sessionutil.getSession("usr_uuid"));
			//압축 해제된 파일 리스트
			System.out.println(listFiles.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return rsltMap;
	}
}
