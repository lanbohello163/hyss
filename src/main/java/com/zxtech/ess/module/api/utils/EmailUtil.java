package com.zxtech.ess.module.api.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zxtech.ess.module.api.utils.Email.MailSenderInfo;

/**
 * 发送邮件
 * 
 * @author syp660
 *
 *         创建时间：2018年11月22日下午2:43:01
 */
public class EmailUtil {
	private static Logger log = LoggerFactory.getLogger(EmailUtil.class);
	private static LinkedBlockingQueue<MailSenderInfo> mailQueue = new LinkedBlockingQueue<>();

	static {
		sendMailFromQueue();
	}

	public static void addMailQueue(MailSenderInfo senderInfo) throws InterruptedException {
		mailQueue.put(senderInfo);
	}

	private static void sendMailFromQueue() {
		new Thread(() -> {
			try {
				while (true) {
					MailSenderInfo senderInfo = mailQueue.take();
					sendHtmlMail(senderInfo);
				}
			} catch (Exception e) {
				log.error("Send Mail is ERROR!!! " + e.getMessage());
			}
		}).start();
	}

	/**
	 * 发送html格式邮件
	 * 
	 * @param senderInfo
	 * @return
	 */
	public static int sendHtmlMail(MailSenderInfo senderInfo) {
		int resultFlag = 1;
		senderInfo.setMailServerHost(SysParameterInfoUtil.getSysParameterInfo("mailServerHost"));
		senderInfo.setMailServerPort(SysParameterInfoUtil.getSysParameterInfo("mailServerPort"));
		senderInfo.setFromAddress(SysParameterInfoUtil.getSysParameterInfo("mailFromAddress"));
		senderInfo.setUserName(SysParameterInfoUtil.getSysParameterInfo("mailUserName"));
		senderInfo.setPassword(SysParameterInfoUtil.getSysParameterInfo("mailPassword"));
		Properties props = senderInfo.getProperties();
		// 根据邮件会话属性构造一个发送邮件的session
		Session sendMailSession = Session.getDefaultInstance(props);
		try {
			// 根据session创建一个邮件消息
			Message sendMailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(senderInfo.getFromAddress());
			// 设置邮件消息的发送者
			sendMailMessage.setFrom(from);

			// 创建邮件接收者地址
			String[] tos = senderInfo.getToAddress();
			if (tos != null && tos.length != 0) {
				InternetAddress[] to = new InternetAddress[tos.length];
				// 设置邮件消息的发送者
				for (int i = 0; i < tos.length; i++) {
					to[i] = new InternetAddress(tos[i]);
				}
				sendMailMessage.setRecipients(Message.RecipientType.TO, to);
			}

			// 设置邮件抄送者地址
			String[] toCCs = senderInfo.getToCarbonCopyAddress();
			if (toCCs != null && toCCs.length != 0) {
				InternetAddress[] toCC = new InternetAddress[toCCs.length];
				// 设置邮件消息的发送者
				for (int i = 0; i < toCCs.length; i++) {
					toCC[i] = new InternetAddress(toCCs[i]);
				}
				sendMailMessage.addRecipients(Message.RecipientType.CC, toCC);
			}

			// 设置邮件密送者地址
			String[] toBCCs = senderInfo.getToBlindCarbonCopyAddress();
			if (toBCCs != null && toBCCs.length != 0) {
				InternetAddress[] toBCC = new InternetAddress[toBCCs.length];
				for (int i = 0; i < toBCCs.length; i++) {
					toBCC[i] = new InternetAddress(toBCCs[i]);
				}
				sendMailMessage.addRecipients(Message.RecipientType.BCC, toBCC);
			}

			// 设置邮件主题
			sendMailMessage.setSubject(MimeUtility.encodeText(senderInfo.getSubject(), "utf-8", "B"));
			Multipart multipart = new MimeMultipart();
			// 邮件文本内容
			if (senderInfo.getContent() != null && !"".equals(senderInfo.getContent())) {
				BodyPart part = new MimeBodyPart();
				part.setContent(senderInfo.getContent(), "text/html;charset=utf-8");// 设置邮件文本内容
				multipart.addBodyPart(part);
			}
			// 附件
			String attachFileNames[] = senderInfo.getAttachFileNames();
			int leng = attachFileNames == null ? 0 : attachFileNames.length;
			for (int i = 0; i < leng; i++) {
				BodyPart part = new MimeBodyPart();
				// 根据文件名获取数据源
				DataSource dataSource = new FileDataSource(attachFileNames[i]);
				DataHandler dataHandler = new DataHandler(dataSource);
				// 得到附件本身并至入BodyPart
				part.setDataHandler(dataHandler);
				// 得到文件名同样至入BodyPart
				part.setFileName(MimeUtility.encodeText(dataSource.getName()));
				multipart.addBodyPart(part);
			}

			sendMailMessage.setContent(multipart);
			// 设置邮件发送的时间
			sendMailMessage.setSentDate(new Date());
			// 发送邮件
			Transport transport = sendMailSession.getTransport();
			transport.connect(senderInfo.getMailServerHost(), senderInfo.getUserName(), senderInfo.getPassword());
			transport.sendMessage(sendMailMessage, sendMailMessage.getAllRecipients());
			// 关闭transport
			transport.close();
		} catch (AddressException e) {
			resultFlag = 0;
			e.printStackTrace();
		} catch (MessagingException e) {
			resultFlag = 0;
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			resultFlag = 0;
			e.printStackTrace();
		}
		return resultFlag;
	}
}
