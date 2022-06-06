package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//https://programmers.co.kr/learn/courses/30/lessons/17684
public class kakao_2lv_17684 {

	public static void main(String[] args) {
		String msg = "ABABABABABABABAB";
		String[] arr = msg.split("");
		List<Integer> list = new ArrayList<Integer>();
		
		HashMap<String, Integer> dicV = new HashMap<String, Integer>();
		
		for (int i = 1; i <= 26; i++)
			dicV.put(String.valueOf((char)(64+i)), i);//A~Z : 1~26

		int lastIdx = 26;
		
		for (int i = 0; i < msg.length(); i++) {
			String s = arr[i];
			int printInt = dicV.get(s);
			int pass = 0;
			
			if (i == msg.length()-1)
				list.add(printInt);
			
			for (int j = i+1; j < msg.length(); j++) {
				if (dicV.containsKey(s + arr[j])) {
					pass++;
					printInt = dicV.get(s + arr[j]);
					s += arr[j];
					
					if (j == msg.length()-1) {
						list.add(printInt);
						i += pass;
					}
					continue;
				}
				
				list.add(printInt);
				dicV.put(s + arr[j], ++lastIdx);
				i += pass;
				break;
			}
			
		}
		
		int[] answer = new int[list.size()];
		int idx = 0;
		for(Integer i : list)
			answer[idx++] = i;
		
		System.out.println(Arrays.toString(answer));
	}

	
	//제출용
	class Solution {
	    public int[] solution(String msg) {
	    	String[] arr = msg.split("");
			List<Integer> list = new ArrayList<Integer>();
			
			HashMap<String, Integer> dicV = new HashMap<String, Integer>();
			
			for (int i = 1; i <= 26; i++)
				dicV.put(String.valueOf((char)(64+i)), i);//A~Z : 1~26

			int lastIdx = 26;
			
			for (int i = 0; i < msg.length(); i++) {
				String s = arr[i];
				int printInt = dicV.get(s);
				int pass = 0;
				
				if (i == msg.length()-1)
					list.add(printInt);
				
				for (int j = i+1; j < msg.length(); j++) {
					if (dicV.containsKey(s + arr[j])) {
						pass++;
						printInt = dicV.get(s + arr[j]);
						s += arr[j];
						
						if (j == msg.length()-1) {
							list.add(printInt);
							i += pass;
						}
						continue;
					}
					
					list.add(printInt);
					dicV.put(s + arr[j], ++lastIdx);
					i += pass;
					break;
				}
				
			}
			
			int[] answer = new int[list.size()];
			int idx = 0;
			for(Integer i : list)
				answer[idx++] = i;
			
	        return answer;
	    }
	}
}
