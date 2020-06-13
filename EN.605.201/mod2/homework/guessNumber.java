import java.util.Scanner;

public class guessNumber {
    public static void main(String[] args) {
        // prompt the user for the upperbound of the random number.
        Scanner scan = new Scanner(System.in);
        System.out.println("Please type an upperbound integer N: ");
        int max = scan.nextInt();

        // prompt the user for the max number of guesses
        System.out.println("Please type the maximum number of guesses: ");
        int maxGuess = scan.nextInt();

        // initialize cnt and isCorrect
        int cnt = 1;
        boolean isCorrect = false;

        // generate a random number 
        int randomNumber = (int) (max * Math.random()) + 1;

        // keep prompting and checking the number until the guess is correct or the # of guesses reach the maxGuess
        while (!isCorrect && cnt <= maxGuess) {
            // prompt the user for the guess
            System.out.println("Please guess the secret number, press 0 to exit.");
            int guess = scan.nextInt();

            // typed 0 to quit the loop
            if (guess == 0){
                break;
            }

            // correct
            if (guess == randomNumber) {
                System.out.println("You're correct! You have guessed " + cnt + " times.");
                isCorrect = true;
            
            // too high
            } else if (guess > randomNumber) {
                System.out.println("Too high!");

            // too low
            } else {
                System.out.println("Too low!");
            }

            cnt++;
        }

        // if the user exitted the loop or reached the maxGuess, print Game over. 
        if (!isCorrect || cnt > maxGuess) {
            System.out.println("GAME OVER!");
        }

    }

}