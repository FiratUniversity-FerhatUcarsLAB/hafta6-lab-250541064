// AD SOYAD: MUHAMMET EREN ALPTEKİN
// ÖĞRENCİ NO: 250541064
// AÇIKLAMA: Bu sistem öğrencilerden vize, final ve ödev notlarını girmesini ister ardından da aldığı notların yüzdelik hesaplamalarını yaparak sınıfı geçme durumlarını, onur listesinde yer alma durumlarını, harf notlarını, bütünleme haklarının olup olmadığını beliritir.

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Kullanıcıdan notları al
        System.out.print("Vize notunu gir: ");
        double vize = scanner.nextDouble();

        System.out.print("Final notunu gir: ");
        double finall = scanner.nextDouble();

        System.out.print("Ödev notunu gir: ");
        double odev = scanner.nextDouble();

        // Ortalama hesaplama
        double ortalama = NotHesaplama.calculateAverage(vize, finall, odev);

        // Sonuçları yazdırma
        System.out.println("Ortalama: " + ortalama);
        System.out.println("Geçme Durumu: " + (NotHesaplama.isPassingGrade(ortalama) ? "GEÇTİ" : "KALDI"));
        System.out.println("Harf Notu: " + NotHesaplama.getLetterGrade(ortalama));
        System.out.println("Onur Listesi: " + (NotHesaplama.isHonorList(ortalama, vize, finall, odev) ? "EVET" : "HAYIR"));
        System.out.println("Bütünleme Hakkı: " + (NotHesaplama.hasRetakeRight(ortalama) ? "VAR" : "YOK"));

        scanner.close();
    }
}

class NotHesaplama {

    // Ortalama hesaplama
    public static double calculateAverage(double vize, double finall, double odev) {
        return vize * 0.30 + finall * 0.40 + odev * 0.30;
    }

    // Geçme durumu
    public static boolean isPassingGrade(double ortalama) {
        return ortalama >= 50;
    }

    // Harf notu
    public static String getLetterGrade(double ortalama) {
        if (ortalama >= 90) {
            return "A";
        } else if (ortalama >= 80) {
            return "B";
        } else if (ortalama >= 70) {
            return "C";
        } else if (ortalama >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    // Onur listesi kontrolü
    public static boolean isHonorList(double ortalama, double vize, double finall, double odev) {
        return ortalama >= 85 && vize >= 70 && finall >= 70 && odev >= 70;
    }

    // Bütünleme hakkı
    public static boolean hasRetakeRight(double ortalama) {
        return ortalama >= 40 && ortalama < 50;
    }
}
