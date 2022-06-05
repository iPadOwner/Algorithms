package programmers;

import java.util.Deque;
import java.util.LinkedList;

//https://programmers.co.kr/learn/courses/30/lessons/17680
public class kakao_2lv_17680 {

	public static void main(String[] args) {
		int cacheSize = 3;
		String[] cities = {"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"};
		int answer = 0;
		
		Deque<String> dq = new LinkedList<String>();
		
		if (cacheSize == 0) {
			answer = cities.length * 5;
			System.out.println(answer);
			return;
		}
		
		for (int i = 0; i < cities.length; i++) {
			String cur = cities[i];
			cur = cur.toUpperCase();
			
			if (dq.contains(cur)) {
				answer++;
				
				dq.remove(cur);
				dq.addFirst(cur);
			}
			else {
				answer += 5;
				dq.addFirst(cur);
			}
			
			if (dq.size() > cacheSize)
				dq.removeLast();
		}
		
		System.out.println(answer);
	}

	
	//제출용
	class Solution {
	    public int solution(int cacheSize, String[] cities) {
	        int answer = 0;
			
			Deque<String> dq = new LinkedList<String>();
			
			if (cacheSize == 0) {
				answer = cities.length * 5;
				return answer;
			}
			
			for (int i = 0; i < cities.length; i++) {
				String cur = cities[i];
				cur = cur.toUpperCase();
				
				if (dq.contains(cur)) {
					answer++;
					
					dq.remove(cur);
					dq.addFirst(cur);
				}
				else {
					answer += 5;
					dq.addFirst(cur);
				}
				
				if (dq.size() > cacheSize)
					dq.removeLast();
			}
	        return answer;
	    }
	}
}
