package com.smileydev.recipez.entities;

import java.util.Date;

public abstract class BasicInfo {
    private String name;
    private Date dateCreated;
    private Date lastUpdate;

    public BasicInfo(String name, Date dateCreated, Date lastUpdate) {
        this.name = name;
        this.dateCreated = dateCreated;
        this.lastUpdate = lastUpdate;
    }
}
