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

	@SuppressWarnings("unchecked")
	public MyHeap(List<T> data){
		isMaxHeap = true;
		size = 1;
		arr = new ArrayList<T>();
		arr.add(null);
		size += data.size();
		arr.addAll(data);
		for (int i=size/2; i>0; i--){
			heapify(i);
		}
	}

	public ArrayList<T> sorty(){
		ArrayList<T> sortedStuff = new ArrayList<T>();
		while (size != 1){
			sortedStuff.add(arr.get(1));
			if (arr.size() == 2) return sortedStuff;
			arr.set(1, arr.remove(size-1));
			size--;
			heapify(1);
		}
		return sortedStuff;
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
		int parentIndex = newIndex/2; 
		while (isMaxHeap ?  (arr.get(parentIndex).compareTo(arr.get(newIndex)) < 0) : (arr.get(parentIndex).compareTo(arr.get(newIndex)) > 0)){
			swap(newIndex, parentIndex);
			newIndex = parentIndex;
			parentIndex = newIndex/2;
			if (parentIndex == 0) return;
		}
	}

	public T remove(){
		if (size < 3) {
			size = 1;
			arr = new ArrayList<T>();
			arr.add(null);
			return null;
		}
		T removed = arr.get(1);
		arr.set(1, arr.remove(size-1));
		size--;
		heapify(1);
		return removed;
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
				chosenIndex = (arr.get(leftChildIndex).compareTo(arr.get(rightChildIndex)) < 0) ? (rightChildIndex) : (leftChildIndex);
				if (parent.compareTo(arr.get(chosenIndex)) < 0){
					heapify(n, chosenIndex);
				}
			}else{
				chosenIndex = (arr.get(leftChildIndex).compareTo(arr.get(rightChildIndex)) < 0) ? (leftChildIndex) : (rightChildIndex);
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

	public void resize(){

	}

	public void swap(int indexOne, int indexTwo){
		Collections.swap(arr, indexOne, indexTwo);
	}

	
	public static void main(String[] args) {
		MyHeap<Integer> h = new MyHeap<Integer>(false);
		int[] donut = new int[] {9, 63, 71, 43, 4, 16, 101, 69};
    	for (int i: donut){
    		h.add(i);
    	}
	}

}