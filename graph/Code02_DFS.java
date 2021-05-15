package graph;

import java.util.HashSet;
import java.util.Stack;

public class Code02_DFS {

	// public static void dfs(Node node) {
	// if (node == null) {
	// return;
	// }
	// Stack<Node> stack = new Stack<>();
	// HashSet<Node> set = new HashSet<>();
	// stack.add(node);
	// set.add(node);
	// System.out.println(node.value);
	// while (!stack.isEmpty()) {
	// Node cur = stack.pop();
	// for (Node next : cur.nexts) {
	// if (!set.contains(next)) {
	// stack.push(cur);
	// stack.push(next);
	// set.add(next);
	// System.out.println(next.value);
	// break;
	// }
	// }
	// }
	// }
	public static void dfs(Node node) {
		if (node == null) {
			return;
		}

		HashSet<Node> set = new HashSet<>();
		Stack<Node> stack = new Stack<>();
		Node cur = null;
		
		stack.add(node);
		set.add(node);
		System.out.println(node.value);

		while (!stack.isEmpty()) {
			cur = stack.pop();
			for (Node tem : cur.nexts) {
				if (!set.contains(tem)) {
					stack.add(cur);
					stack.add(tem);
					System.out.println(tem.value);
					break;
				}
			}
		}
	}

}
