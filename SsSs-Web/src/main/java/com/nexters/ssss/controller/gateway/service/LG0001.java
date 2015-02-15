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
 * 로그인
 * @author limjuhyun
 *
 */
@Service
public class LG0001 implements serviceIf {
	private static final Logger logger = LoggerFactory.getLogger(LG0001.class);

	
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
		logger.debug("reqData >> " + reqData.toString());//삭제예정
		return execute(reqData);
	}

	@Override
	public Map<String, Object> execute(JSONObject reqData) {
		// TODO Auto-generated method stub
		Map<String, Object> rsltMap = new HashMap<String, Object>();
		
		DAO_USER_Impl dui = new DAO_USER_Impl(sqlsession);
		DTO_USER user= new DTO_USER();
		
		try {
			String strUsrUuid = (String) reqData.get("usr_uuid");
			
			logger.debug("usr_uuid is true:: "+dui.check_user_uuid((String) reqData.get("usr_uuid")));
			if(dui.check_user_uuid(strUsrUuid)){//존재한다
				user.setUsr_uuid(strUsrUuid);
				sessionutil.setUserInform(dui.get_user_info(user));
				
			}else{//존재안함
				
				user.setUsr_uuid((String) reqData.get("usr_uuid"));
				user.setUsr_pushid((String) reqData.get("usr_pushid"));
				user.setAlarmyn((String) reqData.get("alarmyn"));
				user.setUsr_nn("주현짱");				
				dui.add_usr(user); //db에 등록
				
				sessionutil.setUserInform(dui.get_user_info(user));

			}
			
			DTO_USER loginUserData = sessionutil.getUserInform();
			
			rsltMap.put("_login_yn", "Y");
			rsltMap.put("_alarm_yn", loginUserData.getAlarmyn());
			
		} catch(Exception e)
		{ 
			e.printStackTrace();
		}
		
		return rsltMap;
	}
}
