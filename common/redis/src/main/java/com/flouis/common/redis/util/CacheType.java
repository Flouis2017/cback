package com.flouis.common.redis.util;

public enum CacheType {

    CAPTCHA("captcha:"),

    ACCOUNT("account:"),

    ORDER("order:"),

    TRADE("trade:"),

    POSI("posi:"),
    STOCK("stock:");

    private String type;

    CacheType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }


}
