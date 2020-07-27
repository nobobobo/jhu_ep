public class MySort {
    public static void main(String[] args) {
        int[] arr = {4,7,8,5,9,2,3,1};
        int[] rev = mySort(arr);

        for (int i=0; i< rev.length; i++){
            System.out.print(rev[i]+ " ");
        }

        System.out.println();
        
    }

    public static int[] mySort(int[] arr){
        int len = arr.length;
        int[] output = new int[len];

        for (int i = 0; i< len; i++){
            int num = arr[i];
            int cnt = 0;
            for (int j = 0; j< len; j++){
                if (arr[j]< num) cnt++;
            }
            output[cnt] = arr[i];
        }

        return output;

    }
    
}