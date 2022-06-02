package programmers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

//https://programmers.co.kr/learn/courses/30/lessons/92334

/*

입출력 예

입력 : ["muzi", "frodo", "apeach", "neo"], ["muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"], 2

출력 : [2,1,1,0]


 

입력 : ["con", "ryan"], ["ryan con", "ryan con", "ryan con", "ryan con"], 3

출력 : [0,0]

 */

public class kakao_1lv_92334 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String inputString = br.readLine();

		String[] inputArr = inputString.split("], ");
		String idListStr = inputArr[0];
		String reportStr = inputArr[1];
		int k = Integer.valueOf(inputArr[2]);

		idListStr = idListStr.replaceAll("\\[", "").replaceAll("\"", "").replaceAll(" ", "");
		reportStr = reportStr.replaceAll("\\[", "").replaceAll("\"", "").replaceAll(", ", ",");

		String[] idListArr = idListStr.split(",");
		String[] reportArr = reportStr.split(",");
		int[] resultArr = new int[idListArr.length];

		HashMap<String, HashMap<String, Integer>> calcMap = new HashMap<String, HashMap<String, Integer>>();
		HashMap<String, Integer> restrictedMap = new HashMap<String, Integer>();

		for (int i = 0; i < idListArr.length; i++) {
			calcMap.put(idListArr[i], new HashMap<String, Integer>());
		}

		for (int i = 0; i < reportArr.length; i++) {
			String[] temp = reportArr[i].split(" ");
			String from = temp[0];
			String to = temp[1];

			HashMap<String, Integer> tempMap = calcMap.get(from);
			if (!tempMap.containsKey(to)) {
				tempMap.put(to, 0);

				if (!restrictedMap.containsKey(to)) {
					restrictedMap.put(to, 1);
				}

				else {
					restrictedMap.put(to, restrictedMap.get(to) + 1);
				}
			}

			((HashMap<String, Integer>) calcMap.get(from)).put(to, 0);
		}

		for (int i = 0; i < idListArr.length; i++) {
			HashMap<String, Integer> tempMap = calcMap.get(idListArr[i]);
			Iterator it = tempMap.keySet().iterator();

			while (it.hasNext()) {
				String target = (String) it.next();
				if (restrictedMap.containsKey(target) && restrictedMap.get(target) >= k) {
					resultArr[i]++;
				}
			}
		}

		bw.write(Arrays.toString(resultArr));
		bw.newLine();

		br.close();
		bw.close();		
	}

	// 제출용
	static class Solution {

		// 속도는 이게 더 빠름
		public static int[] solution(String[] id_list, String[] report, int k) {

			int[] resultArr = new int[id_list.length];

			HashMap<String, HashMap<String, Integer>> calcMap = new HashMap<String, HashMap<String, Integer>>();
			HashMap<String, Integer> restrictedMap = new HashMap<String, Integer>();

			for (int i = 0; i < id_list.length; i++) {
				calcMap.put(id_list[i], new HashMap<String, Integer>());
			}

			for (int i = 0; i < report.length; i++) {
				String[] temp = report[i].split(" ");
				String from = temp[0];
				String to = temp[1];

				HashMap<String, Integer> tempMap = calcMap.get(from);
				
				//여기서 중복제거
				if (!tempMap.containsKey(to)) {
					tempMap.put(to, 0);

					if (!restrictedMap.containsKey(to)) {
						restrictedMap.put(to, 1);
					}
					else {
						restrictedMap.put(to, restrictedMap.get(to) + 1);
					}
				}

				((HashMap<String, Integer>) calcMap.get(from)).put(to, 0);
			}

			for (int i = 0; i < id_list.length; i++) {
				HashMap<String, Integer> tempMap = calcMap.get(id_list[i]);
				Iterator it = tempMap.keySet().iterator();

				while (it.hasNext()) {
					String target = (String) it.next();
					if (restrictedMap.containsKey(target) && restrictedMap.get(target) >= k) {
						resultArr[i]++;
					}
				}
			}

			return resultArr;
		}

		
		//id_list : ["con", "ryan"]
		//report : ["ryan con", "ryan con", "ryan con", "ryan con"]
		//k : 3
		public static int[] solution2(String[] id_list, String[] report, int k) {
			List<String> reportList = Arrays.stream(report)
											.distinct()//여기서 중복제거
											.collect(Collectors.toList());
			/*reportList : ryan con*/
			HashMap<String, Integer> count = new HashMap<>();
			for (String s : reportList) {
				String target = s.split(" ")[1];
				count.put(target, count.getOrDefault(target, 0) + 1);
			}
			/*count : con 1 */
			
			return Arrays.stream(id_list).map(id -> {
				List<String> returnList = reportList.stream()
										.filter(s -> s.startsWith(id + " "))
										.collect(Collectors.toList());
				/*returnList :   ryan con
				 */
				return returnList.stream()
								.filter(s -> count.getOrDefault(s.split(" ")[1], 0) >= k)
								.count();
			}).mapToInt(Long::intValue).toArray();
		}
	}
}