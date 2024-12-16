package com.spachecor.client;

public enum RequestOption {
    CONNECTION("0"),
    MESSAGE("1"),
    DISCONNECTION("2");
    private final String VALUE;
    RequestOption(String value) {
        this.VALUE = value;
    }
    public String getValue() {
        return VALUE;
    }
}
