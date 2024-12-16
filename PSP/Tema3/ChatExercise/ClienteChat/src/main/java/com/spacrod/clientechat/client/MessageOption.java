package com.spacrod.clientechat.client;

/**
 * Enum que define si, como mensaje, nos llega un mensaje para otro usuario o una actualizacion de la lista de usuarios
 * disponibles.
 * @author Selene
 * @version 1.0
 */
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
