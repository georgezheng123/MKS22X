public class QueenBoard{

    private int[][] board;

    public QueenBoard(int size){
        board = new int[size][size];
    }

    private boolean queenIterate(boolean toAdd, int r, int c){
        int add = (toAdd) ? 1:-1 ;
        if (toAdd && board[r][c] != 0 || !toAdd && board[r][c] != -1) return false;
        board[r][c] = (toAdd) ? -1:0 ;
        for (int i=0; i<board.length; i++){
            if (i!=c)board[r][i] += add;
            if (i!=r)board[i][c] += add;
        }
        for (int i=r-Math.min(r,c),j=c-Math.min(r,c); withinBounds(i,j); i++,j++){
            if (r != i && c != j) board[i][j] += add;
        }
        for (int k=r-Math.min(r,board[0].length-c-1),m=c+Math.min(r,board[0].length-c-1); withinBounds(k,m); k+=1,m-=1){
            if (r != k && c != m) board[k][m] += add;
        }
        return true;
    }

    public boolean withinBounds(int r, int c){
        return r >= 0 && c >= 0 && r < board.length && c < board[0].length;
    }

    private boolean addQueen(int r, int c){
        return queenIterate(true, r, c);
    }

    private boolean removeQueen(int r, int c){
        return queenIterate(false, r, c);
    }

    public String toString(){
        String ans = "";
        for (int i=0; i<board.length; i++){
            for (int j=0; j<board[0].length; j++){
                ans += (board[i][j] < 0) ? "Q ":"_ ";;
            }
            ans += "\n";
        }       

        return ans;
    }

    public boolean solve(){
        checkBoard();
        if (!solve(0)){
            board = new int[board.length][board.length];
            return false;
        }
        return true;
    }

    public boolean solve(int col){
        if (col >= board[0].length) return true;
        for (int i=0; i<board.length; i++){
            if (addQueen(i, col)){
                if (solve(col+1)){
                    return true;
                }else{
                    removeQueen(i,col);
                }
            }
        }
        return false;
    }

    public void checkBoard(){
        for (int[] i: board){
            for (int j: i){
                if (j != 0) throw new IllegalStateException();
            }
        }
    }

    public int countSolutions(){
        checkBoard();
        int solns = countSolutions(0);
        if (solns == 0) board = new int[board.length][board.length];
        return solns;
    }

    public int countSolutions(int col){
        int counter=0;
        if (col >= board[0].length) return 1;
        for (int i=0; i<board.length; i++){
            if (addQueen(i, col)){
                int numb = countSolutions(col+1);
                    counter += numb;
                removeQueen(i,col);
            }
        }
        return counter;
    }

}