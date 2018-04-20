import java.util.*;

public class Sorts{

    public static void radixsort(MyLinkedListImproved<Integer> data) {
        if (data.size() < 2) return;
        // System.out.println("unsorted:" + data);
        int numbItera = ("" + data.maxValue()).length();
        @SuppressWarnings("unchecked")
        MyLinkedListImproved<Integer>[] arr = new MyLinkedListImproved[10];
        for (int i=0; i<10; i++){
            arr[i] = new MyLinkedListImproved<Integer>();
        }
        for (int i=0; i<numbItera; i++){
            for (int num: data){
                arr[nDigit(num, i)].addEnd(num);
            }
            data.clear();
            for (MyLinkedListImproved<Integer> j: arr){
                data.extend(j);
                j.clear();
            }
            // System.out.println("iteration: " + i + " " + data);
        }
    }

    public static int nDigit(int numb, int n){
        int div = (int) Math.pow(10, n);
        return numb % (div*10) / div; 
    }

    public static void radixsortIncludingNegatives(MyLinkedListImproved<Integer> data){ 
        MyLinkedListImproved<Integer> negatives = removeNegatives(data);
        for (Integer i: negatives){
            data.add(i*-1);
        }
        System.out.println(data);
    }

    public static MyLinkedListImproved<Integer> removeNegatives(MyLinkedListImproved<Integer> data){
        MyLinkedListImproved<Integer> negatives = new MyLinkedListImproved<Integer>();
        for (Integer i: data){
            if (i < 0){
                negatives.add(i*-1);
                data.remove(i);
            }
        }
        radixsort(negatives);
        radixsort(data);
        return negatives;
    }



    public static void main(String[] args) {
    MyLinkedListImproved<Integer> m = new MyLinkedListImproved<>();


    m.add(-5);
    m.add(0);
    m.add(55);
    m.add(-55);
    m.add(21);
     m.add(46);
    m.add(46);
    m.add(56456);
 m.add(-13);
     m.add(-111);

    System.out.println(m);
    radixsortIncludingNegatives(m);
    // System.out.println(m);
    }


}