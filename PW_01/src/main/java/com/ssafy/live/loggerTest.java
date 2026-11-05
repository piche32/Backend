package com.ssafy.live;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class loggerTest {
	
	private static final Logger log = LoggerFactory.getLogger(loggerTest.class);

	public static void main(String[] args) {
		log.trace("trace level: {}, {}", "trace log", 1);
		log.debug("debug level: {}, {}", "debug log", 1);
		log.info("info level: {}, {}", "info log", 1);
		log.warn("warn level: {}, {}", "warn log", 1);
		log.error("error level: {}, {}", "error log", 1);
	}

}
