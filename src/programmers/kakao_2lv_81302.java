package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//https://programmers.co.kr/learn/courses/30/lessons/81302
public class kakao_2lv_81302 {
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) {
		String[][] places = {
							{"POOOP",
							  "OXXOX",
							  "OPXPX",
							  "OOXOX",
							  "POXXP"},
							 {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
							 {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
							 {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
							 {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
		
		String[][] map;
		List<Pair> startList = new ArrayList<Pair>();
		Queue<Pair> q = new LinkedList<Pair>();
		int[] answer = new int[places.length];
		Arrays.fill(answer, 1);
		
		for (int i = 0; i < places.length; i++) {
			startList = new ArrayList<Pair>();
			map = new String[5][5];
						
			String[] temp = places[i];
			for (int j = 0; j < 5; j++) {
				map[j] = temp[j].split("");
			}
			
			for (int j = 0; j < 5; j++) {
				for (int k = 0; k < 5; k++) {
					if ("P".equals(map[j][k])) {
						startList.add(new Pair(j, k, 0));
					}
				}
			}
			
			for (int j = 0; j < startList.size(); j++) {
				Pair start = startList.get(j);
				q = new LinkedList<Pair>();
				visited = new boolean[5][5];
				
				q.add(start);
				while (!q.isEmpty()) {
					Pair cur = q.poll();
					visited[cur.i][cur.j] = true;
					
					if (answer[i] == 0) {
						break;
					}
					
					for (int t=0; t<4; t++) {
						int nextX = cur.i + dx[t];
						int nextY = cur.j + dy[t];
						
						if (nextX>=0 && nextY>=0 && nextX<5 && nextY<5 && !visited[nextX][nextY]) {
							visited[nextX][nextY] = true;
							Pair next = new Pair(nextX, nextY, 0);
							int nextDist = 0;
							
							if ("O".equals(map[nextX][nextY]))
								nextDist = cur.dist + 1;
							if ("X".equals((map[nextX][nextY])))
								nextDist = cur.dist + 2;
							if ("P".equals(map[nextX][nextY])) {
								nextDist = cur.dist + 1;
								if (nextDist<=2) {
									answer[i] = 0;
									break;
								}
							}
							
							next.dist = nextDist;
							q.add(next);
						}
					}
				}
			}
		}

		System.out.println(Arrays.toString(answer));
		
	}
	
	static class Pair{
		int i;
		int j;
		int dist;
		
		public Pair(int i, int j, int dist) {
			this.i = i;
			this.j = j;
			this.dist = dist;
		}
		
		@Override
		public boolean equals(Object other) {
			if (!(other instanceof Pair))
				return false;
			
			Pair x = (Pair)other;
			if (this.i == x.i && this.j == x.j)
				return true;
			else
				return false;
		}
	}

	//제출용
	class Solution {
		static boolean[][] visited;
		static int[] dx = {-1, 1, 0, 0};
		static int[] dy = {0, 0, -1, 1};
		
	    public int[] solution(String[][] places) {
	    	String[][] map;
			List<Pair> startList = new ArrayList<Pair>();
			Queue<Pair> q = new LinkedList<Pair>();
			int[] answer = new int[places.length];
			Arrays.fill(answer, 1);
			
			for (int i = 0; i < places.length; i++) {
				startList = new ArrayList<Pair>();
				map = new String[5][5];
							
				String[] temp = places[i];
				for (int j = 0; j < 5; j++) {
					map[j] = temp[j].split("");
				}
				
				for (int j = 0; j < 5; j++) {
					for (int k = 0; k < 5; k++) {
						if ("P".equals(map[j][k])) {
							startList.add(new Pair(j, k, 0));
						}
					}
				}
				
				for (int j = 0; j < startList.size(); j++) {
					Pair start = startList.get(j);
					q = new LinkedList<Pair>();
					visited = new boolean[5][5];
					
					q.add(start);
					while (!q.isEmpty()) {
						Pair cur = q.poll();
						visited[cur.i][cur.j] = true;
						
						if (answer[i] == 0) {
							break;
						}
						
						for (int t=0; t<4; t++) {
							int nextX = cur.i + dx[t];
							int nextY = cur.j + dy[t];
							
							if (nextX>=0 && nextY>=0 && nextX<5 && nextY<5 && !visited[nextX][nextY]) {
								visited[nextX][nextY] = true;
								Pair next = new Pair(nextX, nextY, 0);
								int nextDist = 0;
								
								if ("O".equals(map[nextX][nextY]))
									nextDist = cur.dist + 1;
								if ("X".equals((map[nextX][nextY])))
									nextDist = cur.dist + 2;
								if ("P".equals(map[nextX][nextY])) {
									nextDist = cur.dist + 1;
									if (nextDist<=2) {
										answer[i] = 0;
										break;
									}
								}
								
								next.dist = nextDist;
								q.add(next);
							}
						}
					}
				}
			}
			
			return answer;
	    }
	    
	    static class Pair{
			int i;
			int j;
			int dist;
			
			public Pair(int i, int j, int dist) {
				this.i = i;
				this.j = j;
				this.dist = dist;
			}
			
			@Override
			public boolean equals(Object other) {
				if (!(other instanceof Pair))
					return false;
				
				Pair x = (Pair)other;
				if (this.i == x.i && this.j == x.j)
					return true;
				else
					return false;
			}
		}
	}
}
