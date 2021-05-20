package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Code03_TopologySort {

	// directed graph and no loop
	// public static List<Node> sortedTopology(Graph graph) {
	// HashMap<Node, Integer> inMap = new HashMap<>();
	// Queue<Node> zeroInQueue = new LinkedList<>();
	// for (Node node : graph.nodes.values()) {
	// inMap.put(node, node.in);
	// if (node.in == 0) {
	// zeroInQueue.add(node);
	// }
	// }
	// List<Node> result = new ArrayList<>();
	// while (!zeroInQueue.isEmpty()) {
	// Node cur = zeroInQueue.poll();
	// result.add(cur);
	// for (Node next : cur.nexts) {
	// inMap.put(next, inMap.get(next) - 1);
	// if (inMap.get(next) == 0) {
	// zeroInQueue.add(next);
	// }
	// }
	// }
	// return result;
	// }

	public static List<Node> sortedTopology(Graph graph) { // 拓扑排序
		List<Node> result = new ArrayList<>();
		HashMap<Node, Integer> inMap = new HashMap<>();
		Queue<Node> queue = new LinkedList<>();

		for (Node node : graph.nodes.values()) {
			inMap.put(node, node.in);
			if (node.in == 0) {
				queue.add(node);
			}
		}

		Node cur = null;
		while (!queue.isEmpty()) {
			cur = queue.poll();
			result.add(cur);
			for (Node node : cur.nexts) {
				inMap.put(node, inMap.get(node) - 1);
				if (inMap.get(node) == 0) {
					queue.add(node);
				}
			}
		}
		return result;
	}
}
