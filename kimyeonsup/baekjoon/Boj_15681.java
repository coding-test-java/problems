package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj_15681 {

    private static int n, r, q;

    private static int[] treeArray;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer> list = new ArrayList<>();
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        treeArray = new int[n + 1];

        List<List<Integer>> tree = new ArrayList<>();

        for (int i = 0; i < n + 1; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        dfs(r, tree, 0);

        for (int i = 0; i < q; i++) {
            System.out.println(treeArray[Integer.parseInt(br.readLine())]);
        }
    }

    private static int dfs(int node, List<List<Integer>> tree, int prev) {
        treeArray[node]++;
        int cnt = 0;
        for (Integer next : tree.get(node)) {
            if (next != prev) {
                cnt += dfs(next, tree, node);
            }
        }
        treeArray[node] += cnt;
        return 1 + cnt;
    }
}
