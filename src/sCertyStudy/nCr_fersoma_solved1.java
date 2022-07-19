package sCertyStudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class nCr_fersoma_solved1 {
	
	static int N;
	static int K;
	static final int p = 1000000007;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		
		long A = 1;
		long B = 1;
		
		for (int i = 2; i <= N; i++){
			A *= i;
			A %= p;
		}
		
		for (int i = 2; i <= K; i++){
			B *= i;
			B %= p;
		}
		
		for (int i = 2; i <= N - K; i++){
			B *= i;
			B %= p;
		}
		
		B = fastPowering(B, p-2);
		
		bw.write(""+((A*B) % p));
		bw.newLine();
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	static long fastPowering(long x, long y){
		long result =1;
		
		while (y > 0){
		
			if (y % 2 == 1){
				result *= x;
				result %= p;
			}
			
			x *= x;
			x %= p;
			y /= 2;
		}
		
		return result;
	}
	


}
