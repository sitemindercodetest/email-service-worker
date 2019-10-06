package com.siteminder.worker.service.sendgrid.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Content {
    @JsonProperty
    public final  String type;

    @JsonProperty
    public final  String value;

    public Content(String type, String value) {
        this.type = type;
        this.value = value;
    }
}
