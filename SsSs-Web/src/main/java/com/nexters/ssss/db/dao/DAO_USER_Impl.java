package com.nexters.ssss.db.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nexters.ssss.db.dto.DTO_USER;

@Component
public class DAO_USER_Impl implements DAO_USER {
	
	
	private SqlSession sqlsession;
	private DTO_USER dtouser = new DTO_USER();
	public DAO_USER_Impl(){
		
	}
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
		dtouser.setUsr_uuid(uuid);
		int number= Integer.parseInt((String) sqlsession.selectOne("User.check_user_uuid", dtouser));
		if(number==0) {
			return false;
		} else {
			return true;
		}
	}
	@Override
	public String get_usr_no(String uuid){
		
		return (String) sqlsession.selectOne("User.get_usr_no",uuid);
	}
	//완료
	@Override
	public void add_usr(DTO_USER user){
		//dtouser(user);
		sqlsession.selectList("User.add_usr", user);
	}
	

	@Override
	public void assign_nickname(DTO_USER user){
		
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
