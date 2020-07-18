import java.util.Scanner;

/**
 * <h2>BlackjackGameSimulator</h2> a command line based program
 * that simulate blackjack games between a player and a dealer.
 * 
 * @author  Noboru Hayashi
 * @version 1.0
 * @since   2020-07-16
 */
public class BlackjackGameSimulator {

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        // prompts the user's inputs for player's and dealer's name
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of the player: ");
        String playerName = sc.nextLine();
        System.out.println("Enter the name of the Dealer: ");
        String dealerName = sc.nextLine();

        // create GamePlayer and Dealer class. GamePlayer is initialized by money = 100
        GamePlayer gPlayer = new GamePlayer(playerName, 100);
        Dealer dealer = new Dealer(dealerName);
        System.out.println();

        // initialize a deck
        Deck deck = new Deck();
        deck.init();
        deck.shuffle();

        int bet = 1;

        // prompts the user for the amound of betting
        // then runs a new blackjack game
        while (bet != 0) {
            System.out.println(gPlayer.getName() + " has " + gPlayer.getMoney()
                    + " dollars remaining. Please bet (Enter 0 to quit): ");
            bet = sc.nextInt();
            sc.nextLine();
            if (bet == 0) {
                break;
            }
            if (gPlayer.getMoney() < bet) {
                System.out.println("Sorry, you cannot bet over you have.");
            } else {
                System.out.println();
                playNewGame(gPlayer, dealer, deck, bet, sc);
            }
        }

        System.out.println("Game Over!");
    }

    /**
     * void playNewGame(GamePlayer gPlayer, Dealer dealer, Deck deck, int bet, Scanner sc):
     * 
     * a function that simulates one round of Blackjack game, given GamePlayer, Dealer, Deck, bet, Scanner
     * @param gPlayer
     * @param dealer
     * @param deck
     * @param bet
     * @param sc
     */
    public static void playNewGame(GamePlayer gPlayer, Dealer dealer, Deck deck, int bet, Scanner sc) {
        System.out.println("New game starts! Bet amount: " + bet);
        // initialize player's hands by newGame(Deck)
        gPlayer.newGame(deck);
        dealer.newGame(deck);

        // GamePlayer places a bet
        gPlayer.placeBet(bet);
        Boolean isPlayerDone = false;

        // GamePlayer and Dealer each draws card twice 
        gPlayer.drawCard(deck);
        dealer.drawCard(deck);
        gPlayer.drawCard(deck);
        dealer.drawCard(deck);

        // renders out the dealer's hands info
        System.out.println(dealer.toString(isPlayerDone));

        // prompt the user to HIT or STAY
        while (!isPlayerDone && gPlayer.getHandPoint() <= 21) {
            System.out.println(gPlayer.toString());
            System.out.println();
            System.out.println("Please select \"Hit\" or \"Stay\": ");
            String choice = sc.next();
            if (choice.equals("Hit")) {
                gPlayer.drawCard(deck);
            } else if (choice.equals("Stay")) {
                isPlayerDone = true;
            }
            System.out.println();
        }



        // After the gameplayer's actions are done.

        // Check the gameplayer's hand is over 21 or not
        if (gPlayer.getHandPoint() > 21) {
            System.out.println(gPlayer.toString());
            System.out.println("Burst!");
            System.out.println();
            gPlayer.lose();
            return;
        }

        // If the gameplayer's hand doesn't burst, the dealer draws cards
        System.out.println("Dealer is drawing cards... ");
        dealer.drawCards(deck);
        System.out.println(dealer.toString(isPlayerDone));
        System.out.println();

        // Compare handpoints of the gameplayer and the dealer
        // Check who's winner
        if (dealer.getHandPoint() > 21 || dealer.getHandPoint() < gPlayer.getHandPoint()) {
            System.out.println(gPlayer.getName()+" win!");
            System.out.println();
            gPlayer.win();
        } else if (dealer.getHandPoint() > gPlayer.getHandPoint()) {
            System.out.println(dealer.getName()+" wins...");
            System.out.println();
            gPlayer.lose();
        } else {
            System.out.println("TIE.");
            System.out.println();
            gPlayer.tie();
        }
    }

}