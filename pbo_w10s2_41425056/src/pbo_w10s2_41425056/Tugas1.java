

/*
NAMA : Dordiasina Siagian
NIM : 41425056
*/


package pbo_w10s2_41425056;

import java.util.Timer;
import java.util.TimerTask;

public class Tugas1 {
    public static void main(String[] args) {
        final Timer timer = new Timer();

        // Jadwalkan tugas yang berjalan SEKALI, 15 detik dari sekarang
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("time's up!");
                timer.cancel(); // hentikan timer agar program berakhir
            }
        }, 15000);

        System.out.println("Task scheduled.");
    }
}

/*
Cara kerjanya:

Program mencetak teks "Task scheduled." ke layar.

Program memanggil timer.schedule dan memberikan instruksi untuk menunda eksekusi selama 15.000 milidetik (15 detik).

Karena instruksi tersebut, thread utama program akan tertahan (seolah-olah freeze) selama 15 detik ke depan tanpa mencetak apa pun.

Tepat setelah 15 detik berlalu, metode run() di dalam TimerTask akan otomatis dieksekusi. Program akan mencetak teks "time's up!".

Terakhir, timer.cancel() dipanggil agar program berhenti dengan bersih.
*/