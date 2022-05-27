package programmers;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

//https://programmers.co.kr/learn/courses/30/lessons/42889
public class kakao_1lv_42889 {

	public static void main(String[] args) {
		int[] stages = {1,1,1};
		int N = 3;
		
		int len = stages.length;		
		int[] status = new int[N + 1];

		PriorityQueue<double[]> pq = new PriorityQueue<double[]>(new Comparator<double[]>() {
			@Override
			public int compare(double[] o1, double[] o2) {
				if (o1[1] == o2[1]) {
					if (o1[0] < o2[0])
						return -1;
					else
						return 1;
				} else if (o1[1] < o2[1])
					return 1;
				else
					return -1;
			}
		});

		for (int stgNum : stages) {
			if (stgNum > N)
				continue;				
			status[stgNum]++;
		}

		int pastCount = 0;
		for (int i = 1; i < status.length; i++) {
			int count = status[i];
			double m = len - pastCount;
			pastCount += count;

			double r = count / m;
			if (count==0)
				r = 0;
			pq.add(new double[] {i, r});
		}

		int answer[] = new int[pq.size()];
		int i = 0;
		while (!pq.isEmpty())
			answer[i++] = (int) (pq.poll()[0]);

		System.out.println(Arrays.toString(answer));
	}

	// 제출용
	class Solution {
		public int[] solution(int N, int[] stages) {
			int len = stages.length;		
			int[] status = new int[N + 1];

			PriorityQueue<double[]> pq = new PriorityQueue<double[]>(new Comparator<double[]>() {
				@Override
				public int compare(double[] o1, double[] o2) {
					if (o1[1] == o2[1]) {
						if (o1[0] < o2[0])
							return -1;
						else
							return 1;
					} else if (o1[1] < o2[1])
						return 1;
					else
						return -1;					
				}
			});

			for (int stgNum : stages) {
				if (stgNum > N)
					continue;
				status[stgNum]++;
			}

			int pastCount = 0;
			for (int i = 1; i < status.length; i++) {
				int count = status[i];
				double m = len - pastCount;
				pastCount += count;

				double r = count / m;
				if (count==0)
					r = 0;
				pq.add(new double[] {i, r});
			}

			int answer[] = new int[pq.size()];
			int i = 0;
			while (!pq.isEmpty())
				answer[i++] = (int) (pq.poll()[0]);

			return answer;
		}
	}
}
