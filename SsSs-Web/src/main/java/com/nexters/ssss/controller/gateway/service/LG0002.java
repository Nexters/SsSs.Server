package com.nexters.ssss.controller.gateway.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nexters.ssss.controller.gateway.GatewayController;
import com.nexters.ssss.db.dao.DAO_USER_Impl;
import com.nexters.ssss.db.dto.DTO_USER;
import com.nexters.ssss.util.sessionUtil;
import com.nexters.ssss.util.serviceIf;

/**
 * 로그아
 * @author limjuhyun
 *
 */
@Service
public class LG0002 implements serviceIf {
	private static final Logger logger = LoggerFactory.getLogger(LG0002.class);

	
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
		
		try {
			sessionutil.removeUserInform();
			rsltMap.put("_logout_yn", "Y");
			
		} catch(Exception e)
		{ 
			e.printStackTrace();
		}
		
		return rsltMap;
	}
}
