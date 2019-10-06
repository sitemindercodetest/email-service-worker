package com.siteminder.worker.service.sendgrid.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Email {
    @JsonProperty
    public final String email;

    public Email(String email) {
        this.email = email;
    }
}
