package com.kaan.nyp;

import com.kaan.nyp.core.IRepository;
import com.kaan.nyp.core.IncidentManager;
import com.kaan.nyp.models.DDoSIncident;
import com.kaan.nyp.models.Incident;
import com.kaan.nyp.models.MalwareIncident;
import com.kaan.nyp.models.PhishingIncident;
import com.kaan.nyp.patterns.IncidentFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class IncidentTest {

    private IncidentManager manager;
    private MockRepository mockRepo;

    // Testlerde dosyaya yazmamak için sahte (Mock) veri tabanı (DIP prensibi faydası)
    class MockRepository implements IRepository {
        List<Incident> data = new ArrayList<>();
        public void saveAll(List<Incident> incidents) { data = incidents; }
        public List<Incident> loadAll() { return data; }
    }

    @BeforeEach
    public void setup() {
        mockRepo = new MockRepository();
        manager = new IncidentManager(mockRepo);
    }

    @Test
    public void testMalwareCreation() {
        Incident i = new MalwareIncident("INC-1", "Trojan", 3);
        assertEquals("Zararlı Yazılım", i.getIncidentType());
    }

    @Test
    public void testPhishingCreation() {
        Incident i = new PhishingIncident("INC-2", "Sahte E-posta", 2);
        assertEquals(2, i.getSeverity());
    }

    @Test
    public void testFactoryValidCreation() {
        Incident i = IncidentFactory.createIncident("ddos", "INC-3", "Aşırı Trafik", 4);
        assertTrue(i instanceof DDoSIncident);
    }

    @Test
    public void testFactoryInvalidCreation() {
        assertThrows(IllegalArgumentException.class, () -> {
            IncidentFactory.createIncident("bilinmeyen", "INC-X", "Hata", 1);
        });
    }

    @Test
    public void testIncidentSortingAlgorithm() {
        manager.reportIncident(new PhishingIncident("INC-1", "Düşük", 1));
        manager.reportIncident(new DDoSIncident("INC-2", "Kritik", 4));
        manager.reportIncident(new MalwareIncident("INC-3", "Orta", 2));

        List<Incident> sorted = manager.getPrioritizedIncidents();
        // En yüksek seviye (4) en başta olmalı
        assertEquals("INC-2", sorted.get(0).getId());
        assertEquals("INC-3", sorted.get(1).getId());
    }

    @Test
    public void testManagerSavesToRepository() {
        Incident i = new PhishingIncident("INC-1", "Test", 1);
        manager.reportIncident(i);
        assertEquals(1, mockRepo.loadAll().size());
    }

    @Test
    public void testStrategySelection() {
        Incident critical = new DDoSIncident("INC-1", "Test", 4);
        // Strategy'nin hatasız çalıştığını test eder
        assertDoesNotThrow(() -> manager.processIncident(critical));
    }

    @Test
    public void testEncapsulation() {
        Incident i = new MalwareIncident("ID123", "Detail", 3);
        assertNotNull(i.getTimestamp());
        assertEquals("ID123", i.getId());
    }
}