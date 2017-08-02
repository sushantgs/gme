/**
 *
 */
package com;

import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vaayu.database.vo.BaseAttachmentVO;
import com.vaayu.service.AdminService;

/**
 * @author sushantsg
 *
 */
public class TestSpring {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {

		final TestSpring main = new TestSpring();

		final ApplicationContext context = new ClassPathXmlApplicationContext("spring-core-config.xml");
		final BeanFactory factory = context;
		final AdminService service = (AdminService) factory.getBean("adminService");
		main.list(service);

	}

	private void list(final AdminService service) {

		final List<BaseAttachmentVO> attachments = service.listAttachments();
		for (final BaseAttachmentVO attachmentVO : attachments) {

			System.out.println("Attachment ID: " + attachmentVO.getAttachmentID());
			System.out.println("Name: " + attachmentVO.getName());
			System.out.println("Description: " + attachmentVO.getDescription());
			System.out.println("Created By: " + attachmentVO.getCreatedBy());
			System.out.println("Timestamp: " + attachmentVO.getCreatedTimestamp());
		}
	}
}
