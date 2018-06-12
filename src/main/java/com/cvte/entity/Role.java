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
* @author: jan 
* @date: 2018年5月6日 上午11:48:10 
*/
@Entity
@Table(name="role")
public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3539592433652362666L;

	private String id;
	
	private String roleName;
	
	private String roleDescribe;
	
	private Date createDate;
	
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

	@Column(name="role_name")
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name="role_desc")
	public String getRoleDescribe() {
		return roleDescribe;
	}

	public void setRoleDescribe(String roleDescribe) {
		this.roleDescribe = roleDescribe;
	}

	@Column(name="create_date")
	@Temporal(TemporalType.TIMESTAMP) 
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name="deleted")
	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public Role(String id, String roleName, String roleDescribe, Date createDate, int deleted) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.roleDescribe = roleDescribe;
		this.createDate = createDate;
		this.deleted = deleted;
	}

	public Role() {
		super();
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + ", roleDescribe=" + roleDescribe + ", createDate="
				+ createDate + ", deleted=" + deleted + "]";
	}
	
}
