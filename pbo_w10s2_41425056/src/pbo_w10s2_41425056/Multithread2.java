/*
NAMA : Dordiasina Siagian
NIM : 41425056
*/


package pbo_w10s2_41425056;

public class Multithread2 {
    public static void main(String[] args) {
        System.out.println("================ PROGRAM MULTI-THREADING DENGAN RUNNABLE ================");
        System.out.println("========================================================================\n");

        PrintNameThread2[] threads = {
            new PrintNameThread2("A", 25, 50),
            new PrintNameThread2("B", 20, 80),
            new PrintNameThread2("C", 30, 40),
            new PrintNameThread2("D", 15, 120),
            new PrintNameThread2("E", 10, 150)
        };

        System.out.println("\nMenunggu semua thread selesai...\n");

        try {
            for (PrintNameThread2 thread : threads) {
                thread.waitForCompletion();
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread terinterupsi!");
        }

        System.out.println("\n" + "=".repeat(50));
        System.out.println("STATISTIK EKSEKUSI THREAD");
        System.out.println("=".repeat(50));
        System.out.println("Jumlah thread yang dijalankan: " + threads.length);
        System.out.println("Total semua thread telah menyelesaikan tugasnya!");
        System.out.println("\nPROGRAM SELESAI DENGAN SUKSES!");

        System.out.println("\nAnimasi selesai:");
        for (int i = 0; i < 3; i++) {
            System.out.print("Hore.. ");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }
}
