package com.compassuol.sp.challenge.msuser.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class EventNotification {
    @JsonProperty("event")
    private String event;

    @JsonProperty("email")
    private String email;
    @JsonProperty("date")
    private String date;
}
