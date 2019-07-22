package account;

import atm.ATM;
import fileManager.FileManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public interface Card {
    //созадем новую карту
     static CardImpl createNewCard(long cardNumber, int PIN, double balance, boolean isLocked, String lockDate, String unlockDate){
        return new CardImpl(cardNumber, PIN, balance, isLocked, lockDate, unlockDate);
    }
    //получаем номер карты
     long getNumber();
     //получаем пинкод
     int getPin();
     //ставим новый пинкод
     void setPin(int PIN);
     //ну тут все понятно
     double getBalance();
     void addMoney(double sum);
     void withdrawMoney(double sum);
     void lockCard();
     void unlockCard();
     boolean isLocked();
     String getLockDate();
     String getUnlockDate();
     static void checkLocked(){
         ATM.cards.forEach((k, v)->{
             if (v.isLocked()){
                 try {

                     if(new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss").parse(v.getUnlockDate()).before(new Date())){
                         System.out.println("unlocked");
                         v.unlockCard();
                         FileManager.cardFileManager.refreshData();
                     }
                 } catch (ParseException e) {
                     System.out.println("Не удалось распарсить дату");
                     e.printStackTrace();
                 }

             }
         });
     };

}
