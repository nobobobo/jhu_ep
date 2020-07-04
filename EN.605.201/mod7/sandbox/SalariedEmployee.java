/**
 * SalariedEmployee Class
 */
public class SalariedEmployee extends Employee {
    private int annualSalary;

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
    public int getAnnualSalary() {
        return this.annualSalary;
    }

    /**
     * Annual Salary setter
     * 
     * @param annualSalary
     */
    public void setAnnualSalary(int annualSalary) {
        this.annualSalary = annualSalary;
    }

    /**
     * Salaried Employee toString method
     */
    public String toString() {
        return super.toString() + "\n" + getAnnualSalary();
    }

    /**
     * Main method for testing
     * 
     * @param args
     * 
     *             Expected output: 0: <Not Defined> <Not Defined> <Not Defined>
     *             <Not Defined> <Not Defined>, <Not Defined> 1/1, 1900 60000 1:
     *             John Smith 11 Royce St. Boston MA, 02134 6/12, 2017 80000
     */
    public static void main(String args[]) {
        SalariedEmployee emp1 = new SalariedEmployee(60000);

        System.out.println(emp1.toString());

        Name name = new Name("John", "Smith");
        Address addr = new Address("11 Royce St.", "Boston", "MA", "02134");
        Date hirDate = new Date(6, 12, 2017);
        SalariedEmployee emp2 = new SalariedEmployee(1, name, addr, hirDate, 80000);
        System.out.println(emp2.toString());
    }
}