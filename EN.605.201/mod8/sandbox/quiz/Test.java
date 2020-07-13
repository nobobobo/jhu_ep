public class Test {
    public static void main(String[] args) {
        String str = "Now is the time for all";
        System.out.println("\'"+str.substring( 4, 15)+"\'");

        SpecialCheckingAccount test = new SpecialCheckingAccount();
        System.out.println(test.getRate());
    }

}

class BankAccount{
     private double accountBalance;
     public BankAccount() { accountBalance = 0; }
     public BankAccount( double b ) { accountBalance = b; }
}

class SpecialCheckingAccount extends BankAccount
{
     private double interestRate;
     public SpecialCheckingAccount() { 
        super();
      }

      public double getRate(){
          return this.interestRate;
      }
}

