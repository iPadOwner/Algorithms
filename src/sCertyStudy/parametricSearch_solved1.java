package sCertyStudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
(입력)
1
5
1 1 3 6 8
1 1 8 5 6
8 10 0 4 3
8 0 2 3 4
4 3 0 2 1


(출력)
#1 5
*/
public class parametricSearch_solved1 {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	
	static int N;
	static int[][] mountain;
	static boolean[][] visited;
	
	static int[] movX = {-1,1,0,0};
	static int[] movY = {0,0,-1,1};
	
	static int minHeight;
	static int maxHeight;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tc = Integer.parseInt(br.readLine());
		for (int tn = 1; tn <= tc; tn++) {
			N = Integer.parseInt(br.readLine());
			
			mountain = new int [N+1][N+1];
			
			minHeight = Integer.MAX_VALUE;
			maxHeight = 0;
			
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				
				for (int j = 1; j <=N ; j++) {
					int h = Integer.parseInt(st.nextToken());
					mountain[i][j] = h;
					
					minHeight = Math.min(minHeight, h);
					maxHeight = Math.max(maxHeight, h);
				}
			}
	
			int l = 0;
			int h = maxHeight - minHeight;
			
			while(l<=h) {
				int m = (l+h)/2;
				if (check(m))
					h = m-1;
				else
					l = m+1;
			}
			
			bw.write("#" + tn + " " + (h+1) + "\n");
		}
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	static boolean bfs(int x, int y, int low, int high) {
		Queue<int[]> q = new LinkedList<int[]>();
		
		int[] start = {x, y, low, high};
		q.add(start);
		
		boolean rtn = false;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int curX = cur[0];
			int curY = cur[1];
			int curLow = cur[2];
			int curHigh = cur[3];
			
			if (curX<1 || curX>N || curY<1 || curY>N)
				continue;
			
			if (curLow > mountain[curX][curY] || curHigh < mountain[curX][curY])
				continue;
			
			if (curX==N&&curY==N)
				return true;
			
			visited[curX][curY] = true;
			
			for (int i = 0; i <4; i++) {
				int nextX = curX+movX[i];
				int nextY = curY+movY[i];
				
				if (nextX<1 || nextX>N || nextY<1 || nextY>N)
					continue;
				
				if (visited[nextX][nextY])
					continue;
				
				if (curLow > mountain[nextX][nextY] || curHigh < mountain[nextX][nextY])
					continue;
				
				visited[nextX][nextY] = true;
				
				int[] temp = {nextX, nextY, curLow, curHigh};
				q.add(temp);
			}
			
		}
		
		return rtn;
	}
	
	
	static boolean dfs(int x, int y, int low, int high) {
		if (x<1 || x>N || y<1 || y>N)
			return false;
		
		if (low > mountain[x][y] || high < mountain[x][y])
			return false;
		
		if (x==N && y==N)
			return true;
		
		visited[x][y] = true;
		
		for (int i = 0; i <4; i++) {
			int nextX = x+movX[i];
			int nextY = y+movY[i];
			
			if (nextX<1 || nextX>N || nextY<1 || nextY>N)
				continue;
			
			if (visited[nextX][nextY])
				continue;
			
			if (dfs(nextX, nextY, low, high)) {
				return true;
			}
		}
		
		return false;
	}
	
	static boolean check(int m) {
		for(int l = minHeight; l<=(maxHeight-m); l++) {
			visited = new boolean [N+1][N+1];
			
			//if (dfs(1, 1, l, l+m))
			if (bfs(1, 1, l, l+m))
				return true;
		}
		
		return false;
	}
}

