package pbo_w9s3_41425056;

import java.io.*;

/**
 * Nama : Dordiasina Siagian
 * NIM  : 41425056
 */
public class FileIOExample {

    // Tulis data ke file (mode overwrite: isi lama ditimpa)
    public static void writeToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Alice, 25, Computer Science");
            writer.newLine();
            writer.write("Bob, 30, Mathematics");
            writer.newLine();
            writer.write("Charlie, 22, Physics");
            writer.newLine();
            writer.write("Diana, 28, Chemistry");
            writer.newLine();
            System.out.println("Data berhasil ditulis ke " + filename);
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    // Modifikasi 3: append mode (parameter kedua FileWriter bernilai true)
    public static void appendToFile(String filename) {
        System.out.println("\n=== MENAMBAH DATA (APPEND) ===");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write("Eva, 24, Biology");
            writer.newLine();
            // Usia tidak valid, dipakai untuk menguji NumberFormatException
            writer.write("Frank, dua puluh, Biology");
            writer.newLine();
            System.out.println("Data berhasil ditambahkan ke " + filename);
        } catch (IOException e) {
            System.out.println("Error appending file: " + e.getMessage());
        }
    }

    // Baca file dan tampilkan per baris
    public static void readFromFile(String filename) {
        System.out.println("\n=== MEMBACA FILE ===");
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                System.out.println("Line " + lineNumber + ": " + line);
                lineNumber++;
            }
            System.out.println("File berhasil dibaca");
        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Proses tiap baris lalu tulis ke file baru
    public static void processFile(String inputFile, String outputFile) {
        System.out.println("\n=== MEMPROSES FILE ===");
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            int lineNumber = 0;
            writer.write("NAME, AGE, MAJOR, STATUS");
            writer.newLine();

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                try {
                    // Pecah data berdasarkan koma
                    String[] data = line.split(",");
                    String name = data[0].trim().toUpperCase();
                    int age = Integer.parseInt(data[1].trim());
                    String major = data[2].trim();
                    String status = (age > 25) ? "GRADUATE" : "UNDERGRADUATE";

                    String processedLine = String.format("%s, %d, %s, %s", name, age, major, status);
                    writer.write(processedLine);
                    writer.newLine();
                    System.out.println("Processed: " + processedLine);
                } catch (NumberFormatException e) {
                    // Modifikasi 1: usia bukan angka, baris dilewati
                    System.out.println("Baris " + lineNumber + " dilewati, usia bukan angka: " + line);
                } catch (ArrayIndexOutOfBoundsException e) {
                    // Jumlah kolom kurang dari 3
                    System.out.println("Baris " + lineNumber + " dilewati, kolom tidak lengkap: " + line);
                }
            }
            System.out.println("File berhasil diproses dan disimpan sebagai " + outputFile);
        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
        }
    }

    // Modifikasi 2: hitung rata-rata usia (baris tidak valid diabaikan)
    public static double hitungRataRataUsia(String filename) {
        int total = 0;
        int jumlah = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    total += Integer.parseInt(line.split(",")[1].trim());
                    jumlah++;
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    // Baris tidak valid tidak dihitung
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return (jumlah == 0) ? 0 : (double) total / jumlah;
    }

    public static void main(String[] args) {
        String inputFile = "data.txt";
        String outputFile = "output.txt";

        // Tulis, baca, lalu proses file
        writeToFile(inputFile);
        readFromFile(inputFile);
        processFile(inputFile, outputFile);

        // Modifikasi 3: tambah data tanpa menghapus isi lama
        appendToFile(inputFile);
        readFromFile(inputFile);

        // Modifikasi 1: baris dengan usia bukan angka dilewati, program tidak crash
        processFile(inputFile, outputFile);

        // Modifikasi 2: rata-rata usia
        System.out.println("\n=== RATA-RATA USIA ===");
        System.out.printf("Rata-rata usia: %.2f%n", hitungRataRataUsia(inputFile));
    }

    /*
     * PERTANYAAN DAN JAWABAN
     *
     * 1. Apa keuntungan menggunakan try-with-resources?
     *    Jawab: resource yang mengimplementasikan AutoCloseable (reader dan writer)
     *    ditutup otomatis saat blok selesai, baik berhasil maupun terjadi exception.
     *    Tidak perlu finally dengan close() manual, kode lebih singkat, dan
     *    mengurangi risiko file tetap terbuka (resource leak) atau data
     *    buffer yang belum tertulis.
     *
     * 2. Mengapa perlu menangani FileNotFoundException dan IOException secara terpisah?
     *    Jawab: FileNotFoundException adalah subclass IOException dengan penyebab
     *    spesifik (file tidak ada atau tidak bisa dibuka), sehingga pesan dan
     *    penanganannya bisa lebih jelas, misalnya meminta pengguna memeriksa
     *    nama file. IOException menangani kegagalan I/O lain, misalnya gagal
     *    membaca atau disk bermasalah. Catch subclass harus ditulis lebih dulu
     *    daripada catch superclass-nya.
     *
     * 3. Apa yang terjadi jika data.txt sudah ada sebelum program dijalankan?
     *    Jawab: new FileWriter(filename) membuka file dalam mode overwrite, jadi isi
     *    lama dihapus dan diganti data baru. Agar isi lama dipertahankan,
     *    gunakan new FileWriter(filename, true) seperti pada method appendToFile.
     */
}