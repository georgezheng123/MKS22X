import java.util.*;

public class KnightBoard{

	private int[][] board;

	public KnightBoard(int startingRows,int startingCols){
		if (startingRows < 0 || startingCols < 0) throw new IllegalArgumentException();
		board = new int[startingRows][startingCols]; 
	}

	public String toString(){
		String ans = "";
		for (int i=0; i<board.length; i++){
            for (int j=0; j<board[0].length; j++){
                if (board[i][j] != 0) ans += (board[i][j]<10? " ":"") + board[i][j] + " ";
                if (board[i][j] == 0) ans += "_ ";
            }
            ans += "\n";
        }       
		return ans;
	}

	public boolean solve(int row, int col){
		checkSol(row, col);
		board[row][col] = 1;
		return solveHeur(row, col,1);
	}

	private boolean solve(int row, int col, int level){
		
		return solveHeur(row, col,1);
	}

	private boolean addKnight(int row, int col, int level){
		if (board[row][col] != 0){
			return false;
		}
		board[row][col] = level;
		return true;
	}

	private ArrayList<Integer[]> possibilities(int row, int col){
		ArrayList<Integer[]> arr = new ArrayList<Integer[]>();
		int[][] deltas = new int[][] { {-2, -1},  {-2, 1},  {2, -1},  {2, 1},  {-1, -2},  {-1, 2}, {1, -2},  {1, 2}};
		for (int[] i: deltas){
			if (withinBounds(row+i[0], col+i[1])){
				arr.add(new Integer[] {row+i[0], col+i[1]});
			}
		}
		return arr;
	}

	public boolean withinBounds(int r, int c){
        return r >= 0 && c >= 0 && r < board.length && c < board[0].length;
    }

	private boolean removeKnight(int row, int col, int level){
		if (board[row][col] == 0){
			return false;
		}
		board[row][col] = 0;
		return true;
	}

	public int countSolutions(int row, int col){
		checkSol(row, col);
		board[row][col] = 1;
		return countSolutions(row,col,1);
	} 

	public int countSolutions(int row, int col, int level){
		if (level == board.length*board[0].length){
			// System.out.println(toString());
			return 1;
		}
		int counter = 0;
		ArrayList<Integer[]> arr = possibilities(row, col);
		if (arr.size() == 0) return 0;
		for (Integer[] i: arr){
				if (addKnight(i[0], i[1], level+1)){
						counter += countSolutions(i[0], i[1], level+1);
						removeKnight(i[0], i[1], level+1);
				}
		}
		return counter;
	}

	public boolean solveHeur(int row, int col){
		board[row][col] = 1;
		return solveHeur(row, col,1);
	}

	private boolean solveHeur(int row, int col, int level){
		// System.out.println(toString());
		if (level == board.length*board[0].length){
			// System.out.println(toString());
			return true;
		}
		ArrayList<Integer[]> arr = possibilitiesHeur(row, col);
		if (arr.size() == 0) return false;
		for (Integer[] i: arr){
				if (addKnight(i[0], i[1], level+1)){
					if (solveHeur(i[0], i[1], level+1)){
						return true;
					}else{
						removeKnight(i[0], i[1], level+1);
					}
				}
		}
		return false;
	}

	private ArrayList<Integer[]> possibilitiesHeur(int row, int col){
		ArrayList<Integer[]> arr = possibilities(row, col);
		ArrayList<Integer[]> weightedArr = new ArrayList<Integer[]>();
		ArrayList<Integer> weights = new ArrayList<Integer>();
		for (int i=0; i<arr.size(); i++){
			weights.add(getWeight(arr.get(i)[0], arr.get(i)[1]));
		}
		List<Integer> sortedList = new ArrayList<Integer>(new HashSet<Integer>(weights));
		Collections.sort(sortedList);
		for(int i:sortedList){
			// System.out.println("of weight:" + i);
			for(int j=0; j<weights.size(); j++){
				if (i == weights.get(j)){
					weightedArr.add(arr.get(j));
					// System.out.println(Arrays.toString(arr.get(j)));
				}
			}
		}
		Collections.sort(weights);
		// System.out.println(weights.get(weights.size()-1));
		return weightedArr;
	}

