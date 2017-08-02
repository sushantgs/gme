/**
 *
 */
package com.vaayu.database.vo;

import java.util.Date;
import java.util.List;

/**
 * @author sushantsg
 *
 */
public class PaymentView {

	private String billNo;
	private Date billDate;
	private double netAmount;
	private double balance;
	private List<PaymentVO> payments;

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
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param balance
	 *            the balance to set
	 */
	public void setBalance(final double balance) {
		this.balance = balance;
	}

	/**
	 * @return the payments
	 */
	public List<PaymentVO> getPayments() {
		return payments;
	}

	/**
	 * @param payments
	 *            the payments to set
	 */
	public void setPayments(final List<PaymentVO> payments) {
		this.payments = payments;
	}
}
