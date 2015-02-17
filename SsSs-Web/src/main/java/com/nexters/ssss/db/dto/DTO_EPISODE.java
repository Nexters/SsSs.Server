package com.nexters.ssss.db.dto;

import org.springframework.stereotype.Component;

@Component
public class DTO_EPISODE {
	
	private String ep_no;
	private String brcast_date;
	private String brcast_time;
	private String ins_date;
	private String ins_time;
	
	public String getEp_no() {
		return ep_no;
	}

	public void setEp_no(String ep_no) {
		this.ep_no = ep_no;
	}

	public String getBrcast_date() {
		return brcast_date;
	}

	public void setBrcast_date(String brcast_date) {
		this.brcast_date = brcast_date;
	}

	public String getBrcast_time() {
		return brcast_time;
	}

	public void setBrcast_time(String brcast_time) {
		this.brcast_time = brcast_time;
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

	public DTO_EPISODE(){
		
	}
	
	
}
