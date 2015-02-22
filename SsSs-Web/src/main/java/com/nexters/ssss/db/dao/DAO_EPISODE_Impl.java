package com.nexters.ssss.db.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import com.nexters.ssss.db.dto.DTO_EPISODE;

@Component
public class DAO_EPISODE_Impl implements DAO_EPISODE{

	private SqlSession sqlsession;
	
	public DAO_EPISODE_Impl(){
		
	}
	public DAO_EPISODE_Impl(SqlSession sqlsession){
		this.sqlsession=sqlsession;
	}
	@Override
	public String get_last_episode(String ep_no){
		HashMap<String,Object> hashmap = new HashMap<String,Object>();
		int ep_no_=Integer.parseInt(ep_no);
	
		return sqlsession.selectOne("Episode.get_last_episode", ep_no_);
	}
	@Override
	public String get_brcast_time(String ep_no){
		HashMap<String,Object> hashmap = new HashMap<String,Object>();
		int int_ep_no=Integer.parseInt(ep_no);
		hashmap.put("ep_no", int_ep_no);
		String ep_time=((String) sqlsession.selectOne("Episode.get_brcast_time",hashmap));
		return ep_time;
	}
	@Override
	public boolean check_episode(String date, String time) {
		HashMap<String,Object> hashmap = new HashMap<String,Object>();
		hashmap.put("date", date);
		hashmap.put("time", time);
		
		int number= Integer.parseInt ((String) sqlsession.selectOne("Episode.check_episode", hashmap));
		
		if(number==0) {
			return false;
		} else {
			return true;
		}
	}
	@Override
	public DTO_EPISODE get_episode_info(String ep_no){
		
		HashMap<String,Object> hashmap = new HashMap<String,Object>();
		int int_ep_no=Integer.parseInt(ep_no);
		hashmap.put("ep_no", int_ep_no);
		return (DTO_EPISODE) sqlsession.selectOne("Episode.get_episode_info_ep_no", hashmap);
	}
	@Override
	public DTO_EPISODE get_episode_info(String date, String time) {
		// TODO Auto-generated method stub
		HashMap<String,Object> hashmap = new HashMap<String,Object>();
		hashmap.put("date", date);
		hashmap.put("time", time);
		
		return (DTO_EPISODE) sqlsession.selectOne("Episode.get_episode_info", hashmap);
	}
}
