package com.nexters.ssss.controller.gateway.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
public class LG0002 implements serviceIf {

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

		String strBase64 = "data encoded base64";
		FileUtil fu = new FileUtil();
		try {
			//파일을 바이너리로 저장 뒤, 저장된 파일 경로를 받는다.
			String strFilePath = fu.base64StringToFile(strBase64, "/Users/juhyun/Documents/", "png");
			
			//압축파일을 /home/was/files/에 압축해제를 한다.
			ArrayList listFiles = fu.unZip(strFilePath, "/home/was/files/");
			
			//압축 해제된 파일 리스트
			System.out.println(listFiles.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return rsltMap;
	}
}
