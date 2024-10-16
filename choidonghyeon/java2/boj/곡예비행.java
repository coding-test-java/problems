import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class 곡예비행 {
	static int[][] upper = {{0, 1}, {-1, 0}};
	static int[][] lower = {{0, -1}, {-1, 0}};
	static int[][] board;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new int[N][M];
		int[][] dpUpper = new int[N][M];
		int[][] dpLower = new int[N][M];

		for (int i = 0; i < N; i++) {
			Arrays.fill(dpUpper[i], Integer.MIN_VALUE);
			Arrays.fill(dpLower[i], Integer.MIN_VALUE);
		}

		for (int i = 0; i < N; i++) {
			board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		check(N - 1, 0, dpUpper, upper);
		check(N - 1, M - 1, dpLower, lower);

		int answer = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				answer = Math.max(answer, dpUpper[i][j] + dpLower[i][j]);
			}
		}
		System.out.println(answer);
	}

	private static void check(int y, int x, int[][] dp, int[][] moves) {
		boolean[][] visited = new boolean[N][M];
		Queue<int[]> queue = new ArrayDeque<>();

		visited[y][x] = true;
		dp[y][x] = board[y][x];
		queue.offer(new int[] {y, x});
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int cy = curr[0];
			int cx = curr[1];
			for (int[] move : moves) {
				int my = cy + move[0];
				int mx = cx + move[1];

				if (0 <= my && my < N && 0 <= mx && mx < M) {
					if (!visited[my][mx]) {
						queue.add(new int[] {my, mx});
						visited[my][mx] = true;
					}

					if (dp[my][mx] < dp[cy][cx] + board[my][mx]) {
						dp[my][mx] = dp[cy][cx] + board[my][mx];
					}
				}
			}
		}
	}
}
