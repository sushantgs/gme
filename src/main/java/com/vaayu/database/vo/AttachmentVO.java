/**
 *
 */
package com.vaayu.database.vo;

import java.sql.Timestamp;
import java.util.Set;

/**
 * @author sushantsg
 *
 */
public class AttachmentVO {

	private int attachmentID;
	private String name;
	private String description;
	private byte[] attachment;
	private String createdBy;
	private Timestamp createdTimestamp;
	private Set<BillVO> bills;

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @return the attachment
	 */
	public byte[] getAttachment() {
		return attachment;
	}

	/**
	 * @param attachment
	 *            the attachment to set
	 */
	public void setAttachment(final byte[] attachment) {
		this.attachment = attachment;
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

	/**
	 * @return the bills
	 */
	public Set<BillVO> getBills() {
		return bills;
	}

	/**
	 * @param bills
	 *            the bills to set
	 */
	public void setBills(final Set<BillVO> bills) {
		this.bills = bills;
	}

}
