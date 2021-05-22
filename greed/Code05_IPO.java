package greed;

import java.util.PriorityQueue;

public class Code05_IPO {
	public static class Node {
		public int p;
		public int c;

		public Node(int p, int c) {
			this.p = p;
			this.c = c;
		}
	}

	// public static class MinCostComparator implements Comparator<Node> {

	// @Override
	// public int compare(Node o1, Node o2) {
	// return o1.c - o2.c;
	// }

	// }

	// public static class MaxProfitComparator implements Comparator<Node> {

	// @Override
	// public int compare(Node o1, Node o2) {
	// return o2.p - o1.p;
	// }

	// }

	// public static int findMaximizedCapital(int k, int W, int[] Profits, int[]
	// Capital) {
	// Node[] nodes = new Node[Profits.length];
	// for (int i = 0; i < Profits.length; i++) {
	// nodes[i] = new Node(Profits[i], Capital[i]);
	// }

	// PriorityQueue<Node> minCostQ = new PriorityQueue<>(new MinCostComparator());
	// PriorityQueue<Node> maxProfitQ = new PriorityQueue<>(new
	// MaxProfitComparator());
	// for (int i = 0; i < nodes.length; i++) {
	// minCostQ.add(nodes[i]);
	// }
	// for (int i = 0; i < k; i++) {
	// while (!minCostQ.isEmpty() && minCostQ.peek().c <= W) {
	// maxProfitQ.add(minCostQ.poll());
	// }
	// if (maxProfitQ.isEmpty()) {
	// return W;
	// }
	// W += maxProfitQ.poll().p;
	// }
	// return W;
	// }

	public static int getMaximizedCapital(int[] costs, int[] profits, int k, int m) {
		Node[] projects = new Node[profits.length];
		for (int i = 0; i < profits.length; i++) {
			projects[i] = new Node(profits[i], costs[i]);
		}

		PriorityQueue<Node> minCostQueue = new PriorityQueue<>((Node node1, Node node2) -> {
			return node1.c - node2.c;
		});
		PriorityQueue<Node> validProjectQueue = new PriorityQueue<>((Node node1, Node node2) -> {
			return node2.p - node1.p;
		});
		for (int i = 0; i < projects.length; i++) {
			minCostQueue.add(projects[i]);
		}

		for (int i = 0; i < k; i++) {
			while (m >= minCostQueue.peek().c) {
				validProjectQueue.add(minCostQueue.poll());
			}
			if (validProjectQueue.isEmpty()) {
				return m;
			}
			m += validProjectQueue.poll().p;
		}
		return m;
	}

}
