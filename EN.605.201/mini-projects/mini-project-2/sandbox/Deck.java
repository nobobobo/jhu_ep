import java.util.ArrayList;
import java.util.Collections;
/**
 * Deck class
 */
public class Deck {
    private ArrayList<Card> deck;
    private ArrayList<Card> used;

    /**
     * Deck():
     * Constutor of Deck class
     */
    Deck(){
        this.deck = new ArrayList<Card>();
        this.used = new ArrayList<Card>();
    }

    /**
     * ArrayList<Card> getDeck():
     * Deck getter
     * @return ArrayList<Card> 
     */
    public ArrayList<Card> getDeck(){
        return this.deck;
    }

    /**
     * ArrayList<Card> getUsed()
     * Used getter
     * @return ArrayList<Card>
     */
    public ArrayList<Card> getUsed(){
        return this.used;
    }

    /**
     * void init():
     * 
     * initialize a deck with 4 suits * 13 numbers of cards
     */
    public void init(){
        String[] suits = {"♠︎","❤︎","♦︎","♣︎"};
        String[] numbers = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};

        for (String suit: suits){
            for (String number: numbers){
                this.deck.add(new Card(suit, number));
            }
        }
    }

    /**
     * void shuffle():
     * shuffles the deck 
     */
    public void shuffle(){
        Collections.shuffle(this.deck);
    }
    
    /**
     * Card drawOne():
     * draw one card from the Deck's deck arraylist.
     * If the deck's size is 0, adding the used cards to deck and shuffling again.
     * @return Card
     */
    public Card drawOne(){
        Card drawn = this.deck.get(0);
        this.deck.remove(0);
        if (this.deck.size() == 0){
            this.deck = this.used;
            this.used = new ArrayList<Card>();
            shuffle();
        }
        return drawn;
    }

    /**
     * void collectUsed(ArrayList<Card> usedCards):
     * collect usedCards and add to used arraylist
     * @param usedCards
     * 
     */
    public void collectUsed(ArrayList<Card> usedCards){
        for (Card card: usedCards){
            this.used.add(card);
        }
    }
}