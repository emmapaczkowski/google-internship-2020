package com.google.sps.classes;

import java.util.Date;

public class Comment {
    private String message;
    private String name;
    private Date time;

    public Comment(String message, String name, Date time) {
        this.message = message;
        this.name = name;
        this.time = time;
    }

    public String getMessage() {
        return this.message;
    }

    public String getName() {
        return this.name;
    }

    public Date getTime() {
        return this.time;
    }

}