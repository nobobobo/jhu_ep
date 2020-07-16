import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> deck;
    private ArrayList<Card> used;

    Deck(){
        this.deck = new ArrayList<Card>();
        this.used = new ArrayList<Card>();
    }

    public void init(){
        String[] suits = {"S","H","D","C"};
        int[] numbers = {1,2,3,4,5,6,7,8,9,10,11,12,13};

        for (String suit: suits){
            for (int number: numbers){
                this.deck.add(new Card(suit, number));
            }
        }
    }

    public void shuffle(){
        Collections.shuffle(this.deck);
    }
    
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

    public void collectUsed(ArrayList<Card> usedCards){
        for (Card card: usedCards){
            this.used.add(card);
        }
    }

    public static void main(String args[]){
        Deck deck = new Deck();
        deck.init();
        deck.shuffle();
        Card newCard = deck.drawOne();
        System.out.println(newCard.getSuit() + ", "+ newCard.getNumber());
    }
}