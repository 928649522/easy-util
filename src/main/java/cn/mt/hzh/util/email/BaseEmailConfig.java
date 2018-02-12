package cn.mt.hzh.util.email;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;

import cn.mt.hzh.util.email.constant.SmtpHostEnum;

public class BaseEmailConfig {
	private SmtpHostEnum hostEnum;
	private Properties config;
	public void setConfig(Properties config) {
		this.config = config;
	}

	public BaseEmailConfig() {
	}	
    
	public void setHostEnum(SmtpHostEnum hostEnum) {
		this.hostEnum = hostEnum;
	}

	/**
	 * @param hostEnum ��������
	 * */
	public BaseEmailConfig(SmtpHostEnum hostEnum) {
          this.hostEnum=hostEnum;
	}
   public Session init() throws MessagingException{
	   Session session ;
	   if(config!=null){
		   session=Session.getInstance(config);
	   }else{
		   session=Session.getInstance(this.initConfig());
	   }
		 //����Session��debugģʽ�������Ϳ��Բ鿴��������Email������״̬
		 session.setDebug(true);
          return session;
   }
  
   public Properties initConfig(){
		 Properties initConfig = new Properties();
		 //�����ʼ��ṩ�̵ķ�������ַ
		 initConfig.setProperty(hostEnum.getSmtpHost(), hostEnum.getValue());
		 //�����ṩЭ��smtp
		 initConfig.setProperty("mail.transport.protocol", "smtp");
		 //�����û���¼
		 initConfig.setProperty("mail.smtp.auth", "true");
		 //���ö˿� 465 25  ��д�Ļ�Ĭ��
		 initConfig.setProperty("mail.smtp.port", "465");
		 //��������sll��ȫ��֤
		 initConfig.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		 initConfig.setProperty("mail.smtp.socketFactory.port", "465");
		 return initConfig;
   }
}
