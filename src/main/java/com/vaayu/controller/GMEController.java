/**
 *
 */
package com.vaayu.controller;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaayu.service.AdminService;
import com.vaayu.service.MessageService;

/**
 * @author sushantsg
 *
 */
@Controller
public class GMEController {

	private final Logger logger = LoggerFactory.getLogger(GMEController.class);
	private final AdminService adminService;
	private final MessageService messageService;

	@Autowired
	public GMEController(final AdminService adminService, final MessageService messageService) {
		this.adminService = adminService;
		this.messageService = messageService;
	}

	@RequestMapping(value = "/gme", method = RequestMethod.GET)
	public String gme(final Map<String, Object> model) {

		logger.debug("In GMEController.gme()...");

		model.put("title", messageService.getTitle(""));
		model.put("msg", messageService.getDesc());

		return "gme";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(final Map<String, Object> model) {
		logger.debug("In GMEController.home()...");
		return "home";
	}

	@RequestMapping(value = "/attachment", method = RequestMethod.GET)
	public String attachment(final Map<String, Object> model) {
		logger.debug("In GMEController.attachment()...");
		return "attachment";
	}

	@RequestMapping(value = "/bill", method = RequestMethod.GET)
	public String bill(final Map<String, Object> model) {
		logger.debug("In GMEController.bill()...");
		return "bill";
	}

	@RequestMapping(value = "/payment", method = RequestMethod.GET)
	public String payment(final Map<String, Object> model) {
		logger.debug("In GMEController.payment()...");
		return "payment";
	}

	@RequestMapping(value = "/getAttachments", method = RequestMethod.GET)
	public @ResponseBody String getAttachments() {

		logger.debug("In GMEController.getAttachments()...");

		final ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			json = mapper.writeValueAsString(adminService.listAttachments());
		} catch (final IOException e) {
			logger.error(e.getMessage(), e);
		}

		return json;
	}

	@RequestMapping(value = "/getBills", method = RequestMethod.GET)
	public @ResponseBody String getBills(@RequestParam("attachmentID") final String attachmentID) {

		logger.debug("In GMEController.getBills()...");

		final ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			json = mapper.writeValueAsString(adminService.listBills(Integer.valueOf(attachmentID)));
		} catch (final IOException e) {
			logger.error(e.getMessage(), e);
		}

		return json;
	}
}
