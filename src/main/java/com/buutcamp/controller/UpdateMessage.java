package com.buutcamp.controller;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateMessage {

    private String userName;

    private int id;

    @NotNull
    @Size(min=2, max=255)
    private String message;

    public UpdateMessage() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
