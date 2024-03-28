package second.week3;

import java.util.*;
class Solution {
    PriorityQueue<String> queue;
    boolean[] visited;
    public String[] solution(String[][] tickets) {
        queue = new PriorityQueue<>();
        visited = new boolean[tickets.length];
        dfs("ICN","ICN",tickets,1);
        return queue.peek().split(" ");
    }
    public void dfs(String start, String route, String[][] tickets, int cnt){
        if(cnt == tickets.length + 1){
            queue.add(route);
            return;
        }
        for(int i = 0; i < tickets.length; i++){
            if(tickets[i][0].equals(start) && !visited[i]){
                visited[i] = true;
                dfs(tickets[i][1], route + " " + tickets[i][1] , tickets, cnt + 1);
                visited[i] = false;
            }
        }

    }
}