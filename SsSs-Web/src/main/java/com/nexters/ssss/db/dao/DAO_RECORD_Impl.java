package com.nexters.ssss.db.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nexters.ssss.db.dto.DTO_EPISODE;
import com.nexters.ssss.db.dto.DTO_RECORD;
import com.nexters.ssss.db.dto.DTO_USER;

@Component
public class DAO_RECORD_Impl implements DAO_RECORD{
	
	private SqlSession sqlsession;
	
	@Autowired
	private DTO_RECORD dtorecord; //= new DTO_RECORD();

	
	public DAO_RECORD_Impl(){
		
	}
	public DAO_RECORD_Impl(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	@Override
	public String rec_cnt(DTO_EPISODE first,DTO_EPISODE last){
		HashMap<String,Object> hashmap=new HashMap<String,Object>();
		hashmap.put("first_brcast_date", first.getBrcast_date());
		hashmap.put("first_brcast_time", first.getBrcast_time());
		hashmap.put("last_brcast_date", last.getBrcast_date());
		hashmap.put("last_brcast_time", last.getBrcast_time());
		
		return (String) sqlsession.selectOne("Record.rec_cnt",hashmap);
	}
	
	@Override
	public void add_record(DTO_RECORD record){
		sqlsession.insert("Record.add_record", record);
	}
	
}
