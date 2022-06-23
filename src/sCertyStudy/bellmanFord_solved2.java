package sCertyStudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
(입력)
2
3 3 1
1 2 2
1 3 4
2 3 1
3 1 3
3 2 1
1 2 3
2 3 4
3 1 8

(출력)
#1 NO
#2 YES
 */
public class bellmanFord_solved2 {
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static int INF = 1000000000;
	
	public static class Edge {
		int u;
		int v;
		int c;

		Edge(int u, int v, int c) {
			this.u = u;
			this.v = v;
			this.c = c;
		}
	}

	public static void main(String[] args)throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int tc = Integer.parseInt(br.readLine());
		for (int tn = 1; tn <=tc; tn++) {
			
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			ArrayList<Edge> edgeArr = new ArrayList<>();
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				edgeArr.add(new Edge(u, v, c));
				edgeArr.add(new Edge(v, u, c));
			}
			
			for (int i = 0; i < w; i++) {
				st = new StringTokenizer(br.readLine());
				int uW = Integer.parseInt(st.nextToken());
				int vW = Integer.parseInt(st.nextToken());
				int cW = Integer.parseInt(st.nextToken());
				edgeArr.add(new Edge(uW,vW,-cW));
			}
			
			int d[] = new int[n+1];
			Arrays.fill(d, INF);
			boolean isNegativeCycleExist = false;
			
			for (int i = 1; i < n; i++) {
				for (Edge e : edgeArr) {
					int u = e.u;
					int v = e.v;
					int c = e.c;
					if(d[v] > d[u]+c){
						d[v] = d[u]+c;
					}
				}
			}
			
			//문제조건에 의해 음수 사이클이 존재하는지만 보면 된다
			for (Edge e : edgeArr) {
				int u = e.u;
				int v = e.v;
				int c = e.c;
				if(d[v] > d[u]+c){
					isNegativeCycleExist = true;
				}
			}
			
			if(isNegativeCycleExist){
				bw.write("#"+tn+" YES\n");
			}
			else{
				bw.write("#"+tn+" NO\n");
			}
		}
		
		bw.flush();
		br.close();		
		bw.close();
	}	
}
