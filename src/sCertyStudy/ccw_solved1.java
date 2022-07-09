package sCertyStudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class ccw_solved1 {
	static int x1,y1,x2,y2,x3,y3;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		x1 = Integer.parseInt(st.nextToken());
		y1 = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		x2 = Integer.parseInt(st.nextToken());
		y2 = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		x3 = Integer.parseInt(st.nextToken());
		y3 = Integer.parseInt(st.nextToken());
		
		int ccw = CCW(x1, y1, x2, y2, x3, y3);
		
		if (ccw < 0)
			ccw = -1;
		else if (ccw > 0)
			ccw = 1;
		else
			ccw = 0;
		
		bw.write("" + ccw);
		bw.newLine();
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	static int CCW(int ax, int ay, //P1 
			       int bx, int by, //P2
			       int cx, int cy){//P3
		return (ax*by + bx*cy + cx*ay) - (ay*bx + by*cx + cy*ax);
	}
}
