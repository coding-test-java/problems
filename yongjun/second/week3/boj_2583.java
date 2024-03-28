package second.week3;

import java.io.*;
import java.util.*;

public class boj_2583 {
    static boolean[][] array;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int m, n;
    static ArrayList<Integer> results = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        array = new boolean[m][n];
        visited = new boolean[m][n];
        while (k-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            paintArray(x1, y1, x2, y2);
        }
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!array[i][j] && !visited[i][j]) {
                    bfs(i, j);
                    cnt++;
                }
            }
        }
        sb.append(cnt).append("\n");
        Collections.sort(results);
        for (int result : results) {
            sb.append(result).append(" ");
        }
        System.out.println(sb);
    }

    static void paintArray(int x1, int y1, int x2, int y2) {
        for (int i = y1; i < y2; i++) {
            for (int j = x1; j < x2; j++) {
                array[i][j] = true;
            }
        }
    }

    static class Node {
        int x;
        int y;

        public Node(int y, int x) {
            this.x = x;
            this.y = y;
        }
    }

    static void bfs(int y, int x) {
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(new Node(y, x));
        visited[y][x] = true;

        int size = 1;
        while (!nodes.isEmpty()) {
            Node node = nodes.poll();

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                if (isOnBoard(ny, nx) && !visited[ny][nx] && !array[ny][nx]) {
                    nodes.add(new Node(ny, nx));
                    visited[ny][nx] = true;
                    size++;
                }
            }
        }
        results.add(size);
    }

    static boolean isOnBoard(int y, int x) {
        return y >= 0 && x >= 0 && y < m && x < n;
    }
}