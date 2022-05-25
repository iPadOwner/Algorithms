package programmers;

import java.util.Arrays;

public class kakao_1lv_17681 {
	static int N;
	static char[][] mat;

	public static void main(String[] args) {
		int[] arr1 = { 9, 20, 28, 18, 11 };
		int[] arr2 = { 30, 1, 21, 17, 28 };
		N = 5;
		mat = new char[N][N];

		for (int i = 0; i < N; i++) {
			int x = arr1[i];
			doSomething(x, i, N - 1);
		}
		for (int i = 0; i < N; i++) {
			int x = arr2[i];
			doSomething(x, i, N - 1);
		}

		String[] answer = new String[N];
		Arrays.fill(answer, "");
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				answer[i] += String.valueOf(mat[i][j]);

		System.out.println(Arrays.toString(answer));
		
		solution2(N, arr1, arr2);
	}

	static void doSomething(int n, int row, int col) {
		if (col < 0)
			return;

		int val = n / 2;
		int remain = n % 2;

		mat[row][col] = (mat[row][col] == '#' ? '#' : (remain == 1 ? '#' : ' '));
		doSomething(val, row, col - 1);
	}

	// 제출용
	static class Solution {
		static char[][] mat;

		public String[] solution(int n, int[] arr1, int[] arr2) {
			mat = new char[n][n];

			for (int i = 0; i < n; i++) {
				int x = arr1[i];
				doSomething(x, i, n - 1);
			}
			for (int i = 0; i < n; i++) {
				int x = arr2[i];
				doSomething(x, i, n - 1);
			}

			String[] answer = new String[n];
			Arrays.fill(answer, "");
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					answer[i] += String.valueOf(mat[i][j]);

			return answer;
		}

		static void doSomething(int n, int row, int col) {
			if (col < 0)
				return;

			int val = n / 2;
			int remain = n % 2;

			mat[row][col] = (mat[row][col] == '#' ? '#' : (remain == 1 ? '#' : ' '));
			doSomething(val, row, col - 1);
		}
	}

	// 다른사람 풀이
	public static String[] solution2(int n, int[] arr1, int[] arr2) {
		String[] result = new String[n];
		for (int i = 0; i < n; i++) {
			result[i] = Integer.toBinaryString(arr1[i] | arr2[i]);
		}

		for (int i = 0; i < n; i++) {
			result[i] = String.format("%" + n + "s", result[i]);
			result[i] = result[i].replaceAll("1", "#");
			result[i] = result[i].replaceAll("0", " ");
		}

		return result;
	}
}
