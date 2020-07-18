import java.util.Arrays;
import java.lang.Integer;

/**
 * Card class
 */
public class Card {
    private String suit;
    private String number;

    /**
     * Card(String suit, String number):
     * Constructor with two arguments: a card's suit and number(rank)
     * @param suit
     * @param number
     */
    Card(String suit, String number){
        this.suit = suit;
        this.number = number;
    }

    /** 
     * String getSuit():
     * suit getter
     */
    public String getSuit(){
        return this.suit;
    }

    /**
     * String getNumber():
     * number getter
     */
    public String getNumber(){
        return this.number;
    }

    /**
     * int getPoint():
     * calculates the card's point by its number and returns it
     * @return int
     */
    public int getPoint(){
        if (Arrays.asList("J","Q","K").contains(getNumber())){
            return 10;
        } else if (getNumber() == "A"){
            return 1;
        } else {
            return Integer.parseInt(getNumber());
        }
    }

    /**
     * String toString():
     * renders the card's information
     */
    public String toString(){
        return this.suit+ this.number;
    }
}