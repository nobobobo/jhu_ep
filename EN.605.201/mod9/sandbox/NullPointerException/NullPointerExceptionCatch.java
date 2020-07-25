public class NullPointerExceptionCatch {
    public static void main(String[] args) {
        try {
            String nullVariable = null;
            System.out.println(nullVariable.equals(null));

        } catch (NullPointerException exception) {
            System.out.println("This program caught: " + exception);
        }
    }

}