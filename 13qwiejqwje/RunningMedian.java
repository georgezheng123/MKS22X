import java.util.*;

public class RunningMedian{

	int size;
	MyHeap<Double> minHeap, maxHeap;

	public RunningMedian(){
		size = 0;
		minHeap = new MyHeap<Double>(false);
		maxHeap = new MyHeap<Double>();
	}

	public void add(Double toAdd){
		// System.out.println("adding: " + toAdd);
		if (size == 0){
			maxHeap.add(toAdd);
		}else{
			if (toAdd > maxHeap.peek()){
				// System.out.println("going into minheap");
				minHeap.add(toAdd);
			}else{
				// System.out.println("going into maxheap");
				maxHeap.add(toAdd);
			}
			if (Math.abs(minHeap.size() - maxHeap.size()) > 1){
				balance();
			}
		}
		size++;
		// System.out.println("max " + maxHeap);
		// System.out.println("min " + minHeap);
		// System.out.println("\n");
	}

	public Double getMedian(){
		Double median;
		if (minHeap.size() == 1) return maxHeap.peek();
		if (maxHeap.size() == 1) return minHeap.peek();
		if (minHeap.size() == maxHeap.size()){
			median = (minHeap.peek() + maxHeap.peek()) / 2;
		}else{
			// System.out.println(minHeap.peek() + " and " + maxHeap.peek());
			median = (minHeap.size() > maxHeap.size()) ? minHeap.peek() : maxHeap.peek();
		}
		return median;
	}

	public int size(){
		return size;
	}

	public void balance(){
		// System.out.println("balancing max " + maxHeap);
		// System.out.println("balancing min: " + minHeap);
		double switchy;
		if (minHeap.size() > maxHeap.size()){
			switchy = minHeap.remove();
			// System.out.println("am now moving: " + switchy);
			maxHeap.add(switchy);
		}else{
			switchy = maxHeap.remove();
			// System.out.println("am now moving: " + switchy);
			minHeap.add(switchy);
		}
	}

	public static double getMedian(ArrayList<Double> list) {

	// middle = (list.get(list.size())/2 + list.get(list.size()-1)/2) /2;
		//why doesnt this worksdnasdjasdijasd
    Collections.sort(list);
    if (list.size() <= 1) return list.get(0);
    if (list.size() % 2 == 0){
    	while (list.size() != 2){
    		list = new ArrayList<Double>(list.subList(1, list.size()-1));
    	}
    	return (list.get(0) + list.get(1)) / 2.0;
    }else{
    	while (list.size() != 1){
    		list = new ArrayList<Double>(list.subList(1, list.size()-1));
    	}
    	return list.get(0);
    }

	}

	public static void main(String[] args) {
		ArrayList<Double> list = new ArrayList<Double>();
		RunningMedian r = new RunningMedian();
		for (int i=0; i<20000; i++){
			double n = (double)((int)(Math.random()*9999 + 1));
			list.add(n);
			r.add(n);
			if (i % 400 == 0){if (getMedian(list) != r.getMedian()){
							System.out.println(getMedian(list) + " vs " + r.getMedian());
							System.out.println(list);
							System.out.println("\n\n");
						}}
		}
		// System.out.println(Arrays.toString(list));
		// System.out.println(findMedian(list));
		// System.out.println(r.getMedian());
		// r.add(11.0);r.add(11.0); r.add(11.0); 
		// System.out.println(r.getMedian());
		
	}

}