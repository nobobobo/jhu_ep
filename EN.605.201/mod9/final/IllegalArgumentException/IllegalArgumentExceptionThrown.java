public class IllegalArgumentExceptionThrown {
    public static void main(String[] args) {
        MyNumber myNum = new MyNumber(-10.0);
        System.out.println(myNum.myLog());
    }
}

class MyNumber {
    private double num;

    MyNumber(double num){
        this.num = num;
    }

    public double getNum(){
        return this.num;
    }

    public double myLog(){
        if (getNum() <= 0){
            throw new IllegalArgumentException();
        }
        return Math.log(getNum());
    }
    
}