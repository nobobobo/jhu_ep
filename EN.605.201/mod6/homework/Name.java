/**
 * Name class
 */
public class Name {

    private String firstName;
    private String lastName;

    /**
     * Constructor
     */
    Name() {
        this.firstName = "<Not Defined>";
        this.lastName = "<Not Defined>";
    }

    /**
     * Initializer for name
     * 
     * @param firstName
     * @param lastName
     */
    Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * toString method, using getters
     */
    public String toString() {
        return getFirstName() + " " + getLastName();
    }

    /**
     * getter for Firstname field
     * 
     * @return
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * setter for firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * getter for lastName field
     * 
     * @return
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * setter for last name
     * 
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Main method for testing Expected output: 
     * <Not Defined> <Not Defined> 
     * Noboru Hayashi
     * 
     * @param args
     */
    public static void main(String args[]) {
        Name name = new Name();
        System.out.println(name);
        name = new Name("Noboru", "Hayashi");
        System.out.println(name);
    }

}