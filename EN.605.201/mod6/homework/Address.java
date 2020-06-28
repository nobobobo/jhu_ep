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

    Address() {
        this.street = "<Not Defined>";
        this.city = "<Not Defined>";
        this.state = "<Not Defined>";
        this.zip = "<Not Defined>";
    }

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

    public String toString() {
        return getStreet() + " " + getCity() + " " + getState() + ", " + getZip();
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        if (state.length() != 2) {
            throw new RuntimeException("The state field must be 2 characters!");
        }
        this.state = state;
    }

    public String getZip() {
        return this.zip;
    }

    public void setZip(String zip) {
        if (zip.length() != 5) {
            throw new RuntimeException("Zip code must be 5 digit!");
        }
        this.zip = zip;
    }

    public static void main(String args[]) {
        Address addr = new Address();
        System.out.println(addr);
        addr = new Address("11 Royce St.", "Boston", "MA", "02134");
        System.out.println(addr);
    }

}