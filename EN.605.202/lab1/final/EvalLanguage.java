import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * EvalLanguage class
 */
public class EvalLanguage {
    /**
     * Main method Read the given text and generate new output with the results of
     * Eval 1-6 language methods
     * 
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String args[]) throws FileNotFoundException {
        File text = new File("./LabRegExpRequiredInput.txt");
        File output = new File("./output.txt");
        FileWriter fr = null;

        if (args.length != 0) {
            text = new File("./" + args[0]);
            output = new File("./" + args[1]);
        }

        Scanner sc = new Scanner(text);
        String outputString = "L1= { w: w contains equal numbers of A's and B's (in any order) and no other characters}\nL2 = { w: w is of the form AnBn, for some n > 0 }\nL3 = { w: w is of the form AnB2n, for some n > 0 }\nL4 = { w: w is of the form (AnBm)p, for some m,n,p > 0 }\nL5 = { w: w is of the form BnAn for some n > 0 }.\nL6 = { w: w contains equal numbers of A's, B's and C's (in any order) and no other characters}.\n\n";

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            outputString += ("String: \"" + line + "\"\n");
            outputString += ("L1: " + evalL1(line) + "\n");
            outputString += ("L2: " + evalL2(line) + "\n");
            outputString += ("L3: " + evalL3(line) + "\n");
            outputString += ("L4: " + evalL4(line) + "\n");
            outputString += ("L5: " + evalL5(line) + "\n");
            outputString += ("L6: " + evalL6(line) + "\n\n");
        }
        sc.close();

        try {
            output.createNewFile();
            fr = new FileWriter(output);
            fr.write(outputString);
            fr.close();
        } catch (IOException e) {
            System.out.println("Error!");
            e.printStackTrace();
        }
    }

    /**
     * evalL1
     * 
     * read each character from the string, and push each to the corresponding
     * stack. After reading, popping out the same number of the stack until one of
     * them is empty, then check both stacks are empty or not
     * 
     * @param str
     * @return
     */
    public static boolean evalL1(String str) {
        if (str == null || str.length() == 0)
            return false;

        Stack stackA = new Stack();
        Stack stackB = new Stack();

        for (int i = 0; i < str.length(); i++) {
            char nextCh = str.charAt(i);
            if (nextCh == 'A')
                stackA.push(nextCh);
            if (nextCh == 'B')
                stackB.push(nextCh);
            if (nextCh != 'A' && nextCh != 'B')
                return false;
        }

        while (!stackA.isEmpty() && !stackB.isEmpty()) {
            stackA.pop();
            stackB.pop();
        }
        return stackA.isEmpty() && stackB.isEmpty();
    }

    /**
     * evalL2 read characters A's from the string and push to the stack. Once the
     * read character becomes 'B', start popping at every read. Finally check the
     * stack is empty or not to evaluate the string is of form AnBn
     * 
     * @param str
     * @return
     */
    public static boolean evalL2(String str) {
        if (str == null || str.length() == 0)
            return false;

        int i = 0;

        Stack stack = new Stack();

        while (i < str.length() && str.charAt(i) == 'A') {
            stack.push(str.charAt(i));
            i++;
        }

        while (i < str.length() && str.charAt(i) == 'B') {
            stack.pop();
            i++;
        }

        return i == str.length() && stack.isEmpty();

    }

    /**
     * evalL3
     * 
     * read characters A's from the string and push twice to the stack. Once the
     * read character becomes 'B', start popping at every read. Finally check the
     * stack is empty or not to evaluate the string is of form AnB2n
     * 
     * @param str
     * @return
     */

    public static boolean evalL3(String str) {
        if (str == null || str.length() == 0)
            return false;

        int i = 0;

        Stack stack = new Stack();

        while (i < str.length() && str.charAt(i) == 'A') {
            stack.push(str.charAt(i));
            stack.push(str.charAt(i));
            i++;
        }

        while (i < str.length() && str.charAt(i) == 'B') {
            stack.pop();
            i++;
        }

        return i == str.length() && stack.isEmpty();

    }

