package sCertyStudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
입력
10
3
910 442 760
987 465 940
758 184 658
3
875 177 311
65 433 81
100 778 523
4
518 668 879 2
919 285 264 483
970 862 745 43
187 848 45 321
4
880 273 489 367
40 516 640 541
896 668 77 692
928 691 639 735
5
317 462 621 604 114
906 504 735 316 906
976 217 901 309 12
130 748 500 704 334
552 644 172 807 279
6
884 415 390 491 505 160
706 808 291 845 869 158
103 632 517 984 7 14
682 449 94 872 932 861
536 61 276 896 300 520
350 963 386 600 456 0
7
385 842 149 288 709 700 199
372 794 754 905 67 195 838
947 592 540 95 195 273 942
415 134 958 630 858 865 194
213 689 968 512 222 344 866
599 316 220 688 36 813 484
81 268 999 176 844 889 26
8
785 731 945 76 99 309 881 824
618 252 976 243 972 64 570 194
847 796 185 233 690 987 659 656
984 687 763 783 293 155 381 114
375 512 200 99 531 427 65 908
129 682 574 757 632 281 259 108
751 630 682 221 372 199 90 387
717 989 855 445 777 64 772 6
9
463 410 446 222 61 356 407 773 496
879 303 248 671 127 510 998 617 668
752 63 485 25 896 795 466 306 876
343 577 862 860 275 877 181 124 205
83 476 793 459 739 674 547 489 63
914 656 964 366 932 173 188 430 152
970 801 584 685 329 493 301 898 215
828 995 703 931 113 376 189 841 682
171 805 33 844 180 831 241 271 729
10
522 175 545 267 893 404 115 503 815 296
1000 748 789 116 123 316 454 275 314 716
693 900 624 37 962 357 834 449 283 71
698 934 232 25 734 449 79 611 145 501
209 82 750 701 870 726 651 288 255 993
730 722 993 284 958 397 85 757 947 269
464 522 119 659 407 867 375 791 207 685
152 701 625 115 351 970 898 521 642 372
252 307 338 963 169 775 133 253 915 139
705 179 183 94 365 229 53 805 740 701


출력
#1 2140
#2 1831
#3 3180
#4 2923
#5 4058
#6 4990
#7 6230
#8 7160
#9 8058
#10 9230

 */
public class backTracking_solved1 {
	static int testCount;
	static int N;// size of mat
	static int mat[][];
	static boolean visited[];
	static int[] numbers;
	static long finalMax;
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		testCount = Integer.parseInt(br.readLine());
		
		for (int testNumber = 1; testNumber <= testCount; testNumber++) {
		
			N = Integer.parseInt(br.readLine());
			mat = new int[N+1][N+1];
			
			for (int i = 1; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				for (int j=1; j <= N; j++) {
					mat[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			numbers = new int[N];
			for (int i=0; i < N; i++) {
				numbers[i] = i+1;
			}
			
			finalMax = 0;
			for (int i = 0; i < N; i++) {
				visited = new boolean[N];				
				backTracking(i, 1, ""+numbers[i]);
			}			
			
			bw.write("#"+testNumber+" "+(finalMax));
			bw.newLine();
		}		

		br.close();
		bw.close();
	}
	
	public static void backTracking(int cur, int count, String str) {
		if (count==N) {
			String[] t = str.split("/");
			int temp = 0;
			for (int i = 0; i < N; i++) {
				temp += mat[i+1][Integer.parseInt(t[i])];				
			}
			finalMax = Math.max(finalMax, temp);
			
			return;
		}
		
		if (visited[cur])
			return;
		
		visited[cur] = true;		
		
		for (int i = 0; i <N; i++) {
				if(!visited[i]) {
					backTracking(i, count+1, str+"/"+numbers[i]);
					visited[i] = false;
				}
				
				
		}
	}
}