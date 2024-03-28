import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class 여행경로_level3 {
	static class Ticket {
		int ticketNo;
		String arrival;

		public Ticket(int ticketNo, String arrival) {
			this.ticketNo = ticketNo;
			this.arrival = arrival;
		}
	}

	static class State {
		Set<Integer> ticketUsed;
		List<String> paths;

		public State() {
			this.ticketUsed = new HashSet<>();
			this.paths = new ArrayList<>();
			this.paths.add("ICN");
		}

		public State(Set<Integer> ticketUsed, List<String> paths) {
			this.ticketUsed = ticketUsed;
			this.paths = paths;
		}

		public String getLatestArrival() {
			return paths.get(paths.size() - 1);
		}

		public boolean isUsedTicket(int ticketNo) {
			return ticketUsed.contains(ticketNo);
		}

		public State addTicketUsed(Ticket ticket) {
			Set<Integer> copiedTicketUsed = new HashSet<>(this.ticketUsed);  //시간 소요발생 -> copy
			List<String> copiedPaths = new ArrayList<>(this.paths);

			copiedTicketUsed.add(ticket.ticketNo);
			copiedPaths.add(ticket.arrival);

			return new State(copiedTicketUsed, copiedPaths);
		}

		public boolean isCompletedTravel() {
			return this.ticketUsed.size() == ticketCount;
		}
	}

	static int ticketCount;
	Map<String, List<Ticket>> grp;

	public String[] solution(String[][] tickets) {
		grp = new HashMap<>();
		ticketCount = tickets.length;

		for (int i = 0; i < tickets.length; i++) {
			String[] ticket = tickets[i];
			String start = ticket[0];
			String end = ticket[1];

			if (!grp.containsKey(start)) {
				grp.put(start, new ArrayList<>());
			}
			grp.get(start).add(new Ticket(i, end));
		}

		Queue<State> q = new ArrayDeque<>();
		q.add(new State());  //path: ["ICN"] , ticketUsed: [] 로 시작하는 상태에서 bfs 시작

		List<List<String>> answers = new ArrayList<>();
		//bfs
		while (!q.isEmpty()) {
			State currState = q.poll();
			String departure = currState.getLatestArrival();

			if (grp.containsKey(departure)) {
				for (Ticket ticket : grp.get(departure)) {
					if (currState.isUsedTicket(ticket.ticketNo)) {
						continue;
					}

					State updatedState = currState.addTicketUsed(ticket);
					q.add(updatedState);
				}
			}

			if (currState.isCompletedTravel()){
				answers.add(currState.paths);
			}
		}
		return Collections.min(answers, Comparator.comparing(Object::toString)).toArray(new String[0]);
	}
}
