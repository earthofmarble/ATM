package fileManager;

import atm.ATM;
import atm.Banknote;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

 class BanknoteFileManager implements FileManager {

    //читаем список строк, элементы в строке разделены пробелом
    public ArrayList<Object> getData(ArrayList<String> strings) {
        ArrayList<Object> banknotes = new ArrayList<>();
        for (int i=0; i<strings.size(); i++) {
            List<String> tempBanknoteInfo = Arrays.asList(strings.get(i).split("\\s+"));
            int value = Integer.parseInt(tempBanknoteInfo.get(0));
            int amount = Integer.parseInt(tempBanknoteInfo.get(1));
            banknotes.add(new Banknote(value, amount));
        }
        return banknotes;
    }
        //обновляем файлы
     public void refreshData() {
         BufferedWriter writer = null;
         try {
             File banknotesFile = new File(ATM.BANKNOTES_PATH);
             if (banknotesFile.exists() && banknotesFile.isFile()) {
                 try {
                     banknotesFile.delete();
                     banknotesFile.createNewFile();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             } else {
                 banknotesFile.createNewFile();
             }

             writer = new BufferedWriter(new FileWriter(banknotesFile, true));


             BufferedWriter finalWriter = writer;
             ATM.banknotes.forEach((k, v)->{
                 try {
                     finalWriter.write(v.getValue() + " " + v.getAmount());
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
