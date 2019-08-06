package com.hb.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "emp")
public class Employee implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String job;
	private Long mgrId;
	private Date hireDate;
	private Long sal;
	private Long comm;

	public Employee(Long id, String name, String job, Long mgrId, Date hireDate, Long sal, Long comm) {
		super();
		this.id = id;
		this.name = name;
		this.job = job;
		this.mgrId = mgrId;
		this.hireDate = hireDate;
		this.sal = sal;
		this.comm = comm;
	}

	public Employee() {
		super();
	}

	@Id
	@Column(name = "empno", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "ename")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "job")
	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
	@Column(name = "mgr")
	public Long getMgrId() {
		return mgrId;
	}

	public void setMgrId(Long mgrId) {
		this.mgrId = mgrId;
	}
	@Column(name = "hiredate")
	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	@Column(name = "sal")
	public Long getSal() {
		return sal;
	}

	public void setSal(Long sal) {
		this.sal = sal;
	}
	@Column(name = "comm")
	public Long getComm() {
		return comm;
	}

	public void setComm(Long comm) {
		this.comm = comm;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
