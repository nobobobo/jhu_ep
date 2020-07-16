public class Player{
    private int money;

    Player(){
        this.money = 0;
    }

    Player(int money){
        this.money = money;
    }

    public void setMoney(int money){
        this.money = money;
    }

    public int getMoney(){
        return this.money;
    } 
}