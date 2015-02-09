package com.nexters.ssss.db.dao;

import java.util.List;
import com.nexters.ssss.db.dto.DTO_USER;

public interface DAO_USER {
	public List<DTO_USER> selectUserList();
	public boolean check_user_uuid(String uuid);
	public void add_usr(DTO_USER user);
	public void assign_nickname(DTO_USER user);
	public int count_of_story();
	public String get_air_time();
}