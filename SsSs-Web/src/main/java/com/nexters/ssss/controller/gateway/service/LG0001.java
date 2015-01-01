package com.nexters.ssss.controller.gateway.service;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import com.nexters.ssss.util.serviceIf;

public class LG0001 implements serviceIf {

	@Override
	public Map<String, Object> execute(JSONObject reqData) {
		// TODO Auto-generated method stub
		Map<String, Object> rsltMap = new HashMap<String, Object>();
		rsltMap.put("loginYN", false);
		
		
		return rsltMap;
	}

}
