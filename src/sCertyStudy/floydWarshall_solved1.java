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
5 5 9 1
6 6 5 6 6 
5 6 6 6 7 
6 6 5 1 6 
5 6 6 4 2 
7 2 5 5 7 
14 -6 -12 -15 -13 
15 3 -18 -10 -14 
-7 -2 -9 -9 3 
7 -11 -4 2 -5 
-19 -11 3 17 -5 
6 6 23 4
8 6 4 5 5 9 
6 4 5 9 6 5 
9 5 5 2 4 5 
9 8 5 5 6 9 
5 9 6 8 5 5 
9 8 6 6 6 9 
-16 10 14 -22 20 -17 
-12 2 -17 24 30 -15 
-17 -2 -17 -27 10 27 
13 -4 -1 -10 16 -28 
10 -21 0 11 -22 -9 
21 -25 21 4 -1 -17

(출력)
#1 35
#2 58

*/
public class floydWarshall_solved1 {
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	
	static int N;
	static int M;
	static long D;
	static int K;
	
	//2차원배열을 1차원으로 표현할수있다면 잡계산이 줄어들어서 시간이 많이절약된다
	//이 문제의 경우 2차원배열 활용시 시간초과된다
	//또한 미리 선언해놓고 Arrays.fill로 초기화하는게 두세배이상 훨씬빠르다
	static int MAXCNT = 20*20;
	static long[] map = new long [MAXCNT+1];//2차원 맵을 1차원으로 표시
	static long[] darkMatter = new long [MAXCNT+1];//2차원 맵을 1차원으로 표시
	static long[][] dist = new long [MAXCNT+1][MAXCNT+1];
	static long INF = 400*400*1000000 + 1l;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int tc = Integer.parseInt(br.readLine());
		
		for (int tn = 1; tn <= tc; tn++) {
			
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			D = Long.parseLong(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			int count = N*M;
			Arrays.fill(map, 0l);
			Arrays.fill(darkMatter, 0l);
			for (int i = 0; i <= MAXCNT; i++) {
				Arrays.fill(dist[i], INF);
			}
			
			//map(차원)세팅			
			for (int i = 1; i<=N; i++) {
				st = new StringTokenizer(br.readLine());
				
				for (int j = 1; j <=M; j++) {
					map[(i-1)*M+j] = Integer.parseInt(st.nextToken());
				}
			}
			
			//암흑물질 세팅
			for (int i = 1; i <=N; i++) {
				st = new StringTokenizer(br.readLine());
				
				for (int j = 1; j <=M; j++) {
					darkMatter[(i-1)*M+j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			//문제조건에 의한 노드간 거리 계산&반영
			for (int i = 1; i <=count; i++) {
				for (int j = 1; j <=count; j++) {
						if (i==j)
							dist[i][j] = 0;
						else
							dist[i][j] = getDist(i, j);
				}
			}	
						
			//거리계산(floyd-warshall)
			//static 으로 안빼면 시간초과
			floydWarshall(count);
						
			long answer = -1;
			long baseD = darkMatter[1];
			for (int i = 1; i <=count; i++) {
				long d1 = darkMatter[i];
				
				if (d1 <= baseD)
					continue;
				
				for (int j = 1; j <=count; j++) {
					if (i==j)
						continue;
				
					if((dist[1][i] + dist[i][j] + dist[j][1])>D)
						 continue;
					
					long d2 = darkMatter[j];
					
					if (d2 >= baseD)
						continue;
					
					if (d1<=d2)
						continue;
					
					//문제조건에 의한 답
					answer = Math.max(answer, Math.abs(d1 - d2));
				}
			}
			
			bw.write("#" + tn + " " + answer + "\n");
			bw.flush();
		}
		
		br.close();		
		bw.close();
	}
	
	static long getDist(int start, int end) throws Exception {
		long rtn = 0;

		long startDegree = map[start];
		long endDegree = map[end];
		
		//위치계산
		int n1 = (start/M);
		int m1 = (start%M);
		if(m1!=0)
			n1+=1;
		if (m1==0)
			m1 = M;
		int n2 = (end/M);
		int m2 = (end%M);
		if(m2!=0)
			n2+=1;
		if (m2==0)
			m2 = M;
		
		if (startDegree < endDegree) {
			rtn = (endDegree-startDegree)*(Math.abs(n1-n2)+Math.abs(m1-m2))*2;
		}
		else if (startDegree > endDegree) {
			rtn = (startDegree-endDegree)*(Math.abs(n1-n2)+Math.abs(m1-m2));
		}
		else {
			rtn = K;
		}
		
		return rtn;
	}

	static void floydWarshall(int count) {;
		for (int k = 1; k <=count; k++) {
			for (int i = 1; i <=count; i++) {
				for (int j = 1; j <=count; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
	}
}
