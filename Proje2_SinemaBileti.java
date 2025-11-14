// AD SOYAD: MUHAMMET EREN ALPTEKİN
// ÖĞRENCİ NO: 250541064
// AÇIKLAMA: Bu sistem bilet bilgilendirmesi yapıp, indirimli bilet avantajına sahip olan insanların indirimini hesaplar, gün, saat, film ve ücret hakkında bilgilendirme yapar.

public class CinemaTicketSystem {

    // 1. Hafta sonu mu kontrol et
    public static boolean isWeekend(int gun) {
        return (gun == 6 || gun == 7); // 6=Cumartesi, 7=Pazar
    }

    // 2. Matine mi kontrol et
    public static boolean isMatinee(int saat) {
        return saat < 12; // 12:00 öncesi matine
    }

    // 3. Temel fiyat hesapla
    public static double calculateBasePrice(int gun, int saat) {
        if (isWeekend(gun)) {
            return isMatinee(saat) ? 55.0 : 85.0;
        } else {
            return isMatinee(saat) ? 45.0 : 65.0;
        }
    }

    // 4. İndirim hesapla
    public static double calculateDiscount(int yas, int meslek, int gun) {
        double discount = 0.0;

        if (yas >= 65) {
            discount = 0.30;
        } else if (yas < 12) {
            discount = 0.25;
        } else {
            if (meslek == 1) { // Öğrenci
                if (gun >= 1 && gun <= 4) {
                    discount = 0.20;
                } else {
                    discount = 0.15;
                }
            } else if (meslek == 2) { // Öğretmen
                if (gun == 3) {
                    discount = 0.35;
                }
            }
        }
        return discount;
    }

    // 5. Film formatı ekstra ücret
    public static double getFormatExtra(int filmTuru) {
        if (filmTuru == 2) return 25.0;   // 3D
        else if (filmTuru == 3) return 35.0; // IMAX
        else if (filmTuru == 4) return 50.0; // 4DX
        else return 0.0; // 2D
    }

    // 6. Toplam fiyat hesapla
    public static double calculateFinalPrice(int gun, int saat, int yas, int meslek, int filmTuru) {
        double basePrice = calculateBasePrice(gun, saat);
        double discountRate = calculateDiscount(yas, meslek, gun);
        double formatExtra = getFormatExtra(filmTuru);

        double discountedPrice = basePrice * (1 - discountRate);
        return discountedPrice + formatExtra;
    }

    // 7. Bilet bilgisi oluştur
    public static long generateTicketInfo(int gun, int saat, int yas, int meslek, int filmTuru) {
        double finalPrice = calculateFinalPrice(gun, saat, yas, meslek, filmTuru);

        String gunStr;
        if (gun == 1) gunStr = "Pazartesi";
        else if (gun == 2) gunStr = "Salı";
        else if (gun == 3) gunStr = "Çarşamba";
        else if (gun == 4) gunStr = "Perşembe";
        else if (gun == 5) gunStr = "Cuma";
        else if (gun == 6) gunStr = "Cumartesi";
        else if (gun == 7) gunStr = "Pazar";
        else gunStr = "Bilinmeyen";

        String meslekStr;
        if (meslek == 1) meslekStr = "Öğrenci";
        else if (meslek == 2) meslekStr = "Öğretmen";
        else meslekStr = "Diğer";

        String formatStr;
        if (filmTuru == 2) formatStr = "3D";
        else if (filmTuru == 3) formatStr = "IMAX";
        else if (filmTuru == 4) formatStr = "4DX";
        else formatStr = "2D";
        
               System.out.println("=======BİLET BİLGİSİ=======");
               System.out.println("Gün: " + gunStr);
               System.out.println("saat: " + saat + ":00");
               System.out.println("Yaş: " + yas);
               System.out.println("Meslek: " + meslekStr);
               System.out.println("Film Türü: " + formatStr);
               System.out.println("Toplam fiyat: " + finalPrice + "TL");
        return 0;
    }

    // Test için main metodu
    public static void main(String[] args) {
        System.out.println(generateTicketInfo(5, 14, 20, 1, 2));
        // Örnek: Cuma günü, saat 14:00, 20 yaşında öğrenci, 3D film
    }
}

