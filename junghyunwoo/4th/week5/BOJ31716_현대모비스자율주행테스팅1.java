import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [BOJ] 현대모비스 자율 주행 테스팅 1 / Gold 3 / 15m
 * - 84 ms
 * - 그리디
 * - 시작 열에 장애물이 두 개면 완주 불가
 * - K > 1 이고, 시작 열과 도착 열에
 * -   대각으로 장애물이 있으면 완주 불가
 * - 첫 장애물 위치의 반대 차선으로 시작점 결정
 * - 첫 장애물 옆까지 직진
 * - 현재 차선 앞에 장애물이 없으면 직진
 * - 현재 차선 앞에 장애물이 존재하면
 * -   차선 변경할 위치, 이후 직진할 위치 중 장애물이 없으면
 * -     차선 변경 후 직진
 * -   둘 중 하나라도 장애물이 존재하면 완주 불가
 * - 도착 지점 차선과 시작 지점 차선이 같으면
 * -   직진하여 다음 샘플 주행 트랙으로 이동
 * - 도착 지점 차선과 시작 지점 차선이 다르면
 * -   직진 후 차선 변경 혹은 차선 변경 후 직진
 * - (샘플 주행 트랙 이동 * K)
 * -   + (트랙간 이동 * (K - 1)) 출력
 * */
public class BOJ31716_현대모비스자율주행테스팅1 {
    private static final char OBSTACLE = '#';
    private static final char[] FAIL = {'-', '1'};

    public static void main(String[] args) throws IOException {
        int n;
        int i;
        int cnt;
        int add;
        int lane;
        int start;
        long k;
        char[][] map;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new char[2][n];
        br.read(map[0], 0, n);
        br.read();
        br.read(map[1], 0, n);
        if ((map[0][0] == OBSTACLE && map[1][0] == OBSTACLE) || (k > 1
                && ((map[0][n - 1] == OBSTACLE && map[1][0] == OBSTACLE)
                || (map[1][n - 1] == OBSTACLE && map[0][0] == OBSTACLE)))) {
            System.out.print(FAIL); // 시작 열에 장애물이 두 개면 완주 불가
            return; // K > 1 이고, 시작 열과 도착 열에 대각으로 장애물이 있으면 완주 불가
        }
        start = lane = 0;
        for (i = 0; i < n; i++) { // 첫 장애물 위치의 반대 차선으로 시작점 결정
            if (map[0][i] == OBSTACLE) {
                start = lane = 1;
                break;
            } else if (map[1][i] == OBSTACLE) {
                start = lane = 0;
                break;
            }
        }
        if (i == n) {
            i--;
        }
        cnt = i; // 첫 장애물 옆까지 직진
        for (++i; i < n; i++) {
            if (map[lane][i] != OBSTACLE) { // 현재 차선 앞에 장애물이 없으면
                cnt++; // 직진
            } else if (map[lane ^= 1][i - 1] != OBSTACLE && map[lane][i] != OBSTACLE) {
                cnt += 2; // 차선 변경할 위치, 이후 직진할 위치 중 장애물이 없으면 차선 변경 후 직진
            } else { // 둘 중 하나라도 장애물이 존재하면
                System.out.print(FAIL); // 완주 불가
                return;
            }
        }
        if (lane == start) { // 도착 지점 차선과 시작 지점 차선이 같으면
            add = 1; // 직진하여 다음 샘플 주행 트랙으로 이동
        } else { // 도착 지점 차선과 시작 지점 차선이 다르면
            add = 2; // 직진 후 차선 변경 혹은 차선 변경 후 직진
        } // (샘플 주행 트랙 이동 * K) + (트랙간 이동 * (K - 1)) 출력
        System.out.print(cnt * k + add * (k - 1L));
    }
}
