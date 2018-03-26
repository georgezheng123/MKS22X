import java.util.*;

public class Merge{

	private static final int INCREASE = 0;
	private static final int DECREASE = 1;
	private static final int STANDARD = 2;
	private static final int SMALL_RANGE = 3;
	private static final int EMPTY = 4;

	// public static void main(String[] args){
	// 	int[] arr = makeArray(10000000, 2);
	// 	int[] sorted = Arrays.copyOf(arr,arr.length);
	// 	Arrays.sort(sorted);
	// 	mergesort(arr);
	// 	System.out.println(Arrays.toString(arr));
	// 	System.out.println(Arrays.equals(arr, sorted));
	// }

	public static void insertionSort(int[] data, int lo, int hi){ 
		int j = lo+1; 
		while (j <= hi) {  
			int current=data[j];  
			int i = j-1;  
			while (i>lo-1 && data[i]>current) {  
				data[i+1] = data[i];  
				i--;  
			}  
			data[i+1] = current;  
			j++;
		}  
	}

	public static void mergesort(int[] data){
		msort(data, 0, data.length-1);
	}

	private static void msort(int[] data, int lo, int hi){
		if (lo < hi){
			int middle = (lo + hi) / 2;
			if (hi - lo > 20){
				msort(data, lo, middle);
				msort(data, middle+1, hi);
				merge(data, lo, middle, hi);
			}else{
				insertionSort(data, lo, hi);
			}
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


 //Sort testing code


  private static String name(int i){
    if(i==0)return "Increassing";
    if(i==1)return "Decreassing";
    if(i==2)return "Normal Random";
    if(i==3)return "Random with Few Values";
    if(i==4)return "size 0 array";
    return "Error stat array";

  }

  private static int create(int min, int max){
    return min + (int)(Math.random()*(max-min));
  }

  private static int[] makeArray(int size,int type){
    int[]ans =new int[size];
    if(type == STANDARD){
      for(int i = 0; i < size; i++){
        ans[i]= create(-1000000,1000000);
      }
    }
    if(type == INCREASE){
      int current = -5 * size;
      for(int i = 0; i < size; i++){
        ans[i]= create(current,current + 10);
        current += 10;
      }
    }
    if(type == DECREASE){
      int current = 5 * size;
      for(int i = 0; i < size; i++){
        ans[i]= create(current,current + 10);
        current -= 10;
      }
    }
    if(type == SMALL_RANGE){
      for(int i = 0; i < size; i++){
        ans[i]= create(-5,5);
      }
    }
    if(type == EMPTY){
      ans = new int[0];
    }
    return ans;
  }

  public static void main(String[]args){
    if(args.length < 2)return;
    
    int size =  Integer.parseInt(args[0]);
    int type =   Integer.parseInt(args[1]);

    int [] start = makeArray(size,type);
    int [] result = Arrays.copyOf(start,start.length);
    Arrays.sort(result);
    
    long startTime = System.currentTimeMillis();
    
    mergesort(start);
     
    long elapsedTime = System.currentTimeMillis() - startTime;
 
    if(Arrays.equals(start,result)){
      System.out.println("PASS Case "+name(type)+" array, size:"+size+" "+elapsedTime/1000.0+"sec ");
    }else{
      System.out.println("FAIL ! ERROR ! "+name(type)+" array, size:"+size+"  ERROR!");
    }
}

}