package com.kaan.nyp.models;

public class PhishingIncident extends Incident {
    public PhishingIncident(String id, String description, int severity) {
        super(id, description, severity);
    }

    @Override
    public String getIncidentType() {
        return "Oltalama (Phishing)";
    }
}