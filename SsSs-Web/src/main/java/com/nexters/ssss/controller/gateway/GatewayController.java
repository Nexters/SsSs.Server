package com.nexters.ssss.controller.gateway;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netxters.sss.util.message.errorMsg;

/**
 * Gatewawy 컨트롤러
 * @author limjuhyun
 *
 */
@Controller
public class GatewayController {
	private static final Logger logger = LoggerFactory.getLogger(GatewayController.class);
	
	@Autowired
	private SqlSession sqlSession;

	@RequestMapping(value="/gateway", produces="application/json; charset=utf8")
	public @ResponseBody String GatewayController(@RequestParam Map<String, Object> paramMap, ModelMap modelMap) {
		String strJsonData = (String)paramMap.get("JSONData");
		String strTransCd = "";
		Map<String, Object> mapRslt = new HashMap<String, Object>();
		Map<String, Object> mapFinalRslt = new HashMap<String, Object>();
		
		try {
			if(strJsonData==null || "".equals(strJsonData)) {
				throw new Exception("요청 데이터가 없습니다.");
			} else {
				
			}
		} catch (Exception e) {
			strTransCd = "ERROR";
			mapRslt = errorMsg.makeErrorMsg(e.hashCode(), e.getMessage(), "9999");
		} finally  {
			ArrayList<Object> listFinalData = new ArrayList<Object>();
			listFinalData.add(mapRslt);
			
			mapFinalRslt.put("_trans_cd", strTransCd);
			mapFinalRslt.put("_trans_res_data", listFinalData);
		}

		return JSONValue.toJSONString(mapFinalRslt);
	}

}
