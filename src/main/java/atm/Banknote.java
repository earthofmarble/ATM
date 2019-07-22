package atm;

public class Banknote { //банкнота

   private int value;  //номинал
   private int amount;  //количество

   public Banknote(int value, int amount){
        this.value=value;
        this.amount=amount;
    }

    public int getAmount() {
        return amount;
    }

    public int getValue() {
        return value;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
