package com.siteminder.worker.service.sendgrid.transformer;

import com.siteminder.worker.EmailRequestBuilder;
import com.siteminder.worker.model.EmailRequest;
import com.siteminder.worker.service.sendgrid.model.SendGridRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class EmailRequestSendGrindRequestTransformerTest {

    private EmailRequestSendGrindRequestTransformer transformer;
    private EmailRequest emailRequest;
    private SendGridRequest sendGridRequest;

    @Before
    public void setUp() {
        transformer = new EmailRequestSendGrindRequestTransformer();
    }

    @Test
    public void Should_Transform_Subject() {
        emailRequest = new EmailRequestBuilder().build();
        sendGridRequest = transformer.transform(emailRequest);

        assertEquals(emailRequest.subject, sendGridRequest.personalizations.get(0).subject);
    }

    @Test
    public void Should_Transform_Content() {
        emailRequest = new EmailRequestBuilder().build();
        sendGridRequest = transformer.transform(emailRequest);

        assertEquals(emailRequest.body, sendGridRequest.content.get(0).value);
    }

    @Test
    public void Should_Transform_To() {
        emailRequest = new EmailRequestBuilder().build();
        sendGridRequest = transformer.transform(emailRequest);

        List<String> to = sendGridRequest.personalizations.get(0).to
                .stream()
                .map(item -> item.email)
                .collect(Collectors.toList());
        assertEquals(emailRequest.to, to);
    }

    @Test
    public void Should_Transform_From() {
        emailRequest = new EmailRequestBuilder().build();
        sendGridRequest = transformer.transform(emailRequest);

        assertEquals(emailRequest.from, sendGridRequest.from.email);
    }

    @Test
    public void Should_Transform_CC() {
        emailRequest = new EmailRequestBuilder().build();
        sendGridRequest = transformer.transform(emailRequest);

        Optional<List<String>> optionalCC = sendGridRequest.personalizations.get(0).cc
                .map(item -> item.stream()
                        .map(e -> e.email)
                        .collect(Collectors.toList()));
        assertEquals(emailRequest.cc, optionalCC);
    }

    @Test
    public void Should_Transform_Bcc() {
        emailRequest = new EmailRequestBuilder().build();
        sendGridRequest = transformer.transform(emailRequest);

        Optional<List<String>> optionalBcc = sendGridRequest.personalizations.get(0).bcc
                .map(item -> item.stream()
                        .map(e -> e.email)
                        .collect(Collectors.toList()));
        assertEquals(emailRequest.bcc, optionalBcc);
    }

    @Test
    public void Should_Transform_HandleNullCCAndBcc() {
        emailRequest = new EmailRequestBuilder().withBcc(null).withCC(null).build();
        sendGridRequest = transformer.transform(emailRequest);

        assertEquals(emailRequest.subject, sendGridRequest.personalizations.get(0).subject);
    }
}