package com.cvte.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
* @author jan
* @data 2018年8月17日 上午10:02:10
*/
@Entity
@Table(name="message")
public class Message implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2446888515872147129L;
	private int id;
	private int accountId;
	private int messageId;
	private String title;
	private int type;
	private String iconUrl;  // 小图标路径
	private String messageSrc;  // 小源
	private Date generateTime;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "account_id")
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
	@Column(name = "message_id")
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	@Column(name = "title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "type")
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	@Column(name = "icon_url")
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	
	@Column(name = "message_src")
	public String getMessageSrc() {
		return messageSrc;
	}
	public void setMessageSrc(String messageSrc) {
		this.messageSrc = messageSrc;
	}
	
	@Column(name = "generate_time")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getGenerateTime() {
		return generateTime;
	}
	public void setGenerateTime(Date generateTime) {
		this.generateTime = generateTime;
	}

}
