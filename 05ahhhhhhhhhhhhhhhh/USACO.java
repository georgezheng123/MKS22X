import java.io.*;
import java.util.*;

public class USACO {



	public static int bronze(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[r][c];
		int[][] moves = new int[n][3];
		for (int i=0; i<r; i++){
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<c; j++){
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i=0; i<n; i++){
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<3; j++){
				moves[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// stomp(moves[0][0],moves[0][1],moves[0][2],arr);
		// stomp(moves[1][0],moves[1][1],moves[1][2],arr);
		for (int[] i: moves){
			stomp(i[0],i[1],i[2],arr);
		}
		int total = 0;
		for (int i=0; i<r; i++){
			for (int j=0; j<c; j++){
				int diff = e - arr[i][j];
				if (diff > 0) total += diff;
			}
		}
		return total * 5184;
	}

	public static void stomp(int r, int c, int amount, int[][] arr){
		int max = 0;
		for (int i=-1; i<2; i++){
			for (int j=-1; j<2; j++){
				if (arr[r+i][c+j] > max) max = arr[r+i][c+j];
			}
		}
		int change = max - amount; if (change <= 0) change = 0;
		for (int i=-1; i<2; i++){
			for (int j=-1; j<2; j++){
				if (arr[r+i][c+j] > change) arr[r+i][c+j] = change;
			}
		}
		
	}

	public static int silver(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int h = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		
		char[][] board = new char[260][260];
		int[][][] moves = new int[260][260][33];

		for (int i=0; i<h; i++){
			String sr = br.readLine();
			for (int j=0; j<w; j++){
				board[i][j] = sr.charAt(j);
			}
		}

		final int[] dy = new int[] {0,0,+1,-1};  
		final int[] dx = new int[] {+1,-1,0,0};

		st = new StringTokenizer(br.readLine());
		int r1 = Integer.parseInt(st.nextToken())-1;
		int c1 = Integer.parseInt(st.nextToken())-1;
		int r2 = Integer.parseInt(st.nextToken())-1;
		int c2 = Integer.parseInt(st.nextToken())-1;

		moves[r1][c1][0] = 1;

		for(int i = 1; i <= t; ++i){
			for(int y = 0; y < h; ++y){
				for(int x = 0; x < w; ++x){

					moves[y][x][i] = 0;
					if(board[y][x] == '*') continue;

					int k=0;
					while(k<4){
						moves[y][x][i] += getValue(moves, board, x + dx[k],y + dy[k],w,h,y,x,i);
						k++;
					}
				}
			}
		}
		return moves[r2][c2][t];
	}

	public static int getValue(int[][][] dp, char[][] grid, int x2, int y2, int w, int h, int y, int x, int i){
		if(!(0 <= x2 && x2 < w && 0 <= y2 && y2 < h)) return 0;

		if(grid[y2][x2] == '*'){
			return 0;
		}
	
		return dp[y2][x2][i-1];
	}

	public static void main(String[] args) throws IOException{ 
		int heh = silver("silver1.dat");
		System.out.println(heh);
				// for(int[] i: arr){
		// 	System.out.println(Arrays.toString(i));
		// }
		// for(int[] i: moves){
		// 	System.out.println(Arrays.toString(i));
		// }
	}
}