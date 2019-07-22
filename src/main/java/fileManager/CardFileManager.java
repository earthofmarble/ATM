package fileManager;

import account.Card;
import atm.ATM;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

 class CardFileManager implements FileManager {
            //читаем список строк, элементы в строке разделены пробелом
    public ArrayList<Object> getData(ArrayList<String> strings) {
        ArrayList<Object> cards = new ArrayList<>();
        for (int i=0; i<strings.size(); i++) {
            List<String> tempCardInfo = Arrays.asList(strings.get(i).split("\\s+"));
            long cardNumber = Long.parseLong(tempCardInfo.get(0));
            int cardPin = Integer.parseInt(tempCardInfo.get(1));
            double cardBalance = Double.parseDouble(tempCardInfo.get(2));
            boolean cardIsLocked = Boolean.parseBoolean(tempCardInfo.get(3));
            String cardLockDate = null;
            String cardUnlockDate = null;
            if (cardIsLocked) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
                try {
                    Date cardLock = format.parse(tempCardInfo.get(4));
                    Date cardUnlock = format.parse(tempCardInfo.get(5));

                    cardLockDate = format.format(cardLock);
                    cardUnlockDate = format.format(cardUnlock);

                } catch (ParseException e) {
                    System.err.println("Не удалось запарсить дату, проверьте правильность даты в файле с пользовательскими данными. Дата(в нашем случае время) блокировки должна быть в формате yyyy-MM-dd/HH:mm:ss. СТРОКА #" + (i+1));
                    e.printStackTrace();
                }
            }

            cards.add(Card.createNewCard(cardNumber, cardPin, cardBalance, cardIsLocked, cardLockDate, cardUnlockDate));
        }
        return cards;
    }
            //обновляем файлы
     public void refreshData() {
         BufferedWriter writer = null;
         try {
             File cardsFile = new File(ATM.CARDS_PATH);
             if (cardsFile.exists() && cardsFile.isFile()) {
                 try {
                     cardsFile.delete();
                     cardsFile.createNewFile();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             } else {
                 cardsFile.createNewFile();
             }

             writer = new BufferedWriter(new FileWriter(cardsFile, true));

             BufferedWriter finalWriter = writer;
             ATM.cards.forEach((k, v)->{
                 try {
                     finalWriter.write(v.getNumber() + " " + v.getPin()+ " " + v.getBalance() + " " +v.isLocked() + " " + v.getLockDate() + " " +v.getUnlockDate());
                     finalWriter.write("\r");
                 } catch (IOException e) {
                     System.err.println("Не удалось записать в файл! ");
                     e.printStackTrace();
                 }

             });

             writer.flush();
             writer.close();

         } catch (IOException e) {
             System.err.println("Не удается создать/открыть/записать (в) файл с указанным именем! " + e);
         }
     }

}