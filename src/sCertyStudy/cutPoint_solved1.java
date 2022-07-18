package sCertyStudy;
import java.io.*;
import java.util.StringTokenizer;

/*
(입력)
3
5 6
4 5 6 2 2 7
8 1 2 2 2 9
1 1 1 2 3 3
10 1 11 3 3 3
12 13 14 3 3 15
7 7
4 4 1 1 1 7 7
4 4 1 1 1 7 7
1 1 1 1 1 7 7
8 8 1 2 2 3 5
8 8 1 2 3 3 5
8 8 2 2 3 3 5
8 8 8 6 6 6 5
7 7
4 4 1 1 1 7 7
4 4 1 8 8 7 7
1 1 1 1 1 7 7
9 9 1 2 2 3 5
9 9 1 2 3 3 5
9 9 2 2 3 3 5
9 9 9 6 6 6 5

(출력)
#1 1 1 1
#2 10 1 0
#3 14 0 1
 */
public class cutPoint_solved1 {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

    private static final int[] rx = {0, 0, 1, -1};
    private static final int[] ry = {1, -1, 0, 0};
    private static final int[][] map = new int[500][500];
    private static final int[][] isCut = new int[500][500];
    private static final int[][] order = new int[500][500];
    private static int N, M, number, group;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    isCut[i][j] = 0;
                    order[i][j] = 0;
                }
            }

            number = 1;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (order[i][j] == 0) {
                        group = map[i][j];
                        dfs(i, j, true);
                    }
                }
            }

            int[] ans = new int[5];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    ans[isCut[i][j]]++;
                }
            }

            bw.write("#" + tc + " " + ans[2] + " " + ans[3] + " " + ans[4] + "\n");
        }
        
        bw.flush();
        br.close();
        bw.close();
    }


    // 단절점 탐색
    private static int dfs(int i, int j, boolean isRoot) {
        order[i][j] = number++;
        int low = order[i][j];
        int child = 0;
        int child2 = 0;

        for (int n = 0; n < 4; n++) {
            int ni = i + rx[n];
            int nj = j + ry[n];

            if(ni < 0 || nj < 0 || ni > N-1 || nj > M-1)
                continue;

            if(map[ni][nj] != group)
                continue;

            if (order[ni][nj] > 0) {
                low = Math.min(low, order[ni][nj]);
                continue;
            }

            child++;
            int min = dfs(ni, nj, false);

            if (!isRoot && order[i][j] <= min) {
                isCut[i][j] = 1;
            }
            else {
                child2++;
            }

            low = Math.min(low, min);
        }

        if (isRoot) {
            isCut[i][j] = child;
        }
        else if(isCut[i][j] == 1) {
            isCut[i][j] = child + 1 - child2;
        }

        return low;
    }

}
