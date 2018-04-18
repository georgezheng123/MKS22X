import java.util.*;

public class MyLinkedList{

    private Node<Integer> start, end; 
    private int size;

    public MyLinkedList(){
        size = 0;
        start = null;
        end = null;
    }

    public void debug(){
        int counter = 0;
        Node<Integer> loop = start;
        while (loop != null){
            System.out.println("index: " + counter + ", prev: "
                + loop.prev + ", next:" + loop.next);
            loop = loop.next;
            counter++;
        }
    }

    public void clear(){
        size = 0;
        start = null;
        end = null;
    }

    public boolean add(Integer value){
        Node<Integer> newHead = new Node<Integer>(value, null, this.start);
        if (size == 1){
            start.setNext(null);
            start.setPrev(newHead);
            end = start;
        }else if (size > 1){
            start.setPrev(newHead);
        }
        start = newHead;
        size++;
        return true;
    }

    public boolean addMid(int index, Integer value){
        Node<Integer> back = getNode(index-1);
        Node<Integer> front = getNode(index);
        Node<Integer> newNode = new Node<Integer>(value, back, front); 
        back.setNext(newNode);
        front.setPrev(newNode);
        size++;
        return true;
    }

    public boolean addEnd(Integer value){
        Node<Integer> target = (size == 1) ? start : end;
        Node<Integer> newTail = new Node<Integer>(value, target, null); 
        target.setNext(newTail);
        end = newTail;
        size++;
        return true;
    }

    public int size(){
        return size;
    }

    public String toString(){
        Integer[] arr = new Integer[size];
        Node<Integer> current = start;
        for(int i=0; i<size; i++,current=current.next){
            arr[i] = current.getValue();
        }
        return Arrays.toString(arr);
    }

    public Node<Integer> getNode(int index){
        Node<Integer> current = start;
        while(index > 0){
            current = current.next;
            index--;
        }
        return current;
    }

    public Integer get(int index){
        withinBoundsEx(index);
        return getNode(index).getValue();
    }

    public int set(int index, int newValue){
        withinBoundsEx(index);
        Node<Integer> current = getNode(index);
        int oldValue = current.getValue();
        current.setValue(newValue);
        return oldValue;
    }

    public int indexOf(Integer value){
        Node<Integer> current = start;
        for(int i=0; i<size; i++){
            if (current.getValue().equals(value)){
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    public void add(int index, Integer value){
        withinBoundsInc(index);
        if (index == 0){
            add(value);
        }else if(index == size){
            addEnd(value);
        }else{
            addMid(index, value);
        }
    }

    public Integer remove(int index){
        withinBoundsEx(index);
        int value = getNode(index).getValue();
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
    }

    public void removeMid(int index){
        Node<Integer> current = getNode(index);
        Node<Integer> back = current.getPrev();
        Node<Integer> front = current.getNext();
        back.setNext(front);
        front.setPrev(back);
    }

    public void removeEnd(int index){
        end = end.prev;
    }

    public Boolean remove(Integer value){
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


    private class Node<Integer>{
        private Node<Integer> next, prev; 
        private Integer data;

        public Node(Integer data, Node<Integer> prev, Node<Integer> next){
            this.next = next;
            this.prev = prev;
            this.data = data;
        }

        public Node<Integer> getNext(){
            return next;
        }

        public Node<Integer> getPrev(){
            return prev;
        }

        public void setNext(Node<Integer> newNext){
            this.next = newNext;
        }

        public void setPrev(Node<Integer> newPrev){
            this.prev = newPrev;
        }

        public Integer getValue(){
            return data;
        }

        public void setValue(Integer data){
            this.data = data;
        }

    }

    // public static void main(String[] args){
    //     MyLinkedList test = new MyLinkedList();
    //     test.add(0, 12);
    //     // // test.debug();
    //     // System.out.println(test);
    //     // System.out.println(test.remove(new Integer(23)));
    //     System.out.println(test.remove(new Integer(12)));
    //     // System.out.println(test.remove(0));
    //     System.out.println(test);

    // }

}