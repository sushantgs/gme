/**
 *
 */
package com.vaayu.database.vo;

import java.sql.Timestamp;

/**
 * @author sushantsg
 *
 */
public class BaseAttachmentVO {

	private int attachmentID;
	private String name;
	private String description;
	private String createdBy;
	private Timestamp createdTimestamp;

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
