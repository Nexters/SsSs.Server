package com.nexters.ssss;

import java.util.Map;

import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Gatewawy 컨트롤러
 * @author limjuhyun
 *
 */
@Controller
public class GatewayController {
	private static final Logger logger = LoggerFactory.getLogger(GatewayController.class);

	@RequestMapping("/gateway")
	public @ResponseBody String GatewayController(@RequestParam Map<String, Object> paramMap, ModelMap modelMap) {
		logger.info("Parameter Values : "+ paramMap.toString());

		
		
		return JSONValue.toJSONString(paramMap);
	}

}
