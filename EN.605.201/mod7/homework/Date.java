/**
 * 
 * Date Class
 * 
 */

public class Date {
    private int month;
    private int day;
    private int year;

    /**
     * Constructor
     */
    Date() {
        this.month = 1;
        this.day = 1;
        this.year = 1900;
    }

    /**
     * Initilizer with parameters
     * 
     * @param month
     * @param day
     * @param year
     */
    Date(int month, int day, int year) {
        if (month < 1 || month > 12) {
            throw new RuntimeException("Month must be between 1 and 12!");
        }
        if (day < 1 || day > 31) {
            throw new RuntimeException("Day must be between 1 and 31!");
        }
        if (year < 1900 || year > 2020) {
            throw new RuntimeException("Year must be between 1900 and 2020!");
        }

        this.month = month;
        this.day = day;
        this.year = year;
    }

    /**
     * toString() method, get Month, Day, Year field and return them concatenated
     */
    public String toString() {
        return getMonth() + "/" + getDay() + ", " + getYear();
    }

    /**
     * getter method for Month
     * 
     * @return month as int
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * setter method for Month
     * 
     * @param month
     */
    public void setMonth(int month) {
        if (month < 1 || month > 12) {
            throw new RuntimeException("Month must be between 1 and 12!");
        }

        this.month = month;
    }

    /**
     * getter method for Day
     * 
     * @return interger 'day'
     */
    public int getDay() {
        return this.day;
    }

    /**
     * Setter method for Day
     * 
     * @param day
     */
    public void setDay(int day) {
        if (day < 1 || day > 31) {
            throw new RuntimeException("Day must be between 1 and 31!");
        }

        this.day = day;
    }

    /**
     * getter method for Day
     */
    public int getYear() {
        return this.year;
    }

    /**
     * setter method for year
     */
    public void setYear(int year) {
        if (year < 1900 || year > 2020) {
            throw new RuntimeException("Year must be between 1900 and 2020!");
        }

        this.year = year;
    }

    /**
     * main method for testing. Expected output: 
     * 1/1, 1900 
     * 6/28, 2020
     * 
     * @param args
     */
    public static void main(String args[]) {
        Date date = new Date();
        System.out.println(date.toString());

        date = new Date(6, 28, 2020);
        System.out.println(date.toString());
    }
}