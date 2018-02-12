package cn.mt.hzh.util.email.dto;

import java.util.List;

import cn.mt.hzh.util.email.bean.Email;

public class SendEmailDto extends Email{
	
	/**
	 * 发件人
	 * */
      private String sourceEmail;
      private String sorcePassword;
     /**
  	 * 收件人
  	 * */
      private Object targetEmail;
      
      /*
       * 图片集合
       * */
      private List<String>  img;
     
      /**
       * 附件具体位置
       * */
      private List<String> dtoAccessory;
	
     
      
	public SendEmailDto(String sourceEmail, String sorcePassword, String targetEmail,String title, String content) {
		super(title, content);
		this.sourceEmail = sourceEmail;
		this.sorcePassword = sorcePassword;
		this.targetEmail = targetEmail;
	}
	
	
	
	public SendEmailDto(String title, String content, String sourceEmail, String sorcePassword, String targetEmail,
			List<String> img) {
		super(title, content);
		this.sourceEmail = sourceEmail;
		this.sorcePassword = sorcePassword;
		this.targetEmail = targetEmail;
		this.img = img;
	}



	public SendEmailDto(String sourceEmail, String sorcePassword, String targetEmail, List<String> img,
			List<String> dtoAccessory,String title, String content) {
		super(title, content);
		this.sourceEmail = sourceEmail;
		this.sorcePassword = sorcePassword;
		this.targetEmail = targetEmail;
		this.img = img;
		this.dtoAccessory = dtoAccessory;
	}


	public String getSorcePassword() {
		return sorcePassword;
	}
	public void setSorcePassword(String sorcePassword) {
		this.sorcePassword = sorcePassword;
	}
	public List<String> getImg() {
		return img;
	}
	public void setImg(List<String> img) {
		this.img = img;
	}
	
	public List<String> getDtoAccessory() {
		return dtoAccessory;
	}

	public void setDtoAccessory(List<String> dtoAccessory) {
		this.dtoAccessory = dtoAccessory;
	}

	public String getSourceEmail() {
		return sourceEmail;
	}
	public void setSourceEmail(String sourceEmail) {
		this.sourceEmail = sourceEmail;
	}



	public Object getTargetEmail() {
		return targetEmail;
	}



	public void setTargetEmail(Object targetEmail) {
		this.targetEmail = targetEmail;
	}
	
      
      
}
