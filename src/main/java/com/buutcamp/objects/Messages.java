package com.buutcamp.objects;


import java.sql.Timestamp;

public class Messages {

    private int id;

    private Timestamp createDate;

    private Timestamp updateDate;

    private String titleName;

    private String message;

    private String userName;

    public Messages() {
    }

    public Messages(int id, Timestamp createDate, Timestamp updateDate, String titleName, String message, String userName) {
        this.id = id;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.titleName = titleName;
        this.message = message;
        this.userName = userName;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
