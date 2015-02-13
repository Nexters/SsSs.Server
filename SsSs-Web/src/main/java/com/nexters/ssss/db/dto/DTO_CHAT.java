package com.nexters.ssss.db.dto;

import org.springframework.stereotype.Component;

@Component
public class DTO_CHAT {
	private String chat_no;
	private String usr_no;
	private String cont;
	private String ins_date;
	private String ins_time;
	private String usr_nn;
	
	public void DTO_CHAT(){
		
	}
	public String getusr_nn(){
		return usr_nn;
	}
	public void setusr_nn(String usr_nn){
		
	}
	public String getChat_no() {
		return chat_no;
	}
	public void setChat_no(String chat_no) {
		this.chat_no = chat_no;
	}
	public String getUsr_no() {
		return usr_no;
	}
	public void setUsr_no(String usr_no) {
		this.usr_no = usr_no;
	}
	public String getCont() {
		return cont;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}
	public String getIns_date() {
		return ins_date;
	}
	public void setIns_date(String ins_date) {
		this.ins_date = ins_date;
	}
	public String getIns_time() {
		return ins_time;
	}
	public void setIns_time(String ins_time) {
		this.ins_time = ins_time;
	}

}
