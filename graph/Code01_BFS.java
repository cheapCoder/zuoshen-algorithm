package graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;


public class Code01_BFS {

	// public static void bfs(Node node) {
	// if (node == null) {
	// return;
	// }
	// Queue<Node> queue = new LinkedList<>();
	// HashSet<Node> map = new HashSet<>();
	// queue.add(node);
	// map.add(node);
	// while (!queue.isEmpty()) {
	// Node cur = queue.poll();
	// System.out.println(cur.value);
	// for (Node next : cur.nexts) {
	// if (!map.contains(next)) {
	// map.add(next);
	// queue.add(next);
	// }
	// }
	// }
	// }

	public static void bfs(Node node) {
		if (node == null) {
			return;
		}

		Queue<Node> queue = new LinkedList<>();
		HashSet<Node> set = new HashSet<>();
		Node tem = null;
		
		set.add(node);
		queue.add(node);

		while (!queue.isEmpty()) {
			tem = queue.poll();
			System.out.println(tem.value);
			for (Node tem2 : tem.nexts) {
				if (!set.contains(tem2)) {
					queue.add(tem2);
					set.add(tem2);
				}
			}
		}

	}

}
