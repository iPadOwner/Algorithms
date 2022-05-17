package sCertyStudy;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
(입력)
3
12 3
0 3
3 4
5 5
3 6
2 8
4 8
5 0
2 1
4 1
6 2
5 3
7 7
4 10 12
5 5
4 5
16 14
7 17
6 15
11 13
1 2 5 3 4
10 1
19 8
9 10
6 3
4 17
4 16
10 4
13 0
20 20
4 3
14 9
6

(출력)
#1 38
#2 0
#3 342
*/
class kruskal_solved1{
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	
	static int N;
	static int M;
	static int MAX_N = 100001;
    static Point[] points;
    static int[] parent = new int[MAX_N];
    static ArrayList<Road> roads;
	
    static class Point {
        int x;
        int y;
        
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Road implements Comparable<Road> {
        int start;
        int end;
        long cost;
        
        public Road(int start, int end, long cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Road o) {
            return Long.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws Exception {
    	br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            points = new Point[N + 1];
            roads = new ArrayList<>();

            for (int i = 1; i <= N + M; i++) {
                parent[i] = i;
            }

            // 도시 입력
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                points[i] = new Point(x, y);
            }

            st = new StringTokenizer(br.readLine());
            int a = 0;
            for (int i = 0; i < M; i++) {
                int b = Integer.parseInt(st.nextToken());
                
                union(a, b);
            }

            for (int i = 1; i < N; i++) {
                for (int j = i+1; j <= N; j++) {
                    long cost = (long) (Math.pow(points[i].x - points[j].x, 2)
                    		          + Math.pow(points[i].y - points[j].y, 2));
                    roads.add(new Road(i, j, cost));
                }
            }

            bw.write("#" + tc + " " + kruskal() + "\n");
        }
        
        bw.flush();
        br.close();
        bw.close();
    }
    

    static long kruskal() {
        long mst_cost = 0; // MST 일때 총 비용

        //크루스컬 : 정렬필수
        Collections.sort(roads);

        for(Road road : roads) {
            // 서로 다른 트리일 때만 연결
            if(!find(road.start, road.end)) {
                mst_cost += road.cost;
                union(road.start, road.end);
            }
        }

        return mst_cost;
    }

    private static void union(int a, int b) {
        int x = getParent(a);
        int y = getParent(b);

        //작은것을 부모로
        if (x < y) {
            parent[y] = parent[x];
        } else {
            parent[x] = parent[y];
        }
    }

    private static boolean find(int a, int b) {
        int x = getParent(a);
        int y = getParent(b);
        return x == y;
    }

    private static int getParent(int a) {
        if (parent[a] == a)
            return a;
        return parent[a] = getParent(parent[a]);
    }

}
