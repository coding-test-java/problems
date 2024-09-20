import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [BOJ] 망가진 계산기 / Gold 2 / 20m
 * - 68 ms
 * - DFS
 * - DFS(연산 횟수, 이전에 곱한 값, 계산 결과)
 * - 이전에 곱한 수 이하의 수들을 곱해보면서 DFS
 * - 자릿수 초과 시 return
 * - 연산 횟수 도달 시 최대값 갱신 후 return
 * */
public class BOJ1443_망가진계산기 {
    private static final int FAIL = -1;

    private static int p;
    private static int thr;
    private static int max;

    private static void dfs(int depth, int prev, int num) {
        if (num > thr) { // 자릿수 초과
            return;
        }
        if (depth == p) { // 연산 횟수 도달
            if (num > max) { // 최대값 갱신
                max = num;
            }
            return;
        }
        depth++; // 연산 횟수 1 증가
        for (; prev > 1; prev--) { // 이전에 곱한 수 이하의 수들을 곱해보면서 DFS
            dfs(depth, prev, num * prev);
        }
    }

    public static void main(String[] args) throws IOException {
        int d;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        d = Integer.parseInt(st.nextToken());
        thr = (int) Math.pow(10.0, d) - 1; // D 자리 최대값 999...9
        p = Integer.parseInt(st.nextToken()); // 연산 횟수
        max = FAIL;
        dfs(0, 9, 1); // DFS(연산 횟수, 이전에 곱한 값, 계산 결과)
        System.out.print(max);
    }
}
