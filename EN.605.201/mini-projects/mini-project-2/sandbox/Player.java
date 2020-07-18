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

    public void newGame(Deck deck){
        ArrayList<Card> usedCards = this.hand;
        this.hand = new ArrayList<Card>();
        deck.collectUsed(usedCards);
    }

    public void drawCard(Deck deck){
        this.hand.add(deck.drawOne());
    }

    public int getHandPoint(){
        int point = 0;
        for (Card card: this.hand){
            point += card.getPoint();
        }
        return point;
    }

    public String toString(){
        String ret = this.name+"\'s cards: ";
        for (Card card:this.hand){
            ret += card.toString() +" ";
        }
        ret += "\nHand Point: " + getHandPoint();

        return ret;
    }
}