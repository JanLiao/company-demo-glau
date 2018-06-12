package com.cvte.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
* @author: jan 
* @date: 2018年5月6日 上午11:50:00 
*/
@Entity
@Table(name="resource")
public class Resource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6843180438437628033L;
	
	private String id;
	
	private String resourceName;
	
	private String resourceUrl;
	
	private String parentName;
	
	private String rid;

	private int deleted;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name="resource_name")
	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	@Column(name="resource_url")
	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	@Column(name="parent_name")
	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	@Column(name="rid")
	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	@Column(name="deleted")
	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public Resource(String id, String resourceName, String resourceUrl, String parentName, int deleted) {
		super();
		this.id = id;
		this.resourceName = resourceName;
		this.resourceUrl = resourceUrl;
		this.parentName = parentName;
		this.deleted = deleted;
	}

	public Resource() {
		super();
	}

	@Override
	public String toString() {
		return "Resource [id=" + id + ", resourceName=" + resourceName + ", resourceUrl=" + resourceUrl
				+ ", parentName=" + parentName + ", deleted=" + deleted + "]";
	}
}
