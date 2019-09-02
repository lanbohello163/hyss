package com.zxtech.platform.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.zxtech.ess.module.gen.bean.SysRole;

public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String sourceId;
	private Integer userId;
	private String userCode;
	private String empCode;
	private String userPasswd;
	private String realname;
	private Integer currentRoleId;
	private String currentRoleName;
	private String dataType;
	private Boolean isSa = false;
	private Boolean isHeadOffice = false;	//当前角色是否属于总公司
	private List<Integer> compIdList = new ArrayList<>();
	private List<Integer> statIdList = new ArrayList<>();
	private List<Integer> areaIdList = new ArrayList<>();
	private List<Map<String, Object>> menuList = new ArrayList<Map<String,Object>>();
	
	private Integer compId;
	private List<SysRole> roleList = new ArrayList<SysRole>();
	private Set<String> funcSet = new HashSet<>();

	public Integer getCompId() {
		return compId;
	}

	public void setCompId(Integer compId) {
		this.compId = compId;
	}

	public void addCompanyList(Integer companyId) {
		compIdList.add(companyId);
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserPasswd() {
		return userPasswd;
	}

	public void setUserPasswd(String userPasswd) {
		this.userPasswd = userPasswd;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public Integer getCurrentRoleId() {
		return currentRoleId;
	}

	public void setCurrentRoleId(Integer currentRoleId) {
		this.currentRoleId = currentRoleId;
	}

	public String getCurrentRoleName() {
		return currentRoleName;
	}

	public void setCurrentRoleName(String currentRoleName) {
		this.currentRoleName = currentRoleName;
	}

	public List<SysRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<SysRole> roleList) {
		this.roleList = roleList;
	}

	public void addRoleList(SysRole bean) {
		roleList.add(bean);
	}

	public void putFuncName(String funcName) {
		funcSet.add(funcName);
	}

	public boolean hasFuncName(String funcName) {
		if (funcSet.contains(funcName)) {
			return true;
		}
		return false;
	}

	public void cleanUserFunc() {
		funcSet = new HashSet<>();
	}

	public Boolean getIsSa() {
		return isSa;
	}

	public void setIsSa(Boolean isSa) {
		this.isSa = isSa;
	}

	public Boolean getIsHeadOffice() {
		return isHeadOffice;
	}

	public void setIsHeadOffice(Boolean isHeadOffice) {
		this.isHeadOffice = isHeadOffice;
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

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public List<Map<String, Object>> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Map<String, Object>> menuList) {
		this.menuList = menuList;
	}
	
	
}
