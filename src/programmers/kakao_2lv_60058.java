package programmers;

//https://programmers.co.kr/learn/courses/30/lessons/60058
public class kakao_2lv_60058 {

	public static void main(String[] args) {
		String input = "(()))(";
		
		System.out.println(getStr(input, 0));
	}
	

	static String getStr(String str, int idx) {
		if ("".equals(str))
			return str;
		
		int leftOpen = 0;
		int balCheck = 0;
		
		for (int i = idx; i < str.length(); i++) {
			//2
			if (str.charAt(i) == '(') {
				leftOpen++;
				balCheck++;
			}
			else if(str.charAt(i) == ')') {
				if (leftOpen>0)
					leftOpen--;
				balCheck--;
			}
			
			//3
			if (balCheck == 0) {
				if (leftOpen == 0) {
					//3-1
					return str.substring(idx, i+1) + getStr(str, i+1);
				}
				//4
				else {
					//4-1 ~ 4-3
					String temp = "(" + getStr(str, i+1) + ")";
					
					temp += reverseStr(str.substring(idx+1, i));
					
					return temp;
				}
			}
		}
		
		return "";
				
	}
	
	static String reverseStr(String input) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < input.length(); i++)
			sb.append(input.charAt(i)=='('?')':'(');
		
		return sb.toString();
	}
	
	//제출용
	class Solution {
	    public String solution(String p) {
	        return getStr(p, 0);
	    }
	    
	    static String getStr(String str, int idx) {
			if ("".equals(str))
				return str;
			
			int leftOpen = 0;
			int balCheck = 0;
			
			for (int i = idx; i < str.length(); i++) {
				//2
				if (str.charAt(i) == '(') {
					leftOpen++;
					balCheck++;
				}
				else if(str.charAt(i) == ')') {
					if (leftOpen>0)
						leftOpen--;
					balCheck--;
				}
				
				//3
				if (balCheck == 0) {
					if (leftOpen == 0) {
						//3-1
						return str.substring(idx, i+1) + getStr(str, i+1);
					}
					//4
					else {
						//4-1 ~ 4-3
						String temp = "(" + getStr(str, i+1) + ")";
						
						temp += reverseStr(str.substring(idx+1, i));
						
						return temp;
					}
				}
			}
			
			return "";
					
		}
		
		static String reverseStr(String input) {
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < input.length(); i++)
				sb.append(input.charAt(i)=='('?')':'(');
			
			return sb.toString();
		}
	}
}
