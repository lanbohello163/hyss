package com.zxtech.platform.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.zxtech.platform.utils.MD5Util;

/**
 * 此处是树的数据结构，采用自包含的方式完成了多层树状结构
 * 
 * text：是树节点的文字描述 url： 是树节点链接，表征该节点的功能（菜单的时候使用）
 * id：唯一表征树的标记，自动根据text与url的连接串的MD5值表达，该id用于页面上定位节点 isexpand： 节点是否展开 children：
 * 子节点，如果是页的话为null level: 该节点所在的层级 type: 节点类别
 * isdisable值为true 代表此节点默认被选中并且不能被取消选择
 * isnoncheck值为true 代表此节点前面的复选框不可见
 * */
public class TreeBean implements Serializable
{
	private static final long serialVersionUID = 4216184548474878409L;
	private String text = "";
	private String url = "";
	private String id = "";
	private boolean isexpand = false;
	private List<TreeBean> children;

	private int level = 0;
	private int type;

	private boolean isdisable = false;
	private boolean isnoncheck = false;
	private String remark = "";
	private String icon = "";
	private String c_menu_flag = "";
	
	private boolean issa = false;
	
	public boolean isIssa() {
		return issa;
	}

	public void setIssa(boolean issa) {
		this.issa = issa;
	}

	public TreeBean()
	{
	}

	public TreeBean(String text, String url)
	{
		this.text = text;
		this.url = url;
		this.id = MD5Util.get32BitMd5EncString(text + url);
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public boolean isIsdisable() {
		return isdisable;
	}

	public void setIsdisable(boolean isdisable) {
		this.isdisable = isdisable;
	}

	public boolean isIsnoncheck() {
		return isnoncheck;
	}

	public void setIsnoncheck(boolean isnoncheck) {
		this.isnoncheck = isnoncheck;
	}
	
	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public int getLevel()
	{
		return level;
	}

	public void setLevel(int level)
	{
		this.level = level;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public boolean isIsexpand()
	{
		return isexpand;
	}

	public TreeBean setIsexpand(boolean isexpand)
	{
		this.isexpand = isexpand;
		return this;
	}

	public List<TreeBean> getChildren()
	{
		return children;
	}

	public void setChildren(List<TreeBean> children)
	{
		this.children = children;
	}

	public TreeBean addChild(TreeBean child)
	{
		if (children == null)
			children = new ArrayList<TreeBean>();
		children.add(child);

		return this;
	}
	
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getC_menu_flag() {
		return c_menu_flag;
	}

	public void setC_menu_flag(String c_menu_flag) {
		this.c_menu_flag = c_menu_flag;
	}
}
