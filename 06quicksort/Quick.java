import java.util.*;

public class Quick{

 private static final int INCREASE = 0;
 private static final int DECREASE = 1;
 private static final int STANDARD = 2;
 private static final int SMALL_RANGE = 3;
 private static final int EMPTY = 4;

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

private static int[]makeArray(int size,int type){
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
  quicksort(start);
  long elapsedTime = System.currentTimeMillis() - startTime;

  if(Arrays.equals(start,result)){
    System.out.println("PASS Case "+name(type)+" array, size:"+size+" "+elapsedTime/1000.0+"sec ");
  }else{
    System.out.println("FAIL ! ERROR ! "+name(type)+" array, size:"+size+"  ERROR!");
  }
}

public static void quicksort(int[] arr){
  if (arr.length > 1) {
    shuffleArray(arr);
    quicksort(arr, 0, arr.length-1);
  }
}

public static void quicksort(int[] arr, int start, int end){
  if (end > start){
    if (end - start > 10){
      int lt = start, gt = end;
        int toComp = arr[lt];
        int i = lt+1;
        while (i <= gt) {
            if (arr[i] == toComp){
              i++;
            }else if (arr[i] < toComp){
              swap(arr, lt++, i++);
            }else{
              swap(arr, i, gt--);
            }
        }
        quicksort(arr, start, lt-1);
        quicksort(arr, gt+1, end);
    }else{
      insertionSort(arr, start, end);
    }
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
   if (data[i] < pivotValue){
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
  //code from SOF
  private static void shuffleArray(int[] array)
{
    int index;
    Random random = new Random();
    for (int i = array.length - 1; i > 0; i--)
    {
        index = random.nextInt(i + 1);
        if (index != i)
        {
            array[index] ^= array[i];
            array[i] ^= array[index];
            array[index] ^= array[i];
        }
    }
}

}