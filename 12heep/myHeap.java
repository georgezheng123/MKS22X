import java.util.*;

public class MyHeap<T extends Comparable<T>>{

	private T[] arr;
	private int size;
	private boolean isMaxHeap;

	public MyHeap(){
		this(true);
	}

	@SuppressWarnings("unchecked")
	public MyHeap(boolean max){
		isMaxHeap = max;
		size = 0;
		arr = (T[]) new Comparable[10];
	}

	public String toString(){
		if (size == 0) return "[]";
		return Arrays.toString(arr).replace(", null", "");
	}

	public int size(){
		return size;
	}

	public void add(T toAdd){
		if (size >= arr.length) makeBigger();
		arr[size++] = toAdd;
	}

	@SuppressWarnings("unchecked")
	public void makeBigger(){
		T[] newArr = (T[]) new Comparable[size*2];
		System.arraycopy(arr, 0, newArr, 0, arr.length);
		arr = newArr; 
	}

	
	public static void main(String[] args) {
		MyHeap<Integer> h = new MyHeap<Integer>();
		System.out.println(h);
    	for (int i=0; i<1111111; i++){
    		h.add(i);
    	}

    	System.out.println(h);
	}


}