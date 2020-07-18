/**
 * GamePlayer class extended from abstract class: Player
 */

public class GamePlayer extends Player{
    private int money;
    private int bet;

    /**
     * GamePlayer(String name, int money):
     * Constructor with arguments of name and money
     * @param name
     * @param money
     */
    GamePlayer(String name, int money){
        super(name);
        this.money = money;
        this.bet = 0;
    }

    /**
     * int getMoney():
     * Getter method for money attribute.
     * @return int
     */
    public int getMoney(){
        return this.money;
    }

    /**
     * int getBet():
     * Getter method for bet attribute
     * @return int
     */
    public int getBet(){
        return this.bet;
    }

    /**
     * void placeBet(int bet):
     * places a new bet by adding the bet attribute, abstracting the money attribute by the argument bet
     * @param bet
     */
    public void placeBet(int bet){
        this.money -= bet;
        this.bet = bet;
    }

    /**
     * void win():
     * if the Gameplayer wins, double the bet and add it to the money,
     * then reset the bet attribute
     */
    public void win(){
        this.money += 2 * this.bet;
        this.bet = 0;
    }

    /** 
     * void lose():
     * if the GamePlayer loses, reseting the bet attribute only
     */
    public void lose(){
        this.bet = 0;
    }

    /** 
     * void tie():
     * if the Game is tied, the bet money is returned to the money attribute
     */
    public void tie(){
        this.money += this.bet;
        this.bet = 0;
    }
    
}