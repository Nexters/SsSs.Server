package com.nexters.ssss.util;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JSON 파싱
 * @author limjuhyun
 *
 */
public class JsonUtil {
	private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
	private String strSvcCd = null;
	
	/**
	 * JSON 데이터를 파싱 후 서비스 코드를 가져온다.
	 * @param strJsonData JSON 데이터
	 * @return 파싱된 JSONObject
	 */
	public JSONObject parseJSON(String strJsonData) throws RuntimeException {
		JSONObject joPrsingData = null;

		joPrsingData = (JSONObject) JSONValue.parse(strJsonData);

		if(joPrsingData==null) throw new RuntimeException("올바른 JSON 데이터 형식이 아닙니다.");
		if(joPrsingData.containsKey("_req_svc")==false) throw new RuntimeException("서비스 코드가 없습니다.");
		if(joPrsingData.containsKey("_req_data")==false) throw new RuntimeException("요청 데이터가 없습니다.");
		
		strSvcCd = (String) joPrsingData.get("_req_svc");


		return (JSONObject)(((JSONArray) joPrsingData.get("_req_data")).get(0));
	}
	
	/**
	 * 서비스 코드를 가져온다.
	 * @return 서비스 코드
	 */
	public String getSvcCd(){
		return strSvcCd;
	}
}
