package programmers;

//https://programmers.co.kr/learn/courses/30/lessons/92335
public class kakao_2lv_92335 {

	public static void main(String[] args) {
		int n = 110011;
		int k = 10;

		int answer = 0;

		String target = Long.toString(n, k);
		String[] candidates = target.split("0+");
		for (int i = 0; i < candidates.length; i++)
			if (isPrime(Long.parseLong(candidates[i])))
				answer++;

		System.out.println(answer);
	}

	static boolean isPrime(long num) {
		if (num == 1)
			return false;
		else if (num == 2)
			return true;

		//큰수 대비 루트로씌워서 
		long limit = (long) Math.sqrt(num);
		
		//소수 : 1과 자신만을 약수로 가짐
		for (int i = 2; i <= limit; ++i)
			if (num % i == 0)
				return false;
			
		return true;
	}

	
	// 제출용
	class Solution {
		
		public int solution(int n, int k) {
			int answer = 0;

			String target = Long.toString(n, k);
			String[] candidates = target.split("0+");
			for (int i = 0; i < candidates.length; i++)
				if (isPrime(Long.parseLong(candidates[i])))
					answer++;

			return answer;
		}

		static boolean isPrime(long num) {
			if (num == 1)
				return false;
			else if (num == 2)
				return true;

			//큰수 대비 루트로씌워서
			long limit = (long) Math.sqrt(num);
			
			//소수 : 1과 자신만을 약수로 가짐
			for (int i = 2; i <= limit; ++i)
				if (num % i == 0)
					return false;
			
			return true;
		}
	}
}
