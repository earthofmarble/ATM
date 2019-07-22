import account.Card;

import static java.lang.Thread.sleep;

 class UnlockingThread implements Runnable
{
    private boolean tr = true;

    public static void setTr(boolean tr) {
        tr = tr;
    }

    public void run()
    {
        while (tr) {
            try {
                sleep(20000);  //каждые 20 сек проверяем не появились ли кандидаты на разбан
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Card.checkLocked();
        }
        return;
    }
}
