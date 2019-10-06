package com.siteminder.worker.service.sendgrid.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class SendGridRequest {
    @JsonProperty
    public final Email from;

    @JsonProperty
    public final List<Content> content;

    @JsonProperty
    public final List<Personalization> personalizations;

    public SendGridRequest(Email from, List<Content> content, List<Personalization> personalizations) {
        this.from = from;
        this.content = content;
        this.personalizations = personalizations;
    }
}
