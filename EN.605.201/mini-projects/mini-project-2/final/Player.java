import java.util.ArrayList;
/**
 * Abstract Player class
 */
public abstract class Player{
    private String name;
    private ArrayList<Card> hand;

    /**
     * Player(String name)
     * Constructor with an argument of the player's name
     */
    Player(String name){
        this.name = name;
        this.hand = new ArrayList<Card>();
    }

    /**
     * String getName()
     * Name getter
     * @return: String
     */
    public String getName(){
        return this.name;
    }

    /**
     * ArrayList<Card> getHand()
     * Hand getter
     * @return ArrayList<Card> 
     */
    public ArrayList<Card> getHand(){
        return this.hand;
    }

    /** 
     * void newGame(Deck deck):
     * initizaling a new game by resetting the hand,
     * and putting back the cards the player has.
     * @param Deck
     */
    public void newGame(Deck deck){
        ArrayList<Card> usedCards = this.hand;
        this.hand = new ArrayList<Card>();
        deck.collectUsed(usedCards);
    }

    /**
     * void drawCard(Deck deck):
     * draw one card from the deck and add to the player's hand
     * @param Deck
     */
    public void drawCard(Deck deck){
        this.hand.add(deck.drawOne());
    }

    /**
     * int getHandPoint()
     * calculates and returns the player's total hand point 
     * @return int
     */
    public int getHandPoint(){
        int point = 0;
        for (Card card: this.hand){
            point += card.getPoint();
        }
        return point;
    }

    /** 
     * String toString()
     * return a string that contains the player's hands info 
     * @return String
     */
    public String toString(){
        String ret = this.name+"\'s cards: ";
        for (Card card:this.hand){
            ret += card.toString() +" ";
        }
        ret += "\nHand Point: " + getHandPoint();

        return ret;
    }
}