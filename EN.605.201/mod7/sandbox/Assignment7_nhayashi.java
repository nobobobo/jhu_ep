/**
 * 
 * Assignment 7 main java class
 */
public class Assignment7_nhayashi {
    /**
     * Main method: setting up 1 salaried employee and 2 hourly employees and print
     * out all information using toString() for Employee class
     * 
     * @param args
     */
    public static void main(String args[]) {

        // Salaried Employee Creation:
        SalariedEmployee sEmp = new SalariedEmployee(1, new Name("John", "Smith"),
                new Address("11 Royce St.", "Boston", "MA", "02134"), new Date(1, 1, 2016), 80000);

        // Render out the salaried employee info
        System.out.println();
        System.out.println("Salaried Employee: ");
        System.out.println(sEmp.toString());

        // Hourly Employee 1 creation:
        HourlyEmployee hEmp1 = new HourlyEmployee(2, new Name("Noboru", "Hayashi"),
                new Address("1254 Commonwealth Ave.", "Boston", "MA", "02134"), new Date(9, 1, 2016), 15, 20);

        // Render out the hourly employee info
        System.out.println();
        System.out.println("Hourly Employee 1:");
        System.out.println(hEmp1.toString());

        // Hourly Employee 2 creation:
        HourlyEmployee hEmp2 = new HourlyEmployee(2, new Name("Mary", "Jones"),
                new Address("275 Babcock St.", "Boston", "MA", "02215"), new Date(1, 1, 2016), 20, 50);

        // Render out the hourly employee 2 info
        System.out.println();
        System.out.println("Hourly Employee 2:");
        System.out.println(hEmp2.toString());

    }
}