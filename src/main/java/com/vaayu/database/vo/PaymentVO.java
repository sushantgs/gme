/**
 *
 */
package com.vaayu.database.vo;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author sushantsg
 *
 */
public class PaymentVO {

	private int paymentID;
	private int billID;
	private double amount;
	private String amountType;
	private String chequeNo;
	private Date chequeIssueDate;
	private Date receivedDate;
	private String receiver;
	private String createdBy;
	private Timestamp createdTimestamp;

	/**
	 * @return the paymentID
	 */
	public int getPaymentID() {
		return paymentID;
	}

	/**
	 * @param paymentID
	 *            the paymentID to set
	 */
	public void setPaymentID(final int paymentID) {
		this.paymentID = paymentID;
	}

	/**
	 * @return the billID
	 */
	public int getBillID() {
		return billID;
	}

	/**
	 * @param billID
	 *            the billID to set
	 */
	public void setBillID(final int billID) {
		this.billID = billID;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(final double amount) {
		this.amount = amount;
	}

	/**
	 * @return the amountType
	 */
	public String getAmountType() {
		return amountType;
	}

	/**
	 * @param amountType
	 *            the amountType to set
	 */
	public void setAmountType(final String amountType) {
		this.amountType = amountType;
	}

	/**
	 * @return the chequeNo
	 */
	public String getChequeNo() {
		return chequeNo;
	}

	/**
	 * @param chequeNo
	 *            the chequeNo to set
	 */
	public void setChequeNo(final String chequeNo) {
		this.chequeNo = chequeNo;
	}

	/**
	 * @return the chequeIssueDate
	 */
	public Date getChequeIssueDate() {
		return chequeIssueDate;
	}

	/**
	 * @param chequeIssueDate
	 *            the chequeIssueDate to set
	 */
	public void setChequeIssueDate(final Date chequeIssueDate) {
		this.chequeIssueDate = chequeIssueDate;
	}

	/**
	 * @return the receivedDate
	 */
	public Date getReceivedDate() {
		return receivedDate;
	}

	/**
	 * @param receivedDate
	 *            the receivedDate to set
	 */
	public void setReceivedDate(final Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	/**
	 * @return the receiver
	 */
	public String getReceiver() {
		return receiver;
	}

	/**
	 * @param receiver
	 *            the receiver to set
	 */
	public void setReceiver(final String receiver) {
		this.receiver = receiver;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(final String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdTimestamp
	 */
	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	/**
	 * @param createdTimestamp
	 *            the createdTimestamp to set
	 */
	public void setCreatedTimestamp(final Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
}
