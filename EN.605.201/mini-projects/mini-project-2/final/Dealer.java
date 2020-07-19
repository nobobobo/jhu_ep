/**
 * Dealer class extended from Player
 */
public class Dealer extends Player {

    /**
     * Dealer(String name) :
     * Constructor
     * @param name
     */
    Dealer(String name) {
        super(name);
    }

    /**
     * void drawCards(Deck deck):
     * the dealer has to draw cards until the total hand point reaches 17
     * @param deck
     */

    public void drawCards(Deck deck) {
        while (getHandPoint() < 17) {
            drawCard(deck);
        }
    }

    /**
     * String toString(Boolean isPlayerDone):
     * Since the user cannot see the dealer's first card until staying,
     * the toString only renders the second card and point of it if the player's action is not done. 
     * @param isPlayerDone 
     * @return String
     * 
     */
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