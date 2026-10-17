package com.mycompany.smartparkingg;

import java.util.Scanner;

public class SmartParkingg {
    // Array bertipe Superclass (menampung Mobil, Motor, Truk)
    static Kendaraan[] daftarKendaraan = new Kendaraan[10];
    static int jumlahKendaraan = 0;
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        isiDataAwal();
        int pilihan;
        do {
            tampilkanMenu();
            pilihan = bacaInt("Pilih menu : ");
            switch (pilihan) {
                case 1: tambahMobil(); break;
                case 2: tambahMotor(); break;
                case 3: tambahTruk(); break;
                case 4: tampilkanSemua(); break;
                case 5: simulasiKeluar(); break;
                case 6: demoOverloading(); break;
                case 7: layananTambahan(); break;
                case 8: tampilkanAreaParkir(); break;
                case 0: System.out.println("Terima kasih telah menggunakan SmartParking!"); break;
                default: System.out.println("Menu tidak tersedia!");
            }
        } while (pilihan != 0);
    }

    static void tampilkanMenu() {
        System.out.println("\n============================================");
        System.out.println("           SMARTPARKING SYSTEM");
        System.out.println("============================================");
        System.out.println("1. Parkir Mobil");
        System.out.println("2. Parkir Motor");
        System.out.println("3. Parkir Truk");
        System.out.println("4. Tampilkan Semua Kendaraan");
        System.out.println("5. Simulasi Kendaraan Keluar (Dynamic Binding)");
        System.out.println("6. Demo Method Overloading hitungTarif()");
        System.out.println("7. Layanan Tambahan (Interface + instanceof)");
        System.out.println("8. Info Area Parkir (Abstract Method)");
        System.out.println("0. Keluar");
        System.out.println("============================================");
    }

    // 5 data awal: 2 Mobil, 2 Motor, 1 Truk
    static void isiDataAwal() {
        tambahKendaraan(new ParkirMobil("B 1234 ABC", "Budi", 3, 4));
        tambahKendaraan(new ParkirMotor("BE 5678 XY", "Siti", 2, 150));
        tambahKendaraan(new ParkirTruk("BE 9012 TR", "Joko", 5, 8.0));
        tambahKendaraan(new ParkirMobil("BE 3456 DE", "Rina", 1, 5));
        tambahKendaraan(new ParkirMotor("BE 7890 GH", "Andi", 4, 125));
    }

    // Parameter bertipe Superclass (upcasting)
    static void tambahKendaraan(Kendaraan k) {
        if (jumlahKendaraan < daftarKendaraan.length) {
            daftarKendaraan[jumlahKendaraan++] = k;
        } else {
            System.out.println("Area parkir penuh!");
        }
    }

    static void tambahMobil() {
        String plat = bacaString("Plat Nomor   : ");
        String pemilik = bacaString("Pemilik      : ");
        int jam = bacaInt("Lama Parkir (jam) : ");
        int pintu = bacaInt("Jumlah Pintu : ");
        tambahKendaraan(new ParkirMobil(plat, pemilik, jam, pintu));
        System.out.println("Mobil berhasil diparkir.");
    }

    static void tambahMotor() {
        String plat = bacaString("Plat Nomor   : ");
        String pemilik = bacaString("Pemilik      : ");
        int jam = bacaInt("Lama Parkir (jam) : ");
        int cc = bacaInt("Kapasitas (cc) : ");
        tambahKendaraan(new ParkirMotor(plat, pemilik, jam, cc));
        System.out.println("Motor berhasil diparkir.");
    }

    static void tambahTruk() {
        String plat = bacaString("Plat Nomor   : ");
        String pemilik = bacaString("Pemilik      : ");
        int jam = bacaInt("Lama Parkir (jam) : ");
        double ton = bacaInt("Tonase (ton) : ");
        tambahKendaraan(new ParkirTruk(plat, pemilik, jam, ton));
        System.out.println("Truk berhasil diparkir.");
    }

    // Runtime Polymorphism: tampilkanInfo() dan hitungTarif() dipilih sesuai objek aslinya
    static void tampilkanSemua() {
        if (jumlahKendaraan == 0) {
            System.out.println("Belum ada kendaraan yang parkir.");
            return;
        }
        double total = 0;
        System.out.println("\n=========== DAFTAR KENDARAAN PARKIR ===========");
        for (int i = 0; i < jumlahKendaraan; i++) {
            System.out.println("Kendaraan ke-" + (i + 1));
            daftarKendaraan[i].tampilkanInfo();
            total += daftarKendaraan[i].hitungTarif();
        }
        System.out.println("--------------------------------------------");
        System.out.printf("Total Pendapatan Parkir : Rp%,.0f%n", total);
    }

    // Menu simulasi: memanggil method berparameter Superclass
    static void simulasiKeluar() {
        if (jumlahKendaraan == 0) {
            System.out.println("Belum ada kendaraan yang parkir.");
            return;
        }
        ringkasanDaftar();
        int no = bacaInt("Pilih nomor kendaraan yang keluar : ");
        if (no < 1 || no > jumlahKendaraan) {
            System.out.println("Nomor tidak valid!");
            return;
        }
        String member = bacaString("Apakah member? (y/n) : ");
        Kendaraan k = daftarKendaraan[no - 1];
        if (member.equalsIgnoreCase("y")) {
            prosesKeluar(k, true);
        } else {
            prosesKeluar(k);
        }
    }

    static void ringkasanDaftar() {
        System.out.println("\nNo  Jenis   Plat Nomor      Pemilik");
        for (int i = 0; i < jumlahKendaraan; i++) {
            Kendaraan k = daftarKendaraan[i];
            System.out.printf("%-3d %-7s %-15s %s%n", (i + 1), k.getJenis(), k.getPlatNomor(), k.getPemilik());
        }
    }

    // Parameter bertipe Superclass: menerima objek Subclass manapun (upcasting)
    static void prosesKeluar(Kendaraan k) {
        System.out.println("\n>>> PROSES KENDARAAN KELUAR <<<");
        k.tampilkanInfo(); // dynamic binding
        System.out.println("--------------------------------------------");
        System.out.printf("Total Bayar    : Rp%,.0f%n", k.hitungTarif());
    }

    // Overloading method prosesKeluar (versi member)
    static void prosesKeluar(Kendaraan k, boolean member) {
        System.out.println("\n>>> PROSES KENDARAAN KELUAR (MEMBER) <<<");
        k.tampilkanInfo(); // dynamic binding
        System.out.println("--------------------------------------------");
        System.out.printf("Tarif Normal   : Rp%,.0f%n", k.hitungTarif());
        System.out.printf("Total Bayar    : Rp%,.0f (diskon member 10%%)%n",
                k.hitungTarif(k.getLamaParkir(), member));
    }

    // Demo Method Overloading (Compile-Time Polymorphism)
    static void demoOverloading() {
        if (jumlahKendaraan == 0) {
            System.out.println("Belum ada kendaraan yang parkir.");
            return;
        }
        ringkasanDaftar();
        int no = bacaInt("Pilih nomor kendaraan : ");
        if (no < 1 || no > jumlahKendaraan) {
            System.out.println("Nomor tidak valid!");
            return;
        }
        int jam = bacaInt("Simulasi lama parkir (jam) : ");
        Kendaraan k = daftarKendaraan[no - 1];
        System.out.println("\nKendaraan : " + k.getJenis() + " - " + k.getPlatNomor());
        System.out.printf("hitungTarif()              : Rp%,.0f%n", k.hitungTarif());
        System.out.printf("hitungTarif(%d)             : Rp%,.0f%n", jam, k.hitungTarif(jam));
        System.out.printf("hitungTarif(%d, 20.0)       : Rp%,.0f (diskon 20%%)%n", jam, k.hitungTarif(jam, 20.0));
        System.out.printf("hitungTarif(%d, true)       : Rp%,.0f (member)%n", jam, k.hitungTarif(jam, true));
    }

    // Memanggil abstract method infoAreaParkir() milik semua Subclass
    static void tampilkanAreaParkir() {
        if (jumlahKendaraan == 0) {
            System.out.println("Belum ada kendaraan yang parkir.");
            return;
        }
        System.out.println("\n=========== INFO AREA PARKIR ===========");
        for (int i = 0; i < jumlahKendaraan; i++) {
            Kendaraan k = daftarKendaraan[i];
            System.out.println((i + 1) + ". " + k.getJenis() + " - " + k.getPlatNomor());
            k.infoAreaParkir(); 
        }
    }

    // Memanggil method interface dengan pengecekan instanceof terlebih dahulu
    static void layananTambahan() {
        if (jumlahKendaraan == 0) {
            System.out.println("Belum ada kendaraan yang parkir.");
            return;
        }
        ringkasanDaftar();
        int no = bacaInt("Pilih nomor kendaraan : ");
        if (no < 1 || no > jumlahKendaraan) {
            System.out.println("Nomor tidak valid!");
            return;
        }
        Kendaraan k = daftarKendaraan[no - 1];
        System.out.println("\n>>> LAYANAN TAMBAHAN: " + k.getJenis() + " - " + k.getPlatNomor());
        k.infoAreaParkir();

        if (k instanceof DapatDicuci) {
            DapatDicuci d = (DapatDicuci) k;
            d.cuciKendaraan();
        } else {
            System.out.println("-> " + k.getJenis() + " ini tidak menyediakan layanan cuci.");
        }

        if (k instanceof DapatDiperiksa) {
            DapatDiperiksa p = (DapatDiperiksa) k;
            p.periksaKendaraan();
        } else {
            System.out.println("-> " + k.getJenis() + " ini tidak memerlukan pemeriksaan khusus.");
        }
    }

    // Helper input
    static int bacaInt(String pesan) {
        while (true) {
            System.out.print(pesan);
            try {
                return Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
            }
        }
    }

    static String bacaString(String pesan) {
        String s;
        do {
            System.out.print(pesan);
            s = input.nextLine().trim();
        } while (s.isEmpty());
        return s;
    }
}