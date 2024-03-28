import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TreeAndQuery15681 {
	static List<List<Integer>> grp;
	static boolean[] visited;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		grp = new ArrayList<>();
		visited = new boolean[N+1];
		dp = new int[N+1];

		for (int i = 0; i < N+1; i++) {
			grp.add(new ArrayList<>());
		}

		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int N1 = Integer.parseInt(st.nextToken());
			int N2= Integer.parseInt(st.nextToken());

			grp.get(N1).add(N2);
			grp.get(N2).add(N1);
		}

		visited[R] = true;
		dfs(R);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Q; i++) {
			int query = Integer.parseInt(br.readLine());
			sb.append(dp[query]).append("\n");
		}

		System.out.println(sb);
	}

	private static int dfs(int currNode) {
		int children = 1;

		for(int node : grp.get(currNode)) {
			if (visited[node]) {
				continue;
			}

			visited[node] = true;
			children += dfs(node);
		}

		dp[currNode] = children;
		return children;
	}
}
