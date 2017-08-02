/**
 *
 */
package com.vaayu.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author sushantsg
 *
 */
@Service
public class MessageService {

	private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

	public String getDesc() {

		logger.debug("getDesc() is executed!");
		return "GM Enterprises description from service!!!";
	}

	public String getTitle(final String name) {

		logger.debug("getTitle() is executed! $name : {}", name);

		if (StringUtils.isEmpty(name)) {
			return "Hello GM Enterprises";
		} else {
			return "Hello " + name;
		}
	}
}
