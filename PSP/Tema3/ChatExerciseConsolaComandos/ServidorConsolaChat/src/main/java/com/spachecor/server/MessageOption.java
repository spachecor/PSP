package com.spachecor.server;

public enum MessageOption {
    USERS_LIST("0"),
    MESSAGE("1");
    private String value;
    MessageOption(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
