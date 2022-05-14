package sCertyStudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
(입력)
5
10 15 3
1 4 6
6 0 9
7 4 3
4 6 3
0 8 8
5 8 1
5 0 5
0 7 5
7 8 3
2 5 7
4 3 6
9 3 1
9 5 4
2 3 4
3 5 2
1 4 6
6 7 3
8 2 5
10 15 3
1 4 6
6 0 9
7 4 3
4 6 3
0 8 8
5 8 1
5 0 5
0 7 5
7 8 3
2 5 7
4 3 6
7 5 9
2 3 4
3 5 2
6 7 3
1 4 6
8 2 5
9 3 1
10 15 3
1 4 6
6 0 9
7 4 3
4 6 3
0 8 8
5 8 1
5 0 5
0 7 5
7 8 3
2 5 7
4 3 6
9 3 1
9 5 4
2 3 4
3 5 2
1 4 6
6 7 7
8 2 5
4 4 1
0 1 3
0 2 7
2 3 9
3 2 2
1 2 10
4 2 1
1 2 9
0 3 300
1 2 10

(출력)
#1 6 1
#2 NO
#3 BUG
#4 -5 1
#5 BUG
*/
public class bellmanFord_solved1 {
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	
	static int V;
	static int E;
	static int A;
	
	static ArrayList<Edge>[] adj;
	static long[] D;//D[N]: 시작점(0)에서 N번까지의 최소비용  
	static long[] cnt;//cnt[N]: 시작점(0)에서 N번까지의 천사의길 최소사용횟수
	static long INF = 3100000001l;//절대 불가능한 값, 초기화 용
    static long CMP = 1000*1000000+1;//문제조건상 도달 불가능한 값(벨만포드는 음수간선이 존재하므로 도달가능여부는 INF로 비교하면 안됨)
    							     //최대노드수*간선의최대값+1
	static class Edge{
		int end;
		long cost;
		boolean isAngel;
		
		public Edge(int end, long cost, boolean isAngel) {
			this.end = end;
			this.cost = cost;
			this.isAngel = isAngel;			
		}
	}
		
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int tc = Integer.parseInt(br.readLine());
		
		for (int tn = 1; tn<=tc; tn++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			
			adj = new ArrayList[V];
			cnt = new long[V];
			D = new long[V];
			
			for (int i = 0; i<V; i++) {
				adj[i] = new ArrayList<Edge>();
				cnt[i] = 0;
				D[i] = INF;
			}
			
			//일반 길
			for (int i = 0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				long z = Long.parseLong(st.nextToken());

				adj[x].add(new Edge(y, z, false));
				adj[y].add(new Edge(x, z, false));
			}
			
			//천사의길
			for (int i = 0; i<A; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				long z = Long.parseLong(st.nextToken());

				//천사의길은 문제조건상 양방향이 아님
				adj[x].add(new Edge(y, -z, true));
			}
						
			D[0] = 0;//문제조건상 출발지는 0, 도착지는 V-1번
			cnt[0] = 0;//천사의길 사용횟수			
			boolean isNegativeCycle = false;
			
			for(int x=0; x<V; x++) {
				boolean isRouteExists = false;//매 싸이클마다 체크해야 타임아웃이 안난다
				
				for (int i = 0; i<V; i++) {
					for (Edge e : adj[i]) {
						int from = i;
						int to = e.end;
						long cost = e.cost;
						
						//to 노드의 천사의길 사용횟수
						long nextAngelCount = (e.isAngel?cnt[from]+1:cnt[from]);
                        
						//갱신대상 조건
						//(1) cost 가 더 낮음
						//(2) 동일 cost인데 천사의길 사용수가 더 적음 <-중요, 문제조건에 의해 추가됨
						if (D[to]>D[from]+cost
						   || ((D[to]==D[from]+cost)&&cnt[to]>nextAngelCount)) {
							D[to] = D[from]+cost;
							cnt[to] = nextAngelCount;//천사길 사용횟수
							
							isRouteExists = true;//더 돌지 말지 여부
							
							//음수사이클 체크
							if(x==V-1) {
								isNegativeCycle = true;
								break;
							}
						}
					}
				}
				
				//도착점까지 갈수있는 길이 없거나 음수사이클이 있다면 굳이 더 돌지않고 break
				if (!isRouteExists||isNegativeCycle) {
					break;
				}
			}
			
			//음수싸이클
			if (isNegativeCycle) {
				bw.write("#" + tn + " BUG\n");
			}
			//도착지 도달불가
			else if (D[V-1]>=CMP) {
				bw.write("#" + tn + " NO\n");
			}
			else {
				//정상케이스
				bw.write("#" + tn + " " + D[V-1] + " " + cnt[V-1] + "\n");
			}
		}

		bw.flush();
		br.close();		
		bw.close();
	}	
}
