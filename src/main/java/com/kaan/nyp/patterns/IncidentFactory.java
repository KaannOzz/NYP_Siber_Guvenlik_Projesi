package com.kaan.nyp.patterns;

import com.kaan.nyp.models.DDoSIncident;
import com.kaan.nyp.models.Incident;
import com.kaan.nyp.models.MalwareIncident;
import com.kaan.nyp.models.PhishingIncident;

public class IncidentFactory {
    public static Incident createIncident(String type, String id, String description, int severity) {
        switch (type.toLowerCase()) {
            case "malware": return new MalwareIncident(id, description, severity);
            case "phishing": return new PhishingIncident(id, description, severity);
            case "ddos": return new DDoSIncident(id, description, severity);
            default: throw new IllegalArgumentException("Bilinmeyen olay türü.");
        }
    }
}