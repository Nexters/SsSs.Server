package com.nexters.ssss.db.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.nexters.ssss.db.dto.DTO_CHAT;

public interface DAO_CHAT {
	public void add_chat(DTO_CHAT chat);
	public List get_chat_list(String type,String chat_no, String cnt);
}
