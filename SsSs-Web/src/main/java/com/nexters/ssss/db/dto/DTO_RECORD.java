package com.nexters.ssss.db.dto;

import org.springframework.stereotype.Component;

@Component
public class DTO_RECORD {
	private String file_no;
	private String usr_no;
	private String file_nm;
	private String is_play_yn;
	private String is_file_yn;
	private String ins_date;
	private String ins_time;
	
	public DTO_RECORD(){
	
	}
	public String getFile_no() {
		return file_no;
	}
	public void setFile_no(String file_no) {
		this.file_no = file_no;
	}
	public String getIs_play_yn() {
		return is_play_yn;
	}
	public void setIs_play_yn(String is_play_yn) {
		this.is_play_yn = is_play_yn;
	}
	public String getUsr_no() {
		return usr_no;
	}
	public void setUsr_no(String usr_no) {
		this.usr_no = usr_no;
	}
	public String getFile_nm() {
		return file_nm;
	}
	public void setFile_nm(String file_nm) {
		this.file_nm = file_nm;
	}
	public String getIs_file_yn() {
		return is_file_yn;
	}
	public void setIs_file_yn(String is_file_yn) {
		this.is_file_yn = is_file_yn;
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
