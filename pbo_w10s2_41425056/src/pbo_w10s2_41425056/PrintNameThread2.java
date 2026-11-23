/*
NAMA : Dordiasina Siagian
NIM : 41425056
*/


package pbo_w10s2_41425056;

class PrintNameThread2 implements Runnable {
    private Thread thread;
    private String symbol;
    private int printCount;
    private int delay;

    PrintNameThread2(String symbol, int printCount, int delay) {
        this.symbol = symbol;
        this.printCount = printCount;
        this.delay = delay;
        thread = new Thread(this, "Thread-" + symbol);
        System.out.println("Membuat " + thread.getName());
        thread.start();
    }

    @Override
    public void run() {
        System.out.println(thread.getName() + " memulai eksekusi...");
        for (int i = 1; i <= printCount; i++) {
            System.out.print(symbol);
            if (i % 10 == 0) {
                System.out.print("[" + i + "]");
            }
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                System.out.println("\n" + thread.getName() + " terinterupsi!");
                return;
            }
        }
        System.out.println("\n" + thread.getName() + " selesai mencetak " + printCount + " karakter!");
    }

    public void waitForCompletion() throws InterruptedException {
        thread.join();
    }
}