package com.nexters.ssss.controller.gateway.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nexters.ssss.db.dto.DTO_USER;
import com.nexters.ssss.util.serviceIf;
import com.nexters.ssss.util.sessionUtil;

/**
 * 로그아웃
 * @author limjuhyun
 *
 */
@Repository
public class BD0001 implements serviceIf {

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
		sessionutil.removeUsrId();
		System.out.println(sqlsession);
		rsltMap.put("_rslt", ((DTO_USER) sqlsession.selectList("User.getUserList").get(0)).getUsr_nn());
		
		return rsltMap;
	}
}
