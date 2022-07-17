package sCertyStudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
(입력)
2
10 3 5
4 5 14 1 7 5 7 5 10 3
1 4
2 9
3 11
1 11
2 45
7 2 4
8 4 9 1 9 1 10
1 5
2 13
1 22
1 29

(출력)
#1 6
#2 4
*/
public class adhoc_solved1 {
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	
	static class Item{
		int start;
		int end;
		int len;
		
		public Item(int start, int end, int len) {
			this.start = start;
			this.end =end;
			this.len = len;
		}
	}
	
	static class Schedule implements Comparable<Schedule>{
		int K;
		int start;
		int end;
		int cost;
		List<Item> list;
		
		public Schedule(int K, int cost) {
			this.K = K;
			this.cost = cost;
		}
		
		
		@Override
		public int compareTo(Schedule other) {
			if (this.cost < other.cost)
				return -1;
			else if (this.cost == other.cost) {
				if (this.K < other.K)
					return -1;
				else if (this.K == other.K)
					return 0;
				else
					return 1;
			}
			else
				return 1;
		}
	}

	static PriorityQueue<Schedule> pq = new PriorityQueue<Schedule>();
	
	static int N;
	static int Q;
	static int K;
	static int maxCost;
	static int target;
	static Schedule server;
	static int size;
	static Schedule[] servers = new Schedule[100001];

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int tc = Integer.parseInt(br.readLine());
		
		for (int tn = 1; tn <= tc; tn++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());
			maxCost = 0;
			Arrays.fill(servers, null);
			servers[0] = new Schedule(0, 0);
			pq.clear();
			
			for (int i = 1; i <=K; i++) {
				Schedule schedule = new Schedule(i, 0);
				schedule.list = new ArrayList<Item>();
				servers[i] = schedule;
				pq.add(schedule);
				
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <=N; i++) {
				int time = Integer.parseInt(st.nextToken());
				
				Schedule cur = pq.poll();
				
				cur.start = cur.cost;
				cur.end = cur.start + time - 1;
				cur.cost += time;
				maxCost = Math.max(maxCost, cur.cost);
				cur.list.add(new Item(cur.start, cur.end, time));
				
				pq.add(cur);
			}
			
			int[][] pos = new int[K+1][maxCost+1];
			for (int serverNumber = 1; serverNumber <=K; serverNumber++) {
				Schedule server = servers[serverNumber];
				List<Item> jobList = server.list;
				int jobSize = jobList.size();
				
				for (int jobIndex = 0; jobIndex < jobSize; jobIndex++) {
					int startTime = jobList.get(jobIndex).start;
					int endTime = jobList.get(jobIndex).end;
					
					for (int time = startTime; time <= endTime; time++) {
						pos[serverNumber][time] = jobIndex;
					}
				}
			}
			
			int answer = 0;
			
			for (int i = 1; i <=Q; i++) {
				st = new StringTokenizer(br.readLine());
				K = Integer.parseInt(st.nextToken());
				target = Integer.parseInt(st.nextToken());
				
				server = servers[K];
				size = server.list.size();
				
				if (target >= server.cost)
					continue;
				
				int loc = pos[K][target];
				Item item = server.list.get(loc);
				int diff = (item.start+item.len) - target;
				answer += diff;
			}
			
			bw.write("#" + tn + " " + answer + "\n");
		}
		
		br.close();
		bw.flush();
		bw.close();
	}
	
}
