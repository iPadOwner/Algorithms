package sCertyStudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
(입력)
3
10 3
175 182 178 179 170 179 171 185 185 181
3 7 175
1 10 180
1 10 179
7 5
183 176 175 183 174 182 186
1 4 176
2 6 177
1 7 180
1 7 160
5 7 180
2 2
161 168
1 2 175
1 2 188

(출력)
#1 3 4 4
#2 2 2 4 7 2
#3 0 0

*/
public class indexTree_solved1 {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

	static int N;
	static int Q;

	static int[] tree;
	static int treeSize;
	static int startPoint;

	static int[] answer;
	static Item[] persons;
	static Item[] questions;


	static class Item implements Comparable<Item>{
		int idx;
		int height;
		boolean isQuestion;
		int l;
		int r;

		public Item(int idx, int height, boolean isQuestion, int l, int r) {
			this.idx = idx;
			this.height = height;
			this.isQuestion = isQuestion;
			this.l = l;
			this.r = r;
		}

		//키의 역순으로 정렬한다
		@Override
		public int compareTo(Item other) {
			return Integer.compare(other.height, this.height);
		}
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tc = Integer.parseInt(br.readLine());

		for (int tn = 1; tn <=tc; tn++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());

			persons = new Item[N+1];
			answer = new int[Q+1];
			questions = new Item[Q+1];

			persons[0] = new Item(0, 0, false, 0, 0);
			questions[0] = new Item(0, 0, false, 0, 0);
			
			initTree();

			//사람정보
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <=N; i++) {
				int h = Integer.parseInt(st.nextToken());
				Item item = new Item(i, h, false, 0, 0);
				persons[i] = item;
			}
			//사람정보 키 역순 정렬
			Arrays.sort(persons, 1, N+1);

			//질문정보
			for (int i = 1; i <=Q; i++) {
				st = new StringTokenizer(br.readLine());
				int l = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());

				//l~r 구간에 x보다 큰 사람이 몇명인가? 라는 질문
				Item item = new Item(i, x, true, l, r);
				questions[i] = item;
			}
			//질문정보 키 역순정렬
			Arrays.sort(questions, 1, Q+1);

			//사람과 질문을 키 기준 desc로 정렬해서
			//질문에 대한 답을 저장한다
			int personIdx = 1;
			for (int i = 1; i<=Q; i++) {
				Item question = questions[i];
				int from = question.l;
				int to = question.r;
				int questionHeight = question.height;

				//해당 질문에 부합하는 사람이 몇명인가 질의
				for (int j=personIdx; j<=N; j++) {
					//질문의 키보다, 사람의 키가 큰지?
					if (persons[j].height > questionHeight) {
						//해당 person은 questionHeight보다 키가 큼(1)
						//트리 업데이트
						update(persons[j].idx, 1);

						//모든 사람에 대해서 질문 완료(질문과 사람 둘다 키에대해서 역순정렬해놨으므로)
						if (j==N) {
							personIdx = Integer.MAX_VALUE;
							break;
						}
					}
					//다음 사람 인덱스를 저장해둔다
					else {
						personIdx = j;
						break;
					}
				}

				//질문번호-질문의 구간에 해당하는 답을 저장
				answer[question.idx] = getHeightCount(from, to);
			}


			StringBuilder sb = new StringBuilder();
			for(int i=1; i<=Q; i++) {
				sb.append(" ").append(answer[i]);
			}

			bw.write("#" + tn + sb.toString() + "\n");
		}

		br.close();
		bw.flush();
		bw.close();
	}
	
	static void initTree() {
		treeSize = 1;
		for (;treeSize<N;)
			treeSize*=2;
		treeSize*=2;
		startPoint = treeSize/2-1;
		tree = new int[treeSize];
	}

	static void update(int n, int v) {
		n = n + startPoint;
		tree[n] = v;

		for (int i = (n)/2; i>0; i/=2) {
			tree[i] = tree[2*i] + tree[2*i+1];
		}
	}

	static int getHeightCount(int l, int r) {
		int rtn = 0;

		l = l + startPoint;
		r = r + startPoint;

		while(l<=r) {
			if (l%2==1) {
				rtn += tree[l];
				l++;
			}

			if (r%2==0) {
				rtn += tree[r];
				r--;
			}
			l/=2;
			r/=2;
		}

		return rtn;
	}


}

