package com.siteminder.worker.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.siteminder.worker.model.EmailRequest;
import com.siteminder.worker.service.sendgrid.SendGridProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailProviderManager {

    @Autowired
    private MailGunProvider mailGunProvider;

    @Autowired
    private SendGridProvider sendGridProvider;

    @HystrixCommand(fallbackMethod = "sendEmailSecondary")
    public boolean sendEmail(EmailRequest req) {
        return mailGunProvider.sendMail(req);
    }

    public boolean sendEmailSecondary(EmailRequest req) {
        return sendGridProvider.sendMail(req);
    }
}
