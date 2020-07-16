import java.util.ArrayList;

public abstract class Player{
    private String name;
    private ArrayList<Card> hand;


    Player(String name){
        this.name = name;
        this.hand = new ArrayList<Card>();
    }

    public String getName(){
        return this.name;
    }

    public ArrayList<Card> getHand(){
        return this.hand;
    }

    public ArrayList<Card> newGame(){
        ArrayList<Card> usedCards = this.hand;
        this.hand = new ArrayList<Card>();
        return usedCards;
    }

    public void addCard(Card card){
        this.hand.add(card);
    }

    public int getHandPoint(){
        int point = 0;
        for (Card card: this.hand){
            point += card.getPoint();
        }
        return point;
    }
}