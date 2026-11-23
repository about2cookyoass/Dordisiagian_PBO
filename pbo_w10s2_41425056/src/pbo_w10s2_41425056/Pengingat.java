

/*
NAMA : Dordiasina Siagian
NIM : 41425056
*/



package pbo_w10s2_41425056;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Pengingat {
    private Timer timer;
    private String pesan;
    private int interval;
    private int repetisi;
    private int repetisiSaatIni;
    private SimpleDateFormat formatter;

    public Pengingat(int detik, String pesan, int repetisi) {
        this.timer = new Timer();
        this.pesan = pesan;
        this.interval = detik * 1000;
        this.repetisi = repetisi;
        this.repetisiSaatIni = 0;
        this.formatter = new SimpleDateFormat("HH:mm:ss");

        System.out.println("PENGINGAT DIBUAT");
        System.out.println("Pesan: " + pesan);
        System.out.println("Interval: " + detik + " detik");
        System.out.println("Repetisi: " + (repetisi == -1 ? "Tak terbatas" : repetisi) + " kali");
        System.out.println("Waktu mulai: " + getWaktuSekarang());
        System.out.println("----------------------------------------");

        timer.schedule(new TugasPengingat(), interval, interval);
    }

    public Pengingat(int detik, String pesan) {
        this(detik, pesan, 1);
    }

    public Pengingat(int detik) {
        this(detik, "Waktunya makan!", 1);
    }

    class TugasPengingat extends TimerTask {
        @Override
        public void run() {
            repetisiSaatIni++;
            System.out.println("[" + getWaktuSekarang() + "] " + pesan);
            System.out.println("(Pengingat ke-" + repetisiSaatIni + ")");

            if (repetisi != -1 && repetisiSaatIni >= repetisi) {
                System.out.println("Pengingat selesai! Total: " + repetisiSaatIni + " kali");
                timer.cancel();
                tampilkanPesanPenutup();
            }
            System.out.println("----------------------------------------");
        }
    }

    private String getWaktuSekarang() {
        return formatter.format(new Date());
    }

    private void tampilkanPesanPenutup() {
        System.out.println("\nSEMUA PENGINGAT TELAH SELESAI!");
        System.out.println("Sistem pengingat ditutup pada: " + getWaktuSekarang());
        System.out.println("Terima kasih telah menggunakan Pengingat Pintar!");
    }

    public void batalkanPengingat() {
        if (timer != null) {
            timer.cancel();
            System.out.println("Pengingat dibatalkan manual pada: " + getWaktuSekarang());
        }
    }

    public void tampilkanStatus() {
        System.out.println("\nSTATUS PENGINGAT");
        System.out.println("Pesan: " + pesan);
        System.out.println("Repetisi: " + repetisiSaatIni + "/" + (repetisi == -1 ? "Tak terbatas" : repetisi));
        System.out.println("Status: " + (timer != null ? "Aktif" : "Non-aktif"));
    }
}
