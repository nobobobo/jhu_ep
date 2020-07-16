public class GamePlayer extends Player{
    private int money;
    private int bet;

    GamePlayer(String name, int money){
        super(name);
        this.money = money;
        this.bet = 0;
    }

    public void placeBet(int bet){
        this.money -= bet;
        this.bet = bet;
    }

    public void win(){
        this.money += 2 * this.bet;
        this.bet = 0;
    }

    public void lose(){
        this.bet = 0;
    }
}