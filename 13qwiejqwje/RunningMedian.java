public class RunningMedian{

	int size;
	MyHeap<Double> minHeap, maxHeap;

	public RunningMedian(){
		size = 0;
		minHeap = new MyHeap<Double>(false);
		maxHeap = new MyHeap<Double>();
	}

	public void add(Double toAdd){
		balance();
	}

	public Double getMedian(){
		balance();
		return 0.0;
	}

	public int size(){
		return size;
	}

	public void balance(){

	}

	public static void main(String[] args) {
		
	}

}