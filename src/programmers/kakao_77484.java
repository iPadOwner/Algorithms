package programmers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.stream.LongStream;

//https://programmers.co.kr/learn/courses/30/lessons/77484
/*

 */
public class kakao_77484 {

	public static void main(String[] args) {
		final int lottosMaxLen = 6;
		int[] lottos   = {45, 4, 35, 20, 3, 9};   //0,0, 1,25,31,44
		int[] win_nums = {20, 9, 3, 45, 4, 35};//1,6,10,19,31,45
		int[] rankArr  = {6, 6, 5, 4, 3, 2, 1};
		
		//0,0   1,31  25,44
		//      1,31
		
		HashMap<Integer, Integer> lm = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> wm = new HashMap<Integer, Integer>();
		int zeroCnt = 0;
		for (int l : lottos){
			if (l==0){
				zeroCnt++;
				continue;
			}
			lm.put(l,l);
		}
		
		for (int w : win_nums)
			wm.put(w,w);
		
		int unMatchCnt = 0;
		Iterator<Integer> li = lm.keySet().iterator();
		while(li.hasNext()){
			if (!wm.containsKey(li.next()))
				unMatchCnt++;
		}
		
		int minCnt = lottosMaxLen - zeroCnt - unMatchCnt;
		int maxCnt = lottosMaxLen - unMatchCnt;
		
		int minRank = rankArr[minCnt];
		int maxRank = rankArr[maxCnt];
		
		
		int[] answer = {maxRank, minRank};
		
		System.out.println(maxRank + " " + minRank);
	}

	//제출용
	class Solution{
		public int[] solution(int[] lottos, int[] win_nums) {
	        final int lottosMaxLen = 6;
			int[] rankArr  = {6, 6, 5, 4, 3, 2, 1};
			
			HashMap<Integer, Integer> lm = new HashMap<Integer, Integer>();
			HashMap<Integer, Integer> wm = new HashMap<Integer, Integer>();
			int zeroCnt = 0;
			for (int l : lottos){
				if (l==0){
					zeroCnt++;
					continue;
				}
				lm.put(l,l);
			}
			
			for (int w : win_nums)
				wm.put(w,w);
			
			int unMatchCnt = 0;
			Iterator<Integer> li = lm.keySet().iterator();
			while(li.hasNext())
				if (!wm.containsKey(li.next()))
					unMatchCnt++;
			
	        return new int[]{rankArr[lottosMaxLen - unMatchCnt], rankArr[lottosMaxLen - zeroCnt - unMatchCnt]};
	    }
		
		//stream쓴거 (느림)
		public int[] solution2(int[] lottos, int[] win_nums) {
	         return LongStream.of((lottos.length + 1) - Arrays.stream(lottos).filter(l -> Arrays.stream(win_nums).anyMatch(w -> w == l) || l == 0).count(),
	        		 			  (lottos.length + 1) - Arrays.stream(lottos).filter(l -> Arrays.stream(win_nums).anyMatch(w -> w == l)).count())
               .mapToInt(op -> (int) (op > 6 ? op - 1 : op))
               .toArray();
	    }
	}
}
