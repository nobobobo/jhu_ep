/**
 * Job class
 */
public class Job {
    private int id;
    private int printTime;

    /**
     * Constructor
     * @param id
     * @param printTime
     */
    Job(int id, int printTime){
        this.id = id;
        this.printTime = printTime;
    }
   
    /**
     * Id getter
     */
    public int getId(){
        return this.id;
    }

    /**
     * printTime getter
     */
    public int getPrintTime(){
        return this.printTime;
    }
}