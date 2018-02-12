package cn.mt.hzh.util.email.constant;
/**
 * 邮箱类型常量
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
