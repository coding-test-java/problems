import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [BOJ] 재활용 캠페인 / Gold 2 / 25m
 * - 304 ms
 * - 투 포인터
 * - 헤어 에센스 3 개를 합치면 항상 가득 참
 * - 1 개로 이미 가득 찬 헤어에센스 수
 * -   + 2 개로 교환하여 가득 채울 수 있는 경우의 수
 * -   + 나머지 헤어에센스 / 3
 * - 헤어에센스 정렬 후 뒤에서부터 가득 찬 헤어에센스 제외
 * - 투 포인터 : 2 개로 교환하여 가득 채울 수 있는 경우의 수 계산
 * - left 와 right 로 가득 채울 수 있으면 left 증가 right 감소
 * - 가득 채울 수 없으면 left 만 증가
 * */
public class BOJ22988_재활용캠페인 {
    public static void main(String[] args) throws IOException {
        int n;
        int i;
        int cnt;
        int full;
        int left;
        int right;
        long x;
        long half;
        long[] arr;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        n = Integer.parseInt(st.nextToken());
        x = Long.parseLong(st.nextToken()); // 헤어에센스 용기 용량
        arr = new long[n + 1];
        st = new StringTokenizer(br.readLine(), " ", false);
        for (i = 1; i <= n; i++) { // 헤어에센스 잔여량 입력
            arr[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(arr); // 헤어에센스 정렬
        for (right = n; arr[right] == x; right--); // 뒤에서부터 가득 찬 헤어에센스 제외
        full = n - right; // 1 개로 이미 가득 찬 헤어에센스 수
        cnt = 0; // 2 개로 교환하여 가득 채울 수 있는 경우의 수 계산
        half = x >> 1;
        if ((x & 1L) == 0L) {
            half--;
        } // left 와 right 합이 half 를 초과해야 교환하여 가득 채움
        left = 1;
        while (left < right) { // 투 포인터
            if (arr[left] + arr[right] > half) { // left 와 right 로 가득 채울 수 있으면
                cnt++; // 2 개로 교환하여 가득 채울 수 있는 경우의 수
                right--; // right 감소
            }
            left++; // left 증가
        }
        System.out.print(full + cnt + (n - full - (cnt << 1)) / 3); // 헤어 에센스 3 개를 합치면 항상 가득 참
    }
}
