package programmers;

//https://programmers.co.kr/learn/courses/30/lessons/17679
public class kakao_2lv_17679 {

	public static void main(String[] args) {
		int m = 4;// 행
		int n = 5;// 열
		String[] board = { "CCBDE", "AAADE", "AAABF", "CCBBF" };

		char[][] mat = new char[m + 1][n + 1];
		boolean[][] delCheckMat = new boolean[m + 1][n + 1];

		for (int i = 0; i < m; i++) {
			char[] temp = board[i].toCharArray();

			for (int j = 0; j < n; j++) {
				mat[i][j] = temp[j];
			}
		}

		int count = 0;
		int beforeCount = -1;

		while (true) {
			if (count == beforeCount)
				break;

			// 수행결과 count증가 없으면 탈출
			beforeCount = count;

			// 없어질 것들 마킹
			for (int i = 0; i < m; i++)
				for (int j = 0; j < n; j++)
					if (mat[i][j] != ' ' && mat[i][j] == mat[i][j + 1])
						if (mat[i][j] == mat[i + 1][j] && mat[i][j] == mat[i + 1][j + 1]) {
							delCheckMat[i][j] = true;
							delCheckMat[i][j + 1] = true;
							delCheckMat[i + 1][j] = true;
							delCheckMat[i + 1][j + 1] = true;
						}

			// 마킹한거 없앰
			for (int i = 0; i < m; i++)
				for (int j = 0; j < n; j++)
					if (delCheckMat[i][j]) {
						mat[i][j] = ' ';
						delCheckMat[i][j] = false;
						count++;
					}

			// 밑에서부터 위로 올라가면서 아래로 땡긴다
			for (int k = 0; k < m; k++)
				for (int i = m; i > 0; i--)
					for (int j = 0; j < n; j++)
						if (mat[i][j] == ' ') {
							mat[i][j] = mat[i - 1][j];
							mat[i - 1][j] = ' ';
						}

		}

		int answer = count;

		System.out.println(answer);
	}

	// 제출용
	class Solution {
		public int solution(int m, int n, String[] board) {
			char[][] mat = new char[m + 1][n + 1];
			boolean[][] delCheckMat = new boolean[m + 1][n + 1];

			for (int i = 0; i < m; i++) {
				char[] temp = board[i].toCharArray();

				for (int j = 0; j < n; j++) {
					mat[i][j] = temp[j];
				}
			}

			int count = 0;
			int beforeCount = -1;

			while (true) {
				if (count == beforeCount)
					break;

				// 수행결과 count증가 없으면 탈출
				beforeCount = count;

				// 없어질 것들 마킹
				for (int i = 0; i < m; i++)
					for (int j = 0; j < n; j++)
						if (mat[i][j] != ' ' && mat[i][j] == mat[i][j + 1])
							if (mat[i][j] == mat[i + 1][j] && mat[i][j] == mat[i + 1][j + 1]) {
								delCheckMat[i][j] = true;
								delCheckMat[i][j + 1] = true;
								delCheckMat[i + 1][j] = true;
								delCheckMat[i + 1][j + 1] = true;
							}

				// 마킹한거 없앰
				for (int i = 0; i < m; i++)
					for (int j = 0; j < n; j++)
						if (delCheckMat[i][j]) {
							mat[i][j] = ' ';
							delCheckMat[i][j] = false;
							count++;
						}

				// 밑에서부터 위로 올라가면서 아래로 땡긴다
				for (int k = 0; k < m; k++)
					for (int i = m; i > 0; i--)
						for (int j = 0; j < n; j++)
							if (mat[i][j] == ' ') {
								mat[i][j] = mat[i - 1][j];
								mat[i - 1][j] = ' ';
							}

			}

			int answer = count;

			return answer;
		}
	}

}
