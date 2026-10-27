package com.kaan.nyp.models;

import java.io.Serializable;
import java.util.Date;

public abstract class Incident implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String description;
    private int severity; // 1: Düşük, 2: Orta, 3: Yüksek, 4: Kritik
    private Date timestamp;

    public Incident(String id, String description, int severity) {
        this.id = id;
        this.description = description;
        this.severity = severity;
        this.timestamp = new Date();
    }

    public String getId() { return id; }
    public String getDescription() { return description; }
    public int getSeverity() { return severity; }
    public Date getTimestamp() { return timestamp; }

    public abstract String getIncidentType();

    @Override
    public String toString() {
        return "[" + getIncidentType() + "] ID: " + id + " | Seviye: " + severity + " | Detay: " + description;
    }
}