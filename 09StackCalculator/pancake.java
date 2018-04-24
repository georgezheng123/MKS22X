import java.util.*;


public class pancake{
    	private LinkedList<Double> listy;

    	public pancake(){
    		listy = new LinkedList<Double>();
    	}

    	public void push(double value){
    		listy.addLast(value);
    	}

    	public double pop(){
    		return listy.remove(listy.size()-1);
    	}

        public String toString(){
            return listy.toString();
        }
    }