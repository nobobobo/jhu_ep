import java.util.Scanner;

public class assignment4_nhayashi {

    /****
     * The method getStartDay() implements Zeller's Algorithm for determining the
     * day of the week the first day of a month is. The method adjusts Zeller's
     * numbering scheme for day of week ( 0=Saturday, ..., 6=Friday ) to conform to
     * a day of week number that corresponds to ISO conventions (i.e., 1=Monday,
     * ..., 7=Sunday)
     * 
     * Pre-Conditions: The month value, m, is 1-12 The day value, d, is 1-31, or
     * 1-28, 1-29 for February The year value, y, is the full year (e.g., 2012)
     * 
     * Post-Conditions: A value of 1-7 is returned, representing the first day of
     * the month 1 = Monday, ..., 7 = Sunday
     ****/

    public static int getStartDay(int m, int d, int y) {
        // Adjust month number & year to fit Zeller's numbering system
        if (m < 3) {
            m = m + 12;
            y = y - 1;
        }

        int k = y % 100; // Calculate year within century
        int j = y / 100; // Calculate century term
        int h = 0; // Day number of first day in month 'm'

        h = (d + (13 * (m + 1) / 5) + k + (k / 4) + (j / 4) + (5 * j)) % 7;

        // Convert Zeller's value to ISO value (1 = Mon, ... , 7 = Sun )
        int dayNum = ((h + 5) % 7) + 1;

        return dayNum;
    }

    /*
     * Method: getNumDaysInMonth(int m, int y) This method returns a int number of
     * days in a month given the arguments m: month, y: year. In this method,
     * isLeapYear(int y) is used to check the given year is a leap year or not
     *
     * @param m: the number of the month
     * 
     * @param y: the number of the year
     *
     * precondition: m & y are integer type. precondition: m is in in the range
     * between 1~12. y is a positive number postcondition: the number of days (28,
     * 29, 30, or 31) is returned.
     */

    public static int getNumDaysInMonth(int m, int y) {
        if (m == 2) {
            if (isLeapYear(y))
                return 29;
            return 28;
        }

        if (m == 4 || m == 6 || m == 9 || m == 11) {
            return 30;
        } else {
            return 31;
        }
    }

    /*
     * Method: isLeapYear(int y) This method returns if the year given as an
     * argument is a leap year or not:
     * 
     * if (year is not divisible by 4) then (it is a common year) else if (year is
     * not divisible by 100) then (it is a leap year) else if (year is not divisible
     * by 400) then (it is a common year) else (it is a leap year)
     *
     * @param y: the number of the year
     *
     * precondition: y is a positive integer type. postcondition: A boolean value
     * indicating y is a leap year or not
     */

    public static boolean isLeapYear(int y) {
        if (y % 4 != 0) {
            return false;
        } else if (y % 100 != 0) {
            return true;
        } else if (y % 400 != 0) {
            return false;
        } else {
            return true;
        }
    }

    /*
     * Method: getMonthName(int m) This method returns a name of the month given the
     * number of the month
     * 
     * @ param: m: the number of the month precondition: m is an integer in a range
     * between 1 and 12, inclusive postcondition: A string of the name of the month
     * is returned.
     */

    public static String getMonthName(int m) {
        switch (m) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                return "";
        }
    }

    /*
     * Method: printMonthBody(int m, int y) This method renders the days in the
     * calendar associated with the correnponding days of the week
     *
     * @param: m and y are integers
     * 
     * @param: m is between 1 and 12, y is a positive number
     *
     * precondition: 1 <= m <= 12, y > 0
     */

    public static void printMonthBody(int m, int y) {
        int numDays = getNumDaysInMonth(m, y);
        int day = 1;

        // get the weekday of the m/1, y
        // ( converted as 0 = Sun, 1 = Mon, ..., 6 = Saturday)
        int startDay = getStartDay(m, 1, y) % 7;

        // currentDayNum keeps track of the priting day of the week
        // 0 = Sun, 1 = Mon, ..., 6 = Saturday
        int currentDayNum = startDay;

        // init leading blanks
        int numBlanks = 1 + startDay * 4;
        String str = "";
        for (int i = 1; i <= numBlanks; i++) {
            str += " ";
        }

        // concat the day number to the string until it hits the numDays
        // if the day number is under 10, two leading blanks are added, otherwise just 1
        // if the currentDayNum hits Saturday, adding new line to the String.
        while (day <= numDays) {
            if (day < 10) {
                str += "  " + day;
            } else {
                str += " " + day;
            }

            if (currentDayNum == 6) {
                str += "\n ";
                currentDayNum = 0;
            } else {
                str += " ";
                currentDayNum++;
            }

            day++;
        }

        // print str
        System.out.println(str);

    }

    /*
     * Method: printMonthHeader(int m, int y) This method prints out the calendar
     * header with month, year, line-separator, 3-char day names
     * 
     * @param: m: month, y: year.
     *
     * precondition: m is an integer in 1 ~ 12 range, y is positive
     */

    public static void printMonthHeader(int m, int y) {
        // define the number of characters per line:
        // 3 chars for each day * 7 days + 1 blank char between each * 6 + 1 leading
        // blank + 1 following blank
        int numCharPerLine = 3 * 7 + 8;

        // get header string
        String monthAndYear = getMonthName(m) + "  " + y;

        // calculate the number of leading blanks
        int numOfBlanks = (numCharPerLine - monthAndYear.length()) / 2;

        // define str to print
        String str = "";
        for (int i = 0; i < numOfBlanks; i++) {
            str += " ";
        }

        // print
        str += monthAndYear;
        System.out.println(str);

        // print days of week and seperator.
        System.out.println(" Sun Mon Tue Wed Thu Fri Sat");
        System.out.println("-----------------------------");

    }

    /*
     * Method: printMonthCalendar(int m , int y) This method calls printMonthHeader
     * and printMonthBody to print the calendar
     * 
     * @param: m: month number, y: year
     *
     * precondition: m is an integer between 1 and 12, y is a positive integer
     */

    public static void printMonthCalendar(int m, int y) {
        printMonthHeader(m, y);
        printMonthBody(m, y);
    }

    /*
     * Main: prompts the user to enter a month (1-12) and year(e.g. 2020). Then call
     * printMonthCalendar() to render a calendar
     */
    public static void main(String[] args) {
        // Create a Scanner to obtain user input
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a Month (1-12), enter 0 to display a calendar for each month:");
        int m = input.nextInt();
        System.out.println("Enter a year (e.g., 2020): ");
        int y = input.nextInt();

        if (m == 0) {
            for (int i = 1; i <= 12; i++) {
                printMonthCalendar(i, y);
                System.out.println();
            }
        } else {
            printMonthCalendar(m, y);
            System.out.println();
        }

    }
}