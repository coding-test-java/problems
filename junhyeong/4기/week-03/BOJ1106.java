import java.io.*;
import java.util.*;

public class BOJ1106 {
    static int C, N;
    static int[] dp;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        ans = Integer.MAX_VALUE;

        dp = new int[C + 101];
        Arrays.fill(dp, 99999999);
        dp[0] = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int people = Integer.parseInt(st.nextToken());
            for (int j = people; j < C + 101; j++) {
                dp[j] = Math.min(dp[j], cost + dp[j - people]);
            }
        }

        for (int i = C; i < C + 101; i++) {
            ans = Math.min(ans, dp[i]);
        }
        System.out.println(ans);
    }
}
