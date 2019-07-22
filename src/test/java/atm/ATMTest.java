package atm;

import org.junit.Test;

import java.util.*;

public class ATMTest {

    @Test
    public void withdrawInterface() {
        ArrayList<Banknote> banknotes = new ArrayList<>();
        banknotes.add(new Banknote(5, 11));
        banknotes.add(new Banknote(20, 2));
        banknotes.add(new Banknote(10, 8));

        SortedMap<Number, Integer> hashMap = new TreeMap<>();
        for (Banknote banknote : banknotes) {
            hashMap.put(banknote.getValue(), banknote.getAmount());
        }

        int sum = 70;
        int buf=0;
          hashMap.forEach((key1, key2) ->{
              System.out.println(key1);
          });
        ArrayList<Integer> list2 = new ArrayList<>();
        hashMap.keySet().forEach(set ->{
            list2.add((int) set);
        });
      //  list2.addAll(hashMap.keySet());
        list2.forEach(System.out::println);

        int tempI = list2.get(0);

        for (int f=0; f<sum/list2.get(0); f++){
            for (int t=0; t<(sum-(f*list2.get(0))/list2.get(1)); t++){
                for (int tw=0; tw<(sum-(f*list2.get(0))-(tw*list2.get(1))/list2.get(2)); tw++){
                    if (f*5+t*10+tw*20==sum){
                        System.out.println("f: " + f +" t: " + t + " tw: " + tw);
                    }
                }
            }
        }


    }


}