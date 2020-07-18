public class Dealer extends Player {

    Dealer(String name) {
        super(name);
    }

    public void drawCards(Deck deck) {
        while (getHandPoint() < 17) {
            drawCard(deck);
        }
    }

    public String toString(Boolean isPlayerDone) {
        String ret = getName() + "\'s cards: ";
        int handPoint = getHandPoint();
        
        if (isPlayerDone){
            ret += getHand().get(0).toString() + " ";
        } else {
            ret += "<Hidden> ";
            handPoint -= getHand().get(0).getPoint();
        }

        for (int i = 1; i < getHand().size(); i++) {
            ret += getHand().get(i).toString() + " ";
        }
        ret += "\nHand Point is: " + handPoint;

        return ret;
    }
}