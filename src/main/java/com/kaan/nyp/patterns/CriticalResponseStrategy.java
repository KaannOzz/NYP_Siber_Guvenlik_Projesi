package com.kaan.nyp.patterns;

import com.kaan.nyp.models.Incident;

public class CriticalResponseStrategy implements IResponseStrategy {
    @Override
    public void executeResponse(Incident incident) {
        System.out.println("ACİL MÜDAHALE BAŞLATILDI! Sunucular izole ediliyor... Olay: " + incident.getId());
    }
}