package com.kaan.nyp;

import com.kaan.nyp.core.FileRepository;
import com.kaan.nyp.core.IRepository;
import com.kaan.nyp.core.IncidentManager;
import com.kaan.nyp.models.Incident;
import com.kaan.nyp.patterns.IncidentFactory;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        IRepository repository = new FileRepository();
        IncidentManager manager = new IncidentManager(repository);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Siber Güvenlik Olay Yönetimi ---");
            System.out.println("1. Yeni Olay Bildir");
            System.out.println("2. Olayları Önceliğe Göre Listele ve Müdahale Et");
            System.out.println("3. Çıkış");
            System.out.print("Seçiminiz: ");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                System.out.print("Tür (malware/phishing/ddos): ");
                String type = scanner.nextLine();
                System.out.print("Olay ID (Örn: Olay-01): ");
                String id = scanner.nextLine();
                System.out.print("Detay: ");
                String desc = scanner.nextLine();
                System.out.print("Seviye (1: Düşük, 4: Kritik): ");

                try {
                    int severity = Integer.parseInt(scanner.nextLine());
                    Incident incident = IncidentFactory.createIncident(type, id, desc, severity);
                    manager.reportIncident(incident);
                    System.out.println("Olay başarıyla kaydedildi.");
                } catch (Exception e) {
                    System.out.println("Hata: Geçersiz giriş yaptınız.");
                }

            } else if (choice.equals("2")) {
                System.out.println("\n-- Öncelikli Olay Listesi --");
                List<Incident> sortedIncidents = manager.getPrioritizedIncidents();

                if (sortedIncidents.isEmpty()) {
                    System.out.println("Kayıtlı olay bulunamadı.");
                } else {
                    for (Incident inc : sortedIncidents) {
                        System.out.println(inc.toString());
                        manager.processIncident(inc);
                    }
                }
            } else if (choice.equals("3")) {
                System.out.println("Sistemden çıkılıyor...");
                break;
            } else {
                System.out.println("Geçersiz seçim!");
            }
        }
        scanner.close();
    }
}