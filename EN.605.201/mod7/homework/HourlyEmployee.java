/**
 * class HourlyEmployee
 */

public class HourlyEmployee extends Employee {
    private double hourlyPay;
    private int hoursWorked;
    private double earnings;

    /**
     * Constructor with hourlyPay, hoursWorked parameters
     * 
     * @param hourlyPay
     * @param hoursWorked
     */
    HourlyEmployee(double hourlyPay, int hoursWorked) {
        super();
        this.hourlyPay = hourlyPay;
        this.hoursWorked = hoursWorked;
        calcEarnings();
    }

    /**
     * Constructor with employee base params and hourlyPay, hoursWorked params
     * 
     * @param employeeNumber
     * @param name
     * @param address
     * @param hireDate
     * @param hourlyPay
     * @param hoursWorked
     */
    HourlyEmployee(int employeeNumber, Name name, Address address, Date hireDate, float hourlyPay, int hoursWorked) {
        super(employeeNumber, name, address, hireDate);
        this.hourlyPay = hourlyPay;
        this.hoursWorked = hoursWorked;
        calcEarnings();
    }

    /**
     * toString method
     */
    public String toString() {
        return super.toString() + "\nHourly Pay: " + getHourlyPay() + "\nHours Worked: " + getHoursWorked() + "\nCurrent Earnings: " + getEarnings();
    }

    /**
     * calcEarnings() calculates earnings by hoursWorked and hourlyPay attributes
     */
    public void calcEarnings() {
        if (this.hoursWorked > 40) {
            this.earnings = 1.5 * this.hourlyPay * this.hoursWorked;
        } else {
            this.earnings = this.hourlyPay * this.hoursWorked;
        }
    }

    /**
     * hourly pay getter
     */
    public double getHourlyPay() {
        return this.hourlyPay;
    }

    /**
     * hourly pay setter, recalc earnings after setting
     * 
     * @param hourlyPay
     */
    public void setHourlyPay(double hourlyPay) {
        this.hourlyPay = hourlyPay;
        calcEarnings();
    }

    /**
     * hours worked getter
     */
    public int getHoursWorked() {
        return this.hoursWorked;
    }

    /**
     * hours worked setter, recalc earnings after setting
     * 
     * @param hoursWorked
     */
    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
        calcEarnings();
    }

    /**
     * earning getter
     */
    public double getEarnings() {
        return this.earnings;
    }

    /**
     * Main method for testing
     * 
     * @param args 
     * Expected Output: 
     * 0: <Not Defined> <Not Defined>
     * Address: <Not Defined> <Not Defined> <Not Defined>, <Not Defined>
     * Hire Date: 1/1, 1900
     * Hourly Pay: 15.0
     * Hours Worked: 40
     * Current Earnings: 600.0
     * 
     * 1: John Smith
     * Address: 11 Royce St. Boston MA, 02134
     * Hire Date: 6/12, 2017
     * Hourly Pay: 15.0
     * Hours Worked: 50
     * Current Earnings: 1125.0
     */
    public static void main(String args[]) {
        HourlyEmployee emp1 = new HourlyEmployee(15, 40);
        System.out.println(emp1.toString());

        System.out.println();
        Name name = new Name("John", "Smith");
        Address addr = new Address("11 Royce St.", "Boston", "MA", "02134");
        Date hirDate = new Date(6, 12, 2017);
        HourlyEmployee emp2 = new HourlyEmployee(1, name, addr, hirDate, 15, 50);
        System.out.println(emp2.toString());

    }
}