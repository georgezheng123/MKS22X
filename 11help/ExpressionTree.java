public class ExpressionTree{
  

  
  /*return the expression as an infix notation string with parenthesis*/
  /* The sample tree would be: "(3 + (2 * 10))"     */
  public String toString(){
    if (isOp()){
          String left = str(getLeft().toString());
          String right = str(getRight().toString());
          return "(" + left + " " + str(getOp()) + " " + right + ")";
      }else{
        return str(getValue());
      }
  }
  
  /*return the expression as a postfix notation string without parenthesis*/
  /* The sample tree would be: "3 2 10 * +"     */
  public String toStringPostfix(){
    if (isOp()){
          String left = str(getLeft().toStringPostfix());
          String right = str(getRight().toStringPostfix());
          return left + " " + right + " "+ str(getOp());
      }else{
        return str(getValue());
      }
  }
  
  /*return the expression as a prefix notation string without parenthesis*/
  /* The sample tree would be: "+ 3 * 2 10"     */
  
  public String toStringPrefix(){
    if (isOp()){
          String left = str(getLeft().toStringPrefix());
          String right = str(getRight().toStringPrefix());
          return str(getOp()) + " "+ left + " "+ right;
      }else{
        return str(getValue());
      }
  }
  
  public String str(Object o){
    return ""+o;
  }
  
  
  /*return the value of the specified expression tree*/
  public double evaluate(){
      if (isOp()){
          return apply(getOp(),getLeft().evaluate(),getRight().evaluate());
      }else{
        return getValue();
      }
    }
   
    
  /*use the correct operator on both a and b, and return that value*/
  private double apply(char op, double a, double b){
    return new double[]{a+b, a-b, a*b, a/b}["+-*/".indexOf(op)];
  }




  ////////////////////ONLY EDIT ABOVE THIS LINE////////////////////


  
  private char op;
  private double value;
  private ExpressionTree left,right;
  
  /*TreeNodes are immutable, so no issues with linking them across multiple
  *  expressions. The can be constructed with a value, or operator and 2
  * sub-ExpressionTrees*/
  public ExpressionTree(double value){
    this.value = value;
    op = '~';
  }
  public ExpressionTree(char op,ExpressionTree l, ExpressionTree r){
    this.op = op;
    left = l;
    right = r;
  }
  
  public char getOp(){
    return op;
  }
  
  /* accessor method for Value, precondition is that isValue() is true.*/
  private double getValue(){
    return value;
  }
  /* accessor method for left, precondition is that isOp() is true.*/
  private ExpressionTree getLeft(){
    return left;
  }
  /* accessor method for right, precondition is that isOp() is true.*/
  private ExpressionTree getRight(){
    return right;
  }
  
  private boolean isOp(){
    return hasChildren();
  }
  private boolean isValue(){
    return !hasChildren();
  }
  
  private boolean hasChildren(){
    return left != null && right != null;
  }
  
  
  public static void main(String[] args){
    //ugly main sorry!
    ExpressionTree a = new ExpressionTree(4.0);
    ExpressionTree b = new ExpressionTree(2.0);

    ExpressionTree c = new ExpressionTree('+',a,b);
    
    ExpressionTree d = new ExpressionTree('*',c,new ExpressionTree(3.5));


    ExpressionTree ex = new ExpressionTree('-',d,new ExpressionTree(1.0));
    System.out.println(ex);
    System.out.println(ex.toStringPostfix());
    System.out.println(ex.toStringPrefix());
    System.out.println(ex.evaluate());//20

    // ex = new ExpressionTree('+',new ExpressionTree(1.0),ex);
    // System.out.println(ex);
    // System.out.println(ex.toStringPostfix());
    // System.out.println(ex.toStringPrefix());
    // System.out.println(ex.evaluate());//21

    // ex = new ExpressionTree('/',ex,new ExpressionTree(2.0));
    // System.out.println(ex);
    // System.out.println(ex.toStringPostfix());
    // System.out.println(ex.toStringPrefix());
    // System.out.println(ex.evaluate());//10.5   
  }
}