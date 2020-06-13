import java.util.Scanner; 

public class printAsterisks{
    public static void main(String [] args){
        // User prompt for lineNum and choice of display style
        Scanner scan = new Scanner(System.in); 
        System.out.println("Enter the maximum number of asterisks to display on a line of output: ");
        int lineNum = scan.nextInt(); 
        System.out.println("Enter the choice of the display style: (1 or 2) ");
        int choice = scan.nextInt();
        
        // loop lineNum times.
        for (int i =0; i< lineNum; i++){

            // checking the display style and print multiple asterisks. 
            if (choice == 1){
                for (int j = 0; j<= i; j++){
                    System.out.print('*');
                }
                System.out.println();
            } else {
                for (int j = 0; j<= lineNum - 1 - i; j++){
                    System.out.print('*');
                }
                System.out.println();
            }
        }
    }
}