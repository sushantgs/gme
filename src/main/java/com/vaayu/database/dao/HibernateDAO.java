/**
 *
 */
package com.vaayu.database.dao;

import java.io.File;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sushantsg
 *
 */
public abstract class HibernateDAO {

	private static final Logger logger = LoggerFactory.getLogger(HibernateDAO.class);
	private final Session session;

	HibernateDAO() {
		logger.info("Initializing hibernate...");

		final Configuration cfg = new Configuration();
		final File cfgFile = new File("src/main/resources/hibernate.cfg.xml");
		cfg.configure(cfgFile);

		final SessionFactory factory = cfg.buildSessionFactory();
		session = factory.openSession();
	}

	Session getSession() {
		return session;
	}
}
