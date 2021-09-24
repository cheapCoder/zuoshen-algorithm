package class02;

import java.util.HashMap;
import java.util.Stack;

public class Problem07_MaxSumInTree {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int val) {
			value = val;
		}
	}

	public static int maxSumRecursive(Node head) {
		return process(head, 0);
	}

	public static int process(Node x, int pre) {
		if (x == null) {
			return Integer.MIN_VALUE;
		}
		if (x.left == null && x.right == null) {
			return pre + x.value;
		}
		int leftMax = process(x.left, pre + x.value);
		int rightMax = process(x.right, pre + x.value);
		return Math.max(leftMax, rightMax);
	}

	public static int maxSumUnrecursive(Node head) {
		int max = 0;
		HashMap<Node, Integer> sumMap = new HashMap<>();
		if (head != null) {
			Stack<Node> stack = new Stack<Node>();
			stack.add(head);
			sumMap.put(head, head.value);
			while (!stack.isEmpty()) {
				head = stack.pop();
				if (head.left == null && head.right == null) {
					max = Math.max(max, sumMap.get(head));
				}
				if (head.right != null) {
					sumMap.put(head.right, sumMap.get(head) + head.right.value);
					stack.push(head.right);
				}
				if (head.left != null) {
					sumMap.put(head.left, sumMap.get(head) + head.left.value);
					stack.push(head.left);
				}
			}
		}
		return max;
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
