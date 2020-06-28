public class Name {

    private String firstName;
    private String lastName;

    Name() {
        this.firstName = "<Not Defined>";
        this.lastName = "<Not Defined>";
    }

    Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String toString() {
        return getFirstName() + " " + getLastName();
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static void main(String args[]) {
        Name name = new Name();
        System.out.println(name);
        name = new Name("Noboru", "Hayashi");
        System.out.println(name);
    }

}