/**
 *
 */
package com.vaayu.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.vaayu.database.dao.BillDAO;
import com.vaayu.database.dao.PaymentDAO;
import com.vaayu.database.vo.PaymentVO;
import com.vaayu.database.vo.PaymentView;

/**
 * @author sushantsg
 *
 */
@Service
public class PaymentService {

	private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

	private BillDAO billDAO;
	private PaymentDAO paymentDAO;

	/**
	 * @return the billDAO
	 */
	public BillDAO getBillDAO() {
		return billDAO;
	}

	/**
	 * @param billDAO
	 *            the billDAO to set
	 */
	public void setBillDAO(final BillDAO billDAO) {
		this.billDAO = billDAO;
	}

	/**
	 * @return the paymentDAO
	 */
	public PaymentDAO getPaymentDAO() {
		return paymentDAO;
	}

	/**
	 * @param paymentDAO
	 *            the paymentDAO to set
	 */
	public void setPaymentDAO(final PaymentDAO paymentDAO) {
		this.paymentDAO = paymentDAO;
	}

	public void updatePayment(final PaymentVO paymentVO) {
		logger.info("In PaymentService.updatePayment method with VO.");
		paymentDAO.upsert(paymentVO);
	}

	public PaymentView listPayments(final int billID) {
		logger.info("In PaymentService.listPayments method.");
		return paymentDAO.listDetails(billID);
	}
}
