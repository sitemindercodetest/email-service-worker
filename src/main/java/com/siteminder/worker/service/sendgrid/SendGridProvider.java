package com.siteminder.worker.service.sendgrid;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.siteminder.worker.exception.SendEmailException;
import com.siteminder.worker.model.EmailRequest;
import com.siteminder.worker.service.EmailProvider;
import com.siteminder.worker.service.sendgrid.model.SendGridRequest;
import com.siteminder.worker.service.sendgrid.transformer.EmailRequestSendGrindRequestTransformer;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SendGridProvider implements EmailProvider {

    private final Logger logger = LoggerFactory.getLogger(SendGridProvider.class);

    @Value("${config.sendgrid.api.key}")
    private String apiKey;

    @Value("${config.sendgrid.api.endpoint}")
    private String apiEndpoint;

    @Override
    public boolean sendMail(EmailRequest request) throws SendEmailException {
        SendGridRequest sendGridRequest = new EmailRequestSendGrindRequestTransformer().transform(request);
        HttpResponse<JsonNode> response = null;
        try {
            response = Unirest.post(apiEndpoint)
                    .header("authorization", "Bearer " + apiKey)
                    .header("content-type", "application/json")
                    .body(convertToJson(sendGridRequest))
                    .asJson();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logger.error("Failed while converting from EmailRequest to SendGridRequest" + request);
        }
        logger.info(String.format("Response body and status - %s %s", response.getBody(), response.getStatus()));
        return checkStatus(response);
    }

    private String convertToJson(SendGridRequest sendGridRequest) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jdk8Module());
        return objectMapper.writeValueAsString(sendGridRequest);
    }
}
