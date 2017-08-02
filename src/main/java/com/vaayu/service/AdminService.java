/**
 *
 */
package com.vaayu.service;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.vaayu.database.dao.AttachmentDAO;
import com.vaayu.database.dao.BillDAO;
import com.vaayu.database.dao.PaymentDAO;
import com.vaayu.database.vo.BaseAttachmentVO;
import com.vaayu.database.vo.BillView;

/**
 * @author sushantsg
 *
 */
@Service
public class AdminService {

	private static final Logger logger = LoggerFactory.getLogger(AdminService.class);

	private AttachmentDAO attachmentDAO;
	private BillDAO billDAO;
	private PaymentDAO paymentDAO;

	/**
	 * @return the attachmentDAO
	 */
	public AttachmentDAO getAttachmentDAO() {
		return attachmentDAO;
	}

	/**
	 * @param attachmentDAO
	 *            the attachmentDAO to set
	 */
	public void setAttachmentDAO(final AttachmentDAO attachmentDAO) {
		this.attachmentDAO = attachmentDAO;
	}

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

	public void upload(final String fileName, final String fileDescription, final File file) {
		logger.info("In admin service upload method.");
		attachmentDAO.insert(fileName, fileDescription, file);
	}

	public List<BaseAttachmentVO> listAttachments() {
		logger.info("In admin service listAttachments method.");
		return attachmentDAO.list();
	}

	public List<BillView> listBills(final int attachmentID) {
		logger.info("In admin service listBills method.");
		return billDAO.listDetails(attachmentID);
	}

}
