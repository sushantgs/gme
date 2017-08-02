/**
 *
 */
package com.vaayu.database.vo;

import java.util.Date;
import java.util.Set;

/**
 * @author sushantsg
 *
 */
public class BillVO {

	private int billID;
	private int attachmentID;
	private String billNo;
	private Date billDate;
	private String dsrName;
	private String routeName;
	private String retailName;
	private double netAmount;
	private Set<PaymentVO> payments;

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
	 * @return the attachmentID
	 */
	public int getAttachmentID() {
		return attachmentID;
	}

	/**
	 * @param attachmentID
	 *            the attachmentID to set
	 */
	public void setAttachmentID(final int attachmentID) {
		this.attachmentID = attachmentID;
	}

	/**
	 * @return the billNo
	 */
	public String getBillNo() {
		return billNo;
	}

	/**
	 * @param billNo
	 *            the billNo to set
	 */
	public void setBillNo(final String billNo) {
		this.billNo = billNo;
	}

	/**
	 * @return the billDate
	 */
	public Date getBillDate() {
		return billDate;
	}

	/**
	 * @param billDate
	 *            the billDate to set
	 */
	public void setBillDate(final Date billDate) {
		this.billDate = billDate;
	}

	/**
	 * @return the dsrName
	 */
	public String getDsrName() {
		return dsrName;
	}

	/**
	 * @param dsrName
	 *            the dsrName to set
	 */
	public void setDsrName(final String dsrName) {
		this.dsrName = dsrName;
	}

	/**
	 * @return the routeName
	 */
	public String getRouteName() {
		return routeName;
	}

	/**
	 * @param routeName
	 *            the routeName to set
	 */
	public void setRouteName(final String routeName) {
		this.routeName = routeName;
	}

	/**
	 * @return the retailName
	 */
	public String getRetailName() {
		return retailName;
	}

	/**
	 * @param retailName
	 *            the retailName to set
	 */
	public void setRetailName(final String retailName) {
		this.retailName = retailName;
	}

	/**
	 * @return the netAmount
	 */
	public double getNetAmount() {
		return netAmount;
	}

	/**
	 * @param netAmount
	 *            the netAmount to set
	 */
	public void setNetAmount(final double netAmount) {
		this.netAmount = netAmount;
	}

	/**
	 * @return the payments
	 */
	public Set<PaymentVO> getPayments() {
		return payments;
	}

	/**
	 * @param payments
	 *            the payments to set
	 */
	public void setPayments(final Set<PaymentVO> payments) {
		this.payments = payments;
	}
}
