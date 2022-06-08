package programmers;

//https://programmers.co.kr/learn/courses/30/lessons/17687
public class kakao_2lv_17687 {

	public static void main(String[] args) {
		int n = 0;
		int t = 0;
		int m = 0;
		int p = 0;
		
		StringBuilder sb = new StringBuilder();
		
		n = 16;
		t = 16;
		m = 2;
		p = 2;
		int idx = 0;
		while(sb.length()<t*m)
			sb.append(Integer.toString(idx++, n));
		
		
		char[] arr = sb.toString().toCharArray();
		sb = new StringBuilder();
		
		idx = 0;
		int cur = 1;
		while(sb.length()<t) {
			if (cur > m)
				cur = 1;
			
			char c = arr[idx++];
			if(cur == p)
				sb.append(String.valueOf(c));
			
			cur++;
		}
		
		System.out.println(sb.toString().toUpperCase());
	}

	
	//제출용
	class Solution {
	    public String solution(int n, int t, int m, int p) {
	        StringBuilder sb = new StringBuilder();
	        int idx = 0;
			while(sb.length()<t*m)
				sb.append(Integer.toString(idx++, n));
			
			
			char[] arr = sb.toString().toCharArray();
			sb = new StringBuilder();
			
			idx = 0;
			int cur = 1;
			while(sb.length()<t) {
				if (cur > m)
					cur = 1;
				
				char c = arr[idx++];
				if(cur == p)
					sb.append(String.valueOf(c));
				
				cur++;
			}
			
	        return sb.toString().toUpperCase();
	    }
	}
}
