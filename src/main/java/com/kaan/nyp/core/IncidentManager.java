package com.kaan.nyp.core;

import com.kaan.nyp.models.Incident;
import com.kaan.nyp.patterns.CriticalResponseStrategy;
import com.kaan.nyp.patterns.IResponseStrategy;
import com.kaan.nyp.patterns.StandardResponseStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class IncidentManager {
    private IRepository repository;
    private List<Incident> activeIncidents;

    public IncidentManager(IRepository repository) {
        this.repository = repository;
        this.activeIncidents = repository.loadAll();
    }

    public void reportIncident(Incident incident) {
        activeIncidents.add(incident);
        repository.saveAll(activeIncidents);
    }

    // Yönergedeki Algoritma Kriteri: Öncelik Sıralaması
    public List<Incident> getPrioritizedIncidents() {
        List<Incident> sorted = new ArrayList<>(activeIncidents);
        sorted.sort(Comparator.comparingInt(Incident::getSeverity).reversed());
        return sorted;
    }

    public void processIncident(Incident incident) {
        IResponseStrategy strategy = (incident.getSeverity() >= 3)
                ? new CriticalResponseStrategy()
                : new StandardResponseStrategy();
        strategy.executeResponse(incident);
    }
}