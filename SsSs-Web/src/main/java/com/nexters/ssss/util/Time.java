package com.nexters.ssss.util;

public class Time {
	private int min;
	private int hour;
	private int second;
	private int remain_time;
	public int get_min(){
		return min;
	}
	public int get_hour(){
		return hour;
	}
	public int get_second(){
		return second;
	}
	public Time(){
		
	}
	public Time(String time, String get_time){
		
		
		int client_time=Integer.parseInt(time);
		int episode_time=Integer.parseInt(get_time);
		
		int client_hour=Integer.parseInt((String) time.subSequence(0, 2));
		int episode_hour=Integer.parseInt((String) get_time.substring(0, 2));
		
		int client_min=Integer.parseInt((String) time.subSequence(2, 4));
		int episode_min=Integer.parseInt((String) get_time.subSequence(2, 4));
		
		int client_second=Integer.parseInt((String) time.subSequence(4, 6));
		int episode_second=Integer.parseInt((String) get_time.subSequence(4, 6));
		
		if(client_second>episode_second){//클라가 더 크면 hour에서 1빼주고 60 더해줌
			second=(episode_second+60)-client_second;
			episode_min=episode_min-1;
		}else{
			second=episode_second-client_second;
		}
		
		if(client_min>episode_min){
			min=(episode_min+60)-client_min;
			episode_hour=episode_hour-1;
		}else{
			min=(episode_min)-client_min;
		}
		
		hour=episode_hour-client_hour;
		
	}
}
