package sCertyStudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class matrixMultiplication_fibonacci_solved1 {
	static HashMap<Long, Long> d = new HashMap<Long, Long>();
	static final long mod = 1000000007;

	static long fibo(long n) {
		if (n <= 0)
			return 0;
		else if (n == 1 || n == 2)
			return 1;
		else if (d.containsKey(n)) {
			return d.get(n);
		} else {
			if (n % 2 == 1) {
				long m = (n + 1) / 2;
				long t1 = fibo(m);
				long t2 = fibo(m - 1);
				d.put(n, (t1 * t1 + t2 * t2) % mod);
				return d.get(n);
			} else {
				long m = n / 2;
				long t1 = fibo(m - 1);
				long t2 = fibo(m);
				d.put(n, ((2 * t1 + t2) * t2) % mod);
				return d.get(n);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int tc = Integer.parseInt(br.readLine());

		for (int tn = 1; tn <= tc; tn++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			long n = Long.parseLong(st.nextToken());
			long res = fibo(n);
			bw.write("#" + tn + " " + res);
			bw.newLine();
		}
		
		bw.flush();
		br.close();
		bw.close();
	}
}
