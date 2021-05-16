package binary_tree;

import java.util.LinkedList;

public class Code05_IsCBT {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	// public static boolean isCBT(Node head) { // 完全二叉树
	// if (head == null) {
	// return true;
	// }
	// LinkedList<Node> queue = new LinkedList<>();
	// boolean leaf = false;
	// Node l = null;
	// Node r = null;
	// queue.add(head);
	// while (!queue.isEmpty()) {
	// head = queue.poll();
	// l = head.left;
	// r = head.right;
	// if ((leaf && (l != null || r != null)) || (l == null && r != null)) {
	// return false;
	// }
	// if (l != null) {
	// queue.add(l);
	// }
	// if (r != null) {
	// queue.add(r);
	// } else {
	// leaf = true;
	// }
	// }
	// return true;
	// }

	public static boolean isCBT(Node head) {		// 完全二叉树
		if (head == null) {
			return true;
		}

		LinkedList<Node> tem = new LinkedList<>();
		tem.add(head);
		boolean shouldBeLeaf = false;
		while (!tem.isEmpty()) {
			head = tem.poll();
			if (head.right != null && head.left == null) {
				return false;
			}

			if (shouldBeLeaf && (head.left != null || head.right != null)) {
				return false;
			}

			if (head.left != null) {
				tem.add(head.left);
			} else {
				shouldBeLeaf = true;
			}
			if (head.right != null) {
				tem.add(head.right);
			} else {
				shouldBeLeaf = true;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		Node head = new Node(5);
		head.left = new Node(3);
		head.right = new Node(8);
		head.left.left = new Node(2);
		head.left.right = new Node(4);
		head.left.right.left = new Node(12);
		head.left.right.right = new Node(13);
		head.left.left.left = new Node(1);
		head.left.left.right = new Node(10);
		head.right.left = new Node(7);
		head.right.left.left = new Node(6);
		// head.right.left.right = new Node(15);
		head.right.right = new Node(10);
		// head.right.right.left = new Node(9);
		// head.right.right.right = new Node(11);
		System.out.println(isCBT(head));
	}

}
