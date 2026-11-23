/*
NAMA : Dordiasina Siagian
NIM : 41425056
*/

package pbo_w10s2_41425056;

class PrintNameThread extends Thread {
    private int printCount;

    PrintNameThread(String name, int printCount) {
        super(name);
        this.printCount = printCount;
        start();
    }

    @Override
    public void run() {
        String name = getName();
        System.out.println("Thread '" + name + "' memulai eksekusi...");
        for (int i = 0; i < printCount; i++) {
            System.out.print(name);
            try {
                Thread.sleep((long) (Math.random() * 100));
            } catch (InterruptedException e) {
                System.out.println("\nThread '" + name + "' terinterupsi!");
                return;
            }
        }
        System.out.println("\nThread '" + name + "' selesai!");
    }
}

public class Pbo_w10s2_41425056 {

    public static void main(String[] args) {
        // ===== Bagian 1: PrintNameThread (extends Thread) =====
        System.out.println("=== PROGRAM MULTI-THREADING (Thread) ===");
        System.out.println("Memulai 5 thread yang akan mencetak karakter...\n");

        PrintNameThread[] threads = {
            new PrintNameThread("A", 10),
            new PrintNameThread("B", 10),
            new PrintNameThread("C", 10),
            new PrintNameThread("D", 10),
            new PrintNameThread("E", 10)
        };

        for (PrintNameThread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println("Main thread terinterupsi!");
            }
        }
        System.out.println("\n=== SEMUA THREAD (Bagian 1) SELESAI ===\n");

        // ===== Bagian 2: PrintNameThread2 (implements Runnable) =====
        System.out.println("=== PROGRAM MULTI-THREADING (Runnable) ===");

        PrintNameThread2[] runnables = {
            new PrintNameThread2("A", 50, 100),
            new PrintNameThread2("B", 50, 150),
            new PrintNameThread2("C", 50, 200)
        };

        for (PrintNameThread2 r : runnables) {
            try {
                r.waitForCompletion();
            } catch (InterruptedException e) {
                System.out.println("Main thread terinterupsi!");
            }
        }
        System.out.println("\n=== SEMUA THREAD (Bagian 2) SELESAI ===");
        System.out.println("Program selesai dijalankan!");
    }
}