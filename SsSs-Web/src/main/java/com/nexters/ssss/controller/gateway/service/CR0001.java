package com.nexters.ssss.controller.gateway.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONObject;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexters.ssss.db.dao.DAO_CHAT_Impl;
import com.nexters.ssss.db.dto.DTO_CHAT;
import com.nexters.ssss.util.serviceIf;
import com.nexters.ssss.util.sessionUtil;

/**
 * 로그아웃
 * @author limjuhyun
 *
 */
@Service
public class CR0001 implements serviceIf {

	private SqlSession sqlsession;
	private static final boolean isNeedLogin = true;
	private sessionUtil sessionutil;

	@Override
	public Map<String, Object> doFirst(HttpSession session, SqlSession sqlsession_gw, JSONObject reqData) {
		sessionutil = new sessionUtil(session);
		sqlsession = sqlsession_gw;
		
		//System.out.println(sqlSession);
		if(isNeedLogin){
			sessionutil.isUserLogin();
		}
		return execute(reqData);
	}

	@Override
	public Map<String, Object> execute(JSONObject reqData) {
		// TODO Auto-generated method stub
		Map<String, Object> rsltMap = new HashMap<String, Object>();
	
		DAO_CHAT_Impl chat_dao= new DAO_CHAT_Impl(sqlsession);
		DTO_CHAT chat = new DTO_CHAT();
		
		//System.out.println(sqlSession);
		
		//rsltMap.put("_rslt", ((DTO_USER) sqlSession.selectList("User.getUserList").get(0)).getUsr_nn());
	
		///////////////logic
		try{
			chat.setCont((String) reqData.get("cont"));
			chat.setUsr_no((String) sessionutil.getSession("usr_no"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		chat_dao.add_chat(chat);
		///////////////logic
		
		return rsltMap;
	}
}
