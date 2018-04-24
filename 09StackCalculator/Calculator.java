import java.util.*;

public class Calculator{

    public static double eval(String str){
        pancake listy = new pancake();
        StringTokenizer tokenizer = new StringTokenizer(str, " ");
        while (tokenizer.hasMoreElements()) {
            String s = tokenizer.nextToken();
            if (isOp(s)){
                listy.push(operate(listy, s));
            }else{//s is number
                listy.push(Double.parseDouble(s));
            }
            // System.out.println(listy);
        }
        return listy.pop();
    }

    public static double operate(pancake stacky, String operation){
        double toReturn;
        double popOne = stacky.pop();
        double popTwo = stacky.pop();
        if(operation.equals("+")){
            toReturn = popTwo + popOne;
        }
            else if(operation.equals("*")){
                toReturn = popTwo * popOne ;
            }  else if(operation.equals("%")){
                toReturn = popTwo % popOne ;
            } 
            else if(operation.equals("-")){
                toReturn = popTwo - popOne ;
            } else{ // target equals /
                toReturn = popTwo / popOne ;
            }
        return toReturn;
    }


    public static boolean isOp(String target){
        return target.equals("+") || target.equals("*") || target.equals("%") ||
            target.equals("-") || target.equals("/");
    }

    public static void main(String[] args) {
        System.out.println(eval("8 2 + 99 9 - * 2 + 9 -"));
    }

}