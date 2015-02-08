package com.nexters.ssss.db.dto;

public class DTO_USER {
	private String usr_no;
	private String usr_uuid;
	private String usr_nn;
	private String usr_pushid;
	private String alaramyn;
	
	/**
	 * Get User No
	 * @return User No
	 */
	public String getUsr_no() {
		return usr_no;
	}
	
	/**
	 * 
	 * @param usr_no
	 */
	public void setUsr_no(String usr_no) {
		this.usr_no = usr_no;
	}
	
	/**
	 * 
	 * @return User UUID
	 */
	public String getUsr_uuid() {
		return usr_uuid;
	}
	
	/**
	 * 
	 * @param usr_uuid
	 */
	public void setUsr_uuid(String usr_uuid) {
		this.usr_uuid = usr_uuid;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getUsr_nn() {
		return usr_nn;
	}
	
	/**
	 * 
	 * @param usr_nn
	 */
	public void setUsr_nn(String usr_nn) {
		this.usr_nn = usr_nn;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getUsr_pushid() {
		return usr_pushid;
	}
	
	/**
	 * 
	 * @param usr_pushid
	 */
	public void setUsr_pushid(String usr_pushid) {
		this.usr_pushid = usr_pushid;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getAlaramyn() {
		return alaramyn;
	}
	
	/**
	 * 
	 * @param alaramyn
	 */
	public void setAlaramyn(String alaramyn) {
		this.alaramyn = alaramyn;
	}
}