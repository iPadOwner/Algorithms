package programmers;

import java.util.Stack;

public class kakao_1lv_17682 {
	public static void main(String[] args) {
		String input = "10D4S10D";
		char[] arr = input.toCharArray();

		Stack<Integer> stk = new Stack<Integer>();

		for (int i = 0; i < arr.length; i++) {
			char c = arr[i];

			if (Character.isDigit(c)) {
				if (i < arr.length - 1 && Character.isDigit(arr[i + 1])) {
					stk.add(Integer.parseInt(c + "" + arr[i + 1] + ""));
					i++;
				} else
					stk.add(c - '0');
			} else {
				if (c == 'S') {
					int x = stk.pop();
					stk.push(x * 1);
				} else if (c == 'D') {
					int x = stk.pop();
					stk.push(x * x);
				} else if (c == 'T') {
					int x = stk.pop();
					stk.push(x * x * x);
				} else if (c == '*') {
					int x1 = stk.pop();
					x1 *= 2;

					if (!stk.isEmpty()) {
						int x2 = stk.pop();
						x2 *= 2;
						stk.push(x2);
					}

					stk.push(x1);
				} else if (c == '#') {
					int x = stk.pop();
					stk.push(-1 * x);
				}
			}
		}

		int score = 0;
		while (!stk.isEmpty())
			score += stk.pop();

		System.out.println(score);
	}

	// 제출용
	class Solution {
		public int solution(String dartResult) {
			char[] arr = dartResult.toCharArray();
			Stack<Integer> stk = new Stack<Integer>();

			for (int i = 0; i < arr.length; i++) {
				char c = arr[i];

				if (Character.isDigit(c)) {
					if (i < arr.length - 1 && Character.isDigit(arr[i + 1])) {
						stk.add(Integer.parseInt(c + "" + arr[i + 1] + ""));
						i++;
					} else
						stk.add(c - '0');
				} else {
					if (c == 'S') {
						int x = stk.pop();
						stk.push(x * 1);
					} else if (c == 'D') {
						int x = stk.pop();
						stk.push(x * x);
					} else if (c == 'T') {
						int x = stk.pop();
						stk.push(x * x * x);
					} else if (c == '*') {
						int x1 = stk.pop();
						x1 *= 2;

						if (!stk.isEmpty()) {
							int x2 = stk.pop();
							x2 *= 2;
							stk.push(x2);
						}

						stk.push(x1);
					} else if (c == '#') {
						int x = stk.pop();
						stk.push(-1 * x);
					}
				}
			}

			int answer = 0;
			while (!stk.isEmpty())
				answer += stk.pop();

			return answer;
		}

		// 다른 풀이
		public int solution2(String dartResult) {
			Stack<Integer> stack = new Stack<>();
			int sum = 0;
			for (int i = 0; i < dartResult.length(); ++i) {
				char c = dartResult.charAt(i);
				if (Character.isDigit(c)) {
					sum = (c - '0');
					if (sum == 1 && i < dartResult.length() - 1 && dartResult.charAt(i + 1) == '0') {
						sum = 10;
						i++;
					}
					stack.push(sum);
				} else {
					int prev = stack.pop();
					if (c == 'D') {
						prev *= prev;
					} else if (c == 'T') {
						prev = prev * prev * prev;
					} else if (c == '*') {
						if (!stack.isEmpty()) {
							int val = stack.pop() * 2;
							stack.push(val);
						}
						prev *= 2;
					} else if (c == '#') {
						prev *= (-1);
					}
					// System.out.println(prev);
					stack.push(prev);
				}
			}
			int totalScore = 0;
			while (!stack.isEmpty()) {
				totalScore += stack.pop();
			}
			return totalScore;
		}
	}
}
