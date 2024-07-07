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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
