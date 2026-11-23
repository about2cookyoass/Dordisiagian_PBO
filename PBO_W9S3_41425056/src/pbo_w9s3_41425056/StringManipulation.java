package pbo_w9s3_41425056;

/**
 * Nama : Dordiasina Siagian
 * NIM  : 41425056
 */
public class StringManipulation {

    // Modifikasi 2: hitung jumlah huruf vokal (tidak membedakan huruf besar/kecil)
    public static int hitungVokal(String s) {
        int jumlah = 0;
        for (char c : s.toLowerCase().toCharArray()) {
            if ("aiueo".indexOf(c) != -1) {
                jumlah++;
            }
        }
        return jumlah;
    }

    public static void main(String[] args) {
        String text = " Hello World! Welcome to Java Programming ";

        // Operasi dasar
        System.out.println("=== BASIC STRING OPERATIONS ===");
        System.out.println("Original: '" + text + "'");
        System.out.println("Length: " + text.length());
        System.out.println("Trimmed: '" + text.trim() + "'");
        System.out.println("Uppercase: '" + text.toUpperCase() + "'");
        System.out.println("Lowercase: '" + text.toLowerCase() + "'");

        // Substring dan indexing
        System.out.println("\n=== SUBSTRING & INDEXING ===");
        System.out.println("Character at index 5: '" + text.charAt(5) + "'");
        System.out.println("Substring (7-12): '" + text.substring(7, 12) + "'");
        System.out.println("Index of 'World': " + text.indexOf("World"));
        System.out.println("Last index of 'a': " + text.lastIndexOf("a"));

        // Replacement dan comparison
        System.out.println("\n=== REPLACEMENT & COMPARISON ===");
        System.out.println("Replace 'Java' with 'Python': '" + text.replace("Java", "Python") + "'");
        System.out.println("Contains 'Programming': " + text.contains("Programming"));
        System.out.println("Starts with 'Hello': " + text.startsWith("Hello"));
        System.out.println("Ends with 'Programming': " + text.endsWith("Programming"));

        // Split dan join
        System.out.println("\n=== SPLIT & JOIN ===");
        String[] words = text.trim().split(" ");
        System.out.println("Number of words: " + words.length);
        System.out.print("Words: ");
        for (String word : words) {
            System.out.print("[" + word + "] ");
        }
        System.out.println();
        System.out.println("Joined: " + String.join("-", words));

        // StringBuilder
        System.out.println("\n=== STRING BUILDER ===");
        StringBuilder sb = new StringBuilder();
        sb.append("Java");
        sb.append(" Programming");
        sb.insert(0, "Learn ");
        sb.reverse();
        System.out.println("Reversed: " + sb.toString());

        // Jawaban pertanyaan 3: mengambil kata "Java"
        System.out.println("\n=== JAWABAN PERTANYAAN 3 ===");
        int awal = text.indexOf("Java");
        if (awal != -1) {
            System.out.println("Kata hasil substring: '" + text.substring(awal, awal + 4) + "'");
        } else {
            System.out.println("Kata 'Java' tidak ditemukan");
        }

        // Modifikasi 1: kata dengan panjang lebih dari 4 karakter
        System.out.println("\n=== MODIFIKASI 1: KATA LEBIH DARI 4 KARAKTER ===");
        for (String word : words) {
            if (word.length() > 4) {
                System.out.print("[" + word + "] ");
            }
        }
        System.out.println();

        // Modifikasi 2: jumlah huruf vokal
        System.out.println("\n=== MODIFIKASI 2: JUMLAH VOKAL ===");
        System.out.println("Jumlah vokal: " + hitungVokal(text));
    }

    /*
     * PERTANYAAN DAN JAWABAN
     *
     * 1. Mengapa startsWith("Hello") mengembalikan false?
     *    Jawab: String text diawali satu spasi (" Hello ..."), sehingga karakter
     *    pertamanya spasi, bukan 'H'. Solusinya memakai text.trim().startsWith("Hello")
     *    yang akan menghasilkan true. Hal yang sama berlaku untuk endsWith
     *    karena ada spasi di akhir teks.
     *
     * 2. Apa perbedaan String dan StringBuilder?
     *    Jawab: String bersifat immutable (tidak dapat diubah). Setiap operasi seperti
     *    concat atau replace membuat objek String baru. StringBuilder bersifat mutable,
     *    sehingga append, insert, dan reverse mengubah objek yang sama. Karena itu
     *    StringBuilder lebih efisien untuk memanipulasi teks berulang kali, misalnya
     *    di dalam perulangan.
     *
     * 3. Bagaimana cara mengambil hanya kata "Java"?
     *    Jawab: cari posisinya dengan indexOf lalu potong dengan substring:
     *    int awal = text.indexOf("Java");
     *    text.substring(awal, awal + 4);
     *    Cara lain: ambil elemen array hasil split, yaitu words[4].
     */
}