package sCertyStudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class prim_solved1 {

	static int V;
	static int E;

	static int[][] w;
	static boolean[] visited;
	static int[] d;

	static int totalSum;
	static int countOfMST;

	static final int MAX = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		visited = new boolean[V + 1];
		w = new int[V + 1][V + 1];
		d = new int[V + 1];

		for (int i = 0; i <= V; i++) {
			d[i] = MAX;
			visited[i] = false;

			for (int j = 0; j <= V; j++) 
				w[i][j] = MAX;
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());

			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());

			if (w[A][B] > C) {
				w[A][B] = C;
				w[B][A] = C;
			}
		}

		countOfMST = 0;
		d[1] = 0;

		while (countOfMST < V) {

			int min = MAX;
			int u = -1;

			for (int i = 1; i <= V; i++) {
				if (!visited[i] && d[i] < min) {
					min = d[i];
					u = i;					
				}
			}
			
			for (int i = 1; i <= V; i++) {
				if (!visited[i] && w[u][i] != MAX)					
					if (w[u][i] < d[i])
						d[i] = w[u][i];
			}

			countOfMST++;
			totalSum += min;
			visited[u] = true;

		}

		bw.write(String.valueOf(totalSum));

		br.close();
		bw.flush();
		bw.close();
	}

}
