import atm.ATM;
import atm.AtmInterface;

public class Main {

    public static void main(String[] args){
        ATM atm = new ATM();  //создаем банкомат
        UnlockingThread th = new UnlockingThread(); //создаем побочный поток, который будет проверять сроки блокировок карт
        Thread thread = new Thread(th); //инициализируем
        thread.start();  //запускаем

        boolean tr = true;

        while (tr) {
         tr = atm.authorization();    //страница авторизации в бесконечном цикле
      }
        UnlockingThread.setTr(false); //завершаем поток проверок
        System.exit(0);
    }
}
