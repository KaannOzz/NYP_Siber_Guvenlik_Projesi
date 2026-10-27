package com.kaan.nyp.core;

import com.kaan.nyp.models.Incident;
import java.util.List;

public interface IRepository {
    void saveAll(List<Incident> incidents);
    List<Incident> loadAll();
}