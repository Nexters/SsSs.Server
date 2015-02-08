package com.nexters.ssss.controller.gateway.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONObject;

import com.nexters.ssss.db.dao.DAO_USER_Impl;
import com.nexters.ssss.util.sessionUtil;
import com.nexters.ssss.util.serviceIf;

/**
 * 로그인
 * @author limjuhyun
 *
 */
public class LG0001 implements serviceIf {

	private static final boolean isNeedLogin = false;
	private sessionUtil sessionutil;
	private SqlSession sqlsession;
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
		sessionutil.setUsrId("test");
		try {
			DAO_USER_Impl dui = new DAO_USER_Impl(sqlsession);
			rsltMap.put("_rslt", dui.selectUserList());
		} catch(Exception e) { e.printStackTrace(); }
		
		return rsltMap;
	}
}
