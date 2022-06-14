package programmers;

import java.util.ArrayList;
import java.util.List;

//https://programmers.co.kr/learn/courses/30/lessons/67257
public class kakao_2lv_67257 {

	public static void main(String[] args) {
		String exp = "100-200*300-500+20";
		//nums = 100,200,300,500,20
		//ops  = -,*,-,+
		
		List<Long> nums = new ArrayList<Long>();
		List<String> ops = new ArrayList<String>();
		long answer = 0;
		
		int idx = 0;
		for (int i = 0; i < exp.length(); i++) {
			if (exp.charAt(i)=='-' || exp.charAt(i)=='+' || exp.charAt(i)=='*') {
				ops.add(String.valueOf(exp.charAt(i)));
				nums.add(Long.parseLong(exp.substring(idx, i)));
				idx = i+1;
			}
			else if (i==exp.length()-1){
				nums.add(Long.parseLong(exp.substring(idx, i+1)));
			}
		}
		
		String[] prior = {"*+-", "*-+",
		        		  "+*-", "+-*", 
		        		  "-*+", "-+*"};
		
		for (int j = 0; j < 6; j++) {
			List<Long> numList = new ArrayList<Long>();
			List<String> opList = new ArrayList<String>();
			
			for (int i = 0; i < nums.size(); i++)
				numList.add(nums.get(i));
			
			for (int i = 0; i < ops.size(); i++)
				opList.add(ops.get(i));
			
			calculate(numList, opList, prior[j].charAt(0));
			calculate(numList, opList, prior[j].charAt(1));
			calculate(numList, opList, prior[j].charAt(2));
			
			answer = Math.max(answer, Math.abs(numList.get(0)));
		}
		
		System.out.println(answer);
	}
	
	static void calculate(List<Long> nums, List<String> ops, char op) {
		String str = String.valueOf(op);
		for (int i = 0; i < ops.size(); i++) {
			if (ops.get(i).equals(str)) {
				long num1 = nums.get(i);
				long num2 = nums.get(i+1);
				long result = doCal(num1, num2, str);
				
				nums.remove(i);
				nums.remove(i);
				nums.add(i, result);
				ops.remove(i);
				i--;
			}
		}
	}
	
	static long doCal(long num1, long num2, String op) {
		if ("+".equals(op))
			return num1+num2;
		else if ("-".equals(op))
			return num1-num2;
		else
			return num1*num2;
	}

	// 제출용
	class Solution {
		public long solution(String expression) {
			long answer = 0;
			List<Long> nums = new ArrayList<Long>();
			List<String> ops = new ArrayList<String>();
			
			int idx = 0;
			for (int i = 0; i < expression.length(); i++) {
				if (expression.charAt(i)=='-' || expression.charAt(i)=='+' || expression.charAt(i)=='*') {
					ops.add(String.valueOf(expression.charAt(i)));
					nums.add(Long.parseLong(expression.substring(idx, i)));
					idx = i+1;
				}
				else if (i==expression.length()-1){
					nums.add(Long.parseLong(expression.substring(idx, i+1)));
				}
			}
			
			String[] prior = {"*+-", "*-+",
			        		  "+*-", "+-*", 
			        		  "-*+", "-+*"};
			
			for (int j = 0; j < 6; j++) {
				List<Long> numList = new ArrayList<Long>();
				List<String> opList = new ArrayList<String>();
				
				for (int i = 0; i < nums.size(); i++)
					numList.add(nums.get(i));
				
				for (int i = 0; i < ops.size(); i++)
					opList.add(ops.get(i));
				
				calculate(numList, opList, prior[j].charAt(0));
				calculate(numList, opList, prior[j].charAt(1));
				calculate(numList, opList, prior[j].charAt(2));
				
				answer = Math.max(answer, Math.abs(numList.get(0)));
			}			
			
			return answer;
		}
		
		static void calculate(List<Long> nums, List<String> ops, char op) {
			String str = String.valueOf(op);
			for (int i = 0; i < ops.size(); i++) {
				if (ops.get(i).equals(str)) {
					long num1 = nums.get(i);
					long num2 = nums.get(i+1);
					long result = doCal(num1, num2, str);
					
					nums.remove(i);
					nums.remove(i);
					nums.add(i, result);
					ops.remove(i);
					i--;
				}
			}
		}
		
		static long doCal(long num1, long num2, String op) {
			if ("+".equals(op))
				return num1+num2;
			else if ("-".equals(op))
				return num1-num2;
			else
				return num1*num2;
		}
	}
}
