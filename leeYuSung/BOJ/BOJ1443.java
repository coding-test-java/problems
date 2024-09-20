import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1443 {
    static int D;
    static int P;
    static int max;
    static long limit; // 자릿수에 따른 최대값을 저장할 변수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        D = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        limit = (long) Math.pow(10, D);

        max = -1;
        recur(0, 1, 9);
        System.out.println(max);
    }

    static void recur(int time, long num, int start) {
        if (num >= limit) {
            return;
        }

        if (time == P) {//time == P 일 때 max 계산
            max = Math.max(max, (int) num);
            return;
        }

        for (int i = start; i >= 2; i--) {
            recur(time + 1, num * i, i);
        }
    }
}
