package com.siteminder.worker.service;

import com.siteminder.worker.exception.SendEmailException;
import com.siteminder.worker.model.EmailRequest;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;

public interface EmailProvider {

    boolean sendMail(EmailRequest request) throws SendEmailException;

    default boolean checkStatus(HttpResponse<JsonNode> response) throws SendEmailException {
        if (!(response.getStatus() >= 200 && response.getStatus() < 300)) {
            throw new SendEmailException(
                    String.format("Error while sending email - %s %s", response.getBody(), response.getStatus())
            );
        }
        return true;
    }
}
