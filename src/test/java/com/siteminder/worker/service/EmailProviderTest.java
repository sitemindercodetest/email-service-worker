package com.siteminder.worker.service;

import com.siteminder.worker.exception.SendEmailException;
import com.siteminder.worker.model.EmailRequest;
import com.siteminder.worker.service.sendgrid.SendGridProvider;
import kong.unirest.Headers;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.UnirestParsingException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmailProviderTest {
    @Mock
    private HttpResponse<JsonNode> response;

    @Mock
    private Logger logger;

    private ProviderImpl provider;

    class ProviderImpl implements EmailProvider {
        @Override
        public boolean sendMail(EmailRequest request) {
            return false;
        }
    }

    @Before
    public void setUp() {
        provider = new ProviderImpl();
    }

    @Test(expected = SendEmailException.class)
    public void Should_CheckStatus_RaiseExceptionForNon2XXHTTPStatus() throws SendEmailException {
        when(response.getStatus()).thenReturn(402);
        provider.checkStatus(response);
    }

    @Test
    public void Should_CheckStatus_ReturnTrueFor2XXHTTPStatus() throws SendEmailException {
        when(response.getStatus()).thenReturn(202);
        provider.checkStatus(response);
    }
}