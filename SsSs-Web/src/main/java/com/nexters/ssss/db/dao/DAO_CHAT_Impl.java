package com.nexters.ssss.db.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.nexters.ssss.db.dto.DTO_CHAT;

@Component
public class DAO_CHAT_Impl implements DAO_CHAT {
	//@Autowired
	private SqlSession sqlsession;
	private ArrayList<DTO_CHAT> temp_chat_list = new ArrayList<DTO_CHAT>();
	public DAO_CHAT_Impl(){
		
	}
	public DAO_CHAT_Impl(SqlSession sqlsession){
		this.sqlsession= sqlsession;
	}
	@Override
	public List get_chat_list(String type,String chat_no, String cnt){
		HashMap<String,Object> hashmap = new HashMap<String,Object>();
		hashmap.put("type", type);
		hashmap.put("chat_no", chat_no);
		hashmap.put("cnt", cnt);
		
		return sqlsession.selectList("Chat.get_chat_list", hashmap);
	}
	@Override
	public void add_chat(DTO_CHAT chat) {
		System.out.println(sqlsession);
		sqlsession.insert("Chat.add_chat", chat);
	}

}
