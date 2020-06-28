public class Employee {

    private int employeeNumber;
    private Name name;
    private Address address;
    private Date hireDate;

    Employee(){
        this.employeeNumber = 0;
        this.name = new Name();
        this.address = new Address();
        this.hireDate = new Date();
    }

    Employee(int employeeNumber, Name name, Address address, Date hireDate){
        this.employeeNumber = employeeNumber;
        this.name = name;
        this.address = address;
        this.hireDate = hireDate;
    }

    public String toString(){
        return getEmployeeNumber() + ": " + getName() + "\n" + getAddress() + "\n" + getHireDate();
    }

    public int getEmployeeNumber(){
        return this.employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber){
        this.employeeNumber = employeeNumber;
    }

    public String getName(){
        return this.name.toString();
    }

    public void setName(Name name){
        this.name = name;
    }

    public String getAddress(){
        return this.address.toString();
    }

    public void setAddress(Address address){
        this.address = address;
    }

    public String getHireDate(){
        return this.hireDate.toString();
    }

    public void setHireDate(Date hireDate){
        this.hireDate = hireDate;
    }



    public static void main(String args[]){
        Employee emp = new Employee();
        System.out.println(emp.toString());

        Name name = new Name("Noboru", "Hayashi");
        Address addr = new Address("11 Royce St.","Boston","MA","02134");
        Date hireDate = new Date(6,12,2017);
        emp = new Employee(1,name,addr,hireDate);
        System.out.println(emp.toString());

    }
    
}