// AD SOYAD: MUHAMMET EREN ALPTEKİN
// ÖĞRENCİ NO: 250541064
// AÇIKLAMA: Bu program bir restoran sipariş sistemi olarak kullanılmasının yanı sıra indirimleri hesaplar ve bahşiş önerisinde bulunur.

import java.util.Scanner;

public class RestoranSiparis {

    // --- Fiyat Metotları (Aynı Kalacak) ---
    public static int getMainDishPrice(int secim) {
        switch (secim) {
            case 1: return 85;
            case 2: return 120;
            case 3: return 110;
            case 4: return 65;
            default: return 0;
        }
    }

    public static int getAppetizerPrice(int secim) {
        switch (secim) {
            case 1: return 25;
            case 2: return 45;
            case 3: return 55;
            default: return 0;
        }
    }

    public static int getDrinkPrice(int secim) {
        switch (secim) {
            case 1: return 15;
            case 2: return 12;
            case 3: return 35;
            case 4: return 25;
            default: return 0;
        }
    }

    public static int getDessertPrice(int secim) {
        switch (secim) {
            case 1: return 65;
            case 2: return 55;
            case 3: return 35;
            default: return 0;
        }
    }

    public static boolean isComboOrder(boolean anaVar, boolean icecekVar, boolean tatliVar) {
        return anaVar && icecekVar && tatliVar;
    }

    public static boolean isHappyHour(int saat) {
        return (saat >= 14 && saat <= 16);
    }

    public static double calculateDiscount(double tutar, boolean combo, boolean ogrenci, int saat) {
        double indirim = 0;
        if (combo) {
            indirim += 0.10;
            if (ogrenci) {
                indirim += 0.05;
                if (isHappyHour(saat)) {
                    indirim += 0.2;
                }
            }
        } else {
            if (ogrenci) {
                indirim += 0.05;
                if (isHappyHour(saat)) {
                    indirim += 0.05;
                }
            } else if (isHappyHour(saat)) {
                indirim += 0.05;
            }
        }
        return tutar - (tutar * indirim);
    }

    public static double calculateServiceTip(double tutar) {
        return tutar * 0.10;
    }

    // --- Sayı Kontrol Metodu ---
    public static int secimAl(Scanner input, int min, int max) {
        int secim;
        while (true) {
            while (!input.hasNextInt()) {
                System.out.println("Hatalı giriş! Lütfen bir sayı giriniz.");
                input.next();
            }
            secim = input.nextInt();
            if (secim >= min && secim <= max) {
                break;
            } else {
                System.out.println("Hatalı seçim! Lütfen " + min + " ile " + max + " arasında bir sayı giriniz: ");
            }
        }
        return secim;
    }

    // --- YENİ: Evet/Hayır Kontrol Metodu ---
    public static boolean evetHayirAl(Scanner input) {
        while (true) {
            // Girilen yazıyı al, boşlukları sil ve küçük harfe çevir
            String cevap = input.nextLine().trim().toLowerCase();

            if (cevap.equals("evet")) {
                return true; // Öğrenci
            } else if (cevap.equals("hayır")) {
                return false; // Öğrenci değil
            } else {
                System.out.print("Hatalı giriş! Lütfen sadece 'evet' veya 'hayır' yazınız: ");
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // 1. Ana Yemek
        System.out.println("Ana Yemekler: 1-Izgara Tavuk, 2-Adana Kebap, 3-Levrek, 4-Mantı");
        System.out.print("Seçiminiz: ");
        int anaSecim = secimAl(input, 1, 4);
        int anaYemek = getMainDishPrice(anaSecim);
        boolean anaVar = anaYemek > 0;

        // 2. Başlangıç
        System.out.println("\nBaşlangıçlar: 1-Çorba, 2-Humus, 3-Sigara Böreği (0: İstemiyorum)");
        System.out.print("Seçiminiz: ");
        int basSecim = secimAl(input, 0, 3);
        int baslangic = getAppetizerPrice(basSecim);

        // 3. İçecek
        System.out.println("\nİçecekler: 1-Kola, 2-Ayran, 3-Taze Meyve Suyu, 4-Limonata (0: İstemiyorum)");
        System.out.print("Seçiminiz: ");
        int icecekSecim = secimAl(input, 0, 4);
        int icecek = getDrinkPrice(icecekSecim);
        boolean icecekVar = icecek > 0;

        // 4. Tatlı
        System.out.println("\nTatlılar: 1-Künefe, 2-Baklava, 3-Sütlaç (0: İstemiyorum)");
        System.out.print("Seçiminiz: ");
        int tatliSecim = secimAl(input, 0, 3);
        int tatli = getDessertPrice(tatliSecim);
        boolean tatliVar = tatli > 0;

        input.nextLine(); // Buffer temizleme (önceki sayı girişinden kalan "enter"ı siler)

        // 5. Öğrenci Bilgisi (KONTROLLÜ)
        System.out.print("\nÖğrenci misiniz? (evet/hayır): ");
        // Burası artık yeni metoda gidiyor
        boolean ogrenci = evetHayirAl(input);

        // 6. Saat Bilgisi
        System.out.print("Saat kaç? (0-23): ");
        int saat = secimAl(input, 0, 23);

        // --- Hesaplama ---
        double toplam = anaYemek + baslangic + icecek + tatli;
        boolean combo = isComboOrder(anaVar, icecekVar, tatliVar);

        // Not: Kodundaki "sa1at" yazım hatasını düzelttim:
        double indirimliTutar = calculateDiscount(toplam, combo, ogrenci, saat);

        double bahsis = calculateServiceTip(indirimliTutar);

        System.out.println("----------------------------");
        System.out.println("Toplam: " + toplam + " TL");
        System.out.println("İndirimli Tutar: " + indirimliTutar + " TL");
        System.out.println("Bahşiş Önerisi: " + bahsis + " TL");
    }
}