	public int getWeight(int row, int col){
		int counter = 0;
		int[][] deltas = new int[][] { {-2, -1},  {-2, 1},  {2, -1},  {2, 1},  {-1, -2},  {-1, 2}, {1, -2},  {1, 2}};
		for (int[] i: deltas){
			if (withinBounds(row+i[0], col+i[1]) && board[row+i[0]][col+i[1]] == 0){
				counter += 1;
			}
		}
		return counter;
	}

	public void checkSol(int row, int col){
		for (int[] i: board){
			for (int j: i){
				if (j != 0) throw new IllegalStateException();
			}
		}
		try{
			board[row][col] = 0;
		}catch(Exception e){
			throw new IllegalArgumentException();
		}
	}

	public static void main(String[] args){
    KnightBoard a = new KnightBoard(3,3);

    System.out.println(a);
    /* Prints
      _ _ _
      _ _ _
      _ _ _
    */

    for (int i = 0; i < 3; i++){
      for (int j = 0; j < 3; j++){
      	a = new KnightBoard(3,3);
        if (a.solve(i,j)){
          System.out.println("There is an error with your solve method");
        }
      }
    } //prints nothing
    a = new KnightBoard(3,3);
    System.out.println(a.countSolutions(0,0)); //prints 0



    KnightBoard b = new KnightBoard(5,5);
    System.out.println(b.solve(0,0)); //prints true
    System.out.println(b); //prints a valid solution

    try{
      b.solve(0,0);
    }catch(IllegalStateException e){
      System.out.println("Error: The board contains non-zero values");
    } //prints "Error: The board contains non-zero values"

    try{
      b.countSolutions(0,0);
    }catch(IllegalStateException e){
      System.out.println("Error: The board contains non-zero values");
    } //prints "Error: The board contains non-zero values"

    try{
      KnightBoard b1 = new KnightBoard(-1,0);
    }catch(IllegalArgumentException e){
      System.out.println("Error: There cannot be negative parameters in the constructor");
    } //prints "Error: There cannot be negative parameters in the constructor"

    try{
      KnightBoard b1 = new KnightBoard(1,-1);
    }catch(IllegalArgumentException e){
      System.out.println("Error: There cannot be negative parameters in the constructor");
    } //prints "Error: There cannot be negative parameters in the constructor"

    try{
      KnightBoard b1 = new KnightBoard(-1,-1);
    }catch(IllegalArgumentException e){
      System.out.println("Error: There cannot be negative parameters in the constructor");
    } //prints "Error: There cannot be negative parameters in the constructor"

    try{
      KnightBoard b1 = new KnightBoard(5,5);
      b1.solve(0,-1);
    }catch(IllegalArgumentException e){
      System.out.println("Error: There cannot be negative parameters");
    } //prints "Error: There cannot be negative parameters"

    try{
      KnightBoard b1 = new KnightBoard(5,5);
      b1.solve(-1,0);
    }catch(IllegalArgumentException e){
      System.out.println("Error: There cannot be negative parameters");
    } //prints "Error: There cannot be negative parameters"

    try{
      KnightBoard b1 = new KnightBoard(5,5);
      b1.solve(-1,-1);
    }catch(IllegalArgumentException e){
      System.out.println("Error: There cannot be negative parameters");
    } //prints "Error: There cannot be negative parameters"



    for (int i = 0; i < 5; i++){
      for (int j = 0; j < 5; j++){
        KnightBoard abc = new KnightBoard(5,5);
        System.out.println(abc.solve(i,j)); //prints alternating lines of true/false starting with true
      }
    }
    KnightBoard c = new KnightBoard(5,5);

    int totalSol = 0;
    for (int i = 0; i < 5; i++){
      for (int j = 0; j < 5; j++){
      	c = new KnightBoard(5,5);
        totalSol += c.countSolutions(i,j);
      }
    }

    System.out.println(totalSol); //prints 1728

    KnightBoard d = new KnightBoard(5,5);
    System.out.println(d.countSolutions(0,0)); //prints 304

  }
}