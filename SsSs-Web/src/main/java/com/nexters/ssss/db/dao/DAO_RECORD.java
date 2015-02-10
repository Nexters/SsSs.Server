package com.nexters.ssss.db.dao;

import java.util.List;

import com.nexters.ssss.db.dto.DTO_RECORD;
import com.nexters.ssss.db.dto.DTO_USER;

public interface DAO_RECORD {
	public void add_record(DTO_RECORD record,String uuid);
}
