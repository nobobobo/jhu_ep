import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class EvalLanguage {
    public static void main(String args[]) throws FileNotFoundException{
        File text = new File("./LabRegExpRequiredInput.txt");
        File output = new File("./output.txt");
        FileWriter fr = null;

        if (args.length != 0){
            text = new File("./" + args[0]);
            output = new File("./" + args[1]);
        }

        Scanner sc = new Scanner(text);
        String outputString = "";

        while(sc.hasNextLine()){
            String line = sc.nextLine();
            outputString += ("String: \"" + line +"\"\n");
            outputString += ("L1: " +evalL1(line)+"\n");
            outputString += ("L2: " +evalL2(line)+"\n");
            outputString += ("L3: " +evalL3(line)+"\n");
            outputString += ("L4: " +evalL4(line)+"\n");
            outputString += ("L5: " +evalL5(line)+"\n\n");
        }   
        sc.close();

        try {
            output.createNewFile();
            fr = new FileWriter(output);
            fr.write(outputString);
            fr.close();
        } catch (IOException e){
            System.out.println("Error!");
            e.printStackTrace();
        }
    }

    public static boolean evalL1(String str) {
        if (str == null || str.length() == 0)
            return false;
        char firstCh = str.charAt(0);
        if (firstCh != 'A' && firstCh != 'B')
            return false;

        Stack stack = new Stack();

        for (int i = 0; i < str.length(); i++) {
            char nextCh = str.charAt(i);
            if (nextCh != 'A' && nextCh != 'B')
                return false;
            if (nextCh == firstCh) {
                stack.push(nextCh);
            } else {
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    public static boolean evalL2(String str) {
        if (str == null || str == "")
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

    public static boolean evalL3(String str) {
        if (str == null || str == "")
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

    public static boolean evalL4(String str) {
        if (str == null || str == "")
            return false;

        int n = 0, m = 0;
        int i = 0;
        boolean isFirstCheck = true;
        Stack stack = new Stack();

        while (i < str.length() && isFirstCheck) {
            char nextCh = str.charAt(i);
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
            while (i < str.length() && str.charAt(i) == 'A') {
                for (int j = 0; j < m; j++) {
                    stack.push(str.charAt(i));
                }
                i++;
            }

            while (i < str.length() && str.charAt(i) == 'B') {
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

    public static boolean evalL5(String str) {
        if (str == null || str == "")
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

}