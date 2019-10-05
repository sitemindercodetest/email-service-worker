package com.siteminder.worker.service;

import com.siteminder.worker.model.EmailRequest;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.MultipartBody;
import kong.unirest.Unirest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MailGunProvider implements EmailProvider {

    private final Logger logger = LoggerFactory.getLogger(MailGunProvider.class);

    @Value("${config.mailgun.api.key}")
    private String apiKey;

    @Value("${config.mailgun.api.endpoint}")
    private String apiEndpoint;

    @Override
    public boolean sendMail(EmailRequest request) {
        HttpResponse<JsonNode> response = getMultipartBody(request).asJson();
        logger.info(String.format("Response body and status - %s %s", response.getBody(), response.getStatus()));
        return checkStatus(response, logger);
    }

    private MultipartBody getMultipartBody(EmailRequest request) {
        MultipartBody postReq = Unirest.post(apiEndpoint)
                .basicAuth("api", apiKey)
                .field("from", request.from)
                .field("to", request.to)
                .field("subject", request.subject)
                .field("text", request.body);
        request.cc.ifPresent(cc -> postReq.field("cc", cc));
        request.bcc.ifPresent(bcc -> postReq.field("bcc", bcc));
        return postReq;
    }
}
