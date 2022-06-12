package programmers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//https://programmers.co.kr/learn/courses/30/lessons/60057
/*
테스트 1
입력값 〉	"aabbaccc"
기댓값 〉	7

테스트 2
입력값 〉	"ababcdcdababcdcd"
기댓값 〉	9

테스트 3
입력값 〉	"abcabcdede"
기댓값 〉	8

테스트 4
입력값 〉	"abcabcabcabcdededededede"
기댓값 〉	14

테스트 5
입력값 〉	"xababcdcdababcdcd"
기댓값 〉	17

 */
public class kakao_2lv_60507 {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String input = br.readLine();
		bw.write(Solution.solution(input) + "");		
		
		br.close();
		bw.close();
	}

	class Solution {
		public static int solution(String s) {
			int answer = 0;

			for (int i = 1; i <= (s.length() / 2) + 1; i++) {
				int result = getSplitedLength(s, i, 1).length();
				answer = i == 1 ? result : (answer > result ? result : answer);
			}

			return answer;
		}

		public static String getSplitedLength(String s, int cutCount, int repeat) {
			if (s.length() < cutCount)
				return s;
			String result = "";
			String preString = s.substring(0, cutCount);
			String postString = s.substring(cutCount, s.length());

			// 불일치 -> 현재까지 [반복횟수 + 반복문자] 조합
			if (!postString.startsWith(preString)) {
				if (repeat == 1)
					return result += preString + getSplitedLength(postString, cutCount, 1);
				return result += Integer.toString(repeat) + preString + getSplitedLength(postString, cutCount, 1);
			}

			return result += getSplitedLength(postString, cutCount, repeat + 1);
		}
	}

}
