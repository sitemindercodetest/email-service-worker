package com.siteminder.worker.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendEmailException extends Throwable {
    private final Logger logger = LoggerFactory.getLogger(SendEmailException.class);

    public SendEmailException(String message) {
        logger.error(message);
    }
}
