package com.nexters.ssss.util.message;

import java.util.HashMap;
import java.util.Map;

public class errorMsg {
	
	/**
	 * 오류 메시지를 JSON으로 만든다
	 * @param errcd 오류 코드
	 * @param errmsg 오류 메시지
	 * @param actionCd 액션 코드
	 * @return
	 */
	public static Map<String, Object> makeErrorMsg(String errcd, String errmsg, String actionCd) {
		Map<String, Object> mapError = new HashMap<String, Object>();
		
		mapError.put("_error_cd", errcd);
		mapError.put("_error_msg", errmsg);
		mapError.put("_action_cd", actionCd);
		
		return mapError;
	}
	
	/**
	 * 오류 메시지를 JSON으로 만든다
	 * @param errcd 오류 코드
	 * @param errmsg 오류 메시지
	 * @param actionCd 액션 코드
	 * @return
	 */
	public static Map<String, Object> makeErrorMsg(int errcd, String errmsg, String actionCd) {
		return makeErrorMsg(Integer.toString(errcd), errmsg, actionCd);
	}
	
}
