package com.netxters.sss.util.message;

import java.util.HashMap;
import java.util.Map;

public class errorMsg {
	public static Map<String, Object> makeErrorMsg(String errcd, String errmsg, String actionCd) {
		Map<String, Object> mapError = new HashMap<String, Object>();
		
		mapError.put("_error_cd", errcd);
		mapError.put("_error_msg", errmsg);
		mapError.put("_action_cd", actionCd);
		
		return mapError;
	}
	
	public static Map<String, Object> makeErrorMsg(int errcd, String errmsg, String actionCd) {
		return makeErrorMsg(Integer.toString(errcd), errmsg, actionCd);
	}
	
}
