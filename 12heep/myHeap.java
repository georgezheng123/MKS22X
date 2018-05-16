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
		size = 1;
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
		arr.add(toAdd);
		int newIndex = size++;
		if (size == 2) return;
		System.out.println(this + " is size " + size);
		int parentIndex = newIndex/2; 
		System.out.println(newIndex + " " + parentIndex);
		System.out.println((arr.get(parentIndex).compareTo(arr.get(newIndex)) < 0));
		while (isMaxHeap ?  (arr.get(parentIndex).compareTo(arr.get(newIndex)) < 0) : (arr.get(parentIndex).compareTo(arr.get(newIndex)) > 0)){
			swap(newIndex, parentIndex);
			newIndex = parentIndex;
			parentIndex = newIndex/2;
			if (parentIndex == 0) return;
		}
	}

	public void heapify(int n){
		int leftChildIndex = 2*n;
		int rightChildIndex = 2*n+1; 
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
				// System.out.println("here");
				chosenIndex = (arr.get(leftChildIndex).compareTo(arr.get(rightChildIndex)) < 0) ? (rightChildIndex) : (leftChildIndex);
				if (parent.compareTo(arr.get(chosenIndex)) < 0){
					// System.out.println(parent + " " + arr.get(chosenIndex));
					heapify(n, chosenIndex);
					// System.out.println("heep now looks like: " + this);
				}
			}else{
				System.out.println("now heapifying index :" + n);
				chosenIndex = (arr.get(leftChildIndex).compareTo(arr.get(rightChildIndex)) < 0) ? (leftChildIndex) : (rightChildIndex);
				System.out.println(arr.get(chosenIndex));
				if (parent.compareTo(arr.get(chosenIndex)) > 0){
					heapify(n, chosenIndex);
				}
			}
		}
	}

	public void heapify(int n, int otherIndex){
		swap(n, otherIndex);
		heapify(otherIndex);
	}


	public T peek(){
		return arr.get(1);
	}

	public void swap(int indexOne, int indexTwo){
		Collections.swap(arr, indexOne, indexTwo);
	}

	
	public static void main(String[] args) {
		MyHeap<Integer> h = new MyHeap<Integer>();
		int[] donut = new int[] {9, 63, 71, 43, 4, 16, 101, 69};
    	for (int i: donut){
    		h.add(i);
    	}
    	// System.out.println(h);
    	// for (int i=4; i>0; i--){
    	// 	h.heapify(i);
    	// }
    	System.out.println(h);
	}


}