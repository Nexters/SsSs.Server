package com.nexters.ssss.controller.gateway.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nexters.ssss.controller.gateway.GatewayController;
import com.nexters.ssss.db.dao.DAO_EPISODE_Impl;
import com.nexters.ssss.db.dao.DAO_RECORD_Impl;
import com.nexters.ssss.db.dao.DAO_USER_Impl;
import com.nexters.ssss.db.dto.DTO_EPISODE;
import com.nexters.ssss.db.dto.DTO_USER;
import com.nexters.ssss.util.Time;
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
			
			/////////////////////////////////////////////////////////////////
			SimpleDateFormat format_date = new SimpleDateFormat("yyyyMMdd");//날짜
			SimpleDateFormat format_time = new SimpleDateFormat("HHmmss");//시간
			Date d_d= new Date();
			Date d_t= new Date();
			String date=format_date.format(d_d);//date hhmm이 저장 
			String time=format_time.format(d_t);
			DAO_EPISODE_Impl episode= new DAO_EPISODE_Impl(sqlsession);
			DAO_RECORD_Impl record= new DAO_RECORD_Impl(sqlsession);
			
			rsltMap.put("date", date);
			rsltMap.put("time", time);
			//////////////////제일 처음 일단 episode가 등록되어있는지 확인한다.
			if(episode.check_episode(date, time)){
				/**
				 * 등록되어있다.
				 */
				////////////////////////////
				//String remain_time;
				DTO_EPISODE dto_episode=episode.get_episode_info(date, time);//현재 올라온 episode 구하기
				rsltMap.put("episode_no", dto_episode.getEp_no());
				//String get_time=episode.get_brcast_time(ep_no);//위에서 구한 episode의 시간 구하기
				Time md_time = new Time(time,dto_episode.getBrcast_time());
				
				rsltMap.put("remain_hour", md_time.get_hour());
				rsltMap.put("remain_min", md_time.get_min());
				rsltMap.put("remain_second", md_time.get_second());
				////////////////시간 등록완료 일단은 분할해놧는데 합쳐서 hhmmss꼴로 보낼껀지 합의해야함
				
				///////////////////사연 갯수 뿌리기
				String get_last_ep_no=episode.get_last_episode(dto_episode.getEp_no());
				DTO_EPISODE last_dto_episode=episode.get_episode_info(get_last_ep_no);//전 에피소드 시작시간
				rsltMap.put("last_episode", last_dto_episode.getEp_no());
				String res_cnt=record.rec_cnt(dto_episode, last_dto_episode);
				rsltMap.put("res_cnt", res_cnt);
				//String rec_cnt=record.rec_cnt(date, time);// 이미 에피소드가 등록되었다는 얘기니까 
				
				
				////////////////////////////
				
				rsltMap.put("is_next_brdcast", "Y");
			}else{
				/**
				 * 등록 안되어있다.
				 */
				rsltMap.put("is_next_brdcast", "N");//n일경우 방송 준비중입니다 등을 띄워야함
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
