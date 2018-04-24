import java.util.*;

public class MyDeque<E>{

    private int size, begin, end;
    private E[] arr;

    public String toString(){
        return Arrays.toString(arr);
    }

    @SuppressWarnings("unchecked")
    public MyDeque(){
        this(10);
    }

    @SuppressWarnings("unchecked")
    public MyDeque(int initialCapacity){
        if (initialCapacity < 0) throw new IllegalArgumentException();
        arr = (E[]) new Object[initialCapacity];
        size = 0;
        begin = 0;
        end = 0;
    }

    @SuppressWarnings("unchecked")
    public void doubleCapacity(){
        E[] newArr = (E[]) new Object[arr.length*2];
        if (begin > end){
            int beginToEnd = arr.length-begin;
            System.arraycopy(arr, begin, newArr, 0, beginToEnd);
            System.arraycopy(arr, 0, newArr, beginToEnd, size-beginToEnd);
        }else{
            System.arraycopy(arr, 0, newArr, 0, arr.length );
        }
        arr = newArr;
        begin = 0;
        end = size-1;

    }

    public int size(){
        return size;
    }

    public void checkSpace(){
        if (size == arr.length) doubleCapacity();
    }

    public void checkNull(E toCheck){
        if (toCheck == null) throw new NullPointerException();
    }

    public void checkEmpty(){
        if (size == 0) throw new NoSuchElementException();
    }

    public void addFirst(E toAdd){
        checkNull(toAdd);
        checkSpace();
        addFirstIndices();
        arr[begin] = toAdd;
    }

    public void addFirstIndices(){
        if (size == 0) {
            size++;
            return;
        }
        if (begin == 0) begin = arr.length;
        begin--;
        size++;
    }

    public void addLast(E toAdd){
        checkNull(toAdd);
        checkSpace();
        addLastIndices();
        arr[end] = toAdd;
    }

    public void addLastIndices(){
        if (size != 0){
            end = (end+1) % arr.length; 
        }
        size++;
    }

    public void resetIfEmpt(){
        if (size == 0){
            begin = 0;
            end = 0;
        }
    }

    public E removeFirst(){
        checkEmpty();
        E valueAt = arr[begin];
        arr[begin] = null;
        incrementBegin();
        resetIfEmpt();
        return valueAt;
    }

    public void incrementBegin(){
        if (begin+1 == arr.length){
            begin = 0;
        }else{
            begin++;
        }
        size--;
    }

    public E removeLast(){
        checkEmpty();
        E valueAt = arr[end];
        arr[end] = null;
        decrementEnd();
        resetIfEmpt();
        return valueAt;
    }
    public void decrementEnd(){
        if (end == 0){
            end = arr.length-1;
        }else{
            end--;
        }
        size--;
    }

    public E getFirst(){
        checkEmpty();
        return arr[begin];
    }

    public E getLast(){
        checkEmpty();
        return arr[end];
    }


    public static void main(String[] args) {
    MyDeque<String> a = new MyDeque<>(), a1 = new MyDeque<>();
    ArrayList<String> b = new ArrayList<>();

    int size = Integer.parseInt(args[0]);
    for(int i = 0; i < size; i++){
      int temp = (int)(Math.random() * 1000);
      if(temp % 2 == 0){
        a.addFirst("" + temp);
        a1.addFirst("" + temp);
        b.add(0, "" + temp);
      }
      else{
        a.addLast("" + temp);
        a1.addLast("" + temp);
        b.add("" + temp);
      }
      // System.out.println(a1.toString() + " " + a1.begin + " " + a1.end);
    }

    int index = 0;
    boolean hasError = false;
    String errorEvaluation = "Errors found at these indices: ";
    for (String x : b){
      if (!(x.equals(a.getFirst()))){
        System.out.println("The getFirst() function is incorrect at index " + index);
        hasError = true;
      }
      if (!(x.equals(a.removeFirst()))){
        System.out.println("There is an error at index " + index);
        errorEvaluation += index + ", ";
        hasError = true;
      }
      index++;
    }


    if(hasError){
      errorEvaluation = errorEvaluation.substring(0, errorEvaluation.length() - 2);
      System.out.println(errorEvaluation);
      System.out.println("MyDeque: " + a1);
      System.out.println("start: " + a1.begin + ", end: " + a1.end);
      System.out.println("Actual Deque: " + b);
    }
    else{
      System.out.println("Your deque is bug-free!");
    }
  }

}