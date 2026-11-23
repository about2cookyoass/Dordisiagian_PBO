package pbo_w9s2_41425056;

import java.io.*;
public class UpperCaseConverter {
    public static void main(String[] args) {
    try (BufferedReader br = new BufferedReader( new FileReader("input.txt"));
         BufferedWriter bw = new BufferedWriter( new FileWriter("output.txt"))){
        
        String line;
        while ((line = br.readLine())  != null) {
            bw.write(line.toUpperCase());
            bw.newLine();
        }
        System.out.println("konversi Selesai!");
    } catch (IOException e) {
        System.out.println("Terjadi error: " + e.getMessage());
    }
    
    }
    
}
