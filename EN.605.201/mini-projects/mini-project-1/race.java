public class race {
    /*
     * Method: main initialize positions of tortoise and hare, also the length of
     * the race use a while loop to iterate each contenders moves If the position is
     * below the initial position, the position is set back as 1, if the position is
     * over the length the race, the position is set to the length so that the
     * renderPosition can accurately render out each contender's position
     * 
     * After at least one of the contenders passes the finish line, the method
     * print's out the winner.
     *
     */
    public static void main(String[] args) {
        int tortoisePos = 1;
        int harePos = 1;
        int MAX_LIMIT = 50;

        System.out.println("AND THEY'RE OFF!!");

        while (tortoisePos < MAX_LIMIT && harePos < MAX_LIMIT) {
            tortoisePos += moveOnce(true);
            harePos += moveOnce(false);

            if (tortoisePos < 1)
                tortoisePos = 1;
            if (harePos < 1)
                harePos = 1;

            if (tortoisePos > MAX_LIMIT)
                tortoisePos = MAX_LIMIT;
            if (harePos > MAX_LIMIT)
                harePos = MAX_LIMIT;

            renderPosition(tortoisePos, harePos, MAX_LIMIT);
        }

        if (tortoisePos >= MAX_LIMIT && harePos >= MAX_LIMIT) {
            System.out.println("IT'S A TIE!!");
        } else if (tortoisePos >= MAX_LIMIT) {
            System.out.println("TORTOISE WINS!!");
        } else {
            System.out.println("HARE WINS!!");
        }
    }

    /*
     * Method: moveOnce(Boolean isTortoise) This method using a random integer
     * between 1 ~ 10 and decide contender's move returns a number of squares that
     * contender moves next, positive: to the right, negative: to the left
     * 
     * @param: Boolean isTortoise: a boolean represents the method is deciding the
     * next move steps for Tortoise or not(for Hare)
     *
     */
    public static int moveOnce(Boolean isTortoise) {
        int random_int = (int) (Math.random() * 10 + 1);
        int move = 0;
        if (isTortoise) {
            if (random_int <= 5) {
                move = 3;
            } else if (random_int <= 8) {
                move = 1;
            } else if (random_int <= 10) {
                move = 6;
            }
        } else {
            if (random_int <= 2) {
                move = 9;
            } else if (random_int <= 5) {
                move = 1;
            } else if (random_int <= 6) {
                move = -12;
            } else if (random_int <= 8) {
                move = -2;
            }
        }

        return move;
    }

    /*
     * Method: renderPosition(int tortoisePos, int harePos, int maxPos) This method
     * renders out the contender's position ("T" for tortoise, "H" for hare, or
     * "OUCH!!" if two contenders at the same position)
     * 
     * @param: int tortoisePos: an integer represents tortoise's position, between 1
     * ~ MAX_LIMIT
     * 
     * @param: int harePos: an integer represents hare's position, between 1 ~
     * MAX_LIMIT
     * 
     * @param: int maxPos: the maximum number of the squares(positions), decides the
     * length of the std output.
     * 
     */
    public static void renderPosition(int tortoisePos, int harePos, int maxPos) {
        for (int i = 1; i <= maxPos; i++) {
            if (i == tortoisePos && i == harePos) {
                System.out.print("OUCH!!");
            } else if (i == tortoisePos) {
                System.out.print("T");
            } else if (i == harePos) {
                System.out.print("H");
            } else {
                System.out.print(" ");
            }
        }
        System.out.println("");
    }
}