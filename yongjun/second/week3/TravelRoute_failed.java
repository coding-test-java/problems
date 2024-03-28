package second.week3;

import java.util.*;

class TravelRoute_failed {
    ArrayList<String> result = new ArrayList<>();
    boolean[] visited = new boolean[10000];

    public String[] solution(String[][] tickets) {
        dfs(tickets, "ICN");

        int size = result.size();
        String[] answer = new String[size + 1];
        answer[0] = "ICN";
        for(int i = 0; i < size; i++){
            answer[i + 1] = result.get(i);
        }
        return answer;
    }

    public void dfs(String[][] tickets, String arrivalPort) {
        ArrayList<Airport> airports = new ArrayList<>();
        boolean hasArrivalPort = false;

        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i][0].equals(arrivalPort) && !visited[i]) {
                airports.add(new Airport(i, tickets[i][1]));
                hasArrivalPort = true;
            }
        }
        if (hasArrivalPort) {
            Collections.sort(airports);

            Airport firstPort = airports.get(0);
            visited[firstPort.idx] = true;
            result.add(firstPort.name);
            dfs(tickets, firstPort.name);
        }
    }

    class Airport implements Comparable<Airport> {
        int idx;
        String name;

        public Airport(int idx, String name) {
            this.idx = idx;
            this.name = name;
        }

        @Override
        public int compareTo(Airport o) {
            return this.name.compareTo(o.name);
        }
    }
}