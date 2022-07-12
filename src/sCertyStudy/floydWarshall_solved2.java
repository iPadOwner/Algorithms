package sCertyStudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class floydWarshall_solved2 {
	
	static int T;
	static int N;
	static int M;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		
		for (int testNumber = 1; testNumber <= T; testNumber++) {
			
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			
			int[][] w = new int[N+1][N+1];
			
			for (int i = 0; i <= N; i++) {
				for (int j = 0; j <= N; j++) {
					w[i][j] = 0; 
				}			
			}
			
			for (int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x1 = Integer.parseInt(st.nextToken());
				int x2 = Integer.parseInt(st.nextToken());
				
				//x1이 x2보다 작음
				//x1과 x2는 비교를 해봤음
				w[x1][x2] = 1;
			}
			
			for(int k = 1; k <= N; k++){
		        for(int i = 1; i <= N; i++){
		            for(int j = 1; j <= N; j++){
		                if(w[i][k] + w[k][j] == 2)
		                	w[i][j] = 1;
		            }
		        }
		    }
			
			
			int ans = 0;
		    for(int i = 1; i <= N; i++){
		        int valid = 1;
		        for(int j = 1; j <= N; j++){
		            if(i == j)
		            	continue;
		            if(w[i][j] + w[j][i] == 0){
		                valid = 0;
		                break;
		            }
		        }
		        
		        if(valid>0)
		        	ans++;
		    }
	
		    bw.write("#" + testNumber + " " + ans);
			bw.newLine();
		}
		
		br.close();
		bw.close();
	}
	
}
