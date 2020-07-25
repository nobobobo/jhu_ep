public class IllegalArgumentExceptionCatch {
    public static void main(String[] args) {
        try {
        MyNumber myNum = new MyNumber(-10.0);
        System.out.println(myNum.myLog());
        } catch (IllegalArgumentException exception){
            System.out.println("This program caught: " + exception);
        }
    }
}

class MyNumber {
    private double num;

    MyNumber(double num) {
        this.num = num;
    }

    public double getNum() {
        return this.num;
    }

    public double myLog() {
        if (getNum() <= 0) {
            throw new IllegalArgumentException();
        }
        return Math.log(getNum());
    }

}