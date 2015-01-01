package com.nexters.ssss.util;

import java.util.Map;

import org.json.simple.JSONObject;

public interface serviceIf {
	Map<String, Object> execute(JSONObject reqData);
	
}
