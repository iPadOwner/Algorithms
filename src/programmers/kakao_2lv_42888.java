package programmers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

//https://programmers.co.kr/learn/courses/30/lessons/42888
public class kakao_2lv_42888 {

	public static void main(String[] args) {
		String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
		
		PriorityQueue<Log> pq = new PriorityQueue<Log>();
		HashMap<String, String> nameMap = new HashMap<String, String>();
		
		int realSize = 0;
		for (int i = 0; i < record.length; i++) {
			String[] temp = record[i].split(" ");
			
			if (!"Leave".equals(temp[0]))
				nameMap.put(temp[1], temp[2]);
			
			if (!"Change".equals(temp[0]))
				realSize++;
			
			pq.add(new Log(i, temp[0], temp[1]));
		}

		String[] answer = new String[realSize];
		int i=0;
		while(!pq.isEmpty()) {
			Log log = pq.poll();
			if ("Change".equals(log.action))
				continue;
			answer[i++] = nameMap.get(log.uid) + log.action;
		}
		
		System.out.println(Arrays.toString(answer));
	}
	
	static class Log implements Comparable<Log>{
		int id;
		String action;
		String uid;
		
		public Log(int id, String action, String uid) {
			this.id = id;
			this.action = ("Enter".equals(action)?"님이 들어왔습니다.":
							("Leave".equals(action)?"님이 나갔습니다.":"Change"));
			this.uid = uid;
		}
		
		@Override
		public int compareTo(Log o) {
			if (this.id < o.id)
				return -1;
			else if (this.id > o.id)
				return 1;
			else
				return 0;
		}
	}
	
	
	//제출용
	static class Solution {
		static public String[] solution(String[] record) {
			PriorityQueue<Log> pq = new PriorityQueue<Log>();
			HashMap<String, String> nameMap = new HashMap<String, String>();
			
			int realSize = 0;
			for (int i = 0; i < record.length; i++) {
				String[] temp = record[i].split(" ");
				
				if (!"Leave".equals(temp[0]))
					nameMap.put(temp[1], temp[2]);
				
				if (!"Change".equals(temp[0]))
					realSize++;
				
				pq.add(new Log(i, temp[0], temp[1]));
			}

			String[] answer = new String[realSize];
			int i=0;
			while(!pq.isEmpty()) {
				Log log = pq.poll();
				if ("Change".equals(log.action))
					continue;
				answer[i++] = nameMap.get(log.uid) + log.action;
			}
			
			return answer;
		}
		
		static class Log implements Comparable<Log>{
			int id;
			String action;
			String uid;
			
			public Log(int id, String action, String uid) {
				this.id = id;
				this.action = ("Enter".equals(action)?"님이 들어왔습니다.":
								("Leave".equals(action)?"님이 나갔습니다.":"Change"));
				this.uid = uid;
			}
			
			@Override
			public int compareTo(Log o) {
				if (this.id < o.id)
					return -1;
				else if (this.id > o.id)
					return 1;
				else
					return 0;
			}
		}
	}
}
