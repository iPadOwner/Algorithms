package sCertyStudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
(입력)
2
5
1 1 5
2 2 4
3 3 3
4 4 2
5 5 1
9
3 1 7
4 4 6
1 3 9
7 2 3
8 5 2
2 6 8
9 7 1
5 8 5
6 9 4

(출력)
#1 20
#2 77
*/
public class indexTree_solved2 {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	
	static int N;
	
	static final int MAX_Y = 1000000;
	
	static int treeSize;
	static int startPoint;
	static long[] tree;

	static Tank[] tanks;
	static class Tank implements Comparable<Tank>{
		int idx;
		int x;
		int y;
		int score;
		
		public Tank(int idx, int x, int y, int score) {
			this.idx = idx;
			this.x = x;
			this.y = y;
			this.score = score;
		}

		@Override
		public int compareTo(Tank other) {
			if (this.x > other.x) {
				return -1;
			}
			else if (this.x==other.x) {
				if (this.y>other.x) {
					return -1;
				}
				else if (this.y==other.x) {
					return 0;
				}
				else
					return 1;
			}
			else {
				return 1;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tc = Integer.parseInt(br.readLine());
		
		for(int tn=1; tn<=tc; tn++) {
		
			N = Integer.parseInt(br.readLine());
			
			tanks = new Tank[N];
			init();
			
			for (int i = 0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int score = Integer.parseInt(st.nextToken());
				
				tanks[i] = new Tank(i, x, y, score);
			}
			
			Arrays.sort(tanks);
			
			long answer = 0l;
			for (int i = 1; i <=N; i++) {
				Tank t = tanks[i-1];
				
				answer += getSum(t.y, MAX_Y);
				
				update(t.y, t.score);
			}
	
			bw.write("#" + tn + " " + answer + "\n");
		}
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	static void init() {
		treeSize = 1;
		for(;treeSize<MAX_Y;)
			treeSize *=2;
		treeSize *=2;
		
		startPoint = treeSize/2-1;
		
		tree = new long[treeSize];
	}

	static void update(int n, int v) {
		n = n + startPoint;
		tree[n] = (long)v;
		
		for (int i = (n/2); i>0; i/=2) {
			tree[i] = tree[2*i] + tree[2*i+1];			
		}
	}
	
	static long getSum(int l, int r) {
		l = l + startPoint;
		r = r + startPoint;
		
		long rtn = 0l;
		while(l<=r) {
			if ((l&1)==1) {
				rtn += tree[l];
				l++;
			}
			
			if ((r&1)==0) {
				rtn += tree[r];
				r--;
			}
			
			l/=2;
			r/=2;
		}
		
		return rtn;
	}
}

