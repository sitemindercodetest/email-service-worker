package com.siteminder.worker.service.sendgrid.transformer;

import com.siteminder.worker.model.EmailRequest;
import com.siteminder.worker.service.sendgrid.model.Content;
import com.siteminder.worker.service.sendgrid.model.Email;
import com.siteminder.worker.service.sendgrid.model.Personalization;
import com.siteminder.worker.service.sendgrid.model.SendGridRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmailRequestSendGrindRequestTransformer {
    public SendGridRequest transform(EmailRequest request) {
        List<Email> to = request.to.stream().map(Email::new).collect(Collectors.toList());
        Optional<List<Email>> cc = request.cc.map(item -> item.stream().map(Email::new).collect(Collectors.toList()));
        Optional<List<Email>> bcc = request.bcc.map(item -> item.stream().map(Email::new).collect(Collectors.toList()));
        Personalization personalisation = new Personalization(to, cc, bcc, request.subject);
        Email from = new Email(request.from);
        List<Personalization> personalizations = Arrays.asList(personalisation);
        List<Content> contents = Arrays.asList(new Content("text/plain", request.body));

        return new SendGridRequest(from, contents, personalizations);
    }
}
