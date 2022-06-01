package programmers;

import java.util.HashMap;
import java.util.Iterator;

//https://programmers.co.kr/learn/courses/30/lessons/81301
public class kakao_1lv_81301 {
	public static void main(String[] args){
		String s = "one4seveneight";
		
		HashMap<String, String> baseMap = new HashMap<String, String>();
		baseMap.put("zero" , "0");
		baseMap.put("one"  , "1");
		baseMap.put("two"  , "2");
		baseMap.put("three", "3");
		baseMap.put("four" , "4");
		baseMap.put("five" , "5");
		baseMap.put("six"  , "6");
		baseMap.put("seven", "7");
		baseMap.put("eight", "8");
		baseMap.put("nine" , "9");
		
		Iterator<String> it = baseMap.keySet().iterator();
		while(it.hasNext()){
			String cmp = it.next();
			s = s.replaceAll(cmp, baseMap.get(cmp));
		}
		
		System.out.println(s);
	}
	
	//제출용
	class Solution {
	    public int solution(String s) {
	        HashMap<String, String> baseMap = new HashMap<String, String>();
			baseMap.put("zero" , "0");
			baseMap.put("one"  , "1");
			baseMap.put("two"  , "2");
			baseMap.put("three", "3");
			baseMap.put("four" , "4");
			baseMap.put("five" , "5");
			baseMap.put("six"  , "6");
			baseMap.put("seven", "7");
			baseMap.put("eight", "8");
			baseMap.put("nine" , "9");
			
			Iterator<String> it = baseMap.keySet().iterator();
			while(it.hasNext()){
				String cmp = it.next();
				s = s.replaceAll(cmp, baseMap.get(cmp));
			}
			
	        return Integer.valueOf(s);
	    }
	}
}
