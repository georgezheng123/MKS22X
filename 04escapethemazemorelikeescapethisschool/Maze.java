import java.util.*;
import java.io.*;
public class Maze{

    private char[][] maze;
    private boolean animate;//false by default
    private int[][] moves;
    private int r;
    private int c;
    private int[] eloc;

    /*Constructor loads a maze text file, and sets animate to false by default.

      1. The file contains a rectangular ascii maze, made with the following 4 characters:
      '#' - Walls - locations that cannot be moved onto
      ' ' - Empty Space - locations that can be moved onto
      'E' - the location of the goal (exactly 1 per file)
      'S' - the location of the start(exactly 1 per file)

      2. The maze has a border of '#' around the edges. So you don't have to check for out of bounds!

      3. When the file is not found OR the file is invalid (not exactly 1 E and 1 S) then: 
         throw a FileNotFoundException or IllegalStateException
    */
    public Maze(String filename) {
      
        try{
          File text = new File(filename);
        Scanner inf = new Scanner(text);
        int ecount = 0, scount = 0;
        ArrayList<char[]> temp = new ArrayList<char[]>();

        while(inf.hasNextLine()){
            String line = inf.nextLine();
            char[] bob = line.toCharArray();
            for (char i: bob){
              if (i == 'S') scount += 1;
              if (i == 'E') ecount += 1;
            }
            temp.add(bob);
        }
        if (scount != 1 || ecount != 1) throw new IllegalStateException();
        maze = new char[temp.size()][temp.get(0).length];
        r = temp.size();
        c = temp.get(0).length;
        for (int i=0; i<temp.size(); i++){
          maze[i] = temp.get(i);
        }
          // System.out.println(toString());
        moves = compareES();

        }// can be a path like: "/full/path/to/file.txt" 
        catch(IOException e) {
          System.out.println(e);
        }  

    }
    
    private void wait(int millis){
         try {
             Thread.sleep(millis);
         }
         catch (InterruptedException e) {
         }
     }

    public void setAnimate(boolean b){
        animate = b;
    }

    public boolean isValid(int row, int col){
      return maze[row][col] == ' ' || maze[row][col] == 'E';
    }

    public void clearTerminal(){
        //erase terminal, go to top left of screen.
        System.out.println("\033[2J\033[1;1H");
    }


    /*Wrapper Solve Function returns the helper function
      Note the helper function has the same name, but different parameters.
      Since the constructor exits when the file is not found or is missing an E or S, we can assume it exists.
    */
    public int solve(){
            int[] loc = findEraseS();
            return solve(loc[0], loc[1]);
    }

    public int[] findEraseS(){
      int[] location = find('S');
      maze[location[0]][location[1]] = '@';
      return location;
    }

    public int[] find(char thing){
      for (int i=0; i<maze.length; i++){
        for (int j=0; j<maze[0].length; j++){
          if (maze[i][j] == thing){
            return new int[] {i, j};
          }
        }
      }
      return new int[] {0, 0};
    }

    public int[][] compareES(){
      int[] s = find('S');
      int[] e = find('E');
      eloc = e;
      int[][] bob = new int[][] {{-1,0}, {0,1}, {0,-1}, {1, 0}};
      if (s[0] < e[0]){//e is below s
        if (s[1] < e[1]){//e is to the right of s
          // System.out.println(Arrays.toString(e) + "e");
          // System.out.println(Arrays.toString(s) + "s");
          if (e[0] - s[0] < e[1] - s[1]){//more distance rightwards
            bob = new int[][] {{0,1}, {1,0}, {0,-1}, {-1, 0}};
            // System.out.println("wrong");
          }else{
            bob = new int[][] {{1,0}, {0,1}, {0,-1}, {-1, 0}};
            // System.out.println("not wrong");
          }
        }else{//e is to the left of s
            if (Math.abs(e[0] - s[0]) < Math.abs(e[1] - s[1])){//more leftwards
            bob = new int[][] {{0, -1}, {1,0}, {-1, 0}, {0,1}};
          }else{
            bob = new int[][] {{1,0}, {0, -1}, {-1, 0}, {0,1}};
          }
        }
      }else{//e is above s
        if (s[1] < e[1]){//e is to the right of s
          if (e[0] - s[0] < e[1] - s[1]){//more distance rightwards
            bob = new int[][] {{0,1}, {-1,0}, {0,-1}, {1, 0}};
          }else{
            bob = new int[][] {{-1,0}, {0,1}, {0,-1}, {1, 0}};
          }
        }else{//e is to the left of s
            if (Math.abs(e[0] - s[0]) < Math.abs(e[1] - s[1])){//more leftwards
            bob = new int[][] {{0, -1}, {-1,0}, {1, 0}, {0,1}};
          }else{
            bob = new int[][] {{-1,0}, {0, -1}, {1, 0}, {0,1}};
          }
        }
      }
      return bob;
    }


    /*
      Recursive Solve function:

      A solved maze has a path marked with '@' from S to E.

      Returns the number of @ symbols from S to E when the maze is solved,
      Returns -1 when the maze has no solution.

      Postcondition:
        The S is replaced with '@' but the 'E' is not.
        All visited spots that were not part of the solution are changed to '.'
            Note: This is not required based on the algorithm, it is just nice visually to see.
        All visited spots that are part of the solution are changed to '@'
    */
    private int solve(int row, int col){ //you can add more parameters since this is private

        //automatic animation! You are welcome.
        if(animate){
            clearTerminal();
            System.out.println(this);
            wait(30);
        }
          // System.out.println(Arrays.toString(i));
        for (int[] i: moves){
        if (maze[row + i[0]][col + i[1]] == 'E'){
          System.out.println("found");
              return 1;
            }
        }
        for (int[] i: moves){
          // System.out.println(Arrays.toString(i));
          if (isValid(row + i[0], col + i[1])){
              // System.out.println("out");
              maze[row + i[0]][col + i[1]] = '@';
              int ah = solve(row + i[0], col + i[1]);
              if (ah > 0){
                return 1 + ah;
              }
              maze[row + i[0]][col + i[1]] = ' ';
          }
        }
        return -1; //so it compiles
    }

    public String toString(){
      String blah = "";
      for (char[] i: maze){
          for (char j: i){
            blah += j;
          }
          blah += "\n";
        }
        return blah;
    }

    public static void main(String[] args){
      Maze a = new Maze("data1.dat");
      a.solve();
      System.out.println(a);
    }

}