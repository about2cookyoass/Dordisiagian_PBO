/*
NAMA : Dordiasina Siagian
NIM : 41425056
*/


package pbo_w10s2_41425056;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Tugas2 {
    public static void main(String[] args) {
        final Timer timer = new Timer();
        System.out.println("Program started");

        TimerTask task = new TimerTask() {
            private int tick = 0;      // jumlah eksekusi sejauh ini
            private final int MAX = 6; // 3 pasang (started + finished)

            @Override
            public void run() {
                // tick genap = started, tick ganjil = finished
                if (tick % 2 == 0) {
                    System.out.println("Time started at:" + new Date());
                } else {
                    System.out.println("Time finished at:" + new Date());
                }
                tick++;

                if (tick >= MAX) {
                    timer.cancel();
                    System.out.println("Program cancelled");
                }
            }
        };

        // delay awal 0 ms, lalu diulang tiap 5000 ms (5 detik)
        timer.scheduleAtFixedRate(task, 0, 5000);
    }
}

/*
Cara kerjanya:

Program mencetak "Program started" lalu membuat objek Timer.

Di dalam TimerTask, terdapat variabel tick yang berfungsi menghitung jumlah kemunculan, dan batas maksimal MAX yaitu 6 kali. Variabel ini digunakan agar output bergantian antara "Time started" dan "Time finished".

Metode scheduleAtFixedRate dipanggil dengan parameter waktu tunda awal 0 detik, dan pengulangan setiap 5.000 milidetik (5 detik).

Karena waktu tunda awal adalah 0, program akan langsung mencetak "Time started at:" beserta waktu saat itu (menggunakan new Date()).

Lima detik kemudian, task berjalan lagi. Variabel tick bertambah menjadi 1 (ganjil), sehingga program mencetak "Time finished at:" dengan waktu yang baru.

Siklus ini berlanjut bergantian setiap 5 detik. Setelah task berjalan sebanyak 6 kali (3 pasang started dan finished), program akan memanggil timer.cancel() untuk menghentikan pengulangan, lalu mencetak "Program cancelled" sebagai tanda program selesai.
*/