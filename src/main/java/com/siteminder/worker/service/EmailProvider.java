package com.siteminder.worker.service;

import com.siteminder.worker.model.EmailRequest;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import org.slf4j.Logger;

public interface EmailProvider {

    boolean sendMail(EmailRequest request);

    default boolean checkStatus(HttpResponse<JsonNode> response, Logger logger) {
        if (response.getStatus() >= 300) {
            logger.error(String.format("Error while sending email - %s %s", response.getBody(), response.getStatus()));
            throw new RuntimeException("Error while sending email");
        }
        return true;
    }
}
