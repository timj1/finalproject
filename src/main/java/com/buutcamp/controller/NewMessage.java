package com.buutcamp.controller;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NewMessage {

    private String userName;

    @NotNull
    @Size(min=3, max=45)
    private String title;

    @NotNull
    @Size(min=2, max=255)
    private String message;

    public NewMessage() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
