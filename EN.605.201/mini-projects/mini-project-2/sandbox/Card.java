public class Card {
    private String suit;
    private int number;

    Card(String suit, int number){
        this.suit = suit;
        this.number = number;
    }

    public String getSuit(){
        return this.suit;
    }

    public int getNumber(){
        return this.number;
    }

    public int getPoint(){
        if (getNumber()>= 11){
            return 10;
        } else {
            return getNumber();
        }
    }

    public String toString(){
        return this.suit+ this.number;
    }
}