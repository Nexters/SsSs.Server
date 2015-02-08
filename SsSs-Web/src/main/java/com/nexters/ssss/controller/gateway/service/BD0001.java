package com.nexters.ssss.controller.gateway.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONObject;

import com.nexters.ssss.util.serviceIf;
import com.nexters.ssss.util.sessionUtil;

public class BD0001 implements serviceIf {

	private static final boolean isNeedLogin = true;
	private sessionUtil sessionutil;

	@Override
	public Map<String, Object> doFirst(HttpSession session, SqlSession sqlsession, JSONObject reqData) {
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
		rsltMap.put("_usr_id", (String)sessionutil.getSession("_usr_id"));
		
		
		
		return rsltMap;
	}
}
