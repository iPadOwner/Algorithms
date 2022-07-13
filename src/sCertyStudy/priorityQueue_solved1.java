package sCertyStudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
(입력)
5
1
11
100
1000
5842

(출력)
#1 1
#2 12
#3 450
#4 385875
#5 2000000000
*/
public class priorityQueue_solved1 {
		
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	
	static int MAX_N = 5843;//(1 ≤ N ≤ 5842)
	static long[] cache;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		makeNumbers();
		
		st = new StringTokenizer(br.readLine());
		int TC = Integer.parseInt(st.nextToken());
		
		int N;
		for (int TN=1; TN <=TC; TN++) {
			N = Integer.parseInt(br.readLine());
			bw.write("#" + TN + " " + cache[N] + "\n");
		}
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	
	static void makeNumbers() {
		cache = new long[MAX_N];
		
		PriorityQueue<Long> pq = new PriorityQueue<Long>();
		pq.offer(1l);
		
		for (int i=1; i<=MAX_N-1; i++) {
			cache[i] = pq.poll();
			
			while (!pq.isEmpty()&&pq.peek() == cache[i])
				pq.poll();
			
			pq.offer(cache[i]*2);
			pq.offer(cache[i]*3);
			pq.offer(cache[i]*5);
			pq.offer(cache[i]*7);
		}
	}
	
}