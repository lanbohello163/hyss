package com.zxtech.ess.module.api.bean;

import java.sql.Timestamp;
import java.util.Date;

public class ApiOutBound {
	
	private String ROW_ID;
	
	private String PHONE_NUM;
	
	private String AGENT_NUM;
	
	private String ACTIVITY_ID;
	
	private String RECORD_NUM;
	
	private Timestamp HANG_TIME;
	
	private Timestamp CREATE_DATE;
	
	private String CREATED_BY;
	
	private Date UPDATE_DATE;
	
	private String UPDATED_BY;
	
	private String SYNC_FLG;

	public String getROW_ID() {
		return ROW_ID;
	}

	public void setROW_ID(String rOW_ID) {
		ROW_ID = rOW_ID;
	}

	public String getPHONE_NUM() {
		return PHONE_NUM;
	}

	public void setPHONE_NUM(String pHONE_NUM) {
		PHONE_NUM = pHONE_NUM;
	}

	public String getAGENT_NUM() {
		return AGENT_NUM;
	}

	public void setAGENT_NUM(String aGENT_NUM) {
		AGENT_NUM = aGENT_NUM;
	}

	public String getACTIVITY_ID() {
		return ACTIVITY_ID;
	}

	public void setACTIVITY_ID(String aCTIVITY_ID) {
		ACTIVITY_ID = aCTIVITY_ID;
	}

	public String getRECORD_NUM() {
		return RECORD_NUM;
	}

	public void setRECORD_NUM(String rECORD_NUM) {
		RECORD_NUM = rECORD_NUM;
	}

	public Timestamp getHANG_TIME() {
		return HANG_TIME;
	}

	public void setHANG_TIME(Timestamp hANG_TIME) {
		HANG_TIME = hANG_TIME;
	}

	public Timestamp getCREATE_DATE() {
		return CREATE_DATE;
	}

	public void setCREATE_DATE(Timestamp cREATE_DATE) {
		CREATE_DATE = cREATE_DATE;
	}

	public String getCREATED_BY() {
		return CREATED_BY;
	}

	public void setCREATED_BY(String cREATED_BY) {
		CREATED_BY = cREATED_BY;
	}

	public Date getUPDATE_DATE() {
		return UPDATE_DATE;
	}

	public void setUPDATE_DATE(Date uPDATE_DATE) {
		UPDATE_DATE = uPDATE_DATE;
	}

	public String getUPDATED_BY() {
		return UPDATED_BY;
	}

	public void setUPDATED_BY(String uPDATED_BY) {
		UPDATED_BY = uPDATED_BY;
	}

	public String getSYNC_FLG() {
		return SYNC_FLG;
	}

	public void setSYNC_FLG(String sYNC_FLG) {
		SYNC_FLG = sYNC_FLG;
	}

}
