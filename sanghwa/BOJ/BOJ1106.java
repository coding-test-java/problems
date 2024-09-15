package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1106 {

	static long[] dp;
	static int[][] cities;
	static int target;
	static int COST = 0;
	static int VALUE = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(buffer.readLine());

		target = Integer.parseInt(token.nextToken());
		int cityNum = Integer.parseInt(token.nextToken());
		cities = new int[cityNum][2];

		for (int i = 0; i < cityNum; i++) {
			token = new StringTokenizer(buffer.readLine());
			int cost = Integer.parseInt(token.nextToken());
			int value = Integer.parseInt(token.nextToken());
			cities[i][COST] = cost;
			cities[i][VALUE] = value;
		}
		dp = new long[target + 100];
		Arrays.fill(dp, Long.MAX_VALUE);
		dp[0] = 0;

		doDp();
		long result = Long.MAX_VALUE;
		for (int i = target; i < dp.length; i++) {
			result = Math.min(result, dp[i]);
		}
		System.out.println(result);
	}

	static void doDp() {
		for (int i = 1; i < dp.length; i++) {
			for (int[] city : cities) {
				int value = city[VALUE];
				int cost = city[COST];
				if (i < value || dp[i - value] == Long.MAX_VALUE) {
					continue;
				}
				dp[i] = Math.min(dp[i], dp[i - value] + cost);
			}
		}
	}
}
