package com.nexters.ssss.db.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	public void add_record(DTO_RECORD record){
		//이부분 수정해야됨// 작동안됨
		sqlsession.insert("Record.add_record", record);
	}
	
}
