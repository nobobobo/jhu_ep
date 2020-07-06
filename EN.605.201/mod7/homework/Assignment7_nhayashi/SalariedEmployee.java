/**
 * SalariedEmployee Class
 */
public class SalariedEmployee extends Employee {
    private double annualSalary;

    /**
     * Constructor with annual Salary
     * 
     * @param annualSalary
     */
    SalariedEmployee(int annualSalary) {
        super();
        this.annualSalary = annualSalary;
    }

    /**
     * Constructor with emp#, name, address, hireDate, annualSalary
     * 
     * @param employeeNumber
     * @param name
     * @param address
     * @param hireDate
     * @param annualSalary
     */
    SalariedEmployee(int employeeNumber, Name name, Address address, Date hireDate, int annualSalary) {
        super(employeeNumber, name, address, hireDate);
        this.annualSalary = annualSalary;
    }

    /**
     * Annual Salary getter
     * 
     * @return annualSalary
     */
    public double getAnnualSalary() {
        return this.annualSalary;
    }

    /**
     * Annual Salary setter
     * 
     * @param annualSalary
     */
    public void setAnnualSalary(double annualSalary) {
        this.annualSalary = annualSalary;
    }

    /**
     * Salaried Employee toString method
     */
    public String toString() {
        return super.toString() + "\nAnnual Salary: " + getAnnualSalary();
    }

    /**
     * Main method for testing
     * 
     * @param args
     * 
     * Expected output: 
     * 0: <Not Defined> <Not Defined>
     * Address: <Not Defined> <Not Defined> <Not Defined>, <Not Defined>
     * Hire Date: 1/1, 1900
     * Annual Salary: 60000.0
     * 
     * 1: John Smith
     * Address: 11 Royce St. Boston MA, 02134
     * Hire Date: 6/12, 2017
     * Annual Salary: 80000.0
     */
    public static void main(String args[]) {
        SalariedEmployee emp1 = new SalariedEmployee(60000);

        System.out.println(emp1.toString());

        System.out.println();

        Name name = new Name("John", "Smith");
        Address addr = new Address("11 Royce St.", "Boston", "MA", "02134");
        Date hirDate = new Date(6, 12, 2017);
        SalariedEmployee emp2 = new SalariedEmployee(1, name, addr, hirDate, 80000);
        System.out.println(emp2.toString());
    }
}