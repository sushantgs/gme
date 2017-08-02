/**
 *
 */
package com.vaayu.database.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaayu.database.vo.AttachmentVO;
import com.vaayu.database.vo.BaseAttachmentVO;

/**
 * @author sushantsg
 *
 */
public class AttachmentDAO extends HibernateDAO {

	private static final Logger logger = LoggerFactory.getLogger(AttachmentDAO.class);

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

	public AttachmentVO get(final int attachmentID) {
		logger.debug("Retrieving attachment object with ID:" + attachmentID);
		return getSession().get(AttachmentVO.class, attachmentID);
	}

	public List<BaseAttachmentVO> list() {

		logger.debug("Fetching all attachments");
		final List<BaseAttachmentVO> attachments = new ArrayList<>();
		BaseAttachmentVO baseVO;

		final List<AttachmentVO> result = getSession().createQuery("FROM AttachmentVO").list();

		for (final AttachmentVO vo : result) {
			baseVO = new BaseAttachmentVO();
			baseVO.setAttachmentID(vo.getAttachmentID());
			baseVO.setName(vo.getName());
			baseVO.setDescription(vo.getDescription());
			baseVO.setCreatedBy(vo.getCreatedBy());
			baseVO.setCreatedTimestamp(vo.getCreatedTimestamp());

			attachments.add(baseVO);
		}

		return attachments;
	}

	public void insert(final String fileName, final String fileDescription, final File file) {

		logger.debug("Uploading the file:" + file.getAbsolutePath());

		final Session session = getSession();
		final Transaction t = session.beginTransaction();

		final AttachmentVO attachmentVO = upload(session, fileName, fileDescription, file);
		billDAO.insert(session, file, attachmentVO);

		t.commit();
	}

	private AttachmentVO upload(final Session session, final String fileName, final String fileDescription,
			final File file) {
		final AttachmentVO vo = new AttachmentVO();
		vo.setName(fileName);
		vo.setDescription(fileDescription);
		byte[] data;
		try {
			data = Files.readAllBytes(file.toPath());
			vo.setAttachment(data);
		} catch (final IOException e) {
			logger.error(e.getMessage(), e);
		}
		vo.setCreatedBy("Sushant");
		vo.setCreatedTimestamp(Timestamp.valueOf(LocalDateTime.now()));

		// save the record
		session.persist(vo);

		return vo;
	}

}
