package cn.mt.hzh.util.email;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import cn.mt.hzh.util.email.constant.SmtpHostEnum;
import cn.mt.hzh.util.email.dto.SendEmailDto;
import cn.mt.hzh.util.judge.collection.JudgeOneOrMore;

public class SendEmailUtil {
	private SendEmailDto emailDto;
	private SmtpHostEnum hostEnum;
	private static SendEmailUtil instance = null;
	private BaseEmailConfig baseConfig;
	private Session session;

	public static final SendEmailUtil getInstance() {
		if (instance == null) {
			synchronized (SendEmailUtil.class) {
				if (instance == null) {
					return new SendEmailUtil();
				}
			}
		}
		return instance;
	}

	private SendEmailUtil() {
		baseConfig = new BaseEmailConfig();
	}
	/**
	 * @param config �Զ����ʼ�ͨ�������ļ�
	 * �����ʼ�ͨ������
	 * */
    public void loadConfig(Properties config){
    	baseConfig.setConfig(config);
    }
	public boolean beginSend(SendEmailDto emailDto, SmtpHostEnum hostEnum) {
		baseConfig.setHostEnum(hostEnum);
		this.hostEnum = hostEnum;
		this.emailDto = emailDto;
		try {
			return send();
		} catch (MessagingException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * @param emailDto �ʼ������Ϣ
	 * @return ���ͳɹ�true ʧ��false;
	 * ��ʼ�����ʼ�
	 * */
	public boolean beginSend(SendEmailDto emailDto) {
		baseConfig.setHostEnum(SmtpHostEnum.TENCENT_SMTP_HOST);
		this.hostEnum = SmtpHostEnum.TENCENT_SMTP_HOST;
		this.emailDto = emailDto;
		try {
			return send();
		} catch (MessagingException | IOException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	/**
	 * �����ʼ�
	 * */
	private boolean send() throws MessagingException, FileNotFoundException, IOException {
		session = baseConfig.init();
		Transport ts = session.getTransport();
		Message message = null;
		ts.connect(hostEnum.getValue(), emailDto.getSourceEmail(), emailDto.getSorcePassword());
		if (emailDto.getImg() != null || emailDto.getDtoAccessory() != null) {
			message = this.writeEmail(session);
			ts.sendMessage(message, message.getAllRecipients());
			ts.close();
		} else {
			message = this.writeSimpleEmail(session);
			ts.sendMessage(message, message.getAllRecipients());
			ts.close();
		}
		return true;

	}

	/**
	 * @param list �����ʼ��������û�
	 * @return ����һ���������С������ʼ��û����ļ���
	 * */
	private InternetAddress[] addInternetAddress(List<String> list) throws AddressException {
		InternetAddress[] ias = new InternetAddress[list.size()];
		for (int i = 0; i < list.size(); i++) {
			ias[i] = new InternetAddress(list.get(i));
		}
		return ias;
	}

	@SuppressWarnings("unchecked")
	private MimeMessage writeSimpleEmail(Session session)
			throws AddressException, MessagingException, FileNotFoundException, IOException {
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(emailDto.getSourceEmail()));
		if (JudgeOneOrMore.isCollection(emailDto.getTargetEmail())) {
			message.setRecipients(Message.RecipientType.TO,
					this.addInternetAddress((List<String>) emailDto.getTargetEmail()));
		}else{
			message.setRecipient(Message.RecipientType.TO,new InternetAddress((String) emailDto.getTargetEmail()));
		}
		message.setSubject(emailDto.getTitle());
		message.setContent(emailDto.getContent(), "text/html;charset=UTF-8");
		return message;
	}

	@SuppressWarnings("unchecked")
	private MimeMessage writeEmail(Session session)
			throws AddressException, MessagingException, FileNotFoundException, IOException {
		MimeMessage message = new MimeMessage(session);
		// ָ���ʼ��ķ�����
		message.setFrom(new InternetAddress(emailDto.getSourceEmail()));
		// ָ���ʼ����ռ���
		if (JudgeOneOrMore.isCollection(emailDto.getTargetEmail())) {
			message.setRecipients(Message.RecipientType.TO,
					this.addInternetAddress((List<String>) emailDto.getTargetEmail()));
		}else{
			message.setRecipient(Message.RecipientType.TO,new InternetAddress((String) emailDto.getTargetEmail()));
		}		// �ʼ��ı���
		message.setSubject(emailDto.getTitle());
		// ������ͼƬ�Ĺ�ϵ
		MimeMultipart imgAndTxtMM = null;
		MimeMultipart accessoryAndcontent = null;
		String textContent = emailDto.getContent();
		if (emailDto.getImg() != null && emailDto.getImg().size() > 0) {
			imgAndTxtMM = new MimeMultipart();
			MimeBodyPart text = new MimeBodyPart();
			int imgCount = 1;
			for (String imagePath : emailDto.getImg()) {
				textContent += "<img src='cid:" + imgCount + "'></br>";
				MimeBodyPart image = new MimeBodyPart();
				File file = new File(imagePath);
				DataHandler dh = new DataHandler(new FileDataSource(file));
				image.setDataHandler(dh);
				image.setContentID(String.valueOf(imgCount));
				imgAndTxtMM.addBodyPart(image);
				imgCount++;
			}
			text.setContent(textContent, "text/html;charset=UTF-8");
			imgAndTxtMM.addBodyPart(text);
			imgAndTxtMM.setSubType("related");
		}
		//����������(ͼ�Ĳ�ï������)�Ĺ�ϵ
		MimeBodyPart content;
		if (imgAndTxtMM != null && emailDto.getDtoAccessory().size() > 0) {
			accessoryAndcontent = new MimeMultipart("mixed");
			content = new MimeBodyPart();
			content.setContent(imgAndTxtMM);
			accessoryAndcontent.addBodyPart(content);
			for (String accessoryFilePath : emailDto.getDtoAccessory()) {
				MimeBodyPart accessory = this.createAccessory(accessoryFilePath);
				accessoryAndcontent.addBodyPart(accessory);
			}
			message.setContent(accessoryAndcontent);
			message.saveChanges();
			return message;
		}else if(imgAndTxtMM == null&& emailDto.getDtoAccessory().size() > 0){
			accessoryAndcontent = new MimeMultipart("mixed");
			MimeBodyPart text = new MimeBodyPart();
			text.setContent(textContent, "text/html;charset=UTF-8");
			accessoryAndcontent.addBodyPart(text);
			for (String accessoryFilePath : emailDto.getDtoAccessory()) {
				MimeBodyPart accessory = this.createAccessory(accessoryFilePath);
				accessoryAndcontent.addBodyPart(accessory);
			}
			message.setContent(accessoryAndcontent);
			message.saveChanges();
			return message;
		}
		else {
			message.setContent(imgAndTxtMM);
			message.saveChanges();
			return message;
		}
	}

	private MimeBodyPart createAccessory(String filePath) throws MessagingException {
		MimeBodyPart accessory = new MimeBodyPart();
		FileDataSource fs = new FileDataSource(filePath);
		accessory.setDataHandler(new DataHandler(fs));
		accessory.setFileName(fs.getName());
		return accessory;
	}

}
