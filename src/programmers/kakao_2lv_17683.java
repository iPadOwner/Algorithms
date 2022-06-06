package programmers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.PriorityQueue;

public class kakao_2lv_17683 {

	public static void main(String[] args) throws ParseException {
		String m = "ABC";
		String[] musicinfos = {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"};

		/*C# -> Z
		  D# -> Y
		  F# -> X
		  G# -> T
		  A# -> Q
		*/
		
		String answer = "";
		m = m.replaceAll("C\\#", "Z").replaceAll("D\\#", "Y").replaceAll("F\\#", "X")
				 .replaceAll("G\\#", "T").replaceAll("A\\#", "Q");
		PriorityQueue<PlayedMusic> pq = new PriorityQueue<PlayedMusic>();
		for (int i = 0; i < musicinfos.length; i++) {
			String[] temp = musicinfos[i].split(",");
			temp[3] = temp[3].replaceAll("C\\#", "Z").replaceAll("D\\#", "Y").replaceAll("F\\#", "X")
							 .replaceAll("G\\#", "T").replaceAll("A\\#", "Q");
			pq.add(new PlayedMusic(temp[0], temp[1], temp[2], temp[3]));
		}
		
		while(!pq.isEmpty()) {
			PlayedMusic pm = pq.poll();
			
			if (pm.realPlayedNote.contains(m)) {
				answer = pm.subject;
				break;
			}
		}
		
		if ("".equals(answer))
			answer = "(None)";

		System.out.println(answer);
	}

	static class PlayedMusic implements Comparable {
		int seq;
		String begin;
		String end;
		String subject;
		String note;
		long length;
		String realPlayedNote;

		public PlayedMusic(String begin, String end, String subject, String note) {
			this.begin = begin;
			this.end = end;
			this.subject = subject;
			this.note = note;
			
			setPlayLength();
			setPlayNote();
		}
		
		void setPlayLength() {
			try {
				SimpleDateFormat f = new SimpleDateFormat("HH:mm", Locale.KOREA);
				Date d1 = f.parse(this.begin);
				Date d2 = f.parse(this.end);
				long diff = d1.getTime() - d2.getTime();
				this.length = Math.abs(diff / 1000 / 60);
			} catch (ParseException e) {				
				e.printStackTrace();
			}
		}
		
		void setPlayNote() {
			StringBuilder sb = new StringBuilder();
			char[] noteArr = this.note.toCharArray();
			int idx = 0;
			
			for(int i=0; i<this.length; i++) {
				sb.append(noteArr[idx++]);
				
				if(idx>=noteArr.length)
					idx = 0;
			}
			
			this.realPlayedNote = sb.toString();
		}

		@Override
		public int compareTo(Object o) {
			PlayedMusic other = (PlayedMusic)o;
		
			if (this.length > other.length)
				return -1;
			else if (this.length < other.length)
				return 1;
			else {
				if (this.seq < other.seq)
					return -1;
				else
					return 1;
			}
		}
	}

	// 제출용
	class Solution {
		public String solution(String m, String[] musicinfos) {
			/*C# -> Z
			  D# -> Y
			  F# -> X
			  G# -> T
			  A# -> Q
			*/
			
			String answer = "";
			m = m.replaceAll("C\\#", "Z").replaceAll("D\\#", "Y").replaceAll("F\\#", "X")
					 .replaceAll("G\\#", "T").replaceAll("A\\#", "Q");
			PriorityQueue<PlayedMusic> pq = new PriorityQueue<PlayedMusic>();
			for (int i = 0; i < musicinfos.length; i++) {
				String[] temp = musicinfos[i].split(",");
				temp[3] = temp[3].replaceAll("C\\#", "Z").replaceAll("D\\#", "Y").replaceAll("F\\#", "X")
								 .replaceAll("G\\#", "T").replaceAll("A\\#", "Q");
				pq.add(new PlayedMusic(temp[0], temp[1], temp[2], temp[3]));
			}
			
			while(!pq.isEmpty()) {
				PlayedMusic pm = pq.poll();
				
				if (pm.realPlayedNote.contains(m)) {
					answer = pm.subject;
					break;
				}
			}
			
			if ("".equals(answer))
				answer = "(None)";
			
			return answer;
		}
		
		static class PlayedMusic implements Comparable {
			int seq;
			String begin;
			String end;
			String subject;
			String note;
			long length;
			String realPlayedNote;

			public PlayedMusic(String begin, String end, String subject, String note) {
				this.begin = begin;
				this.end = end;
				this.subject = subject;
				this.note = note;
				
				setPlayLength();
				setPlayNote();
			}
			
			void setPlayLength() {
				try {
					SimpleDateFormat f = new SimpleDateFormat("HH:mm", Locale.KOREA);
					Date d1 = f.parse(this.begin);
					Date d2 = f.parse(this.end);
					long diff = d1.getTime() - d2.getTime();
					this.length = Math.abs(diff / 1000 / 60);
				} catch (ParseException e) {				
					e.printStackTrace();
				}
			}
			
			void setPlayNote() {
				StringBuilder sb = new StringBuilder();
				char[] noteArr = this.note.toCharArray();
				int idx = 0;
				
				for(int i=0; i<this.length; i++) {
					sb.append(noteArr[idx]);
					
					idx++;
					
					if(idx>=noteArr.length)
						idx = 0;
				}
				
				this.realPlayedNote = sb.toString();
			}

			@Override
			public int compareTo(Object o) {
				PlayedMusic other = (PlayedMusic)o;
			
				if (this.length > other.length)
					return -1;
				else if (this.length < other.length)
					return 1;
				else {
					if (this.seq < other.seq)
						return -1;
					else
						return 1;
				}
			}
		}
	}
}
