package com.mycompany.smartparkingg;

public abstract class Kendaraan {
    private String platNomor;
    private String pemilik;
    private int lamaParkir; // dalam jam

    public Kendaraan(String platNomor, String pemilik, int lamaParkir) {
        this.platNomor = platNomor;
        this.pemilik = pemilik;
        this.lamaParkir = lamaParkir;
    }

    // Getter & Setter
    public String getPlatNomor() { return platNomor; }
    public void setPlatNomor(String platNomor) { this.platNomor = platNomor; }
    public String getPemilik() { return pemilik; }
    public void setPemilik(String pemilik) { this.pemilik = pemilik; }
    public int getLamaParkir() { return lamaParkir; }
    public void setLamaParkir(int lamaParkir) { this.lamaParkir = lamaParkir; }

    // Parkir minimal dihitung 1 jam
    protected int normalisasiJam(int jam) {
        return (jam < 1) ? 1 : jam;
    }

    // Method yang di-override oleh setiap subclass
    public abstract String getJenis();
    public abstract double hitungTarif(int jam);
    public abstract void infoAreaParkir(); 

    
    public double hitungTarif() {
        return hitungTarif(lamaParkir);
    }

    // 2. Parameter jam + diskon (persen)
    public double hitungTarif(int jam, double diskonPersen) {
        double tarif = hitungTarif(jam);
        return tarif - (tarif * diskonPersen / 100);
    }

    // 3. Parameter jam + status member (diskon 10%)
    public double hitungTarif(int jam, boolean member) {
        return member ? hitungTarif(jam, 10.0) : hitungTarif(jam);
    }

    // Method yang di-override untuk menampilkan informasi
    public void tampilkanInfo() {
        System.out.println("--------------------------------------------");
        System.out.printf("%-14s: %s%n", "Jenis", getJenis());
        System.out.printf("%-14s: %s%n", "Plat Nomor", platNomor);
        System.out.printf("%-14s: %s%n", "Pemilik", pemilik);
        System.out.printf("%-14s: %d jam%n", "Lama Parkir", lamaParkir);
    }
}