    /**
     * evalL4
     * 
     * Read the string from the left and check how many A's and B's are in the
     * pattern, such as for string ABBABBABBABB it gets n = 1, m = 2 (ABB). Then use
     * this result to read remaining character, and check the remaining substring
     * follows this pattern by checking the stack is empty or not.
     * 
     * @param str
     * @return
     */
    public static boolean evalL4(String str) {
        if (str == null || str.length() == 0)
            return false;

        int n = 0, m = 0;
        int i = 0;
        boolean isFirstCheck = true;
        Stack stack = new Stack();

        while (i < str.length() && isFirstCheck) {
            char nextCh = str.charAt(i);
            if (stack.isEmpty() && nextCh == 'B')
                return false;
            if (nextCh != 'A' && nextCh != 'B')
                return false;

            if (nextCh == 'A' && !stack.isEmpty() && stack.peek() == 'B') {
                isFirstCheck = false;
                break;
            }

            if (nextCh == 'A')
                n++;
            if (nextCh == 'B')
                m++;
            stack.push(nextCh);
            i++;
        }

        while (!stack.isEmpty()) {
            stack.pop();
        }

        while (i < str.length()) {
            if (str.charAt(i) != 'A' && str.charAt(i) != 'B')
                return false;

            int aEnd = i + n;
            int bEnd = i + n + m;

            while (i < str.length() && i < aEnd && str.charAt(i) == 'A') {
                for (int j = 0; j < m; j++) {
                    stack.push(str.charAt(i));
                }
                i++;
            }

            while (i < str.length() && i < bEnd && str.charAt(i) == 'B') {
                for (int j = 0; j < n; j++) {
                    if (stack.isEmpty())
                        return false;
                    stack.pop();
                }
                i++;
            }

            if (!stack.isEmpty())
                return false;
        }

        return true;

    }

    /**
     * evalL5
     * 
     * Similar to evalL2, read characters 'B' first and push to the stack. Once all
     * B's are read and start reading A's, start popping element from the stack.
     * Then check the stack is empty or not once the string is read.
     * 
     * @param str
     * @return
     */
    public static boolean evalL5(String str) {
        if (str == null || str.length() == 0)
            return false;

        int i = 0;

        Stack stack = new Stack();

        while (i < str.length() && str.charAt(i) == 'B') {
            stack.push(str.charAt(i));
            i++;
        }

        while (i < str.length() && str.charAt(i) == 'A') {
            stack.pop();
            i++;
        }

        return i == str.length() && stack.isEmpty();

    }

    /**
     * evalL6
     * 
     * Similar to evalL1, using three stacks: stackA, stackB, stackC and store each
     * character from the string Once the all characters from the string are read,
     * start poppoing the same number of the elements. After one of three stacks is
     * being empty, check the other two are empty or not to evaluate the same number
     * of A's, B's and C's are in the string
     * 
     * @param str
     * @return
     */
    public static boolean evalL6(String str) {
        if (str == null || str.length() == 0)
            return false;

        Stack stackA = new Stack();
        Stack stackB = new Stack();
        Stack stackC = new Stack();

        for (int i = 0; i < str.length(); i++) {
            char nextCh = str.charAt(i);
            if (nextCh == 'A')
                stackA.push(nextCh);
            if (nextCh == 'B')
                stackB.push(nextCh);
            if (nextCh == 'C')
                stackC.push(nextCh);
            if (nextCh != 'A' && nextCh != 'B' && nextCh != 'C')
                return false;
        }

        while (!stackA.isEmpty() && !stackB.isEmpty() && !stackC.isEmpty()) {
            stackA.pop();
            stackB.pop();
            stackC.pop();
        }
        return stackA.isEmpty() && stackB.isEmpty() && stackC.isEmpty();
    }

}