package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Boj_2583 {
    private static int[][] board;
    private static int[][] direction = {
            {1, 0}, {0, 1}, {-1, 0}, {0, -1}
    };

    private static int n, m, k;
    private static boolean[][] visited;
    private static int count = 0;
    private static int area;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer> list = new ArrayList<>();
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[m][n];
        visited = new boolean[m][n];

        // board에 직사각형 영역 표시 = 1
        while (k-- > 0) {
            st = new StringTokenizer(br.readLine());
            int firstX = Integer.parseInt(st.nextToken());
            int firstY = Integer.parseInt(st.nextToken());
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());

            for (int i = firstY; i < endY; i++) {
                for (int j = firstX; j < endX; j++) {
                    board[i][j] = 1;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0 && !visited[i][j]) {
                    area = 1;
                    count++;
                    list.add(dfs(i, j));
                }
            }
        }

        Collections.sort(list);

        System.out.println(count);
        list.forEach(v -> System.out.print(v + " "));
    }

    private static int dfs(int y, int x) {
        visited[y][x] = true;
        for (int i = 0; i < direction.length; i++) {
            int nextY = direction[i][0] + y;
            int nextX = direction[i][1] + x;
            if (nextY >= 0 && nextY < m && nextX >= 0 && nextX < n
                    && !visited[nextY][nextX] && board[nextY][nextX] == 0) {
                area++;
                dfs(nextY, nextX);
            }
        }

        return area;
    }
}
