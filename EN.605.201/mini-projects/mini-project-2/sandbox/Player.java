import java.util.ArrayList;

public abstract class Player{
    private String name;
    private ArrayList<Card> hand;


    Player(String name){
        this.name = name;
        this.hand = new ArrayList<Card>();
    }
}