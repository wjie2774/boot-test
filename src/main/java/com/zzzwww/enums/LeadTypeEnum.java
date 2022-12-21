package com.zzzwww.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;


@Getter
@RequiredArgsConstructor
public enum LeadTypeEnum {
    House("house"),
    Lot("lot");

    private final String value;

    public static LeadTypeEnum getByValue(String value) {
        if (Objects.isNull(value)) {
            return null;
        }
        for (LeadTypeEnum item : LeadTypeEnum.values()) {
            if (value.equalsIgnoreCase(item.getValue())) {
                return item;
            }
        }
        throw new RuntimeException("Invalid value");
    }
}
