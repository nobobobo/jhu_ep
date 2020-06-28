import java.util.Scanner;

public class Assignment6_nhayashi {
    public static void main(String args[]){
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter a number of employees: ");
        int num = input.nextInt();
        input.nextLine();
        
        Employee[] employeeArray = new Employee[num];

        for (int i = 0; i<num ; i++){
            employeeArray[i] = promptOnce();
        }

        for (int i = 0; i<num; i++){
            System.out.println(employeeArray[i].toString());
        }
    }

    public static Employee promptOnce() {
        Scanner input = new Scanner(System.in);

        System.out.println("Employee number: ");
        int employeeNumber = input.nextInt();
        input.nextLine();

        System.out.println("Firstname: ");
        String firstName = input.nextLine();
        System.out.println("Lastname: ");
        String lastName = input.nextLine();
        Name name = new Name(firstName, lastName);

        System.out.println("Street name: ");
        String street = input.nextLine();
        System.out.println("City name: ");
        String city = input.nextLine();
        System.out.println("State name (2 characters): ");
        String state = input.nextLine();
        System.out.println("5-digit Zipcode: ");
        String zip = input.nextLine();
        Address addr = new Address(street, city, state, zip);

        System.out.println("Year of Hire (1900-2020): ");
        int year = input.nextInt();
        input.nextLine();
        System.out.println("Month of Hire (1-12): ");
        int month = input.nextInt();
        input.nextLine();
        System.out.println("Day of Hire (1-31): ");
        int day = input.nextInt();
        input.nextLine();
        Date hireDate = new Date(month, day, year);

        return new Employee(employeeNumber, name, addr, hireDate);
    }

}