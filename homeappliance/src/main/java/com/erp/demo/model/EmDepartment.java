package com.erp.demo.model;
// Generated 2024�~3��2�� �U��7:02:46 by Hibernate Tools 6.3.1.Final

/**
 * EmDepartment generated by hbm2java
 */
public class EmDepartment implements java.io.Serializable {

	private String departmentCode;
	private String departmentName;
	private String jobTitle;

	public EmDepartment() {
	}

	public EmDepartment(String departmentCode, String departmentName, String jobTitle) {
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

	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getJobTitle() {
		return this.jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

}
