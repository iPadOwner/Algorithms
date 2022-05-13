package sCertyStudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
[입출력 예]
(입력)
3
6 6 0
1 2 3
5 1 2
5 6 5
3 4 3
6 4 6
2 3 3
1 4
6 6 1
1 2 3
5 1 2
5 6 5
3 4 3
6 4 6
2 3 3
4 1
6 6 2
1 2 3
5 1 2
5 6 5
3 4 3
6 4 6
2 3 3
1 4

(출력)
#1 9
#2 7
#3 4
*/
public class dijkstra_solved_1 {
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	
	static ArrayList<Node>[] adj;
	static long[][] D;//D[N][K] : N까지 가는데 워프를 K개 사용했을때 노드의 최단거리
	static int N;
	static int M;
	static int K;
	
	static long INF = 10000000001l;
	
	static class Node implements Comparable<Node>{
		int end;//도착점
		int warpCount;//워프한 횟수
		long cost;//비용
		
		public Node(int end, int warpCount, long cost) {
			this.end = end;
			this.cost = cost;
			this.warpCount = warpCount;
		}

		@Override
		public int compareTo(Node other) {
			return Long.compare(this.cost, other.cost);
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int tc = Integer.parseInt(br.readLine());
		
		for (int tn = 1; tn <=tc; tn++) {			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
		
			adj = new ArrayList[N+1];
			for (int i = 0; i<=N; i++)
				adj[i] = new ArrayList<Node>();			
			
			D = new long[N+1][K+1];
			for (int i = 0; i <=N; i++)
				Arrays.fill(D[i], INF);
			
			for (int i = 0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				long cost = Long.parseLong(st.nextToken());
				
				adj[from].add(new Node(to, 0, cost));
				adj[to].add(new Node(from, 0, cost));
			}
			
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			long answer = dijkstra(x, y);
	
			bw.write("#" + tn + " " + answer + "\n");
		}
		
		bw.flush();
		br.close();		
		bw.close();
	}
	
	static long dijkstra(int start, int end) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		D[start][0] = 0;
		Node x = new Node(start, 0, 0);
		pq.add(x);
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int here = cur.end;
			int warpCount = cur.warpCount;
			
			//불필요한 탐색 skip. 아래 구문 생략시 샘플 다맞아도 시간초과 발생
			//여기까지 cur.warpCount만큼 워프를 해서 온 비용이(cur.cost),
			//최소값 (D[here][warpCount]) 보다 큰것은 더 볼필요도 없다
			if (D[here][warpCount] < cur.cost)
				continue;
			
			//도착지에 도착하면 종료
			if (here == end)
				return cur.cost;
			
			for (Node next : adj[here]) {
				//다음지점으로 이동시 추가적으로 워프를 사용하지 않음
				//here까지 오는데 0개를 썼으면 0개, 1개를 썼다면 1개, 2개를 썼다면 2개 그대로 두고,
				//즉 워프를 추가적으로 하지 않고 원래 이동에 필요한 비용만 가지고 비교한다
				if(D[next.end][warpCount] > next.cost + cur.cost) {
					D[next.end][warpCount] = next.cost + cur.cost;
					Node temp = new Node(next.end, warpCount, D[next.end][warpCount]);
					pq.add(temp);
				}			
				
				//워프 사용 하는 케이스
				//워프 사용 개수가 K보다 작은경우, 추가적으로 워프를 사용하는 경우의 비용을 갱신
				//워프를 쓰면 문제조건에 의해 비용은 무조건 1로 고정된다
				// (1)D[next.end][warpCount+1] : next까지 워프를 한번 더 해서(warpCount+1) 갈때의 비용
				// (2)D[here][warpCount] : here까지 warpCount만큼 워프해서 온 비용
				// (1) > (2)+1(워프할경우 비용은 1) 이면 갱신한다
				if(warpCount<K && D[next.end][warpCount+1] > D[here][warpCount]+1) {
					D[next.end][warpCount+1] = D[here][warpCount]+1;
					
					Node temp = new Node(next.end, warpCount+1, D[next.end][warpCount+1]);
					pq.add(temp);
				}			
			}
		}
		
		return -1;
	}
}
