import java.util.Scanner;

public class Assignment5_nhayashi {


    /*
    * main(): prompt the user's choice of the type of translation, 
    * and the original string of Morse code characters or English characters
    */
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Please select a type of translation:");
        System.out.println("0: Morse -> English");
        System.out.println("1: English -> Morse");
        int engToMor = input.nextInt();
        input.nextLine();
        System.out.println("Please enter the input string:");
        String original = input.nextLine();

        // based on the type of translation, call translateToEnglish() or translateToMorse()
        if (engToMor == 0){
            translateToEnglish(original);
        } else if (engToMor == 1){
            translateToMorse(original);
        }
    }

    
    /*
    * void translateToEnglish():
    * This function parses input Morse string and calls convertChar to translate characters one by one, 
    * then prints out to translated string in English to the console
    * @param: morseString, a string in morse code characters: ".... . .-.. .-.. ---"
    * precondition: morseString follows a specific format: each character is separated by a space, and each word is separated by a pipe | 
    */
    public static void translateToEnglish(String morseString){
        String[] morseArr = morseString.split(" ");
        for (String mor : morseArr){
            System.out.print(convertChar(mor, false));         
        }
        System.out.println();
    }


    /*
    * void translateToMorse():
    * This function parses input English string and calls convertChar to translate characters one by one, 
    * then prints out to translated string in Morse Code String to the console
    * @param: englishString, a string in english characters: "Hello"
    * precondition: englishString follows a format: each word is separated by a space, and doesn't contain symbols
    */
    public static void translateToMorse(String englishString){
        String[] englishArr = englishString.split("");
        for (String eng: englishArr){
            System.out.print(convertChar(eng, true)+" ");
        }
        System.out.println();
    }

    
    /*
    * String convertChar(): 
    * convert a character in english to morse code character or vice versa
    * @param: originalChar, a character in English or Morse Code, stored in a string data type variable: "A", ".-"
    * @param: toMorse, a boolean indicating the conversion is Eng -> Morse (true), or Morse -> Eng (false)
    * precondition: originalChar must be a string with a single English character (alphabet or difit), or a single Morse code character
    * postcondition: this method return a string with a single corresponding character in Morse or English. If no character mataches, return null
    */
    public static String convertChar(String originalChar, boolean toMorse){
        String[] engCharDict = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","1","2","3","4","5","6","7","8","9","0"," "};
        String[] morCharDict = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--..",".----", "..---","...--","....-",".....","-....","--...","---..","----.","-----","|"};

        
        for (int i=0; i<engCharDict.length; i++){
            if (toMorse){
                if (engCharDict[i].equalsIgnoreCase(originalChar)) return morCharDict[i];
            } else {
                if (morCharDict[i].equalsIgnoreCase(originalChar)) return engCharDict[i];
            }
        }

        return null;
    }



    
}