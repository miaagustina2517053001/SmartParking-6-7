package com.mycompany.smartparkingg;

public class ParkirTruk extends Kendaraan implements DapatDiperiksa {
    private double tonase;

    public ParkirTruk(String platNomor, String pemilik, int lamaParkir, double tonase) {
        super(platNomor, pemilik, lamaParkir);
        this.tonase = tonase;
    }

    public double getTonase() { return tonase; }

    @Override
    public String getJenis() {
        return "Truk";
    }

    // Jam pertama Rp10.000, berikutnya Rp6.000/jam, ditambah biaya tonase Rp2.000/ton
    @Override
    public double hitungTarif(int jam) {
        int j = normalisasiJam(jam);
        return 10000 + (j - 1) * 6000 + (tonase * 2000);
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.printf("%-14s: %.1f ton%n", "Tonase", tonase);
        System.out.printf("%-14s: Rp%,.0f%n", "Tarif", hitungTarif());
    }

    @Override
    public void infoAreaParkir() {
        System.out.println("-> [AREA PARKIR] Truk diarahkan ke Zona C (area khusus kendaraan berat).");
    }

    @Override
    public void periksaKendaraan() {
        System.out.println("-> [PERIKSA] Petugas menimbang muatan truk " + getPlatNomor() + " (" + tonase + " ton).");
    }
}