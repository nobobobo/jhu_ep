public class BlackjackGame {

    public static void main(String[] args) {
        GamePlayer gPlayer = new GamePlayer("Noboru", 100);
        Dealer dealer = new Dealer("Dealer");

        Deck deck = new Deck();
        deck.init();
        deck.shuffle();

        gPlayer.addCard(deck.drawOne());
        gPlayer.addCard(deck.drawOne());

        dealerDraws(dealer, deck);

        System.out.println(gPlayer.getName()+ ": "+ gPlayer.getHandPoint());
        System.out.println(dealer.getName() + ": "+ dealer.getHandPoint());

        deck.collectUsed(gPlayer.newGame());
        deck.collectUsed(dealer.newGame());

        gPlayer.addCard(deck.drawOne());
        gPlayer.addCard(deck.drawOne());

        dealerDraws(dealer, deck);

        System.out.println(gPlayer.getName()+ ": "+ gPlayer.getHandPoint());
        System.out.println(dealer.getName() + ": "+ dealer.getHandPoint());


    }

    public static void dealerDraws(Dealer dealer, Deck deck){
        while (dealer.getHandPoint() < 17){
            dealer.addCard(deck.drawOne());
        }
    }


    
}