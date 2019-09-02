package com.zxtech.platform.utils.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;

import com.zxtech.platform.vo.UserBean;

public class PageParameter implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int rows = 15;
	private int page = 1;
	private boolean isPageQeury = false;
	private List<Integer> compIdList = new ArrayList<>();
	
	private List<Integer> statIdList = new ArrayList<>();
	
	private List<Integer> areaIdList = new ArrayList<>();
	
	private String query_helper;
	private String link_query_id;
	
	private String dgTitle;
	private String dgFields;
	private String dgColumnTitles;
	private String dgMultiTitles;
	
	public String getDgMultiTitles() {
		return dgMultiTitles;
	}

	public void setDgMultiTitles(String dgMultiTitles) {
		this.dgMultiTitles = dgMultiTitles;
	}

	public String getQuery_helper() {
		return query_helper;
	}

	public void setQuery_helper(String query_helper) {
		this.query_helper = StringEscapeUtils.unescapeHtml(query_helper);
	}

	public List<Integer> getCompIdList() {
		return compIdList;
	}

	public void setCompIdList(List<Integer> compIdList) {
		this.compIdList = compIdList;
	}

	public List<Integer> getStatIdList() {
		return statIdList;
	}

	public void setStatIdList(List<Integer> statIdList) {
		this.statIdList = statIdList;
	}

	public List<Integer> getAreaIdList() {
		return areaIdList;
	}

	public void setAreaIdList(List<Integer> areaIdList) {
		this.areaIdList = areaIdList;
	}
	
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	//从UserBean 中获取权限集合
	public void setDataPermissionList(UserBean user) {
		if(!user.getIsHeadOffice()) {
			this.compIdList = user.getCompIdList();
			this.statIdList = user.getStatIdList();
			this.areaIdList = user.getAreaIdList();
		}
	}

	public String getDgTitle() {
		return dgTitle;
	}

	public void setDgTitle(String dgTitle) {
		this.dgTitle = dgTitle;
	}

	public String getDgFields() {
		return dgFields;
	}

	public void setDgFields(String dgFields) {
		this.dgFields = dgFields;
	}

	public String getDgColumnTitles() {
		return dgColumnTitles;
	}

	public void setDgColumnTitles(String dgColumnTitles) {
		this.dgColumnTitles = dgColumnTitles;
	}
	public String getLink_query_id() {
		return link_query_id;
	}

	public void setLink_query_id(String link_query_id) {
		this.link_query_id = link_query_id;
	}

	public boolean isPageQeury() {
		return isPageQeury;
	}

	public void setPageQeury(boolean isPageQeury) {
		this.isPageQeury = isPageQeury;
	}
	
}
