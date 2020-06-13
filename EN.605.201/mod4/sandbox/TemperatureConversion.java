import java.util.Scanner;

public class TemperatureConversion{
    // A method to convert fah -> cel:
    public static float fah_to_cel(float fah){
        return 5F / 9F * (fah - 32F);
    }

    // A method to convert cel -> fah:
    public static float cel_to_fah(float cel){
        return 9F / 5F * cel + 32F;
    }

    public static void main(String[] args){

        int userChoice = 0; // User selection: 1, 2, 3
        float temperatureFahrenheit = 0; // Fahrenheit temperature
        float temperatureCelsius = 0; // Celsius temperature
        Scanner input = new Scanner(System.in); // Create a Scanner to obtain user input

        while (userChoice != 3){

            System.out.print("Enter 1 to convert F->C, 2 to convert C->F, 3 to quit: ");
            userChoice = input.nextInt(); // Read user input
            switch (userChoice){

                case 1: // Convert Fahrenheit to Celsius
                    System.out.print("Enter a Fahrenheit temperature: ");
                    temperatureFahrenheit = input.nextFloat();
                    System.out.println(temperatureFahrenheit + " degrees Fahrenheit is " + fah_to_cel(temperatureFahrenheit)
                            + " degrees Celsius");
                    break;

                case 2: // Convert Celsius to Fahrenheit
                    System.out.print("Enter a Celsius temperature: ");
                    temperatureCelsius = input.nextFloat();
                    System.out.println(temperatureCelsius + " degrees Celsius is " + cel_to_fah(temperatureCelsius)
                            + " degrees Fahrenheit");
                    break;

                case 3: // End Program
                    System.out.println("Bye Bye");
                    break;

                default: // Invalid Data Entered

                    System.out.println("Invalid Data: You must enter 1, 2, or 3");

            }
        }
    }
}