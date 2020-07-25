public class ArrayIndexOutOfBoundsExceptionCatch {
    public static void main(String[] args) {
        try {
            int[] arr = {1,2,3};
            System.out.println(arr[100]);
        } catch (ArrayIndexOutOfBoundsException exception){
            System.out.println("This program caught: " + exception);
        }
    }
    
}