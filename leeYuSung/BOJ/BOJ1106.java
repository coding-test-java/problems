import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1106 {
    static int c;
    static int n;
    static int [][] table;
    static int [][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        table = new int[n][2];
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            table[i][0] = Integer.parseInt(st.nextToken());
            table[i][1] = Integer.parseInt(st.nextToken());
        }
        dp = new int[n][1000];
        for(int i = 0; i<n; i++){
            for(int j = 0; j<1000; j++){
                dp[i][j] = 101;
            }
        }
        System.out.println(sol(0,0));
    }

    static int sol(int idx, int people){
        //중복도 가능해야함 -> 만약 도시를 다 돌았는데 사람을 못채웠다면 다시 idx 0으로 가면 되지 않을까 X
        // 선택을 하면 넘어가지 않는 방식으로 해서 중복을 허용하게
        if(people >= c){//목표 고객 달성
            return 0;
        }
        if(idx == n){ //도시 다 돌았지만 목표고객 x
            return Integer.MAX_VALUE;
        }
        if(dp[idx][people] != 101){ //중복해서 계산하는 것 방지
            return dp[idx][people];
        }

        dp[idx][people] = Math.min(sol(idx+1,people),sol(idx,people+table[idx][1]) + table[idx][0]);
        return dp[idx][people];
    }
}
