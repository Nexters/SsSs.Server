package com.nexters.ssss.controller.gateway.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.nexters.ssss.db.dao.DAO_CHAT_Impl;
import com.nexters.ssss.db.dto.DTO_CHAT;
import com.nexters.ssss.db.dto.DTO_USER;
import com.nexters.ssss.util.serviceIf;
import com.nexters.ssss.util.sessionUtil;

/**
 * 로그아웃
 * @author limjuhyun
 *
 */
@Service
public class CR0002 implements serviceIf {

	
	
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
		Map<String, Object> buffer = new HashMap<String, Object>();
		Map<String, Object> rsltMap = new HashMap<String, Object>();
		List<DTO_CHAT> listRsltData = null;
		ArrayList<HashMap> listFinalRsltData = new ArrayList<HashMap>();
		//sessionutil.removeUsrId();
		
		List<DTO_CHAT> chat_list = new ArrayList<DTO_CHAT>();
		DAO_CHAT_Impl chat_dao= new DAO_CHAT_Impl(sqlsession);
		listRsltData=chat_dao.get_chat_list((String)reqData.get("type"),(String)reqData.get("chat_no"),(String)reqData.get("cnt"));
		for(DTO_CHAT dto : listRsltData) {
			HashMap<String, Object> mapListData = new HashMap<String, Object>();
			mapListData.put("chat_no", dto.getChat_no());
			listFinalRsltData.add(mapListData);
		}
		rsltMap.put("_rslt", listFinalRsltData);
		/**
		 * 
		 */
		DTO_USER user = sessionutil.getUserInform();
		user.getUsr_nn();
		user.getUsr_pushid();
		/**
		 * 
		 */
		return rsltMap;
	}
}

