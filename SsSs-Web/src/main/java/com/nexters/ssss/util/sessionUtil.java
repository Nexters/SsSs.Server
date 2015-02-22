package com.nexters.ssss.util;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nexters.ssss.conf.conf;
import com.nexters.ssss.db.dto.DTO_USER;

/**
 * 세션 관리
 * @author limjuhyun
 *
 */
public class sessionUtil {
	private static final Logger logger = LoggerFactory.getLogger(sessionUtil.class);
	private static final String USER_DATA_FILED = "usr_data";
	private HttpSession nSession;
	
	/**
	 * 초기 세션 정보 저장
	 * @param session
	 */
	public sessionUtil(HttpSession session) {
		nSession = session;
		if(conf.isDebug) {
			printSessionInform();
		}
	}
	
	/**
	 * 사용자가 로그인하였는지 확인
	 * @throws RuntimeException
	 */
	public void isUserLogin() throws RuntimeException  {
		if(nSession.getAttribute(USER_DATA_FILED)==null) {
			throw new RuntimeException("로그인이 필요합니다.");
		}
	}
	
	/**
	 * 세션 설정 (session.setAttribute)
	 * @param key
	 * @param value
	 */
	public void setSession(String key, Object value){
		nSession.setAttribute(key, value);
	}
	
	/**
	 * 세션 제거 (session.removeAttribute)
	 * @param key
	 */
	public void removeSession(String key){
		nSession.removeAttribute(key);
	}
	
	/**
	 * 세션 정보 가져오기 (session.getAttribute)
	 * @param key
	 * @return
	 */
	public Object getSession(String key){
		return nSession.getAttribute(key);
	}
	
	/**
	 * 사용자 정보 설정
	 * @param dto
	 */
	public void setUserInform(DTO_USER dto) {
		nSession.setAttribute(USER_DATA_FILED, dto);
	}
	
	/**
	 * 사용자 정보 가져오기
	 * @return DTO_USER
	 */
	public DTO_USER getUserInform() {
		DTO_USER dtoUser = (DTO_USER) nSession.getAttribute(USER_DATA_FILED);
		if(conf.isDebug) {
			printUserInform(dtoUser);
			printSessionInform();
		}
		return dtoUser;
	}
	
	/**
	 * 사용자 정보 삭제
	 */
	public void removeUserInform() {
		nSession.removeAttribute(USER_DATA_FILED);
	}
	
	/**
	 * 세션 파괴
	 */
	public void removeSession() {
		nSession.invalidate();
	}
	
	/**
	 * 유저 정보 출력
	 * @param dto
	 */
	public void printUserInform(DTO_USER dto) {
		logger.debug("로그인 사용자 정보 ############################################");
		logger.debug("getUsr_no::"+dto.getUsr_no());
		logger.debug("getUsr_nn::"+dto.getUsr_nn());
		logger.debug("getUsr_pushid::"+dto.getUsr_pushid());
		logger.debug("getUsr_uuid::"+dto.getUsr_uuid());
		logger.debug("getAlarmyn::"+dto.getAlarmyn());
	}
	
	/**
	 * 세션 정보 출력
	 */
	public void printSessionInform() {
		logger.debug("세션 정보 ############################################");
		logger.debug("getCreationTime::"+nSession.getCreationTime());
		logger.debug("isNew::"+nSession.isNew());
		logger.debug("getLastAccessedTime::"+nSession.getLastAccessedTime());
		logger.debug("getMaxInactiveInterval::"+nSession.getMaxInactiveInterval());
	}

}
