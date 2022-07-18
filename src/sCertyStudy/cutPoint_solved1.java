package sCertyStudy;
import java.io.*;
import java.util.StringTokenizer;

/*
[기출P-0087]그룹의 수(단절점)
특별한 추가 로직이 필요없는 단절점 알고리즘 문제
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

            // 단절점 찾기 DFS
            number = 1;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    // 방문하지 않은 정점이면 탐색
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
        order[i][j] = number++; // DFS 탐색 순서 저장
        int low = order[i][j]; // low 의 초기값은 자기 자신의 order
        int child = 0; // 자식 수 count
        int child2 = 0; // 빼야될 자식의 수 count

        for (int n = 0; n < 4; n++) {
            int ni = i + rx[n];
            int nj = j + ry[n];

            // 범위 초과
            if(ni < 0 || nj < 0 || ni > N-1 || nj > M-1)
                continue;

            // 같은 그룹이 아님
            if(map[ni][nj] != group)
                continue;

            // 만약 이미 DFS에서 탐색된 정점이라면
            // 현재 정점의 방문순서와 탐색된 정점의 방문 순서 중 min값(=low)을 찾는다.
            if (order[ni][nj] > 0) {
                low = Math.min(low, order[ni][nj]);
                // 이미 방문한 정점이면 Skip
                continue;
            }

            child++;
            int min = dfs(ni, nj, false);

            // 정점 A가 시작 정점(root)이 아닐 때
            // A번 정점에서 자식 노드들이 정점 A를 거치지 않고
            // 정점 A보다 빠른 방문번호를 가진 정점으로 갈 수 없다면 단절점이다.
            if (!isRoot && order[i][j] <= min) {
                isCut[i][j] = 1;
            }
            else {
                // 정점 A 보다 빠른 방문번호로 갔다면 그 쪽 자식은
                // 이미 지나온 길에 연결되었다는 뜻이다.
                // 즉 현재 정점 탐색 전에 지나온 길과 같은 그룹이다.
                child2++;
            }

            low = Math.min(low, min);
        }

        // 정점 A가 시작 정점(root)일 때
        // 자식 수가 2개 이상이면 단절점이다.
        if (isRoot) {
//            isCut[i][j] = (child >= 2);
            isCut[i][j] = child;
        }
        else if(isCut[i][j] == 1) {
            // 지나온 길도 child 에 포함되어야 하니 + 1 을 해준다.
            // 지나온 길과 연결된 child2 는 지나온 길과 같은 그룹이니 빼준다.
            isCut[i][j] = child + 1 - child2;
        }

        return low;
    }

}
