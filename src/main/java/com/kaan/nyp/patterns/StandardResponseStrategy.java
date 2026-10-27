package com.kaan.nyp.patterns;

import com.kaan.nyp.models.Incident;

public class StandardResponseStrategy implements IResponseStrategy {
    @Override
    public void executeResponse(Incident incident) {
        System.out.println("Standart inceleme prosedürü uygulanıyor. Kayıt altına alındı: " + incident.getId());
    }
}