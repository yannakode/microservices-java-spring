package com.compassuol.sp.challenge.msuser.model.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Event {
    CREATE("CREATE"),
    UPDATE("UPDATE"),
    LOGIN("LOGIN"),
    UPDATE_PASSWORD("UPDATE_PASSWORD");

    private final String value;
}