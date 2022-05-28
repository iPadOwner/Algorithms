package programmers;

import java.util.Stack;

//https://programmers.co.kr/learn/courses/30/lessons/64061
public class kakao_1lv_64061 {
	public static void main(String[] args) {
		int[][] board = {{0,0,0,0,0}
					  ,{0,0,1,0,3}
					  ,{0,2,5,0,1}
					  ,{4,2,4,4,2}
					  ,{3,5,1,3,1}};
		
		int answer = 0;
		int N = board.length;
		int[] moves = {1,5,3,5,1,2,1,4};
		for (int i=0; i < moves.length; i++)
			moves[i]--;
		
		Stack<Integer> stack = new Stack<Integer>();
		
		for (int j : moves) {
			for (int i = 0; i < N; i++) {
				if (board[i][j]!=0) {
					int value = board[i][j];
					board[i][j] = 0;
					
					if (!stack.isEmpty() && stack.peek() == value) {
						stack.pop();
						answer+=2;
						break;
					}
					
					stack.push(value);					
					break;
				}
			}
		}
		
		System.out.println(answer);
	}
	
	
	//제출용
	class Solution {
	    public int solution(int[][] board, int[] moves) {
	        int answer = 0;
	        
	        int N = board.length;
			for (int i=0; i < moves.length; i++)
				moves[i]--;
			
			Stack<Integer> stack = new Stack<Integer>();
			
			for (int j : moves) {
				for (int i = 0; i < N; i++) {
					if (board[i][j]!=0) {
						int value = board[i][j];
						board[i][j] = 0;
						
						if (!stack.isEmpty() && stack.peek() == value) {
							stack.pop();
							answer+=2;
							break;
						}
						
						stack.push(value);					
						break;
					}
				}
			}
			
			return answer;
	    }
	}
}
