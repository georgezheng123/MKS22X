import java.util.*;

public class MyHeap<T extends Comparable<T>>{

	private ArrayList<T> arr;
	private int size;
	private boolean isMaxHeap;

	public MyHeap(){
		this(true);
	}

	@SuppressWarnings("unchecked")
	public MyHeap(boolean max){
		isMaxHeap = max;
		size = 0;
		arr = new ArrayList<T>();
		arr.add(null);
	}

	public String toString(){
		return arr.toString();
	}

	public int size(){
		return size;
	}

	public void add(T toAdd){
		if (isMaxHeap){
			addMaxHeap(toAdd);
		}else{
			addMinHeap(toAdd);
		}
		size++;
	}

	public void addMaxHeap(T toAdd){
		
	}

	public void addMinHeap(T toAdd){
		
	}

	public void heapify(int n){
		int leftChildIndex = 2*n+1;
		int rightChildIndex = 2*n+2; 
		if (leftChildIndex >= size) return;//no child
		T parent = arr.get(n);
		T child = arr.get(leftChildIndex);
		if (rightChildIndex >= size){//one child
			if (parent != child){
				if (parent.compareTo(child) < 0) {
					if (isMaxHeap) {
						heapify(n, leftChildIndex);
					}
				}else{
					if (!isMaxHeap){
						heapify(n, leftChildIndex);
					}
				}
			}
		}else{//two children
			int chosenIndex;
			if (isMaxHeap){
				chosenIndex = (arr.get(leftChildIndex).compareTo(arr.get(rightChildIndex)) < 0) ? (rightChildIndex) : (leftChildIndex);
				if (parent.compareTo(arr.get(chosenIndex)) < 0){
					heapify(n, chosenIndex);
				}
			}else{
				chosenIndex = (arr.get(leftChildIndex).compareTo(arr.get(leftChildIndex)) < 0) ? (leftChildIndex) : (rightChildIndex);
				if (parent.compareTo(arr.get(chosenIndex)) > 0){
					heapify(n, chosenIndex);
				}
			}
		}
		
	}

	public void heapify(int n, int leftChildIndex){
		swap(n, leftChildIndex);
		heapify(leftChildIndex);
	}


	public T peek(){
		return arr.get(0);
	}

	public void swap(int indexOne, int indexTwo){
		Collections.swap(arr, indexOne, indexTwo);
	}

	
	public static void main(String[] args) {
		MyHeap<Integer> h = new MyHeap<Integer>();
		System.out.println(h);
    	for (int i=0; i<10; i++){
    		h.add(i);
    	}
	}


}