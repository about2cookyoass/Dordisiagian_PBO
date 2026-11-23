/*
NAMA : Dordiasina Siagian
NIM : 41425056
*/



package pbo_w10s2_41425056;

public class TestTimer {
    public static void main(String[] args) {
        System.out.println("================ MEMULAI PROGRAM PENGINGAT PINTAR ================");
        System.out.println("==================================================================\n");

        System.out.println("1. PENGINGAT MAKAN SEDERHANA");
        Pengingat pengingatMakan = new Pengingat(3);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n2. PENGINGAT BELAJAR (Multiple)");
        Pengingat pengingatBelajar = new Pengingat(2, "Istirahatkan mata dan minum air!", 3);

        System.out.println("\n3. PENGINGAT OLAHRAGA");
        Pengingat pengingatOlahraga = new Pengingat(4, "Waktunya stretching! Jangan duduk terlalu lama", 2);

        System.out.println("\nMenunggu semua pengingat selesai...");
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n\nLAPORAN AKHIR PROGRAM");
        System.out.println("=====================");
        pengingatMakan.tampilkanStatus();

        System.out.println("\n4. CONTOH PEMBATALAN MANUAL");
        Pengingat pengingatBatal = new Pengingat(1, "Pesan ini akan dibatalkan", 5);

        try {
            Thread.sleep(3000);
            pengingatBatal.batalkanPengingat();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n================ PROGRAM TESTER SELESAI ================");
    }
}