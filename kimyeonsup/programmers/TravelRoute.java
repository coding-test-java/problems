package pgs;

import java.util.*;

public class TravelRoute {
    static String[] solution;
    private Map<String, Deque<String>> treeMap;
    private List<String> paths = new ArrayList<>();
    private Map<String, Integer> ticketCount;

    public static void main(String[] args) {
//        String[][] param = {{"ICN", "BOO"}, {"ICN", "COO"}, {"COO", "DOO"}, {"DOO", "COO"}, {"BOO", "DOO"}, {"DOO", "BOO"}, {"BOO", "ICN"}, {"COO", "BOO"}};
//        String[][] param = {{"ICN","A"},{"A","B"},{"A","C"},{"B","A"},{"C","A"}};
        String[][] param = {{"ICN", "A"}, {"A", "B"}, {"A", "C"}, {"C", "A"}, {"B", "D"}};
//        String[][] param = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};
//        String[] solution = new Solution().solution(new String[][]{
//                {"ICN", "JFK"},
//                {"HND", "IAD"},
//                {"JFK", "HND"}
//        });
//        System.out.println(Arrays.toString(solution));
        solution = new TravelRoute().solution(param);
        System.out.println(Arrays.toString(solution));
    }

    public String[] solution(String[][] tickets) {
        String[] answer = {};

        treeMap = new TreeMap<>();
        ticketCount = new HashMap<>();

        for (int i = 0; i < tickets.length; i++) {
            String from = tickets[i][0];
            String to = tickets[i][1];

            Deque<String> toAirLines = treeMap.getOrDefault(from, new ArrayDeque<>());
            toAirLines.add(to);
            treeMap.put(from, toAirLines);
            ticketCount.put(from, ticketCount.getOrDefault(from, 0) + 1);
        }

        dfs("ICN", tickets.length, "");

        Collections.sort(paths);
        return paths.get(0).split(" ");
    }

    private void dfs(String from, int cnt, String path) {
        Deque<String> toAirlines = treeMap.get(from);

        if (cnt == 0) {
            paths.add(path + from + " ");
            return;
        }

        if (toAirlines == null) return;


        int size = toAirlines.size();
        if (size == 0) return;
        String[] toList = toAirlines.toArray(new String[size]);
        for (int i = 0; i < toList.length; i++) {
            String to = toList[i];
            toAirlines.poll();

            if (to != null) {
                dfs(to, cnt - 1, path + from + " ");
                toAirlines.offer(to);
            }
        }
    }
}
