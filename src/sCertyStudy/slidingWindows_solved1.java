package sCertyStudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

//sliding window
/*
입력
8 3
1 3 -1 -3 5 3 6 7

출력
-1 3 3
-3 3 -1
-3 5 1
-3 5 5
3 6 14
3 7 16
 */
public class slidingWindows_solved1 {
    static int arrSize;
    static int subArrSize;
    static int[] arr;
      
    public static void main(String[] args) throws Exception {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        arrSize = Integer.parseInt(st.nextToken());
        subArrSize = Integer.parseInt(st.nextToken());
        
        arr = new int[arrSize+1];        
        st = new StringTokenizer(br.readLine());
        
        for (int i=1;i<=arrSize;i++){
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        
        Deque<Integer> q1 = new ArrayDeque<Integer>();
        Deque<Integer> q2 = new ArrayDeque<Integer>();
        
        long sum = 0;
        
        for (int i=1;i<=arrSize;i++){
            
        	while (!q1.isEmpty() && arr[q1.getLast()] >= arr[i]){
            	q1.pollLast();
            }
            
        	while (!q2.isEmpty() && arr[q2.getLast()] <= arr[i]){
        		q2.pollLast();
        	}
        	
            q1.addLast(i);
            q2.addLast(i);
            sum += arr[i];
            
            if (i > subArrSize){
            	sum -= arr[i-subArrSize];
            }
            
            while (q1.getFirst() <= i-subArrSize){
            	q1.pollFirst();
            }
            
            while (q2.getFirst() <= i-subArrSize){
            	q2.pollFirst();
            }
            
            if (i >= subArrSize){
                bw.write(arr[q1.getFirst()] + " " + arr[q2.getFirst()] + " " + sum + "\n");
            }
        }
        
        bw.flush();
        bw.close();
    }
}