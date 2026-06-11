import java.util.Random;

class SumThread extends Thread {
    private int[] data;
    private int start;
    private int end;
    private long partialSum;

    public SumThread(int[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    public void run() {
        partialSum = 0;
        for (int i = start; i < end; i++) {
            partialSum += data[i];
        }
    }

    public long getPartialSum() {
        return partialSum;
    }
}

public class PenjumlahanParalel {
    public static void main(String[] args) {
        int size = 10000000; // 10 juta elemen
        int numThreads = 4;  // jumlah thread

        int[] array = new int[size];
        Random rand = new Random();

        // Mengisi array dengan angka acak 1 - 100
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(100) + 1;
        }

        SumThread[] threads = new SumThread[numThreads];
        int chunkSize = size / numThreads;

        long startTime = System.nanoTime();

        // Membagi array dan menjalankan thread
        for (int i = 0; i < numThreads; i++) {
            int start = i * chunkSize;
            int end = (i == numThreads - 1) ? size : start + chunkSize;

            threads[i] = new SumThread(array, start, end);
            threads[i].start();
        }

        long totalSum = 0;

        // Menunggu semua thread selesai
        try {
            for (int i = 0; i < numThreads; i++) {
                threads[i].join();
                totalSum += threads[i].getPartialSum();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.nanoTime();

        double executionTime = (endTime - startTime) / 1_000_000.0;

        System.out.println("=== PENJUMLAHAN DATA BESAR SECARA PARALEL ===");
        System.out.println("Jumlah elemen array : " + size);
        System.out.println("Jumlah thread       : " + numThreads);
        System.out.println("Total penjumlahan   : " + totalSum);
        System.out.printf("Waktu eksekusi      : %.3f ms%n", executionTime);
    }
}