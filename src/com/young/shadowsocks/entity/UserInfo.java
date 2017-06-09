package com.young.shadowsocks.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UserInfo")
public class UserInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String code;
	private String name;
	private String pwd;
	private String phone;
	/**
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone 要设置的 phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email 要设置的 email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return createtime
	 */
	public Date getCreatetime() {
		return createtime;
	}

	/**
	 * @param createtime 要设置的 createtime
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	/**
	 * @return lastlogtime
	 */
	public Date getLastlogtime() {
		return lastlogtime;
	}

	/**
	 * @param lastlogtime 要设置的 lastlogtime
	 */
	public void setLastlogtime(Date lastlogtime) {
		this.lastlogtime = lastlogtime;
	}

	/**
	 * @return duedate
	 */
	public Date getDuedate() {
		return duedate;
	}

	/**
	 * @param duedate 要设置的 duedate
	 */
	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}

	/**
	 * @return serverip
	 */
	public String getServerip() {
		return serverip;
	}

	/**
	 * @param serverip 要设置的 serverip
	 */
	public void setServerip(String serverip) {
		this.serverip = serverip;
	}

	/**
	 * @return serverpwd
	 */
	public String getServerpwd() {
		return serverpwd;
	}

	/**
	 * @param serverpwd 要设置的 serverpwd
	 */
	public void setServerpwd(String serverpwd) {
		this.serverpwd = serverpwd;
	}

	private String email;
	private Date createtime;
	private Date lastlogtime;
	private Date duedate;
	private String serverip;
	private String serverpwd;

	public UserInfo(int id) {
		this.id = id;
	}
	
	public UserInfo() {
		// TODO 自动生成的构造函数存根
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}