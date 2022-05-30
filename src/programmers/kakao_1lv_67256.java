package programmers;

//https://programmers.co.kr/learn/courses/30/lessons/67256
public class kakao_1lv_67256 {

	static int leftFingerPos;
	static int rightFingerPos;
	static StringBuilder answer;
	
	public static void main(String[] args) {
		int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
		String hand = "right";
		
		char[] fixed    = {'?','L','?','R','L','?','R','L','?','R'};
		leftFingerPos  = 10;
		rightFingerPos = 11;
		answer = new StringBuilder();
		
		int [][]posArr = {{1, 3}//0
						 ,{0, 0}//1
						 ,{1, 0}//2
						 ,{2, 0}//3
						 ,{0, 1}//4
						 ,{1, 1}//5
						 ,{2, 1}//6
						 ,{0, 2}//7
						 ,{1, 2}//8
						 ,{2, 2}//9
						 ,{0, 3}//10(*)
						 ,{2, 3}//11(#)
						 };
		
		
		for (int input : numbers) {
			if (fixed[input] == 'L')
				addLeft(input);
			else if (fixed[input] == 'R')
				addRight(input);
			else {
				int ldist = getDist(posArr, input, leftFingerPos);
				int rdist = getDist(posArr, input, rightFingerPos);
				
				if (ldist < rdist)
					addLeft(input);
				else if (ldist > rdist)
					addRight(input);
				else {
					if ("right".equals(hand))
						addRight(input);
					else
						addLeft(input);
				}
			}
		}
		
		System.out.println(answer.toString());
	}

	static void addLeft(int num) {
		leftFingerPos = num;
		answer.append('L');		
	}
	
	static void addRight(int num) {
		rightFingerPos = num;
		answer.append('R');
	}
	
	//맨하탄거리
	static int getDist(int[][] posArr, int from, int to) {
		int[] f = posArr[from];
		int[] t = posArr[to];
		
		return Math.abs(f[0] - t[0]) + Math.abs(f[1] - t[1]);
	}
	
	// 제출용
	class Solution {
		static int leftFingerPos;
		static int rightFingerPos;
		static StringBuilder answer;
		
		public String solution(int[] numbers, String hand) {
			
			char[] fixed    = {'?','L','?','R','L','?','R','L','?','R'};
			leftFingerPos  = 10;
			rightFingerPos = 11;
			answer = new StringBuilder();
			
			int [][]posArr = {{1, 3}//0
							 ,{0, 0}//1
							 ,{1, 0}//2
							 ,{2, 0}//3
							 ,{0, 1}//4
							 ,{1, 1}//5
							 ,{2, 1}//6
							 ,{0, 2}//7
							 ,{1, 2}//8
							 ,{2, 2}//9
							 ,{0, 3}//10(*)
							 ,{2, 3}//11(#)
							 };
			
			
			for (int input : numbers) {
				if (fixed[input] == 'L')
					addLeft(input);
				else if (fixed[input] == 'R')
					addRight(input);
				else {
					int ldist = getDist(posArr, input, leftFingerPos);
					int rdist = getDist(posArr, input, rightFingerPos);
					
					if (ldist < rdist)
						addLeft(input);
					else if (ldist > rdist)
						addRight(input);
					else {
						if ("right".equals(hand))
							addRight(input);
						else
							addLeft(input);
					}
				}
			}
			
			return answer.toString();
		}
		
		static void addLeft(int num) {
			leftFingerPos = num;
			answer.append('L');
		}
		
		static void addRight(int num) {
			rightFingerPos = num;
			answer.append('R');
		}
		
		//맨하탄거리
		static int getDist(int[][] posArr, int from, int to) {
			int[] f = posArr[from];
			int[] t = posArr[to];
			
			return Math.abs(f[0] - t[0]) + Math.abs(f[1] - t[1]);
		}
	}
}
