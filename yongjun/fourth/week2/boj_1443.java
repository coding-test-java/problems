package fourth.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1443 {
	private static int d, p;
	private static long maxNumber = 1;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		d = Integer.parseInt(st.nextToken());
		p = Integer.parseInt(st.nextToken());

		if (p == 0) {
			System.out.println(1);
			return;
		}

		backTracking(1, 0, 9);
		if (maxNumber == 1) {
			maxNumber = -1;
		}
		System.out.println(maxNumber);
	}

	private static void backTracking(long number, int depth, int start) {
		if ((number + "").length() > d) {
			return;
		}
		if (depth == p) {
			maxNumber = Math.max(maxNumber, number);
			return;
		}

		for (int i = start; i >= 2; i--) {
			backTracking(number * i, depth + 1, i);
		}
	}
}
