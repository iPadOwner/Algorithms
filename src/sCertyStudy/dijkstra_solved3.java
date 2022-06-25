package sCertyStudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;



//this is from feature
public class dijkstra_solved3 {
	static class Node implements Comparable<Node>{
		public int end;
		public int cost;
		
		public Node(int end, int cost) {
			this.end = end;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
	
	
	static int N;
	static int M;
	static int X;
	
	static ArrayList<Node>[] con;
	static ArrayList<Node>[] conReverse;
	
	static int[] D;	
	static int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
		int testCount = Integer.parseInt(br.readLine());

		for (int testNumber = 1; testNumber <= testCount; testNumber++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			con = new ArrayList[N+1];
			conReverse = new ArrayList[N+1];
			D = new int[N+1];
			Arrays.fill(D, INF);
			
			for (int i = 0; i <= N; i++) {
				con[i] = new ArrayList<Node>();
				conReverse[i] = new ArrayList<Node>();
			}
			
			for (int i = 1; i <= M; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				
				//간선. 각 방에서 파티장으로 향하는 간선정보가 있음.
				//이 간선으로는 각 방에서 파티장으로 가는 최단거리를 일일히 구해야 함
				con[s].add(new Node(e, t));
				
				//역방향 간선(파티장에서 각 방으로 가는 방향). 파티장에서 각 방으로 가는 최단거리가 있음.
				//이 간선 기준으로 한번만 다익스트라 돌리면, 파티장에서 각 방으로 향하는 최단거리,
				//즉 방에서 각 파티장으로 향하는 최단거리를 구할수 있다.
				conReverse[e].add(new Node(s, t));
			}
			
			//파티장에서 각 방으로 가는 거리
			dijkstra(X, con);
			
			//D 구한것을 복사한다
			int[] Dreverse = D.clone();
			
			
			//새로 cost를 구하기 위해 초기화한다
			D = new int[N+1];
			Arrays.fill(D, INF);			
			
			//각 방에서 파티장으로 가는 거리
			//파티장에서 역방향 간선으로 가는것과 동일
			dijkstra(X, conReverse);
			
			int result = 0;
			for (int i=1; i<=N; i++){
				//가장 오래 걸리는 학생을 찾는다
				result = Math.max(result, D[i] + Dreverse[i]); 
			}
			
			bw.write("#" + testNumber + " " + result);
			bw.newLine();
		}
		
		bw.flush();
		br.close();
		bw.close();
	}
	
	
	static void dijkstra(int start, ArrayList<Node>[] list) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		D[start] = 0;
		pq.add(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			for (Node next : list[cur.end]) {
				//줄일수있을때만 갱신한다
				if (D[next.end] > next.cost + cur.cost) {
					D[next.end] = next.cost + cur.cost;
					pq.add(new Node(next.end, D[next.end]));
				}				
			}
		}
	}
}
