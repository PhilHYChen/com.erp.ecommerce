package com.erp.demo.model;
// Generated 2024�~3��2�� �U��7:02:46 by Hibernate Tools 6.3.1.Final

import java.sql.Timestamp;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Order generated by hbm2java
 */
@Entity
public class Order implements java.io.Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer oid;
	private String mid;
	private int status;
	private Integer amount;
	private Integer total;
	@Column(length=50)
	private String paymentCode;
	@Column(length=100)
	private String shippingMethod;
	@Column(length=45)
	private String recipient;
	@Column(length=10)
	private String cellphone;
	@Column(length=4)
	private String landlineprefix;
	@Column(length=8)
	private String landline;
	@Column(length=255)
	private String email;
	private int postalCode;
	private String address;
	private String did;
	private String footnote;
	@CreatedDate()
	private Timestamp createTime;
	// 修改時自動創建時間
	@LastModifiedDate
	private Timestamp updateTime;
	@LastModifiedBy
	@Column(length=45)
	private String updateName;

	public Order() {
	}

	public Order(Integer oid,String mid, int status, Integer amount, Integer total, String paymentCode,
			String shippingMethod, String recipient, String cellphone, String landlineprefix, String landline,
			String email, int postalCode, String address, String did, String footnote, Timestamp createTime,
			Timestamp updateTime, String updateName) {
		this.oid = oid;
		this.mid = mid;
		this.status = status;
		this.amount = amount;
		this.total = total;
		this.paymentCode = paymentCode;
		this.shippingMethod = shippingMethod;
		this.recipient = recipient;
		this.cellphone = cellphone;
		this.landlineprefix = landlineprefix;
		this.landline = landline;
		this.email = email;
		this.postalCode = postalCode;
		this.address = address;
		this.did = did;
		this.footnote = footnote;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.updateName = updateName;
	}

	public Integer getOid() {
		return this.oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public String getMid() {
		return this.mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getTotal() {
		return this.total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getPaymentCode() {
		return this.paymentCode;
	}

	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}

	public String getShippingMethod() {
		return this.shippingMethod;
	}

	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

	public String getRecipient() {
		return this.recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getCellphone() {
		return this.cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getLandlineprefix() {
		return this.landlineprefix;
	}

	public void setLandlineprefix(String landlineprefix) {
		this.landlineprefix = landlineprefix;
	}

	public String getLandline() {
		return this.landline;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDid() {
		return this.did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getFootnote() {
		return this.footnote;
	}

	public void setFootnote(String footnote) {
		this.footnote = footnote;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateName() {
		return this.updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

}
