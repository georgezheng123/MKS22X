import java.util.*;

public class Recursion{

	public static void main(String[] args){
		Recursion tester = new Recursion(); 
		// for(int i=0; i<10; i++){
		// 	System.out.println(sumZeroToN(i));
		// }
		// System.out.println(sumAtoB(0,5));
		// System.out.println(sumAtoB(5,5));
		// System.out.println(sumAtoB(2,5));
		// System.out.println(sumAtoB(4,5));
		// for(int i=0; i<11; i++){
		// 	System.out.println(i + " " + tester.fact(i));
		// }
		for(int i=0; i<100; i++){
			System.out.println(i + " " + tester.sqrt(i));
		}
	}

	public static int sumZeroToN(int n){
		if (n < 0) throw new IllegalArgumentException();
		if (n == 0) return 0;
		return n + sumZeroToN(n-1);
	}

	public static int sumAtoB(int a, int b){
		if (b < a) throw new IllegalArgumentException();
		if (a == b) return a;
		return b + sumAtoB(a, b-1);
	}

	public int fact(int n){
		if (n < 0) throw new IllegalArgumentException();
		if (n == 0) return 1;
		return n * fact(n-1);
	}

	public int fib(int n){
		if (n < 0) throw new IllegalArgumentException();
		HashMap<Integer, Integer> fibstuff = new HashMap<Integer, Integer>();
		fibstuff.put(0, 0); fibstuff.put(1, 1);
		return fib_helper(n, fibstuff);
	}

	public int fib_helper(int n, HashMap<Integer, Integer> fibstuff){
		if (fibstuff.containsKey(n)) return fibstuff.get(n);
		fibstuff.put(n, fib_helper(n-1, fibstuff) + fib_helper(n-2, fibstuff));
		return fibstuff.get(n);
	}

	public double sqrt(double n){
		if (n < 0) throw new IllegalArgumentException();
		double guess = n/2;
		return sqrt(n, guess);
	}

	public double sqrt(double n, double guess){
		if (goodEnough(n, guess)){
			return guess;
		}else{
			return sqrt(n, ( n / guess + guess) / 2);
		}
	}

	public boolean goodEnough(double n, double guess){
		return Math.abs(Math.sqrt(n) - guess) < 0.00000001;
	}

}