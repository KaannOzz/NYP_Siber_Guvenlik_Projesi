package com.kaan.nyp.core;

import com.kaan.nyp.models.Incident;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileRepository implements IRepository {
    private final String filename = "incidents.dat";

    @Override
    public void saveAll(List<Incident> incidents) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(incidents);
        } catch (IOException e) {
            System.out.println("Veri kaydedilemedi: " + e.getMessage());
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Incident> loadAll() {
        File file = new File(filename);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Incident>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}