public class Lab4 {

    public static void main(String[] args) {
        int[] gaps = { 29524, 9841, 3280, 1093, 364, 121, 40, 13, 4, 1 };
        int[] arr = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };

        // shellSort(arr, gaps);
        heapSort(arr);

        for (int num: arr){
            System.out.println(num);
        }

    }

    public static void shellSort(int[] arr, int[] gaps) {
        int n = arr.length;
        for (int gap : gaps) {
            for (int i = gap; i < n; i++) {
                int key = arr[i];
                int j = i;
                while (j >= gap && arr[j - gap] > key) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = key;
            }
        }
    }

    public static void heapSort(int[] arr) {

        int size = arr.length;

        for (int i = (size-1) -1; i >= 0; i--) {
            heapify(arr, i, size);
        }

        for (int i = size - 1; i > 0; i--) {
            int tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;

            heapify(arr, 0, i);
        }

    }

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