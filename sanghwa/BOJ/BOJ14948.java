package BOJ;

import java.io.*;
import java.util.*;

public class BOJ14948 {
	static int N, M;
	static int[][] map;
	static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

	static class Point {
		int x, y, maxLevel;
		boolean usedItem;

		Point(int x, int y, int maxLevel, boolean usedItem) {
			this.x = x;
			this.y = y;
			this.maxLevel = maxLevel;
			this.usedItem = usedItem;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		int left = 0, right = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				right = Math.max(right, map[i][j]);
			}
		}

		int result = binarySearch(left, right);
		System.out.println(result);
	}

	// 이분탐색
	static int binarySearch(int left, int right) {
		int result = right;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (canReach(mid)) {
				result = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return result;
	}

	// 문제생각의 전환: 레벨을 기준으로 도착할 수 있는지 판단한다. BFS
	static boolean canReach(int level) {
		Queue<Point> queue = new LinkedList<>();
		boolean[][][] visited = new boolean[N][M][2];
		queue.offer(new Point(0, 0, map[0][0], false));
		visited[0][0][0] = true;

		while (!queue.isEmpty()) {
			Point cur = queue.poll();

			if (cur.x == N - 1 && cur.y == M - 1) {
				return true;
			}

			for (int[] dir : dirs) {
				int nx = cur.x + dir[0];
				int ny = cur.y + dir[1];

				if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
					int newMaxLevel = Math.max(cur.maxLevel, map[nx][ny]);
					if (newMaxLevel <= level && !visited[nx][ny][cur.usedItem ? 1 : 0]) {
						queue.offer(new Point(nx, ny, newMaxLevel, cur.usedItem));
						visited[nx][ny][cur.usedItem ? 1 : 0] = true;
					}
				}
			}

			if (!cur.usedItem) {
				for (int[] dir : dirs) {
					int nx = cur.x + dir[0] * 2;
					int ny = cur.y + dir[1] * 2;

					if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny][1]) {
						int newMaxLevel = Math.max(cur.maxLevel, map[nx][ny]);
						if (newMaxLevel <= level) {
							queue.offer(new Point(nx, ny, newMaxLevel, true));
							visited[nx][ny][1] = true;
						}
					}
				}
			}
		}

		return false;
	}
}