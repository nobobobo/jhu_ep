public class Dealer extends Player{

    Dealer(String name){
        super(name);
    }

    public void drawCards(Deck deck){
        while (getHandPoint() < 17){
            drawCard(deck);
        }
    }
}