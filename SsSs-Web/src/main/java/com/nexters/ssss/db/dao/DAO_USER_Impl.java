package com.nexters.ssss.db.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.nexters.ssss.db.dto.DTO_USER;

public class DAO_USER_Impl implements DAO_USER {
	
	private SqlSession sqlsession;

	public DAO_USER_Impl(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	
	@Override
	public List<DTO_USER> selectUserList() {
		
		List UserList = null;
		
		try {
			System.out.println("sqlsession::"+sqlsession);
			UserList = sqlsession.selectList("User.getUserList");
		} catch (Exception e) { e.printStackTrace(); }
		
		return UserList;
	}
}
