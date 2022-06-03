package programmers;

import java.util.ArrayList;
import java.util.List;

//https://programmers.co.kr/learn/courses/30/lessons/17677
public class kakao_2lv_17677 {

	public static void main(String[] args) {
		String str1 = "E=M*C^2	";
		String str2 = "e=m*c^2";
		str1 = str1.toUpperCase();
		str2 = str2.toUpperCase();
		
		char[] arr1 = str1.toCharArray();
		char[] arr2 = str2.toCharArray();
		
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		List<String> list3 = new ArrayList<String>();
		
		for (int i = 0; i < arr1.length-1; i++) {
			if ((int)arr1[i]<65 || (int)arr1[i]>90)
				continue;
			
			if ((int)arr1[i+1]<65 || (int)arr1[i+1]>90)
				continue;
			
			StringBuilder sb = new StringBuilder();
			list1.add(sb.append(arr1[i]).append(arr1[i+1]).toString());
		}
		
		for (int i = 0; i < arr2.length-1; i++) {
			if ((int)arr2[i]<65 || (int)arr2[i]>90)
				continue;
			
			if ((int)arr2[i+1]<65 || (int)arr2[i+1]>90)
				continue;
			
			StringBuilder sb = new StringBuilder();
			list2.add(sb.append(arr2[i]).append(arr2[i+1]).toString());
		}
		
		for (int i = 0; i < list1.size(); i++) {
			for (int j = 0; j < list2.size(); j++) {
				if (list1.get(i).equals(list2.get(j))) {
					String val = list1.get(i);
					list3.add(val);
					list1.remove(i);
					list2.remove(j);
					i--;
					break;
				}
			}
		}
		

		double a = list3.size();
		double b = list1.size() + list2.size() + list3.size();
		double c = a/b;
		
		if (a==0 && b==0)
			c = 1.0;
		
		int answer = (int)(c*65536);
		
		System.out.println(answer);
	}

	//제출용
	class Solution {
	    public int solution(String str1, String str2) {
	    	str1 = str1.toUpperCase();
			str2 = str2.toUpperCase();
			
			char[] arr1 = str1.toCharArray();
			char[] arr2 = str2.toCharArray();
			
			List<String> list1 = new ArrayList<String>();
			List<String> list2 = new ArrayList<String>();
			List<String> list3 = new ArrayList<String>();
			
			for (int i = 0; i < arr1.length-1; i++) {
				if ((int)arr1[i]<65 || (int)arr1[i]>90)
					continue;
				
				if ((int)arr1[i+1]<65 || (int)arr1[i+1]>90)
					continue;
				
				StringBuilder sb = new StringBuilder();
				list1.add(sb.append(arr1[i]).append(arr1[i+1]).toString());
			}
			
			for (int i = 0; i < arr2.length-1; i++) {
				if ((int)arr2[i]<65 || (int)arr2[i]>90)
					continue;
				
				if ((int)arr2[i+1]<65 || (int)arr2[i+1]>90)
					continue;
				
				StringBuilder sb = new StringBuilder();
				list2.add(sb.append(arr2[i]).append(arr2[i+1]).toString());
			}
			
			for (int i = 0; i < list1.size(); i++) {
				for (int j = 0; j < list2.size(); j++) {
					if (list1.get(i).equals(list2.get(j))) {
						String val = list1.get(i);
						list3.add(val);
						list1.remove(i);
						list2.remove(j);
						i--;
						break;
					}
				}
			}
			

			double a = list3.size();
			double b = list1.size() + list2.size() + list3.size();
			double c = a/b;
			
			if (a==0 && b==0)
				c = 1.0;
			
			int answer = (int)(c*65536);
			
			return answer;
	    }
	}
}
