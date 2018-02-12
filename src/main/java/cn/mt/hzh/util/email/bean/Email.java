package cn.mt.hzh.util.email.bean;

import java.util.List;

public class Email {
    private String title;
    private String content;
    /**
     * ¸½¼þÃû
     * */
    private String accessory;
    
    
	public Email(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}
	public Email(String title, String content, List<String> image) {
		super();
		this.title = title;
		this.content = content;
	}
	public Email(String title, String content, List<String> image, String accessory) {
		super();
		this.title = title;
		this.content = content;
		this.accessory = accessory;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getAccessory() {
		return accessory;
	}
	public void setAccessory(String accessory) {
		this.accessory = accessory;
	}
    
    
    
}
