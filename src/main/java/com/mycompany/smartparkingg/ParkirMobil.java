package com.mycompany.smartparkingg;

public class ParkirMobil extends Kendaraan implements DapatDicuci, DapatDiperiksa {
    private int jumlahPintu;

    public ParkirMobil(String platNomor, String pemilik, int lamaParkir, int jumlahPintu) {
        super(platNomor, pemilik, lamaParkir);
        this.jumlahPintu = jumlahPintu;
    }

    public int getJumlahPintu() { return jumlahPintu; }

    @Override
    public String getJenis() {
        return "Mobil";
    }

    // Jam pertama Rp5.000, jam berikutnya Rp3.000/jam
    @Override
    public double hitungTarif(int jam) {
        int j = normalisasiJam(jam);
        return 5000 + (j - 1) * 3000;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.printf("%-14s: %d pintu%n", "Jumlah Pintu", jumlahPintu);
        System.out.printf("%-14s: Rp%,.0f%n", "Tarif", hitungTarif());
    }

    @Override
    public void infoAreaParkir() {
        System.out.println("-> [AREA PARKIR] Mobil diarahkan ke Zona A (lantai 1 dan 2).");
    }

    @Override
    public void cuciKendaraan() {
        System.out.println("-> [CUCI] Mobil " + getPlatNomor() + " dicuci: sabun, bilas, dan vakum interior.");
    }

    @Override
    public void periksaKendaraan() {
        System.out.println("-> [PERIKSA] Petugas memeriksa bagasi dan " + jumlahPintu + " pintu mobil " + getPlatNomor() + ".");
    }
}