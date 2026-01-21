# Gold-Management

**Gold Calculator**, Türkiye merkezli olarak tasarlanmış, JavaFX tabanlı bir masaüstü uygulamasıdır.  
Uygulama, kullanıcıların altın yatırımlarını takip etmesine ve **güncel 24 ayar gram altın fiyatını** canlı bir API üzerinden görüntülemesine olanak tanır.

Bu proje eğitim ve pratik amaçlı geliştirilmiştir.

---

## ✨ Özellikler

- 📈 Güncel 24 ayar gram altın fiyatı (USD bazlı)
- ➕ Altın yatırımı ekleme
- 💱 Çoklu para birimi desteği (₺, $, €)
- 🧵 Arka planda API çağrıları (`Task` kullanımı)
- 🖥️ JavaFX masaüstü arayüzü
- 🔐 Güvenli API anahtarı kullanımı (environment variable)

---

## 🛠️ Kullanılan Teknolojiler

- Java 17+
- JavaFX
- HTTPURLConnection
- JSON (org.json)
- GoldAPI

---

## 🌍 Bölgesel Bilgi

Bu uygulama **Türkiye odaklı** geliştirilmiştir.

- Arayüz dili Türkçedir
- Altın yatırımı Türkiye’de yaygın kullanım göz önünde bulundurularak tasarlanmıştır
- Güncel altın fiyatı **USD bazlı** olarak alınır ve bilgilendirme amaçlı gösterilir

---

## 🔑 API Yapılandırması (Önemli)

Bu uygulama, canlı altın fiyatlarını almak için **GoldAPI** kullanır.

⚠️ **API anahtarı güvenlik sebebiyle bu repoda bulunmamaktadır.**

Uygulamayı çalıştırmadan önce API anahtarını bir **environment variable** olarak tanımlamanız gerekir.

## 📁 Proje Yapısı
├── GoldCalculator.java # Ana JavaFX uygulaması
├── GoldRateService.java # Güncel altın fiyatını alan API servisi
├── ConvertingGrams.java #  Gelecekte gram dönüşüm ve hesaplama özellikleri için ayrılmış ek sahne
└── stylesheet.css # Uygulama stil dosyası

#### English

# Gold-Management

**Gold Calculator** is a JavaFX-based desktop application designed with a Turkey-centered perspective.  
The application allows users to track their gold investments and view the **current 24K gram gold price** fetched from a live API.

This project was developed for educational and practice purposes.

---

## ✨ Features

- 📈 Live 24K gram gold price (USD-based)
- ➕ Add gold investments
- 💱 Multi-currency support (₺, $, €)
- 🧵 Background API calls using `Task`
- 🖥️ JavaFX desktop interface
- 🔐 Secure API key usage via environment variables

---

## 🛠️ Technologies Used

- Java 17+
- JavaFX
- HTTPURLConnection
- JSON (org.json)
- GoldAPI

---

## 🌍 Regional Information

This application is developed with a **Turkey-focused** approach.

- The user interface language is Turkish
- The design takes into account the widespread use of gold investments in Turkey
- The current gold price is retrieved **in USD** and displayed for informational purposes

---

## 🔑 API Configuration (Important)

This application uses **GoldAPI** to fetch live gold prices.

⚠️ **The API key is NOT included in this repository for security reasons.**

Before running the application, you must define the API key as an **environment variable**.

---

## 📁 Project Structure
├── GoldCalculator.java # Main JavaFX application
├── GoldRateService.java # API service that retrieves the current gold price
├── ConvertingGrams.java # Scene reserved for future gram conversion and calculation features
└── stylesheet.css # Application style sheet

