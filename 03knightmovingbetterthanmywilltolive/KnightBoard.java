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

	public boolean solve(){
		return solve(0,0,1);
	}

	private boolean solve(int row, int col, int level){
		return false;
	}

	private boolean addKnight(int row, int col, int level){
		if (board[row][col] != 0){
			return false;
		}
		board[row][col] = level;
		return true;
	}

	private int[][] possibilities(){
		int[][] deltas = new int[][] { {-2, -1},  {-2, 1},  {2, -1},  {2, 1},  {-1, -2},  {-1, 2}, {1, -2},  {1, 2}};
		return deltas;
	}

	private boolean removeKnight(int row, int col, int level){
		if (board[row][col] == 0){
			return false;
		}
		board[row][col] = 0;
		return true;
	}

	public int countSolutions(){
		int counter = 0;
		return counter;
	} 

	public static void main(String[] args){
		KnightBoard potato = new KnightBoard(5,5);
		System.out.println(potato);
	}

}