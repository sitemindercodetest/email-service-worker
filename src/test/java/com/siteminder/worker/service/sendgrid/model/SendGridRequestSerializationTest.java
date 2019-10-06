package com.siteminder.worker.service.sendgrid.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.siteminder.worker.EmailRequestBuilder;
import com.siteminder.worker.service.sendgrid.transformer.EmailRequestSendGrindRequestTransformer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class SendGridRequestSerializationTest {
    private String jsonValue;
    private final ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {
        mapper.registerModule(new Jdk8Module());
    }

    @Test
    public void Should_Not_Include_CC() throws JsonProcessingException {
        SendGridRequest emailRequest = new EmailRequestSendGrindRequestTransformer().transform(new EmailRequestBuilder()
                .withCC(null)
                .withBcc(null)
                .build());
        jsonValue = mapper.writeValueAsString(emailRequest);

        assertFalse(jsonValue.contains("cc"));
    }

    @Test
    public void Should_Not_Include_Bcc() throws JsonProcessingException {
        SendGridRequest emailRequest = new EmailRequestSendGrindRequestTransformer().transform(new EmailRequestBuilder()
                .withCC(null)
                .withBcc(null)
                .build());
        jsonValue = mapper.writeValueAsString(emailRequest);

        assertFalse(jsonValue.contains("bcc"));
    }
}
