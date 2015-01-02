package com.nexters.ssss.util;

import java.util.Map;

import org.json.simple.JSONObject;
import javax.servlet.http.HttpSession;

/**
 * 서비스 인터페이스
 * @author limjuhyun
 *
 */
public interface serviceIf {
	
	/**
	 * 서비스 처음 실행 시 호출
	 * @param session 세션
	 * @param reqData 요청 데이터
	 * @return
	 */
	
	Map<String, Object> doFirst(HttpSession session, JSONObject reqData);
	/**
	 * 비즈니스 로직 부분
	 * @param reqData 요청 데이터
	 * @return
	 */
	Map<String, Object> execute(JSONObject reqData);
	
}
