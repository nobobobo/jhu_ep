public class Test{
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};

        System.out.println(findLargest(arr));
        System.out.println(findSmallest(arr));
        
    }

    public static int findLargest(int[] arr){
        int res = arr[0];

        for (int i = 1; i< arr.length; i++){
            if (arr[i]>res) res = arr[i];
        }

        return res;
    }

    public static int findSmallest(int[] arr){
        int res = arr[0];

        for (int i = 1; i< arr.length; i++){
            if (arr[i]<res) res = arr[i];
        }

        return res;
    }
}