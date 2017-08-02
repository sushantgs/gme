/**
 *
 */
package com.vaayu.database.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaayu.database.vo.AttachmentVO;
import com.vaayu.database.vo.BillVO;
import com.vaayu.database.vo.BillView;

/**
 *
 * @author sushantsg
 */
public class BillDAO extends HibernateDAO {

	private static final Logger logger = LoggerFactory.getLogger(BillDAO.class);
	private PaymentDAO paymentDAO;

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

	public List<BillVO> list(final int attachmentID) {

		logger.debug("Fetching all bills");
		final List<BillVO> bills = getSession().createQuery("FROM BillVO where attachmentID=" + attachmentID).list();
		return bills;
	}

	public List<BillView> listDetailsPerfIssue(final int attachmentID) {

		logger.debug("Fetching bill details with calculated balance.");
		final List<BillView> bills = new ArrayList<>();
		BillView view;
		final List<BillVO> list = list(attachmentID);
		for (final BillVO vo : list) {
			view = new BillView();
			view.setAttachmentID(vo.getAttachmentID());
			view.setBillID(vo.getBillID());
			view.setBillNo(vo.getBillNo());
			view.setBillDate(vo.getBillDate());
			view.setDsrName(vo.getDsrName());
			view.setRouteName(vo.getRouteName());
			view.setRetailName(vo.getRetailName());
			view.setNetAmount(vo.getNetAmount());
			view.setBalance(paymentDAO.getBalance(vo.getBillID()));

			bills.add(view);
		}

		return bills;
	}

	public List<BillView> listDetails(final int attachmentID) {

		logger.debug("Fetching bill details with calculated balance.");

		final String query = "select b.billID as billID, b.attachmentID as attachmentID, b.billNo as billNo, b.billDate as billDate,"
				+ " b.dsrName as dsrName, b.routeName as routeName, b.retailName as retailName, b.netAmount as netAmount,"
				+ " sum(b.netAmount - (select sum(amount) as totalAmount from PaymentVO p where p.billID=b.billID)) as balance"
				+ " from BillVO b where b.attachmentID=" + attachmentID
				+ " group by b.billID, b.attachmentID, b.billNo, b.billDate, b.dsrName, b.routeName, b.retailName, b.netAmount";
		final Query q = getSession().createQuery(query);
		q.setResultTransformer(Transformers.aliasToBean(BillView.class));
		final List<BillView> bills = q.list();

		return bills;
	}

	public BillVO get(final int billID) {
		logger.debug("Retrieving bill object with ID:" + billID);
		return getSession().get(BillVO.class, billID);
	}

	public void insert(final Session session, final File file, final AttachmentVO attachmentVO) {

		logger.info("Inserting bill details from:" + file.getName());

		try (FileInputStream excelFile = new FileInputStream(file); Workbook workbook = new XSSFWorkbook(excelFile);) {

			final Sheet datatypeSheet = workbook.getSheetAt(1);

			logger.info("Excel sheet name:" + datatypeSheet.getSheetName());

			final Iterator<Row> rows = datatypeSheet.iterator();

			while (rows.hasNext()) {

				final BillVO vo = new BillVO();
				final Row currentRow = rows.next();

				if (currentRow.getRowNum() == 0) {
					continue;
				} else if (currentRow.getRowNum() >= datatypeSheet.getLastRowNum()) {
					break;
				}

				logger.info("Bill no:" + currentRow.getCell(0).getStringCellValue());

				vo.setAttachmentID(attachmentVO.getAttachmentID());
				vo.setBillNo(currentRow.getCell(0).getStringCellValue());
				vo.setBillDate(currentRow.getCell(1).getDateCellValue());
				vo.setDsrName(currentRow.getCell(2).getStringCellValue());
				vo.setRouteName(currentRow.getCell(3).getStringCellValue());
				vo.setRetailName(currentRow.getCell(4).getStringCellValue());
				vo.setNetAmount(currentRow.getCell(5).getNumericCellValue());

				logger.info("Inserting bill no:" + currentRow.getCell(0).getStringCellValue());
				// save the record
				session.persist(vo);
			}
		} catch (final IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
