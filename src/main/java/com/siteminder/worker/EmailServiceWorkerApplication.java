package com.siteminder.worker;

import com.siteminder.worker.listener.EmailSqsListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class EmailServiceWorkerApplication {
    @Autowired
    private EmailSqsListener emailSqsListener;

    public static void main(String[] args) {
        SpringApplication.run(EmailServiceWorkerApplication.class, args);
    }
}
