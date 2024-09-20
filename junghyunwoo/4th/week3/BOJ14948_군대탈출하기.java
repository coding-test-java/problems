import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [BOJ] 군대탈출하기 / Gold 2 / 25m
 * - 132 ms
 * - 이분 탐색 + DFS
 * - map 1 차원 변환
 * - 한 줄은 점프 가능하므로 사방에 벽 두 줄 세우기
 * - 탈출 가능한 최소 레벨 -> lowerBound
 * - 이분 탐색 해당 레벨로 탈출 가능한지 검증
 * - 점프 하지 않았고 레벨 제한에 걸리면 점프해서 탐색
 * - 이미 점프 했거나 레벨 제한에 걸리지 않으면 다음 칸 탐색
 * - DFS로 목적지에 도달할 때까지 확인
 * */
public class BOJ14948_군대탈출하기 {
    private static final int INF = Integer.MAX_VALUE;

    private static int level;
    private static int start;
    private static int dest;
    private static int end;
    private static int jumped;
    private static int[] map;
    private static int[] d;
    private static boolean[][] visited;

    private static boolean dfs(int pos) {
        int i;
        int npos;

        if (visited[pos][jumped]) { // 이미 방문한 노드
            return false;
        }
        visited[pos][jumped] = true; // 방문 처리
        if (map[pos] > level) { // 레벨 제한에 걸림
            return false;
        }
        if (pos == dest) { // 목적지 도달
            return true;
        }
        for (i = 0; i < 4; i++) { // 4방 탐색
            npos = pos + d[i];
            if (jumped == 0 && map[npos] > level) { // 점프 하지 않았고 레벨 제한에 걸림
                jumped++; // 점프
                if (dfs(npos + d[i])) { // 점프한 위치부터 DFS로 도착 가능한지 확인
                    jumped--; // 점프 초기화
                    return true; // 목적지 도착 가능
                }
                jumped--; // 점프 초기화
            } else { // 이미 점프 했거나 레벨 제한에 걸리지 않으면
                if (dfs(npos)) { // 다음 칸부터 DFS로 도착 가능한지 확인
                    return true; // 목적지 도착 가능
                }
            }
        }
        return false; // 목적지 도착 불가
    }

    private static int lowerBound(int right) {
        int left;

        jumped = 0;
        left = Math.max(map[start], map[dest]); // 출발지와 목적지 레벨 이상이어야 함
        while (left < right) { // 이분 탐색
            level = left + right >> 1;
            visited = new boolean[end][2]; // 방문 배열 초기화
            if (dfs(start)) { // 목적지 도착 가능
                right = level; // 레벨 다운
            } else { // 목적지 도착 불가능
                left = level + 1; // 레벨 업
            }
        }
        return right; // 목적지 도착 가능한 최소 레벨 (lowerBound)
    }

    public static void main(String[] args) throws IOException {
        int n;
        int m;
        int i;
        int j;
        int max;
        int size;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        n = Integer.parseInt(st.nextToken()) + 1;
        m = Integer.parseInt(st.nextToken()) + 1;
        size = m + 3;
        start = 2 * size + 2;
        dest = n * size + m;
        end = (n + 3) * size;
        map = new int[end]; /// map 1 차원 변환
        d = new int[] {-size, 1, size, -1};
        for (i = 2; i <= m; i++) { // 가장 윗줄 벽 세우기
            map[i] = INF;
        }
        System.arraycopy(map, 2, map, size + 2, m - 1); // 위에서 두 번째 줄 벽 세우기
        System.arraycopy(map, 2, map, (n + 1) * size + 2, (size << 1) - 4); // 아래쪽 두 줄 벽 세우기
        max = 0;
        for (i = 2 * size; i <= dest; i += size) {
            map[i] = INF; // 가장 왼쪽 벽 세우기
            map[i + 1] = INF; // 왼쪽에서 두 번째 벽 세우기
            st = new StringTokenizer(br.readLine(), " ", false);
            for (j = 2; j <= m; j++) {
                map[i + j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[i + j]); // 블록 최대 레벨
            }
            map[i + m + 1] = INF; // 오른쪽에서 두 번째 벽 세우기
            map[i + m + 2] = INF; // 가장 오른쪽 벽 세우기
        }
        System.out.print(lowerBound(max)); // 탈출 가능한 최소 레벨 -> lowerBound
    }
}
