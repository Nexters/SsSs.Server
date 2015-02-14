package com.nexters.ssss.controller.gateway;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nexters.ssss.util.message.errorMsg;
import com.nexters.ssss.util.JsonUtil;
import com.nexters.ssss.controller.gateway.service.*;

/**
 * Gatewawy 컨트롤러
 * @author limjuhyun
 *
 */
@Controller
public class GatewayController {
	private static final Logger logger = LoggerFactory.getLogger(GatewayController.class);
	
	@Autowired
	private SqlSession sqlsession; 
	
	private Object svcClass = null;
	
	@RequestMapping(value="/gateway", produces="application/json; charset=utf8")
	public @ResponseBody String GatewayController(@RequestParam Map<String, Object> paramMap, HttpSession session) {
		String strJsonData = (String)paramMap.get("JSONData");
		String strTransCd = "";
		
		Map<String, Object> mapRslt = new HashMap<String, Object>();
		Map<String, Object> mapFinalRslt = new HashMap<String, Object>();
		
		JsonUtil joUtil = new JsonUtil();
		
		
		logger.debug("strJsonData::"+strJsonData);
		
		try {
			JSONObject joParseData = null; // 파싱된 JSON 데이터를 저장
			
			//JSONData 파라미터가 있는지 확인, 있으면 JSON 파싱을 시작한다.
			if(strJsonData==null || "".equals(strJsonData)) {
				throw new RuntimeException("요청 데이터가 없습니다.");
			} else {
				try {
					joParseData = joUtil.parseJSON(strJsonData);
					strTransCd = joUtil.getSvcCd();
				} catch (Exception e) {
					throw e;
				}

			}
			
			//요청이 들어온 데이터를 로그에 남겨 둔다.
			//logger.debug("strReqSvc::"+strTransCd);
			//logger.debug("joParseData::"+joParseData.toJSONString());
						
			// 로그인을 한다
			if("LG0001".equals(strTransCd))  {
				svcClass = new LG0001();
				mapRslt = ((LG0001)svcClass).doFirst(session, sqlsession, joParseData);
			} 
			
			// 로그아웃을 한다
			else if("UD0001".equals(strTransCd))  {
				svcClass = new UD0001();
				mapRslt = ((UD0001)svcClass).doFirst(session, sqlsession, joParseData);
			} 
			
			// 현재 로그인된 사용자를 가져와서 출력한다
			else if("CR0001".equals(strTransCd))  {
				svcClass = new CR0001();
				mapRslt = ((CR0001)svcClass).doFirst(session, sqlsession, joParseData);
			}else if("CR0002".equals(strTransCd))  {
				svcClass = new CR0002();
				mapRslt = ((CR0002)svcClass).doFirst(session, sqlsession, joParseData);
			} 
			
			// 서비스가 없을 경우에는.. 오류를 
			else {
				throw new RuntimeException("존재하는 서비스가 없습니다.");
			}
			
			
		} 
		
		// RuntimeException의 경우 의도적인 오류이기 때문에 정확한 오류 내용을 출력하지 않는다.
		catch (RuntimeException e) {
			strTransCd = "ERROR";
			mapRslt = errorMsg.makeErrorMsg(e.hashCode(), e.getMessage(), "9999");
			logger.debug("hashCode::"+e.hashCode()+", Message::"+e.getMessage());
		} catch (Exception e) {
			strTransCd = "ERROR";
			mapRslt = errorMsg.makeErrorMsg(e.hashCode(), e.getMessage(), "9999");
			logger.error("GatewayError", e);
		} finally  {
			ArrayList<Object> listFinalData = new ArrayList<Object>();
			listFinalData.add(mapRslt);
			
			mapFinalRslt.put("_res_svc", strTransCd);
			mapFinalRslt.put("_res_data", listFinalData);
		}

		return JSONValue.toJSONString(mapFinalRslt);
	}

}
