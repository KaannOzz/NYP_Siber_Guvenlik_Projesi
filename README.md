# Akıllı Siber Güvenlik Olay Yönetimi Projesi

Bu proje, Nesneye Yönelik Programlama (NYP) dersi proje ödevi kapsamında geliştirilmiş bir siber güvenlik vaka yönetimi uygulamasıdır. Konsol üzerinden çalışan bu sistem, siber güvenlik olaylarını sınıflandırır, önceliklendirir ve müdahale süreçlerini yönetir.

## Proje Hakkında
Sistem, "Kritik > Yüksek > Orta > Düşük" kuralına göre siber güvenlik vakalarını (Malware, Phishing, DDoS) önceliklendirir ve her vakaya uygun müdahale stratejisini uygular. Kayıtlar `incidents.dat` dosyasında kalıcı olarak saklanır. Proje, tek kişilik bir ekip tarafından baştan sona tasarlanmış ve kodlanmıştır.

## Kullanılan Teknolojiler ve Mimari
- **Dil:** Java (JDK 17+)
- **Proje Yönetimi:** Maven
- **Veri Kalıcılığı:** Nesne Serileştirme (Object Serialization - `.dat` dosyası)
- **Birim Testi:** JUnit 5

## Nesneye Yönelik Programlama (OOP) Özellikleri
- **Kapsülleme (Encapsulation):** `Incident` sınıfındaki tüm alanlar `private` tutulmuş ve getter metotları ile erişime açılmıştır.
- **Kalıtım (Inheritance):** `MalwareIncident`, `PhishingIncident` ve `DDoSIncident` sınıfları soyut `Incident` sınıfından türetilmiştir.
- **Soyutlama (Abstraction):** Olay türlerini belirten `getIncidentType()` metodu `Incident` sınıfında soyut (abstract) tanımlanmış ve alt sınıflarda doldurulmuştur. Aynı şekilde veri erişimi `IRepository` arayüzü ile soyutlanmıştır.
- **Çok Biçimlilik (Polymorphism):** İşleyici metotlar, alt sınıfları üst sınıf (`Incident`) referansıyla kabul edip dinamik olarak ilgili alt sınıfın davranışını sergiler.

## SOLID Prensipleri
1. **S (Single Responsibility):** Arayüz (`Main`), veri kalıcılığı (`FileRepository`) ve iş mantığı (`IncidentManager`) tamamen ayrı sınıflara bölünmüştür.
2. **O (Open/Closed):** Sisteme yeni bir olay türü ekleneceğinde mevcut kodlar değiştirilmeden (örneğin `RansomwareIncident` eklenerek) genişletilebilir yapıdadır.
3. **L (Liskov Substitution):** Alt sınıflar (örn. `MalwareIncident`), üst sınıfın (`Incident`) kullanıldığı her yerde sorunsuz ve hatasız çalışır.
4. **D (Dependency Inversion):** `IncidentManager` sınıfı doğrudan somut `FileRepository` sınıfına değil, `IRepository` arayüzüne bağlıdır (Soyutlamaya bağımlılık).

## Tasarım Kalıpları
1. **Factory Pattern (Üretici Kalıbı):** Kullanıcıdan alınan türe göre ilgili siber güvenlik olayı nesnesinin karmaşıklığı gizlenerek dinamik ve güvenli bir şekilde üretilmesini sağlar (`IncidentFactory`).
2. **Strategy Pattern (Strateji Kalıbı):** Olayın kritiklik seviyesine göre farklı müdahale prosedürlerinin (Kritik veya Standart) if/else bloklarına boğulmadan, çalışma zamanında seçilip uygulanmasını sağlar (`IResponseStrategy`).

## Kurulum ve Çalıştırma
1. Projeyi bilgisayarınıza klonlayın veya zip olarak indirin.
2. IntelliJ IDEA (veya Eclipse/VS Code) ile projeyi bir `Maven` projesi olarak açın.
3. IDE üzerinden Maven bağımlılıklarını güncelleyerek JUnit kütüphanesinin indiğinden emin olun.
4. `src/main/java/com/kaan/nyp/Main.java` dosyasını çalıştırarak uygulamayı başlatın.
5. Birim testleri çalıştırmak için `src/test/java/.../IncidentTest.java` dosyasını çalıştırabilirsiniz.