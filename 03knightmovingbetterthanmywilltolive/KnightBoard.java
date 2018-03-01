import java.util.*;

public class KnightBoard{

	private int[][] board;

	public KnightBoard(int startingRows,int startingCols){
		board = new int[startingRows][startingCols]; 
	}

	public String toString(){
		String ans = "";
		for (int i=0; i<board.length; i++){
            for (int j=0; j<board[0].length; j++){
                ans += (board[i][j]<10? " ":"") + board[i][j] + " ";
            }
            ans += "\n";
        }       
		return ans;
	}

	public boolean solve(int row, int col){
		board[row][col] = 1;
		return solve(row, col,1);
	}

	private boolean solve(int row, int col, int level){
		
		if (level == board.length*board[0].length){
			System.out.println(toString());
			return true;
		}
		ArrayList<Integer[]> arr = possibilities(row, col);
		if (arr.size() == 0) return false;
		for (Integer[] i: arr){
				if (addKnight(i[0], i[1], level+1)){
					if (solve(i[0], i[1], level+1)){
						return true;
					}else{
						removeKnight(i[0], i[1], level+1);
					}
				}
		}
		return false;
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
		board[row][col] = 1;
		return countSolutions(row,col,1);
	} 

	public int countSolutions(int row, int col, int level){
		if (level == board.length*board[0].length){
			System.out.println(toString());
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
		System.out.println(toString());
		if (level == board.length*board[0].length){
			System.out.println(toString());
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
		Set<Integer> s = new HashSet<Integer>(weights);
		List<Integer> sortedList = new ArrayList<Integer>(s);
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
		// System.out.println("return size: " + weightedArr.size());
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



	public static void main(String[] args){
		KnightBoard potato = new KnightBoard(25,25);
		// for(Integer[] i: potato.possibilities(3,3)){
		// 	System.out.println(Arrays.toString(i));
		// }
		// for(Integer[] i: potato.possibilitiesHeur(1,2)){
		// 	System.out.println(Arrays.toString(i));
		// }
		potato.solveHeur(0,0);

	}

}