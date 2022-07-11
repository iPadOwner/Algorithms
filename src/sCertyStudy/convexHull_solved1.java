package sCertyStudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
입력
8
1 1
1 2
1 3
2 1
2 2
2 3
3 1
3 2

출력
5
*/

public class convexHull_solved1 {

	static class Point {
		public Point(int x, int y) {
			this.x = x;

			this.y = y;
		}

		int x, y;
	}

	static int ccw(int ax, int ay, int bx, int by, int cx, int cy) {
		long v = (long) (bx - ax) * (cy - ay) - (long) (by - ay) * (cx - ax);

		if (v > 0) {
			return 1;
		}

		if (v < 0) {
			return -1;
		}

		return 0;
	}

	static int ccw(Point a, Point b, Point c) {
		return ccw(a.x, a.y, b.x, b.y, c.x, c.y);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());

		Point[] A = new Point[N + 1];

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int x = Integer.parseInt(st.nextToken());

			int y = Integer.parseInt(st.nextToken());

			A[i] = new Point(x, y);
		}

		for (int i = 1; i <= N; i++) {
			if (A[1].y > A[i].y || A[1].y == A[i].y && A[1].x > A[i].x) {
				Point tmp = A[1];

				A[1] = A[i];

				A[i] = tmp;
			}
		}

		for (int i = N; i > 0; i--) {
			A[i].x -= A[1].x;

			A[i].y -= A[1].y;
		}

		Arrays.sort(A, 1, N + 1, new Comparator<Point>() {
			public int compare(Point a, Point b) {
				int v = ccw(new Point(0, 0), a, b);

				if (v > 0) {
					return -1;
				}

				if (v < 0) {
					return 1;
				}

				return (Math.abs(a.x) + a.y) - (Math.abs(b.x) + b.y);
			}
		});

		ArrayList<Integer> stk = new ArrayList<>();

		stk.add(1);

		for (int i = 2; i <= N; i++) {
			while (stk.size() > 1 && ccw(A[stk.get(stk.size() - 2)], A[stk.get(stk.size() - 1)], A[i]) <= 0) {
				stk.remove(stk.size() - 1);
			}

			stk.add(i);
		}

		bw.write(stk.size() + "");
		bw.flush();
		bw.close();
		br.close();
	}
}
