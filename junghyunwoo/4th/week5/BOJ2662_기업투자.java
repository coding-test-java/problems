import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [BOJ] 기업투자 / Gold 2 / 15m
 * - 100 ms
 * - Knapsack
 * - 각 기업별, 투자 액수별 DP 수행
 * - 새로운 이익 계산 시 이전 기업까지의 결과만 참조
 * - 이익 크기 비교는 현재까지의 결과에서 비교
 * - 이익이 업데이트 되면 해당 위치에 마지막 투자 액수 저장
 * - 최대 이익 출력 후 마지막 투자 액수를 거꾸로 따라가면서
 * -   각 기업에 투자한 액수 추적
 * */
public class BOJ2662_기업투자 {
    private static final char SPACE = ' ';
    private static final char LINE_BREAK = '\n';

    public static void main(String[] args) throws IOException {
        int n;
        int m;
        int i;
        int j;
        int k;
        int val;
        int profit;
        int[] prev;
        int[] curr;
        int[] invest;
        int[] profits;
        int[][] ans;
        int[][] companies;
        StringBuilder sb;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        companies = new int[m][n + 1]; // 각 기업, 투자 액수별 이익금
        curr = new int[n + 1]; // 현재 DP 값
        ans = new int[m][n + 1]; // 상태별 마지막 투자 액수
        invest = ans[0]; // 첫 기업 투자 액수
        for (i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ", false);
            st.nextToken();
            for (j = m - 1; j > 0; j--) { // 마지막 출력 시 거꾸로 추적하므로 기업 순서 뒤집기
                companies[j][i] = Integer.parseInt(st.nextToken());
            }
            curr[i] = Integer.parseInt(st.nextToken()); // 마지막 기업은 DP 값으로 바로 적용
            invest[i] = i; // 투자 액수 저장
        }
        prev = new int[n + 1]; // 이전 DP 저장용
        for (i = 1; i < m; i++) {
            System.arraycopy(curr, 1, prev, 1, n); // 이전 상태 업데이트
            profits = companies[i]; // i 번 기업의 투자 액수별 이익금
            invest = ans[i]; // i 번 기업까지 계산했을 때 마지막 투자 액수
            for (j = 1; j <= n; j++) {
                profit = profits[j]; // i 번 기업의 투자 액수 j 에 대한 이익금
                for (k = j; k <= n; k++) {
                    val = prev[k - j] + profit; // 새로운 이익 계산
                    if (val > curr[k]) { // 새로운 이익이 현재 DP 값보다 크면
                        invest[k] = j; // 마지막 투자 액수 업데이트
                        curr[k] = val; // DP 값 업데이트
                    }
                }
            }
        }
        sb = new StringBuilder();
        sb.append(curr[n]).append(LINE_BREAK); // 최대 이익금
        j = n;
        for (i = m - 1; i >= 0; i--) { // 기업 역추적
            sb.append(ans[i][j]).append(SPACE); // 해당 기업에 투자한 액수 출력
            j -= ans[i][j]; // 투자금 합 역추적
        }
        System.out.print(sb.toString());
    }
}
