package com.zxtech.ess.module.api.utils.Email;

import java.util.Properties;

public class MailSenderInfo {
	
	 // 发送邮件的服务器的IP  
	private String mailServerHost = "xxxxxxxx";
	// 发送邮件的服务器的端口
	private String mailServerPort = "xxxxxxxx";
	// 邮件发送者的地址
	private String fromAddress = "xxxxxxxx";
	// 邮件接收者的地址
	private String[] toAddress;  
	// 邮件密送接收者的地址  
	private String[] toBlindCarbonCopyAddress;  
	// 邮件抄送接收者的地址  
	private String[] toCarbonCopyAddress;  
	// 登陆邮件发送服务器的用户名  
	private String userName = "xxxxxxxx";  
	// 登陆邮件发送服务器的密码
	private String password = "xxxxxxxx";
	// 邮件主题
	private String subject;
	// 邮件的文本内容
	private String content;
	// 邮件附件的文件名
	private String[] attachFileNames;
	
	 /** 
	 * 获得邮件会话属性 
	 */  
	public Properties getProperties() {  
		Properties pro = new Properties();  
		pro.put("mail.smtp.ssl.enable", "true");
        pro.put("mail.transport.protocol","smtp");  // 使用的协议（JavaMail规范要求）
        pro.put("mail.smtp.host", this.mailServerHost);  
		pro.put("mail.smtp.port", this.mailServerPort);  
		return pro;  
	}  
	

	public String getMailServerHost() {
		return mailServerHost;
	}
	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}
	public String getMailServerPort() {
		return mailServerPort;
	}
	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}
	public String getFromAddress() {
		return fromAddress;
	}
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	public String[] getToAddress() {
		return toAddress;
	}
	public void setToAddress(String[] toAddress) {
		this.toAddress = toAddress;
	}
	public String[] getToBlindCarbonCopyAddress() {
		return toBlindCarbonCopyAddress;
	}
	public void setToBlindCarbonCopyAddress(String[] toBlindCarbonCopyAddress) {
		this.toBlindCarbonCopyAddress = toBlindCarbonCopyAddress;
	}
	public String[] getToCarbonCopyAddress() {
		return toCarbonCopyAddress;
	}
	public void setToCarbonCopyAddress(String[] toCarbonCopyAddress) {
		this.toCarbonCopyAddress = toCarbonCopyAddress;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String[] getAttachFileNames() {
		return attachFileNames;
	}
	public void setAttachFileNames(String[] attachFileNames) {
		this.attachFileNames = attachFileNames;
	}
}
