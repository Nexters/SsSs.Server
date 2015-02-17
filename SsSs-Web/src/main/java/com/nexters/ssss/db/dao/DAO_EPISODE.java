package com.nexters.ssss.db.dao;

import com.nexters.ssss.db.dto.DTO_EPISODE;

public interface DAO_EPISODE {
	public String get_brcast_time(String ep_no);
	public boolean check_episode(String date,String time);
	public DTO_EPISODE get_episode_info(String date,String time);
	public String get_last_episode(String ep_no);
	public DTO_EPISODE get_episode_info(String ep_no);
}
