package programmers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

//this is from feature8
//https://programmers.co.kr/learn/courses/30/lessons/92341
public class kakao_2lv_92341 {

	static int baseTime;
	static int baseFee;
	static int extraTime;
	static int extraFee;
	
	public static void main(String[] args) throws Exception{
		
		int[] fees = {180, 5000, 10, 600};
		String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
		
		baseTime = fees[0];
		baseFee = fees[1];
		extraTime = fees[2];
		extraFee = fees[3];

		HashMap<String, Deque<HashMap<String, String>>> map = new HashMap<String, Deque<HashMap<String, String>>>();
		
		for (int i = 0; i < records.length; i++) {
			String[] temp = records[i].split(" ");
			
			String time = temp[0];
			String num = temp[1];
			String type = temp[2];
			
			map.put(num, map.getOrDefault(num, new ArrayDeque<HashMap<String, String>>()));
			Deque<HashMap<String, String>> q = map.get(num);
			if (q.peekLast()==null)
				q.addLast(new HashMap<String, String>());

			if (q.peekLast().get(type)!=null) {
				q.addLast(new HashMap<String, String>());
				q.peekLast().put(type, time);
			}
			else
				q.peekLast().put(type, time);
		}
		
		List<Car> list = new ArrayList<Car>();
		Iterator<String> it = map.keySet().iterator();
		while(it.hasNext()) {
			String num = it.next();
			Car c = new Car(num);
			
			Deque<HashMap<String, String>> times = map.get(num);
			while(!times.isEmpty()) {
				HashMap<String, String> temp = times.pollFirst();
				String inTime = temp.get("IN");
				String outTime = temp.get("OUT");
				if (outTime==null)
					outTime = "";
				
				String[] t = {inTime, outTime};
				c.inAndOuts.add(t);
			}
			
			c.setMins();
			c.setFees();
			list.add(c);
		}
		
		Collections.sort(list);
		
		int[] answer = new int[list.size()];
		for (int i = 0; i < answer.length; i++)
			answer[i] = list.get(i).fees;

		System.out.println(Arrays.toString(answer));
	}
	
	static class Car implements Comparable<Car>{
		List<String[]> inAndOuts;
		String num;
		int mins;
		int fees;
		
		public Car(String num) {
			this.inAndOuts = new ArrayList<String[]>();
			this.num = num;
			this.mins = 0;
			this.fees = 0;
		}
		
		public void setMins() {
			
			for (int i = 0; i < this.inAndOuts.size(); i++) {
				String[] temp = this.inAndOuts.get(i);
				String in = temp[0];
				String out = "23:59";
				
				if (temp.length>1 && !"".equals(temp[1]))
					out = temp[1];
				
				try {
					SimpleDateFormat f = new SimpleDateFormat("HH:mm", Locale.KOREA);
					Date d1 = f.parse(in);
					Date d2 = f.parse(out);
					long diff = d2.getTime() - d1.getTime();
					this.mins += Integer.parseInt(Long.toString(Math.abs(diff / 1000 / 60)));						
				}
				catch(ParseException e) {
					e.printStackTrace();
				}	
			}
			
		}
		
		public void setFees() {
			if (this.mins <= baseTime) {
				this.fees = baseFee;
				return;
			}
			
			int x = this.mins - baseTime;
			if (x % extraTime != 0)
				x = ((x/extraTime)+1)*extraTime;
			
			this.fees = (int)(baseFee + Math.ceil(x / extraTime)*extraFee);
		}
		
		@Override
		public int compareTo(Car o) {
			return this.num.compareTo(o.num);
		}
	}

	//제출용
	class Solution {
		static int baseTime;
		static int baseFee;
		static int extraTime;
		static int extraFee;
		
	    public int[] solution(int[] fees, String[] records) {

	    	baseTime = fees[0];
			baseFee = fees[1];
			extraTime = fees[2];
			extraFee = fees[3];

			HashMap<String, Deque<HashMap<String, String>>> map = new HashMap<String, Deque<HashMap<String, String>>>();
			
			for (int i = 0; i < records.length; i++) {
				String[] temp = records[i].split(" ");
				
				String time = temp[0];
				String num = temp[1];
				String type = temp[2];
				
				map.put(num, map.getOrDefault(num, new ArrayDeque<HashMap<String, String>>()));
				Deque<HashMap<String, String>> q = map.get(num);
				if (q.peekLast()==null)
					q.addLast(new HashMap<String, String>());

				if (q.peekLast().get(type)!=null) {
					q.addLast(new HashMap<String, String>());
					q.peekLast().put(type, time);
				}
				else
					q.peekLast().put(type, time);
			}
			
			List<Car> list = new ArrayList<Car>();
			Iterator<String> it = map.keySet().iterator();
			while(it.hasNext()) {
				String num = it.next();
				Car c = new Car(num);
				
				Deque<HashMap<String, String>> times = map.get(num);
				while(!times.isEmpty()) {
					HashMap<String, String> temp = times.pollFirst();
					String inTime = temp.get("IN");
					String outTime = temp.get("OUT");
					if (outTime==null)
						outTime = "";
					
					String[] t = {inTime, outTime};
					c.inAndOuts.add(t);
				}
				
				c.setMins();
				c.setFees();
				list.add(c);
			}
			
			Collections.sort(list);
			
			int[] answer = new int[list.size()];
			for (int i = 0; i < answer.length; i++)
				answer[i] = list.get(i).fees;
	    	
	        return answer;
	    }
	    
	    static class Car implements Comparable<Car>{
			List<String[]> inAndOuts;
			String num;
			int mins;
			int fees;
			
			public Car(String num) {
				this.inAndOuts = new ArrayList<String[]>();
				this.num = num;
				this.mins = 0;
				this.fees = 0;
			}
			
			public void setMins() {
				
				for (int i = 0; i < this.inAndOuts.size(); i++) {
					String[] temp = this.inAndOuts.get(i);
					String in = temp[0];
					String out = "23:59";
					
					if (temp.length>1 && !"".equals(temp[1]))
						out = temp[1];
					
					try {
						SimpleDateFormat f = new SimpleDateFormat("HH:mm", Locale.KOREA);
						Date d1 = f.parse(in);
						Date d2 = f.parse(out);
						long diff = d2.getTime() - d1.getTime();
						this.mins += Integer.parseInt(Long.toString(Math.abs(diff / 1000 / 60)));						
					}
					catch(ParseException e) {
						e.printStackTrace();
					}	
				}
				
			}
			
			public void setFees() {
				if (this.mins <= baseTime) {
					this.fees = baseFee;
					return;
				}
				
				int x = this.mins - baseTime;
				if (x % extraTime != 0)
					x = ((x/extraTime)+1)*extraTime;
				
				this.fees = (int)(baseFee + Math.ceil(x / extraTime)*extraFee);
			}
			
			@Override
			public int compareTo(Car o) {
				return this.num.compareTo(o.num);
			}
		}
	}
}
