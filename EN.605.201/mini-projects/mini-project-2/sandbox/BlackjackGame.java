public class BlackjackGame {

    public static void main(String[] args) {
        GamePlayer gPlayer = new GamePlayer("Noboru", 100);
        Dealer dealer = new Dealer("Dealer");

        Deck deck = new Deck();
        deck.init();
        deck.shuffle();

        gPlayer.drawCard(deck);
        gPlayer.drawCard(deck);

        dealer.drawCards(deck);

        System.out.println(gPlayer.toString());
        System.out.println(dealer.toString());

        gPlayer.newGame(deck);
        dealer.newGame(deck);

        gPlayer.drawCard(deck);
        gPlayer.drawCard(deck);

        dealer.drawCards(deck);

        System.out.println(gPlayer.toString());
        System.out.println(dealer.toString());


    }
    
}