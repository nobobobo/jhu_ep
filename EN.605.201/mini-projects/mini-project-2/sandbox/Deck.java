import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> deck;

    Deck(){
        this.deck = new ArrayList<Card>();
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
        return drawn;
    }

    public static void main(String args[]){
        Deck deck = new Deck();
        deck.init();
        deck.shuffle();
        Card newCard = deck.drawOne();
        System.out.println(newCard.getSuit() + ", "+ newCard.getNumber());
    }
}