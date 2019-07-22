package fileManager;

import java.io.*;
import java.util.ArrayList;

public interface FileManager {

   FileManager cardFileManager = new CardFileManager();
   FileManager banknoteFileManager = new BanknoteFileManager();

      ArrayList<Object> getData(ArrayList<String> strings);
      void refreshData();
        //читаем построчно файлы и записываем строки в списки
      static ArrayList<String> readFile(String filePath){
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String strLine;
            while ((strLine = reader.readLine()) != null) {
                list.add(strLine);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.err.println("Не удалось открыть файл с аккаунтами! Проверьте указанный к файлу путь");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Произошла ошибка. Файл не доступен");
            e.printStackTrace();
        }
        return list;
    }
}
