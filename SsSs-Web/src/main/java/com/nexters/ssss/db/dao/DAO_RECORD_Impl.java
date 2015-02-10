package com.nexters.ssss.db.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.nexters.ssss.db.dto.DTO_RECORD;
import com.nexters.ssss.db.dto.DTO_USER;

public class DAO_RECORD_Impl implements DAO_RECORD{
	
	private SqlSession sqlsession;
	private DTO_RECORD dtorecord = new DTO_RECORD();

	public DAO_RECORD_Impl(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	
	@Override
	public void add_record(DTO_RECORD record,String uuid){
		
		HashMap<String,Object> hashmap = new HashMap<String,Object>();
		hashmap.put("record", record);
		hashmap.put("usr_uuid", uuid);
		sqlsession.selectOne("Record.add_record", hashmap);
	}
	
}
