package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

//https://programmers.co.kr/learn/courses/30/lessons/72412
public class kakao_2lv_72412 {
	
	static HashMap<String, ArrayList<Integer>> keyMap;
	static String[] infoArr;
	static String[] workArr;
	static int score;
	
	public static void main(String[] args) {
		int[] answer;
		String [] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
		String [] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
		
		keyMap = new HashMap<String, ArrayList<Integer>>();
		for (int i = 0; i < info.length; i++) {
			infoArr = info[i].split(" ");
			workArr = new String[4];
			score = Integer.parseInt(infoArr[4]);
			dfs_setKeyMapOfAllCase(0);
		}

		Iterator<String> it = keyMap.keySet().iterator();
		while(it.hasNext())
			Collections.sort(keyMap.get(it.next()));
		
		answer = new int[query.length];
		int idx = 0;
		for (int i = 0; i < query.length; i++) {
			String[] queryArr = query[i].split(" and | ");
			String key = queryArr[0] + queryArr[1] + queryArr[2] + queryArr[3];
			int targetScore = Integer.parseInt(queryArr[4]);
			
			int cnt = 0;
			if (keyMap.get(key) == null) {
				answer[idx++] = 0;
			}
			else {
				int totalCnt = keyMap.get(key).size();
				int minus = binarySearch(keyMap.get(key), targetScore);
				answer[idx++] = totalCnt - minus;
			}
		}
		
		System.out.println(Arrays.toString(answer));
	}
	
	static void dfs_setKeyMapOfAllCase(int idx) {
		if (idx==4) {
			String key = workArr[0] + workArr[1] + workArr[2] + workArr[3];

			if (!keyMap.containsKey(key))
				keyMap.put(key, new ArrayList<Integer>());
			
			keyMap.get(key).add(score);
			return;
		}
		
		workArr[idx] = infoArr[idx];
		dfs_setKeyMapOfAllCase(idx+1);
		workArr[idx] = "-";
		dfs_setKeyMapOfAllCase(idx+1);
	}
	
	static int binarySearch(ArrayList<Integer> list, int targetScore) {
		int l = 0;
		int r = list.size()-1;
		int mid = 0;
		
		while(l<=r) {
			mid = (l+r)/2;
			
			if (list.get(mid) < targetScore)
				l = mid+1;
			else
				r = mid-1;
		}
		
		return l;
	}

	
	// 제출용
	class Solution {
		static HashMap<String, ArrayList<Integer>> keyMap;
		static String[] infoArr;
		static String[] workArr;
		static int score;

		public static int[] solution(String[] info, String[] query) {
			
			keyMap = new HashMap<String, ArrayList<Integer>>();
			for (int i = 0; i < info.length; i++) {
				infoArr = info[i].split(" ");
				workArr = new String[4];
				score = Integer.parseInt(infoArr[4]);
				dfs_setKeyMapOfAllCase(0);
			}

			Iterator<String> it = keyMap.keySet().iterator();
			while(it.hasNext())
				Collections.sort(keyMap.get(it.next()));
			
			int[] answer = new int[query.length];
			int idx = 0;
			for (int i = 0; i < query.length; i++) {
				String[] queryArr = query[i].split(" and | ");
				String key = queryArr[0] + queryArr[1] + queryArr[2] + queryArr[3];
				int targetScore = Integer.parseInt(queryArr[4]);
				
				int cnt = 0;
				if (keyMap.get(key) == null) {
					answer[idx++] = 0;
				}
				else {
					int totalCnt = keyMap.get(key).size();
					int minus = binarySearch(keyMap.get(key), targetScore);
					answer[idx++] = totalCnt - minus;
				}
			}
			
			return answer;
		}
		
		static void dfs_setKeyMapOfAllCase(int idx) {
			if (idx==4) {
				String key = workArr[0] + workArr[1] + workArr[2] + workArr[3];

				if (!keyMap.containsKey(key))
					keyMap.put(key, new ArrayList<Integer>());
				
				keyMap.get(key).add(score);
				return;
			}
			
			workArr[idx] = infoArr[idx];
			dfs_setKeyMapOfAllCase(idx+1);
			workArr[idx] = "-";
			dfs_setKeyMapOfAllCase(idx+1);
		}
		
		static int binarySearch(ArrayList<Integer> list, int targetScore) {
			int l = 0;
			int r = list.size()-1;
			int mid = 0;
			
			while(l<=r) {
				mid = (l+r)/2;
				
				if (list.get(mid) < targetScore)
					l = mid+1;
				else
					r = mid-1;
			}
			
			return l;
		}

	}
}
