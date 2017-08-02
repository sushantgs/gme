/**
 *
 */
package com.vaayu.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaayu.database.vo.PaymentVO;
import com.vaayu.service.PaymentService;

/**
 * @author sushantsg
 *
 */
@Controller
public class PaymentController {

	private final Logger logger = LoggerFactory.getLogger(PaymentController.class);
	private final PaymentService paymentService;

	@Autowired
	public PaymentController(final PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@RequestMapping(value = "/updatePayment", method = RequestMethod.POST)
	public @ResponseBody String updatePayment(@RequestBody final PaymentVO paymentVO) {

		logger.debug("In PaymentController.updatePayment()...");
		logger.info("bill ID:" + paymentVO.getBillID());
		paymentVO.setReceivedDate(Timestamp.valueOf(LocalDateTime.now()));
		paymentService.updatePayment(paymentVO);

		return "Successsss...";
	}

	@RequestMapping(value = "/getPayments", method = RequestMethod.GET)
	public @ResponseBody String getPayment(@RequestParam("billID") final String billID) {

		logger.debug("In PaymentController.getPayments()...");

		final ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			json = mapper.writeValueAsString(paymentService.listPayments(Integer.valueOf(billID)));
		} catch (final IOException e) {
			logger.error(e.getMessage(), e);
		}

		return json;
	}
}
