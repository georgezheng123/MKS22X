import java.util.*;

public class Quick{

	public static void main(String[] args){
		
		int[] arr = new int[] {999,999,999,4,1,0, 0,0,3,2,999,999,999};
	
		quicksort(arr);
		System.out.println(Arrays.toString(arr));
		
	}

	public static void quicksort(int[] arr){
		if (arr.length > 1) quicksort(arr, 0, arr.length-1);
	}

	public static void quicksort(int[] arr, int start, int end){
		if (end - start > 0){
			int pivotIndex = partition(arr, start, end);
			quicksort(arr, start, pivotIndex-1);
			quicksort(arr, pivotIndex+1, end);

		}
	}

	public static int quickselect(int[] data, int k){
		int start = 0, end = data.length-1;
		while (start < end){
			int index = partition(data, start, end);
			if (index > k){
				end -= 1;
			}else if (index < k){
				start += 1;
			}else{
				return data[index];
			}
		}

		return data[start];
	}


	public static int partition(int[] data, int start, int end){
		swap(data, end, (int) (Math.random()*(end-start+1) + start));
		int pivotValue = data[end], j = start;
		for (int i=start; i<end; i++){
			if (data[i] <= pivotValue){
				swap(data, i, j++);
			}
		}
		swap(data, j, end);
		return j;
	}

	public static void swap(int[] arr, int a, int b){
		if (arr[a] != arr[b]){int c = arr[a];
				arr[a] = arr[b];
				arr[b] = c;}
	}

}