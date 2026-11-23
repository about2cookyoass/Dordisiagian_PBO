package pbo_w9s3_41425056;

import java.io.*;
import java.util.*;

/**
 * Nama : Dordiasina Siagian
 * NIM  : 41425056
 */
public class TextProcessor {

    // Buat file sumber contoh
    public static void createSampleFile(String filename) {
        String[] sampleText = {
            "Java programming is fun and powerful.",
            "Streams and Files are important concepts.",
            "Exception handling makes code robust.",
            "String manipulation is frequently used.",
            "Practice makes perfect in programming."
        };

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String line : sampleText) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Sample file created: " + filename);
        } catch (IOException e) {
            System.out.println("Error creating sample file: " + e.getMessage());
        }
    }

    // Tugas mudah: kalimat dihitung dari jumlah tanda titik pada satu baris
    public static int hitungKalimat(String line) {
        int jumlah = 0;
        for (char c : line.toCharArray()) {
            if (c == '.') {
                jumlah++;
            }
        }
        return jumlah;
    }

    // Tugas sedang: cari semua kata dengan frekuensi tertinggi (urut abjad)
    public static List<String> kataTerbanyak(Map<String, Integer> wordFrequency) {
        int maxFreq = Collections.max(wordFrequency.values());
        List<String> top = new ArrayList<>();
        for (Map.Entry<String, Integer> e : wordFrequency.entrySet()) {
            if (e.getValue() == maxFreq) {
                top.add(e.getKey());
            }
        }
        Collections.sort(top);
        return top;
    }

    // Proses file: uppercase, statistik, lalu tulis report
    public static void processTextFile(String inputFile, String outputFile, String reportFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
             BufferedWriter reportWriter = new BufferedWriter(new FileWriter(reportFile))) {

            String line;
            int totalLines = 0;
            int totalWords = 0;
            int totalChars = 0;
            int totalSentences = 0;
            Map<Character, Integer> charFrequency = new HashMap<>();
            Map<String, Integer> wordFrequency = new HashMap<>();

            while ((line = reader.readLine()) != null) {
                totalLines++;

                // Ubah baris ke huruf besar
                writer.write(line.toUpperCase());
                writer.newLine();

                // Statistik kata, karakter, dan kalimat
                String[] words = line.split("\\s+");
                totalWords += words.length;
                totalChars += line.length();
                totalSentences += hitungKalimat(line);

                // Frekuensi kata (huruf kecil, tanda baca dibuang)
                for (String w : words) {
                    String clean = w.toLowerCase().replaceAll("[^a-z]", "");
                    if (!clean.isEmpty()) {
                        wordFrequency.put(clean, wordFrequency.getOrDefault(clean, 0) + 1);
                    }
                }

                // Frekuensi karakter huruf
                for (char c : line.toLowerCase().toCharArray()) {
                    if (Character.isLetter(c)) {
                        charFrequency.put(c, charFrequency.getOrDefault(c, 0) + 1);
                    }
                }
            }

            // Tulis report
            reportWriter.write("=== TEXT PROCESSING REPORT ===");
            reportWriter.newLine();
            reportWriter.write("Total Lines: " + totalLines);
            reportWriter.newLine();
            reportWriter.write("Total Words: " + totalWords);
            reportWriter.newLine();
            reportWriter.write("Total Characters: " + totalChars);
            reportWriter.newLine();
            reportWriter.write("Average Words per Line: " + ((double) totalWords / totalLines));
            reportWriter.newLine();
            reportWriter.write("Total Sentences: " + totalSentences);
            reportWriter.newLine();
            reportWriter.write("Most Frequent Word(s): " + kataTerbanyak(wordFrequency)
                    + " (" + Collections.max(wordFrequency.values()) + "x)");
            reportWriter.newLine();
            reportWriter.newLine();
            reportWriter.write("CHARACTER FREQUENCY:");
            reportWriter.newLine();

            // Urutkan karakter dari frekuensi terbesar
            List<Map.Entry<Character, Integer>> sortedChars = new ArrayList<>(charFrequency.entrySet());
            sortedChars.sort((a, b) -> b.getValue().compareTo(a.getValue()));

            for (Map.Entry<Character, Integer> entry : sortedChars) {
                reportWriter.write(entry.getKey() + ": " + entry.getValue());
                reportWriter.newLine();
            }

            System.out.println("File processing completed!");
            System.out.println("  - Input: " + inputFile);
            System.out.println("  - Output: " + outputFile);
            System.out.println("  - Report: " + reportFile);

        } catch (IOException e) {
            System.out.println("Error processing files: " + e.getMessage());
        }
    }

    // Tampilkan isi report ke layar
    public static void displayReport(String reportFile) {
        System.out.println("\n=== PROCESSING REPORT ===");
        try (BufferedReader reader = new BufferedReader(new FileReader(reportFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading report: " + e.getMessage());
        }
    }

    // Tugas sulit: buat file CSV contoh
    public static void createSampleCsv(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("nama,umur,nilai");
            writer.newLine();
            writer.write("Andi,20,85.5");
            writer.newLine();
            writer.write("Budi,22,90");
            writer.newLine();
            writer.write("Citra,21,78");
            writer.newLine();
            writer.write("Dedi,23,88.5");
            writer.newLine();
            writer.write("Eka,20,95");
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error creating CSV: " + e.getMessage());
        }
    }

    // Tugas sulit: statistik tiap kolom numerik (count, min, max, mean, median)
    public static void summarizeCsv(String filename) {
        System.out.println("\n=== CSV SUMMARY STATISTICS ===");
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String[] header = reader.readLine().split(",");
            List<List<Double>> columns = new ArrayList<>();
            boolean[] numeric = new boolean[header.length];
            Arrays.fill(numeric, true);
            for (int i = 0; i < header.length; i++) {
                columns.add(new ArrayList<>());
            }

            String line;
            while ((line = reader.readLine()) != null) {
                String[] cells = line.split(",");
                for (int i = 0; i < header.length && i < cells.length; i++) {
                    try {
                        columns.get(i).add(Double.parseDouble(cells[i].trim()));
                    } catch (NumberFormatException e) {
                        numeric[i] = false; // kolom teks, tidak dihitung
                    }
                }
            }

            for (int i = 0; i < header.length; i++) {
                if (!numeric[i] || columns.get(i).isEmpty()) {
                    continue;
                }
                List<Double> v = columns.get(i);
                Collections.sort(v);
                double sum = 0;
                for (double d : v) {
                    sum += d;
                }
                int n = v.size();
                double median = (n % 2 == 1) ? v.get(n / 2) : (v.get(n / 2 - 1) + v.get(n / 2)) / 2;
                System.out.printf(Locale.US, "%s -> count: %d, min: %.2f, max: %.2f, mean: %.2f, median: %.2f%n",
                        header[i], n, v.get(0), v.get(n - 1), sum / n, median);
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String sourceFile = "source.txt";
        String processedFile = "processed.txt";
        String reportFile = "report.txt";
        String csvFile = "nilai.csv";

        // Buat file sumber, proses, lalu tampilkan report
        createSampleFile(sourceFile);
        processTextFile(sourceFile, processedFile, reportFile);
        displayReport(reportFile);

        // Tugas sulit: ringkasan statistik CSV
        createSampleCsv(csvFile);
        summarizeCsv(csvFile);
    }

    /*
     * PERTANYAAN DAN JAWABAN
     *
     * 1. Bagaimana cara kerja HashMap untuk menghitung frekuensi karakter?
     *    Jawab: HashMap menyimpan pasangan key dan value. Key adalah karakter dan
     *    value adalah jumlah kemunculannya. Untuk setiap karakter dipanggil
     *    getOrDefault(c, 0) + 1 lalu disimpan lagi dengan put. Jika karakter
     *    belum ada, nilai awalnya 0 sehingga menjadi 1. Pencarian key berjalan
     *    cepat karena HashMap memakai hash code.
     *
     * 2. Mengapa memakai \\s+ untuk split kata, bukan spasi biasa?
     *    Jawab: \\s+ berarti satu atau lebih whitespace (spasi, tab, newline).
     *    Jika dipakai split(" ") dan ada dua spasi berturut-turut, hasilnya
     *    memuat elemen kosong sehingga jumlah kata salah. \\s+ memperlakukan
     *    spasi ganda atau tab sebagai satu pemisah.
     *
     * 3. Apa keuntungan BufferedReader dan BufferedWriter?
     *    Jawab: keduanya memakai buffer di memori sehingga data dibaca atau ditulis
     *    per blok, bukan per karakter ke disk. Jumlah operasi I/O berkurang dan
     *    program lebih cepat. BufferedReader juga menyediakan readLine(), dan
     *    BufferedWriter menyediakan newLine().
     */
}