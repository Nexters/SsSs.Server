package com.nexters.ssss.db.dao;

import com.nexters.ssss.db.dto.DTO_EPISODE;
import com.nexters.ssss.db.dto.DTO_RECORD;

public interface DAO_RECORD {
	public void add_record(DTO_RECORD record);
	public String rec_cnt(DTO_EPISODE first,DTO_EPISODE last);
}
