package cn.mt.hzh.util.email.constant;
/**
 * �������ͳ���
 * */
public enum SmtpHostEnum {
   TENCENT_SMTP_HOST("mail.smtp.host","smtp.qq.com");
	private  String  smtpHost;
	private  String  value;
	
	
	
	private SmtpHostEnum(String smtpHost, String value) {
		this.smtpHost = smtpHost;
		this.value = value;
	}

	public String getSmtpHost() {
		return smtpHost;
	}

	public String getValue() {
		return value;
	}

	
}
