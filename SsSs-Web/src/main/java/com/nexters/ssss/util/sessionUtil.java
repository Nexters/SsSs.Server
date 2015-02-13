package com.nexters.ssss.util;

import javax.servlet.http.HttpSession;

import com.nexters.ssss.db.dto.DTO_USER;

public class sessionUtil {
	private static final String USER_ID_FIELD = "usr_uuid";
	private static final String USER_NO_FIELD = "usr_no";
	private HttpSession nSession;
	
	public void setUserInform(DTO_USER dto) {
		nSession.setAttribute("USER", dto);
	}
	
	public DTO_USER getUserInform(){
		return (DTO_USER) nSession.getAttribute("USER");
	}
	public sessionUtil(HttpSession session) {
		nSession = session;
	}
	
	public void isUserLogin() throws RuntimeException  {
		if(nSession.getAttribute(USER_ID_FIELD)==null) {
			throw new RuntimeException("로그인이 필요합니다.");
		}
	}
	public void setUseNo(String usr_no){
		nSession.setAttribute(USER_NO_FIELD, usr_no);
	}
	public void setUsrId(String usrId){
		nSession.setAttribute(USER_ID_FIELD, usrId);
	}
	
	public void removeUsrId(){
		nSession.removeAttribute(USER_ID_FIELD);
	}
	
	public void setSession(String key, Object value){
		nSession.setAttribute(key, value);
	}
	
	public void removeSession(String key){
		nSession.removeAttribute(key);
	}
	
	public Object getSession(String key){
		return nSession.getAttribute(key);
	}

}
