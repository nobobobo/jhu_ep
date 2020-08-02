import java.util.*;

/**
 * Assignment11_nhayashi_q2 class
 */
public class Assignment11_nhayashi_q2 {
    /**
     * Main
     * 
     * Init a prompt scanner, linkedlist, and random generator
     * if the user wants to add a new job, 
     * construct a new Job class, and push to a linked list printQueue's tail.
     * After the user quit the prompt loop, print out job ids and printTime-s in FIFO order.
     */
    public static void main(String[] args) {
        Scanner prompt = new Scanner(System.in);

        Boolean quit = false;
        LinkedList<Job> printQueue = new LinkedList<Job>();

        long rnSeed = 1;
        Random rnGenerator = new Random(rnSeed);

        int id = 1;
        int printTime;

        while (!quit) {
            System.out.println("Do you want to add a new print job? (Y/N):");
            String res = prompt.next().trim().toLowerCase();
            if (res.equals("yes")|| res.equals("y")) {
                printTime = rnGenerator.nextInt(991) + 10;
                Job newJob = new Job(id, printTime);
                printQueue.addLast(newJob);
                id++;
            } else if (res.equals("no")|| res.equals("n")){
                
                quit = true;
            }
        }
        
        for (Job job : printQueue){
            System.out.println("Job ID: "+ job.getId() + " Print Time: " + job.getPrintTime());
        }
    }
}