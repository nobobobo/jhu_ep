import java.util.Scanner;

public class BlackjackGameSimulator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of the player: ");
        String playerName = sc.nextLine();
        System.out.println("Enter the name of the Dealer: ");
        String dealerName = sc.nextLine();

        GamePlayer gPlayer = new GamePlayer(playerName, 100);
        Dealer dealer = new Dealer(dealerName);
        System.out.println();

        Deck deck = new Deck();
        deck.init();
        deck.shuffle();

        int bet = 1;

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

    public static void playNewGame(GamePlayer gPlayer, Dealer dealer, Deck deck, int bet, Scanner sc) {
        System.out.println("New game starts! Bet amount: " + bet);
        gPlayer.newGame(deck);
        dealer.newGame(deck);

        gPlayer.placeBet(bet);
        Boolean isPlayerDone = false;

        gPlayer.drawCard(deck);
        dealer.drawCard(deck);
        gPlayer.drawCard(deck);
        dealer.drawCard(deck);

        System.out.println(dealer.toString(isPlayerDone));

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

        if (gPlayer.getHandPoint() > 21) {
            System.out.println(gPlayer.toString());
            System.out.println("Burst! Sorry you lose...");
            System.out.println();
            gPlayer.lose();
            return;
        }

        System.out.println("Dealer is drawing cards... ");
        dealer.drawCards(deck);
        System.out.println(dealer.toString(isPlayerDone));
        System.out.println();

        if (dealer.getHandPoint() > 21 || dealer.getHandPoint() < gPlayer.getHandPoint()) {
            System.out.println("You win!");
            System.out.println();
            gPlayer.win();
        } else if (dealer.getHandPoint() > gPlayer.getHandPoint()) {
            System.out.println("You lose...");
            System.out.println();
            gPlayer.lose();
        } else {
            System.out.println("TIE.");
            System.out.println();
            gPlayer.tie();
        }
    }

}