package com.kaan.nyp.patterns;

import com.kaan.nyp.models.Incident;

public interface IResponseStrategy {
    void executeResponse(Incident incident);
}