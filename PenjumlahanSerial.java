import java.util.Random;

public class PenjumlahanSerial {

    public static void main(String[] args) {

        // Menentukan ukuran array besar
        int ukuranArray = 1000000;

        // Membuat array
        int[] array = new int[ukuranArray];

        // Mengisi array dengan angka acak
        Random rand = new Random();
        for (int i = 0; i < ukuranArray; i++) {
            array[i] = rand.nextInt(100); // angka acak 0 - 99
        }

        // Mulai menghitung waktu eksekusi
        long waktuMulai = System.nanoTime();

        // Penjumlahan serial
        long total = 0;
        for (int i = 0; i < array.length; i++) {
            total += array[i];
        }

        // Selesai menghitung waktu eksekusi
        long waktuSelesai = System.nanoTime();

        // Menghitung waktu eksekusi dalam milidetik
        double waktuEksekusi = (waktuSelesai - waktuMulai) / 1000000.0;

        // Menampilkan hasil
        System.out.println("Ukuran Array      : " + ukuranArray);
        System.out.println("Total Penjumlahan : " + total);
        System.out.println("Waktu Eksekusi    : " + waktuEksekusi + " ms");
    }
}