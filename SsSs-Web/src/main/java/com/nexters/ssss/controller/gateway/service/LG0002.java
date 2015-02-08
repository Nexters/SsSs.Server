package com.nexters.ssss.controller.gateway.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.nexters.ssss.util.serviceIf;
import com.nexters.ssss.util.sessionUtil;

/**
 * 로그아웃
 * @author limjuhyun
 *
 */
public class LG0002 implements serviceIf {

	private SqlSession sqlsession;
	
	private static final boolean isNeedLogin = false;
	private sessionUtil sessionutil;

	@Override
	public Map<String, Object> doFirst(HttpSession session, SqlSession sqlsession_gw, JSONObject reqData) {
		sessionutil = new sessionUtil(session);
		sqlsession = sqlsession_gw;
		if(isNeedLogin){
			sessionutil.isUserLogin();
		}
		
		return execute(reqData);
	}

	@Override
	public Map<String, Object> execute(JSONObject reqData) {
		// TODO Auto-generated method stub
		Map<String, Object> rsltMap = new HashMap<String, Object>();
		sessionutil.removeUsrId();
		System.out.println(sqlsession);
		rsltMap.put("_rslt", sqlsession.selectList("User.getUserList"));
		
		
		
		return rsltMap;
	}
}
