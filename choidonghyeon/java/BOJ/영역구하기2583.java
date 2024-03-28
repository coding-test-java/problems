import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 영역구하기2583 {
	static int[][] delta = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	static boolean[][] board, visited;
	static int M, N, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		board = new boolean[M][N];
		visited = new boolean[M][N];

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			fillBoard(x1, y1, x2, y2);
		}

		List<Integer> answer = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (!board[i][j] && !visited[i][j]) {
					visited[i][j] = true;
					answer.add(dfs(i, j));
				}
			}
		}

		//answer.sort(Integer::compareTo);  -> java 8 240ms
		Collections.sort(answer); //-> java 8 88ms
		System.out.println(answer.size());
		StringBuilder sb = new StringBuilder();

		for (Integer i : answer) {
			sb.append(i).append(" ");
		}
		System.out.println(sb);
	}

	private static Integer dfs(int y, int x) {
		int foundArea = 1;
		for (int[] d : delta) {
			int newY = d[0] + y;
			int newX = d[1] + x;

			if (0 <= newY && newY < M && 0 <= newX && newX < N && !board[newY][newX] && !visited[newY][newX]) {
				visited[newY][newX] = true;
				foundArea += dfs(newY, newX);
			}
		}
		return foundArea;
	}

	private static void fillBoard(int x1, int y1, int x2, int y2) {
		for (int i = x1; i < x2; i++) {
			for (int j = y1; j < y2; j++) {
				board[M - j - 1][i] = true;
			}
		}
	}
}
