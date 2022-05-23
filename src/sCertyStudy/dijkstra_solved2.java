package sCertyStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
(입력)
2
3 3
1 2 3
2 3 1
1 3 2
6 9
1 2 2
1 3 4
2 3 1
2 5 2
2 4 4
3 5 3
4 5 3
4 6 2
5 6 2

(출력)
#1 2
#2 6
 */
public class dijkstra_solved2 {
	 static class Node implements Comparable {
	        int dest;
	        int cost;
	        public Node(int dest, int cost) {
	            this.dest = dest;
	            this.cost = cost;
	        }

	        // min-heap (오름차순)
	        @Override
	        public int compareTo(Object o) {
	            return this.cost - ((Node)o).cost;
	        }
	    }

	    static ArrayList<Node>[] adjList;
	    static int[] cost;
	    static int N;

	    public static void main(String[] args) throws IOException {
	    	
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        StringTokenizer st;
	        int T = Integer.parseInt(br.readLine());

	        for (int tc = 1; tc <= T; tc++) {
	            st = new StringTokenizer(br.readLine());
	            N = Integer.parseInt(st.nextToken());
	            int M = Integer.parseInt(st.nextToken());

	            cost = new int[N+1];
	            adjList = new ArrayList[N+1];

	            
	            for (int i = 0; i < cost.length; i++) {
					cost[i] = Integer.MAX_VALUE;					
				}
	            
	            // 인접리스트 초기화
	            for (int i = 0; i < N + 1; i++) {
	                adjList[i] = new ArrayList<>();
	            }

	            for (int i = 0; i < M; i++) {
	                st = new StringTokenizer(br.readLine());
	                int a = Integer.parseInt(st.nextToken());
	                int b = Integer.parseInt(st.nextToken());
	                int c = Integer.parseInt(st.nextToken());

	                adjList[a].add(new Node(b, c));
	                // 양방향 간선
	                adjList[b].add(new Node(a, c));
	            }

	            dijkstra(1);

	            if(cost[N]==Integer.MAX_VALUE)
	            	System.out.println("#" + tc + " -1");
	            else
	            	System.out.println("#" + tc + " " + cost[N]);

	        }
	    }

	    static void dijkstra(int start) {
	        dijkstra(start, -1);
	    }

	    static void dijkstra(int start, int dest) {
	        PriorityQueue<Node> PQ = new PriorityQueue<>();
	        
	        for(int i = 1; i <= N; i++) {
	            cost[i] = Integer.MAX_VALUE;
	        }

	        cost[start] = 0;
	        PQ.add(new Node(start, 0));

	        while(!PQ.isEmpty()) {
	            Node now = PQ.poll();

	            if(now.dest == dest)
	                break;

	            // 연결된 간선 탐색
	            for(Node next : adjList[now.dest]) {
	                // cost 가 더 작을 때만 갱신하고 PQ큐에 넣음
	                if(cost[next.dest] > next.cost + now.cost) {
	                    cost[next.dest] = next.cost + now.cost;
	                    PQ.add(new Node(next.dest, cost[next.dest]));
	                }
	            }
	        }
	    }

}
