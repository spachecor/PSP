package com.spacrod.servidorchat.client;

/**
 * Enum que define los tipos de mensaje que pueden recibirse
 * @author Selene
 * @version 1.0
 */
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