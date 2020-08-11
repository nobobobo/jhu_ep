import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Lab4 {

    public static void main(String[] args) {
        /* pre define gap sequences */
        int[] gaps_1 = { 1, 4, 13, 40, 121, 364, 1093, 3280, 9841, 29524 };
        int[] gaps_2 = { 1, 5, 17, 53, 149, 373, 1121, 3371, 10111, 30341 };
        int[] gaps_3 = { 1, 10, 30, 60, 120, 360, 1080, 3240, 9720, 29160 };
        int[] gaps_4 = { 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2028, 4096, 8192, 16384, 32768 };

        /** setting up total time counter */
        // long total = 0;

        /** loop 5000 times to calculate averate, commented out for writing files */
        // for (int i = 0; i < 5000; i++) {
        try {
            int[] arr = new int[50];
            File inFile = new File("./Lab 4 Input Files/rev50.dat");
            Scanner sc = new Scanner(inFile);
            int idx = 0;
            while (sc.hasNextInt() && idx < 50) {
                arr[idx] = sc.nextInt();
                idx++;
            }
            /** init start time */
            long start = System.nanoTime();

            /** sorting... */
            //shellSort(arr, gaps_4);
            heapSort(arr);

            /** clocking the end time and calculate the duration */
            long end = System.nanoTime();
            long time = end - start;
            // total += time;
            
            /** writing out files */
            FileWriter outFile = new FileWriter("./Lab_4_Output_Files/hs_rev50.txt");
            outFile.write("Heap sort, reverse: "+String.valueOf(time/1000.0)+ " microseconds.\n");
            for (int num: arr){
                outFile.write(String.valueOf(num)+"\n");
            }
            outFile.close();

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        } 
        // }

        //System.out.println("Average time elapsed (microseconds): " + total / 1000.0 / 5000.0);
        System.out.println("Done!");
    }

    /**
     * shell sort: operates shell sort given original array, and gap sequences
     * @param arr
     * @param gaps
     */
    public static void shellSort(int[] arr, int[] gaps) {
        int n = arr.length;
        int gapIdx = 0;
        while (gapIdx < gaps.length) {
            if (gaps[gapIdx] > n) {
                break;
            }
            gapIdx++;
        }
        gapIdx -= 2;

        while (gapIdx >= 0) {
            int gap = gaps[gapIdx];
            for (int i = gap; i < n; i++) {
                int key = arr[i];
                int j = i;
                while (j >= gap && arr[j - gap] > key) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = key;
            }
            gapIdx--;
        }
    }

    /**
     * heapsort: operates heapsort by heapify the original array then sorting
     * @param arr
     */
    public static void heapSort(int[] arr) {

        int size = arr.length;

        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(arr, i, size);
        }

        for (int i = size - 1; i > 0; i--) {
            int tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;

            heapify(arr, 0, i);
        }

    }

    /**
     * heapify: a helper function that creates a heap
     * @param arr
     * @param rootIdx
     * @param size
     */
    public static void heapify(int arr[], int rootIdx, int size) {
        int maxIdx = rootIdx;
        int lIdx = rootIdx * 2 + 1;
        int rIdx = rootIdx * 2 + 2;

        if (lIdx < size && arr[lIdx] > arr[maxIdx])
            maxIdx = lIdx;
        if (rIdx < size && arr[rIdx] > arr[maxIdx])
            maxIdx = rIdx;

        if (maxIdx != rootIdx) {
            int tmp = arr[maxIdx];
            arr[maxIdx] = arr[rootIdx];
            arr[rootIdx] = tmp;

            heapify(arr, maxIdx, size);
        }
    }

}