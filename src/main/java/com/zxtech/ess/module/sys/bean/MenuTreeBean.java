package com.zxtech.ess.module.sys.bean;

import com.zxtech.platform.vo.TreeBean;

/**
 * 用于构建页面的菜单树
 * @author 
 *
 */
public class MenuTreeBean extends TreeBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean ischecked;
	
	/**
	 * 如果节点类型是菜单则该字段存储的是默认funId
	 * 如果节点类型是按钮则该字段存储的是所属menuId
	 */
	private String refId;

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public boolean isIschecked() {
		return ischecked;
	}

	public void setIschecked(boolean ischecked) {
		this.ischecked = ischecked;
	}


}
