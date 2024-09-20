import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [BOJ] 호텔 / Gold 4 / 15m
 * - 64 ms
 * - Knapsack
 * - dp[i] = i 명의 고객을 늘리기 위한 최소 비용
 * - 현재 정보의 고객 이하 -> 현재 정보의 비용으로 가능
 * - 현재 정보의 고객 초과
 * - -> dp[i]는 (dp[i - 고객] + 비용)과 비교하여 작은 쪽으로 갱신
 * */
public class BOJ1106_호텔 {
    private static final int INF = Integer.MAX_VALUE >> 1;

    public static void main(String[] args) throws IOException {
        int c;
        int n;
        int cost;
        int customer;
        int i;
        int[] dp;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        dp = new int[c + 1]; // dp[i] = i 명의 고객을 늘리기 위한 최소 비용
        for (i = 1; i <= c; i++) {
            dp[i] = INF;
        }
        while (n-- > 0) { // N 개의 도시 정보
            st = new StringTokenizer(br.readLine(), " ", false);
            cost = Integer.parseInt(st.nextToken()); // 비용
            customer = Integer.parseInt(st.nextToken()); // 고객
            for (i = Math.min(customer, c); i >= 0; i--) { // 현재 정보의 고객 이하
                dp[i] = Math.min(dp[i], cost); // 현재 정보의 비용으로 가능
            }
            for (i = customer + 1; i <= c; i++) { // 현재 정보의 고객 초과
                dp[i] = Math.min(dp[i], dp[i - customer] + cost); // dp[i]는 (dp[i - 고객] + 비용)과 비교
            }
        }
        System.out.print(dp[c]); // dp[C] = C 명의 고객을 늘리기 위한 최소 비용
    }
}
