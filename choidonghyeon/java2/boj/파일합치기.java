import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 파일합치기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();

			System.out.println(solve(N, arr));
		}
	}

	private static int solve(int n, int[] arr) {
		int[][] dp = new int[n][n];
		int[] sum = new int[n + 1];

		for (int i = 0; i < n; i++) {
			dp[i][i] = 0;
			sum[i + 1] = sum[i] + arr[i];
		}

		for (int i = 1; i < n; i++) {
			for (int j = 0; j + i < n; j++) {
				dp[j][j + i] = Integer.MAX_VALUE;
				for (int k = j; k < j + i; k++) {
					dp[j][j + i] = Math.min(dp[j][j + i], dp[j][k] + dp[k + 1][j + i] + sum[j + i + 1] - sum[j]);
				}
			}
		}
		return dp[0][n - 1];
	}
}
