import java.util.*;

public class Merge{
	
	public static void main(String[] args){
		
		int[] arr = new int[] {52, 12, 53, 1};
		mergesort(arr);

		// arr = new int[] {1, 5, 9, 2, 7, 19};

		// merge(arr, 0, 5/2,5);
		
		System.out.println(Arrays.toString(arr));
		
	}

	public static void mergesort(int[] data){
		msort(data, 0, data.length-1);
	}

	private static void msort(int[] data, int lo, int hi){
		if (lo < hi){
			int middle = (lo + hi) / 2;
			msort(data, lo, middle);
			msort(data, middle+1, hi);
			merge(data, lo, middle, hi);
		}
	}
	// {0, 3, 6, 1, 5, 7}
	public static void merge(int[] data, int start, int middle, int end){
		int[] sorted = new int[end-start+1];
		int counter = 0, begin = start, mid = middle+1;
		while ((start <= middle) && (mid <= end)){
			if (data[start] < data[mid]){
				sorted[counter] = data[start];
				start++;
			}else if (data[start] >= data[mid]){
				sorted[counter] = data[mid];
				mid++;
			}
			counter++;
		}
		for (int i=start; i<=middle; i++){
			sorted[counter] = data[i];
			counter++;
		}
		for (int i=mid; i<=end; i++){
			sorted[counter] = data[i];
			counter++;
			
		}
		System.arraycopy(sorted, 0, data, begin, sorted.length);
	}



}