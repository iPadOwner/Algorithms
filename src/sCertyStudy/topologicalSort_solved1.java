package sCertyStudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
(입력)
2
4 3 2
1 2 2 1
2 3 3 4
3 4 5 4
2 1 2
1 2 5 7

(출력)
#1 11 3
#2 7 1

*/
public class topologicalSort_solved1 {
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

	static int[] inputEdge;
	static ArrayList<Integer> ordered;
	static ArrayList<Node>[] adj;

	static int N;
	static int M;
	static int K;
	
	static int MAX_N = 100000;
	static int MAX_M = 200000;

	static class Node {
		public int cur;
		public int to;
		public int cost;

		public Node(int cur, int to, int cost) {
			this.cur = cur;
			this.to = to;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tc = Integer.parseInt(br.readLine());

		for (int tn = 1; tn <= tc; tn++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			adj = new ArrayList[MAX_M + 1];
			inputEdge = new int[MAX_N + 1];
			ordered = new ArrayList<Integer>();

			for (int i = 0; i <= N; i++)
				adj[i] = new ArrayList<Node>();

			long answer = 0l;
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				int q = Integer.parseInt(st.nextToken());
				
				if (p==q) {
					answer += p;
				}
				else if (p>q) {
					adj[a].add(new Node(a, b, p));
					inputEdge[b]++;
				}
				else {
					adj[b].add(new Node(b, a, q));
					inputEdge[a]++;
				}
			}

			int subAnswer = topologicalSort(K);
			
			for (int here : ordered)
				for (Node v : adj[here])
					answer += v.cost;
			
			bw.write("#" + tn + " " + answer + " " + subAnswer + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static int topologicalSort(int K) throws Exception{
		PriorityQueue<Integer> startNode = new PriorityQueue<>();
		ordered.clear();

		for (int i = 1; i <= N; i++)
			if (inputEdge[i] == 0)
				startNode.add(i);

		int cnt = 0;
		int orderAnswer = 0;
				
		while(!startNode.isEmpty()) {
			int cur = startNode.poll();
			cnt++;
			ordered.add(cur);
			
			if (cnt==K)
				orderAnswer = cur;
			
			for(int j=0; j<adj[cur].size(); j++) {
				int next = adj[cur].get(j).to;
								
				if(--inputEdge[next]==0)
					startNode.add(next);
			}
		}
		
		return orderAnswer;
	}
}
