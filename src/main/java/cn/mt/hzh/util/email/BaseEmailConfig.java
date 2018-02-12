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
	 * @param hostEnum 邮箱类型
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
		 //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
		 session.setDebug(true);
          return session;
   }
  
   public Properties initConfig(){
		 Properties initConfig = new Properties();
		 //配置邮件提供商的服务器地址
		 initConfig.setProperty(hostEnum.getSmtpHost(), hostEnum.getValue());
		 //配置提供协议smtp
		 initConfig.setProperty("mail.transport.protocol", "smtp");
		 //开启用户登录
		 initConfig.setProperty("mail.smtp.auth", "true");
		 //设置端口 465 25  不写的话默认
		 initConfig.setProperty("mail.smtp.port", "465");
		 //必须设置sll安全认证
		 initConfig.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		 initConfig.setProperty("mail.smtp.socketFactory.port", "465");
		 return initConfig;
   }
}
