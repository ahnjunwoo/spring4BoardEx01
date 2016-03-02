package org.zerock.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zerock.controller.ReplyController;

public class LogBackTest {
	private static Logger logger = LoggerFactory.getLogger(ReplyController.class);
	public static void main(String[] args) {
		logger.trace("trace2");
        logger.debug("debug2");
        logger.info("info2");
        logger.warn("warn2");
        logger.error("error2");
	}

}
