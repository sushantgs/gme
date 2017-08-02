/**
 *
 */
package com.vaayu.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaayu.service.AdminService;
import com.vaayu.service.MessageService;

/**
 * @author sushantsg
 *
 */
@Controller
public class BillController {

	private final Logger logger = LoggerFactory.getLogger(BillController.class);
	private final AdminService adminService;
	private final MessageService messageService;

	@Autowired
	public BillController(final AdminService adminService, final MessageService messageService) {
		this.adminService = adminService;
		this.messageService = messageService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(final Map<String, Object> model) {

		logger.debug("index() is executed!");

		model.put("title", messageService.getTitle(""));
		model.put("msg", messageService.getDesc());

		return "index";
	}

	@RequestMapping(value = "/uploadBill", method = RequestMethod.GET)
	public String uploadBill(final Map<String, Object> model) {

		logger.debug("uploadBill() is executed!");

		model.put("title", messageService.getTitle(""));
		model.put("msg", messageService.getDesc());

		return "uploadBill";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(@RequestParam("fileName") final String fileName,
			@RequestParam("fileDescription") final String fileDescription,
			@RequestParam("file") final MultipartFile file) {

		logger.debug("upload() is executed!");

		String message = "";

		if (!file.isEmpty()) {
			try {
				final byte[] bytes = file.getBytes();

				// Creating the directory to store file
				final String rootPath = System.getProperty("catalina.home");
				final File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				final File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getName());
				final BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				adminService.upload(fileName, fileDescription, serverFile);

				logger.info("Server File Location=" + serverFile.getAbsolutePath());

				message = "You successfully uploaded file=" + file.getName();
			} catch (final IOException e) {
				logger.error(e.getMessage(), e);
				message = "You failed to upload " + file.getName() + " => " + e.getMessage();
			}
		} else {
			message = "You failed to upload " + file.getName() + " because the file was empty.";
		}

		// model.put("message", message);

		return "uploadBill";
	}

	@RequestMapping(value = "/billBoot", method = RequestMethod.GET)
	public String billBoot(final Map<String, Object> model) {

		logger.debug("billBoot() is executed!");

		final ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			json = mapper.writeValueAsString(adminService.listBills(1));
		} catch (final IOException e) {
			logger.error(e.getMessage(), e);
		}
		model.put("bills", json);

		return "billBoot";
	}

	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public String report(final Map<String, Object> model) {

		logger.debug("report() is executed!");

		model.put("title", messageService.getTitle(""));
		model.put("msg", messageService.getDesc());

		return "report";
	}

	@RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
	public ModelAndView hello(@PathVariable("name") final String name) {

		logger.debug("hello() is executed - $name {}", name);

		final ModelAndView model = new ModelAndView();
		model.setViewName("index");

		model.addObject("title", messageService.getTitle(name));
		model.addObject("msg", messageService.getDesc());

		return model;
	}
}
