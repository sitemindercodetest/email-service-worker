package com.siteminder.worker.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siteminder.worker.exception.SendEmailException;
import com.siteminder.worker.model.EmailRequest;
import com.siteminder.worker.service.EmailProviderManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;
import java.io.IOException;
import static org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy.ON_SUCCESS;

@Component
public class EmailSqsListener {
    private final Logger logger = LoggerFactory.getLogger(EmailSqsListener.class);

    @Autowired
    private EmailProviderManager emailProviderManager;

    @SqsListener(value = "${sqs.queueName}", deletionPolicy = ON_SUCCESS)
    public void getMessage(String message) throws IOException, SendEmailException {
        EmailRequest emailRequest = new ObjectMapper().readValue(message, EmailRequest.class);
        emailProviderManager.sendEmail(emailRequest);
    }
}
