/**
 * Contact class
 * 
 */
public class Contact{
    String firstName;
    String lastName;
    int phone;
    String email;

    Contact(String firstName, String lastName, int phone, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public int getPhone(){
        return this.phone;
    }
    public String getEmail(){
        return this.email;
    }
}