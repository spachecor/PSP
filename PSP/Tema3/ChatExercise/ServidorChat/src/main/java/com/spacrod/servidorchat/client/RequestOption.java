package com.spacrod.servidorchat.client;

public enum RequestOption {
    CONNECTION(0),
    MESSAGE(1),
    DISCONNECTION(2);
    private final int VALUE;
    RequestOption(int value) {
        this.VALUE = value;
    }
    public int getValue() {
        return VALUE;
    }
}