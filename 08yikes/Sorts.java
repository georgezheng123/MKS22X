import java.util.*;

public class Sorts{

    public static void radixsortH(MyLinkedListImproved<Integer> data) {
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
        // System.out.println("digit: " + n + "of number: " + numb);
        return numb % (div*10) / div; 
    }

    public static void radixsort(MyLinkedListImproved<Integer> data){ 
        for (Integer j: data){
            if (j < 0){
                MyLinkedListImproved<Integer> negatives = removeNegatives(data);
                for (Integer i: negatives){
                    data.addBeg(i*-1);
                }
                return;
            }
        }
        radixsortH(data);


        
        // System.out.println(data);
    }

    public static void radixsortIncludingNegatives(MyLinkedListImproved<Integer> data){ 
        radixsort(data);
    }

    public static MyLinkedListImproved<Integer> removeNegatives(MyLinkedListImproved<Integer> data){
        MyLinkedListImproved<Integer> negatives = new MyLinkedListImproved<Integer>();
        for (Integer i: data){
            if (i < 0){
                negatives.addBeg(i*-1);
                data.remove(i);
            }
        }
        radixsortH(negatives);
        radixsortH(data);
        return negatives;
    }

    public static void main(String[] args) {
        int[] blah = new int[] {4, -4, 21, 0, -22, 12, -11, 0, 4, 5, -99};
        MyLinkedListImproved<Integer> donut = new MyLinkedListImproved<Integer>();
        for (int i: blah){
            donut.add(i);
        }
        radixsort(donut);
        System.out.println(donut);
    }




}