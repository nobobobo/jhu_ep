import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;

public class Assignment11_nhayashi_q1{
    /**
     * Main
     * @param args
     * @throws FileNotFoundException
     * 
     * Prompt the user for the file path
     * Then read the file to construct a contact list by treemap
     * Print info of each contact finally. 
     */
    public static void main(String[]  args) throws FileNotFoundException {
        Scanner prompt = new Scanner(System.in);
        System.out.println("Please enter input file path: ");
        String filePath = prompt.next();

        File input = new File(filePath);
        Scanner inFile = new Scanner(input);

        String firstName, lastName, email;
        int phone;
        TreeMap<String, Contact> contactList = new TreeMap<String, Contact>();

        while (inFile.hasNextLine()) {
            firstName = inFile.next( );
            lastName = inFile.next();
            phone = inFile.nextInt();
            email = inFile.next();
            contactList.put(lastName, new Contact(firstName, lastName, phone, email));
        }

        for (Map.Entry entry: contactList.entrySet()){
            Contact contact = contactList.get(entry.getKey());
            System.out.println(contact.getLastName()+", "+contact.getFirstName()+" Phone: " + contact.getPhone() + " Email: " + contact.getEmail());
        }

    }
}
