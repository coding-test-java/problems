import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(buffer.readLine());
		StringBuilder sb = new StringBuilder();

		int testNum = Integer.parseInt(token.nextToken());

		while (testNum-- > 0) {
			token = new StringTokenizer(buffer.readLine());
			int fileNum = Integer.parseInt(token.nextToken());
			int[] file = new int[fileNum + 1];
			int[] sum = new int[fileNum + 1];
			int[][] dp = new int[fileNum + 1][fileNum + 1];

			token = new StringTokenizer(buffer.readLine());
			for (int i = 1; i <= fileNum; i++) {
				int cost = Integer.parseInt(token.nextToken());
				file[i] = cost;
				sum[i] = sum[i- 1] + cost;
			}

			for (int i = 1; i < fileNum; i++) {
				dp[i][i + 1] = file[i] + file[i + 1];
			}

			for (int k = 2; k < fileNum; k++) {
				for (int i = 1; i <= fileNum - k ; i++) {
					int j = i + k;
					dp[i][j] = Integer.MAX_VALUE;
					for (int p = i; p < j; p++) {
						dp[i][j] = Math.min(dp[i][j], dp[i][p] + dp[p + 1][j] + sum[j] - sum[i - 1]);
					}
				}
			}
			sb.append(dp[1][fileNum]).append("\n");
		}
		System.out.print(sb);
	}
}