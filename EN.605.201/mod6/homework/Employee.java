/**
 * Employee Class
 */

public class Employee {

    private int employeeNumber;
    private Name name;
    private Address address;
    private Date hireDate;

    /**
     * Constructor
     */
    Employee() {
        this.employeeNumber = 0;
        this.name = new Name();
        this.address = new Address();
        this.hireDate = new Date();
    }

    /**
     * Employee initializer
     */
    Employee(int employeeNumber, Name name, Address address, Date hireDate) {
        this.employeeNumber = employeeNumber;
        this.name = name;
        this.address = address;
        this.hireDate = hireDate;
    }

    /**
     * toString method, using getters
     */
    public String toString() {
        return getEmployeeNumber() + ": " + getName() + "\n" + getAddress() + "\n" + getHireDate();
    }

    /**
     * getter method for Employee Number
     * 
     * @return
     */
    public int getEmployeeNumber() {
        return this.employeeNumber;
    }

    /**
     * Setter method for employee number
     */
    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    /**
     * getter method for name
     * 
     * @return
     */
    public String getName() {
        return this.name.toString();
    }

    /**
     * setter method for name
     * 
     * @param name
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     * getter method for address
     * 
     * @return
     */
    public String getAddress() {
        return this.address.toString();
    }

    /**
     * setter method for address
     * 
     * @param address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * getter method for hire date
     * 
     * @return
     */
    public String getHireDate() {
        return this.hireDate.toString();
    }

    /**
     * setter method for hire date
     * 
     * @param hireDate
     */
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    /**
     * Main method for testing Expected output:
     *  0: <Not Defined> <Not Defined> 
     * <Not Defined> <Not Defined> <Not Defined>, <Not Defined> 
     * 1/1, 1900 
     * 1: Noboru Hayashi 
     * 11 Royce St. Boston MA, 02134 
     * 6/12, 2017
     * 
     * @param args
     */
    public static void main(String args[]) {
        Employee emp = new Employee();
        System.out.println(emp.toString());

        Name name = new Name("Noboru", "Hayashi");
        Address addr = new Address("11 Royce St.", "Boston", "MA", "02134");
        Date hireDate = new Date(6, 12, 2017);
        emp = new Employee(1, name, addr, hireDate);
        System.out.println(emp.toString());

    }

}