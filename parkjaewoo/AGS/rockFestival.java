package AGS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class rockFestival {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int C, N, L, num;
        int[] prefixSum;
        C = Integer.parseInt(st.nextToken());

        for (int x = 0; x < C; x++) {
            double min = Double.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            prefixSum = new int[N + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i < N + 1; i++) {
                num = Integer.parseInt(st.nextToken());
                prefixSum[i] = prefixSum[i - 1] + num;
            }

            int start, end;
            start = 0;
            end = L;
            for (int j = start; j < N - end + 1; j++) {
                for (int k = j + end; k < N + 1; k++) {
                    double tmp = (prefixSum[k] - prefixSum[j]) / (double)(k - j);
                    min = Math.min(min, tmp);
                }
            }

            System.out.printf("%.11f\n",min);
        }
    }
}
