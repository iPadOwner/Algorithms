package sCertyStudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

/*
(입력)
3      
4 3    
1 2    
2 3    
3 4    
5      
2 1 2  
2 1 4
1 2
2 1 2
2 1 4
4 4    
1 2
2 3
3 4
4 1
5
2 1 4
1 2
2 1 2
2 1 4
2 3 1
8 9    
5 4
2 6
6 7
1 3
1 6
7 8
1 8
8 2
7 2
11
2 5 6
1 6
1 5
2 1 6
2 1 7
1 3
2 1 7
1 8
2 1 6
2 3 8
1 4

(출력)
#1 1110
#2 1111
#3 011101
*/
public class unionFind_solved1 {
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	
	static int[] par;

	static int N;
	static int M;
	static int Q;
	
	static Stack<String> questions;
	static List<String> nationList;
	static boolean[] conn;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int tc = Integer.parseInt(br.readLine());
		for (int tn = 1; tn <= tc; tn++) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			par = new int[N+1];
			
			for (int i=1; i<=N; i++) {
				par[i] = i;
			}
			
			questions = new Stack<String>();
			nationList = new ArrayList<String>();
			conn = new boolean[M+1];
			Arrays.fill(conn, true);
			
			for (int i=0; i<M; i++) {
				nationList.add(br.readLine());
			}
			
			st = new StringTokenizer(br.readLine());
			Q = Integer.parseInt(st.nextToken());
			
			for (int i = 1; i<=Q; i++) {
				String q = br.readLine();
				
				if (q.startsWith("1")) {
					st = new StringTokenizer(q); st.nextToken();
					conn[Integer.parseInt(st.nextToken())] = false;
				}
				
				questions.add(q);
			}
			
			for (int i = 0; i<M; i++) {
				st = new StringTokenizer(nationList.get(i));
				
				if (conn[i+1])
					union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			StringBuilder sb = new StringBuilder();
			while (!questions.isEmpty()) {
				String q = questions.pop();
				
				if (q.startsWith("1")) {
					st = new StringTokenizer(q); st.nextToken();
					
					int target = Integer.parseInt(st.nextToken());
					st = new StringTokenizer(nationList.get(target-1));
					
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());
					union(a, b);
				}
				else {
					st = new StringTokenizer(q);st.nextToken();
					
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());
					
					if (isSameGroup(a,b))
						sb.append("1");
					else
						sb.append("0");
				}
			}
			
			sb = sb.reverse();
			bw.write("#" + tn + " " + sb.toString()+"\n");
			bw.flush();
		}
		
		br.close();
		bw.close();
	}
	
	
	private static void union(int a, int b) {
        int x = find(a);
        int y = find(b);

        if (x < y) {
        	par[y] = par[x];
        } else {
        	par[x] = par[y];
        }
 	}

	private static boolean isSameGroup(int a, int b) {
	    int x = find(a);
	    int y = find(b);
	    return x == y;
	}
	
	private static int find(int a) {
	    if (par[a] == a)
	        return a;
	    return par[a] = find(par[a]);
	}
}
