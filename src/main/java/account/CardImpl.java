package account;

import fileManager.FileManager;
import java.text.SimpleDateFormat;
import java.util.Date;
        //тут все тоже понятно
 class CardImpl implements Card {
    private long cardNumber;
    private int PIN;
    private double balance;
    private boolean isLocked;
    private String lockDate;
    private String unlockDate;

   CardImpl(long cardNumber, int PIN, double balance, boolean isLocked, String lockDate, String unlockDate) {
        this.cardNumber = cardNumber;
        this.PIN = PIN;
        this.balance = balance;
        this.isLocked = isLocked;
        this.lockDate = lockDate;
        this.unlockDate = unlockDate;
    }

    public long getNumber() {
        return this.cardNumber;
    }

    public int getPin() {
        return this.PIN;
    }

    public void setPin(int PIN){
       this.PIN = PIN;
    }

    public double getBalance() {
        return this.balance;
    }

    private void setBalance(double newBalance) {
       this.balance = newBalance;
    }

    public void addMoney(double sum){
       setBalance(getBalance()+sum);
    }

    public void withdrawMoney(double sum){
        setBalance(getBalance()-sum);
        FileManager.cardFileManager.refreshData();
    }

    public void lockCard() {
        this.isLocked=true;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
        String dateNow = format.format(new Date());
        System.out.println(dateNow);
        setLockDate(dateNow);
        Date date = new Date();
        setUnlockDate(format.format(new Date(date.getTime()+60*1000)));
    }

    public void unlockCard() {
        this.isLocked=false;
        this.unlockDate=null;
        this.lockDate=null;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public String getLockDate() {
        return lockDate;
    }

    public String getUnlockDate() {
        return unlockDate;
    }

     private void setLockDate(String lockDate) {
         this.lockDate = lockDate;
     }
     private void setUnlockDate(String unlockDate){
       this.unlockDate=unlockDate;
     }

 }
