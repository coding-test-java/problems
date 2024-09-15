package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1443 {

	static int maxTarget;
	static int operationCnt;
	static int result = -1;

	public static void main(String[] args) throws IOException {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(buffer.readLine());

		int power = Integer.parseInt(token.nextToken());
		maxTarget = (int)Math.pow(10, power);
		operationCnt = Integer.parseInt(token.nextToken());

		if (operationCnt == 0) {
			System.out.println(1);
			return;
		}

		backTracking(0, 1, 9);
		System.out.println(result);
	}

	static void backTracking(int cnt, int currNum, int maxMultiplier) {
		if (cnt == operationCnt) {
			result = Math.max(currNum, result);
			return;
		}

		for (int i = maxMultiplier; i >= 2; i--) {
			long nextNum = (long)currNum * i;
			if (nextNum >= maxTarget) continue;
			if (nextNum * Math.pow(9, operationCnt - cnt - 1) <= result) break; // 최선의 결과가 현재 결과보다 작으면 중지
			backTracking(cnt + 1, (int)nextNum, i); // maxMultiplier값을 다르게 전달하여 4*3, 3*4와 같은 중복연산 방지
		}
	}
}
