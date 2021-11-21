package com.example.a20180100_20180109_20180064.notification;

public class Notification {
    String title,description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Notification(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
