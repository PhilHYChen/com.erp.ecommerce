package com.erp.demo.model;
// Generated 2024�~2��28�� �W��3:00:56 by Hibernate Tools 6.3.1.Final

import java.io.Serializable;

/**
 * EmDepartment generated by hbm2java
 */
public class EmDepartment implements java.io.Serializable {

	private String departmentCode;
	private Serializable departmentName;
	private Serializable jobTitle;

	public EmDepartment() {
	}

	public EmDepartment(String departmentCode, Serializable departmentName, Serializable jobTitle) {
		this.departmentCode = departmentCode;
		this.departmentName = departmentName;
		this.jobTitle = jobTitle;
	}

	public String getDepartmentCode() {
		return this.departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public Serializable getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(Serializable departmentName) {
		this.departmentName = departmentName;
	}

	public Serializable getJobTitle() {
		return this.jobTitle;
	}

	public void setJobTitle(Serializable jobTitle) {
		this.jobTitle = jobTitle;
	}

}
