import java.util.Arrays;
import java.lang.Integer;

public class Card {
    private String suit;
    private String number;

    Card(String suit, String number){
        this.suit = suit;
        this.number = number;
    }

    public String getSuit(){
        return this.suit;
    }

    public String getNumber(){
        return this.number;
    }

    public int getPoint(){
        if (Arrays.asList("J","Q","K").contains(getNumber())){
            return 10;
        } else if (getNumber() == "A"){
            return 1;
        } else {
            return Integer.parseInt(getNumber());
        }
    }

    public String toString(){
        return this.suit+ this.number;
    }
}