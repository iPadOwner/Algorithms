package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//https://programmers.co.kr/learn/courses/30/lessons/17686
public class kakao_2lv_17686 {

	public static void main(String[] args) {
		String[] files = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};

		String pattern = "[0-9]+";
		List<File> list = new ArrayList<File>();
		for (int i = 0; i < files.length; i++) {
			Pattern p = Pattern.compile(pattern);
			String target = files[i];
			target = target.toUpperCase();
			Matcher m = p.matcher(target);
			
			if (m.find()) {
				String number = m.group();
				int num = Integer.parseInt(number);
				
				int numberStartIdx = target.indexOf(number);
				String head = target.substring(0, numberStartIdx);
				
				int numberEndIdx = numberStartIdx + number.length() - 1;
				String tail = target.substring(numberEndIdx);
				
				File f = new File(head, number, tail, num, files[i]);
				list.add(f);
			}
		}
		
		list.sort(Comparator.comparing(File::getHead).thenComparing(Comparator.naturalOrder()));
		String[] answer =  new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			StringBuffer sb = new StringBuffer();
			answer[i] = list.get(i).getOrigin();
		}
		
		System.out.println(Arrays.toString(answer));
		
	}
	
	static class File implements Comparable<File>{
		String head;
		String number;
		String tail;
		String origin;
		int num;
		
		public File(String head, String number, String tail, int num, String origin) {
			this.head = head;
			this.number = number;
			this.tail = tail;
			this.num = num;
			this.origin = origin;
		}
		
		public String getHead() {
			return this.head;
		}
		
		public String getNumber() {
			return this.number;
		}
		
		public String getTail() {
			return this.tail;
		}
		
		public String getOrigin() {
			return this.origin;
		}
		
		@Override
		public int compareTo(File o) {
			return this.num-o.num;
		}
	
	}

	//제출용
	class Solution {
	    public String[] solution(String[] files) {
	        String pattern = "[0-9]+";
			List<File> list = new ArrayList<File>();
			for (int i = 0; i < files.length; i++) {
				Pattern p = Pattern.compile(pattern);
				String target = files[i];
				target = target.toUpperCase();
				Matcher m = p.matcher(target);
				
				if (m.find()) {
					String number = m.group();
					int num = Integer.parseInt(number);
					
					int numberStartIdx = target.indexOf(number);
					String head = target.substring(0, numberStartIdx);
					
					int numberEndIdx = numberStartIdx + number.length() - 1;
					String tail = target.substring(numberEndIdx);
					
					File f = new File(head, number, tail, num, files[i]);
					list.add(f);
				}
			}
			
			list.sort(Comparator.comparing(File::getHead).thenComparing(Comparator.naturalOrder()));
			String[] answer =  new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				StringBuffer sb = new StringBuffer();
				answer[i] = list.get(i).getOrigin();
			}
			
	        return answer;
	    }
	    
	    static class File implements Comparable<File>{
			String head;
			String number;
			String tail;
			String origin;
			int num;
			
			public File(String head, String number, String tail, int num, String origin) {
				this.head = head;
				this.number = number;
				this.tail = tail;
				this.num = num;
				this.origin = origin;
			}
			
			public String getHead() {
				return this.head;
			}
			
			public String getNumber() {
				return this.number;
			}
			
			public String getTail() {
				return this.tail;
			}
			
			public String getOrigin() {
				return this.origin;
			}
			
			@Override
			public int compareTo(File o) {
				return this.num-o.num;
			}
		
		}
	}
}
