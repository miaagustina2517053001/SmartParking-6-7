package com.mycompany.smartparkingg;

public class ParkirMotor extends Kendaraan implements DapatDicuci {
    private int kapasitasCC;

    public ParkirMotor(String platNomor, String pemilik, int lamaParkir, int kapasitasCC) {
        super(platNomor, pemilik, lamaParkir);
        this.kapasitasCC = kapasitasCC;
    }

    public int getKapasitasCC() { return kapasitasCC; }

    @Override
    public String getJenis() {
        return "Motor";
    }

    // Jam pertama Rp2.000, jam berikutnya Rp1.000/jam
    @Override
    public double hitungTarif(int jam) {
        int j = normalisasiJam(jam);
        return 2000 + (j - 1) * 1000;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.printf("%-14s: %d cc%n", "Kapasitas", kapasitasCC);
        System.out.printf("%-14s: Rp%,.0f%n", "Tarif", hitungTarif());
    }

    @Override
    public void infoAreaParkir() {
        System.out.println("-> [AREA PARKIR] Motor diarahkan ke Zona B (area roda dua).");
    }

    @Override
    public void cuciKendaraan() {
        System.out.println("-> [CUCI] Motor " + getPlatNomor() + " dicuci: semprot air dan lap bodi.");
    }
}