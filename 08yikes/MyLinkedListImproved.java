import java.util.*;
import java.util.Iterator;

public class MyLinkedListImproved<T extends Comparable<T>> implements Iterable<T>{

    private Node<T> start, end; 
    private int size;

    public MyLinkedListImproved(){
        size = 0;
        start = null;
        end = null;
    }

    public void debug(){
        int counter = 0;
        Node<T> loop = start;
        while (loop != null){
            System.out.println("index: " + counter + ", prev: "
                + loop.prev + ", next:" + loop.next);
            loop = loop.next;
            counter++;
        }
        System.out.println("isempty");
    }

    public void clear(){
        size = 0;
        start = null;
        end = null;
    }

    public boolean add(T value){
        Node<T> newHead = new Node<T>(value, null, this.start);
        if (size == 1){
            start.setNext(null);
            start.setPrev(newHead);
            end = start;
        }else if (size > 1){
            start.setPrev(newHead);
        }
        start = newHead;
        if (size == 0){
            end = start;
        }
        size++;
        return true;
    }

    public boolean addMid(int index, T value){
        Node<T> back = getNode(index-1);
        Node<T> front = getNode(index);
        Node<T> newNode = new Node<T>(value, back, front); 
        back.setNext(newNode);
        front.setPrev(newNode);
        size++;
        return true;
    }

    public boolean addEnd(T value){
        // debug();
        if (size == 0){
            return add(value);
        }
        Node<T> target = (size == 1) ? start : end;
        Node<T> newTail = new Node<T>(value, target, null); 
        target.setNext(newTail);
        end = newTail;
        size++;
        return true;
    }

    public int size(){
        return size;
    }

    public String toString(){
        Object[] arr = new Object[size];
        Node<T> current = start;
        for(int i=0; i<size; i++,current=current.next){
            arr[i] = current.getValue();
        }
        return Arrays.toString(arr);
    }

    public Node<T> getNode(int index){
        Node<T> current = start;
        while(index > 0){
            current = current.next;
            index--;
        }
        return current;
    }

    public T get(int index){
        withinBoundsEx(index);
        return getNode(index).getValue();
    }

    public T set(int index, T newValue){
        withinBoundsEx(index);
        Node<T> current = getNode(index);
        T oldValue = current.getValue();
        current.setValue(newValue);
        return oldValue;
    }

    public int indexOf(T value){
        Node<T> current = start;
        for(int i=0; i<size; i++){
            if (current.getValue().equals(value)){
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    public void add(int index, T value){
        withinBoundsInc(index);
        if (index == 0){
            add(value);
        }else if(index == size){
            addEnd(value);
        }else{
            addMid(index, value);
        }
    }

    public T remove(int index){
        withinBoundsEx(index);
        T value = getNode(index).getValue();
        if (index == 0){
            removeStart(index);
        }else if(index == size-1){
            removeEnd(index);
        }else{
            removeMid(index);
        }
        size--;
        return value;
    }

    public void removeStart(int index){
        start = start.next;
        if (start != null) start.setPrev(null);
    }

    public void removeMid(int index){
        Node<T> current = getNode(index);
        Node<T> back = current.getPrev();
        Node<T> front = current.getNext();
        back.setNext(front);
        front.setPrev(back);
    }

    public void removeEnd(int index){
        end = end.prev;
        end.setNext(null);
    }

    public Boolean remove(T value){
        int index = indexOf(value);
        if (index != -1){
            remove(index);
            return true;
        }
        return false;
    }

    public void withinBoundsEx(int index){
        if (index < 0 || index >= size()){
            throw new IndexOutOfBoundsException();
        }
    }

    public void withinBoundsInc(int index){
        if (index < 0 || index > size()){
            throw new IndexOutOfBoundsException();
        }
    }

    public int max(){
        if (size == 0) return -1;
        Node<T> current = start;
        int maxIndex = 0;
        T maxValue = current.getValue();
        for (int i=0; i<size; i++){
            T currentValue = current.getValue();
            if (currentValue.compareTo(maxValue)>0){
                maxIndex = i;
                maxValue = currentValue;
            }
             current = current.next;
        }
        return maxIndex;
    }

    public T maxValue(){
        return get(max());
    }

    public int min(){
        if (size == 0) return -1;
        Node<T> current = start;
        int maxIndex = 0;
        T maxValue = current.getValue();
        for (int i=0; i<size; i++){
            T currentValue = current.getValue();
            if (currentValue.compareTo(maxValue)<0){
                maxIndex = i;
                maxValue = currentValue;
            }
             current = current.next;
        }
        return maxIndex;
    }

    public void extend(MyLinkedListImproved<T> other){
        if (other.size() != 0){
            if (size() != 0){
                end.setNext(other.getNode(0));
                other.getNode(0).setPrev(end);
                end = other.end;
                size += other.size();
                other.clear();
            }else{
                start = other.start;
                end = other.end;
                size += other.size();
                other.clear();
            }
        }
    }


    private class Node<T>{
        private Node<T> next, prev; 
        private T data;

        public Node(T data, Node<T> prev, Node<T> next){
            this.next = next;
            this.prev = prev;
            this.data = data;
        }

        public String toString(){
            return data + "";
        }

        public Node<T> getNext(){
            return next;
        }

        public Node<T> getPrev(){
            return prev;
        }

        public void setNext(Node<T> newNext){
            this.next = newNext;
        }

        public void setPrev(Node<T> newPrev){
            this.prev = newPrev;
        }

        public T getValue(){
            return data;
        }

        public void setValue(T data){
            this.data = data;
        }

    }

    public LinkedListIterator iterator(){
            return new LinkedListIterator(start);
    }

    private class LinkedListIterator implements Iterator<T>{

        private Node<T> currentNode;

        public LinkedListIterator(Node<T> start) {
            currentNode = start;
        }

        public boolean hasNext() {
            return currentNode != null;
        }

        public T next(){
            T currentValue = currentNode.getValue();
            currentNode = currentNode.next;
            return currentValue;
        }
        
    }


    public static void main(String[] args){
    MyLinkedListImproved<Integer> l = new MyLinkedListImproved<>();
    MyLinkedListImproved<Integer> m = new MyLinkedListImproved<>();


    m.add(Integer.valueOf(3));
    l.add(Integer.valueOf(9));
    l.add(Integer.valueOf(912));
    System.out.println(l);
    System.out.println(m);
    l.extend(m);
    System.out.println(l + " is size " + l.size());
    System.out.println(m + " is size " + m.size());

        
 

    }

}