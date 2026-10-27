package com.kaan.nyp.models;

public class DDoSIncident extends Incident {
    public DDoSIncident(String id, String description, int severity) {
        super(id, description, severity);
    }

    @Override
    public String getIncidentType() {
        return "DDoS Saldırısı";
    }
}