package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

//https://programmers.co.kr/learn/courses/30/lessons/64065
public class kakao_2lv_64065 {

	public static void main(String[] args) {
		String s = "{{20,111},{111}}";

		String[] temp = s.split("\\}\\,\\{");
		HashMap<Integer, Integer> visited = new HashMap<Integer, Integer>();
		PriorityQueue<A> pq = new PriorityQueue<A>();
		List<Integer> orderedList = new ArrayList<Integer>();

		for (int i = 0; i < temp.length; i++)
			temp[i] = temp[i].replaceAll("[^0-9\\,]", "");

		for (int i = 0; i < temp.length; i++)
			pq.add(new A(temp[i].split(",")));

		while (!pq.isEmpty()) {
			A a = pq.poll();

			for (String str : a.arr) {
				int key = Integer.parseInt(str);
				if (visited.containsKey(key))
					continue;

				visited.put(key, null);
				orderedList.add(key);
			}
		}

		System.out.println(Arrays.toString(orderedList.toArray()));
	}

	static class A implements Comparable {
		String[] arr;
		int length;

		public A(String[] arr) {
			this.arr = arr;

			if (arr != null)
				this.length = arr.length;
			else
				this.length = 0;
		}

		@Override
		public int compareTo(Object o) {
			A other = (A) o;
			if (this.length < other.length)
				return -1;
			else if (this.length == other.length)
				return 0;
			else
				return 1;
		}
	}

	// 제출용
	class Solution {
		public int[] solution(String s) {
			String[] temp = s.split("\\}\\,\\{");
			HashMap<Integer, Integer> visited = new HashMap<Integer, Integer>();
			PriorityQueue<A> pq = new PriorityQueue<A>();
			List<Integer> orderedList = new ArrayList<Integer>();

			for (int i = 0; i < temp.length; i++)
				temp[i] = temp[i].replaceAll("[^0-9\\,]", "");

			for (int i = 0; i < temp.length; i++)
				pq.add(new A(temp[i].split(",")));

			while (!pq.isEmpty()) {
				A a = pq.poll();

				for (String str : a.arr) {
					int key = Integer.parseInt(str);
					if (visited.containsKey(key))
						continue;

					visited.put(key, null);
					orderedList.add(key);
				}
			}

			int[] answer = new int[orderedList.size()];
			int idx = 0;
			for (int i : orderedList)
				answer[idx++] = i;

			return answer;
		}

		static class A implements Comparable {
			String[] arr;
			int length;

			public A(String[] arr) {
				this.arr = arr;

				if (arr != null)
					this.length = arr.length;
				else
					this.length = 0;
			}

			@Override
			public int compareTo(Object o) {
				A other = (A) o;
				if (this.length < other.length)
					return -1;
				else if (this.length == other.length)
					return 0;
				else
					return 1;
			}
		}

		// 다른사람 풀이
		public int[] solution2(String s) {
			Set<String> set = new HashSet<>();
			String[] arr = s.replaceAll("[{]", " ").replaceAll("[}]", " ").trim().split(" , ");
			
			Arrays.sort(arr, (a, b) -> {
				return a.length() - b.length();
			});
			
			int[] answer = new int[arr.length];
			int idx = 0;
			for (String s1 : arr) {
				for (String s2 : s1.split(",")) {
					if (set.add(s2))
						answer[idx++] = Integer.parseInt(s2);
				}
			}
			return answer;
		}
	}
}
