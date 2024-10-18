import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static long[][] grid, dpUp, dpDown;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		grid = new long[N][M];
		dpUp = new long[N][M];
		dpDown = new long[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				grid[i][j] = Long.parseLong(st.nextToken());
				dpUp[i][j] = Long.MIN_VALUE;
				dpDown[i][j] = Long.MIN_VALUE;
			}
		}

		setDpUp();
		setDpDown();

		long maxScore = dpUp[0][M-1] + dpDown[0][M-1];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				maxScore = Math.max(maxScore, dpUp[i][j] + dpDown[i][j]);
			}
		}

		System.out.println(maxScore);
	}

	static void setDpUp() {
		dpUp[N-1][0] = grid[N-1][0];
		for (int i = N - 1; i >= 0; i--) {
			for (int j = 0; j < M; j++) {
				if (i == N - 1 && j == 0) continue;
				long fromBottom = (i < N-1) ? dpUp[i+1][j] : Long.MIN_VALUE;
				long fromLeft = (j > 0) ? dpUp[i][j-1] : Long.MIN_VALUE;
				dpUp[i][j] = Math.max(fromBottom, fromLeft) + grid[i][j];
			}
		}
	}

	static void setDpDown() {
		dpDown[N-1][M-1] = grid[N-1][M-1];
		for (int i = N - 1; i >= 0; i--) {
			for (int j = M - 1; j >= 0; j--) {
				if (i == N - 1 && j == M - 1) continue;
				long fromBottom = (i < N-1) ? dpDown[i+1][j] : Long.MIN_VALUE;
				long fromRight = (j < M-1) ? dpDown[i][j+1] : Long.MIN_VALUE;
				dpDown[i][j] = Math.max(fromBottom, fromRight) + grid[i][j];
			}
		}
	}
}