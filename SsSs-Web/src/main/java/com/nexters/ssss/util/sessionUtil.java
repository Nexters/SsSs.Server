package com.nexters.ssss.util;

import javax.servlet.http.HttpSession;

import com.nexters.ssss.db.dto.DTO_USER;

public class sessionUtil {
	private static final String USER_DATA_FILED = "usr_data";
	private HttpSession nSession;
	
	/**
	 * 초기 세션 정보 저장
	 * @param session
	 */
	public sessionUtil(HttpSession session) {
		nSession = session;
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
		return (DTO_USER) nSession.getAttribute(USER_DATA_FILED);
	}
	
	/**
	 * 사용자 정보 삭제
	 */
	public void removeUserInform() {
		nSession.removeAttribute(USER_DATA_FILED);
	}

}
