package skill;

import java.util.HashMap;
import java.util.Stack;

// 二叉树每个结点都有一个int型权值，
// 给定一棵二叉树，要求计算出从根结点到叶结点的所有路径中，最大的权值和为多少。

public class C14_MaxSumInTree {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int val) {
			value = val;
		}
	}

	// public static int maxSumRecursive(Node head) {
	// return process(head, 0);
	// }

	// public static int process(Node x, int pre) {
	// if (x == null) {
	// return Integer.MIN_VALUE;
	// }
	// if (x.left == null && x.right == null) {
	// return pre + x.value;
	// }
	// int leftMax = process(x.left, pre + x.value);
	// int rightMax = process(x.right, pre + x.value);
	// return Math.max(leftMax, rightMax);
	// }

	// public static int maxSumUnrecursive(Node head) {
	// int max = 0;
	// HashMap<Node, Integer> sumMap = new HashMap<>();
	// if (head != null) {
	// Stack<Node> stack = new Stack<Node>();
	// stack.add(head);
	// sumMap.put(head, head.value);
	// while (!stack.isEmpty()) {
	// head = stack.pop();
	// if (head.left == null && head.right == null) {
	// max = Math.max(max, sumMap.get(head));
	// }
	// if (head.right != null) {
	// sumMap.put(head.right, sumMap.get(head) + head.right.value);
	// stack.push(head.right);
	// }
	// if (head.left != null) {
	// sumMap.put(head.left, sumMap.get(head) + head.left.value);
	// stack.push(head.left);
	// }
	// }
	// }
	// return max;
	// }
	public static int maxSumRecursive(Node head) {
		if (head == null) {
			return 0;
		}

		return Math.max(head.left == null ? 0 : maxSumRecursive(head.left),
				head.right == null ? 0 : maxSumRecursive(head.right)) + head.value;
	}

	public static int maxSumUnrecursive(Node head) {
		if (head == null) {
			return 0;
		}

		int res = 0;
		// NOTE: 有瑕疵，若树有存在值相同的node呢
		HashMap<Node, Integer> preSumMap = new HashMap<>();
		preSumMap.put(head, head.value);

		Stack<Node> recurStack = new Stack<>();
		recurStack.push(head);

		while (!recurStack.isEmpty()) {
			head = recurStack.pop();
			if (head.left == null && head.right == null) {
				res = Math.max(res, preSumMap.get(head));
			}
			if (head.left != null) {
				preSumMap.put(head.left, head.left.value + preSumMap.get(head));
				recurStack.push(head.left);
			}
			if (head.right != null) {
				preSumMap.put(head.right, head.right.value + preSumMap.get(head));
				recurStack.push(head.right);
			}
		}
		return res;
	}

	public static void main(String[] args) {
		Node head = new Node(4);
		head.left = new Node(1);
		head.left.right = new Node(5);
		head.right = new Node(-7);
		head.right.left = new Node(3);
		System.out.println(maxSumRecursive(head));
		System.out.println(maxSumUnrecursive(head));

	}

}
