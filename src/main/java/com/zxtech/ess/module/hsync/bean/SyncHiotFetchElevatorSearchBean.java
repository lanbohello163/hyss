package com.zxtech.ess.module.hsync.bean;

import com.zxtech.platform.utils.page.PageParameter;

public class SyncHiotFetchElevatorSearchBean extends PageParameter {
	
	private static final long serialVersionUID = 1L;
	
	private Integer comp_id;
	private Integer stat_id;
	private String asset_num;
	
	public Integer getComp_id() {
		return comp_id;
	}
	public void setComp_id(Integer comp_id) {
		this.comp_id = comp_id;
	}
	public Integer getStat_id() {
		return stat_id;
	}
	public void setStat_id(Integer stat_id) {
		this.stat_id = stat_id;
	}
	public String getAsset_num() {
		return asset_num;
	}
	public void setAsset_num(String asset_num) {
		this.asset_num = asset_num;
	}
	
}
