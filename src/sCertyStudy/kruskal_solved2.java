package sCertyStudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
(입력)
1
10 5 5
1 8 23
8 4 8
4 5 23
5 2 15
2 6 9
6 9 26
9 10 2
10 3 24
3 7 17
7 1 19
(출력)
#1 62
*/
public class kruskal_solved2 {
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	
	static int N;
	static int M;
	static int K;
	static int[] parent;
    static ArrayList<Edge> edges_asis;
    static ArrayList<Edge> edges_tobe;
	
    static Comparator<Edge> cost_asc = new Comparator<Edge>() {
		public int compare(Edge a, Edge b) {
			return Long.compare(a.cost, b.cost);
		}};
		
	static Comparator<Edge> cost_desc = new Comparator<Edge>() {
		public int compare(Edge a, Edge b) {
			return Long.compare(b.cost, a.cost);
		}};
    
    static class Edge {
        int start;
        int end;
        long cost;
        
        public Edge(int start, int end, long cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
    	br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int tc = Integer.parseInt(br.readLine());
		
		for (int tn = 1; tn <= tc; tn++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			parent = new int[N+1];
			edges_asis = new ArrayList<>();
			edges_tobe = new ArrayList<>();
			for (int i = 0; i <=N; i++) {
				parent[i] = i;
			}
	        
			for (int i = 0; i <M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				long cost = Long.parseLong(st.nextToken());
				
				edges_asis.add(new Edge(from, to, cost));
			}
			
			for (int i = 0; i <K; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				long cost = Long.parseLong(st.nextToken());
				
				edges_tobe.add(new Edge(from, to, cost));
			}
			
			long answer = 0l;		
			
			answer += kruskal(0, edges_asis);
			answer += kruskal(1, edges_tobe);			
			
			bw.write("#" + tn + " " + answer + "\n");
    	}
		
        bw.flush();
        br.close();
        bw.close();
    }
    

    static long kruskal(int mode, ArrayList<Edge> list) {
    	long mst_cost = 0l;
    	
        Collections.sort(list, (mode==0?cost_desc:cost_asc));
        
        if (mode==0){
        	 for(Edge road : list) {
 	            if(!find(road.start, road.end)) {
 	                union(road.start, road.end);
 	            }
 	            else {
 	            	mst_cost += road.cost;
 	            }
 	        }
        }
        else {
	        for(Edge road : list) {
	            if(!find(road.start, road.end)) {
	                mst_cost += road.cost;
	                union(road.start, road.end);
	            }
	        }
        }        

        return mst_cost;
    }

    static void union(int a, int b) {
        int x = getParent(a);
        int y = getParent(b);

        if (x < y) {
            parent[y] = parent[x];
        } else {
            parent[x] = parent[y];
        }
    }

    static boolean find(int a, int b) {
        int x = getParent(a);
        int y = getParent(b);
        return x == y;
    }

    static int getParent(int a) {
        if (parent[a] == a)
            return a;
        return parent[a] = getParent(parent[a]);
    }

}
