package com.siteminder.worker.service.sendgrid.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Optional;

public class Personalization {
    @JsonProperty
    public final List<Email> to;

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    public final Optional<List<Email>> cc;

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    public final Optional<List<Email>> bcc;

    @JsonProperty
    public final  String subject;

    public Personalization(List<Email> to, Optional<List<Email>> cc, Optional<List<Email>> bcc, String subject) {
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.subject = subject;
    }

}

