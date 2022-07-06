package sCertyStudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
입력
ACAYKP
CAPCAK

출력
4
*/
public class LCS_solved1 {
	static StringTokenizer st; 
	static BufferedReader br;
	static BufferedWriter bw;
	
	static int[][] dp;//LCS의 길이를 구할때
	static char[] A;
	static char[] B;
	
	static String[][] solution;//LCS자체를 구할때
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String aStr = br.readLine();
		String bStr = br.readLine();

		A = aStr.toCharArray();
		B = bStr.toCharArray();
		
		bw.write("" + LCS_length());
		bw.newLine();
		
		bw.write("" + LCS_String());
		bw.newLine();
		
		bw.flush();
		br.close();
		bw.close();
	}
	
	//LCS 길이 구하기
	public static int LCS_length() throws Exception {
		int aLen = A.length;
		int bLen = B.length;
		
		dp = new int[aLen+1][bLen+1];

		for (int i = 0; i<=bLen; i++) {
			dp[0][i] = 0;
		}

		for (int i = 0; i<=aLen; i++) {
			dp[i][0] = 0;
		}

		for (int i=1; i<=aLen; i++) {
			for (int j=1; j<=bLen; j++) {
				
				//if (Xi-1 == Yj-1), then LCS(Xi, Yj) : LCS(Xi-1, Yj-1) + 1  
				if (A[i-1] == B[j-1]) {
					dp[i][j] = dp[i-1][j-1] + 1;
				}
				//if (Xi-1 != Yj-1), then LCS(Xi, Yj) : max( LCS(Xi-1, Yj), LCS(Xi, Yj-1) )
				else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		
		return dp[aLen][bLen];
	}
	
	//LCS 문자열 구하기
	public static String LCS_String() throws Exception {
		int aLen = A.length;
		int bLen = B.length;
		
		String[][] solution = new String[aLen+1][bLen+1];

		for (int i = 0; i<=bLen; i++) {
			solution[0][i] = "0";
		}

		for (int i = 0; i<=aLen; i++) {
			solution[i][0] = "0";
		}

		//LCS 테이블 세팅
		for (int i=1; i<=aLen; i++) {
			for (int j=1; j<=bLen; j++) {
				
				//if (Xi-1 == Yj-1), then LCS(Xi, Yj) : LCS(Xi-1, Yj-1) + 1  
				if (A[i-1] == B[j-1]) {
					solution[i][j] = "diagonal";
				}
				//if (Xi-1 != Yj-1), then LCS(Xi, Yj) : max( LCS(Xi-1, Yj), LCS(Xi, Yj-1) )
				else {
					if (dp[i][j] == dp[i-1][j]) {
						solution[i][j] = "top";
					}
					else {
						solution[i][j] = "left";
					}
				}
			}
		}
		
		//문자열 조합 시작
		String x = solution[aLen][bLen];
		String answer = "";
		
		int a = aLen;
		int b = bLen;
		
		while (!"0".equals(x)) {
			
			if ("diagonal".equals(solution[a][b])) {
				answer = A[a-1] + answer;
				a--;
				b--;
			}
			else if ("left".equals(solution[a][b])) {
				b--;
			}
			else if ("top".equals(solution[a][b])) {
				a--;
			}
			
			x = solution[a][b];
		}
		
		return answer;
	}
}
