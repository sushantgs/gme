/**
 *
 */
package com.vaayu.database.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaayu.database.vo.BillVO;
import com.vaayu.database.vo.PaymentVO;
import com.vaayu.database.vo.PaymentView;

/**
 *
 * @author sushantsg
 */
public class PaymentDAO extends HibernateDAO {

	private static final Logger logger = LoggerFactory.getLogger(PaymentDAO.class);

	private BillDAO billDAO;

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

	public PaymentVO get(final int paymentID) {
		logger.debug("Retrieving payment object with ID:" + paymentID);
		return getSession().get(PaymentVO.class, paymentID);
	}

	public List<PaymentVO> list(final int billID) {

		logger.debug("Fetching all payments for the bill ID:" + billID);

		return getSession().createQuery("FROM PaymentVO where billID=" + billID).list();
	}

	public PaymentView listDetails(final int billID) {

		logger.debug("Fetching all payments with bill details for the bill ID:" + billID);

		final PaymentView view = new PaymentView();
		final List<PaymentVO> payments = list(billID);

		final BillVO billVO = billDAO.get(billID);
		view.setBillNo(billVO.getBillNo());
		view.setBillDate(billVO.getBillDate());
		view.setNetAmount(billVO.getNetAmount());
		final double totalAmount = receivedAmount(payments);
		view.setBalance(billVO.getNetAmount() - totalAmount);
		view.setPayments(payments);

		return view;
	}

	public double getBalance(final int billID) {
		logger.debug("Fetching balance for the bill ID:" + billID);

		final List<PaymentVO> payments = list(billID);
		final double totalAmount = receivedAmount(payments);
		final BillVO billVO = billDAO.get(billID);
		return billVO.getNetAmount() - totalAmount;
	}

	private double receivedAmount(final List<PaymentVO> payments) {
		logger.debug("Calculating the balance.");
		double totalAmount = 0;
		for (final PaymentVO vo : payments) {
			totalAmount += vo.getAmount();
		}
		return totalAmount;
	}

	public PaymentVO upsert(final PaymentVO vo) {

		logger.debug("Insert or Update the payment.");
		final Session session = getSession();
		final Transaction t = session.beginTransaction();
		PaymentVO paymentVO;

		if (0 == vo.getPaymentID()) {
			paymentVO = new PaymentVO();
			paymentVO.setBillID(vo.getBillID());
			paymentVO.setCreatedBy("Sushant");
			paymentVO.setCreatedTimestamp(Timestamp.valueOf(LocalDateTime.now()));
		} else {
			paymentVO = get(vo.getPaymentID());
		}
		paymentVO.setAmount(vo.getAmount());
		paymentVO.setAmountType(vo.getAmountType());
		paymentVO.setChequeNo(vo.getChequeNo());
		paymentVO.setChequeIssueDate(vo.getChequeIssueDate());
		paymentVO.setReceivedDate(vo.getReceivedDate());
		paymentVO.setReceiver(vo.getReceiver());

		session.persist(paymentVO);
		t.commit();

		return paymentVO;
	}

}
