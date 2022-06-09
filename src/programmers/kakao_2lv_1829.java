package programmers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

//https://programmers.co.kr/learn/courses/30/lessons/1829
public class kakao_2lv_1829 {
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static boolean[][] visited;
	static int[][] picture = {{1, 1, 1, 0}
							  ,{1, 2, 2, 0}
							  ,{1, 0, 0, 1}
							  ,{0, 0, 0, 1}
							  ,{0, 0, 0, 3}
							  ,{0, 0, 0, 3}};

	public static void main(String[] args) {
		int m = 6;//행
		int n = 4;//열
		
		visited = new boolean[m][n];
				
		int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        HashMap<Integer, Integer> sizeCheckMap = new HashMap<Integer, Integer>();

        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		if (!visited[i][j] && picture[i][j]>0) {
        			visited[i][j] = true;
        			
        			int val = picture[i][j];
        			if (!sizeCheckMap.containsKey(val))
        				sizeCheckMap.put(val, 0);
        			
        			int size = getSizeByBFS(i, j, val, m, n);
        			sizeCheckMap.put(val, Math.max(sizeCheckMap.get(val), size));
        			numberOfArea++;
        		}
        	}
		}

        Iterator<Integer> it = sizeCheckMap.keySet().iterator();
        while(it.hasNext())
        	maxSizeOfOneArea = Math.max(maxSizeOfOneArea, sizeCheckMap.get(it.next()));
        
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;

        System.out.println(Arrays.toString(answer));
	}
	
	static int getSizeByBFS(int i, int j, int val, int m, int n) {
		int sum = 1;
		
		Queue<Pair> queue = new LinkedList<Pair>();
		queue.add(new Pair(i, j));
		
		while(!queue.isEmpty()) {
			Pair cur = queue.poll();
			
			for (int k = 0; k < 4; k++) {
				int nextI = cur.i + dx[k];
				int nextJ = cur.j + dy[k];
				
				if (nextI>=0 && nextJ>=0 && nextI<m && nextJ<n
				    && !visited[nextI][nextJ] && val==picture[nextI][nextJ]) {
					visited[nextI][nextJ] = true;
					sum++;
					queue.add(new Pair(nextI, nextJ));					
				}
			}
		}
		
		return sum;
	}
	
	static class Pair{
		int i;
		int j;
		
		public Pair(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	

	
	//제출용
	class Solution {
		static int[] dx = {-1,1,0,0};
		static int[] dy = {0,0,-1,1};
		static boolean[][] visited;
		
	    public int[] solution(int m, int n, int[][] picture) {
	    	visited = new boolean[m][n];
			
			int numberOfArea = 0;
	        int maxSizeOfOneArea = 0;

	        HashMap<Integer, Integer> sizeCheckMap = new HashMap<Integer, Integer>();

	        for (int i = 0; i < m; i++) {
	        	for (int j = 0; j < n; j++) {
	        		if (!visited[i][j] && picture[i][j]>0) {
	        			visited[i][j] = true;
	        			
	        			int val = picture[i][j];
	        			if (!sizeCheckMap.containsKey(val))
	        				sizeCheckMap.put(val, 0);
	        			
	        			int size = getSizeByBFS(i, j, val, m, n, picture);
	        			sizeCheckMap.put(val, Math.max(sizeCheckMap.get(val), size));
	        			numberOfArea++;
	        		}
	        	}
			}

	        Iterator<Integer> it = sizeCheckMap.keySet().iterator();
	        while(it.hasNext())
	        	maxSizeOfOneArea = Math.max(maxSizeOfOneArea, sizeCheckMap.get(it.next()));
	        
	        int[] answer = new int[2];
	        answer[0] = numberOfArea;
	        answer[1] = maxSizeOfOneArea;
	        
	        return answer;
	    }
	    
	    int getSizeByBFS(int i, int j, int val, int m, int n, int[][] picture) {
			int sum = 1;
			
			Queue<Pair> queue = new LinkedList<Pair>();
			queue.add(new Pair(i, j));
			
			while(!queue.isEmpty()) {
				Pair cur = queue.poll();
				
				for (int k = 0; k < 4; k++) {
					int nextI = cur.i + dx[k];
					int nextJ = cur.j + dy[k];
					
					if (nextI>=0 && nextJ>=0 && nextI<m && nextJ<n
					    && !visited[nextI][nextJ] && val==picture[nextI][nextJ]) {
						visited[nextI][nextJ] = true;
						sum++;
						queue.add(new Pair(nextI, nextJ));					
					}
				}
			}
			
			return sum;
		}
		
		class Pair{
			int i;
			int j;
			
			public Pair(int i, int j) {
				this.i = i;
				this.j = j;
			}
		}
	}
}
