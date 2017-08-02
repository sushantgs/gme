/**
 *
 */
package com;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.transform.Transformers;

import com.vaayu.database.vo.AttachmentVO;
import com.vaayu.database.vo.BillVO;
import com.vaayu.database.vo.BillView;

/**
 * @author sushantsg
 *
 */
public class TestHibernate {

	public static void main(final String[] args) {

		final TestHibernate main = new TestHibernate();
		// creating configuration object
		final Configuration cfg = new Configuration();
		final File cfgFile = new File("src/main/resources/hibernate.cfg.xml");
		cfg.configure(cfgFile);

		// creating seession factory object
		final SessionFactory factory = cfg.buildSessionFactory();

		// creating session object
		final Session session = factory.openSession();

		// creating transaction object
		final Transaction t = session.beginTransaction();

		// main.insert(session);
		// main.list(session);
		main.listDetails(session);

		t.commit();// transaction is committed
		session.close();
	}

	private void insert(final Session session) {

		final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		final BillVO vo = new BillVO();

		vo.setAttachmentID(1);
		vo.setBillNo("123");

		try {
			vo.setBillDate(formatter.parse("06/07/2017"));
		} catch (final ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vo.setDsrName("test dsr name");
		vo.setRouteName("test route name");
		vo.setRetailName("test retail name");
		vo.setNetAmount(123.34);

		// save the record
		session.persist(vo);

		System.out.println("successfully saved");
	}

	private List<BillVO> list(final Session session) {

		final List<AttachmentVO> attachments = session.createQuery("from AttachmentVO").list();
		for (final AttachmentVO attachmentVO : attachments) {

			System.out.println("Attachment ID: " + attachmentVO.getAttachmentID());
			System.out.println("Name: " + attachmentVO.getName());
			System.out.println("Description: " + attachmentVO.getDescription());
			System.out.println("Created By: " + attachmentVO.getCreatedBy());
			System.out.println("Timestamp: " + attachmentVO.getCreatedTimestamp());
			// final List<BillVO> bills = attachmentVO.getBills();
			final Set<BillVO> bills = attachmentVO.getBills();

			for (final BillVO vo : bills) {
				System.out.println("Bill ID: " + vo.getBillID());
				System.out.println("Attachment ID: " + vo.getAttachmentID());
				System.out.println("Bill Number: " + vo.getBillNo());
				System.out.println("DSR: " + vo.getDsrName());
				System.out.println("Retail: " + vo.getRetailName());
				System.out.println("Route: " + vo.getRouteName());
				System.out.println("Amount: " + vo.getNetAmount());
				System.out.println("Date: " + vo.getBillDate());
			}
		}

		return null;
	}

	private void listDetails(final Session session) {

		final String query = "select b.billID as billID, b.attachmentID as attachmentID, b.billNo as billNo, b.billDate as billDate,"
				+ " b.dsrName as dsrName, b.routeName as routeName, b.retailName as retailName, b.netAmount as netAmount,"
				+ " p.amount as amount, sum(b.netAmount - p.amount) as balance from BillVO b left join PaymentVO p on b.billID = p.billID "
				+ "where b.attachmentID=" + 1
				+ "group by b.billID, b.attachmentID, b.billNo, b.billDate, b.dsrName, b.routeName, b.retailName, b.netAmount, p.amount";
		final Query q = session.createQuery(query);
		q.setResultTransformer(Transformers.aliasToBean(BillView.class));
		final List<BillView> bills = q.list();

		for (final BillView vo : bills) {
			System.out.println("Bill ID: " + vo.getBillID());
			System.out.println("Attachment ID: " + vo.getAttachmentID());
			System.out.println("Bill Number: " + vo.getBillNo());
			System.out.println("DSR: " + vo.getDsrName());
			System.out.println("Retail: " + vo.getRetailName());
			System.out.println("Route: " + vo.getRouteName());
			System.out.println("Amount: " + vo.getNetAmount());
			System.out.println("Date: " + vo.getBillDate());
		}
	}
}
