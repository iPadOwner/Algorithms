package sCertyStudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
(입력)
3
8
5 0 4 8 5 3 7
4 0 2 1 4
8 1 4 1 4 7 8
1 0 4 7 3 4 6
0 0 3 3 5 8
4 1 2 7 4
8 0 2 5 8
5 1 1 5
5
3 0 3 3 5 2
1 1 2 4 1
0 1 4 1 2 3 4
1 0 1 3
3 1 4 1 3 4 5
3
0 0 1 1
1 0 1 1
1 1 1 1

(출력)
#1 16
#2 10
#3 4
*/
public class LCA_solved1 {
	
	static int LOGNUM = 17;
	static int[][] parent;
	static int[] depth;
	static boolean[] visited;
	static ArrayList<Integer>[] adj;
	static ArrayList<Integer>[] roomNumList;
	
	static int N;
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int tc = Integer.parseInt(br.readLine());
		
		for (int tn = 1; tn<=tc; tn++) {
			
			N = Integer.parseInt(br.readLine());
			parent = new int[LOGNUM+1][N+1];
			visited = new boolean[N+1];
			depth = new int[N+1];
			adj = new ArrayList[N+1];
			roomNumList = new ArrayList[N+1];
			
			for (int i = 0; i <=N; i++) {
				adj[i] = new ArrayList<Integer>();
				roomNumList[i] = new ArrayList<Integer>();
			}
			
			int root = -1;
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				int child = i;
				int par = Integer.parseInt(st.nextToken());
				int mode = Integer.parseInt(st.nextToken());
				
				//문제조건에 의한 루트노드
				if (par == 0)
					root = par;
				
				adj[par].add(child);
				
				int k = Integer.parseInt(st.nextToken());
				
				//i번 개미가 말한 방 번호들을 담아둔다
				for(int j=0; j<k; j++) {
					int roomNum = Integer.parseInt(st.nextToken());
					roomNumList[child].add(roomNum);
				}
			}
			
			bfs(root, 0);
			aces_find();
			
			long answer = 0;
			
			for (int i=1; i<=N; i++) {
				int curDepth = depth[i];
				
				for (int room : roomNumList[i]) {
					
					if (depth[room] > curDepth)
						continue;
					
					if (lca(i, room) == room) {
						curDepth = Math.min(curDepth, depth[room]);
					}
				}
				answer += (long)(depth[i] - curDepth);//이문제에선 음수나올일 없다
			}
			
			answer = answer * 2l;
			
			bw.write("#" + tn + " " + answer + "\n");
		}
		
		bw.flush();
		br.close();
		bw.close();
	}
	
	static void bfs(int start, int dep) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(start);
		visited[start] = true;
		depth[start] = dep;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for (int next : adj[cur]) {
				if (visited[next])
					continue;
				visited[next] = true;
				parent[0][next] = cur;
				depth[next] = depth[cur]+1;
				
				q.add(next);
			}
		}
	}
	
	static void aces_find() {
		for (int K=1; K<=LOGNUM; K++)
			for (int V=1; V<=N; V++)
				parent[K][V] = parent[K-1][parent[K-1][V]];
	}
	
	static int lca(int x, int y) {
		if (depth[x]>depth[y]) {
			int temp = x;
			x = y;
			y = temp;
		}
		
		for (int i = LOGNUM; i >=0; i--) {
			if ((depth[y]-depth[x]) >= (1<<i)) {
				y = parent[i][y];
			}
		}
		
		if (x==y)
			return x;
		
		for (int i = LOGNUM; i >=0; i--) {
			if (parent[i][x] != parent[i][y]) {
				x = parent[i][x];
				y = parent[i][y];
			}
		}
		
		return parent[0][x];
	}
}
