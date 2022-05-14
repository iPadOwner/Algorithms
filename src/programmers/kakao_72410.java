package programmers;

//https://programmers.co.kr/learn/courses/30/lessons/72410
public class kakao_72410 {

	public static void main(String[] args) {
		String answer = "abcdefghijklmn.p";
		
		//step1 : 소문자로 변경
		answer = answer.toLowerCase();
		
		//step2 : 알파벳소문자,숫자,빼기,하이픈,쩜 을 제외한 모든것을 제거
		answer = answer.replaceAll("[^a-z0-9\\-\\_\\.]", "");
		
		//step3 : '.'이 최소 2번이상 반복되는것을 '.'으로 변경
		answer = answer.replaceAll("[\\.]{2,}", ".");
		
		//step4 : 양끝에 '.' 으로 시작하거나 끝나면 제거
		answer = answer.replaceAll("^[.]|[.]$", "");
		
		//step5 : 빈 문자열이면 'a' 대입
		if(answer==null || "".equals(answer))
			answer = "a";
		
		//step6 : 15개 초과시 이후 문자는 버리고, 제거후 '.' 로 끝나면 제거.
		if (answer.length()>15){
			answer = answer.substring(0, 15);
			answer = answer.replaceAll("^[.]|[.]$", "");
		}
		
		//step7 : new_id 의 길이가 2자 이하라면, new_id의 마지막 문자를 길이가 3이될때까지 복사
		while(answer.length()<3)
			answer += answer.charAt(answer.length()-1);
		
		System.out.println(answer);
	}

	//제출용
	class Solution{
		public String solution(String new_id) {
	        String answer = new_id;
	        
			answer = answer.toLowerCase();
			answer = answer.replaceAll("[^a-z0-9\\-\\_\\.]", "");
			answer = answer.replaceAll("[\\.]{2,}", ".");
			answer = answer.replaceAll("^[.]|[.]$", "");
			
			if(answer==null || "".equals(answer))
				answer = "a";

			if (answer.length()>15){
				answer = answer.substring(0, 15);
				answer = answer.replaceAll("^[.]|[.]$", "");
			}
			
			while(answer.length()<3)
				answer += answer.charAt(answer.length()-1);
			
	        return answer;
	    }
	}
}
