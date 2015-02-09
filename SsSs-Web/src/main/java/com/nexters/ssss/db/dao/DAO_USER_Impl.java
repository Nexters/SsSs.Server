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
	
	@Override
	public boolean check_user_uuid(String uuid){
		
		return false;
	}
	@Override
	public void add_usr(DTO_USER user){
		
	}
	@Override
	public void assign_nickname(){
		
	}
	@Override
	public int count_of_story(){
		
		return 0;
	}
	@Override
	public String get_air_time(){
		
		return null;
	}
}
