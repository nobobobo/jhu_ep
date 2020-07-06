/*
*
* Address Class
*
*/
public class Address {

    private String street;
    private String city;
    private String state;
    private String zip;

    /**
     * Constructor
     */

    Address() {
        this.street = "<Not Defined>";
        this.city = "<Not Defined>";
        this.state = "<Not Defined>";
        this.zip = "<Not Defined>";
    }

    /**
     * Initialize Address with parameters
     * @param street: String of street name
     * @param city: String of city name
     * @param state: String of State, 2 chars
     * @param zip: String of 5 digit zip code
     */
    Address(String street, String city, String state, String zip) {
        if (state.length() != 2) {
            throw new RuntimeException("The state field must be 2 characters!");
        }

        if (zip.length() != 5) {
            throw new RuntimeException("Zip code must be 5 digit!");
        }

        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    /**
     * returns Address information in String
     */
    public String toString() {
        return getStreet() + " " + getCity() + " " + getState() + ", " + getZip();
    }

    /**
     * getter for street name
     * @return: street string
     */
    public String getStreet() {
        return this.street;
    }

    /**
     * setter for street name
     * @param street: String for street name
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * getter for city name
     * @return: city string 
     */
    public String getCity() {
        return this.city;
    }

    /**
     * setter for city name
     * @param city: City name
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * getter for state name
     * @return
     */
    public String getState() {
        return this.state;
    }

    /**
     * setter of state name
     * @param state
     */
    public void setState(String state) {
        if (state.length() != 2) {
            throw new RuntimeException("The state field must be 2 characters!");
        }
        this.state = state;
    }

    /**
     * getter for zip code
     * @return
     */
    public String getZip() {
        return this.zip;
    }

    /**
     * setter for zip code
     * @param zip
     */
    public void setZip(String zip) {
        if (zip.length() != 5) {
            throw new RuntimeException("Zip code must be 5 digit!");
        }
        this.zip = zip;
    }

    /**
     * Main method for testing
     * 
     * Expected output: 
     * <Not Defined> <Not Defined> <Not Defined>, <Not Defined>
     * 11 Royce St. Boston MA, 02134
     * @param args
     */
    public static void main(String args[]) {
        Address addr = new Address();
        System.out.println(addr);
        addr = new Address("11 Royce St.", "Boston", "MA", "02134");
        System.out.println(addr);
    }

}