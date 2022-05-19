package sCertyStudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

/*
(입력)
5
1
1
2
2 1
5
1 4 2 5 3
7
1 2 3 4 5 6 7
15
4 1 7 12 9 8 15 6 11 3 5 14 10 13 2

(출력)
#1 1
#2 2
#3 2
#4 1
#5 6

*/
public class LIS_solved1 {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

	static int[] arr;
	static int N;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		int ans;
		for (int tc = 1; tc <= T; tc++) {
			ans = 0;

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			arr = new int[N];

			st = new StringTokenizer(br.readLine());
			for (int i = N-1; i >= 0; i--) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			// LIS 의 길이가 정답이 된다.
			ans = findLIS1(arr, N); //Arrays.binarySearch 활용법
			//ans = findLIS2(arr); //TreeMap 활용법

			bw.write("#" + tc + " " + ans + "\n");
		}

		br.close();
		bw.flush();
		bw.close();
	}


	//Arrays.binarySearch 활용법
	static int findLIS1(int[] inputArr, int size) {
		int[] tailTable = new int[size];
		int lisLength = 0;

		tailTable[0] = inputArr[0];
		lisLength = 1;

		for (int i = 1; i < size; i++) {
			//후보값이 첫번째값보다 작다면 교체
			if (inputArr[i]<tailTable[0]) {
				tailTable[0] = inputArr[i];
			}
			//후보값이 마지막값보다 크다면 교체
			else if (inputArr[i]>tailTable[lisLength-1]) {
				tailTable[lisLength] = inputArr[i];
				lisLength++;//길이증가
			}
			else {
				//lower-bound 위치를 찾고 교체
				int idx = Arrays.binarySearch(tailTable, 0, lisLength, inputArr[i]);
				//System.out.println("      idx:"+idx);
				idx = (idx<0)?-idx-1:idx;//lower-bound 처리
				tailTable[idx] = inputArr[i];
			}
		}

		return lisLength;
	}

	//TreeMap 활용법
	static int findLIS2(int[] inputArr) {
		TreeMap<Integer, Integer> tm = new TreeMap<>();

		for(int i=0; i<N; i++) {
			if (!tm.isEmpty()&&tm.lastKey()>=inputArr[i]) {
				//lower-bound 처리
				tm.remove(tm.ceilingKey(inputArr[i]));
			}
			tm.put(inputArr[i], i);
		}

		return tm.size();
	}


}
