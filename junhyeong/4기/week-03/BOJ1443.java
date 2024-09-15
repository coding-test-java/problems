import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
* 2 <= d <= 8
* 0 < p <= 30
* */
public class BOJ1443 {
    static int d;
    static int p;
    static int max_int;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        d = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());

        answer = 0;
        max_int = get_max(d);
        solve(1, 0, 9);
        System.out.println(answer == 0 ? -1 : answer);
    }

    private static void solve(int sum, int dep, int num) {
        if (sum > max_int)
            return;

        if (dep == p) {
            answer = Math.max(answer, sum);
            return;
        }

        for (int i = num; i >= 2; i--) {
            solve(sum * i, dep + 1, i);
        }
    }

    private static int get_max(int n) {
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans *= 10;
            ans += 9;
        }
        return ans;
    }
}
